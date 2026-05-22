package Mbar;
import main.S1;
import main.S2;
import main.Selections;
import main.TitleScreen;

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
        jFrame.setSize(200, 320);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);

        // This sets the layout of the panel itself
        this.setLayout(null);

        // HOME BUTTON (S1) - Kept your coordinates (10, 100)
        JButton HomeButton = createHomebutton(40, 130);
        HomeButton.addActionListener(e -> {
            TitleScreen titleScreen = new TitleScreen();
            jFrame.dispose();
        });

        JButton endbutton = endButton(40, 200);
        endbutton.addActionListener(e -> {
            S2 s2 = new S2();
            s2.startGamethread();
            jFrame.dispose();
        });

        // Add the buttons to this JPanel
        this.add(HomeButton);
        this.add(endbutton);

        // 1. CRITICAL FIX: Add this JPanel to your JFrame!
        jFrame.add(this);

        // 3. ALWAYS make the frame visible AFTER all components are added
        jFrame.setVisible(true);
    }

    private JButton createHomebutton(int x, int y) {
        JButton homebutton = new JButton();
        homebutton.setBounds(x, y, 120, 40);

        homebutton.setContentAreaFilled(false);
        homebutton.setFocusPainted(false);
        homebutton.setBorderPainted(true);
        homebutton.setVisible(true);
        homebutton.setText("Home");
        homebutton.setForeground(Color.pink);

        URL imgURL = getClass().getResource("/Images/cpixil-frame-0 (4).png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(120, 40, Image.SCALE_SMOOTH);
            homebutton.setIcon(new ImageIcon(scaled));
        } else {
            homebutton.setContentAreaFilled(false);
            homebutton.setOpaque(false);
        }
        return homebutton;
    }

    private JButton endButton(int x, int y){
        // Added text label "End" so there is something to look at
        JButton end = new JButton();
        end.setBounds(x, y, 120, 40);
        end.setText("End");
        end.setForeground(Color.pink);

        end.setFocusPainted(false);
        end.setBackground(new Color(0, 0, 0)); // Black button body
        end.setVisible(true);

        return end;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(248, 233, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}