/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maytinh.dao;

import maytinh.entity.User;
import java.util.List;

public interface UserDAO {

    User create(User entity);

    void update(User entity);

    void deleteById(String username);

    List<User> findAll();

    User findById(String username);

    User login(String username, String password);
}
