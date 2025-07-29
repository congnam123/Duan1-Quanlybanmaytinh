package maytinh.controller;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public interface QuanlydanhgiaController {

    void fillToTable(JTable table);

    void timKiemDanhGia(JTable table, JTextField txtSearch);

    void locDanhGiaTheoSao(JTable table, JComboBox comboBox);

    // ✅ Thêm phương thức để xử lý trả lời
    void traLoiDanhGia(JTable table, JTextField txtTraLoi);
}
