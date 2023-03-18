package com.example.backend.service;

import com.example.backend.domain.Member;
import lombok.SneakyThrows;
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

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
        executorService = Executors.newFixedThreadPool(10);
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
        helper.setFrom("madzarmaksim@gmail.com");
        mailSender.send(mimeMessage);
    }
    @SneakyThrows
    public void sendLoginEmail(Member member, String token) {

        String recipientAddress = member.getEmail();
        String subject = "Login Email";
        String confirmationUrl = "http://localhost:8080/login/confirm?token=" + token;
        String body = "<a href=" + confirmationUrl + ">this</a>";

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body,true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setFrom("madzarmaksim@gmail.com");
        mailSender.send(mimeMessage);
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
        helper.setFrom("madzarmaksim@gmail.com");
        mailSender.send(mimeMessage);
    }
}