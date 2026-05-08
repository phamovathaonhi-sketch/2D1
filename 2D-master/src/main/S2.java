package main;

import Tile.TileManager;
import gameChar.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class S2 extends JPanel implements Runnable {

    private JFrame jframe;
    private Thread gamethread;

    // ===== TILE SETTINGS =====
    public int MAXSCREENCOL = 16;
    public int MAXSCREENROW = 12;
    public int originalTileSize = 16;
    public int scale = 4;
    public int tileSize = originalTileSize * scale;
    public int screenheight = tileSize * MAXSCREENROW;
    public int screenwidth = tileSize * MAXSCREENCOL;

    // ===== FPS =====
    int FPS = 60;

    // ===== GAME OBJECTS =====
    Keyhandler K = new Keyhandler();

    public S2() {
        jframe = new JFrame("S2");
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

    @Override
    public void run() {

    }
}
