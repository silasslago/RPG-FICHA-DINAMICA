package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class TextLabel extends Entity{

	private int imaginaryY;
	public Font font;
	protected Color color;
	public String text;
	public boolean writing = false;
	public int typeText;
	
	private int timer = 0;
	private int maxTimer = 30;
	private boolean show = true;
	
	public TextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, Font font, 
			Color color, String text, int typeText) {
		super(x, y-font.getSize(), width, height, speed, sprite);
		this.font = font;
		this.color = color;
		this.text = text;
		this.imaginaryY = (int)y;
		this.typeText = typeText;
		if(font.getSize() == 51) {
			this.y = 580;
		}else if(font.getSize() == 31) {
			if(y == 533) {
				this.y = 510;
			}else if(y == 423) {
				this.y= 400;
			}else if(y == 313){
				this.y= 290;
			}
		}
	}

	public void beginToWrite() {
		writing = true;
		if(text.equals("")) {
			return;
		}
		text = "";
		if(typeText == 1) {
			int newX = this.getX() + this.getWidth()/2;
			setX(newX);
		}
		width = 10;
	}
	
	public void tick() {
		if(writing) {
			timer++;
			if(timer > this.maxTimer) {
				timer = 0;
				show = !show;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(font);
		g.drawString(text, getX(), imaginaryY);
		if(writing && show) {
			g.setColor(Color.white);
			g.drawLine(getX()+width, getY(), getX()+width, getY()+height);
		}
		if(text.equals("")) {
			g.setColor(Color.red);
			g.drawRect(getX(), getY(), width, height);
		}
	}
	
}
