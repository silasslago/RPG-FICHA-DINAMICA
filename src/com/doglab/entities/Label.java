package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class Label extends Entity{

	protected ArrayList<Entity> labels; 
	protected EditButton edit;
	public boolean isEditing = false;
	
	public Label(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<Entity>();
		edit = new EditButton(getX()+getWidth()-10, 10, 25, 25, 0, Game.spr_entities.getSprite(76, 156, 25, 25));
	}
	
	public void tick() {
		if(this.isColliding(edit, Game.mouseController)) {
			isEditing = !isEditing;
		}
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.tick();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawImage(edit.getSprite(), edit.getX(), edit.getY(), null);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}

}
