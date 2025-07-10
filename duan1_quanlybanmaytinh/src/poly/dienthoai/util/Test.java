/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package poly.dienthoai.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import poly.dienthoai.entity.Category;
/**
 *
 * @author Admin
 */
public class Test  {
   public static void main(String[] args) throws SQLException {
        //1. Thêm mới 2 bản ghi Categories
      String sql = "INSERT INTO Categories (Id, Name) VALUES(?, ?)";
        XJdbc.executeUpdate(sql, "C01", "Loai 1");
        XJdbc.executeUpdate(sql, "C02", "Loai 2");

        //2. Truy vấn nhiều bản ghi
       String sql1 = "SELECT * FROM Categories WHERE Name LIKE ?";
        
        ResultSet rs = XJdbc.executeQuery(sql, "%Loai%");
        while (rs.next()) {
            String id = rs.getString("Id");
           String name = rs.getString("Name");
           System.out.println("ID: " + id + ", Name: " + name);
        }
        //3. Truy xuất nhiều bản ghi và chuyển đổi sang List<Bean>
       String sql2 = "SELECT * FROM Categories WHERE Name LIKE ?";
        List<Category> beans = XQuery.getBeanList(Category.class, sql, "%Loai%");
        for (Category bean : beans) {
            System.out.println(bean.toString());
        }
        //4. Truy xuất một bản ghi và chuyển đổi sang Bean
        String sql3 = "SELECT * FROM Categories WHERE Id=?";
        Category bean = XQuery.getSingleBean(Category.class, sql, "C02");
        System.out.println(bean.toString());
        //5. Truy vấn 1 giá trị
        String sql4 = "SELECT max(Id) FROM Categories WHERE Name LIKE ?";
        String id = XJdbc.getValue(sql, "%Loai%");
        System.out.println("max(Id)= "+ id);
    }
}
