package com.doglab.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.doglab.entities.Entity;
import com.doglab.main.Game;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 16;

	public World() {
		
	}
	
	public static double calculoDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public static Tile catchTile(int x, int y) {
		int mapX = x/16;
		int mapY = y/16;
		return tiles[mapX + (mapY*WIDTH)];
	}
	
	public static void restartGame() {
		
	}
	
	public static boolean place_free(int xnext, int ynext, int width, int height) {
		// Checando se os tiles ao redor do player tem colisão ou não.
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + width - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + height - 1) / TILE_SIZE;
		
		int x4 = (xnext + width - 1) / TILE_SIZE;
		int y4 = (ynext + height - 1) / TILE_SIZE;

		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof TileWall || 
				(tiles[x2 + (y2 * World.WIDTH)] instanceof TileWall) ||
				(tiles[x3 + (y3 * World.WIDTH)] instanceof TileWall) ||
				(tiles[x4 + (y4 * World.WIDTH)] instanceof TileWall)));
	}
	
	public static boolean place_free(int xnext, int ynext) {
		// Checando se os tiles ao redor do player tem colisão ou não.
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
		
		int x4 = (xnext + TILE_SIZE - 1) / TILE_SIZE;
		int y4 = (ynext + TILE_SIZE - 1) / TILE_SIZE;
		
		return !((tiles[x1 + (y1 * World.WIDTH)] instanceof TileWall || 
				(tiles[x2 + (y2 * World.WIDTH)] instanceof TileWall) ||
				(tiles[x3 + (y3 * World.WIDTH)] instanceof TileWall) ||
				(tiles[x4 + (y4 * World.WIDTH)] instanceof TileWall)));
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		// Sistema que renderiza somente o que está aparecendo na camera.

		int xstart = Camera.x >> 4;
		int ystart = Camera.y >> 4;
		int xfinal = xstart + (Game.WIDTH >> 4);
		int yfinal = ystart + (Game.HEIGHT >> 4);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) {
					continue;
				}
				Tile tile = tiles[xx + (yy * WIDTH)];
				tile.render(g);
			}
		}
	}
}
