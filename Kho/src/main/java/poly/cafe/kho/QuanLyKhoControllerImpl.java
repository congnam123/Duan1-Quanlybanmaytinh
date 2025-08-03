package poly.cafe.kho;

import maytinh.controller.QuanLyKhoController;
import maytinh.dao.KhoDAO;
import maytinh.entity.Kho;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Implementation của QuanLyKhoController
 * @author Admin
 */
public class QuanLyKhoControllerImpl implements QuanLyKhoController {
    
    private KhoDAO khoDAO;
    
    public QuanLyKhoControllerImpl() {
        this.khoDAO = new KhoDAOImpl();
    }
    
    @Override
    public void loadData(JTable table) {
        try {
            khoDAO.fillToTable(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tải dữ liệu: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean xuatNhapKho(String maHang, String tenHang, String soLuong, 
                               String giaTien, String trangThai) {
        // Validation chi tiết
        if (!validateForm(maHang, tenHang, soLuong, giaTien, trangThai)) {
            return false;
        }
        
        try {
            boolean result = khoDAO.xuatNhapKho(maHang, tenHang, soLuong, giaTien, trangThai);
            if (result) {
                JOptionPane.showMessageDialog(null, "Xuất/Nhập kho thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Xuất/Nhập kho thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xuất/nhập kho: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @Override
    public boolean capNhatKho(Kho kho) {
        if (kho == null) {
            JOptionPane.showMessageDialog(null, "Không có dữ liệu để cập nhật!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validation cho cập nhật
        if (!validateKhoEntity(kho)) {
            return false;
        }
        
        try {
            boolean result = khoDAO.capNhatKho(kho);
            if (result) {
                JOptionPane.showMessageDialog(null, "Cập nhật kho thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cập nhật kho thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            return result;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật kho: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    @Override
    public boolean xoaKho(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        int confirm = JOptionPane.showConfirmDialog(null, 
            "Bạn có chắc muốn xóa bản ghi này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Kho kho = khoDAO.layDuLieuTuBang(table);
                if (kho != null) {
                    boolean result = khoDAO.xoaKho(kho.getMaKho());
                    if (result) {
                        JOptionPane.showMessageDialog(null, "Xóa kho thành công!", 
                            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa kho thất bại!", 
                            "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa kho: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        return false;
    }
    
    @Override
    public void timKiemKho(JTable table, String keyword) {
        try {
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            
            if (keyword.trim().isEmpty()) {
                khoDAO.fillToTable(table);
                return;
            }
            
            // Validation keyword
            if (keyword.length() < 2) {
                JOptionPane.showMessageDialog(null, "Từ khóa tìm kiếm phải có ít nhất 2 ký tự!", 
                    "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            // Tìm kiếm theo mã hàng hoặc tên hàng
            for (Kho kho : khoDAO.timKiemKho(keyword)) {
                model.addRow(new Object[]{
                    kho.getMaHang(),
                    kho.getTenHang(),
                    kho.getSoLuong(),
                    String.format("%,.0f VNĐ", kho.getGiaTien()),
                    kho.getTrangThai(),
                    kho.getThoiGian()
                });
            }
            
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy kết quả nào!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi tìm kiếm: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void layDuLieuTuBang(JTable table, JTextField txtMaHang, JTextField txtTenHang, 
                                JTextField txtSoLuong, JTextField txtGiaTien, 
                                JTextField txtTrangThai, JTextField txtThoiGian) {
        try {
            // Sử dụng method từ DAO để lấy dữ liệu
            khoDAO.layDuLieuTuBang(table, txtMaHang, txtTenHang, txtSoLuong, txtGiaTien, txtTrangThai);
            
            // Thêm thông báo thành công
            JOptionPane.showMessageDialog(null, "Đã chọn dữ liệu từ bảng!\n" +
                "Bạn có thể chỉnh sửa và nhấn 'Xuất Nhập' để cập nhật.", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi lấy dữ liệu: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public void clearForm(JTextField txtMaHang, JTextField txtTenHang, JTextField txtSoLuong, 
                         JTextField txtGiaTien, JTextField txtTrangThai, JTextField txtThoiGian) {
        txtMaHang.setText("");
        txtTenHang.setText("");
        txtSoLuong.setText("");
        txtGiaTien.setText("");
        txtTrangThai.setText("");
        if (txtThoiGian != null) {
            txtThoiGian.setText("");
        }
    }
    
    @Override
    public void hienThiLichSu(JTable table) {
        try {
            khoDAO.hienThiLichSu(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi hiển thị lịch sử: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    @Override
    public boolean validateForm(String maHang, String tenHang, String soLuong, 
                               String giaTien, String trangThai) {
        // Validation cho mã hàng
        if (maHang == null || maHang.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã hàng không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (maHang.trim().length() < 3) {
            JOptionPane.showMessageDialog(null, "Mã hàng phải có ít nhất 3 ký tự!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (maHang.trim().length() > 50) {
            JOptionPane.showMessageDialog(null, "Mã hàng không được quá 50 ký tự!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Kiểm tra format mã hàng (chỉ cho phép chữ cái, số và dấu gạch ngang)
        if (!Pattern.matches("^[A-Za-z0-9-]+$", maHang.trim())) {
            JOptionPane.showMessageDialog(null, "Mã hàng chỉ được chứa chữ cái, số và dấu gạch ngang!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validation cho tên hàng
        if (tenHang == null || tenHang.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên hàng không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (tenHang.trim().length() < 5) {
            JOptionPane.showMessageDialog(null, "Tên hàng phải có ít nhất 5 ký tự!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (tenHang.trim().length() > 255) {
            JOptionPane.showMessageDialog(null, "Tên hàng không được quá 255 ký tự!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validation cho số lượng
        if (soLuong == null || soLuong.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Số lượng không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            int sl = Integer.parseInt(soLuong.trim());
            if (sl <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (sl > 999999) {
                JOptionPane.showMessageDialog(null, "Số lượng không được quá 999,999!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validation cho giá tiền
        if (giaTien == null || giaTien.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Giá tiền không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        try {
            double gia = Double.parseDouble(giaTien.trim());
            if (gia <= 0) {
                JOptionPane.showMessageDialog(null, "Giá tiền phải lớn hơn 0!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            
            if (gia > 999999999999.99) {
                JOptionPane.showMessageDialog(null, "Giá tiền không được quá 999,999,999,999.99!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Giá tiền phải là số!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validation cho trạng thái
        if (trangThai == null || trangThai.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trạng thái không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String trangThaiTrim = trangThai.trim();
        if (!trangThaiTrim.equals("Xuất") && !trangThaiTrim.equals("Nhập")) {
            JOptionPane.showMessageDialog(null, "Trạng thái phải là 'Xuất' hoặc 'Nhập'!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Validation cho entity Kho
     */
    private boolean validateKhoEntity(Kho kho) {
        if (kho.getMaHang() == null || kho.getMaHang().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Mã hàng không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (kho.getTenHang() == null || kho.getTenHang().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Tên hàng không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (kho.getSoLuong() <= 0) {
            JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (kho.getGiaTien() <= 0) {
            JOptionPane.showMessageDialog(null, "Giá tiền phải lớn hơn 0!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (kho.getTrangThai() == null || kho.getTrangThai().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Trạng thái không được để trống!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String trangThai = kho.getTrangThai().trim();
        if (!trangThai.equals("Xuất") && !trangThai.equals("Nhập")) {
            JOptionPane.showMessageDialog(null, "Trạng thái phải là 'Xuất' hoặc 'Nhập'!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Validation cho tìm kiếm
     */
    public boolean validateSearchKeyword(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return false;
        }
        
        if (keyword.trim().length() < 2) {
            JOptionPane.showMessageDialog(null, "Từ khóa tìm kiếm phải có ít nhất 2 ký tự!", 
                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (keyword.trim().length() > 100) {
            JOptionPane.showMessageDialog(null, "Từ khóa tìm kiếm không được quá 100 ký tự!", 
                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        return true;
    }
    
    /**
     * Validation cho xóa kho
     */
    public boolean validateDeleteOperation(JTable table) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Kiểm tra xem dữ liệu có hợp lệ không
        try {
            String maHang = (String) table.getValueAt(row, 0);
            if (maHang == null || maHang.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Dữ liệu không hợp lệ để xóa!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi kiểm tra dữ liệu: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
} 