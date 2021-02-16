package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Roller extends Entity{

	public int step;
	private boolean thisStep;
	private int barX, barY, barW, barH;
	
	public Roller(double x, double y, int width, int height, double speed, BufferedImage sprite, boolean step,
			int barX, int barY, int barW, int barH) {
		super(x, y, width, height, speed, sprite);
		this.step = (int)speed;
		this.thisStep = step;
		this.barX = barX;
		this.barY = barY;
		this.barW = barW;
		this.barH = barH;
	}
	
	public void tick() {
		if(Game.mouseController.isPressed) {
			if(thisStep) {
				if(this.isColliding(this, new Entity(Game.mouseController.currentX, 
						Game.mouseController.currentY+Game.roller.getY()*Game.roller.step, width, height, 0, null))) {
					int dif = (int)Game.mouseController.currentY+Game.roller.getY()*Game.roller.step - height/2;
					this.setY(dif);
				}
			}else {
				if(this.isColliding(this, new Entity(Game.mouseController.currentX, Game.mouseController.currentY, 
						width, height, 0, null))) {
					int dif = (int)Game.mouseController.currentY - height/2;
					this.setY(dif);
				}
			}
			
		}
		if(this.getY() < barY) {
			this.setY(barY);
		}
		if(this.getY() > barY+barH-height) {
			this.setY(barY+barH-height);
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		if(thisStep) {
			g.fillRect(barX, barY-Game.roller.getY()*Game.roller.step, barW, barH);
			g.setColor(Color.gray);
			g.fillRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		}else {
			g.fillRect(Game.WIDTH-getWidth(), 0, getWidth(), Game.HEIGHT);
			g.setColor(Color.gray);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
		
	}

}
