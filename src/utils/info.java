package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import date.date;

import java.io.*;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class info {
    public String url;
    public String path;
    public Boolean moon;
    private date date = new date();
    public info(String a,String b,Boolean c) throws MalformedURLException {
        this.url = a;
        this.path = b;
        this.moon = c;
    }

    public info(){

    }
    public void saveConfig() throws IOException {
        JsonObject jsonObject = new JsonObject();
        Path path = Paths.get("src\\resource\\config.json");
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
        String haw = null;
        try {
            Path path = Paths.get("src\\resource\\config.json");
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
            date.Print(text + " 读取失败！");
            date.Print(text + ":" + haw);
            e.printStackTrace();
            return null;
        }
        return haw;
    }

    public Boolean readAll(String text) throws IOException {
        Boolean haw = null;
        try {
            Path path = Paths.get("src\\resource\\config.json");
            JsonObject jsonObject = new Gson().fromJson(new String(Files.readAllBytes(path), StandardCharsets.UTF_8), JsonObject.class);
            if (jsonObject.has("Client")) {
                JsonObject modJsonObject = jsonObject.get("Client").getAsJsonObject();
                if (modJsonObject.has(text)) {
                    haw = modJsonObject.get(text).getAsBoolean();
                    date.Print(text + " 读取成功！");
                    date.Print(text + ":" + haw);
                    return haw;
                }
            }
        } catch (IOException e) {
            date.Print(text + " 读取失败！");
            date.Print(text + ":" + haw);
            e.printStackTrace();
            return null;
        }
        return haw;
    }

    public String getIp() throws IOException {
        return InetAddress.getLocalHost().getHostAddress();
    }
}
