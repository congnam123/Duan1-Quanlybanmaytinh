/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh.impl;

import maytinh.controller.OtpServiceController;
import maytinh.util.Mailer;
import maytinh.util.OtpUtil;

/**
 *
 * @author phuc
 */
public class EmailOtpServiceimpl implements OtpServiceController{
    private String otp;
    private long createdTime;
    private final long TIMEOUT = 5 * 60 * 1000; // 5 phút
    private String toEmail;

    public EmailOtpServiceimpl(String toEmail) {
        this.toEmail = toEmail;
    }
    @Override
    public String generate(String userIdentifier) {
        otp = OtpUtil.generateOtp(6);
        createdTime = System.currentTimeMillis();
        String subject = "Mã OTP xác minh";
        String content = "Xin chào " + userIdentifier + ",\n\nMã OTP của bạn là: " + otp;
        try {
            Mailer.send(toEmail, subject, content);
        } catch (Exception e) {
            throw new RuntimeException("Gửi OTP thất bại: " + e.getMessage());
        }
        return otp;
    }

    @Override
    public boolean validate(String inputOtp) {
        if (otp == null) return false;
        boolean match = otp.equals(inputOtp);
        boolean expired = System.currentTimeMillis() - createdTime > TIMEOUT;
        return match && !expired;
    }
}