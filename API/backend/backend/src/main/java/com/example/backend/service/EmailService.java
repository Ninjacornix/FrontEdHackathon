package com.example.backend.service;

import com.example.backend.domain.Alert;
import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Component
public class EmailService {

    private final JavaMailSender mailSender;
    private final ExecutorService executorService;
    private final MemberRepository memberRepository;
    private static final Logger logger = LogManager.getLogger(RecordService.class);

    public EmailService(JavaMailSender mailSender, MemberRepository memberRepository) {
        this.mailSender = mailSender;
        this.memberRepository = memberRepository;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    @SneakyThrows
    public void sendConfirmationEmail(Member securityUser, String token) {

        String recipientAddress = securityUser.getEmail();
        String subject = "Confirmation Email";
        String confirmationUrl = "http://localhost:8080/register/confirmEmail?token=" + token;
        String body = "<a href=" + confirmationUrl + ">this</a>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body,true);
        helper.setTo("madzarmaksim@gmail.com");
        helper.setSubject(subject);
        helper.setFrom("ian.balen6@gmail.com");
        mailSender.send(mimeMessage);
        logger.info("Confirmation email sent successfully");
    }


    @SneakyThrows
    public void sendCode(Member securityUser, String token) {
        String recipientAddress = securityUser.getEmail();
        String subject = "Login Email";
        String body = token;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body,true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setFrom("ian.balen6@gmail.com");
        mailSender.send(mimeMessage);
        logger.info("Code sent successfully");
    }

    @SneakyThrows
    public void sendAlert(Alert alert) {
        List<Member> members = memberRepository.findAll();
        List<String> recipientAddresses = members.stream().map(Member::getEmail).toList();
        String subject = "Alert";
        String body = alert.toString();

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body,true);
        helper.setSubject(subject);
        helper.setFrom("ian.balen6@gmail.com");
        for (String recipientAddress : recipientAddresses) {
            helper.setTo(recipientAddress);
            executorService.execute(() -> mailSender.send(mimeMessage));
        }
        logger.info("Alert sent successfully");
    }
}
