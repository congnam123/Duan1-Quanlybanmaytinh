/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh.entity;

/**
 *
 * @author Cong Nam
 */
import javax.swing.JOptionPane;
import java.awt.Component;

public class MsgBox {

    /**
     * Hiển thị hộp thoại thông báo.
     * @param parent thành phần cha
     * @param message thông báo
     */
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Hiển thị hộp thoại xác nhận.
     * @param parent thành phần cha
     * @param message thông báo
     * @return true nếu chọn Yes, false nếu chọn No
     */
    public static boolean confirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message, "Xác nhận", JOptionPane.YES_NO_OPTION);
        return result == JOptionPane.YES_OPTION;
    }

    /**
     * Hiển thị hộp thoại nhập dữ liệu.
     * @param parent thành phần cha
     * @param message thông báo
     * @return chuỗi người dùng nhập vào hoặc null nếu hủy
     */
    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message);
    }
}
