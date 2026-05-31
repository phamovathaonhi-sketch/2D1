package Mbar;
import Kitchen.Lose;
import ingredient.IngredientList;
import main.S2;
import main.Selections;


import javax.swing.*;
import java.awt.*;

public class MBar extends JPanel {
    private JFrame jFrame;
    private IngredientList ingredientList;
    private String recipeType;
    private JFrame gamewindow;

    public MBar(IngredientList ingredientList, String recipeType, JFrame gamewindow){
        this.ingredientList= ingredientList;
        this.recipeType = recipeType;
        this.gamewindow = gamewindow;
        jFrame = new JFrame("Menu Bar");
        init();
    }

    public void init(){
        jFrame.setSize(200, 320);
        jFrame.setLocation(380,180);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        this.setLayout(null);

        JButton HomeButton = createHomebutton(40, 90);
        HomeButton.addActionListener(e -> {
            Selections selections = new Selections(ingredientList);
            gamewindow.dispose();
            jFrame.dispose();
        });

        JButton endbutton = endButton(40, 140);
        endbutton.addActionListener(e -> {
            new Lose("loseScreen.png");
        });

        JButton ingredientbutton = ingredientButton(40, 190);
        ingredientbutton.addActionListener(e -> {
            if (recipeType.equals("CAKE")){
                ingredientList.loadCake();
            } else if (recipeType.equals("SUSHI")) {
                ingredientList.loadSushi();
            }
            ingredientList.showWindow();
        });
        // Add the buttons to JPanel
        this.add(HomeButton);
        this.add(endbutton);
        this.add(ingredientbutton);

        jFrame.add(this);
        jFrame.setVisible(true);
    }

    private JButton createHomebutton(int x, int y) {
        JButton homebutton = new JButton();
        homebutton.setBounds(x, y, 120, 40);

        homebutton.setContentAreaFilled(false);
        homebutton.setFocusPainted(false);
        homebutton.setBorderPainted(true);
        homebutton.setVisible(true);
        homebutton.setText("Home");
        homebutton.setForeground(Color.pink);
        return homebutton;
    }

    private JButton endButton(int x, int y){
        JButton end = new JButton();
        end.setBounds(x, y, 120, 40);
        end.setText("End");
        end.setForeground(Color.pink);
        end.setFocusPainted(false);
        end.setVisible(true);

        return end;
    }

    private JButton ingredientButton(int x, int y){
        JButton ingredient = new JButton();
        ingredient.setBounds(x,y,120,40);
        ingredient.setText("Ingredient");
        ingredient.setForeground(Color.pink);
        ingredient.setFocusPainted(false);
        ingredient.setVisible(true);

        return ingredient;
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(248, 233, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}