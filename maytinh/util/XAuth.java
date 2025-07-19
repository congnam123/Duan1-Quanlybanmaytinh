package maytinh.util;

import maytinh.entity.User;
import maytinh.entity.User1;

/**
 * Lớp tiện ích để quản lý trạng thái đăng nhập của người dùng hiện tại.
 */
public class XAuth {
    // Người dùng hiện tại đã đăng nhập
    public static User1 user = null;

    /**
     * Xóa thông tin người dùng hiện tại (đăng xuất).
     */
    public static void clear() {
        user = null;
    }

    /**
     * Kiểm tra xem có người dùng nào đang đăng nhập không.
     * @return true nếu đã đăng nhập, false nếu chưa
     */
    public static boolean isLogin() {
        return user != null;
    }

    /**
     * Kiểm tra người dùng hiện tại có phải là quản trị viên hay không.
     * @return true nếu là quản trị viên, false nếu không hoặc chưa đăng nhập
     */
    public static boolean isManager() {
        return isLogin() && user.isManager();
    }

    /**
     * Lấy tên đăng nhập của người dùng hiện tại.
     * @return username hoặc null nếu chưa đăng nhập
     */
    public static String getUsername() {
        return isLogin() ? user.getUsername() : null;
    }

    /**
     * Lấy tên đầy đủ của người dùng hiện tại.
     * @return fullname hoặc null nếu chưa đăng nhập
     */
    public static String getFullname() {
        return isLogin() ? user.getFullname() : null;
    }

    /**
     * Lấy email của người dùng hiện tại.
     * @return email hoặc null nếu chưa đăng nhập
     */
    public static String getEmail() {
        return isLogin() ? user.getEmail() : null;
    }

    /**
     * Kiểm tra người dùng có vai trò cụ thể hay không.
     * @param role vai trò cần kiểm tra (ví dụ: "admin", "staff")
     * @return true nếu đúng vai trò, false nếu không hoặc chưa đăng nhập
     */
    public static boolean hasRole(String role) {
        return isLogin() && role.equalsIgnoreCase(user.getRole());
    }

    /**
     * Kiểm tra tài khoản hiện tại có bị khóa không.
     * @return true nếu tài khoản bị khóa, false nếu hoạt động hoặc chưa đăng nhập
     */
    public static boolean isDisabled() {
        return isLogin() && !user.isEnabled();
    }
}