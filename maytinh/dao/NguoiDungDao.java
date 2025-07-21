package maytinh.dao;

import java.util.List;
import maytinh.entity.NguoiDung;

public interface NguoiDungDao {
    List<NguoiDung> findAll();
    List<NguoiDung> findByKeyword(String keyword);

    // Thêm mới hoặc cập nhật người dùng
    void update(NguoiDung nd);
    // Xóa người dùng theo username
    void delete(String fullname);

    public int getTongDonHang(String username);

    public List<NguoiDung> findByUsername(String keyword);
}
