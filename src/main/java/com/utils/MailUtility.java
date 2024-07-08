package com.utils;

import java.util.Properties;
import jakarta.*;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.*;

public class MailUtility {
    public static void sendExceptionEmailToDeveloper(String[] to, String subject, String body) {
        // Set the SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Set the email account credentials
        String username = "javadeveloper08042001@gmail.com";
        String password = "bkbq sgoq xkej xefy";

        // Create the session
        Session session = Session.getInstance(props, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create the email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            
            // Create an array of InternetAddress for the recipients
            InternetAddress[] recipientAddresses = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                recipientAddresses[i] = new InternetAddress(to[i]);
            }
            
            message.setRecipients(Message.RecipientType.TO, recipientAddresses);
            message.setSubject(subject);
            message.setText(body);

            // Send the email
            Transport.send(message);
            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}