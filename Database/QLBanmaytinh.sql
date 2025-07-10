-- Tạo cơ sở dữ liệu
CREATE DATABASE QLBanmaytinh;
GO

USE QLBanmaytinh;
GO

-- Bảng người dùng
CREATE TABLE Users (
    UserID INT PRIMARY KEY IDENTITY,
    HoTen NVARCHAR(100),
    Email VARCHAR(100) UNIQUE,
    MatKhau VARCHAR(100),
    VaiTro NVARCHAR(20) CHECK (VaiTro IN ('KhachHang', 'Admin')),
    NgayTao DATETIME DEFAULT GETDATE()
);

-- Bảng sản phẩm
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY IDENTITY,
    TenSP NVARCHAR(100),
    MoTa NVARCHAR(255),
    Gia DECIMAL(18, 2),
    SoLuongTon INT,
    NgayThem DATETIME DEFAULT GETDATE()
);

-- Đơn hàng
CREATE TABLE DonHang (
    MaDH INT PRIMARY KEY IDENTITY,
    UserID INT,
    NgayLap DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,2),
    TrangThai NVARCHAR(50),
    FOREIGN KEY (UserID) REFERENCES Users(UserID)
);

-- Chi tiết đơn hàng
CREATE TABLE ChiTietDonHang (
    MaCTDH INT PRIMARY KEY IDENTITY,
    MaDH INT,
    MaSP INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- Đánh giá sản phẩm
CREATE TABLE DanhGia (
    MaDG INT PRIMARY KEY IDENTITY,
    UserID INT,
    MaSP INT,
    NoiDung NVARCHAR(255),
    Diem INT CHECK (Diem BETWEEN 1 AND 5),
    NgayDanhGia DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (UserID) REFERENCES Users(UserID),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- Thêm người dùng (admin và khách hàng)
INSERT INTO Users (HoTen, Email, MatKhau, VaiTro)
VALUES 
(N'Nguyễn Văn A', 'admin@example.com', 'admin123', 'Admin'),
(N'Lê Thị B', 'b.le@example.com', 'khach123', 'KhachHang'),
(N'Trần Văn C', 'c.tran@example.com', 'khach456', 'KhachHang');

-- Thêm sản phẩm máy tính
INSERT INTO SanPham (TenSP, MoTa, Gia, SoLuongTon)
VALUES 
(N'Máy tính Dell OptiPlex 7090', N'Intel Core i5, 8GB RAM, 256GB SSD', 13500000, 12),
(N'Máy tính HP ProDesk 400 G7', N'Intel Core i7, 16GB RAM, 512GB SSD', 18500000, 8),
(N'Máy tính ASUS ExpertCenter D5', N'Ryzen 5, 8GB RAM, 256GB SSD', 12500000, 15),
(N'Máy tính Lenovo ThinkCentre M720s', N'Intel Core i5, 8GB RAM, 1TB HDD', 14500000, 10),
(N'Máy tính Acer Veriton X', N'Intel Core i3, 4GB RAM, 500GB HDD', 9500000, 20);

-- Thêm đơn hàng mẫu
INSERT INTO DonHang (UserID, TongTien, TrangThai)
VALUES 
(2, 14000000, N'Đã thanh toán'),
(3, 18500000, N'Đang xử lý');

-- Thêm chi tiết đơn hàng
INSERT INTO ChiTietDonHang (MaDH, MaSP, SoLuong, DonGia)
VALUES 
(1, 1, 1, 13500000),
(1, 5, 1, 500000),
(2, 2, 1, 18500000);

-- Thêm đánh giá
INSERT INTO DanhGia (UserID, MaSP, NoiDung, Diem)
VALUES 
(2, 1, N'Sản phẩm tốt, chạy mượt', 5),
(3, 2, N'Đóng gói cẩn thận, hiệu năng ổn', 4);

-- Truy vấn doanh thu (ví dụ)
-- SELECT 
--     CONVERT(DATE, NgayLap) AS Ngay,
--     SUM(TongTien) AS DoanhThu
-- FROM DonHang
-- WHERE TrangThai = 'Đã thanh toán'
-- GROUP BY CONVERT(DATE, NgayLap);
SELECT name FROM sys.databases;
