package maytinh.controller;

import maytinh.entity.User;

/**
 * Interface controller cho màn hình Thông tin cá nhân
 * @author phuc
 */
public interface ThongtincanhanController {
    
    /**
     * Load thông tin người dùng hiện tại vào form
     */
    void loadUserInfo();
    
    /**
     * Cập nhật thông tin người dùng
     */
    void updateUserInfo();
    
    /**
     * Thoát khỏi màn hình
     */
    void exit();
    
    /**
     * Kiểm tra và validate dữ liệu trước khi cập nhật
     */
    boolean validateData();
} 