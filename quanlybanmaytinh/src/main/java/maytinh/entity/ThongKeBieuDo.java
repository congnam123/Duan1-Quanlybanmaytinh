package maytinh.entity;

import java.sql.ResultSet;
import javax.swing.JFrame;
import maytinh.util.XJdbc;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ThongKeBieuDo {

    public void hienThiBieuDoDoanhThu() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            String sql = "SELECT LoaiMayTinh, SUM(ct.SoLuong * sp.Gia) AS DoanhThu "
                       + "FROM ChiTietDonHang ct "
                       + "JOIN SanPham sp ON ct.MaSP = sp.ID "
                       + "GROUP BY LoaiMayTinh";
            ResultSet rs = XJdbc.query(sql);

            while (rs.next()) {
                String loai = rs.getString("LoaiMayTinh");
                double doanhThu = rs.getDouble("DoanhThu");
                dataset.addValue(doanhThu, "Doanh thu", loai);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê doanh thu theo loại máy tính",
                "Loại máy",
                "Doanh thu",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        ChartPanel chartPanel = new ChartPanel(barChart);
        JFrame frame = new JFrame("Biểu đồ doanh thu");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }
}
