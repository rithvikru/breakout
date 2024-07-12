import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.*;
import java.io.File;

public class MusicPlayer {
    public void playMusic() {
        String musicLocation = "africa-toto.wav";
        try (AudioInputStream stream = AudioSystem.getAudioInputStream(new File("africa-toto.wav"))) {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(e -> {
                if (e.getType() == LineEvent.Type.STOP) {
                    clip.close();
                }
            });
            clip.open(stream);
            clip.start();
            new java.util.concurrent.CountDownLatch(1).await();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

