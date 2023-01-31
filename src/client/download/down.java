package client.download;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import static client.info.info.Print;

public class down {
    public static String filename;
    public static String ext;
    public static int downo;
    public static int length;

    public static void Dio(String downloadurl,String path) throws MalformedURLException {
        int bytesum = 0;
        int byteread = 0;
        File urlFile = new File(downloadurl);
        filename = urlFile.getName();
        ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
        URL url = new URL(downloadurl);
        File file = new File(path,filename );

        try {
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            OutputStream out = new FileOutputStream(file);
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public String filename(){
        return filename;
    }

    public int filelength(){
        return length;
    }

    public String ext() {
        return ext;
    }

}
