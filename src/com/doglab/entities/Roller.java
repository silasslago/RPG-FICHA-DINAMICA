package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Roller extends Entity{

	public int step;
	private boolean thisStep;
	private int barX, barY, barW, barH;
	public boolean selected = false;
	
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
		
		if(Game.mouseController.currentRoller == null) {
			selected = false;
			if(this.isColliding(this, new Entity(Game.mouseController.currentX, 
					Game.mouseController.currentY+Game.roller.getY()*Game.roller.step, Game.mouseController.width, Game.mouseController.height, 0, null))) {
				selected = true;
				Game.mouseController.currentRoller = this;
			}
			if(this.isColliding(this, new Entity(Game.mouseController.currentX, Game.mouseController.currentY, 
					Game.mouseController.width, Game.mouseController.height, 0, null))) {
				selected = true;
				Game.mouseController.currentRoller = this;
			}	
		}
		
		if(Game.mouseController.isPressed) {
			if(thisStep) {
				if(selected) {
					int dif = (int)Game.mouseController.currentY+Game.roller.getY()*Game.roller.step - height/2;
					this.setY(dif);
				}
			}else {
				if(selected) {
					int dif = (int)Game.mouseController.currentY - height/2;
					this.setY(dif);
				}
			}
		}else {
			Game.mouseController.currentRoller = null;
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
			g.setColor(Color.lightGray);
			g.fillRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		}else {
			g.fillRect(barX, barY, barW, barH);
			g.setColor(Color.lightGray);
			g.fillRect(getX(), getY(), getWidth(), getHeight());
		}
	}
	
	public void setBars(int barx, int bary, int barw, int barh) {
		this.barX = barx;
		this.barY = bary;
		this.barW = barw;
		this.barH = barh;
		this.height = barh;
		this.width = barw;
	}
	
}
