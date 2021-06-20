import javax.swing.*;
import java.awt.*;

public class Ball {
    public int radius;
    private int x, y;
    public int speed_x, speed_y;
    public String kind;
    public Ball(){
        Restore();
    }

    //碰撞
    public Boolean collide(int x, int y, int w, int h){//传入brick参数
        if(this.x + 2*radius > x && this.x < x+w &&
           this.y + 2*radius > y && this.y < y+h){
            return true;
        }
        return false;
    }
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Image img = new ImageIcon("Game\\images\\ball.png").getImage();
        g.drawImage(img, this.x, this.y, 2*radius, 2*radius, null);
    }
    public void kind_change(String kind){//改变小球种类
        this.kind = kind;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void Restore(){//设置小球初始位置
        radius = 5;
        x=(Game.game_box_w-Paddle.paddle_width)/2 + Paddle.paddle_width/2-radius;
        y=Game.game_box_h-Paddle.paddle_button-2*radius;
        this.speed_x = 2;
        this.speed_y = -2;
        this.kind = "normal";
    }
    public Boolean move_Bounce(){//移动和反弹
        x += speed_x;
        y += speed_y;
        if(x < 0){
            x = 0;
            Bounce_X();
        }
        if(y < 0) {
            y = 0;
            Bounce_Y();
        }
        if(x+2*radius > Game.game_box_w){
            x = Game.game_box_w-2*radius;
            Bounce_X();
        }
        return true;
    }
    public void Bounce_brick(int x, int y, int w, int h){
        if(this.y + radius >= y && this.y + radius <= y+h){//侧撞
            Bounce_X();
        }
        if(this.x + radius >= x && this.x + radius <= x+w){//上下撞
            Bounce_Y();
        }
    }
    public void Bounce_X(){
        speed_x = -speed_x;
    }
    public void Bounce_Y(){
        speed_y = -speed_y;
    }
    public Boolean IsOut(){
        if(y+2*radius > Game.game_box_h) return true;
        return false;
    }
}
