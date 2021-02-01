package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class Label extends Entity{

	protected ArrayList<Entity> labels; 
	protected EditButton edit;
	private boolean current = false;
	
	public Label(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<Entity>();
		edit = new EditButton(getX()+getWidth()-25-10, getY()+10, 25, 25, 0, Game.spr_entities.getSprite(76, 156, 25, 25));
	}
	
	public void tick() {
		int size = 30;
		if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()) &&
				(Game.mouseController.currentX < this.getX()+this.getWidth() && 
						Game.mouseController.currentY < this.getY()+this.getHeight())) {
			if(!current) {
				this.x-=size;
				this.y-=size;
				this.width+=size*2;
				this.height+=size*2;
				current = true;
			}
		}else {
			if(current) {
				this.x+=size;
				this.y+=size;
				this.width-=size*2;
				this.height-=size*2;
				current = false;
			}
		}
		
		if(this.isColliding(edit, Game.mouseController)) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Label) {
					if(e != this) {
						((Label) e).edit.isEditing = false;
					}
				}
			}
			Game.mouseController.resetPosition();
			edit.actionPerformed();
			for(int i = 0; i < labels.size(); i++) {
				Entity e = labels.get(i);
				if(e instanceof TextLabel) {
					((TextLabel) e).edit.actionPerformed();
				}
			}
		}
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.tick();
		}
	}
	
	public void render(Graphics g) {
		if(!edit.isEditing) {
			g.setColor(new Color(0xFF424242));
		}else {
			g.setColor(Color.WHITE);
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawImage(edit.getSprite(), edit.getX(), edit.getY(), null);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}

}
