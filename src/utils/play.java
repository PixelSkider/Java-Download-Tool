package utils;


import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;


public class play {
    public void playclick() throws IOException {
        File file = new File("src\\resource\\sound\\click.wav");
        AudioClip audioClip = Applet.newAudioClip(file.toURI().toURL());
        audioClip.play();

    }
}
