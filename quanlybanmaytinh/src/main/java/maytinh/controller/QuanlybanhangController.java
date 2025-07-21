package maytinh.controller;

import javax.swing.JTable;
import javax.swing.JTextField;

public interface QuanlybanhangController {

    void fillToTable(JTable table);

    void timKiemDonHang(JTable table, JTextField txtSearch);

    void capNhatDonHang(JTable tblDonHang);

    void xoaDonHang(JTable tblDonHang);

}
