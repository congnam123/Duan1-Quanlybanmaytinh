package poly.cafe.kho;

import maytinh.entity.Kho;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Interface Controller cho quản lý Kho
 * @author Admin
 */
public interface QuanLyKhoController {
    
    /**
     * Load dữ liệu vào bảng
     * @param table Bảng cần load dữ liệu
     */
    void loadData(JTable table);
    
    /**
     * Xuất/Nhập kho
     * @param maHang Mã hàng
     * @param tenHang Tên hàng
     * @param soLuong Số lượng
     * @param giaTien Giá tiền
     * @param trangThai Trạng thái (Xuất/Nhập)
     * @return true nếu thành công, false nếu thất bại
     */
    boolean xuatNhapKho(String maHang, String tenHang, String soLuong, String giaTien, String trangThai);
    
    /**
     * Cập nhật kho
     * @param kho Đối tượng kho cần cập nhật
     * @return true nếu thành công, false nếu thất bại
     */
    boolean capNhatKho(Kho kho);
    
    /**
     * Xóa kho
     * @param table Bảng chứa dữ liệu
     * @return true nếu thành công, false nếu thất bại
     */
    boolean xoaKho(JTable table);
    
    /**
     * Tìm kiếm kho
     * @param table Bảng hiển thị kết quả
     * @param keyword Từ khóa tìm kiếm
     */
    void timKiemKho(JTable table, String keyword);
    
    /**
     * Lấy dữ liệu từ bảng vào form
     * @param table Bảng chứa dữ liệu
     * @param txtMaHang TextField mã hàng
     * @param txtTenHang TextField tên hàng
     * @param txtSoLuong TextField số lượng
     * @param txtGiaTien TextField giá tiền
     * @param txtTrangThai TextField trạng thái
     * @param txtThoiGian TextField thời gian
     */
    void layDuLieuTuBang(JTable table, JTextField txtMaHang, JTextField txtTenHang, 
                         JTextField txtSoLuong, JTextField txtGiaTien, 
                         JTextField txtTrangThai, JTextField txtThoiGian);
    
    /**
     * Clear form
     * @param txtMaHang TextField mã hàng
     * @param txtTenHang TextField tên hàng
     * @param txtSoLuong TextField số lượng
     * @param txtGiaTien TextField giá tiền
     * @param txtTrangThai TextField trạng thái
     * @param txtThoiGian TextField thời gian
     */
    void clearForm(JTextField txtMaHang, JTextField txtTenHang, JTextField txtSoLuong, 
                   JTextField txtGiaTien, JTextField txtTrangThai, JTextField txtThoiGian);
    
    /**
     * Hiển thị lịch sử xuất nhập kho
     * @param table Bảng hiển thị lịch sử
     */
    void hienThiLichSu(JTable table);
    
    /**
     * Validate form trước khi thêm/cập nhật
     * @param maHang Mã hàng
     * @param tenHang Tên hàng
     * @param soLuong Số lượng
     * @param giaTien Giá tiền
     * @param trangThai Trạng thái
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    boolean validateForm(String maHang, String tenHang, String soLuong, String giaTien, String trangThai);
    
    /**
     * Validate từ khóa tìm kiếm
     * @param keyword Từ khóa tìm kiếm
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    boolean validateSearchKeyword(String keyword);
    
    /**
     * Validate thao tác xóa
     * @param table Bảng chứa dữ liệu
     * @return true nếu hợp lệ, false nếu không hợp lệ
     */
    boolean validateDeleteOperation(JTable table);
} 