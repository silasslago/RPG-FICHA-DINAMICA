package com.doglab.entities;

import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Button extends Entity{
	
	public Button(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		Entity e = new Entity(getX(), getY(), getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		
	}

}
