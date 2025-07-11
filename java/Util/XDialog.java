package Util;

import javax.swing.JOptionPane;
import java.awt.Component;

public class XDialog {
    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }
}