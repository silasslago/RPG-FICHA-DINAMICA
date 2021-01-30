package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class IconLabel extends Label{

	private CharacterIcon characterIcon;
	private Dice dice;
	
	public IconLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		characterIcon = new CharacterIcon(415, 160, 1, 1, 0, null);
		dice = new Dice(510, 125, 76, 71 ,0, Game.spr_entities.getSprite(0, 156, 76, 71));
		labels.add(dice);
		labels.add(characterIcon);
	}
	
	public void tick() {
		
	}

	public void render(Graphics g) {
		super.render(g);
		int textY = 165;
		textY+=30;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
	}
	
}
