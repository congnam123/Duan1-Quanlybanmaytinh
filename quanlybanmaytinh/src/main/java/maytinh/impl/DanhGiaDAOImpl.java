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
                        rs.getInt("MaDG"),
                        rs.getString("Username"),
                        rs.getInt("MaSP"),
                        rs.getInt("Diem"),
                        rs.getString("NoiDung"),
                        rs.getTimestamp("NgayDanhGia")
                );
                // ✅ Thêm trả lời
                dg.setTraLoi(rs.getString("TraLoi")); // có thể là null
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
        String sql = "SELECT * FROM DanhGia WHERE Username LIKE ?";
        try (ResultSet rs = XJdbc.query(sql, "%" + keyword + "%")) {
            while (rs.next()) {
                DanhGia dg = new DanhGia(
                        rs.getInt("MaDG"),
                        rs.getString("Username"),
                        rs.getInt("MaSP"),
                        rs.getInt("Diem"),
                        rs.getString("NoiDung"),
                        rs.getTimestamp("NgayDanhGia")
                );
                // ✅ Thêm trả lời
                dg.setTraLoi(rs.getString("TraLoi"));
                list.add(dg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ Thêm phương thức cập nhật trả lời
    public void capNhatTraLoi(int maDG, String traLoi) {
        String sql = "UPDATE DanhGia SET TraLoi = ? WHERE MaDG = ?";
        try {
            XJdbc.update(sql, traLoi, maDG);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean traLoiDanhGia(int maDanhGia, String traLoi) {
        String sql = "UPDATE DanhGia SET TraLoi = ? WHERE MaDG = ?";

        try (Connection con = XJdbc.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, traLoi);
            ps.setInt(2, maDanhGia);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
