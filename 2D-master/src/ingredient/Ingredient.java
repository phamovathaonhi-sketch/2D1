package ingredient;

import gameChar.Player;

import javax.swing.*;
import java.awt.*;

public class Ingredient {
    private String name;
    private Image img;
    private int worldX, worldY;
    private final int size= 32;
    private boolean collected = false;

    public Ingredient(String name, int x, int y) {
        this.name = name;
        this.worldX = x;
        this.worldY = y;
        this.img = new ImageIcon(getClass().getResource("/Images/" + name + ".png")).getImage();
    }
    public void draw(Graphics2D g2, Player p){
        if (!collected){
            int screenX = worldX - p.worldx + p.sX;
            int screenY = worldY - p.worldy + p.sY;
            g2.drawImage(img, screenX, screenY, size, size, null);
        }
    }
    public Rectangle getBounds(){
        return new Rectangle(worldX, worldY, size,size);
    }
    public boolean isCollected(){
        return collected;
    }
    public void collect(){
        this.collected = true;
    }
    public String getName(){
        return name;
    }
    public Image getImg(){
        return img;
    }
}
