package maytinh.entity;

import java.util.Date;

/**
 * Entity cho Giỏ hàng
 * @author PC
 */
public class GioHang {
    private int maGioHang;
    private int maSP;
    private String tenSP;
    private double gia;
    private int soLuong;
    private String username;
    private Date ngayThem;
    private boolean trangThai;
    
    // Constructors
    public GioHang() {
    }
    
    public GioHang(int maSP, String tenSP, double gia, int soLuong, String username) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.soLuong = soLuong;
        this.username = username;
        this.ngayThem = new Date();
        this.trangThai = true;
    }
    
    // Getters and Setters
    public int getMaGioHang() {
        return maGioHang;
    }
    
    public void setMaGioHang(int maGioHang) {
        this.maGioHang = maGioHang;
    }
    
    public int getMaSP() {
        return maSP;
    }
    
    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }
    
    public String getTenSP() {
        return tenSP;
    }
    
    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }
    
    public double getGia() {
        return gia;
    }
    
    public void setGia(double gia) {
        this.gia = gia;
    }
    
    public int getSoLuong() {
        return soLuong;
    }
    
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Date getNgayThem() {
        return ngayThem;
    }
    
    public void setNgayThem(Date ngayThem) {
        this.ngayThem = ngayThem;
    }
    
    public boolean isTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    // Utility methods
    public double getThanhTien() {
        return gia * soLuong;
    }
    
    @Override
    public String toString() {
        return "GioHang{" +
                "maGioHang=" + maGioHang +
                ", maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                ", username='" + username + '\'' +
                ", ngayThem=" + ngayThem +
                ", trangThai=" + trangThai +
                '}';
    }
}
