package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;


public class mouseController extends Entity{

	public boolean isPressed = false;
	public double xTarget, yTarget;
	public double currentX, currentY;
	
	public TextLabel currentTextLabel;
	public Roller currentRoller = null;
	
	public mouseController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		order = 3;
		if(isPressed) {
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
