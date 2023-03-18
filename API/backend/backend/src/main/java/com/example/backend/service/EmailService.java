package com.example.backend.service;

import com.example.backend.domain.Alert;
import com.example.backend.domain.Member;
import com.example.backend.repository.MemberRepository;
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
    private final MemberRepository memberRepository;

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
        helper.setTo("ian.balen6@gmail.com");
        helper.setSubject(subject);
        helper.setFrom("evil.evaluators@gmail.com");
        mailSender.send(mimeMessage);
    }

    @SneakyThrows
    public void sendCode(Member securityUser, String token) {
        String recipientAddress = securityUser.getEmail();
        String subject = "Login Email";
        String body = getBodyForCode(token);

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setText(body,true);
        helper.setTo(recipientAddress);
        helper.setSubject(subject);
        helper.setFrom("evil.evaluators@gmail.com");
        mailSender.send(mimeMessage);
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
        helper.setFrom("evil.evaluators@gmail.com");
        for (String recipientAddress : recipientAddresses) {
            helper.setTo(recipientAddress);
            executorService.execute(() -> mailSender.send(mimeMessage));
        }
    }




    private String getBodyForCode(String token){
        return "<html>\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Two Factor Authentication Code</title>\n" +
                "    <style>\n" +
                "      body {\n" +
                "        font-family: Arial, sans-serif;\n" +
                "        font-size: 16px;\n" +
                "        line-height: 1.5;\n" +
                "      }\n" +
                "      \n" +
                "      .container {\n" +
                "        max-width: 600px;\n" +
                "        margin: 0 auto;\n" +
                "        padding: 20px;\n" +
                "        border: 1px solid #ccc;\n" +
                "      }\n" +
                "      \n" +
                "      h1 {\n" +
                "        margin-top: 0;\n" +
                "      }\n" +
                "      \n" +
                "      p {\n" +
                "        margin-bottom: 20px;\n" +
                "      }\n" +
                "      \n" +
                "      .code {\n" +
                "        display: inline-block;\n" +
                "        font-size: 24px;\n" +
                "        font-weight: bold;\n" +
                "        padding: 10px 20px;\n" +
                "        background-color: #f5f5f5;\n" +
                "        border: 1px solid #ccc;\n" +
                "        border-radius: 4px;\n" +
                "        margin-right: 10px;\n" +
                "      }\n" +
                "      \n" +
                "      .note {\n" +
                "        font-size: 14px;\n" +
                "        color: #999;\n" +
                "      }\n" +
                "    </style>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "    <div class=\"container\">\n" +
                "      <h1>Two Factor Authentication Code</h1>\n" +
                "      <p>Please use the following code to complete your login:</p>\n" +
                "      <div>\n" +
                "        <span class=\"code\">" + token + "</span>\n" +
                "        <span class=\"note\">This code will expire in 5 minutes.</span>\n" +
                "      </div>\n" +
                "      <p>If you did not request this code, please ignore this email.</p>\n" +
                "    </div>\n" +
                "  </body>\n" +
                "</html>";
    }

}
