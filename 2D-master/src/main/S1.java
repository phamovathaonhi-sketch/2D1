package main;

import Mbar.MBar;

import javax.swing.*;
import java.awt.*;

public class S1 extends GamePanel {

    private JFrame jframe;
    private JPanel uiPanel;
    private MBar menu;

    private Thread gamethread;
    int FPS = 60;

    public S1() {
        jframe = new JFrame("Farm1");
        init();

        this.addKeyListener(K);
        this.setFocusable(true);
    }

    private void init() {

        jframe.setSize(screenwidth, screenheight);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setLayout(null);

        // GAME LAYER
        this.setBounds(0, 0, screenwidth, screenheight);

        // UI LAYER
        uiPanel = new JPanel();
        uiPanel.setLayout(null);
        uiPanel.setOpaque(false);
        uiPanel.setBounds(0, 0, screenwidth, screenheight);

        // MENU BAR (hidden at start)
        menu = new MBar();
        menu.setVisible(true);
        // MENU BUTTON
        JButton menuButton = new JButton("Menu");
        menuButton.setBounds(10, 10, 80, 40);
        menuButton.setBackground(new Color(0,0,0));

        menuButton.addActionListener(e -> {
            MBar m = new MBar();
        });

        uiPanel.add(menuButton);
        uiPanel.setVisible(true);

        jframe.add(this);
        jframe.add(uiPanel);
        jframe.setVisible(true);
    }


    public void startGamethread() {
        gamethread = new Thread(this);
        gamethread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1_000_000_000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gamethread != null) {

            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime /= 1_000_000;

                if (remainingTime < 0) remainingTime = 0;

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