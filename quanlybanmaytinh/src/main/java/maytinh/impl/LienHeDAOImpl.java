package maytinh.impl;

import java.sql.*;
import java.util.*;
import java.util.Date;
import maytinh.dao.LienHeDAO;
import maytinh.entity.LienHe;
import maytinh.util.XJdbc;

public class LienHeDAOImpl implements LienHeDAO {

    @Override
    public void insert(LienHe lh) {
        String sql = "INSERT INTO LienHe (Username, NoiDung, ThoiGian, Status) VALUES (?, ?, ?, ?)";
        try {
            XJdbc.update(sql,
                lh.getUsername(),
                lh.getNoidung(),
                new java.sql.Timestamp(lh.getThoigian().getTime()),
                lh.getStatus() != null ? lh.getStatus() : "pending");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LienHe> selectByUsername(String username) {
        String sql = "SELECT * FROM LienHe WHERE Username = ?";
        return selectBySql(sql, username);
    }

    public List<LienHe> getAll() {
        String sql = "SELECT * FROM LienHe";
        return selectBySql(sql);
    }

    @Override
    public void updateAdminReply(int id, String adminReply, String status) {
        String sql = "UPDATE LienHe SET AdminReply = ?, AdminReplyTime = ?, Status = ? WHERE ID = ?";
        try {
            XJdbc.update(sql, adminReply, new java.sql.Timestamp(new Date().getTime()), status, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<LienHe> getPendingContacts() {
        String sql = "SELECT * FROM LienHe WHERE Status = 'pending' ORDER BY ThoiGian DESC";
        return selectBySql(sql);
    }

    @Override
    public LienHe findById(int id) {
        String sql = "SELECT * FROM LienHe WHERE ID = ?";
        List<LienHe> list = selectBySql(sql, id);
        return list.isEmpty() ? null : list.get(0);
    }

    private List<LienHe> selectBySql(String sql, Object... args) {
        List<LienHe> list = new ArrayList<>();
        try (ResultSet rs = XJdbc.query(sql, args)) {
            while (rs.next()) {
                LienHe lh = new LienHe();
                lh.setId(rs.getInt("ID"));
                lh.setUsername(rs.getString("Username"));
                lh.setNoidung(rs.getString("NoiDung"));
                lh.setThoigian(rs.getTimestamp("ThoiGian"));
                lh.setAdminReply(rs.getString("AdminReply"));
                lh.setAdminReplyTime(rs.getTimestamp("AdminReplyTime"));
                lh.setStatus(rs.getString("Status"));
                list.add(lh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
