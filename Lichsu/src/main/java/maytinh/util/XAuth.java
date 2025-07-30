package maytinh.util;

import maytinh.entity.User;

/**
 * Lớp XAuth dùng để lưu thông tin người dùng đã đăng nhập
 */
public class XAuth {

    // Biến lưu người dùng hiện tại, sẽ được gán sau khi đăng nhập thành công
    public static User user = null;

    /**
     * Kiểm tra đã đăng nhập chưa
     */
    public static boolean isLogin() {
        return user != null;
    }

    /**
     * Đăng xuất
     */
    public static void logoff() {
        user = null;
    }
}
