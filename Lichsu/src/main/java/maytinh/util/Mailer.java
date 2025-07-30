package maytinh.util;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Lớp tiện ích gửi email bằng Gmail SMTP. Hỗ trợ nội dung HTML và định dạng
 * UTF-8 (hỗ trợ tiếng Việt).
 */
public class Mailer {

    private static final String FROM_EMAIL = "nonre1065@gmail.com";
    private static final String FROM_PASSWORD = "tjhk gaux asfd tmpk"; // App password Gmail

    /**
     * Gửi email với nội dung HTML đẹp và hỗ trợ tiếng Việt.
     *
     * @param toEmail địa chỉ email người nhận
     * @param subject tiêu đề email
     * @param otpCode mã OTP
     * @throws MessagingException
     * @throws UnsupportedEncodingException
     */
    public static void send(String toEmail, String subject, String otpCode)
            throws MessagingException, UnsupportedEncodingException {

        // Thiết lập cấu hình SMTP Gmail
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Phiên làm việc với Gmail
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, FROM_PASSWORD);
            }
        });

        // Soạn email
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(FROM_EMAIL, MimeUtility.encodeText("Hệ thống bán máy tính", "UTF-8", "B")));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(subject);

        // Nội dung HTML đẹp hơn
        String htmlContent = ""
                + "<div style='font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px; border-radius: 10px;'>"
                + "  <div style='max-width: 600px; margin: auto; background-color: #ffffff; padding: 30px; border: 1px solid #ddd; border-radius: 10px;'>"
                + "    <h2 style='color: #2c3e50; font-family: Tahoma, sans-serif; text-align: center;'>Xác nhận đăng ký tài khoản khách hàng</h2>"
                + "    <p>Xin chào quý khách,</p>"
                + "    <p>Cảm ơn quý khách đã lựa chọn hệ thống <strong>Bán Máy Tính Chính Hãng</strong>.</p>"
                + "    <p>Chúng tôi đã nhận được yêu cầu tạo tài khoản của quý khách.</p>"
                + "    <p style='margin-top: 20px;'>Mã xác thực (OTP) của quý khách là:</p>"
                + "    <div style='text-align: center; margin: 20px 0;'>"
                + "      <span style='display: inline-block; font-size: 28px; color: #e74c3c; font-weight: bold; padding: 10px 20px; border: 2px dashed #e74c3c; border-radius: 8px;'>"
                + otpCode
                + "      </span>"
                + "    </div>"
                + "    <p><i>Vui lòng không chia sẻ mã này với bất kỳ ai để đảm bảo an toàn tài khoản.</i></p>"
                + "    <p style='margin-top: 30px;'>Trân trọng,<br><strong>Đội ngũ hỗ trợ - Hệ thống bán máy tính</strong></p>"
                + "  </div>"
                + "</div>";

        message.setContent(htmlContent, "text/html; charset=UTF-8");

        // Gửi email
        Transport.send(message);
    }
}
