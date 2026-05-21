package Mbar;

import main.S1;
import main.Selections;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class MBar extends JPanel {
    private JFrame jFrame;
    private Thread thread;

    public MBar(){
       jFrame = new JFrame("Menu Bar");
       init();
       this.requestFocusInWindow();
    }
    public void init(){
        jFrame.setSize(200,320);
        jFrame.setLocation(490,490);
        jFrame.setBackground(new Color(197, 91, 91));
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        JButton HomeButton = createHomebutton(100,275);
        /*HomeButton.addActionListener(e -> {
            S1 s1 = new S1();
            s1.startGamethread();
            jFrame.dispose();

        });

         */
        jFrame.add(HomeButton);
    }
    public JButton createHomebutton(int x, int y) {
        JButton homebutton = new JButton();
        homebutton.setBounds(x, y, 50, 50);
        homebutton.setBorderPainted(false);
        homebutton.setContentAreaFilled(false);
        homebutton.setFocusPainted(false);
        homebutton.setOpaque(false);
        homebutton.setBorderPainted(false);
        homebutton.setVisible(true);

      /*  URL imgURL = getClass().getResource("/Images/cpixil-frame-0 (4).png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(100, 40, Image.SCALE_SMOOTH);
            homebutton.setIcon(new ImageIcon(scaled));
        }

       */
        return homebutton;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 210, 225));
        g.fillRect(490, 490, 200,320);
    }
}
