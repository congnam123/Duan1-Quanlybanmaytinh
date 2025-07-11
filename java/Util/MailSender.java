package Util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailSender {

    public static void send(String to, String subject, String content) {
        final String fromEmail = "your-email@gmail.com"; // Thay bằng Gmail của bạn
        final String password = "your-app-password";      // Mật khẩu ứng dụng (không phải mật khẩu Gmail thường)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);
            System.out.println("Đã gửi email đến: " + to);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

