package maytinh.dao;

import java.util.List;
import maytinh.entity.GioHang;

/**
 * DAO interface cho Giỏ hàng
 * @author PC
 */
public interface GioHangDAO {

    // CRUD operations
    GioHang create(GioHang entity);
    void update(GioHang entity);
    void deleteById(Integer id);
    List<GioHang> findAll();
    GioHang findById(Integer id);
    
    /**
     * Lấy danh sách giỏ hàng theo username
     * @param username
     * @return List<GioHang>
     */
    List<GioHang> selectByUsername(String username);
    
    /**
     * Thêm sản phẩm vào giỏ hàng
     * @param maSP
     * @param soLuong
     * @param username
     * @return boolean
     */
    boolean addToCart(int maSP, int soLuong, String username);
    
    /**
     * Cập nhật số lượng sản phẩm trong giỏ hàng
     * @param maGioHang
     * @param soLuong
     * @return boolean
     */
    boolean updateQuantity(int maGioHang, int soLuong);
    
    /**
     * Xóa sản phẩm khỏi giỏ hàng
     * @param maGioHang
     * @return boolean
     */
    boolean removeFromCart(int maGioHang);
    
    /**
     * Xóa tất cả sản phẩm trong giỏ hàng của user
     * @param username
     * @return boolean
     */
    boolean clearCart(String username);
    
    /**
     * Kiểm tra sản phẩm đã có trong giỏ hàng chưa
     * @param maSP
     * @param username
     * @return GioHang hoặc null
     */
    GioHang findByProductAndUser(int maSP, String username);
    
    /**
     * Lấy tổng số lượng item trong giỏ hàng
     * @param username
     * @return int
     */
    int getCartItemCount(String username);
    
    /**
     * Lấy tổng tiền trong giỏ hàng
     * @param username
     * @return double
     */
    double getTotalAmount(String username);
}
