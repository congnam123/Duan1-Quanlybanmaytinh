/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package maytinh.controller;

import java.awt.Frame;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import maytinh.ui.Manchay;
import maytinh.util.XDialog;

/**
 *
 * @author phuc
 */
public interface dangNhapConTroller {

    void open();

    void login();

    void dangky();

    void init();

    default void exit() {
        if (XDialog.confirm("Bạn muốn kết thúc?")) {
            System.exit(0);
        }
    }

    default void showJDialog(JDialog dialog) {
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
default void showWelcomeJDialog(){
    this.showJDialog(new Manchay(null, true));
}




}
