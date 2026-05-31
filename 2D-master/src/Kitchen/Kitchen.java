package Kitchen;

import ingredient.Bag;
import ingredient.Ingredient;
import ingredient.IngredientList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Kitchen extends JPanel{
    private JFrame jFrame;
    private IngredientList ingredientList;
    private Bag bag;
    private Image background;
    private String recipeType;

    private ArrayList<String> recipeOrder = new ArrayList<>();
    private ArrayList<String> placedIngredients = new ArrayList<>();

    private int lives = 3;
    private Ingredient draggedIng= null;
    private int dragX, dragY;
    private final int slotSize = 60;
    private final int gap = 10;
    private final int bagStartX = 50;
    private final int bagStartY = 150;
    private final int recipeStartX = 400;
    public final int recipeStartY = 150;

    public Kitchen(IngredientList ingredientList, Bag bag, String recipeType){
        this.recipeType = recipeType;
        this.ingredientList = ingredientList;
        this.bag = bag;
        this.background = new ImageIcon(getClass().getResource("/Images/kitchen.png")).getImage();
        for (String name : ingredientList.getIngredients()){
            recipeOrder.add(name);
        }
        jFrame= new JFrame("Kitchen");
        init();
    }

    public void init(){
        jFrame.setSize(800, 600);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(null);
        this.setBounds(0, 0, 800, 600);
        jFrame.add(this);
        jFrame.setVisible(true);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i<bag.size(); i++){
                    int x = bagStartX;
                    int y = bagStartY + i * (slotSize + gap);
                    Rectangle slot = new Rectangle(x,y,slotSize,slotSize);

                    if (slot.contains(e.getX(), e.getY())){
                        draggedIng = bag.get(i);
                        dragX = e.getX();
                        dragY = e.getY();
                        break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (draggedIng == null){
                    return;
                }
                int nextSlot = placedIngredients.size();
                if (nextSlot < recipeOrder.size()){
                    int x = recipeStartX;
                    int y = recipeStartY +nextSlot * (slotSize + gap);
                    Rectangle slot = new Rectangle(x,y, slotSize,slotSize);

                    if (slot.contains(e.getX(), e.getY())){
                        if (draggedIng.getName().equalsIgnoreCase(recipeOrder.get(nextSlot))){
                            placedIngredients.add(draggedIng.getName());
                            bag.remove(bag.getItems().indexOf(draggedIng));

                            if (placedIngredients.size() == recipeOrder.size()){
                                jFrame.dispose();
                                if (recipeType.equals("CAKE")){
                                    new Win("wincake.png");
                                }else{
                                    new Win("winsushi.png");
                                }
                                return;
                            }
                        }else{
                            lives --;
                            if (lives <= 0){
                                jFrame.dispose();
                                new Lose("loseScreen.png");
                                return;
                            }
                        }
                    }
                }
                draggedIng = null;
                repaint();

            }
        });
       this.addMouseMotionListener(new MouseAdapter() {
           @Override
           public void mouseDragged(MouseEvent e) {
               if (draggedIng != null){
                   dragX = e.getX();
                   dragY = e.getY();
                   repaint();
               }
           }
       });
       jFrame.add(this);
       jFrame.setVisible(true);
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // Background
       g2.drawImage(background,0,0,getWidth(),getHeight(),null);
       // lives
        g2.setColor(new Color(100,60,60));
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Lives: "+ lives, 50,100);
        // bag label
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD,16));
        g2.drawString("Your Bag", bagStartX,bagStartY -10);

        // bag slots
        for (int i = 0; i < bag.size(); i++) {
            int x = bagStartX;
            int y = bagStartY + i * (slotSize + gap);

            g2.setColor(new Color(0,0,0,90));
            g2.fillRoundRect(x,y,slotSize,slotSize,8,8);
            g2.setColor(new Color(210,180,120));
            g2.drawRoundRect(x,y,slotSize,slotSize,8,8);
            Ingredient ing = bag.get(i);
            if (draggedIng != ing){
                g2.drawImage(ing.getImg(), x +4, y+4, slotSize-8, slotSize-8, null);
                g2.setColor(Color.BLACK);
                g2.setFont(new Font("Arial", Font.PLAIN,10));
                g2.drawString(ing.getName(), x+4, y + slotSize -4);
            }

        }
        // recipe label
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Recipe Order", recipeStartX, recipeStartY-10);
        // recipe slots
        for (int i = 0; i< recipeOrder.size(); i++){
            int x = recipeStartX;
            int y = recipeStartY + i* (slotSize + gap);

            if (i< placedIngredients.size()){
                g2.setColor(new Color(0,200,0,90));
            }else{
                g2.setColor(new Color(2, 2, 2,90));
            }
            g2.fillRoundRect(x,y, slotSize,slotSize,8,8);
            g2.setColor(new Color(210,180,120));
            g2.drawRoundRect(x,y, slotSize,slotSize,8,8);
            g2.setColor(Color.BLACK);
            g2.setFont(new Font("Arial", Font.PLAIN, 12));
            g2.drawString((i+1) + ". "+ recipeOrder.get(i), x+4, y +slotSize/2);

            // draw dragged ingredient following cursor
            if (draggedIng != null){
                g2.drawImage(draggedIng.getImg(),
                        dragX-slotSize /2, dragY-slotSize/2, slotSize,slotSize,null);
            }
        }
    }
}
