package settings;

import client.client;
import utils.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class setting extends JFrame {
    color color = new color();
    static int use = 0,esu = 0;

    public setting() {
        this.setTitle("settings");
        this.setSize(400,100);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        swing();
    }

    public void swing(){
        Color backcColor = color.getColor(client.moon);
        Color fontColor = color.getFontColor(client.moon);

        Font font = new Font("宋体",Font.BOLD,15);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(backcColor);

        JButton JButtonBack = new JButton("Back");
        JButtonBack.setForeground(fontColor);
        JButtonBack.setBackground(backcColor);
        JButtonBack.setFont(font);
        JButtonBack.setSize(180,80);
        JButtonBack.setLocation(220,10);

        JRadioButton c1 = new JRadioButton("单线程",true);
        c1.setLocation(5,0);
        c1.setSize(100,50);
        c1.setBackground(backcColor);
        c1.setFont(font);
        c1.setForeground(fontColor);
        JRadioButton c2 = new JRadioButton("多线程");
        c2.setLocation(5,50);
        c2.setSize(100,50);
        c2.setFont(font);
        c2.setBackground(backcColor);
        c2.setForeground(fontColor);
        ButtonGroup c4 = new ButtonGroup();
        c4.add(c1);
        c4.add(c2);

        JRadioButton a1 = new JRadioButton("HTTPS",true);
        a1.setLocation(105,0);
        a1.setSize(100,50);
        a1.setBackground(backcColor);
        a1.setFont(font);
        a1.setForeground(fontColor);
        JRadioButton a2 = new JRadioButton("HTTP");
        a2.setLocation(105,50);
        a2.setSize(100,50);
        a2.setBackground(backcColor);
        a2.setForeground(fontColor);
        a2.setFont(font);
        ButtonGroup a4 = new ButtonGroup();
        a4.add(a1);
        a4.add(a2);
        JButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (a1.isSelected()){
                   use = 0;
                }else if (a2.isSelected()) {
                    use = 1;
                }
                if (c1.isSelected()){
                    esu = 0;
                }else if (c2.isSelected()){
                    esu = 1;
                }
                setVisible(false);
            }

        });

        jPanel.add(a1);
        jPanel.add(a2);
        jPanel.add(c1);
        jPanel.add(c2);
        jPanel.add(JButtonBack);
        this.add(jPanel);
    }

    public static int getUse(){
        return use;
    }

    public static int getEsu() {
        return esu;
    }
}
