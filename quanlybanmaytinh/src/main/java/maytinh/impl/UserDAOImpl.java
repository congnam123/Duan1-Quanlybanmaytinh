/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh.impl;

import java.util.List;
import maytinh.dao.UserDAO;
import maytinh.entity.User;
import maytinh.util.XJdbc;
import maytinh.util.XQuery;

/**
 *
 * @author Admin
 */
public class UserDAOImpl implements UserDAO{
     String createSql = "INSERT INTO Users(Username, Password, Enabled, Fullname, Photo, Manager) VALUES (?, ?, ?, ?, ?, ?)";
    String updateSql = "UPDATE Users SET Password=?, Enabled=?, Fullname=?, Photo=?, Manager=? WHERE Username=?";
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
            entity.isManager()
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
            entity.getUsername()
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
    String sql = "SELECT * FROM Users WHERE Username=?";
    return XQuery.getSingleBean(User.class, sql, username);
}

@Override
public void insert(User user) {
    String sql = "INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager) VALUES (?, ?, ?, ?, ?, ?)";
    XJdbc.update(sql,
        user.getUsername(),
        user.getPassword(),
        user.isEnabled(),
        user.getFullname(),
        user.getPhoto(),
        user.isManager()
    );
}


}
