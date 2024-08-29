package org.example.cinemabackend.user.core.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.port.primary.EmailUseCases;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailUseCases {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmailToConfirmAccount(String to, String verificationLink) {
        String subject = "Account Verification";
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            String htmlContent = new String(Files.readAllBytes(Paths.get("src/main/resources/account-verification-email.html")));
            String formattedHtmlContent = htmlContent.replace("{{verificationLink}}", verificationLink);
            mimeMessageHelper.setText(formattedHtmlContent, true);
            mimeMessageHelper.setFrom("pamiwpw@gmail.com");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        mailSender.send(message);
    }

}
