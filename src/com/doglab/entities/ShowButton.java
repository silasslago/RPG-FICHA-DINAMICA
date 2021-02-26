package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class ShowButton extends Button{

	private BufferedImage open, closed;
	
	public ShowButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		open = sprite;
		closed = Game.spr_entities.getSprite(151, 231, 25, 25);
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
		if(TextLabel.showSpace) {
			this.setSprite(open);
		}else {
			this.setSprite(closed);
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY(), width, height, null);
	}
	
}
