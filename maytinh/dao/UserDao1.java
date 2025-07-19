package maytinh.dao;

import java.util.List;
import maytinh.entity.User1;

public interface UserDao1 {

    boolean isEmailExists(String email);

    void insert(User1 user);

    List<User1> getAll();
}
