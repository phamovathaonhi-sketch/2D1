package main;

import Mbar.MBar;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Selections extends JPanel implements Runnable {

    private JFrame jframe;
    private Thread gameThread;
    private boolean running = true;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public Selections() {
        jframe = new JFrame("Selections");
        init();
    }

    private void init() {
        jframe.setSize(WIDTH, HEIGHT);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.setLayout(null);
        this.setBounds(0, 0, WIDTH, HEIGHT);
        jframe.add(this);

        try {
            InputStream is = getClass().getResourceAsStream(
                    "/Images/a34c95dc15ad78b97bb6c5fd681f8579.jpg");
            if (is != null) {
                jframe.setIconImage(ImageIO.read(is));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton cake = createCakeButton(180, 280);
        cake.addActionListener(e -> {
            S1 s1 = new S1();
            s1.startGamethread();
            running = false;
            jframe.dispose();
        });

        JButton fish = createFishButton(480, 300);
        fish.addActionListener(e -> {
           S2 s2 = new S2();
           s2.startGamethread();
            running = false;
            jframe.dispose();
        });
        //test button
        JButton testbutton = createTestButton(280, 100);
        testbutton.addActionListener(e -> {
                MBar mbar = new MBar();
        });

        this.add(cake);
        this.add(fish);
        this.add(testbutton);

        startGameThread();
        jframe.setVisible(true);
    }

    private JButton createCakeButton(int x, int y) {
        JButton button = new JButton();
        button.setBounds(x, y, 180, 180);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        URL imgURL = getClass().getResource("/Images/cake.png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            button.setIcon(new ImageIcon(scaled));
        }
        return button;
    }
    private JButton createFishButton(int x, int y) {
        JButton button2 = new JButton();
        button2.setBounds(x, y, 180, 180);
        button2.setBorderPainted(false);
        button2.setContentAreaFilled(false);
        button2.setFocusPainted(false);
        button2.setOpaque(false);

        URL imgURL = getClass().getResource("/Images/sashimi.png");
        if (imgURL != null) {
            ImageIcon temp = new ImageIcon(imgURL);
            Image scaled = temp.getImage().getScaledInstance(180, 180, Image.SCALE_SMOOTH);
            button2.setIcon(new ImageIcon(scaled));
        }
        return button2;
    }

    public JButton createTestButton(int x, int y ){
        JButton jbutton3 = new JButton();
        jbutton3.setBounds(x,y, 50,50);
        jbutton3.setBackground(new Color(0,0,0));
        return jbutton3;


    }

    private void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        final int FPS = 60;
        final long drawInterval = 1_000_000_000 / FPS;

        while (running) {
            long start = System.nanoTime();
            repaint();

            long sleep = drawInterval - (System.nanoTime() - start);
            if (sleep > 0) {
                try {
                    Thread.sleep(sleep / 1_000_000);
                } catch (InterruptedException ignored) {}
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(245, 210, 225));
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(new Color(234, 45, 96, 229));
        g.setFont(new Font("Arial", Font.BOLD, 47));
        g.drawString("CHOOSE ONE TO CONTINUE", 105, 180 );

    }
}