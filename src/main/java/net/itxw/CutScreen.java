package net.itxw;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.IntellitypeListener;
import com.melloware.jintellitype.JIntellitype;

/**
 * @Author: houyong 另一中实现 参考而已
 * @Date: 2019/9/19
 */
public class CutScreen implements HotkeyListener, IntellitypeListener {

    private final int HOTKET=111;

    public static void main(String[] args) {
        new CutScreen().setTray();

    }
    public CutScreen() {

        //添加监听
        if (JIntellitype.isJIntellitypeSupported()) {
            //注册热键快捷键
            JIntellitype.getInstance().registerHotKey(HOTKET, JIntellitype.MOD_CONTROL+JIntellitype.MOD_ALT, 'X');
            //启动监听
            JIntellitype.getInstance().addHotKeyListener(this);
            JIntellitype.getInstance().addIntellitypeListener(this);
        }
    }


    /**
     * 快捷键组合键按键事件
     * @param i
     */
    @Override
    public void onHotKey(int i) {
        System.out.println(i);
        //如果是我指定的快捷键就执行指定的操作
        if(i==HOTKET){
            startCut();
        }
    }

    @Override
    public void onIntellitype(int i) {
        System.out.println(i);
    }

    public void startCut() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ScreenShotWindow cutScreenWin=new ScreenShotWindow();
                    cutScreenWin.setVisible(true);
                    cutScreenWin.toFront();
                } catch (AWTException e) {
                    e.printStackTrace();
                } finally {
                }
            }
        });
    }

    //添加托盘显示：1.先判断当前平台是否支持托盘显示
    public void setTray() {

        if(SystemTray.isSupported()){//判断当前平台是否支持托盘功能
            //创建托盘实例
            SystemTray tray = SystemTray.getSystemTray();
            //创建托盘图标：1.显示图标Image 2.停留提示text 3.弹出菜单popupMenu 4.创建托盘图标实例
            //1.创建Image图像
            URL fileURL = CutScreen.class.getResource("image/itxw.jpg");
            Image image = Toolkit.getDefaultToolkit().getImage(fileURL);
            //2.停留提示text
            String text = "IT学问网截图";
            //3.弹出菜单popupMenu
            PopupMenu popMenu = new PopupMenu();

            MenuItem itmOpen = new MenuItem("截图");
            itmOpen.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    startCut();
                }
            });

            MenuItem itmExit = new MenuItem("退出");
            itmExit.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    System.exit(0);
                }
            });
            popMenu.add(itmOpen);
            popMenu.add(itmExit);

            //创建托盘图标
            TrayIcon trayIcon = new TrayIcon(image,text,popMenu);
            //将托盘图标加到托盘上
            try {
                tray.add(trayIcon);
            } catch (AWTException e1) {
                e1.printStackTrace();
            }
        }
    }


}


