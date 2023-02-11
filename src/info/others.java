package info;

import com.sun.awt.AWTUtilities;

import javax.swing.*;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static java.awt.Color.lightGray;

public class others {
    private long runtime;
    public void main() throws UnknownHostException {
        JFrame jFrame = new JFrame("test");
        jFrame.setSize(200,200);
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.getContentPane().setBackground(lightGray);
        jFrame.setResizable(false);
        AWTUtilities.setWindowShape(jFrame,new RoundRectangle2D.Double(0.0D,0.0D,600,400,26.0D,26.0D));


        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBackground(Color.white);

        JLabel JLabelIP = new JLabel("");
        JLabelIP.setSize(280,50);
        JLabelIP.setLocation(40,10);
        JLabelIP.setText("IP:" +getip());


        jPanel.add(JLabelIP);
        jFrame.add(jPanel);
    }

    private String getip() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }

}
