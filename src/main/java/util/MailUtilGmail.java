package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailUtilGmail {
    public static void sendMail(String to, String from, String subject, String body, boolean isBodyHTML) throws MessagingException {
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtps");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        System.out.println("Creating mail session..."); // Log thông tin
        Session session = Session.getInstance(properties, new jakarta.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                System.out.println("Authenticating with email: " + from); // Log thông tin
                return new PasswordAuthentication(from, "slpb pksv ygov bxqz"); // Thay thế bằng mật khẩu ứng dụng
            }
        });

        session.setDebug(true); // Đặt chế độ debug

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setContent(body, isBodyHTML ? "text/html" : "text/plain");

        System.out.println("Sending email to: " + to); // Log thông tin
        Transport.send(message);
        System.out.println("Email sent successfully!"); // Log thông tin
    }
}
