package main;

import Mbar.MBar;
import ingredient.IngredientList;

import javax.swing.*;
import java.awt.*;

public class S2 extends GamePanel {

    private JFrame jframe;
    private JPanel uiPanel;
    private IngredientList ingredientList;
    private String recipeType = "SUSHI";

    private Thread gamethread;
    int FPS = 60;

    public S2(IngredientList ingredientList) {
        this.ingredientList = ingredientList;
        jframe = new JFrame("Farm2");
        init();

        this.addKeyListener(K);
        this.setFocusable(true);
    }

    private void init() {

        jframe.setSize(screenwidth, screenheight);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // LAYERED PANE
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, screenwidth, screenheight);

        // GAME LAYER
        this.setBounds(0, 0, screenwidth, screenheight);
        layeredPane.add(this, Integer.valueOf(0)); // bottom layer

        // UI LAYER
        uiPanel = new JPanel(null);
        uiPanel.setOpaque(false);
        uiPanel.setBounds(0, 0, screenwidth,screenheight);
        layeredPane.add(uiPanel, Integer.valueOf(1)); // top layer

        // MENU BUTTON
        JButton menuButton = new JButton();
        menuButton.setBounds(10, 10, 50, 50);
        menuButton.setBackground(new Color(0,0,0));
        menuButton.setFocusable(false);


        menuButton.addActionListener(e -> {
            new MBar(ingredientList, recipeType);
        });

        uiPanel.add(menuButton);

        jframe.add(layeredPane);
        jframe.setVisible(true);
        this.requestFocusInWindow();
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