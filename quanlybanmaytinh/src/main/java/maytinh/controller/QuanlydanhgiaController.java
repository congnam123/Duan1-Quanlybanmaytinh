package maytinh.controller;

import javax.swing.JTable;
import javax.swing.JTextField;

public interface QuanlydanhgiaController {

    void fillToTable(JTable table);

    void timKiemDanhGia(JTable table, JTextField txtSearch);

    void locDanhGiaTheoSao(JTable table, javax.swing.JComboBox comboBox);
}
