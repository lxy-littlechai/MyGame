import javax.swing.*;
import java.awt.*;

public class Victory extends JComponent{
    JFrame JF = new JFrame();



    public Victory(){
        JF.add(this);
        JF.setLocation(Game.game_x, Game.game_y);
        JF.setSize(Game.game_w, Game.game_h);
        JF.setVisible(true);
    }

    public void draw(Graphics g){
        Image img = new ImageIcon("Game\\images\\Victory.jpg").getImage();
        g.drawImage(img, 0, 0, Game.game_w, Game.game_h, null);
    }

    protected void paintComponent(Graphics g){
        this.draw(g);
    }
}
