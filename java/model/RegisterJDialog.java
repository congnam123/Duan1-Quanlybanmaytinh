package model;

import Util.MailSender;
import javax.swing.*;

public class RegisterJDialog extends JDialog {

    private JTextField txtFullName, txtEmail;
    private JPasswordField txtPassword, txtConfirmPassword;
    private JButton btnRegister;

    public RegisterJDialog() {
        setTitle("Đăng ký tài khoản");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        txtFullName = new JTextField();
        txtEmail = new JTextField();
        txtPassword = new JPasswordField();
        txtConfirmPassword = new JPasswordField();
        btnRegister = new JButton("Đăng ký");

        btnRegister.addActionListener(e -> register());

        setLayout(new java.awt.GridLayout(6, 2, 10, 10));
        add(new JLabel("Họ tên:"));
        add(txtFullName);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Mật khẩu:"));
        add(txtPassword);
        add(new JLabel("Xác nhận mật khẩu:"));
        add(txtConfirmPassword);
        add(new JLabel(""));
        add(btnRegister);
    }

    private void register() {
        String fullName = txtFullName.getText().trim();
        String email = txtEmail.getText().trim();
        String password = new String(txtPassword.getPassword());
        String confirm = new String(txtConfirmPassword.getPassword());

        if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không khớp!");
            return;
        }

        JOptionPane.showMessageDialog(this, "Đăng ký thành công! Hệ thống sẽ gửi email xác nhận.");

        MailSender.send(
            email,
            "Xác nhận đăng ký tài khoản",
            "Xin chào " + fullName + ",\n\nBạn đã đăng ký thành công!\n\nTrân trọng!"
        );

        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegisterJDialog().setVisible(true));
    }
}