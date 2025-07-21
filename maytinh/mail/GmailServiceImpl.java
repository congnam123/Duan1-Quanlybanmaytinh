package maytinh.mail;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.util.Properties;

public class GmailServiceImpl implements MailService {

    private final String username = "nonre1065@gmail.com";       // Gmail
    private final String password = "mwds pggb ssni cxux";       // App Password

    @Override
    public void send(String to, String subject, String content) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com"); // ✅ THÊM DÒNG NÀY

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(content);

        Transport.send(message);
    }
}
