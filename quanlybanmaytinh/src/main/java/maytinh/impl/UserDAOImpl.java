package maytinh.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import maytinh.dao.UserDAO;
import maytinh.entity.User;
import maytinh.util.XJdbc;
import maytinh.util.XQuery;

public class UserDAOImpl implements UserDAO {

    String createSql = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager, Email, NgaySinh, DiaChi, SoDienThoai, PhanLoai ,PhanLoaiTaiKhoan ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=?, Email=?, NgaySinh=?, DiaChi=?, SoDienThoai=?, PhanLoai=?,PhanLoaiTaiKhoan=? WHERE Username=?";
    String deleteSql = "DELETE FROM Users WHERE Username=?";
    String findAllSql = "SELECT * FROM Users";
    String findByUsernameSql = "SELECT * FROM Users WHERE Username=?";

    @Override
    public User create(User entity) {
        Object[] values = {
            entity.getUsername(),
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getEmail(),
            entity.getNgaySinh(),
            entity.getDiaChi(),
            entity.getSoDienThoai(),
            entity.getPhanLoai(),
            entity.getPhanLoaiTaiKhoan()
        };
        XJdbc.executeUpdate(createSql, values);
        return entity;
    }

    @Override
    public void update(User entity) {
        Object[] values = {
            entity.getPassword(),
            entity.isEnabled(),
            entity.getFullname(),
            entity.getPhoto(),
            entity.isManager(),
            entity.getEmail(),
            entity.getNgaySinh(),
            entity.getDiaChi(),
            entity.getSoDienThoai(),
            entity.getPhanLoai(),
            entity.getUsername(),
            entity.getPhanLoaiTaiKhoan()
        };
        XJdbc.executeUpdate(updateSql, values);
    }

    @Override
    public void deleteById(String username) {
        XJdbc.executeUpdate(deleteSql, username);
    }

    @Override
    public List<User> findAll() {
        return XQuery.getBeanList(User.class, findAllSql);
    }

    @Override
    public User findById(String username) {
        return XQuery.getSingleBean(User.class, findByUsernameSql, username);
    }

    public User findByUsername(String username) {
        return XQuery.getSingleBean(User.class, findByUsernameSql, username);
    }

    @Override
    public void insert(User user) {
        String sql = "INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager, Email, NgaySinh, DiaChi, SoDienThoai, PhanLoai) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        XJdbc.update(sql,
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getFullname(),
                user.getPhoto(),
                user.isManager(),
                user.getEmail(),
                user.getNgaySinh(),
                user.getDiaChi(),
                user.getSoDienThoai(),
                user.getPhanLoai()
        );
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sql = "SELECT * FROM Users WHERE Username = ? AND Password = ?";
        return XQuery.getSingleBean(User.class, sql, username, password);
    }

    @Override
    public User findByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";
        try {
            ResultSet rs = XJdbc.query(sql, email);
            if (rs.next()) {
                return readFromResultSet(rs);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private User readFromResultSet(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUsername(rs.getString("Username"));
        user.setPassword(rs.getString("Password"));
        user.setFullname(rs.getString("Fullname"));
        user.setManager(rs.getBoolean("Manager"));
        user.setEnabled(rs.getBoolean("Enabled"));
        user.setPhoto(rs.getString("Photo"));
        user.setEmail(rs.getString("Email"));
        user.setNgaySinh(rs.getDate("NgaySinh"));
        user.setDiaChi(rs.getString("DiaChi"));
        user.setSoDienThoai(rs.getString("SoDienThoai"));
        user.setPhanLoai(rs.getString("PhanLoaiTaiKhoan"));
        return user;
    }

}
