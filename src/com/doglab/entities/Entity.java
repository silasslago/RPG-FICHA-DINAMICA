package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.doglab.world.Node;
import com.doglab.world.Vector2i;
import com.doglab.world.World;
import com.doglab.main.Game;
import com.doglab.world.Camera;

public class Entity {

	protected double x, y; 
	protected int width, height;
	protected BufferedImage sprite;
	protected double speed;
	protected double gravity = 0.3;
	
	protected List<Node> path;
	
	protected int maskx, masky, maskw, maskh;
	
	private int aStarDir;
	protected int depth;
	
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
	
	protected int getAStarDir() {
		return this.aStarDir;
	}
	
	public void collision(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(this.getX()-Camera.x, this.getY()-Camera.y, width, height);
	}
	
	public void followPath(List<Node> path) {
		if(path != null) {
			if(path.size() > 0) {
				Vector2i target = path.get(path.size() - 1).tile;
				if(this.getX() < target.x * 16) {
					x++;
					this.aStarDir = 0;
				}else if(this.getX() > target.x * 16) {
					x--;
					this.aStarDir = 1;
				}
				if(this.getY() < target.y * 16) {
					y++;
					this.aStarDir = 3;
				}else if(this.getY() > target.y * 16) {
					y--;
					this.aStarDir = 2;
				}
				
				if(this.getX() == target.x * 16 && this.getY() == target.y * 16) {
					path.remove(path.size() - 1);
				}
			}
		}
	}
	
	public boolean isColliding(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskx, e1.getY() + e1.masky, e1.width, e1.height);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskx, e2.getY() + e2.masky, e2.width, e2.height);
		return e1Mask.intersects(e2Mask);
	}
	
	public void updateCamera() {
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH / 2), 0, World.WIDTH * World.TILE_SIZE - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT / 2), 0, World.HEIGHT * World.TILE_SIZE - Game.HEIGHT);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX() - Camera.x, this.getY() - Camera.y, width, height, null);
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
