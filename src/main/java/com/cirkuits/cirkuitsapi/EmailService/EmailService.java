package com.cirkuits.cirkuitsapi.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.antlr.v4.runtime.misc.Utils.readFile;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String sender, String emailDest, String subject, String templateName) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(MimeMessage.RecipientType.TO, emailDest);
        message.setSubject(subject);
        String htmlTemplate = "";
        try {
            // Read the HTML template into a String variable
             htmlTemplate = readFile("templates/"+templateName+".html").toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Replace placeholders in the HTML template with dynamic values
        // htmlTemplate = htmlTemplate.replace("${name}", "John Doe");
        // htmlTemplate = htmlTemplate.replace("${message}", "Hello, this is a test email.");

        // Set the email's content to be the HTML template
        message.setContent(htmlTemplate, "text/html; charset=utf-8");
        if(htmlTemplate.isBlank())
            throw new MessagingException("Template cannot be null");
        mailSender.send(message);
    }
}
