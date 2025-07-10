package maytinh.util;

import java.sql.*;

/**
 * Lớp tiện ích hỗ trợ làm việc với CSDL quan hệ
 *
 * @author NghiemN
 * @version 1.0
 */
public class XJdbc {

    private static Connection connection;

    /**
     * Mở kết nối nếu chưa mở hoặc đã đóng
     */
    public static Connection openConnection() {
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dburl = "jdbc:sqlserver://localhost:1433;database=QLBanmaytinh;encrypt=true;trustServerCertificate=true;";
        String username = "sa";
        String password = "12345";

        try {
            if (!isReady()) {
                Class.forName(driver);
                connection = DriverManager.getConnection(dburl, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException("Không thể kết nối đến CSDL", e);
        }
        return connection;
    }

    /**
     * Kiểm tra kết nối đã mở hay chưa
     */
    public static boolean isReady() {
        try {
            return (connection != null && !connection.isClosed());
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Đóng kết nối
     */
    public static void closeConnection() {
        try {
            if (isReady()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Không thể đóng kết nối", e);
        }
    }

    /**
     * Truy vấn dữ liệu (SELECT)
     */
    public static ResultSet executeQuery(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi truy vấn: " + sql, e);
        }
    }

    /**
     * Cập nhật dữ liệu (INSERT, UPDATE, DELETE)
     */
    public static int executeUpdate(String sql, Object... args) {
        try {
            PreparedStatement stmt = prepareStatement(sql, args);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Lỗi cập nhật: " + sql, e);
        }
    }

    /**
     * Truy vấn một giá trị đơn
     */
    public static Object executeQuerySingleValue(String sql, Object... args) {
        try (Connection con = openConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getObject(1);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException("Lỗi truy vấn đơn giá trị", e);
        }
    }

    /**
     * Tạo PreparedStatement từ câu lệnh SQL và đối số
     */
    private static PreparedStatement prepareStatement(String sql, Object... args) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            stmt.setObject(i + 1, args[i]);
        }
        return stmt;
    }
    
} 
