package utils;

import java.awt.*;

public class color {
    public Boolean moon;

    public void setMoon(Boolean moon){
        this.moon = moon;
    }

    public Color getColor(Boolean moon) {
        Color whitecolor = new Color(220, 220, 220);
        Color blackcolor = new Color(35,38,46);
        Color back = moon ? whitecolor : blackcolor;
        return back;
    }

    public Color getFontColor(Boolean moon){
        Color whitecolor = new Color(255, 255, 255);
        Color blackcolor = new Color(0, 0, 0);
        Color back = moon ? blackcolor : whitecolor;
        return back;

    }

    public Color getCaretColor(Boolean moon){
        Color whitecolor = new Color(255, 255, 255);
        Color blackcolor = new Color(0, 0, 0);
        Color back = moon ? blackcolor : whitecolor;
        return back;
    }
}
