package com.doglab.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Tile {

	public boolean show = true;
	
	protected BufferedImage sprite;
	public int x, y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		if(show) {
			g.drawImage(this.sprite, this.x - Camera.x, this.y - Camera.y, null);
		}
	}
	
}
