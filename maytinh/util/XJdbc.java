package maytinh.util;

import java.sql.*;

public class XJdbc {

    // ==== CẤU HÌNH CƠ SỞ DỮ LIỆU ====
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;database=maytinh_db;encrypt=true;trustServerCertificate=true;";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "123456"; // 🔁 Đảm bảo đúng với SQL Server của bạn
    private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    private static Connection connection;

    // ==== MỞ KẾT NỐI ====
    public static Connection openConnection() {
        try {
            if (!isReady()) {
                Class.forName(DRIVER);
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("❌ Không thể kết nối CSDL", e);
        }
        return connection;
    }

    // ==== KIỂM TRA TRẠNG THÁI KẾT NỐI ====
    public static boolean isReady() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    // ==== ĐÓNG KẾT NỐI ====
    public static void closeConnection() {
        try {
            if (isReady()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("❌ Không thể đóng kết nối", e);
        }
    }

    // ==== TRUY VẤN SELECT ====
    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Lỗi truy vấn SELECT: " + sql, e);
        }
    }

    // ==== TRUY VẤN UPDATE / INSERT / DELETE ====
    public static int executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("❌ Lỗi cập nhật: " + sql, e);
        }
    }

    // ==== TRUY VẤN GIÁ TRỊ ĐƠN ====
    public static Object executeQuerySingleValue(String sql, Object... args) {
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi truy vấn giá trị đơn", e);
        }
    }

    // ==== TẠO PREPARED STATEMENT ====
    private static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    // ==== CẬP NHẬT VỚI ĐÓNG KẾT NỐI ====
    public static void update(String sql, Object... args) {
        try (PreparedStatement stmt = getStmt(sql, args)) {
            stmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("❌ Lỗi khi update dữ liệu", e);
        }
    }

    // ==== LẤY PREPARED STATEMENT MỚI ====
    public static PreparedStatement getStmt(String sql, Object... args) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }

    // ==== TẠO KẾT NỐI MỚI MỖI LẦN ====
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("❌ Không tìm thấy driver SQL Server", e);
        }
    }
}
