package Mbar;
import ingredientList.IngredientList;
import main.S1;
import main.S2;
import main.Selections;
import main.TitleScreen;

import javax.swing.*;
import java.awt.*;

public class MBar extends JPanel {
    private JFrame jFrame;
    private IngredientList ingredientList;
    private String recipeType;

    S1 s1;


    public MBar(IngredientList ingredientList, String recipeType){
        this.ingredientList= ingredientList;
        this.recipeType = recipeType;
        jFrame = new JFrame("Menu Bar");
        init();
    }

    public void init(){
        jFrame.setSize(200, 320);
        jFrame.setLocation(0,0);
        jFrame.setResizable(false);
        jFrame.setVisible(true);

        this.setLayout(null);

        JButton HomeButton = createHomebutton(40, 90);
        HomeButton.addActionListener(e -> {
            TitleScreen titleScreen = new TitleScreen();
            jFrame.dispose();
        });

        JButton endbutton = endButton(40, 140);
        endbutton.addActionListener(e -> {
            S2 s2 = new S2();
            s2.startGamethread();
            jFrame.dispose();
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
        JButton instructionsbutton = instructionsButton(40,240);


        // Add the buttons to JPanel
        this.add(HomeButton);
        this.add(endbutton);
        this.add(ingredientbutton);
        this.add(instructionsbutton);

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
    private JButton instructionsButton(int x, int y){
        JButton instruction = new JButton();
        instruction.setBounds(x,y, 120,40);
        instruction.setText("Instruction");
        instruction.setForeground(Color.pink);
        instruction.setFocusPainted(false);
        instruction.setVisible(true);

        return instruction;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(248, 233, 235));
        g.fillRect(0, 0, getWidth(), getHeight());
    }
}