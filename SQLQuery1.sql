-- Tạo CSDL
CREATE DATABASE login_app;
GO

USE login_app;
GO

-- Bảng người dùng
CREATE TABLE Users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    fullName NVARCHAR(100) NOT NULL,
    email NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(100) NOT NULL
);
GO

-- Thêm tài khoản mẫu
INSERT INTO Users (fullName, email, password)
VALUES
    (N'Phúc Admin', 'admin@gmail.com', '123456'),
    (N'Người Dùng Mẫu', 'user@gmail.com', 'abcdef');
GO
