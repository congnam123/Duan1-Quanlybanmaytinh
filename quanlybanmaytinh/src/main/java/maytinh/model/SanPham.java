package maytinh.model;

/**
 * Class đại diện cho sản phẩm
 */
public class SanPham {
    private String tenSP;
    private String loaiSP;
    private double gia;
    private int soLuong;
    private String moTa;
    private String hinhAnh;

    // Constructor mặc định
    public SanPham() {
    }

    // Constructor đầy đủ
    public SanPham(String tenSP, String loaiSP, double gia, int soLuong) {
        this.tenSP = tenSP;
        this.loaiSP = loaiSP;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    // Getter và Setter
    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
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

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "tenSP='" + tenSP + '\'' +
                ", loaiSP='" + loaiSP + '\'' +
                ", gia=" + gia +
                ", soLuong=" + soLuong +
                '}';
    }
}
