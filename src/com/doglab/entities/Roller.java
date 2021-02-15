package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Roller extends Entity{

	public int step = 10;
	
	public Roller(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(Game.mouseController.isPressed) {
			if(this.isColliding(this, new Entity(Game.mouseController.currentX, Game.mouseController.currentY, 
					Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null))) {
				int dif = (int)Game.mouseController.currentY - height/2;
				this.setY(dif);
			}
		}
		if(this.getY() < 0) {
			this.setY(0);
		}
		if(this.getY() > Game.HEIGHT-this.getHeight()) {
			this.setY(Game.HEIGHT-this.getHeight());
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(Game.WIDTH-getWidth(), 0, getWidth(), Game.HEIGHT);
		g.setColor(Color.gray);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
	}

}
