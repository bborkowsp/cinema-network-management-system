package org.example.cinemabackend.user.core.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.example.cinemabackend.user.core.port.primary.EmailUseCases;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailUseCases {

    private static final String ACCOUNT_VERIFICATION_EMAIL_SUBJECT = "Account Verification";
    private static final String RESET_PASSWORD_EMAIL_SUBJECT = "Reset Password";
    private static final String PATH_TO_ACCOUNT_VERIFICATION_EMAIL_TEMPLATE = "src/main/resources/email-templates/account-verification.html";
    private static final String PATH_TO_RESET_PASSWORD_EMAIL_TEMPLATE = "src/main/resources/email-templates/reset-password.html";
    private static final String HREF_PLACEHOLDER = "{{link}}";
    private final Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    public void sendEmailToResetPassword(String to, String resetPasswordUrl) {
        sendEmail(to, RESET_PASSWORD_EMAIL_SUBJECT, PATH_TO_RESET_PASSWORD_EMAIL_TEMPLATE, resetPasswordUrl);
    }

    @Override
    public void sendEmailToConfirmAccount(String to, String verificationUrl) {
        sendEmail(to, ACCOUNT_VERIFICATION_EMAIL_SUBJECT, PATH_TO_ACCOUNT_VERIFICATION_EMAIL_TEMPLATE, verificationUrl);
    }

    private void sendEmail(String to, String subject, String templatePath, String replacement) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            String htmlContent = loadEmailTemplate(templatePath);
            String formattedHtmlContent = htmlContent.replace(HREF_PLACEHOLDER, replacement);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(formattedHtmlContent, true);
            mimeMessageHelper.setFrom("pamiwpw@gmail.com");
        } catch (Exception e) {
            logger.error("Failed to send email, to: " + to + ", subject: " + subject);
            throw new RuntimeException(e);
        }
        logger.info("Sending email to: " + to + ", subject: " + subject);
        mailSender.send(message);
    }

    private String loadEmailTemplate(String templatePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(templatePath)));
    }
}
