
import javax.swing.*;
import java.awt.*;

public class Brick extends JPanel{
    public static final int Brick_w = 50, Brick_h = 20;
    private int Brick_x, Brick_y;
    public int live;
    public String color;
    public Brick(int x, int y, int live){
        this.Brick_x = x;
        this.Brick_y = y;
        this.live = live;
    }
    public Boolean isLive(){
        return this.live > 0;
    }
    public int getX(){
        return this.Brick_x;
    }
    public int getY(){
        return this.Brick_y;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        if(this.isLive()){

            Image img = null;
            String filename = "Game\\images\\block_" + color + ".png";
            img = new ImageIcon(filename).getImage();
            g2.drawImage(img, Brick_x, Brick_y, Brick_w, Brick_h, null);
        }
    }
}
