package maytinh.dao;

import java.util.List;
import maytinh.entity.Quanlybanhang;

public interface QuanlybanhangDAO {
    List<Quanlybanhang> selectAll();
    List<Quanlybanhang> searchByCustomerName(String keyword);
    void updateTrangThai(int maDH, String trangThai);
    void deleteByCustomerName(String tenKH);
}
