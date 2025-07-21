package maytinh.impl;

import java.sql.*;
import java.util.*;
import maytinh.dao.DanhGiaDAO;
import maytinh.entity.DanhGia;
import maytinh.util.XJdbc;

public class DanhGiaDAOImpl implements DanhGiaDAO {

    @Override
    public List<DanhGia> getAll() {
        List<DanhGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DanhGia";
        try (ResultSet rs = XJdbc.query(sql)) {
            while (rs.next()) {
                DanhGia dg = new DanhGia(
                    rs.getInt("MaDG"),                         // ✅
                    rs.getString("Username"),                 // ✅
                    rs.getInt("MaSP"),                        // ✅
                    rs.getInt("Diem"),                        // ✅
                    rs.getString("NoiDung"),                  // ✅
                    rs.getTimestamp("NgayDanhGia")           // ✅
                );
                list.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<DanhGia> searchByKeyword(String keyword) {
        List<DanhGia> list = new ArrayList<>();
        String sql = "SELECT * FROM DanhGia WHERE Username LIKE ?"; // ✅ Tìm theo tên người dùng
        try (ResultSet rs = XJdbc.query(sql, "%" + keyword + "%")) {
            while (rs.next()) {
                DanhGia dg = new DanhGia(
                    rs.getInt("MaDG"),                         // ✅
                    rs.getString("Username"),                 // ✅
                    rs.getInt("MaSP"),                        // ✅
                    rs.getInt("Diem"),                        // ✅
                    rs.getString("NoiDung"),                  // ✅
                    rs.getTimestamp("NgayDanhGia")           // ✅
                );
                list.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
