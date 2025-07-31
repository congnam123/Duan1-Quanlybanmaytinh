package maytinh.entity;

/**
 * Entity class cho Sản Phẩm
 * @author Cong Nam
 */
public class SanPham {
    private int maSP;
    private String tenSP;
    private double gia;
    private String moTa;
    private String hinhAnh;
    private String loaiSP;
    private int soLuong;
    private boolean trangThai;

    // Constructor mặc định
    public SanPham() {
    }

    // Constructor đầy đủ
    public SanPham(int maSP, String tenSP, double gia, String moTa, String hinhAnh, String loaiSP, int soLuong, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.gia = gia;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
        this.loaiSP = loaiSP;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
    }

    // Getter và Setter methods
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

    public String getLoaiSP() {
        return loaiSP;
    }

    public void setLoaiSP(String loaiSP) {
        this.loaiSP = loaiSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPham{" +
                "maSP=" + maSP +
                ", tenSP='" + tenSP + '\'' +
                ", gia=" + gia +
                ", moTa='" + moTa + '\'' +
                ", hinhAnh='" + hinhAnh + '\'' +
                ", loaiSP='" + loaiSP + '\'' +
                ", soLuong=" + soLuong +
                ", trangThai=" + trangThai +
                '}';
    }
}
