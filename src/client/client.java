package client;

import download.multi;
import download.single;
import judgment.test;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import static info.print.Print;
import static java.awt.Color.lightGray;

public class client {
    public static String url;
    public static String path;
    private static int status;
    public static boolean downboolean;
    public static String inPath = "输入下载地址！";
    public static String io;
    private single s = new single();
    private test t = new test();
    private multi m = new multi();
    public void main() {
        JFrame jFrame = new JFrame("Hi,I am a JFrame");
        jFrame.setSize(550,400);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(lightGray);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);

        JTextField JTextFieldURL = new JTextField("输入下载网址！");
        JTextFieldURL.setText("down.shudaxia.com/ShuDaXia_PC_OFFICES_Setup_v2.6.1.8.exe");
        JTextFieldURL.setForeground(Color.black);
        JTextFieldURL.setCaretColor(Color.BLACK);
        JTextFieldURL.setSize(200,50);

        JTextField JTextFieldPath = new JTextField("输入下载地址！");
        JTextFieldPath.setForeground(Color.black);
        JTextFieldPath.setCaretColor(Color.BLACK);
        JTextFieldPath.setSize(200,50);
        JTextFieldPath.setLocation(0,50);

        JButton JButtonDownload = new JButton("Download");
        JButtonDownload.setSize(100,50);
        JButtonDownload.setLocation(200,0);
        JButtonDownload.setBackground(lightGray);
        JButtonDownload.setBorderPainted(false);

        JButton JButtonSave = new JButton("Save");
        JButtonSave.setSize(100,50);
        JButtonSave.setLocation(200,50);
        JButtonSave.setBackground(lightGray);
        JButtonSave.setBorderPainted(false);

        JButton JButtonFastSave = new JButton("...");
        JButtonFastSave.setSize(25,25);
        JButtonFastSave.setLocation(300,75);
        JButtonFastSave.setBackground(Color.white);
        JButtonFastSave.setBorderPainted(false);

        JLabel JLabelPrint = new JLabel();
        JLabelPrint.setSize(300,30);
        JLabelPrint.setLocation(203,100);

        JRadioButton c1 = new JRadioButton("单线程",true);
        c1.setLocation(0,100);
        c1.setSize(100,25);
        JRadioButton c2 = new JRadioButton("多线程");
        c2.setLocation(0,125);
        c2.setSize(100,25);
        JRadioButton c3 = new JRadioButton("Null");
        c3.setLocation(0,150);
        c3.setSize(100,25);
        ButtonGroup c4 = new ButtonGroup();
        c4.add(c1);
        c4.add(c2);
        c4.add(c3);

        JRadioButton a1 = new JRadioButton("HTTPS",true);
        a1.setLocation(100,100);
        a1.setSize(100,25);
        JRadioButton a2 = new JRadioButton("HTTP");
        a2.setLocation(100,125);
        a2.setSize(100,25);
        JRadioButton a3 = new JRadioButton("Null");
        a3.setLocation(100,150);
        a3.setSize(100,25);
        ButtonGroup a4 = new ButtonGroup();
        a4.add(a1);
        a4.add(a2);
        a4.add(a3);


        JButtonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a1.isSelected()) {
                    io = "https://";
                }else if (a2.isSelected()){
                    io = "http://";
                }else if (a3.isSelected()) {
                    io = "";
                }
                url = io + JTextFieldURL.getText();
                try {
                    t.testURL(url);
                } catch (Exception ex) {
                    status = 404;
                    Print("网址链接错误！");
                    JLabelPrint.setText("网址链接错误!");
                    throw new RuntimeException(ex);
                }

                if (url != null){
                    if (status != 404){
                        Print("网址链接成功！");
                        JLabelPrint.setText("网址链接成功！");
                        Print("开始下载...");
                        JLabelPrint.setText("开始下载...");
                        try {
                            if (c1.isSelected()){
                                s.main(url,path);
                                JLabelPrint.setText("下载完成！");
                                JLabelPrint.setText("用时" + s.getBeginTime() + "秒");
                            }else if (c2.isSelected()){
                                m.main(url,path);
                                JLabelPrint.setText("下载完成");
                                JLabelPrint.setText("用时" + m.getBeginTime() + "秒");
                            }else if (c3.isSelected()){
                                JLabelPrint.setText("操你妈你爱下不下不下滚");
                            }
                        } catch (MalformedURLException ex) {
                            JLabelPrint.setText("网络链接错误！");
                            ex.printStackTrace();
                        }
                    }else {
                        Print("网址链接失败！");
                        JLabelPrint.setText("网址链接失败！");
                    }
                }else {
                    Print("网址输入为空！");
                    JLabelPrint.setText("网址输入为空！");
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
                            t.testPath(path);
                            Print("地址测试成功！");
                            JLabelPrint.setText("地址测试成功!");
                        }catch (IOException ee){
                            Print("地址测试失败！");
                            JLabelPrint.setText("地址测试失败!");
                        }
                    }else {
                        Print("地址输入错误！");
                        JLabelPrint.setText("地址输入错误！");
                    }
                }else {
                    Print("地址输入为空！");
                    JLabelPrint.setText("地址输入为空！");

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
                result = fileChooser.showOpenDialog(jFrame);
                if (JFileChooser.APPROVE_OPTION == result) {
                    path=fileChooser.getSelectedFile().getPath();
                    JTextFieldPath.setText(path);
                }
            }
        });

        jPanel.add(a1);
        jPanel.add(a2);
        jPanel.add(a3);
        jPanel.add(c1);
        jPanel.add(c2);
        jPanel.add(c3);
        jPanel.add(JLabelPrint);
        jPanel.add(JTextFieldURL);
        jPanel.add(JButtonDownload);
        jPanel.add(JTextFieldPath);
        jPanel.add(JButtonSave);
        jPanel.add(JButtonFastSave);
        jFrame.add(jPanel);
    }


}
