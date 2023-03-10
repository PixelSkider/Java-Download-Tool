package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import date.date;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class files {
    private static int status;
    public String url = null;
    public String path = null;
    public Boolean moon = null;
    private date date = new date();
    public files(String a, String b, Boolean c) throws MalformedURLException {
        this.url = a;
        this.path = b;
        this.moon = c;
    }

    public files(){

    }
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

    public void createFile() throws IOException {
        File json = new File(getRunPath() + "\\config\\config.json");
        File date = new File(getRunPath() + "\\resource\\date.txt");
        File config = new File(getRunPath() + "\\config\\");
        File resource = new File(getRunPath() + "\\resource\\");
        if (!config.exists()){
            config.mkdir();
        }
        if (!resource.exists()){
            resource.mkdir();
        }
        if (!json.exists()){
            json.createNewFile();
        }
        if (!date.exists()) {
            date.createNewFile();
        }
    }

    public void createjson() throws IOException {
        JsonObject jsonObject = new JsonObject();
        Path path = Paths.get(getRunPath() + "\\config\\config.json");
        JsonObject modJsonObject = new JsonObject();
        modJsonObject.addProperty("Path",(String)null);
        modJsonObject.addProperty("URL",(String) null);
        modJsonObject.addProperty("Moon",(Boolean)null);
        jsonObject.add("Client", modJsonObject);
        Files.write(path,new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject).getBytes(StandardCharsets.UTF_8));
    }

    public void saveConfig() throws IOException {
        JsonObject jsonObject = new JsonObject();
        Path path = Paths.get(getRunPath() + "\\config\\config.json");
        JsonObject modJsonObject = new JsonObject();
        modJsonObject.addProperty("Path",this.path);
        modJsonObject.addProperty("URL",this.url);
        modJsonObject.addProperty("Moon",this.moon);
        jsonObject.add("Client", modJsonObject);
        try {
            date.Print("保存成功！");
            date.Print("Path:" + this.path);
            date.Print("URL:" + this.url);
            date.Print("Moon:" + this.moon);
            Files.write(path,new GsonBuilder().setPrettyPrinting().create().toJson(jsonObject).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            date.Print("保存失败！");
            date.Print("请稍后重试！");
            e.printStackTrace();
        }
    }

    public String read(String text) throws IOException {
        createFile();
        String haw = null;
        try {
            Path path = Paths.get( getRunPath() + "\\config\\config.json");
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(path), StandardCharsets.UTF_8), JsonObject.class);
            if (jsonObject.has("Client")) {
                JsonObject modJsonObject = jsonObject.get("Client").getAsJsonObject();
                if (modJsonObject.has(text)) {
                    haw = modJsonObject.get(text).getAsString();
                    date.Print(text + " 读取成功！");
                    date.Print(text + ":" + haw);
                    return haw;
                }
            }
        } catch (IOException e) {
            createjson();
            date.Print(text + " 读取失败！");
            date.Print(text + ":" + haw);
            e.printStackTrace();
            return null;
        }
        return haw;
    }

    public Boolean readAll(String text) throws IOException {
        createFile();
        Boolean haw = false;
        try {
            Path path = Paths.get(getRunPath() + "\\config\\config.json");
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(path), StandardCharsets.UTF_8), JsonObject.class);
            if (jsonObject.has("Client")) {
                JsonObject modJsonObject = jsonObject.get("Client").getAsJsonObject();
                if (modJsonObject.has(text)) {
                    haw = modJsonObject.get(text).getAsBoolean();
                    if (Boolean.TRUE.equals(haw = null)){
                        date.Print(text + " 读取成功！");
                        date.Print(text + ":" + "false");
                        return false;
                    }else {
                        date.Print(text + " 读取成功！");
                        date.Print(text + ":" + haw);
                        return haw;
                    }
                }
            }
        } catch (IOException e) {
            createjson();
            date.Print(text + " 读取失败！");
            date.Print(text + ":" + haw);
            e.printStackTrace();
            return false;
        }
        return haw;
    }

    public String getIp() throws IOException {
        return InetAddress.getLocalHost().getHostAddress();
    }

    public String getRunPath() {
        return System.getProperty("user.dir");
    }
}
