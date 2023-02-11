package client;

import com.sun.awt.AWTUtilities;
import download.multi;
import download.single;
import info.others;
import judgment.test;

import javax.annotation.Resource;
import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static info.print.Print;
import static java.awt.Color.lightGray;

public class client {
    public static String url;
    public static String path;
    private static int status;
    public static String inPath = " Pressdown Path";
    public static String inURL = " Pressdown URL";
    public static String io;
    private static Color back = new Color(35,38,46);
    private int use = 1;
    private int esu;
    private single s = new single();
    private test t = new test();
    private multi m = new multi();
    private others o = new others();
    public void main() {
        ImageIcon title = new ImageIcon("src\\resource\\Title.png");
        ImageIcon download = new ImageIcon("src\\resource\\Download.png");

        Font font = new Font("宋体",Font.BOLD,15);

        JFrame jFrame = new JFrame("Hi,I am a JFrame");
        jFrame.setSize(750,450);
        jFrame.setUndecorated(true);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(back);
        jFrame.setResizable(false);
        AWTUtilities.setWindowShape(jFrame, new RoundRectangle2D.Double(
                0.0D, 0.0D, jFrame.getWidth(), jFrame.getHeight(), 30,
                30));

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(back);

        JTextField JTextFieldURL = new JTextField(inURL);
        JTextFieldURL.setText("down.shudaxia.com/ShuDaXia_PC_OFFICES_Setup_v2.6.1.8.exe");
        JTextFieldURL.setForeground(Color.gray);
        JTextFieldURL.setCaretColor(Color.black);
        JTextFieldURL.setFont(font);
        JTextFieldURL.setBackground(back);
        JTextFieldURL.setSize(300,50);
        JTextFieldURL.setLocation((750 - 300) / 2,(450 - 50) / 2 - 60);

        JTextField JTextFieldPath = new JTextField(inPath);
        JTextFieldPath.setForeground(Color.gray);
        JTextFieldPath.setCaretColor(Color.black);
        JTextFieldPath.setFont(font);
        JTextFieldPath.setBackground(back);
        JTextFieldPath.setSize(300,50);
        JTextFieldPath.setLocation((750 - 300) / 2,(450 - 50) / 2 );

        JButton JButtonDownload = new JButton("Download");
        JButtonDownload.setForeground(Color.WHITE);
        JButtonDownload.setFont(font);
        JButtonDownload.setBackground(back);
        JButtonDownload.setSize(145,50);
        JButtonDownload.setLocation((750 - 300) / 2,(450 - 50) / 2 + 55);

        JButton JButtonSave = new JButton("Save");
        JButtonSave.setForeground(Color.WHITE);
        JButtonSave.setFont(font);
        JButtonSave.setBackground(back);
        JButtonSave.setSize(145,50);
        JButtonSave.setLocation((750 - 300) / 2 + 155,(450 - 50) / 2 + 55);

        JButton JButtonInfo = new JButton("Info");
        JButtonInfo.setForeground(Color.WHITE);
        JButtonInfo.setFont(font);
        JButtonInfo.setBackground(back);
        JButtonInfo.setSize(145,50);
        JButtonInfo.setLocation((750 - 300) / 2,(450 - 50) / 2 + 110);

        JButton JButtonFastSave = new JButton("...");
        JButtonFastSave.setForeground(Color.WHITE);
        JButtonFastSave.setFont(font);
        JButtonFastSave.setBackground(back);
        JButtonFastSave.setSize(145,50);
        JButtonFastSave.setLocation((750 - 300) / 2 + 155,(450 - 50) / 2 + 110);

        JButton JButtonSettings = new JButton("Settings");
        JButtonSettings.setForeground(Color.WHITE);
        JButtonSettings.setFont(font);
        JButtonSettings.setBackground(back);
        JButtonSettings.setSize(300,50);
        JButtonSettings.setLocation((750 - 300) / 2,(450 - 50) / 2 + 165);

        JLabel JLabelTtile = new JLabel(title);
        JLabelTtile.setSize(114,54);
        JLabelTtile.setLocation((750 - 114) / 2,30);



        JButtonSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settings();
            }

        });


        JButtonInfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    o.main();
                } catch (UnknownHostException ex) {
                    ex.printStackTrace();
                }
            }

        });

        JButtonDownload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (use == 0){
                    io = "HTTPS://";
                }else if (use == 1){
                    io = "HTTP://";
                }else if (use == 2){
                    io = "";
                }
                url = io + JTextFieldURL.getText();
                try {
                    t.testURL(url);
                } catch (Exception ex) {
                    status = 404;
                    Print("网址链接错误！");
                    throw new RuntimeException(ex);
                }

                if (url != null){
                    if (status != 404){
                        Print("网址链接成功！");
                        Print("开始下载...");
                        try {
                            if (esu == 0){
                                s.main(url,path);
                            }else if (esu == 1){
                                m.main(url,path);
                            }else if (esu == 3){
                                return;
                            }
                        } catch (MalformedURLException ex) {
                            ex.printStackTrace();
                        }
                    }else {
                        Print("网址链接失败！");
                    }
                }else {
                    Print("网址输入为空！");
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
                        }catch (IOException ee){
                            Print("地址测试失败！");
                        }
                    }else {
                        Print("地址输入错误！");
                    }
                }else {
                    Print("地址输入为空！");

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

        jPanel.add(JTextFieldURL);
        jPanel.add(JTextFieldPath);
        jPanel.add(JButtonSave);
        jPanel.add(JButtonSettings);
        jPanel.add(JButtonFastSave);
        jPanel.add(JButtonDownload);
        jPanel.add(JButtonInfo);
        jPanel.add(JLabelTtile);
        jFrame.add(jPanel);
    }

    public void settings(){
        Font font = new Font("宋体",Font.BOLD,15);
        JFrame settings = new JFrame("settings");
        settings.setSize(350,75);
        settings.setUndecorated(true);
        settings.setVisible(true);
        settings.setLocationRelativeTo(null);
        settings.getContentPane().setBackground(back);
        settings.setResizable(false);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(back);

        JButton JButtonBack = new JButton("Back");
        JButtonBack.setForeground(Color.BLACK);
        JButtonBack.setFont(font);
        JButtonBack.setBackground(back);
        JButtonBack.setSize(145,75);
        JButtonBack.setLocation(205,0);

        JRadioButton c1 = new JRadioButton("单线程",true);
        c1.setLocation(5,0);
        c1.setSize(100,25);
        JRadioButton c2 = new JRadioButton("多线程");
        c2.setLocation(5,25);
        c2.setSize(100,25);
        JRadioButton c3 = new JRadioButton("Null");
        c3.setLocation(5,50);
        c3.setSize(100,25);
        ButtonGroup c4 = new ButtonGroup();
        c4.add(c1);
        c4.add(c2);
        c4.add(c3);

        JRadioButton a1 = new JRadioButton("HTTPS",true);
        a1.setLocation(105,0);
        a1.setSize(100,25);
        JRadioButton a2 = new JRadioButton("HTTP");
        a2.setLocation(105,25);
        a2.setSize(100,25);
        JRadioButton a3 = new JRadioButton("Null");
        a3.setLocation(105,50);
        a3.setSize(100,25);
        ButtonGroup a4 = new ButtonGroup();
        a4.add(a1);
        a4.add(a2);
        a4.add(a3);

        JButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a1.isSelected()){
                    use = 0;
                }else if (a2.isSelected()) {
                    use = 1;
                }else if (a3.isSelected()){
                    use = 2;
                }
                if (c1.isSelected()){
                    esu = 0;
                }else if (c2.isSelected()){
                    esu = 1;
                }else if (c3.isSelected()){
                    esu = 2;
                }
                settings.setVisible(false);
            }

        });

        jPanel.add(a1);
        jPanel.add(a2);
        jPanel.add(a3);
        jPanel.add(c1);
        jPanel.add(c2);
        jPanel.add(c3);
        jPanel.add(JButtonBack);
        settings.add(jPanel);
    }
}
