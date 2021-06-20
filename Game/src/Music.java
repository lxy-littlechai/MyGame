
import javax.sound.sampled.*;
import java.io.File;

public class Music {

    public static int sound = 0;
    File musicPath = null;
    Clip clip = null;

    public void playMusic(String musicLocation) {
        try {
            //创建相当于音乐播放器的对象
            clip = AudioSystem.getClip();

            //将指定位置的文件转为可播放的文件
            musicPath = new File(musicLocation);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicPath);

            //播放器打开这个可播放的文件
            clip.open(audioInputStream);

            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-sound);

            clip.start();//只播放一次
            clip.loop(Clip.LOOP_CONTINUOUSLY);//让它循环播放
//            while(true){
//
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!clip.isRunning()){
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    public void playBGM(String filename) {// 背景音乐播放
        try {
            File musicPath = new File(filename);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-sound);
                clip.start();
            }
            if (!clip.isRunning()) {
                clip.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void stop(){
        try {
            clip.stop();//直接停止
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}

















