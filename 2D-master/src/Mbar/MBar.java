package Mbar;
import main.S1;
import main.S2;
import main.Selections;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MBar extends JPanel {
    private JFrame jFrame;
    private Thread thread;
    Selections selections;

    public MBar(){
       jFrame = new JFrame("Menu Bar");
       init();

    }
    public void init(){
        jFrame.setSize(200,320);
        jFrame.setLocationRelativeTo(null);
        jFrame.setBackground(new Color(197, 91, 91));
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        //HOME BUTTON
        JButton HomeButton = createHomebutton(100,275);
        HomeButton.addActionListener(e -> {
            S1 s1 = new S1();
            s1.startGamethread();
            jFrame.dispose();
        });
        // END BUTTON
        JButton endbutton = endButton(200, 275);
        endbutton.addActionListener(e -> {
            S2 s2 = new S2();
            s2.startGamethread();
            jFrame.dispose();
        });
        jFrame.add(HomeButton);
    }
    private JButton createHomebutton(int x, int y) {
        JButton homebutton = new JButton();
        homebutton.setBounds(x, y, 200,400);
        homebutton.setBorderPainted(false);
        homebutton.setContentAreaFilled(false);
        homebutton.setFocusPainted(false);
        homebutton.setOpaque(false);
        homebutton.setBorderPainted(false);
        homebutton.setVisible(true);

       URL imgURL = getClass().getResource("/Images/cpixil-frame-0 (4).png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(100,40 , Image.SCALE_SMOOTH);
            homebutton.setIcon(new ImageIcon(scaled));
        }
        return homebutton;
    }
    private JButton endButton(int x, int y){
        JButton end = new JButton();
        end.setBounds(x,y, 100,40);
        end.setBorderPainted(false);
        end.setContentAreaFilled(false);
        end.setFocusPainted(false);
        end.setOpaque(false);
        end.setBackground(new Color(0,0,0));

        return end;
    }
}
