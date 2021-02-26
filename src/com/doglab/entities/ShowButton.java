package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class ShowButton extends Button{

	public ShowButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		Entity e = new Entity(getX(), getY(), getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		TextLabel.showSpace = !TextLabel.showSpace;
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY(), width, height, null);
	}
	
}
