package utils;

import javax.swing.*;

public class image {
    public ImageIcon getIcon(Boolean moon){
        ImageIcon icon = new ImageIcon();
        if (moon){
            icon = new ImageIcon("src\\resource\\icon\\moon.png");
        }else {
            icon = new ImageIcon("src\\resource\\icon\\Sun.png");
        }
        return icon;
    }

    public ImageIcon getLogo(Boolean moon){
        ImageIcon icon = new ImageIcon();
        if (moon) {
            icon = new ImageIcon("src\\resource\\logo\\black.png");
        }else {
            icon = new ImageIcon("src\\resource\\logo\\white.png");
        }
        return icon;
    }
}
