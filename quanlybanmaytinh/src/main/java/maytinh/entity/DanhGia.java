package maytinh.entity;

import java.util.Date;

public class DanhGia {
    private int maDG;
    private String username;
    private int maSP;
    private int diem;
    private String noiDung;
    private Date ngayDanhGia;

    // ✅ Thêm thuộc tính trả lời
    private String traLoi;

    public DanhGia() {}

    public DanhGia(int maDG, String username, int maSP, int diem, String noiDung, Date ngayDanhGia) {
        this.maDG = maDG;
        this.username = username;
        this.maSP = maSP;
        this.diem = diem;
        this.noiDung = noiDung;
        this.ngayDanhGia = ngayDanhGia;
    }

    // ✅ Constructor mới có thêm trả lời (nếu cần)
    public DanhGia(int maDG, String username, int maSP, int diem, String noiDung, Date ngayDanhGia, String traLoi) {
        this(maDG, username, maSP, diem, noiDung, ngayDanhGia);
        this.traLoi = traLoi;
    }

    // Getter và Setter
    public int getMaDG() {
        return maDG;
    }

    public void setMaDG(int maDG) {
        this.maDG = maDG;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayDanhGia() {
        return ngayDanhGia;
    }

    public void setNgayDanhGia(Date ngayDanhGia) {
        this.ngayDanhGia = ngayDanhGia;
    }

    // ✅ Getter & Setter cho Trả lời
    public String getTraLoi() {
        return traLoi;
    }

    public void setTraLoi(String traLoi) {
        this.traLoi = traLoi;
    }
}
