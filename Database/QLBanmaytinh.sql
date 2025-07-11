USE master;
GO

DROP DATABASE IF EXISTS QLBanmaytinh;
GO


CREATE DATABASE QLBanmaytinh;
GO

USE QLBanmaytinh;
GO

-- BẢNG NGƯỜI DÙNG (USERNAME LÀ KHÓA CHÍNH)
CREATE TABLE Users (
    Username NVARCHAR(20) NOT NULL,
    Password NVARCHAR(50) NOT NULL,
    Enabled BIT NOT NULL,
    Fullname NVARCHAR(50) NOT NULL,
    Photo NVARCHAR(50) NOT NULL,
    Manager BIT NOT NULL,
    PRIMARY KEY (Username)
);

-- BẢNG SẢN PHẨM
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY IDENTITY,
    TenSP NVARCHAR(100),
    MoTa NVARCHAR(255),
    Gia DECIMAL(18, 2),
    SoLuongTon INT,
    NgayThem DATETIME DEFAULT GETDATE()
);

-- BẢNG ĐƠN HÀNG (THAM CHIẾU Username)
CREATE TABLE DonHang (
    MaDH INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(20),
    NgayLap DATETIME DEFAULT GETDATE(),
    TongTien DECIMAL(18,2),
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

-- THÊM NGƯỜI DÙNG
INSERT INTO Users (Username, Password, Enabled, Fullname, Photo, Manager)
VALUES 
('admin', 'admin123', 1, N'Nguyễn Văn A', 'admin.jpg', 1),
('khach1', '123456', 1, N'Lê Thị B', 'b.jpg', 0),
('khach2', '456789', 1, N'Trần Văn C', 'c.jpg', 0);

-- THÊM SẢN PHẨM
INSERT INTO SanPham (TenSP, MoTa, Gia, SoLuongTon)
VALUES 
(N'Máy tính Dell OptiPlex 7090', N'Intel Core i5, 8GB RAM, 256GB SSD', 13500000, 12),
(N'Máy tính HP ProDesk 400 G7', N'Intel Core i7, 16GB RAM, 512GB SSD', 18500000, 8),
(N'Máy tính ASUS ExpertCenter D5', N'Ryzen 5, 8GB RAM, 256GB SSD', 12500000, 15),
(N'Máy tính Lenovo ThinkCentre M720s', N'Intel Core i5, 8GB RAM, 1TB HDD', 14500000, 10),
(N'Máy tính Acer Veriton X', N'Intel Core i3, 4GB RAM, 500GB HDD', 9500000, 20);

-- THÊM ĐƠN HÀNG
INSERT INTO DonHang (Username, TongTien, TrangThai)
VALUES 
('khach1', 14000000, N'Đã thanh toán'),
('khach2', 18500000, N'Đang xử lý');

-- THÊM CHI TIẾT ĐƠN HÀNG
INSERT INTO ChiTietDonHang (MaDH, MaSP, SoLuong, DonGia)
VALUES 
(1, 1, 1, 13500000),
(1, 5, 1, 500000),
(2, 2, 1, 18500000);

-- THÊM ĐÁNH GIÁ
INSERT INTO DanhGia (Username, MaSP, NoiDung, Diem)
VALUES 
('khach1', 1, N'Sản phẩm tốt, chạy mượt', 5),
('khach2', 2, N'Đóng gói cẩn thận, hiệu năng ổn', 4);

select * from Users