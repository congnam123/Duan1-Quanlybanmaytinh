-- ✅ DATABASE HOÀN CHỈNH CHO HỆ THỐNG CHAT 2 CHIỀU
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
    Email NVARCHAR(100),
    PhanLoai NVARCHAR(50),
    Photo NVARCHAR(50) NOT NULL,
    PhanLoaiTaiKhoan NVARCHAR(50)
);

-- BẢNG LOẠI SẢN PHẨM
CREATE TABLE LoaiSanPham (
    MaLoai INT PRIMARY KEY IDENTITY,
    TenLoai NVARCHAR(50) NOT NULL
);

-- BẢNG SẢN PHẨM
CREATE TABLE SanPham (
    MaSP INT PRIMARY KEY IDENTITY,
    TenSP NVARCHAR(100) NOT NULL,
    MoTa NVARCHAR(255),
    Gia DECIMAL(18, 2) NOT NULL,
    SoLuongTon INT NOT NULL,
    NgayThem DATETIME DEFAULT GETDATE(),
    MaLoai INT,
    FOREIGN KEY (MaLoai) REFERENCES LoaiSanPham(MaLoai)
);

-- BẢNG ĐƠN HÀNG
CREATE TABLE DonHang (
    MaDH INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(20),
    NgayLap DATETIME DEFAULT GETDATE(),
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
    TraLoi NVARCHAR(255),
    FOREIGN KEY (Username) REFERENCES Users(Username),
    FOREIGN KEY (MaSP) REFERENCES SanPham(MaSP)
);

-- ✅ BẢNG LIÊN HỆ (HỖ TRỢ CHAT 2 CHIỀU)
CREATE TABLE LienHe (
    ID INT PRIMARY KEY IDENTITY,
    Username NVARCHAR(20),
    NoiDung NVARCHAR(MAX),
    ThoiGian DATETIME DEFAULT GETDATE(),
    AdminReply NVARCHAR(MAX) NULL,
    AdminReplyTime DATETIME NULL,
    Status NVARCHAR(20) DEFAULT 'pending' NOT NULL,
    FOREIGN KEY (Username) REFERENCES Users(Username),
    CONSTRAINT CK_LienHe_Status CHECK (Status IN ('pending', 'replied', 'closed'))
);

-- Tạo index để tăng hiệu suất
CREATE INDEX IX_LienHe_Status ON LienHe(Status);
CREATE INDEX IX_LienHe_Username ON LienHe(Username);
CREATE INDEX IX_LienHe_ThoiGian ON LienHe(ThoiGian DESC);

-- ===============================================
-- DỮ LIỆU MẪU
-- ===============================================

-- DỮ LIỆU NGƯỜI DÙNG
INSERT INTO Users (Username, Password, Enabled, Manager, Fullname, NgaySinh, DiaChi, SoDienThoai, Email, PhanLoai, Photo, PhanLoaiTaiKhoan)
VALUES
('admin', 'admin123', 1, 1, N'Nguyễn Văn A', '1995-05-20', N'123 Lê Lợi, TP.HCM', '0912345678', 'admin@gmail.com', N'Thường', 'admin.jpg', N'Admin'),
('user1', '123456', 1, 0, N'Trần Thị B', '1998-08-15', N'45 Nguyễn Trãi, Hà Nội', '0909123456', 'user1@gmail.com', N'Vip', 'user1.jpg', N'Khách hàng'),
('user2', '654321', 1, 0, N'Lê Văn C', '2000-12-01', N'99 Pasteur, Đà Nẵng', '0987654321', 'user2@gmail.com', N'Thường', 'user2.jpg', N'Khách hàng'),
('khach1', 'abc123', 1, 0, N'Nguyễn Khách 1', '1990-01-01', N'Hà Nội', '0900000001', 'khach1@gmail.com', N'Vip', 'khach1.jpg', N'Khách hàng'),
('khach2', 'xyz789', 1, 0, N'Lê Khách 2', '1992-02-02', N'Sài Gòn', '0900000002', 'khach2@gmail.com', N'Vip', 'khach2.jpg', N'Khách hàng');

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
(3, 3, 1, 14000000),
(4, 4, 1, 18500000);

-- DỮ LIỆU ĐÁNH GIÁ
INSERT INTO DanhGia (Username, MaSP, NoiDung, Diem)
VALUES
('khach1', 1, N'Sản phẩm tốt, chạy mượt', 5),
('khach2', 2, N'Đóng gói cẩn thận, hiệu năng ổn', 4),
('user1', 1, N'Ổn nhưng hơi nóng máy', 3),
('user2', 2, N'Sản phẩm chưa đúng mô tả', 2);

