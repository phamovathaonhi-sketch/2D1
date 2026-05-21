package main;

import Mbar.MBar;
import Tile.TileManager;
import gameChar.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class S1 extends GamePanel{

    private JFrame jframe;
    private Thread gamethread;
    private MBar bar;

    // ===== FPS =====
    int FPS = 60;


    public S1() {
        jframe = new JFrame("Farm1");
        init();

        this.addKeyListener(K);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    private void init() {
        jframe.setSize(screenwidth, screenheight);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);

        try {
            InputStream is = getClass().getResourceAsStream(
                    "/Images/a34c95dc15ad78b97bb6c5fd681f8579.jpg");
            if (is != null) {
                jframe.setIconImage(ImageIO.read(is));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton mBar = MenuButton(200,200);
        mBar.addActionListener(e -> new MBar());
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(0, 0, screenwidth, screenheight);
        this.setDoubleBuffered(true);
        this.add(mBar);

        jframe.add(this);
        jframe.setVisible(true);
    }

    public void startGamethread() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    public JButton MenuButton(int x, int y){
        JButton button = new JButton();
        button.setSize(50,50);
        button.setLocation(x,y);
        button.setBackground(new Color(238, 154, 154));
        button.addActionListener(e ->{
            new MBar();
        });
        return button;
    }



    @Override
    public void run() {
        double drawInterval = 1_000_000_000/ FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gamethread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        p.update();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        p.paint(g2);

        g2.dispose();
    }
}