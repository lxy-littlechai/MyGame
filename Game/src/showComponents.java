import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
public class showComponents extends JComponent{
    ArrayList<Brick> bricks;
    Paddle paddle;
    Ball ball;
    ArrayList<Event> events;

    Background background = new Background();
    Life life;
    Score score;

    public showComponents(Ball ball, ArrayList<Brick> bricks, Paddle paddle, ArrayList<Event> events, Life life, Score score){
        this.ball = ball;
        this.life = life;
        this.paddle = paddle;
        this.bricks = bricks;
        this.events = events;
        this.score = score;
    }

    protected void paintComponent(Graphics g){
        background.draw(g);
        ball.draw(g);
        life.draw(g);
        paddle.draw(g);
        score.draw(g);
        for(Brick brick: bricks){
            brick.draw(g);
        }
        for(Event event: events){
            if(event.Isout()) continue;
            event.draw(g);
        }
    }


}