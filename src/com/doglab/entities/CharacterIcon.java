package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class CharacterIcon extends Entity{

	private BufferedImage barrier;
	private int barrierX, barrierY;
	private int iconX, iconY;
	private BufferedImage camera;
	
	public CharacterIcon(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		barrierX = (int)(x-77);
		barrierY = (int)(y-77);
		iconX = (int)(x-45);
		iconY = (int)(y-65);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(camera, this.getX(), this.getY()-15, null);
		g.drawImage(Game.player.icon, iconX, iconY, 130, 130, null);
		g.drawImage(barrier, barrierX, barrierY, 196, 156, null);
	}

}
