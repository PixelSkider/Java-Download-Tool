package info;



import client.client;
import utils.color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class info extends JFrame {
    private utils.info info = new utils.info();
    utils.color color = new color();
    Boolean moon;
    Color backcColor = color.getColor(client.moon);
    Color fontColor = color.getFontColor(client.moon);
    public info() throws IOException {
        this.setTitle("info");
        this.setSize(400,100);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        swing();
    }

    public void swing() throws IOException {
        Font font = new Font("宋体", Font.BOLD, 15);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(backcColor);

        JLabel JLabelIP = new JLabel("");
        JLabelIP.setSize(220, 30);
        JLabelIP.setLocation(27, 5);
        JLabelIP.setBackground(backcColor);
        JLabelIP.setForeground(fontColor);
        JLabelIP.setText("IP:" + info.getIp());
        JLabelIP.setFont(font);

        JLabel JLabelLink = new JLabel("BiliBili:b23.tv/glxK3XY");
        JLabelLink.setSize(220, 30);
        JLabelLink.setBackground(backcColor);
        JLabelLink.setForeground(fontColor);
        JLabelLink.setLocation(27, 35);
        JLabelLink.setFont(font);

        JLabel JLabelLink1 = new JLabel("Discord:MinLoL#8680");
        JLabelLink1.setSize(220, 30);
        JLabelLink1.setBackground(backcColor);
        JLabelLink1.setForeground(fontColor);
        JLabelLink1.setLocation(27, 65);
        JLabelLink1.setFont(font);

        JButton JButtonBack = new JButton("Back");
        JButtonBack.setFont(font);
        JButtonBack.setBackground(backcColor);
        JButtonBack.setForeground(fontColor);
        JButtonBack.setSize(145, 75);
        JButtonBack.setLocation(247, 10);

        jPanel.add(JLabelIP);
        jPanel.add(JLabelLink);
        jPanel.add(JLabelLink1);
        jPanel.add(JButtonBack);
        this.add(jPanel);

        JButtonBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

}
