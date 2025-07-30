package lichsu;

import java.util.List;
import maytinh.entity.LichSu;

public interface LichSuDAO {

   

    List<LichSu> getLichSuByUsername(String username);

    List<LichSu> searchByDateRange(String username, String from, String to);

    void deleteLichSuByUsername(String username);

    List<LichSu> searchByTenSP(String username, String tenSP);

    public List<LichSu> searchByDateRangeAndProduct(String username, String from, String to, String tenSP);

    public List<LichSu> findAll();
}
