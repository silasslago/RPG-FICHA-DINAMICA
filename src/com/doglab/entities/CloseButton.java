package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class CloseButton extends Button{

	protected Label diceLabel;
	
	public CloseButton(double x, double y, int width, int height, double speed, BufferedImage sprite, Label l) {
		super(x, y, width, height, speed, sprite);
		diceLabel = l;
	}
	
	public void tick() {
		Entity e = new Entity(getX(), getY(), getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		diceLabel.changeTickers();
		Game.entities.remove(diceLabel);
		Game.mouseController.resetPosition();
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY(), width, height, null);
	}

}
