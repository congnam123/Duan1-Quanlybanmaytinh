
package dao;

import model.User1;
import java.sql.*;

public class UserDAO1 {
    private final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=login_app;encrypt=true;trustServerCertificate=true";
    private final String USERNAME = "sa"; 
    private final String PASSWORD = "123456"; 

    public User1 findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection con = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String fullName = rs.getString("fullName");
                String password = rs.getString("password");
                return new User1(fullName, email, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
