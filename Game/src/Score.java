import javax.swing.*;
import java.awt.*;

public class Score {

    static int score = 0;
    String rank = "得分";

    public Score(){

    }

    public void add(){
        score = score + 1;
    }

    public void draw(Graphics g){
        Font font = new Font("楷体",Font.PLAIN, 25);
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(font);
        g2.setColor(Color.PINK);
        g2.drawString(rank, 0, 20);
        g2.drawString(String.valueOf(score), 60, 20);
    }
}
