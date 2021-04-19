package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.Random;

import com.doglab.main.Game;

public class Entity {

	protected double x, y; 
	protected int width, height;
	protected BufferedImage sprite;
	protected double speed;
	protected double gravity = 0.3;
	
	protected int order = 0;
	
	protected int maskx, masky, maskw, maskh;
	
	protected int depth;
	public int inLocal;
	
	public static Random rand = new Random();
	
	public static Comparator<Entity> entitySorter = new Comparator<Entity>() {
		
		@Override
		public int compare(Entity n0, Entity n1) {
			if(n1.depth < n0.depth) {
				return + 1;
			}
			if(n1.depth > n0.depth) {
				return -1;
			}
			return 0;
		}
		
	};
	
	public Entity(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		this.speed = speed;
		this.maskx = 0;
		this.masky = 0;
		this.maskw = width;
		this.maskh = height;
	}
	
	public void setMask(int maskx, int masky, int maskw, int maskh) {
		this.maskx = maskx;
		this.masky = masky;
		this.maskw = maskw;
		this.maskh = maskh;
	}
	
	public static double calculoDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	}
	
	public void collision(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX(), this.getY(), width, height);
	}
	
	public boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.maskw, e1.maskh);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.maskw, e2.maskh);
		return e1Mask.intersects(e2Mask);
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY() -Game.roller.getY()*Game.roller.step, width, height, null);
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public BufferedImage getSprite() {
		return this.sprite;
	}
	
}
