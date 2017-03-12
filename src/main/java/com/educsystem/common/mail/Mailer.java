package com.educsystem.common.mail;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created by Denis on 27.02.2017.
 */
public class Mailer {
    public static void send(String login, List<String> recep) throws AddressException{
        String hostname = "smtp.gmail.com";
        int port = 587;
        final String fromEmail = "bestghostclan@gmail.com";
        final String password = "16121992";
        final String toEmail = "bestghostclan@yandex.ru";

        Properties prop = System.getProperties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", hostname);
        prop.put("mail.smtp.port", port);

        Session session = Session.getDefaultInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            for(int i = 0; i < recep.size(); i++) {
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(recep.get(i)));
            }
            message.setSubject("Автоматическое оповещение");
            message.setText("Внимание! " +
                    "Администратор с ником \"" + login + "\" вошел в систему!\"");
            Transport.send(message);
            System.out.println("Send success");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
