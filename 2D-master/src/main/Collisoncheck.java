package main;

import gameChar.Entity;

public class Collisoncheck {
    GamePanel gp;
    public Collisoncheck(GamePanel gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftworldx = entity.worldx + entity.solidArea.x;
        int entityRightworldx = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopworldy = entity.worldy + entity.solidArea.y;
        int entityBottomworldy = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityleftcol = entityLeftworldx/gp.tileSize;
        int entityrightcol = entityRightworldx/gp.tileSize;
        int entityTopRow = entityBottomworldy/gp.tileSize;
        int entityBottomRow= entityBottomworldy/ gp.tileSize;

        int Tilenum1;
        int Tilenum2;
        switch (entity.direction){
            case "up":
                entityTopRow = (entityBottomworldy - entity.speed)/ gp.tileSize;
                Tilenum1 = gp.tileManager.mapTileNum[entityleftcol][entityTopRow]; // to check the left side of the collision
                Tilenum2 = gp.tileManager.mapTileNum[entityrightcol][entityTopRow];// to check the right side
                if (gp.tileManager.tile[Tilenum1].collision== true || gp.tileManager.tile[Tilenum2].collision == true){
                    // if one of this is correct then player is hitting the collision and it cannot move
                    entity.collisionON = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomworldy + entity.speed)/ gp.tileSize;
                Tilenum1 = gp.tileManager.mapTileNum[entityleftcol][entityBottomRow]; // to check the left side of the collision
                Tilenum2 = gp.tileManager.mapTileNum[entityrightcol][entityBottomRow];// to check the right side
                if (gp.tileManager.tile[Tilenum1].collision== true || gp.tileManager.tile[Tilenum2].collision == true){
                    // if one of this is correct then player is hitting the collision and it cannot move
                    entity.collisionON = true;
                }
                break;
            case "left":
               entityleftcol = (entityLeftworldx- entity.speed)/ gp.tileSize;
                Tilenum1 = gp.tileManager.mapTileNum[entityleftcol][entityTopRow]; // to check the left side of the collision
                Tilenum2 = gp.tileManager.mapTileNum[entityleftcol][entityBottomRow];// to check the right side
                if (gp.tileManager.tile[Tilenum1].collision== true || gp.tileManager.tile[Tilenum2].collision == true){
                    // if one of this is correct then player is hitting the collision and it cannot move
                    entity.collisionON = true;
                }
                break;
            case "right":
                entityrightcol= (entityRightworldx - entity.speed)/ gp.tileSize;
                Tilenum1 = gp.tileManager.mapTileNum[entityrightcol][entityTopRow]; // to check the left side of the collision
                Tilenum2 = gp.tileManager.mapTileNum[entityrightcol][entityBottomRow];// to check the right side
                if (gp.tileManager.tile[Tilenum1].collision== true || gp.tileManager.tile[Tilenum2].collision == true){
                    // if one of this is correct then player is hitting the collision and it cannot move
                    entity.collisionON = true;
                }
                break;
        }

    }
}
