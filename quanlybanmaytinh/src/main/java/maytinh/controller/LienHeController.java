package maytinh.controller;

import java.util.List;
import maytinh.dao.LienHeDAO;
import maytinh.entity.LienHe;
import maytinh.impl.LienHeDAOImpl;

public class LienHeController {
    private final LienHeDAO dao = new LienHeDAOImpl();

    public void guiLienHe(LienHe lh) {
        dao.insert(lh);
    }

    public List<LienHe> getLienHeTheoUsername(String username) {
        return dao.selectByUsername(username);
    }

    public List<LienHe> getAllLienHe() {
        return dao.getAll();
    }

    public LienHe getLienHeById(int id) {
        return dao.findById(id);
    }
}
