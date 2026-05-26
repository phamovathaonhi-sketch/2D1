package main;

import ingredientList.IngredientList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class TitleScreen extends JPanel implements Runnable {

    private JFrame jframe;
    private TitleBackground background;
    private Thread gameThread;
    private boolean running = true;
    IngredientList ingredientList;

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    public TitleScreen() {
        jframe = new JFrame("My first 2D game");
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

        JButton startButton = new JButton();
        startButton.setBounds(280, 390, 250, 95);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        startButton.setFocusPainted(false);
        startButton.setOpaque(false);

        startButton.setIcon(new ImageIcon(
                getClass().getResource("/Images/pixil-frame-0.png")));

        startButton.addActionListener(e -> {
            IngredientList ingredientList = new IngredientList();
            new Selections(ingredientList);
            jframe.dispose();
        });

        this.add(startButton);

        background = new TitleBackground();
        startGameThread();

        jframe.setVisible(true);
    }
    public void closeWindow(){
        jframe.dispose();
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
        if (background != null) {
            background.draw(g, getWidth(), getHeight());
        }
    }
}