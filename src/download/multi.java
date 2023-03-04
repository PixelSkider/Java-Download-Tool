package download;


import date.date;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


public class multi {
    private String filename;
    private String fileext;
    private int length;
    private int BlockSize;
    private int BlockNum;
    private long beginTime;
    private long begin_time;
    private date date = new date();

    public void main(String downloadurl,String path) throws IOException {
        try {
            begin_time = new Date().getTime();
            URL fileurl = new URL(downloadurl);
            File urlFile = new File(downloadurl);
            URLConnection conn = fileurl.openConnection();
            this.filename = urlFile.getName();
            this.length = conn.getContentLength();
            this.BlockSize = length / 1024 / 1024 / 5;
            this.BlockNum = 5;
            this.fileext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            date.Print("文件大小:" + length / 1024 / 1024 + "MB");
            date.Print("线程总数:" + BlockNum);
            Thread[] threads = new Thread[BlockNum];
            for (int i = 0; i < BlockNum; i++) {
                final int index = i;
                final int finalBlockNum = BlockNum;
                final String finalFileName = filename;
                threads[i] = new Thread() {
                    public void run() {
                        try {
                            URLConnection conn = fileurl.openConnection();
                            InputStream in = conn.getInputStream();
                            int beginPoint = 0, endPoint = 0;
                            beginPoint = index * BlockSize;
                            if (index < finalBlockNum - 1) {
                                endPoint = beginPoint + BlockSize;
                            } else {
                                endPoint = length;
                            }
                            File savefile = new File(path, finalFileName + (index + 1));
                            FileOutputStream fos = new FileOutputStream(savefile);
                            in.skip(beginPoint);
                            byte[] buffer = new byte[1024];
                            int count;
                            int process = beginPoint;
                            while (process < endPoint) {
                                count = in.read(buffer);
                                if (process + count >= endPoint) {
                                    count = endPoint - process;
                                    process = endPoint;
                                } else {
                                    process += count;
                                }
                                fos.write(buffer, 0, count);
                            }
                            date.Print("线程" + index + "下载完成");
                            fos.close();
                            in.close();
                        } catch (Exception e) {
                            long end_time = new Date().getTime();
                            long seconds = (end_time - begin_time) / 1000;
                            beginTime = seconds % 60;
                            beginTime = (System.currentTimeMillis() - beginTime / 1000);
                            try {
                                date.Print("下载失败");
                                date.Print("用时" + beginTime + "秒");
                            } catch (IOException ex) {
                                ex.printStackTrace();
                            }
                            e.printStackTrace();
                        }
                    }

                };
                threads[i].start();
            }
            for (Thread t : threads) {
                t.join();
            }
            File savefile = new File(path,filename);
            FileOutputStream fos = new FileOutputStream(savefile);
            for (int i = 0; i < BlockNum; i++) {
                File file = new File(path,filename + (i + 1));
                FileInputStream fis = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int count;
                while ((count = fis.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                file.delete();
                fis.close();
            }
            long end_time = new Date().getTime();
            long seconds = (end_time - begin_time) / 1000;
            beginTime = seconds % 60;
            date.Print("下载完成！");
            date.Print("用时" + beginTime + "秒");
            fos.close();
        } catch (FileNotFoundException e) {
            long end_time = new Date().getTime();
            long seconds = (end_time - begin_time) / 1000;
            beginTime = seconds % 60;
            beginTime = (System.currentTimeMillis() - beginTime / 1000);
            date.Print("出现错误下载中止！");
            date.Print("用时" + beginTime + "秒");
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
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
