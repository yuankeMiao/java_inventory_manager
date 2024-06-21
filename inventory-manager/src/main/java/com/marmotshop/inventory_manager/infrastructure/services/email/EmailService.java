package com.marmotshop.inventory_manager.infrastructure.services.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);

        mailSender.send(message);
    }

    public void sendHtmlEmail(String to, String subject, String body) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(subject);

        String htmlContent =  String.format("<h1>This is a test Spring Boot email</h1>" +
                "<p>It can contain <strong>%s</strong> content.</p>", body);
        message.setContent(htmlContent, "text/html; charset=utf-8");

        mailSender.send(message);
    }
}
