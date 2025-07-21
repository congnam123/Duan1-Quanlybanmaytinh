package maytinh.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import maytinh.entity.Quanlybanhang;
import maytinh.util.XJdbc;


public class QuanlybanhangImpl {

    public List<Quanlybanhang> getAllDonHang() {
        List<Quanlybanhang> list = new ArrayList<>();
        try {
            String sql = """
                SELECT 
                    u.fullname AS TenKhachHang, 
                    p.TenDienThoai AS TenSanPham, 
                    bd.SoLuong, 
                    b.TongTien,
                    b.PhuongThucThanhToan,
                    b.TrangThai
                FROM Bill b
                JOIN Users u ON b.Username = u.Username
                JOIN BillDetails bd ON b.MaHD = bd.MaHD
                JOIN Drinks p ON bd.MaDT = p.MaDT
            """;

            ResultSet rs = XJdbc.query(sql);
            while (rs.next()) {
                String tenKhachHang = rs.getString("TenKhachHang");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                double tongTien = rs.getDouble("TongTien");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                String trangThai = rs.getString("TrangThai");

                Quanlybanhang qlbh = new Quanlybanhang(
                        tenKhachHang,
                        tenSanPham,
                        soLuong,
                        tongTien,
                        phuongThuc,
                        trangThai
                );

                list.add(qlbh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Quanlybanhang> searchDonHangByTenKhachHang(String keyword) {
        List<Quanlybanhang> list = new ArrayList<>();
        try {
            String sql = """
                SELECT 
                    u.fullname AS TenKhachHang, 
                    p.TenDienThoai AS TenSanPham, 
                    bd.SoLuong, 
                    b.TongTien,
                    b.PhuongThucThanhToan,
                    b.TrangThai
                FROM Bill b
                JOIN Users u ON b.Username = u.Username
                JOIN BillDetails bd ON b.MaHD = bd.MaHD
                JOIN Drinks p ON bd.MaDT = p.MaDT
                WHERE u.fullname LIKE ?
            """;

            ResultSet rs = XJdbc.query(sql, "%" + keyword + "%");
            while (rs.next()) {
                String tenKhachHang = rs.getString("TenKhachHang");
                String tenSanPham = rs.getString("TenSanPham");
                int soLuong = rs.getInt("SoLuong");
                double tongTien = rs.getDouble("TongTien");
                String phuongThuc = rs.getString("PhuongThucThanhToan");
                String trangThai = rs.getString("TrangThai");

                Quanlybanhang qlbh = new Quanlybanhang(
                        tenKhachHang,
                        tenSanPham,
                        soLuong,
                        tongTien,
                        phuongThuc,
                        trangThai
                );

                list.add(qlbh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
