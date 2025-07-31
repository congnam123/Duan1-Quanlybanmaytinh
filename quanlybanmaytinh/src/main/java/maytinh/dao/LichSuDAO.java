package maytinh.dao;

import java.util.List;
import maytinh.entity.LichSu1;

public interface LichSuDAO {
    List<LichSu1> getLichSuByUsername(String username);
    List<LichSu1> searchByDateRange(String username, String from, String to);
    void deleteLichSuByUsername(String username);
}
