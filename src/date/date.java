package date;

import java.io.*;
import java.util.Calendar;

public class date {

    public void Print(String out) throws IOException {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int D = calendar.get(Calendar.MONTH);
        int G = calendar.get(Calendar.DAY_OF_YEAR);
        int M = calendar.get(Calendar.MINUTE);
        int S = calendar.get(Calendar.SECOND);
        String Time = "[" + Y + ":" + D +":" + G + ":" + M + ":" + S + "] ";
        System.out.println(Time + out);
        savedate(out);
    }

    public void savedate(String text) throws IOException {
        Calendar calendar = Calendar.getInstance();
        int Y = calendar.get(Calendar.YEAR);
        int D = calendar.get(Calendar.MONTH);
        int G = calendar.get(Calendar.DAY_OF_YEAR);
        int M = calendar.get(Calendar.MINUTE);
        int S = calendar.get(Calendar.SECOND);
        String Time = "[" + Y + ":" + D +":" + G + ":" + M + ":" + S + "] ";
        String Out = Time + text + "\n";
        String date = "src\\resource\\date.txt";
        File f = new File(date);
        FileWriter fw = new FileWriter(f,true);
        fw.write(Out);
        fw.close();
    }
}
