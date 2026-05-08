package gameChar;
import main.Keyhandler;
import main.S1;
import main.S2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    S1 gamePanel;
    Keyhandler keyhandler;

    public Player(S1 gamePanel, Keyhandler keyhandler) {
        this.gamePanel = gamePanel;
        this.keyhandler = keyhandler;
        setDefault();
        getImage();
    }

    public void setDefault(){
        x= 100;
        y=100;
        speed = 4;
        direction = "down";
    }
    public void getImage(){
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/behindpose1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/behindpose2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/pose1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/pose2.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/poseleft1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/poseleft2.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/poseright1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/poseright2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(){
        if (keyhandler.downPressed == true || keyhandler.upPressed == true || keyhandler.leftPressed == true || keyhandler.rightPressed == true){
            if (keyhandler.upPressed == true){
                direction ="up";
                y-=speed;
            } else if (keyhandler.downPressed == true) {
                direction ="down";
                y+=speed;
            } else if (keyhandler.rightPressed == true) {
                direction ="right";
                x += speed;
            } else if (keyhandler.leftPressed == true) {
                direction ="left";
                x -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12){
                if (spritenum ==1){
                    spritenum =2;
                } else if (spritenum ==2) {
                    spritenum =1;

                }
                spriteCounter =0;
            }
        }


    }
    public void paint(Graphics2D g2){
        BufferedImage img= null;

        switch (direction){
            case "up":
                if (spritenum == 1){
                    img = up1;
                }
                if (spritenum == 2)
                    img = up2;
                break;
            case "down":
                if (spritenum ==1) {
                    img = down1;
                }
                if (spritenum == 2){
                    img = down2;
                }
                break;
            case "left":
                if (spritenum == 1){
                    img = left1;
                }
                if (spritenum ==2){
                    img = left2;
                }
                break;
            case "right":
                if (spritenum ==1){
                    img=right1;
                }
                if (spritenum == 2){
                    img = right2;
                }
                break;

        }
        g2.drawImage(img, x,y, gamePanel.tileSize,gamePanel.tileSize, null);
    }
}