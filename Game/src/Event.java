import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Event {
    int x, y;
    final int w = 40, h = 40;//图标大小
    final int speed = 1;
    String kind;

    public Event(int x, int y){
        this.x = x;
        this.y = y;
        this.kind = Event_kind();
    }
    //在(x,y)随机掉落
    static public Boolean Event_build(){
        Random random = new Random();
        if(random.nextInt(101) <= 40){
            return true;
        }
        else return false;
    }
    //掉落物品的种类
    public String Event_kind(){
        Random random = new Random();
        int num = random.nextInt(8);
        switch (num){
            case 0: return "through";
            case 1: return "rock";
            case 2: return "explode";
            case 3: return "paddlex2";
            case 4: return "paddle_2";
            case 5: return "ball_shrink";
            case 6: return "ball_over";
            case 7: return "dead";
            default: return "normal";
        }

    }
    public void Move(){
        y += speed;
    }
    public boolean Isout(){
        if(y > Game.game_box_h){
            kind = "normal";
            return true;
        }
        else return false;
    }
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        if(!Isout()){
            Image img = null;
            String filename = "Game\\images\\" + kind + ".png";
            img = new ImageIcon(filename).getImage();
            g.drawImage(img, x, y, w, h, null);
        }

    }
}
