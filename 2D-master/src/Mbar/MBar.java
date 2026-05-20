package Mbar;

import javax.swing.*;
import java.awt.*;

public class MBar extends JPanel {
    private JFrame jFrame;
    private Thread thread;

    public MBar(){
       jFrame = new JFrame("Menu Bar");
       init();
    }
    public void init(){
        jFrame.setSize(200,400);
        jFrame.setLocation(50,50);
        JButton button = new JButton();
        button.setSize(200,200);
        button.setLocation(50,50);
        button.setBackground(new Color(191, 55, 55));
        jFrame.add(button);



    }



}