-- ✅ DỮ LIỆU LIÊN HỆ (DEMO CHAT 2 CHIỀU)
INSERT INTO LienHe (Username, NoiDung, ThoiGian, AdminReply, AdminReplyTime, Status)
VALUES
-- Liên hệ đã được trả lời
('khach1', N'Cho mình hỏi về chế độ bảo hành của máy tính Dell OptiPlex 7090?', '2025-07-28 09:30:00',
 N'Chào bạn! Máy tính Dell OptiPlex 7090 có chế độ bảo hành 12 tháng chính hãng. Bảo hành bao gồm lỗi phần cứng và phần mềm. Bạn có thể mang máy đến các trung tâm bảo hành Dell hoặc liên hệ hotline để được hỗ trợ.',
 '2025-07-28 10:15:00', 'replied'),

-- Liên hệ chưa được trả lời
('user1', N'Mình muốn hỏi về việc nâng cấp RAM cho máy tính. Hiện tại máy có 8GB, có thể nâng lên 16GB được không?', '2025-07-29 14:20:00', NULL, NULL, 'pending'),

('khach2', N'Sản phẩm HP ProDesk 400 G7 có hỗ trợ card đồ họa rời không? Mình cần dùng cho thiết kế.', '2025-07-29 16:45:00', NULL, NULL, 'pending'),

-- Liên hệ đã đóng
('user2', N'Mình đặt hàng từ hôm qua nhưng chưa nhận được xác nhận. Cho mình hỏi tình trạng đơn hàng.', '2025-07-27 11:00:00',
 N'Chào bạn! Đơn hàng của bạn đã được xác nhận và đang trong quá trình chuẩn bị hàng. Dự kiến giao hàng trong 2-3 ngày làm việc. Cảm ơn bạn đã tin tưởng sản phẩm của chúng tôi.',
 '2025-07-27 11:30:00', 'closed'),

-- Thêm một số liên hệ khác
('khach1', N'Có chương trình khuyến mãi nào cho khách hàng VIP không?', '2025-07-30 08:00:00', NULL, NULL, 'pending'),

('user1', N'Cảm ơn shop đã tư vấn nhiệt tình. Sản phẩm rất tốt!', '2025-07-29 20:30:00',
 N'Cảm ơn bạn đã phản hồi tích cực! Chúng tôi rất vui khi sản phẩm làm bạn hài lòng. Chúc bạn sử dụng máy hiệu quả!',
 '2025-07-30 09:00:00', 'replied');

-- ===============================================
-- KIỂM TRA DỮ LIỆU
-- ===============================================

PRINT '=== KIỂM TRA DỮ LIỆU ===';

SELECT 'Users' AS TableName, COUNT(*) AS RecordCount FROM Users
UNION ALL
SELECT 'LoaiSanPham', COUNT(*) FROM LoaiSanPham
UNION ALL
SELECT 'SanPham', COUNT(*) FROM SanPham
UNION ALL
SELECT 'DonHang', COUNT(*) FROM DonHang
UNION ALL
SELECT 'ChiTietDonHang', COUNT(*) FROM ChiTietDonHang
UNION ALL
SELECT 'DanhGia', COUNT(*) FROM DanhGia
UNION ALL
SELECT 'LienHe', COUNT(*) FROM LienHe;

PRINT '=== CHI TIẾT BẢNG LIÊN HỆ ===';
SELECT
    ID,
    Username,
    LEFT(NoiDung, 50) + '...' AS NoiDung_Short,
    ThoiGian,
    CASE
        WHEN AdminReply IS NOT NULL THEN LEFT(AdminReply, 30) + '...'
        ELSE NULL
    END AS AdminReply_Short,
    AdminReplyTime,
    Status
FROM LienHe
ORDER BY ThoiGian DESC;

PRINT '=== THỐNG KÊ LIÊN HỆ THEO TRẠNG THÁI ===';
SELECT
    Status,
    COUNT(*) AS SoLuong,
    CAST(COUNT(*) * 100.0 / (SELECT COUNT(*) FROM LienHe) AS DECIMAL(5,2)) AS PhanTram
FROM LienHe
GROUP BY Status
ORDER BY SoLuong DESC;

PRINT '=== DATABASE ĐÃ SẴN SÀNG CHO HỆ THỐNG CHAT 2 CHIỀU! ===';
PRINT 'Bạn có thể đăng nhập với:';
PRINT '- Admin: username=admin, password=admin123';
PRINT '- User: username=user1, password=123456';
PRINT '- Khách: username=khach1, password=abc123';
