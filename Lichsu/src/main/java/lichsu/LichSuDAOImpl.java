package lichsu;

import java.sql.*;
import java.util.*;
import maytinh.dao.LichSuDAO;
import maytinh.entity.LichSu;
import maytinh.util.XJdbc;

public class LichSuDAOImpl implements LichSuDAO {

    
    @Override
    public List<LichSu> getLichSuByUsername(String username) {
        List<LichSu> list = new ArrayList<>();

        String sql = "SELECT dh.maDH, sp.tenSP, ctdh.soLuong, ctdh.donGia, dh.trangThai , dh.NgayLap " +
                     "FROM DonHang dh " +
                     "JOIN ChiTietDonHang ctdh ON dh.maDH = ctdh.maDH " +
                     "JOIN SanPham sp ON ctdh.MaSP = sp.MaSP " +
                     "WHERE dh.Username = ? " +
                     "ORDER BY dh.NgayLap DESC";

        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LichSu ls = new LichSu();
                ls.setMaDH(rs.getInt("MaDH"));
                ls.setTenSP(rs.getString("TenSP"));
                ls.setSoLuong(rs.getInt("SoLuong"));
                ls.setDonGia(rs.getInt("DonGia"));
                ls.setTrangThai(rs.getString("TrangThai"));
                 ls.setNgayMua(rs.getTimestamp("NgayLap"));
                list.add(ls);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    

    @Override
    public List<LichSu> searchByDateRange(String username, String from, String to) {
        List<LichSu> list = new ArrayList<>();
        String sql = """
            SELECT dh.MaDH, sp.TenSP, ctdh.SoLuong, ctdh.DonGia, dh.TrangThai, dh.NgayLap
            FROM DonHang dh
            JOIN ChiTietDonHang ctdh ON dh.MaDH = ctdh.MaDH
            JOIN SanPham sp ON sp.MaSP = ctdh.MaSP
            WHERE dh.Username = ? AND dh.NgayLap BETWEEN ? AND ?
        """;

        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, from);
            ps.setString(3, to);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSu ls = new LichSu();
                ls.setMaDH(rs.getInt("MaDH"));
                ls.setTenSP(rs.getString("TenSP"));
                ls.setSoLuong(rs.getInt("SoLuong"));
                ls.setDonGia(rs.getInt("DonGia"));
                ls.setTrangThai(rs.getString("TrangThai"));
                ls.setNgayMua(rs.getDate("NgayLap"));
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void deleteLichSuByUsername(String username) {
        String sql = "DELETE FROM DonHang WHERE Username = ?";
        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<LichSu> searchByTenSP(String username, String tenSP) {
        List<LichSu> list = new ArrayList<>();
        try {
            Connection con = XJdbc.getConnection();
            String sql = "SELECT * FROM LichSuMuaHang WHERE TenSP LIKE ? AND Username = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + tenSP + "%");
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSu ls = new LichSu();
                ls.setMaDH(rs.getInt("MaDH"));
                ls.setTenSP(rs.getString("TenSP"));
                // thêm các trường khác nếu có
                list.add(ls);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<LichSu> searchByDateRangeAndProduct(String username, String fromDate, String toDate, String tenSP) {
    List<LichSu> list = new ArrayList<>();
    String sql = "SELECT * FROM LichSuMuaHang WHERE Username = ? AND TenSP LIKE ? AND NgayLap BETWEEN ? AND ?";
    
    try (Connection conn = XJdbc.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, username);
        stmt.setString(2, "%" + tenSP + "%");
        stmt.setString(3, fromDate);
        stmt.setString(4, toDate);
        
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            LichSu ls = new LichSu();
            ls.setMaDH(rs.getInt("MaDH"));
            ls.setTenSP(rs.getString("TenSP"));
            ls.setSoLuong(rs.getInt("SoLuong"));
            ls.setDonGia(rs.getInt("DonGia"));
            ls.setTrangThai(rs.getString("TrangThai"));
            ls.setNgayMua(rs.getDate("NgayMua"));
            list.add(ls);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    return list;
}

   @Override
public List<LichSu> findAll() {
    List<LichSu> list = new ArrayList<>();
    String sql = "SELECT * FROM LichSuMuaHang";
    try (Connection conn = XJdbc.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            LichSu ls = new LichSu();
            ls.setMaDH(rs.getInt("MaDH"));
            ls.setTenSP(rs.getString("TenSP"));
            ls.setSoLuong(rs.getInt("SoLuong"));
            ls.setDonGia(rs.getInt("DonGia"));
            ls.setTrangThai(rs.getString("TrangThai"));
            ls.setNgayMua(rs.getDate("NgayMua"));
            list.add(ls);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}



}
