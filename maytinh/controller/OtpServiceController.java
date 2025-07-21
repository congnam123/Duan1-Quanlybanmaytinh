/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maytinh.controller;

/**
 *
 * @author phuc
 */
public interface OtpServiceController {
        String generate(String usernameOrEmail);
    boolean validate(String inputOtp);
}
