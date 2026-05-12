package main;

import Tile.TileManager;
import gameChar.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class S1 extends JPanel implements Runnable {

    private JFrame jframe;
    private Thread gamethread;

    // ===== TILE SETTINGS =====
    public int MAXSCREENCOL=16;
    public int MAXSCREENROW = 12;
    public int originalTileSize = 16;
    public int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public int screenheight = tileSize* MAXSCREENROW;
    public int screenwidth = tileSize * MAXSCREENCOL;
    // world settings
    public final int  maxWorldCol = 16;
    public final int maxWorldRow = 12;
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // ===== FPS =====
    int FPS = 60;

    // ===== GAME OBJECTS =====
    TileManager tileManager = new TileManager(this);
    Keyhandler K = new Keyhandler();
    public Player p = new Player(this, K);

    public S1() {
        jframe = new JFrame("S1");
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

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setBounds(0, 0, screenwidth, screenheight);
        this.setDoubleBuffered(true);

        jframe.add(this);
        jframe.setVisible(true);
    }

    public void startGamethread() {
        gamethread = new Thread(this);
        gamethread.start();
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