package maytinh.impl;

import java.sql.*;
import java.util.*;
import maytinh.dao.LichSuDAO;
import maytinh.entity.LichSu1;
import maytinh.util.XJdbc;

public class LichSuDAOImpl implements LichSuDAO {

    @Override
    public List<LichSu1> getLichSuByUsername(String username) {
        List<LichSu1> list = new ArrayList<>();
        String sql = """
            SELECT dh.MaDH, sp.TenSP, ctdh.SoLuong, ctdh.DonGia, dh.TrangThai, dh.NgayLap
            FROM DonHang dh
            JOIN ChiTietDonHang ctdh ON dh.MaDH = ctdh.MaDH
            JOIN SanPham sp ON sp.MaSP = ctdh.MaSP
            WHERE dh.Username = ?
        """;

        try (Connection conn = XJdbc.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LichSu1 ls = new LichSu1();
                ls.setMaDH(rs.getInt("MaDH"));
                ls.setTenSP(rs.getString("TenSP"));
                ls.setSoLuong(rs.getInt("SoLuong"));
                ls.setDonGia(rs.getDouble("DonGia"));
                ls.setTrangThai(rs.getString("TrangThai"));
                ls.setNgayLap(rs.getDate("NgayLap"));
                list.add(ls);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public List<LichSu1> searchByDateRange(String username, String from, String to) {
        List<LichSu1> list = new ArrayList<>();
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
                LichSu1 ls = new LichSu1();
                ls.setMaDH(rs.getInt("MaDH"));
                ls.setTenSP(rs.getString("TenSP"));
                ls.setSoLuong(rs.getInt("SoLuong"));
                ls.setDonGia(rs.getDouble("DonGia"));
                ls.setTrangThai(rs.getString("TrangThai"));
                ls.setNgayLap(rs.getDate("NgayLap"));
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
}
