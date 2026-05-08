package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TitleBackground {

    private BufferedImage image1;
    private BufferedImage image2;

    private int frameCounter = 0;
    private final int switchRate = 60;

    public TitleBackground() {
        loadImages();
    }

    private void loadImages() {
        try {
            image1 = ImageIO.read(getClass().getResourceAsStream("/Images/bacground2(1).png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/Images/background1(1).png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics g, int width, int height) {
        frameCounter++;
        if (frameCounter >= switchRate * 2) {
            frameCounter = 0;
        }

        if (frameCounter < switchRate) {
            g.drawImage(image1, 0, 0, width, height, null);
        } else {
            g.drawImage(image2, 0, 0, width, height, null);
        }
    }
}