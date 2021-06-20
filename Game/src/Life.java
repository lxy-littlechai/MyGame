import javax.swing.*;
import java.awt.*;

public class Life {
    public static int life1 = 1;
    public static int life2 = 1;
    public static int life3 = 1;//三点生命值

    public void decline(){
        if(life3 == 1) {
            life3 = 0;

        }
        else {
            if(life2 == 1) life2 = 0;
            else if(life1 == 1) life1 = 0;
        }
    }

    public void restore(){
        life1 = 1;
        life2 = 1;
        life3 = 1;
    }

    public void draw(Graphics g){
        Image img = new ImageIcon("Game\\images\\live.png").getImage();
        if(life1 != 0) g.drawImage(img, Game.game_box_w-25, 0,20, 20, null);
        if(life2 != 0) g.drawImage(img, Game.game_box_w-55, 0,20, 20, null);
        if(life3 != 0) g.drawImage(img, Game.game_box_w-85, 0,20, 20, null);
    }
}
