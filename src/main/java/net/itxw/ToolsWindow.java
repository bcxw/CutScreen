package net.itxw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @Author: houyong
 * @Date: 2019/9/20
 */
/*
 * 操作窗口
 */
class ToolsWindow extends JWindow
{
    private ScreenShotWindow parent;

    public ToolsWindow(ScreenShotWindow parent,int x,int y) {
        this.parent=parent;
        this.init();
        this.setLocation(x, y);
        this.pack();
        this.setVisible(true);
    }

    private void init(){

        this.setLayout(new BorderLayout());
        JToolBar toolBar=new JToolBar("Java 截图");

        //确定按钮 保存图片到剪贴板
        JButton ocrButton=new JButton("识别");
        ocrButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.ocrImage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(ocrButton);

        //确定按钮 保存图片到剪贴板
        JButton confirmButton=new JButton("确定");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.saveImage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(confirmButton);

        //保存按钮
        JButton saveButton=new JButton("保存");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    parent.saveImage();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        toolBar.add(saveButton);

        //关闭按钮
        JButton closeButton=new JButton("取消");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                parent.dispose();
            }
        });
        toolBar.add(closeButton);

        this.add(toolBar,BorderLayout.NORTH);
    }




}