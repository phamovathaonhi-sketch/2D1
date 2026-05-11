package Tile;

import main.S1;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    S1 gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(S1 gp) {
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
                    getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].img = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/water.png"));

            tile[2] = new Tile();
            tile[2].img = ImageIO.read(
                    getClass().getResourceAsStream("/tiles/path.png"));
            tile[3] = new Tile();
            tile[3].img = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].img = ImageIO.read(getClass().getResourceAsStream("/tiles/cereal.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* public void loadMap() {

        try {

            InputStream is = getClass().getResourceAsStream("/map/Mapa.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW){

                String line = br.readLine();

                while (col < gp.MAXSCREENCOL){
                    String num[] = line.split(" ");

                    int number = Integer.parseInt(num[col]);

                    mapTileNum[col][row] = number;

                    col++;

                    if (col == gp.MAXSCREENCOL){

                        col = 0;
                        row++;
                    }
                }
            }

            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    */
   public void loadMap() {

       try {

           InputStream is = getClass().getResourceAsStream("/map/Mapa.txt");
           BufferedReader br = new BufferedReader(new InputStreamReader(is));

           int row = 0;

           while (row < gp.MAXSCREENROW) {

               String line = br.readLine();

               String[] num = line.split(" ");

               for (int col = 0; col < gp.MAXSCREENCOL; col++) {

                   int number = Integer.parseInt(num[col]);

                   mapTileNum[col][row] = number;
               }

               row++;
           }

           br.close();

       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gp.MAXSCREENCOL && row< gp.MAXSCREENROW){
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].img, x ,y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if (col == gp.MAXSCREENCOL){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}




