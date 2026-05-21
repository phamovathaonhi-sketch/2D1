package Mbar;

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
        jFrame.setLocation(300,300);
        jFrame.setBackground(new Color(197, 91, 91));
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        JButton HomeButton = createHomebutton(200-200/2,275);
        HomeButton.addActionListener(e -> {
            Selections selection = new Selections();
            jFrame.dispose();
        });
        this.add(HomeButton);
    }
    public JButton createHomebutton(int x, int y){
        JButton homebutton = new JButton();
        homebutton.setBounds(x,y, 100,40);
        homebutton.setBorderPainted(false);
        homebutton.setContentAreaFilled(false);
        homebutton.setFocusPainted(false);
        homebutton.setOpaque(false);
        homebutton.setBorderPainted(false);

        URL imgURL = getClass().getResource("/Images/cake.png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            homebutton.setIcon(new ImageIcon(scaled));
        }
        return homebutton;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 210, 225));
        g.fillRect(0, 0, getWidth(), getHeight());


    }



}
