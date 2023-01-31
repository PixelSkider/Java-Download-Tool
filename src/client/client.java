package client;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static client.download.down.Dio;
import static client.info.info.Print;
import static java.awt.Color.black;
import static java.awt.Color.lightGray;


public class client {
    public static String url;
    public static String path;
    public static boolean downboolean;
    public static String inPath = "输入下载地址！";
    public static int status;
    public static String io;


    public static void main(String[] args) {
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
                    io = null;
                }
                url = io + JTextFieldURL.getText();
                try {
                    testURL(url);
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
                            Dio(url,path);
                            closetest(path);
                        } catch (MalformedURLException ex) {
                            JLabelPrint.setText("网络链接错误！");
                            try {
                                closetest(path);
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
                            ex.printStackTrace();
                        } catch (IOException ex) {
                            try {
                                closetest(path);
                            } catch (IOException exc) {
                                exc.printStackTrace();
                            }
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
                Print(url);
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
                            testPath(path);
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
                    Print("路径:" + path);
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

    private static void testURL(String url)throws Exception{
        try {
            URL test = new URL(url);
            HttpURLConnection oc = (HttpURLConnection)test.openConnection();
            oc.setAllowUserInteraction(false);
            oc.setConnectTimeout(3000);
            status = oc.getResponseCode();
        }catch (Exception e) {
            status = 404;

            e.printStackTrace();
            throw e;
        }
    }

    private static void testPath(String path)throws IOException{
        try {
            File file = new File(path,"test");
            if (!file.exists()){
                file.createNewFile();
            }else {
                Print("文件测试成功！");
            }
        }catch (Exception e) {
            throw new IOException();
        }

    }

    private static void closetest(String path)throws IOException {
        File file = new File(path,"test");
        try {
            if (!file.exists()){
                file.delete();
            }else {
                return;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}
