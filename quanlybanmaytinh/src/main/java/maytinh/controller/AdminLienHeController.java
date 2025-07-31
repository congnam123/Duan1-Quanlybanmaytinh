package maytinh.controller;

import java.util.List;
import maytinh.dao.LienHeDAO;
import maytinh.entity.LienHe;
import maytinh.impl.LienHeDAOImpl;

/**
 * Controller cho Admin quản lý liên hệ
 * @author Cong Nam
 */
public class AdminLienHeController {
    private final LienHeDAO dao = new LienHeDAOImpl();

    /**
     * Lấy tất cả liên hệ
     */
    public List<LienHe> getAllLienHe() {
        return dao.getAll();
    }

    /**
     * Lấy các liên hệ chưa được trả lời
     */
    public List<LienHe> getPendingContacts() {
        return dao.getPendingContacts();
    }

    /**
     * Lấy liên hệ theo username
     */
    public List<LienHe> getLienHeTheoUsername(String username) {
        return dao.selectByUsername(username);
    }

    /**
     * Lấy liên hệ theo ID
     */
    public LienHe getLienHeById(int id) {
        return dao.findById(id);
    }

    /**
     * Trả lời liên hệ
     */
    public void replyContact(int id, String adminReply) {
        dao.updateAdminReply(id, adminReply, "replied");
    }

    /**
     * Đóng liên hệ
     */
    public void closeContact(int id) {
        dao.updateAdminReply(id, null, "closed");
    }

    /**
     * Đếm số liên hệ chưa trả lời
     */
    public int countPendingContacts() {
        return getPendingContacts().size();
    }
}
