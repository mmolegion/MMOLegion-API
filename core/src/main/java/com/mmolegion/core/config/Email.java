package com.mmolegion.core.config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Map;
import java.util.Properties;

public class Email {

    private static Session session;

    static {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "m.outlook.com");
        props.put("mail.smtp.auth", "true");

        session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("dev@mmolegion.com", System.getProperty("email.password"));
            }
        });
    }

    public static void sendMessage(Map<String,String> params) {
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("donotreply@mmolegion.com"));

            message.setRecipients(
                    Message.RecipientType.TO, InternetAddress.parse(params.get("to")));
            message.setSubject(params.get("subject"));

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(params.get("message"), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
