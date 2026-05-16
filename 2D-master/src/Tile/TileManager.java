package Tile;

import main.GamePanel;
import main.S1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
   public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadImages();
        loadMap();

    }

    private void loadImages() {
        try {
            tile[0] = new Tile();
            tile[0].img = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/water.png"));

            tile[1] = new Tile();
            tile[1].img = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].img = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/path2.png"));
            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].img = ImageIO.read(getClass().getResourceAsStream("/tiles/cereal.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/map/Map1.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int row = 0;

            while (row < gp.maxWorldRow) {

                String line = br.readLine();
                String[] num = line.split(" ");

                for (int col = 0; col < gp.maxWorldCol; col++) {
                    int number = Integer.parseInt(num[col]);
                    mapTileNum[col][row] = number;
                }

                row++;
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
        int worldcol = 0;
        int worldrow = 0;

        while (worldcol < gp.maxWorldCol && worldrow < gp.maxWorldRow) {

            int tileNum = mapTileNum[worldcol][worldrow];

            int worldX = worldcol * gp.tileSize;
            int worldY = worldrow * gp.tileSize;

            int screenX = worldX - gp.p.worldx + gp.p.sX;
            int screenY = worldY - gp.p.worldy + gp.p.sY;

            g2.drawImage(tile[tileNum].img, screenX, screenY, gp.tileSize, gp.tileSize, null);

            worldcol++;

            if (worldcol == gp.maxWorldCol) {
                worldcol = 0;
                worldrow++;
            }
        }
    }
}




