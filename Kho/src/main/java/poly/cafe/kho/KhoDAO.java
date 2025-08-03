package poly.cafe.kho;

import maytinh.entity.Kho;
import javax.swing.JTable;
import javax.swing.JTextField;
import java.util.List;

/**
 * Interface DAO cho quản lý Kho
 * @author Admin
 */
public interface KhoDAO {
    
    /**
     * Lấy tất cả dữ liệu kho
     * @return List kho
     */
    List<Kho> getAllKho();
    
    /**
     * Thêm mới kho
     * @param kho Dữ liệu kho cần thêm
     * @return true nếu thành công, false nếu thất bại
     */
    boolean themKho(Kho kho);
    
    /**
     * Cập nhật kho
     * @param kho Dữ liệu kho cần cập nhật
     * @return true nếu thành công, false nếu thất bại
     */
    boolean capNhatKho(Kho kho);
    
    /**
     * Xóa kho theo mã
     * @param maKho Mã kho cần xóa
     * @return true nếu thành công, false nếu thất bại
     */
    boolean xoaKho(int maKho);
    
    /**
     * Tìm kiếm kho theo từ khóa
     * @param keyword Từ khóa tìm kiếm
     * @return List kho tìm được
     */
    List<Kho> timKiemKho(String keyword);
    
    /**
     * Lấy kho theo mã
     * @param maKho Mã kho
     * @return Kho tìm được hoặc null
     */
    Kho getKhoByMa(int maKho);
    
    /**
     * Fill dữ liệu vào bảng
     * @param table Bảng cần fill dữ liệu
     */
    void fillToTable(JTable table);
    
    /**
     * Lấy dữ liệu từ bảng
     * @param table Bảng chứa dữ liệu
     * @return Kho được chọn
     */
    Kho layDuLieuTuBang(JTable table);
    
    /**
     * Lấy dữ liệu từ bảng vào form
     * @param table Bảng chứa dữ liệu
     * @param txtMaHang TextField mã hàng
     * @param txtTenHang TextField tên hàng
     * @param txtSoLuong TextField số lượng
     * @param txtGiaTien TextField giá tiền
     * @param txtTrangThai TextField trạng thái
     */
    void layDuLieuTuBang(JTable table, JTextField txtMaHang, JTextField txtTenHang, 
                         JTextField txtSoLuong, JTextField txtGiaTien, JTextField txtTrangThai);
    
    /**
     * Xuất nhập kho
     * @param maHang Mã hàng
     * @param tenHang Tên hàng
     * @param soLuong Số lượng
     * @param giaTien Giá tiền
     * @param trangThai Trạng thái (Xuất/Nhập)
     * @return true nếu thành công, false nếu thất bại
     */
    boolean xuatNhapKho(String maHang, String tenHang, String soLuong, String giaTien, String trangThai);
    
    /**
     * Hiển thị lịch sử kho
     * @param table Bảng hiển thị lịch sử
     */
    void hienThiLichSu(JTable table);
} 