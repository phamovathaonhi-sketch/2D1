package gameChar;

import main.Keyhandler;
import main.S2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;

public class Player2 extends Entity{
     S2 gamepanel;
     Keyhandler keyhandler;

    public Player2(S2 gamepanel,Keyhandler keyhandler) {
        this.gamepanel = gamepanel;
       this.keyhandler = keyhandler;
        loadImg();
    }
    public void setDefault(){
        x= 100;
        y=100;
        speed = 4;
        direction = "down";
    }
    public void loadImg(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("player/behindpose1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("player/behindpose2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("player/pose1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("player/pose2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("player/poseright1.png"));
            right2= ImageIO.read(getClass().getResourceAsStream("player/poseright2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("player/poseleft1"));
            left2 = ImageIO.read(getClass().getResourceAsStream("player/poseleft2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void update() {
        if (keyhandler.downPressed==true||keyhandler.rightPressed==true||keyhandler.upPressed==true|| keyhandler.leftPressed){
            if (keyhandler.downPressed == true) {
                direction = "down";
                y -= speed;
            }
            if (keyhandler.leftPressed == true) {
                direction = " left";
                x -= speed;
            }
            if (keyhandler.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            if (keyhandler.upPressed == true) {
                direction = " up";
                y += speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spritenum == 1) {
                    spritenum = 2;
                } else if (spritenum == 2) {
                    spritenum = 1;

                }
                spriteCounter = 0;
            }
        }
    }
    public void paint(Graphics2D g){
        BufferedReader img = null;

        switch (direction){
            case "down":
                if (spritenum == 1){
                    img = down1;
                }
        }
    }


}
