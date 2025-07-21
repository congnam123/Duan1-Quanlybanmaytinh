package maytinh.entity;

import java.sql.Date;

public class NguoiDung {
    private String username;
    private String fullname;
    private Date NgaySinh;
    private String DiaChi;
    private String SoDienThoai;
    private String phanLoai;     
    private int tongDonHang;

    // GETTER
    public String getUsername() { return username; }
    public String getFullname() { return fullname; }
    public Date getNgaySinh() { return NgaySinh; }
    public String getDiaChi() { return DiaChi; }
    public String getSoDienThoai() { return SoDienThoai; }
    public String getPhanLoai() { return phanLoai; }
    public int getTongDonHang() { return tongDonHang; }

    // SETTER
    public void setUsername(String username) { this.username = username; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public void setNgaySinh(Date NgaySinh) { this.NgaySinh = NgaySinh; }
    public void setDiaChi(String DiaChi) { this.DiaChi = DiaChi; }
    public void setSoDienThoai(String SoDienThoai) { this.SoDienThoai = SoDienThoai; }
    public void setPhanLoai(String phanLoai) { this.phanLoai = phanLoai; }
    public void setTongDonHang(int tongDonHang) { this.tongDonHang = tongDonHang; }
}
