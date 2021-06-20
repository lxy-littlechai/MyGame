import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    //定义两个按钮
    private JButton Game_Start;
    private JButton Game_Over;
    public Menu(){

    }

    public static void main(String[] args) {
        while(true){
            StartMenu startMenu = new StartMenu();
            Game game = new Game();
            game.run();
            game.thread.interrupt();
        }

    }
}