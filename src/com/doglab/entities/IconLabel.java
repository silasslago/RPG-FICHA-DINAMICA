package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class IconLabel extends Label{

	public CharacterIcon characterIcon;
	private int degress = 0;
	
	public IconLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		characterIcon = new CharacterIcon(getX()+70, getY()+80, 1, 1, 0, null);
		labels.add(characterIcon);
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			
		}
		degress++;
	}

	public void render(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(getX()-20, getY()- Game.roller.getY()*Game.roller.step, 350, 460);
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX()-20, getY()- Game.roller.getY()*Game.roller.step, 350, 460);
		
		Graphics2D g2D = (Graphics2D)g;
		AffineTransform old = g2D.getTransform();
		if(current) {
			g2D.rotate(Math.toRadians(degress),  getX()+205+76/2+inLocal, getY()+inLocal+15+getY()/2+71/2 - Game.roller.getY()*Game.roller.step);
			g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), getX()+205+inLocal, getY()+inLocal+15+getY()/2 - Game.roller.getY()*Game.roller.step, 
					76, 71 , null);
		}else {
			g2D.rotate(Math.toRadians(degress),  getX()+205+76/2+inLocal, getY()+inLocal+getY()/2+71/2 - Game.roller.getY()*Game.roller.step);
			g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), getX()+205+inLocal, getY()+inLocal+getY()/2 - Game.roller.getY()*Game.roller.step, 
					76, 71 , null);
		}
		g2D.setTransform(old);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}
	
}
