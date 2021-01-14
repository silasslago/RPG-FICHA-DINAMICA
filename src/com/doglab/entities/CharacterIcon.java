package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class CharacterIcon extends Entity{

	private BufferedImage barrier;
	private int barrierX, barrierY;
	private int iconX, iconY;
	
	public CharacterIcon(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		barrierX = (int)(x-32);
		barrierY = (int)(y-12);
		iconX = (int)(x-65);
		iconY = (int)(y-65);
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(barrier, barrierX, barrierY, null);
		g.drawImage(Game.player.icon, iconX, iconY, 130, 130, null);
	}

}
