package maytinh.dao;

import java.util.List;
import maytinh.entity.LienHe;

public interface LienHeDAO {
    void insert(LienHe lh);
    List<LienHe> selectByUsername(String username);
    List<LienHe> getAll();
    void updateAdminReply(int id, String adminReply, String status);
    List<LienHe> getPendingContacts();
    LienHe findById(int id);
}
