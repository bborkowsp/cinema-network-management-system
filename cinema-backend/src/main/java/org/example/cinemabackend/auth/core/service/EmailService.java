package org.example.cinemabackend.auth.core.service;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.cinemabackend.auth.core.port.primary.EmailUseCases;
import org.example.cinemabackend.ticketing.core.domain.Ticket;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailService implements EmailUseCases {
    private static final String ACCOUNT_VERIFICATION_EMAIL_SUBJECT = "Account Verification";
    private static final String RESET_PASSWORD_EMAIL_SUBJECT = "Reset Password";
    private static final String PATH_TO_ACCOUNT_VERIFICATION_EMAIL_TEMPLATE = "src/main/resources/email-templates/account-verification.html";
    private static final String PATH_TO_RESET_PASSWORD_EMAIL_TEMPLATE = "src/main/resources/email-templates/reset-password.html";
    private static final String PATH_TO_TICKET_EMAIL_TEMPLATE = "src/main/resources/email-templates/ticket.html";
    private static final Logger LOGGER = LogManager.getLogger(EmailService.class);
    private final JavaMailSender mailSender;

    @Override
    public void sendTicketToUser(Ticket ticket) {
        String qrCodeBase64 = Base64.getEncoder().encodeToString(ticket.getQrCode());

        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("firstName", ticket.getFirstName());
        placeholders.put("lastName", ticket.getLastName());
        placeholders.put("email", ticket.getEmail());
        placeholders.put("cinema.name", ticket.getCinema().getName());
        placeholders.put("screeningRoom.name", ticket.getScreeningRoom().getName());
        placeholders.put("screening.movieTitle", ticket.getScreening().getMovie().getTitle());
        placeholders.put("screening.startTime", ticket.getScreening().getStartTime().toString());
        placeholders.put("qrCode", qrCodeBase64);

        String bookedSeatsHtml = ticket.getBookedSeats().stream()
                .map(seat -> String.format("<li>Row: %d, Seat: %d (Zone: %s)</li>", seat.getSeatRow(), seat.getSeatColumn(), seat.getSeatZone()))
                .collect(Collectors.joining());
        placeholders.put("bookedSeats", bookedSeatsHtml);

        sendEmail(ticket.getEmail(), "Your Cinema Ticket", PATH_TO_TICKET_EMAIL_TEMPLATE, placeholders);
    }


    @Override
    public void sendEmailToResetPassword(String to, String resetPasswordUrl) {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("link", resetPasswordUrl);
        sendEmail(to, RESET_PASSWORD_EMAIL_SUBJECT, PATH_TO_RESET_PASSWORD_EMAIL_TEMPLATE, placeholders);
    }

    @Override
    public void sendEmailToConfirmAccount(String to, String verificationUrl) {
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("link", verificationUrl);
        sendEmail(to, ACCOUNT_VERIFICATION_EMAIL_SUBJECT, PATH_TO_ACCOUNT_VERIFICATION_EMAIL_TEMPLATE, placeholders);
    }

    private void sendEmail(String to, String subject, String templatePath, Map<String, String> placeholders) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            String htmlContent = loadEmailTemplate(templatePath);

            for (Map.Entry<String, String> entry : placeholders.entrySet()) {
                htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(htmlContent, true);
            mimeMessageHelper.setFrom("pamiwpw@gmail.com");
        } catch (Exception e) {
            LOGGER.error("Failed to send email, to: " + to + ", subject: " + subject);
            throw new RuntimeException(e);
        }
        LOGGER.info("Sending email to: " + to + ", subject: " + subject);
        mailSender.send(message);
    }


    private String loadEmailTemplate(String templatePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(templatePath)));
    }
}
