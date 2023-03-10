package utils;

import javax.swing.*;

public class image {
    public ImageIcon getIcon(Boolean moon){
        ImageIcon icon = new ImageIcon();
        if (moon){
            icon = new ImageIcon( getRunPath() + "\\resource\\icon\\moon.png");
        }else {
            icon = new ImageIcon( getRunPath() + "\\resource\\icon\\Sun.png");
        }
        return icon;
    }

    public ImageIcon getLogo(Boolean moon){
        ImageIcon icon = new ImageIcon();
        if (moon) {
            icon = new ImageIcon( getRunPath()+ "\\resource\\logo\\black.png");
        }else {
            icon = new ImageIcon(getRunPath() +  "\\resource\\logo\\white.png");
        }
        return icon;
    }

    public ImageIcon getBottom(Boolean moon){
        ImageIcon icon = new ImageIcon();
        if (moon) {
            icon = new ImageIcon( getRunPath()+ "\\resource\\bottom\\black.png");
        }else {
            icon = new ImageIcon(getRunPath() +  "\\resource\\bottom\\white.png");
        }
        return icon;
    }



    public String getRunPath() {
        return System.getProperty("user.dir");
    }
}
