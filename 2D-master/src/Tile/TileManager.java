package Tile;

import main.S1;

import javax.imageio.ImageIO;
import java.awt.*;

public class TileManager {

    S1 gp;
    Tile[] tile;

    public TileManager(S1 gp) {
        this.gp = gp;
        tile = new Tile[10];
        loadImages();
    }

    private void loadImages() {
        try {
            tile[0] = new Tile();
            tile[0].img1 = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].img1 = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/water.png"));

            tile[2] = new Tile();
            tile[2].img1 = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/path.png"));




        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
      int col = 0;
      int row = 0;
      int x = 0;
      int y = 0;


    }
}