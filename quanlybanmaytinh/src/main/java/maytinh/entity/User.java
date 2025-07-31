/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package maytinh.entity;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    private String username;
    private String password;
    private boolean enabled;
    private String fullname;
    private String email; 
    @Builder.Default
    private String photo = "photo.png";
    private boolean manager;
    private Date ngaySinh;
    private String diaChi;
    private String soDienThoai;
    private String phanLoai;
    private String PhanLoaiTaiKhoan;
}

