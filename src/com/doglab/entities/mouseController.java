package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class mouseController extends Entity{

	public boolean isPressed = false;
	public double xTarget, yTarget;
	public double currentX, currentY;
	
	public TextLabel currentTextLabel;
	
	public mouseController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		order = 3;
		if(isPressed) {
			if(Menu.showReadme) {
				Menu.showReadme = false;
				this.resetPosition();
				for(int i = 0; i < Game.entities.size(); i++) {
					Entity e = Game.entities.get(i);
					if(e instanceof ReadmeLabel) {
						Game.entities.remove(e);
					}
				}
			}
			this.x = (int)xTarget;
			this.y = (int)yTarget;
		}
	}
	
	public void render(Graphics g) {
		/*
		g.setColor(Color.blue);
		g.fillRect(getX(), getY(), width, height);
		g.fillRect((int)currentX, (int)currentY, width, height);
		*/
	}

	public void resetPosition() {
		this.x = 0-width;
		this.y = 0-height;
		this.xTarget = x;
		this.yTarget = y;
		isPressed = false;
	}
	
}
