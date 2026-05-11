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
        mapTileNum = new int[gp.MAXSCREENCOL][gp.MAXSCREENROW];
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
    public void loadMap(){
        try {
            InputStream is = getClass().getResourceAsStream("Mapa.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.MAXSCREENCOL && row < gp.MAXSCREENROW){
                String line = br.readLine();
                while (col < gp.MAXSCREENCOL ){
                    String num[] = line.split(" ");
                    int number = Integer.parseInt(num[col]);
                    mapTileNum[col][row]= number;
                    col++;
                    if (col == gp.MAXSCREENCOL){
                        col = 0;
                        row ++;

                    }

                }
                br.close();

            }
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
           g2.drawImage(tile[0].img1, x ,y, gp.tileSize, gp.tileSize, null);
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