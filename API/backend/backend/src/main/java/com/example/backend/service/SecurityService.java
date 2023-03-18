package com.example.backend.service;

import com.example.backend.domain.Member;
import com.example.backend.exception.BadRequestException;
import com.example.backend.repository.MemberRepository;
import com.example.backend.request.RegisterMemberRequest;
import com.example.backend.result.ActionResult;
import com.example.backend.security.VerificationToken;
import com.example.backend.security.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final MemberRepository memberRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public ActionResult register(RegisterMemberRequest request) {
        boolean userAlreadyExists = memberRepository.existsByEmail(request.getEmail());
        if(userAlreadyExists) {
            throw new BadRequestException("User with email: " + request.getEmail() + " already exists!");
        }

        Member securityUser = Member.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role("ROLE_MEMBER")
                .phoneNumber(request.getPhoneNumber())
                .active(false)
                .build();

        memberRepository.save(securityUser);

        String token = UUID.randomUUID().toString();
        verificationTokenRepository.save(new VerificationToken(token,securityUser));

        emailService.sendConfirmationEmail(securityUser,token);
        return new ActionResult(true, "Confirmation mail successfully sent!");
    }

    public ActionResult confirmEmail(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token not found!"));

        Member securityUser = verificationToken.getMember();
        if(securityUser == null)
            return new ActionResult(false,"User not in database!");

        if(checkExpired(verificationToken)){
            verificationTokenRepository.deleteByToken(token);
            memberRepository.deleteByEmail(securityUser.getEmail());
            return new ActionResult(false, "Verification token has expired!");
        }

        securityUser.setActive(true);
        memberRepository.save(securityUser);
        verificationTokenRepository.deleteByToken(token);

        return new ActionResult(true,"Successfully Logged in");
    }

        private boolean checkExpired(VerificationToken verificationToken){
        Calendar cal = Calendar.getInstance();
        return (verificationToken.getExpirationDate().getTime() - cal.getTime().getTime()) <= 0;
    }

    @SneakyThrows
    public ActionResult login(String token) {
        String email = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member securityUser = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        if(!securityUser.getActive())
            throw new BadRequestException("User is not active!");

        VerificationToken verificationToken = verificationTokenRepository.findByToken(token)
                .orElseThrow(() -> new EntityNotFoundException("Token not found!"));

        if(checkExpired(verificationToken)){
            verificationTokenRepository.deleteByToken(token);
            memberRepository.deleteByEmail(securityUser.getEmail());
            return new ActionResult(false, "Verification token has expired!");
        }
        return new ActionResult(true,"Successfully sent login email!");
    }

    public ActionResult sendCode(String email) {
        boolean userExists = memberRepository.existsByEmail(email);
        if(!userExists) {
            throw new BadRequestException("User with email: " + email + " does not exist!");
        }
        Member securityUser = memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found!"));

        Random random = new Random();
        String token = String.valueOf(random.nextInt(10000));

        verificationTokenRepository.save(new VerificationToken(token,securityUser));

        emailService.sendCode(securityUser,token);
        return new ActionResult(true, "Confirmation mail successfully sent!");
    }
}
