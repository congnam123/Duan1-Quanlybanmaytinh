package maytinh.entity;

public class Quanlybanhang {
    private String tenKhachHang;
    private String tenSanPham;
    private int soLuong;
    private double tongTien;
    private String phuongThucThanhToan;
    private String trangThai;

    public Quanlybanhang() {
    }

    public Quanlybanhang(String tenKhachHang, String tenSanPham, int soLuong, double tongTien, String phuongThucThanhToan, String trangThai) {
        this.tenKhachHang = tenKhachHang;
        this.tenSanPham = tenSanPham;
        this.soLuong = soLuong;
        this.tongTien = tongTien;
        this.phuongThucThanhToan = phuongThucThanhToan;
        this.trangThai = trangThai;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getPhuongThucThanhToan() {
        return phuongThucThanhToan;
    }

    public void setPhuongThucThanhToan(String phuongThucThanhToan) {
        this.phuongThucThanhToan = phuongThucThanhToan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}
