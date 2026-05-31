package main;

import Kitchen.Gate;
import Kitchen.Kitchen;
import Mbar.MBar;
import ingredient.Bag;
import ingredient.BagPopUp;
import ingredient.Ingredient;
import ingredient.IngredientList;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class S1 extends GamePanel {

    private JFrame jframe;
    private JPanel uiPanel;
    private IngredientList ingredientList;
    private String recipeType = "CAKE";
    private Bag bag = new Bag();
    private Gate gate;
    private boolean gateVisible = false;

    private ArrayList<Ingredient> mapIngredients = new ArrayList<>();

    private Thread gamethread;
    int FPS = 60;

    public S1(IngredientList ingredientList) {
        this.ingredientList = ingredientList;
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
        // Bag button
        JButton bagButton = new JButton();
        bagButton.setBounds(10,490,50,50);
        bagButton.setIcon(new ImageIcon(getClass().getResource("/Images/bag.png")));
        bagButton.setBorderPainted(false);
        bagButton.setLayout(null);
        bagButton.setFocusable(false);

        // MENU BUTTON
        JButton menuButton = new JButton();
        menuButton.setBounds(10, 10, 50, 50);
        menuButton.setIcon(new ImageIcon(getClass().getResource("/Images/menubutton.png")));
        menuButton.setFocusable(false);


        menuButton.addActionListener(e -> {
            new MBar(ingredientList, recipeType, jframe);
        });
        gate = new Gate(45*48,30*48);
        String[][] ingredientByTile = {{"fish", "seaweed"},{"wheat","flour"},{"milk","soysauce","strawberry","sugar","vinegar","egg","vanilla"}};
        int[]tileTypes = {0,4,5};
        int amount = 3;
        Random rnd = new Random();

        for (int t = 0; t < tileTypes.length; t++) {
            int tileType = tileTypes[t];
            ArrayList<Integer> xPositions = new ArrayList<>();
            ArrayList<Integer> yPositions = new ArrayList<>();

            for (int row = 0; row < tileManager.mapTileNum[0].length; row++) {
                for (int col = 0; col < tileManager.mapTileNum.length; col++) {
                    if (tileManager.mapTileNum[col][row] == tileType) {
                        xPositions.add(col * 48);
                        yPositions.add(row * 48);
                    }
                }
            }

            for (String name : ingredientByTile[t]) {
                for (int i = 0; i < amount; i++) {
                    if (!xPositions.isEmpty()) {
                        int index = rnd.nextInt(xPositions.size());
                        int x = xPositions.get(index);
                        int y = yPositions.get(index);
                        mapIngredients.add(new Ingredient(name, x, y));
                    }
                }
            }
        }

        bagButton.addActionListener(e -> {
            BagPopUp.show(jframe, bag);
            this.requestFocusInWindow();
        });

        uiPanel.add(menuButton);
        uiPanel.add(bagButton);

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

         for (Ingredient ing: mapIngredients){
             if (!ing.isCollected() && p.getBounds().intersects(ing.getBounds())){
                 ing.collect();
                 bag.add(ing);
                 ingredientList.removeIng(ing.getName());
             }
         }
         if (ingredientList.isEmpty()){
             gateVisible = true;
         }
         if (gateVisible&& p.getBounds().intersects(gate.getBounds())){
             gamethread = null;
             jframe.dispose();
             new Kitchen(ingredientList,bag, recipeType);
         }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        for (Ingredient ing: mapIngredients){
            ing.draw(g2, p);
        }
        if (gateVisible){
            gate.draw(g2, p);

        }
        p.paint(g2);


        g2.dispose();
    }
}