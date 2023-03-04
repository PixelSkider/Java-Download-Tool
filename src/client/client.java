package client;

import com.sun.awt.AWTUtilities;
import date.date;
import download.multi;
import download.single;
import info.info;
import settings.setting;
import utils.color;
import utils.file;
import utils.image;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.*;

public class client extends JFrame{
    public static String url,path,io;
    private static int status;
    public static String inPath = " Pressdown Path";
    public static String inURL = " Pressdown URL";
    public static Boolean moon = true;
    single single = new single();
    file file = new file();
    multi multi = new multi();
    date date = new date();
    utils.color color = new color();
    utils.image image = new image();

    public client() throws IOException {
        this.setTitle("Hi,I am a JFrame");
        this.setSize(750,450);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(color.getColor(moon));
        this.setResizable(false);
        AWTUtilities.setWindowShape(this, new RoundRectangle2D.Double(
                0.0D, 0.0D, this.getWidth(), this.getHeight(), 30,
                30));
        swing();
    }

    public void swing() throws IOException {
        Font font = new Font("宋体",Font.BOLD,15);
        moon = readAll("Moon");

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(color.getColor(moon));

        JTextField JTextFieldURL = new JTextField(inURL);
        JTextFieldURL.setText(read("URL"));
        JTextFieldURL.setText("down.shudaxia.com/ShuDaXia_PC_OFFICES_Setup_v2.6.1.8.exe");
        JTextFieldURL.setForeground(color.getFontColor(moon));
        JTextFieldURL.setBackground(color.getColor(moon));
        JTextFieldURL.setCaretColor(color.getCaretColor(moon));
        JTextFieldURL.setFont(font);
        JTextFieldURL.setSize(300,50);
        JTextFieldURL.setLocation((750 - 300) / 2,(450 - 50) / 2 - 60);

        JTextField JTextFieldPath = new JTextField(inPath);
        JTextFieldPath.setText(read("Path"));
        JTextFieldPath.setForeground(color.getFontColor(moon));
        JTextFieldPath.setBackground(color.getColor(moon));
        JTextFieldPath.setCaretColor(color.getCaretColor(moon));
        JTextFieldPath.setFont(font);
        JTextFieldPath.setSize(300,50);
        JTextFieldPath.setLocation((750 - 300) / 2,(450 - 50) / 2 );

        JButton JButtonDownload = new JButton("Download");
        JButtonDownload.setForeground(color.getFontColor(moon));
        JButtonDownload.setBackground(color.getColor(moon));
        JButtonDownload.setFont(font);
        JButtonDownload.setSize(145,50);
        JButtonDownload.setLocation((750 - 300) / 2,(450 - 50) / 2 + 55);

        JButton JButtonSave = new JButton("Save");
        JButtonSave.setForeground(color.getFontColor(moon));
        JButtonSave.setBackground(color.getColor(moon));
        JButtonSave.setFont(font);
        JButtonSave.setSize(145,50);
        JButtonSave.setLocation((750 - 300) / 2 + 155,(450 - 50) / 2 + 55);

        JButton JButtonInfo = new JButton("Info");
        JButtonInfo.setForeground(color.getFontColor(moon));
        JButtonInfo.setBackground(color.getColor(moon));
        JButtonInfo.setFont(font);
        JButtonInfo.setSize(145,50);
        JButtonInfo.setLocation((750 - 300) / 2,(450 - 50) / 2 + 110);

        JButton JButtonMoon = new JButton();
        JButtonMoon.setFont(font);
        JButtonMoon.setIcon(image.getIcon(moon));
        JButtonMoon.setBackground(color.getColor(moon));
        JButtonMoon.setSize(50,50);
        JButtonMoon.setLocation(7,7);
        JButtonMoon.setFocusPainted(false);
        JButtonMoon.setBorderPainted(false);

        JButton JButtonFastSave = new JButton("...");
        JButtonFastSave.setForeground(color.getFontColor(moon));
        JButtonFastSave.setBackground(color.getColor(moon));
        JButtonFastSave.setFont(font);
        JButtonFastSave.setSize(145,50);
        JButtonFastSave.setLocation((750 - 300) / 2 + 155,(450 - 50) / 2 + 110);

        JButton JButtonSettings = new JButton("Settings");
        JButtonSettings.setForeground(color.getFontColor(moon));
        JButtonSettings.setBackground(color.getColor(moon));
        JButtonSettings.setFont(font);
        JButtonSettings.setSize(300,50);
        JButtonSettings.setLocation((750 - 300) / 2,(450 - 50) / 2 + 165);

        JLabel JLabelTtile = new JLabel();
        JLabelTtile.setIcon(image.getLogo(moon));
        JLabelTtile.setSize(114,54);
        JLabelTtile.setLocation((750 - 114) / 2,30);

        JButtonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setting setting = new setting();
                try {
                    date.Print("设置...");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });


        JButtonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    info info = new info();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });

        JButtonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (settings.setting.getUse() == 0){
                    io = "HTTPS://";
                }else if (settings.setting.getUse() == 1) {
                    io = "HTTP://";
                }
                url = io + JTextFieldURL.getText();
                try {
                    file.testURL(url);
                } catch (Exception ex) {
                    status = 404;
                    try {
                        date.Print("网址链接错误！");
                        date.Print("网址链接错误！");
                    } catch (IOException exc) {
                        exc.printStackTrace();
                    }
                    throw new RuntimeException(ex);
                }

                if (url != null){
                    if (status != 404){
                        try {
                            date.Print("网址链接成功！");
                            date.Print("开始下载...");
                            if (settings.setting.getEsu() == 0){
                                single.main(url,path);
                                saveconfig(url,path,moon);
                            } else if (settings.setting.getEsu() == 1){
                                multi.main(url,path);
                                saveconfig(url,path,moon);
                            }else if (settings.setting.getEsu() == 3){
                                saveconfig(url,path,moon);
                                return;
                            }
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }else {
                        try {
                            date.Print("网址链接失败！");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else {
                    try {
                        date.Print("网址输入为空！");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

        JButtonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                path = JTextFieldPath.getText();
                File file = new File(path);
                if (path != null){
                    if (path != inPath){
                        try {
                            client.this.file.testPath(path);
                            url = JTextFieldURL.getText();
                            saveconfig(url,path,moon);
                        }catch (IOException ee){

                            ee.printStackTrace();
                            try {
                                date.Print("地址测试失败！");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }else {
                        try {
                            date.Print("地址输入错误！");
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }else {

                    try {
                        date.Print("地址输入为空！");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        JButtonFastSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = 0;
                JFileChooser fileChooser = new JFileChooser();
                FileSystemView fsv = FileSystemView.getFileSystemView();
                fileChooser.setCurrentDirectory(fsv.getHomeDirectory());
                fileChooser.setDialogTitle("请选择要保存地址");
                fileChooser.setApproveButtonText("确定");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                result = fileChooser.showOpenDialog(client.this);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path=fileChooser.getSelectedFile().getPath();
                    JTextFieldPath.setText(path);
                    try {
                        date.Print("选择路径...");
                        date.Print("选择成功");
                        date.Print("Path:" + path);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });

        JButtonMoon.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                moon = !moon;
                color.setMoon(moon);
                JTextFieldURL.setForeground(color.getFontColor(moon));
                JTextFieldURL.setBackground(color.getColor(moon));
                JTextFieldURL.setCaretColor(color.getCaretColor(moon));
                JButtonSettings.setForeground(color.getFontColor(moon));
                JButtonSettings.setBackground(color.getColor(moon));
                JTextFieldPath.setForeground(color.getFontColor(moon));
                JTextFieldPath.setBackground(color.getColor(moon));
                JTextFieldPath.setCaretColor(color.getCaretColor(moon));
                JButtonDownload.setForeground(color.getFontColor(moon));
                JButtonDownload.setBackground(color.getColor(moon));
                JButtonSave.setForeground(color.getFontColor(moon));
                JButtonSave.setBackground(color.getColor(moon));
                JButtonInfo.setForeground(color.getFontColor(moon));
                JButtonInfo.setBackground(color.getColor(moon));
                JButtonMoon.setIcon(image.getIcon(moon));
                JButtonMoon.setBackground(color.getColor(moon));
                JButtonFastSave.setForeground(color.getFontColor(moon));
                JButtonFastSave.setBackground(color.getColor(moon));
                JLabelTtile.setIcon(image.getLogo(moon));
                jPanel.setBackground(color.getColor(moon));
                paint();
            }
        });

        jPanel.add(JTextFieldURL);
        jPanel.add(JTextFieldPath);
        jPanel.add(JButtonSave);
        jPanel.add(JButtonSettings);
        jPanel.add(JButtonFastSave);
        jPanel.add(JButtonMoon);
        jPanel.add(JButtonDownload);
        jPanel.add(JButtonInfo);
        jPanel.add(JLabelTtile);
        this.add(jPanel);
    }


    private void saveconfig(String url,String path,Boolean moon) throws IOException {
        utils.info ii = new utils.info(url,path,moon);
        ii.saveConfig();
    }

    private String read(String text) throws IOException {
        utils.info info = new utils.info();
       return info.read(text);
    }

    private Boolean readAll(String text) throws IOException {
        utils.info info = new utils.info();
        return info.readAll(text);
    }

    private void paint(){
        this.repaint();
    }

}
