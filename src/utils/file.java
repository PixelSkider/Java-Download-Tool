package utils;

import date.date;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;


public class file {
    private static int status;
    private date date =new date();
    public void testURL(String url)throws Exception{
        try {
            URL test = new URL(url);
            HttpURLConnection oc = (HttpURLConnection)test.openConnection();
            oc.setAllowUserInteraction(false);
            oc.setConnectTimeout(3000);
            status = oc.getResponseCode();
        }catch (Exception e) {
            status = 404;

            e.printStackTrace();
            throw e;
        }

    }

    public void testPath(String path)throws IOException {
        try {
            File file = new File(path,"test");
            if (!file.exists()){
                file.createNewFile();
            }else {
                date.Print("文件测试成功！");
            }

        }catch (Exception e) {
            date.Print("文件测试失败!");
            e.printStackTrace();
        }

    }

    public void testFile(String path){
        try {
            File file = new File(path);
            if (!file.exists()){
                file.createNewFile();
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
