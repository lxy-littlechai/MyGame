import javax.swing.*;
import java.awt.*;

public class Background  extends JComponent{
    public void draw(Graphics g){
        Image img = new ImageIcon("Game\\images\\background.jpg").getImage();
        g.drawImage(img, 0, 0, Game.game_w, Game.game_h, null);
    }
    protected void paintComponent(Graphics g){
        this.draw(g);
    }

}
