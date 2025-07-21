/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maytinh.dao;

import maytinh.entity.User;

public interface UserDAO extends CrudDao<User, String> {

    User findByUsernameAndPassword(String username, String password);
    User findByEmail(String email);
    void update(User user);
    public void insert(User user);

}
