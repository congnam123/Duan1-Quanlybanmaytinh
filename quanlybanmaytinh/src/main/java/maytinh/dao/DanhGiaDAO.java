package maytinh.dao;

import java.util.List;
import maytinh.entity.DanhGia;

public interface DanhGiaDAO {
    List<DanhGia> getAll();
    List<DanhGia> searchByKeyword(String keyword);
}
