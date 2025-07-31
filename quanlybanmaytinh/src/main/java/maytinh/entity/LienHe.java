package maytinh.entity;

import java.util.Date;

public class LienHe {
    private int id;
    private String username;
    private String noidung;
    private Date thoigian;
    private String adminReply;
    private Date adminReplyTime;
    private String status; // "pending", "replied", "closed"

    public LienHe() {}

    public LienHe(int id, String username, String noidung, Date thoigian) {
        this.id = id;
        this.username = username;
        this.noidung = noidung;
        this.thoigian = thoigian;
        this.status = "pending";
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getNoidung() { return noidung; }
    public void setNoidung(String noidung) { this.noidung = noidung; }

    public Date getThoigian() { return thoigian; }
    public void setThoigian(Date thoigian) { this.thoigian = thoigian; }

    public String getAdminReply() { return adminReply; }
    public void setAdminReply(String adminReply) { this.adminReply = adminReply; }

    public Date getAdminReplyTime() { return adminReplyTime; }
    public void setAdminReplyTime(Date adminReplyTime) { this.adminReplyTime = adminReplyTime; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
