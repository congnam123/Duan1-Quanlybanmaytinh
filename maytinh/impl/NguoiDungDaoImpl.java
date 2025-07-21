package maytinh.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import maytinh.dao.NguoiDungDao;
import maytinh.entity.MsgBox;
import maytinh.entity.NguoiDung;
import maytinh.util.XJdbc;

public class NguoiDungDaoImpl implements NguoiDungDao {

    @Override
    public List<NguoiDung> findAll() {
        String sql = "SELECT u.Username, u.Fullname, u.NgaySinh, u.DiaChi, u.SoDienThoai, u.PhanLoai, "
                + "ISNULL(COUNT(d.MaDH), 0) AS TongDonHang "
                + "FROM Users u LEFT JOIN DonHang d ON u.Username = d.Username "
                + "GROUP BY u.Username, u.Fullname, u.NgaySinh, u.DiaChi, u.SoDienThoai, u.PhanLoai";
        return selectBySql(sql);
    }

    @Override
    public List<NguoiDung> findByKeyword(String keyword) {
        String sql = "SELECT * FROM Users WHERE Fullname LIKE ?";
        return selectBySql(sql, "%" + keyword + "%");
    }

    /**
     * Phương thức thực hiện truy vấn và trả về danh sách người dùng
     */
protected List<NguoiDung> selectBySql(String sql, Object... args) {
    List<NguoiDung> list = new ArrayList<>();
    try {
        ResultSet rs = XJdbc.query(sql, args);
        while (rs.next()) {
            NguoiDung nd = new NguoiDung();
            nd.setUsername(rs.getString("Username"));
            nd.setFullname(rs.getString("Fullname"));
            nd.setNgaySinh(rs.getDate("NgaySinh"));
            nd.setDiaChi(rs.getString("DiaChi"));
            nd.setSoDienThoai(rs.getString("SoDienThoai"));
            nd.setPhanLoai(rs.getString("PhanLoai"));

            // Lấy tổng đơn hàng
            int tongDon = getTongDonHang(nd.getUsername());
            nd.setTongDonHang(tongDon);

            list.add(nd);
        }
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
    return list;
}


    @Override
    public void update(NguoiDung nd) {
        String sql = "UPDATE Users SET Fullname = ?, NgaySinh = ?, DiaChi = ?, SoDienThoai = ?, PhanLoai = ? WHERE Username = ?";
        int rows = XJdbc.updateWithResult(sql,
                nd.getFullname(),
                nd.getNgaySinh(),
                nd.getDiaChi(),
                nd.getSoDienThoai(),
                nd.getPhanLoai(),
                nd.getUsername()
        );

        if (rows == 0) {
            MsgBox.alert(null, "Không tìm thấy khách hàng để cập nhật!");
        } else {
            MsgBox.alert(null, "Cập nhật thành công!");
        }
    }

    @Override
    public void delete(String username) {
        String sql = "DELETE FROM Users WHERE Username = ?"; // ✅ Sửa lại dùng Username
        XJdbc.update(sql, username);
    }
    
    public int getTongDonHang(String username) {
    String sql = "SELECT COUNT(*) AS SoDonHang FROM DonHang WHERE Username = ?";
    try (ResultSet rs = XJdbc.query(sql, username)) {
        if (rs.next()) {
            return rs.getInt("SoDonHang");
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0; // Nếu lỗi hoặc không có đơn
}
    @Override
public List<NguoiDung> findByUsername(String username) {
    String sql = "SELECT * FROM Users WHERE Username LIKE ?";
    return selectBySql(sql, "%" + username + "%"); // Cho phép tìm mờ (ví dụ: "user")
}


}
