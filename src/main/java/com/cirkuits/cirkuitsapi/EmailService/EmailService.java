package com.cirkuits.cirkuitsapi.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeBodyPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    String readFile(String Filepath) throws IOException {
        Path file = Paths.get(Filepath);
        return Files.readString(file, StandardCharsets.UTF_8);
    }

    public void sendEmail(String sender, String emailDest, String subject, String templateName) throws MessagingException {
        String cid = UUID.randomUUID().toString();
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(MimeMessage.RecipientType.TO, emailDest);
        message.setSubject(subject);
        String htmlTemplate = "";
        try {
             htmlTemplate = readFile("src/main/resources/templates/"+templateName+".html");
            // -- Attach image
            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile("resources/teapot.jpg");
            imagePart.setContentID("<" + cid + ">");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        String cleanTemplate = htmlTemplate.replace("{cid}", cid);
        message.setContent(cleanTemplate, "text/html; charset=utf-8");

        if(cleanTemplate.isEmpty())
            throw new MessagingException("Template cannot be null");
        mailSender.send(message);
    }
}
