package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class OptionsLabel extends Label{

	private boolean show = false;
	private BufferedImage closed;

	public OptionsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		SaveButton saveButton = new SaveButton(Game.WIDTH/2-40, 10, 25, 25, 0, Game.spr_entities.getSprite(76, 231, 25, 25));
		labels.add(saveButton);
		ShowButton showButton = new ShowButton(Game.WIDTH/2-5, 10, 25, 25, 0, 
				Game.spr_entities.getSprite(51, 231, 25, 25));
		labels.add(showButton);
		EditButton editButton = new EditButton(Game.WIDTH/2+30, 10, 25, 25, 0, 
				Game.spr_entities.getSprite(76, 156, 25, 25));
		labels.add(editButton);
		closed = Game.spr_entities.getSprite(1, 231, 25, 25);
	}
	
	public void tick() {
		order = 10;
		for(int i = 0; i < labels.size(); i++) {
			if(show) {
				labels.get(i).tick();
			}
		}
		Entity e = new Entity(getX()+getWidth()/2-12, getY()+getHeight(), 25, 25, 0, null);
		if(this.isColliding(Game.mouseController, e)) {
			Game.mouseController.resetPosition();
			this.setSprite(getSprite());
			if(this.getY() == 0) {
				this.setY(0-getHeight());
				show = false;
			}else {
				this.setY(0);
				show = true;
			}
		}
	}
	
	public void render(Graphics g) {
		if(show) {
			g.setColor(new Color(0xFF000000));
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(new Color(0xFFE8EDEB));
			g.drawLine(getX(), 45, getWidth(), 45);
			for(int i = 0; i < labels.size(); i++) {
				labels.get(i).render(g);
			}
			g.drawImage(getSprite(), getX()+getWidth()/2-12, getY()+getHeight(), 25, 25, null);
		}else {
			g.drawImage(closed, getX()+getWidth()/2-12, getY()+getHeight(), 25, 25, null);
		}
		
	}

}
