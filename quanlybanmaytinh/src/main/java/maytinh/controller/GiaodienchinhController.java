package maytinh.controller;

import javax.swing.JDialog;
import javax.swing.JFrame;
import maytinh.ui.Doimatkhau;
import maytinh.ui.Lichsu;
import maytinh.ui.Manchay;
import maytinh.ui.Quanlysanpham;
import maytinh.ui.Thongtincanhan;
import maytinh.ui.Thongtinsanpham;
import maytinh.ui.dangNhap;
import maytinh.ui.doanhthu;
import maytinh.ui.giohang;
import maytinh.ui.quanlybanhang;
import maytinh.ui.quanlydanhgia;
import maytinh.ui.quanlykhachhang;
import maytinh.ui.quanlykho;
import maytinh.ui.thanhtoan;
import maytinh.util.XDialog;

/**
 *
 * @author 84327
 */
public interface GiaodienchinhController {


    default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
        }
    }

    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    default void showWelcomeJDialog(JFrame frame) {
        this.showJDialog(new Manchay(frame, true));
    }

    default void showDangNhapJDialog(JFrame frame) {
        this.showJDialog(new dangNhap(frame, true));
    }

    default void showQuanLyKhachHangJDialog(java.awt.Frame parent) {
        this.showJDialog(new quanlykhachhang(parent, true));
    }

    default void showQuanlysanphamJDialog(java.awt.Frame parent) {
        this.showJDialog(new Quanlysanpham(parent, true));
    }

    default void showquanlykhoJDialog(java.awt.Frame parent) {
        this.showJDialog(new quanlykho(parent, true));
    }

    default void showquanlybanhangJDialog(java.awt.Frame parent) {
        this.showJDialog(new quanlybanhang(parent, true));
    }

    default void showdoanhthuJDialog(java.awt.Frame parent) {
        this.showJDialog(new doanhthu(parent, true));
    }

    default void showquanlydanhgiaJDialog(java.awt.Frame parent) {
        this.showJDialog(new quanlydanhgia(parent, true));
    }

    default void showDoimatkhauJDialog(java.awt.Frame parent) {
        this.showJDialog(new Doimatkhau(parent, true));
    }

    default void showThongtincanhanJDialog(java.awt.Frame parent) {
        this.showJDialog(new Thongtincanhan(parent, true));
    }

    default void showThongtinsanphamJDialog(java.awt.Frame parent) {
        this.showJDialog(new Thongtinsanpham(parent, true));
    }

    default void showgiohangJDialog(java.awt.Frame parent) {
        this.showJDialog(new giohang(parent, true));
    }

    default void showthanhtoanJDialog(java.awt.Frame parent) {
        this.showJDialog(new thanhtoan(parent, true));
    }

    default void showLichsuJDialog(java.awt.Frame parent) {
        this.showJDialog(new Lichsu(parent, true));
    }

}
