package main;

import ui.LoginJDialog;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginJDialog dialog = new LoginJDialog(null);
            dialog.setVisible(true);
        });
    }
}