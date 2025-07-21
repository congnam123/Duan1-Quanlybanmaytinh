package maytinh.dao;

import jakarta.mail.MessagingException;

public interface MailService {
    void send(String to, String subject, String content) throws MessagingException;
}
