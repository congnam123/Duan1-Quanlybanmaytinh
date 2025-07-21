/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maytinh.controller;

import javax.swing.JFrame;
import maytinh.ui.Manchay;
import maytinh.util.XDialog;

/**
 *
 * @author Cong Nam
 */
public interface DangKyController {
      void open();
    void register();
        default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
        }
    }

}
