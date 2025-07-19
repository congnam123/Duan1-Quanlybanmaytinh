package maytinh.impl;

import java.util.ArrayList;
import java.util.List;
import maytinh.dao.UserDao1;
import maytinh.entity.User1;

public abstract class UserDAOImpl1 implements UserDao1 {

    private static final List<User1> users = new ArrayList<>();

    @Override
    public boolean isEmailExists(String email) {
        return users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }

    @Override
    public void insert(User1 user) {
        users.add(user);
    }

    /**
     *
     * @return
     */
    @Override
    public List<User1> getAll() {
        return users;
    }
}
