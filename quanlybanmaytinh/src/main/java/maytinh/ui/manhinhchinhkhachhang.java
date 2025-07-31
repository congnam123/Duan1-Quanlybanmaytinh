/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package maytinh.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import maytinh.controller.GiaodienchinhController;
import maytinh.dao.GioHangDAO;
import maytinh.entity.SanPham;
import maytinh.impl.GioHangDAOImpl;
import maytinh.util.XAuth;
import maytinh.util.XJdbc;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
/**
 *
 * @author PC
 */
public class manhinhchinhkhachhang extends javax.swing.JDialog implements GiaodienchinhController {

    private GioHangDAO gioHangDAO;
    private javax.swing.JPanel productPanel; // Panel chứa các sản phẩm

    /**
     * Creates new form manhinhchinh
     */
    public manhinhchinhkhachhang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.gioHangDAO = new GioHangDAOImpl();
        initComponents();
        setupProductPanel(); // Setup panel sản phẩm
        this.setLocationRelativeTo(null);
        loadSanPham(); // Load sản phẩm khi khởi tạo
    }

    /**
     * Setup panel chứa sản phẩm với scroll
     */
    private void setupProductPanel() {
        // Tạo panel chứa sản phẩm
        productPanel = new javax.swing.JPanel();
        productPanel.setLayout(new java.awt.GridLayout(0, 3, 15, 15)); // 0 rows (unlimited), 3 columns, 15px gap
        productPanel.setBackground(new java.awt.Color(204, 255, 255));

        // Tạo scroll pane
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(productPanel);
        scrollPane.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        // Xóa layout cũ của jPanel2 và thêm scroll pane
        jPanel2.removeAll();
        jPanel2.setLayout(new java.awt.BorderLayout());
        jPanel2.add(scrollPane, java.awt.BorderLayout.CENTER);
    }

    /**
     * Load danh sách sản phẩm từ database
     */
    private void loadSanPham() {
        try {
            List<SanPham> danhSachSanPham = getAllSanPham();
            productPanel.removeAll();

            if (danhSachSanPham.isEmpty()) {
                showNoProductsMessage();
            } else {
                for (SanPham sp : danhSachSanPham) {
                    createProductCard(sp);
                }
            }

            productPanel.revalidate();
            productPanel.repaint();
        } catch (Exception e) {
            showErrorMessage("Lỗi khi tải sản phẩm: " + e.getMessage());
            System.out.println("Lỗi database: " + e.getMessage());
        }
    }

    /**
     * Hiển thị thông báo không có sản phẩm
     */
    private void showNoProductsMessage() {
        javax.swing.JLabel noProductLabel = new javax.swing.JLabel("<html><center>📦<br>Không có sản phẩm nào</center></html>");
        noProductLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 18));
        noProductLabel.setForeground(new java.awt.Color(108, 117, 125));
        noProductLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        productPanel.add(noProductLabel);
    }

    /**
     * Tạo card sản phẩm đơn giản
     */
    private void createProductCard(SanPham sp) {
        javax.swing.JPanel cardPanel = new javax.swing.JPanel(new java.awt.BorderLayout());
        cardPanel.setBackground(java.awt.Color.WHITE);
        cardPanel.setBorder(javax.swing.BorderFactory.createCompoundBorder(
            javax.swing.BorderFactory.createLineBorder(new java.awt.Color(230, 230, 230), 1),
            javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        cardPanel.setPreferredSize(new Dimension(320, 400));

        // Hình ảnh sản phẩm
        javax.swing.JLabel imageLabel = createProductImage(sp.getHinhAnh());
        cardPanel.add(imageLabel, java.awt.BorderLayout.NORTH);

        // Thông tin sản phẩm
        javax.swing.JPanel infoPanel = createInfoPanel(sp);
        cardPanel.add(infoPanel, java.awt.BorderLayout.CENTER);

        // Nút thêm vào giỏ hàng
        javax.swing.JButton addToCartBtn = createAddToCartButton(sp);
        cardPanel.add(addToCartBtn, java.awt.BorderLayout.SOUTH);

        productPanel.add(cardPanel);
    }

    /**
     * Tạo panel thông tin sản phẩm
     */
    private javax.swing.JPanel createInfoPanel(SanPham sp) {
        javax.swing.JPanel infoPanel = new javax.swing.JPanel();
        infoPanel.setLayout(new javax.swing.BoxLayout(infoPanel, javax.swing.BoxLayout.Y_AXIS));
        infoPanel.setBackground(java.awt.Color.WHITE);
        infoPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 15, 12, 15));

        // Tên sản phẩm
        String tenSP = sp.getTenSP().length() > 35 ? sp.getTenSP().substring(0, 35) + "..." : sp.getTenSP();
        javax.swing.JLabel nameLabel = new javax.swing.JLabel("<html><center>" + tenSP + "</center></html>");
        nameLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
        nameLabel.setAlignmentX(javax.swing.JComponent.CENTER_ALIGNMENT);

        // Giá
        javax.swing.JLabel priceLabel = new javax.swing.JLabel(String.format("%,.0f VNĐ", sp.getGia()));
        priceLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16));
        priceLabel.setForeground(new java.awt.Color(220, 53, 69));
        priceLabel.setAlignmentX(javax.swing.JComponent.CENTER_ALIGNMENT);

        // Trạng thái
        javax.swing.JLabel statusLabel = new javax.swing.JLabel("Còn: " + sp.getSoLuong());
        statusLabel.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 11));
        statusLabel.setForeground(new java.awt.Color(108, 117, 125));
        statusLabel.setAlignmentX(javax.swing.JComponent.CENTER_ALIGNMENT);

        infoPanel.add(nameLabel);
        infoPanel.add(javax.swing.Box.createVerticalStrut(8));
        infoPanel.add(priceLabel);
        infoPanel.add(javax.swing.Box.createVerticalStrut(8));
        infoPanel.add(statusLabel);

        return infoPanel;
    }

    /**
     * Tạo nút thêm vào giỏ hàng
     */
    private javax.swing.JButton createAddToCartButton(SanPham sp) {

        javax.swing.JButton addToCartBtn = new javax.swing.JButton("🛒 Thêm vào giỏ hàng");
        addToCartBtn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        addToCartBtn.setBackground(new java.awt.Color(40, 167, 69));
        addToCartBtn.setForeground(java.awt.Color.WHITE);
        addToCartBtn.setFocusPainted(false);
        addToCartBtn.setEnabled(sp.getSoLuong() > 0);

        addToCartBtn.addActionListener(e -> addToCart(sp));

        return addToCartBtn;
    }

    /**
     * Xử lý thêm sản phẩm vào giỏ hàng
     */
    private void addToCart(SanPham sp) {
        String input = JOptionPane.showInputDialog(this,
            "Nhập số lượng (tối đa " + sp.getSoLuong() + "):",
            "Thêm vào giỏ hàng",
            JOptionPane.QUESTION_MESSAGE);

        if (input != null && !input.trim().isEmpty()) {
            try {
                int soLuong = Integer.parseInt(input.trim());
                if (soLuong <= 0 || soLuong > sp.getSoLuong()) {
                    showErrorMessage("Số lượng không hợp lệ!");
                    return;
                }

                String username = getCurrentUsername();
                if (gioHangDAO.addToCart(sp.getMaSP(), soLuong, username)) {
                    updateCartButton();
                    showSuccessMessage("Đã thêm " + soLuong + " " + sp.getTenSP() + " vào giỏ hàng!");
                } else {
                    showErrorMessage("Không thể thêm sản phẩm vào giỏ hàng!");
                }

            } catch (NumberFormatException ex) {
                showErrorMessage("Vui lòng nhập số hợp lệ!");
            }
        }
    }



    /**
     * Lấy tất cả sản phẩm từ database
     */
    private List<SanPham> getAllSanPham() {
        List<SanPham> list = new ArrayList<>();
        String sql = "SELECT sp.MaSP, sp.TenSP, sp.MoTa, sp.Gia, sp.SoLuongTon, sp.HinhAnh, lsp.TenLoai " +
                     "FROM SanPham sp " +
                     "LEFT JOIN LoaiSanPham lsp ON sp.MaLoai = lsp.MaLoai " +
                     "WHERE sp.SoLuongTon > 0"; // Chỉ lấy sản phẩm còn hàng

        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setGia(rs.getDouble("Gia"));
                sp.setMoTa(rs.getString("MoTa"));
                sp.setHinhAnh(rs.getString("HinhAnh")); // Lấy hình ảnh từ DB
                sp.setLoaiSP(rs.getString("TenLoai") != null ? rs.getString("TenLoai") : "Khac");
                sp.setSoLuong(rs.getInt("SoLuongTon"));
                sp.setTrangThai(rs.getInt("SoLuongTon") > 0); // Còn hàng nếu số lượng > 0
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    /**
     * Cập nhật text nút giỏ hàng để hiển thị số lượng item
     */
    private void updateCartButton() {
        String username = getCurrentUsername();
        int itemCount = gioHangDAO.getCartItemCount(username);
        if (itemCount > 0) {
            jButton3.setText("Gio hang (" + itemCount + ")");
        } else {
            jButton3.setText("Gio hang");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Welcom !");

        jPanel2.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 977, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jButton9.setText("Liên hệ");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 102, 102));
        jButton8.setText("Đăng xuất");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton7.setForeground(new java.awt.Color(0, 102, 102));
        jButton7.setText("Đổi mật khẩu");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton6.setForeground(new java.awt.Color(0, 102, 102));
        jButton6.setText("Đánh giá");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton5.setForeground(new java.awt.Color(0, 102, 102));
        jButton5.setText("Lịch sử");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 102, 102));
        jButton4.setText("Thanh toán");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 102, 102));
        jButton3.setText("Giỏ hàng");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 102, 102));
        jButton1.setText("Thông tin cá nhân");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                                .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(jButton9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(58, 58, 58)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)
                        .addContainerGap(148, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        this.showThongtincanhanJDialog(null);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.showgiohangJDialog(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        this.showthanhtoanJDialog(null);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        this.showLichsuJDialog(null);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        this.showDoimatkhauJDialog(null);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        int chon = JOptionPane.showConfirmDialog(this, "Ban co chac chan muon dang xuat khong?", "Dang xuat", JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            this.dispose();

            // Mở lại giao diện đăng nhập
            dangNhap login = new dangNhap(null, true);
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        this.showLienHeJDialog(null);
    }//GEN-LAST:event_jButton9ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                manhinhchinhkhachhang dialog = new manhinhchinhkhachhang(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    /**
     * Hiển thị dialog giỏ hàng
     */
    private void showgiohangJDialog(Object obj) {
        giohang dialog = new giohang(null, true);
        dialog.setVisible(true);
        // Cập nhật lại nút giỏ hàng sau khi đóng dialog
        updateCartButton();
    }

    /**
     * Hiển thị dialog thanh toán
     */
    private void showthanhtoanJDialog(Object obj) {
        thanhtoan dialog = new thanhtoan(null, true);
        dialog.setVisible(true);
        // Cập nhật lại nút giỏ hàng sau khi đóng dialog
        updateCartButton();
    }

    /**
     * Hiển thị dialog lịch sử mua hàng
     */
    private void showLichsuJDialog(Object obj) {
        LichSuMuaHang dialog = new LichSuMuaHang(null, true);
        dialog.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables

    // Helper methods
    private String getCurrentUsername() {
        return XAuth.user != null ? XAuth.user.getUsername() : "guest";
    }

    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Tạo label hình ảnh sản phẩm
     */
    private JLabel createProductImage(String imageName) {
        JLabel imageLabel = new JLabel("📷", SwingConstants.CENTER);
        imageLabel.setPreferredSize(new Dimension(300, 160));
        imageLabel.setOpaque(true);
        imageLabel.setBackground(new Color(248, 249, 250));
        imageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));

        // Thử tải hình ảnh thực
        try {
            String imagePath = "/images/" + (imageName != null ? imageName : "default_computer.jpg");
            URL imageURL = getClass().getResource(imagePath);

            if (imageURL != null) {
                ImageIcon icon = new ImageIcon(imageURL);
                Image scaledImage = icon.getImage().getScaledInstance(280, 240, Image.SCALE_SMOOTH);
                imageLabel.setIcon(new ImageIcon(scaledImage));
                imageLabel.setText("");
            }
        } catch (Exception e) {
            // Giữ placeholder mặc định
        }

        return imageLabel;
    }

}
