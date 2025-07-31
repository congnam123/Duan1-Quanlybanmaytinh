package maytinh.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import maytinh.dao.GioHangDAO;
import maytinh.entity.GioHang;
import maytinh.util.XJdbc;

/**
 * Implementation của GioHangDAO
 * @author PC
 */
public class GioHangDAOImpl implements GioHangDAO {

    @Override
    public GioHang create(GioHang entity) {
        // ✅ Sửa SQL để phù hợp với cấu trúc bảng hiện tại
        String sql = "INSERT INTO GioHang (Username, MaSP, SoLuong, NgayThem, TrangThai) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, entity.getUsername());
            ps.setInt(2, entity.getMaSP());
            ps.setInt(3, entity.getSoLuong());
            ps.setTimestamp(4, new Timestamp(entity.getNgayThem().getTime()));
            ps.setBoolean(5, entity.isTrangThai());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setMaGioHang(generatedKeys.getInt(1));
                    }
                }
            }
            return entity;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void update(GioHang entity) {
        // ✅ Sửa SQL để phù hợp với cấu trúc bảng hiện tại
        String sql = "UPDATE GioHang SET MaSP=?, SoLuong=?, Username=?, NgayThem=?, TrangThai=? WHERE MaGioHang=?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, entity.getMaSP());
            ps.setInt(2, entity.getSoLuong());
            ps.setString(3, entity.getUsername());
            ps.setTimestamp(4, new Timestamp(entity.getNgayThem().getTime()));
            ps.setBoolean(5, entity.isTrangThai());
            ps.setInt(6, entity.getMaGioHang());

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM GioHang WHERE MaGioHang=?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<GioHang> findAll() {
        List<GioHang> list = new ArrayList<>();
        String sql = "SELECT * FROM GioHang";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(createGioHangFromResultSet(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public GioHang findById(Integer id) {
        String sql = "SELECT * FROM GioHang WHERE MaGioHang=?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createGioHangFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<GioHang> selectByUsername(String username) {
        List<GioHang> list = new ArrayList<>();
        String sql = "SELECT gh.*, sp.TenSP, sp.Gia FROM GioHang gh " +
                     "LEFT JOIN SanPham sp ON gh.MaSP = sp.MaSP " +
                     "WHERE gh.Username=? AND gh.TrangThai=1 ORDER BY gh.NgayThem DESC";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(createGioHangFromResultSet(rs));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addToCart(int maSP, int soLuong, String username) {
        // Kiểm tra sản phẩm đã có trong giỏ hàng chưa
        GioHang existing = findByProductAndUser(maSP, username);

        if (existing != null) {
            // Nếu đã có thì cập nhật số lượng
            existing.setSoLuong(existing.getSoLuong() + soLuong);
            update(existing);
            return true;
        } else {
            // Nếu chưa có thì thêm mới
            // ✅ Tạo GioHang đơn giản không cần TenSP và Gia
            GioHang gioHang = new GioHang();
            gioHang.setMaSP(maSP);
            gioHang.setSoLuong(soLuong);
            gioHang.setUsername(username);
            gioHang.setNgayThem(new java.util.Date());
            gioHang.setTrangThai(true);

            GioHang result = create(gioHang);
            return result != null;
        }
    }

    @Override
    public boolean updateQuantity(int maGioHang, int soLuong) {
        String sql = "UPDATE GioHang SET SoLuong=? WHERE MaGioHang=?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, soLuong);
            ps.setInt(2, maGioHang);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean removeFromCart(int maGioHang) {
        try {
            deleteById(maGioHang);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean clearCart(String username) {
        String sql = "DELETE FROM GioHang WHERE Username=?";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public GioHang findByProductAndUser(int maSP, String username) {
        String sql = "SELECT * FROM GioHang WHERE MaSP=? AND Username=? AND TrangThai=1";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maSP);
            ps.setString(2, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return createGioHangFromResultSet(rs);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCartItemCount(String username) {
        String sql = "SELECT COUNT(*) FROM GioHang WHERE Username=? AND TrangThai=1";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            // Nếu bảng chưa tồn tại, trả về 0
            System.err.println("Bảng GioHang chưa tồn tại. Vui lòng chạy script create_tables.sql");
            return 0;
        }
        return 0;
    }

    @Override
    public double getTotalAmount(String username) {
        // ✅ Sửa SQL để JOIN với SanPham để lấy giá
        String sql = "SELECT SUM(sp.Gia * gh.SoLuong) " +
                     "FROM GioHang gh " +
                     "INNER JOIN SanPham sp ON gh.MaSP = sp.MaSP " +
                     "WHERE gh.Username=? AND gh.TrangThai=1";
        try (Connection conn = XJdbc.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    double total = rs.getDouble(1);
                    return rs.wasNull() ? 0.0 : total; // Xử lý trường hợp NULL
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Lỗi khi tính tổng tiền giỏ hàng: " + e.getMessage());
            return 0.0;
        }
        return 0.0;
    }
    
    /**
     * Tạo đối tượng GioHang từ ResultSet
     */
    private GioHang createGioHangFromResultSet(ResultSet rs) throws SQLException {
        GioHang gh = new GioHang();
        gh.setMaGioHang(rs.getInt("MaGioHang"));
        gh.setMaSP(rs.getInt("MaSP"));
        gh.setSoLuong(rs.getInt("SoLuong"));
        gh.setUsername(rs.getString("Username"));
        gh.setNgayThem(rs.getTimestamp("NgayThem"));
        gh.setTrangThai(rs.getBoolean("TrangThai"));

        // ✅ Chỉ set TenSP và Gia nếu có trong ResultSet (từ JOIN với SanPham)
        try {
            gh.setTenSP(rs.getString("TenSP"));
            gh.setGia(rs.getDouble("Gia"));
        } catch (SQLException e) {
            // Nếu không có cột TenSP và Gia (query không JOIN), bỏ qua
            gh.setTenSP("");
            gh.setGia(0.0);
        }

        return gh;
    }
}
