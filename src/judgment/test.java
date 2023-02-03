package judgment;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import static info.print.Print;

public class test {
    private static int status;
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
                Print("文件测试成功！");
            }
        }catch (Exception e) {
        }

    }
}
