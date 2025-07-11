/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Util;

/**
 *
 * @author phuc
 */
import javax.swing.JOptionPane;

public class MessageUtil {
    public static void show(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public static boolean confirm(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    } 
}
