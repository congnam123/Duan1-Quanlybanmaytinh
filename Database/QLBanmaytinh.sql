-- XÓA VÀ TẠO LẠI DATABASE
USE master;
DROP DATABASE IF EXISTS QLBanmaytinh;
GO
CREATE DATABASE QLBanmaytinh;
GO
USE QLBanmaytinh;
GO

-- BẢNG NGƯỜI DÙNG
CREATE TABLE Users (
    Username NVARCHAR(20) NOT NULL PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT NOT NULL,
    Manager BIT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    NgaySinh DATE,
    DiaChi NVARCHAR(100),
    SoDienThoai NVARCHAR(15),
    PhanLoai NVARCHAR(50),
    Photo NVARCHAR(50) NOT NULL
);

-- BẢNG LOẠI SẢN PHẨM
CREATE TABLE LoaiSanPham (
    MaLoai INT PRIMARY KEY IDENTITY,
    TenLoai NVARCHAR(50) NOT NULL
);

-- BẢNG SẢN PHẨM
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY IDENTITY,
    TenSP NVARCHAR(100),
    MoTa NVARCHAR(255),
    Gia DECIMAL(18, 2),
    SoLuongTon INT,
    NgayThem DATETIME DEFAULT GETDATE(),
    MaLoai INT,
    FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai)
);

-- BẢNG ĐƠN HÀNG
CREATE TABLE DonHang (
    MaDH INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(20),
    NgayLap DATETIME,
    TongTien DECIMAL(18,2),
    PhuongThucThanhToan NVARCHAR(50),
    TrangThai NVARCHAR(50),
    FOREIGN KEY (Username) REFERENCES Users(Username)
);

-- BẢNG CHI TIẾT ĐƠN HÀNG
CREATE TABLE ChiTietDonHang (
    MaCTDH INT PRIMARY KEY IDENTITY,
    MaDH INT,
    MaSP INT,
    SoLuong INT,
    DonGia DECIMAL(18,2),
    FOREIGN KEY (MaDH) REFERENCES DonHang(MaDH),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- BẢNG ĐÁNH GIÁ
CREATE TABLE DanhGia (
    MaDG INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(20),
    MaSP INT,
    NoiDung NVARCHAR(255),
    Diem INT CHECK (Diem BETWEEN 1 AND 5),
    NgayDanhGia DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (Username) REFERENCES Users(Username),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- DỮ LIỆU NGƯỜI DÙNG
INSERT INTO Users (Username, Password, Enabled, Manager, Fullname, NgaySinh, DiaChi, SoDienThoai ,PhanLoai, Photo)
VALUES 
('admin', 'admin123', 1, 1, N'Nguyễn Văn A', '1995-05-20', N'123 Lê Lợi, TP.HCM', '0912345678',N'Thường', 'admin.jpg'),
('user1', '123456', 1, 0, N'Trần Thị B', '1998-08-15', N'45 Nguyễn Trãi, Hà Nội', '0909123456',N'Vip', 'user1.jpg'),
('user2', '654321', 1, 0, N'Lê Văn C', '2000-12-01', N'99 Pasteur, Đà Nẵng', '0987654321',N'Thường', 'user2.jpg'),
('khach1', 'abc123', 1, 0, N'Nguyễn Khách 1', '1990-01-01', N'Hà Nội', '0900000001',N'Vip', 'khach1.jpg'),
('khach2', 'xyz789', 1, 0, N'Lê Khách 2', '1992-02-02', N'Sài Gòn', '0900000002',N'Vip', 'khach2.jpg');

-- DỮ LIỆU LOẠI SẢN PHẨM
INSERT INTO LoaiSanPham (TenLoai)
VALUES 
(N'Văn phòng'),
(N'Gaming'),
(N'Đồ họa'),
(N'Giá rẻ');

-- DỮ LIỆU SẢN PHẨM
INSERT INTO SanPham (TenSP, MoTa, Gia, SoLuongTon, MaLoai)
VALUES 
(N'Máy tính Dell OptiPlex 7090', N'Intel Core i5, 8GB RAM, 256GB SSD', 13500000, 12, 1),
(N'Máy tính HP ProDesk 400 G7', N'Intel Core i7, 16GB RAM, 512GB SSD', 18500000, 8, 2),
(N'Máy tính ASUS ExpertCenter D5', N'Ryzen 5, 8GB RAM, 256GB SSD', 12500000, 15, 1),
(N'Máy tính Lenovo ThinkCentre M720s', N'Intel Core i5, 8GB RAM, 1TB HDD', 14500000, 10, 3),
(N'Máy tính Acer Veriton X', N'Intel Core i3, 4GB RAM, 500GB HDD', 9500000, 20, 4);

-- DỮ LIỆU ĐƠN HÀNG
INSERT INTO DonHang (Username, NgayLap, TongTien, PhuongThucThanhToan, TrangThai)
VALUES 
('khach1', '2025-07-19', 14000000, N'Tiền mặt', N'Đã thanh toán'),
('khach2', '2025-07-20', 18500000, N'Chuyển khoản', N'Đang xử lý'),
('user1', '2025-07-19', 14000000, N'Tiền mặt', N'Đã thanh toán'),
('user2', '2025-07-20', 18500000, N'Chuyển khoản', N'Đang xử lý');

-- DỮ LIỆU CHI TIẾT ĐƠN HÀNG
INSERT INTO ChiTietDonHang (MaDH, MaSP, SoLuong, DonGia)
VALUES 
(1, 1, 1, 13500000),
(1, 5, 1, 500000),
(2, 2, 1, 18500000),
(1, 5, 1, 500000),
(2, 2, 1, 18500000);

-- DỮ LIỆU ĐÁNH GIÁ
INSERT INTO DanhGia (Username, MaSP, NoiDung, Diem)
VALUES 
('khach1', 1, N'Sản phẩm tốt, chạy mượt', 5),
('khach2', 2, N'Đóng gói cẩn thận, hiệu năng ổn', 4),
('user1', 1, N'Sản phẩm tốt, chạy mượt', 3),
('user2', 2, N'Đóng gói cẩn thận, hiệu năng ổn', 2);

-- KIỂM TRA DỮ LIỆU
SELECT * FROM Users;
SELECT * FROM LoaiSanPham;
SELECT * FROM SanPham;
SELECT * FROM DonHang;
SELECT * FROM ChiTietDonHang;
SELECT * FROM DanhGia;

-- DOANH THU NGÀY TEST
SELECT * FROM DonHang
WHERE NgayLap BETWEEN '2025-07-19' AND '2025-07-20';
