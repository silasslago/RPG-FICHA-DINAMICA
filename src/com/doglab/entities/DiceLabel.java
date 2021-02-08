package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class DiceLabel extends Label{

	private int currentTimerDestroy = 0;
	private int secondsDestroy = 3;
	private int maxTimeDestroy = secondsDestroy*60;
	
	private boolean animation = true;
	private int degress = 0;
	private int maxDegress = 360;
	private int times = 0;
	
	private int diceX = 0;
	private int diceY = 0;
	private int diceW = 0;
	private int diceH = 0;
	
	private int roll = 0;
	private String state = null;
	
	public DiceLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int random,
			String state) {
		super(x, y, width, height, speed, sprite);
		diceH = sprite.getHeight();
		diceW = sprite.getWidth();
		diceX = (int)(x+width/2-diceW/2);
		diceY = (int)(y+height/2-diceH/2);
		roll = random;
		this.state = state;
	}
	
	public void tick() {
		this.order = 2;
		currentTimerDestroy++;
		if(currentTimerDestroy > maxTimeDestroy) {
			Game.entities.remove(this);
		}
		if(animation) {
			degress+=50;
			times++;
			degress-=3.1*times;
			if(degress > maxDegress) {
				animation = false;
			}
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		if(animation) {
			AffineTransform old = g2D.getTransform();
			g2D.rotate(Math.toRadians(degress), diceX+diceW/2, diceY+diceH/2);
			g.drawImage(getSprite(), diceX, diceY, diceW, diceH, null);
			g2D.setTransform(old);
		}else {
			g.setColor(Color.WHITE);
			g.setFont(new Font("sitka banner", Font.BOLD, 50));
			g.drawString(Integer.toString(roll), diceX+20, diceY+30);
			g.setFont(new Font("sitka banner", Font.BOLD, 20));
			g.drawString(state, diceX, diceY+60);
		}
	}

}
