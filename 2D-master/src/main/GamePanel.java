package main;

import Tile.TileManager;
import gameChar.Player;

import javax.swing.*;

public abstract class GamePanel extends JPanel implements Runnable  {
    public int MAXSCREENCOL=16;
    public int MAXSCREENROW = 12;
    public int originalTileSize = 16;
    public int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public int screenheight = tileSize* MAXSCREENROW;
    public int screenwidth = tileSize * MAXSCREENCOL;
    // world settings
    Keyhandler K = new Keyhandler();
    public final int  maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize* maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;
    public TileManager tileManager;
    public Collisoncheck c = new Collisoncheck(this);
    public Player p;

    public GamePanel() {
        tileManager = new TileManager(this);
        p = new Player(this, K);
    }

}
