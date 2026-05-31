package Kitchen;

import gameChar.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Gate {
    private int worldX, worldY;
    private final int width = 110;
    private final int height = 150;
    private Image image;

    public Gate(int worldX, int worldY){
        this.worldX = worldX;
        this.worldY = worldY;
        this.image = new ImageIcon(getClass().getResource("/Images/gate.png")).getImage();
    }
    public void draw(Graphics2D g2, Player p){
        int screenX = worldX - p.worldx + p.sX;
        int screenY = worldY - p.worldy+ p.sY;
        g2.drawImage(image, screenX, screenY, width, height, null);
    }
    public Rectangle getBounds(){
        return new Rectangle(worldX,worldY,width, height);
    }

}
