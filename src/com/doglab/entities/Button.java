package com.doglab.entities;

import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Button extends Entity{
	
	public Button(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		if(this.isColliding(this, Game.mouseController)) {
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		
	}

}
