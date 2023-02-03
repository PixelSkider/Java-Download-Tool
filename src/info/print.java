package info;

import java.util.Calendar;

public class print {
    public static void Print(String out){
        Calendar calendar = Calendar.getInstance();
        int H = calendar.get(Calendar.HOUR_OF_DAY);
        int M = calendar.get(Calendar.MINUTE);
        int S = calendar.get(Calendar.SECOND);
        System.out.println("["
                + H + ":"
                + M + ":"
                + S + "]" + " "
                + out);
    }

    public static void Print(int out){
        Calendar calendar = Calendar.getInstance();
        int H = calendar.get(Calendar.HOUR_OF_DAY);
        int M = calendar.get(Calendar.MINUTE);
        int S = calendar.get(Calendar.SECOND);
        System.out.println("["
                + H + ":"
                + M + ":"
                + S + "]" + " "
                + out);
    }
}
