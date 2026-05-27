package gameChar;
import main.GamePanel;
import main.Keyhandler;
import main.S1;
import main.S2;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gamePanel;
    Keyhandler keyhandler;
    public int sX;
    public int sY;



    public Player(GamePanel gamePanel, Keyhandler keyhandler) {
        this.gamePanel = gamePanel;
        this.keyhandler = keyhandler;
        sX = gamePanel.screenwidth/2 - (gamePanel.tileSize/2);
        sY = gamePanel.screenheight/2 - (gamePanel.tileSize/2);
        solidArea = new Rectangle(8,16, 32, 32);
        setDefault();
        getImage();
    }
    public Rectangle getBounds() {
        return new Rectangle(worldx, worldy, gamePanel.tileSize, gamePanel.tileSize);
    }


    public void setDefault(){
        for (int row = 0; row < gamePanel.maxWorldRow; row++) {
            for (int col = 0; col < gamePanel.maxWorldCol; col++) {

                if (gamePanel.tileManager.mapTileNum[col][row] == 2) {

                    worldx = col * gamePanel.tileSize;
                    worldy = row * gamePanel.tileSize;

                    speed = 4;
                    direction = "down";
                    return;
                }
            }
        }
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
            } else if (keyhandler.downPressed == true) {
                direction ="down";
            } else if (keyhandler.rightPressed == true) {
                direction ="right";
            } else if (keyhandler.leftPressed == true) {
                direction ="left";
            }
            // if collision = false then player can  move
            collisionON = false;
            gamePanel.c.checkTile(this);
            if (collisionON == false){
                switch (direction){
                    case "up":
                        worldy-=speed;
                        break;
                    case "down":
                        worldy+=speed;
                        break;
                    case "right":
                        worldx += speed;
                        break;
                    case "left":
                        worldx -= speed;
                        break;

                }
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
        g2.drawImage(img, sX,sY, gamePanel.tileSize,gamePanel.tileSize, null);
    }
}