package Tile;

import main.TitleScreen;

import java.io.IOException;

public class TileManager2 {
    TitleScreen gp;
    Tile t;

    public TileManager2(TitleScreen gp){
        this.gp = gp;
        getIMG();
    }

    public void getIMG(){
        try {
            t = new Tile();

        }catch (Exception e ){
            throw new RuntimeException();
        }

    }
}
