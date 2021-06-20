

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class Paddle extends JComponent  {

    //设置板子的坐标
    private static int x = 0;
    private static int y = 0;

    //板子大小
    //不应该是常量，后期可以通过道具加长或缩短
    public static int paddle_width = 80;
    public static int paddle_height = 10;

    //设置板子距离底部的距离
    //也可通过道具变化
    public  static int paddle_button = 20;

    //板子移动速度
    //也可通过道具变化
    public static int paddle_speed = 0;

    public Paddle(){
        setStartPosition();
    }
    //设置paddle的初始位置
    public void setStartPosition(){
        paddle_width = 80;
        x=(Game.game_box_w-paddle_width)/2;
        y=Game.game_box_h-paddle_button;
    }

    //画出板子
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        Image img = new ImageIcon("Game\\images\\paddle.png").getImage();
        g.drawImage(img,x,y,paddle_width,paddle_height, null);
    }

    public void Move(int x){
         if(x+paddle_width > Game.game_box_w) x = Game.game_box_w-paddle_width;
         this.x = x;
    }

    public Boolean Collide(int x, int y, int w, int h){
        if(x+w >= this.x && x <= this.x + paddle_width && y <= this.y+paddle_height && y+h >= this.y)
            return true;
        return false;
    }

    public int getX(){
         return this.x;
    }
    public int getY(){
        return this.y;
    }
}
