package download;



import date.date;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

public class single {
    private String filename;
    private String fileext;
    private int length;
    private long beginTime;
    private date date = new date();
    public void main(String downloadurl, String path) throws IOException {
        File urlFile = new File(downloadurl);
        this.filename = urlFile.getName();
        this.fileext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        URL fileurl = new URL(downloadurl);
        File saveFile = new File(path,filename);
        try {
            date.Print("文件大小:" + length / 1024 / 1024 + "MB");
            long begin_time = new Date().getTime();
            HttpURLConnection conn = (HttpURLConnection)fileurl.openConnection();
            OutputStream out = new FileOutputStream(saveFile);
            InputStream in = conn.getInputStream();
            conn.setConnectTimeout(3000);
            urlFile.getParentFile().mkdirs();
            length = conn.getContentLength();
            byte[] buf = new byte[1024];

            int len;
            int count = 0;
            while ((len = in.read(buf)) != - 1){
                out.write(buf,0,len);
                out.flush();
                ++count;
            }

            out.close();
            in.close();
            conn.disconnect();
            long end_time = new Date().getTime();
            long seconds = (end_time - begin_time) / 1000;
            beginTime = seconds % 60;
            date.Print("下载完成！");
            date.Print("用时" + beginTime + "秒");
        } catch (FileNotFoundException e) {
            beginTime = (System.currentTimeMillis() - beginTime / 1000);
            date.Print("用时" + beginTime + "秒");
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String getFilename(){
        return filename;
    }

    public int getLength(){
        return length;
    }

    public String getFileext() {
        return fileext;
    }

    public long getBeginTime(){
        return beginTime;
    }
}
