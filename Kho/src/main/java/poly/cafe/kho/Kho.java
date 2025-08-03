package poly.cafe.kho;

import java.sql.Timestamp;

/**
 * Entity Kho - Đại diện cho dữ liệu kho trong hệ thống
 * @author Admin
 */
public class Kho {
    private int maKho;
    private String maHang;
    private String tenHang;
    private int soLuong;
    private double giaTien;
    private String trangThai; // "Xuất" hoặc "Nhập"
    private Timestamp thoiGian;

    public Kho() {
    }

    public Kho(int maKho, String maHang, String tenHang, int soLuong, double giaTien, String trangThai, Timestamp thoiGian) {
        this.maKho = maKho;
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.soLuong = soLuong;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
        this.thoiGian = thoiGian;
    }

    // Getters and Setters
    public int getMaKho() {
        return maKho;
    }

    public void setMaKho(int maKho) {
        this.maKho = maKho;
    }

    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Timestamp getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(Timestamp thoiGian) {
        this.thoiGian = thoiGian;
    }

    @Override
    public String toString() {
        return "Kho{" +
                "maKho=" + maKho +
                ", maHang='" + maHang + '\'' +
                ", tenHang='" + tenHang + '\'' +
                ", soLuong=" + soLuong +
                ", giaTien=" + giaTien +
                ", trangThai='" + trangThai + '\'' +
                ", thoiGian=" + thoiGian +
                '}';
    }
} 