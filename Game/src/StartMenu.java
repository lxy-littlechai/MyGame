import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class StartMenu extends JComponent implements MouseListener {
    JFrame JF = new JFrame();
    public static Boolean on = true;
    public StartMenu(){
        on = true;
        JF.add(this);
        JF.addMouseListener(this);
        JF.setLocation(Game.game_x, Game.game_y);
        JF.setSize(Game.game_w, Game.game_h);
        JF.setVisible(true);

        while (true){
            try {
                new Thread().sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(!on) break;
        }
    }

    public void draw(Graphics g){
        Image img = new ImageIcon("Game\\images\\Start.png").getImage();
        g.drawImage(img, 0, 0, Game.game_w, Game.game_h, null);
    }

    protected void paintComponent(Graphics g){
        this.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        on = false;
        JF.dispose();

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
}