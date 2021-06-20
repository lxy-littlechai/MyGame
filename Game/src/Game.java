import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class Game implements MouseMotionListener, Runnable {
    public static final int game_x = 150;
    public static final int game_y = 10;
    public static final int game_w = 1010;
    public static final int game_h = 650;
    public static final int game_box_w = 990;//实际边框
    public static final int game_box_h = 610;

    //初始化各种控件
    Paddle paddle = new Paddle();
    ArrayList<Brick> bricks = set_Brick();
    ArrayList<Event> events = new ArrayList<>();
    Ball ball = new Ball();
    Life life = new Life();
    JFrame JF = new JFrame();
    Score score = new Score();
    showComponents components = new showComponents(ball, bricks, paddle, events, life, score);
    Music music = new Music();//播放小声音
    Music music2 = new Music();//专门播放大的BGM
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {}
    });
    static int INF = 1000000000;

    public Game(){
        Score.score = 0;
        life.restore();
        ball.Restore();
        paddle.setStartPosition();
        JF.addMouseMotionListener(this);
        JF.setLocation(game_x, game_y);
        JF.setSize(game_w, game_h);
        JF.setVisible(true);
        JF.add(components);
        JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        music2.playMusic("Game\\music\\BGM.wav");

    }

    public void run() {
        System.out.println("游戏开始");
        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread.start();
        while(true){
            //小球移动&与墙碰撞

            ball.move_Bounce();
            if(ball.IsOut()){//判断是否出界
                events.clear();
                ball.Restore();
                paddle.setStartPosition();
                life.decline();
                music.playBGM("Game\\music\\loseLife.wav");
                try {//掉生命值后要sleep防止生命值全部清零，我也不知道为啥wrong
                    thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //后期改为pause
                if(life.life1 == 1){
                    final boolean[] on = {false};
                    JF.addMouseListener(new MouseListener() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            on[0] = true;
                        }

                        @Override
                        public void mousePressed(MouseEvent e) {

                        }

                        @Override
                        public void mouseReleased(MouseEvent e) {

                        }

                        @Override
                        public void mouseEntered(MouseEvent e) {

                        }

                        @Override
                        public void mouseExited(MouseEvent e) {

                        }
                    });

                    while(on[0] == false){
                        try {
                            thread.sleep(0);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                }

            }

            //GameOver
            if(life.life1 == 0) {
                music2.stop();
                music.playBGM("Game\\music\\dead.wav");
                JF.dispose();
                Lose lose = new Lose();

                try {
                    thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lose.JF.dispose();
                return; //生命值为0
            }

            //球碰板
            if(ball.collide(paddle.getX(),paddle.getY(), Paddle.paddle_width, Paddle.paddle_height)){
                music.playBGM("Game\\music\\Paddle.wav");
                ball.Bounce_Y();
                int mid = (2*paddle.getX() + paddle.paddle_width)/2;
                ball.speed_x = (ball.getX() - mid)/10;

                //System.out.println(ball.speed_x);
                ball.kind = "normal";
            }

            int BrickExist = 0;//是否有存活的砖块
            //判断碰撞
            for(Brick brick: bricks){
                if(brick.isLive() == false) continue;

                if(ball.collide(brick.getX(), brick.getY(), Brick.Brick_w, Brick.Brick_h)){
                    music.playBGM("Game\\music\\Brick.wav");

                    //normal ball
                    if(ball.kind.equals("normal")){
                        brick.live --;
                        ball.Bounce_brick(brick.getX(), brick.getY(), Brick.Brick_w, Brick.Brick_h);
                    }
                    //through ball
                    if(ball.kind.equals("through")){
                        brick.live = 0;
                    }

                    //explode ball
                    if(ball.kind.equals("explode")){
                        for(Brick tmp: bricks){
                            if(Math.abs(brick.getX() - tmp.getX()) + Math.abs(brick.getY() - tmp.getY()) <= 100){
                                tmp.live = 0;
                            }
                        }
                    }

                    //brick消失
                    if(brick.isLive() == false){
                        score.add();
                        if(Event.Event_build() == true){
                            Event event = new Event(brick.getX(), brick.getY());
                            events.add(event);
                        }
                    }
                }
            }

            //判断是否还有砖
            for(Brick brick: bricks){
                if(brick.isLive() == false) continue;
                BrickExist = 1;
            }

            if(BrickExist == 0){
                music2.stop();
                music.playBGM("Game\\music\\victory.wav");
                JF.dispose();
                Victory victory = new Victory();

                try {
                    thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                victory.JF.dispose();
                return; //生命值为0
            }

            //图标移动
            for (Event event: events){
                if(event.Isout()) continue;
                event.Move();
            }

            //图标与板碰撞
            for (Event event: events){
                if(event.Isout()) continue;
                if(paddle.Collide(event.x, event.y, event.w, event.h)){
                    music.playBGM("Game\\music\\Event.wav");

                    event.y = Game.game_box_h + 1000;//out

                    if(event.kind.equals("paddlex2")){
                        paddle.paddle_width = paddle.paddle_width*2;
                    }

                    if(event.kind.equals("paddle_2")){
                        paddle.paddle_width = paddle.paddle_width/2;
                    }

                    if(event.kind.equals("dead")){
                        life.decline();
                    }

                    if(event.kind.equals("through")){
                        ball.kind = "through";
                    }

                    if(event.kind.equals("rock")){
                        ball.speed_x += 2;
                        ball.speed_y -= 2;
                    }

                    if(event.kind.equals("explode")){
                        ball.kind = "explode";
                    }

                    if(event.kind.equals("ball_shrink")){
                        ball.radius -= 2;
                        if(ball.radius <= 1) ball.radius = 1;
                    }

                    if(event.kind.equals("ball_over")){
                        ball.radius += 2;


                    }
                }
            }

            try {
                thread.sleep(9);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            components.repaint();
        }





    }

    private static ArrayList<Brick> set_Brick(){//初始化砖块
        ArrayList<Brick> bricks = new ArrayList<>();
        Random random = new Random();
        Random rand_live = new Random();
        Random rand_color = new Random();

        for(int i = 0; i < game_w;i += Brick.Brick_w){
            for(int j = 40;j < game_h-10*Brick.Brick_h;j += Brick.Brick_h){
                if(random.nextInt(101) <= -1){//30%概率生成砖块
                    int color = rand_color.nextInt(5);
                    Brick brick = new Brick(i, j, rand_live.nextInt(5));
                    if(color == 0) brick.setColor("blue");
                    if(color == 1) brick.setColor("cyan");
                    if(color == 2) brick.setColor("purple");
                    if(color == 3) brick.setColor("pink");
                    if(color == 4) brick.setColor("yellow");
                    bricks.add(brick);

                }
            }
        }
        return bricks;
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        paddle.Move(e.getX());
    }

}
