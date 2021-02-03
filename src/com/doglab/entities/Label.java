package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.doglab.main.Game;

public class Label extends Entity{

	protected ArrayList<Entity> labels; 
	protected EditButton edit;
	protected int size = 30;
	private boolean light = false;
	protected boolean current = false;
	
	protected static Comparator<Entity> labelSorter = new Comparator<Entity>() {

		@Override
		public int compare(Entity o1, Entity o2) {
			
			if(o1.order < o2.order) {
				return -1;
			}
			if(o1.order > o2.order) {
				return +1;
			}
			
			return 0;
		}
		
	};
	
	public Label(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<Entity>();
		edit = new EditButton(getX()+getWidth()-35, getY()+10, 25, 25, 0, Game.spr_entities.getSprite(76, 156, 25, 25));
	}
	
	public void tick() {
		if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()) &&
				(Game.mouseController.currentX < this.getX()+this.getWidth() && 
						Game.mouseController.currentY < this.getY()+this.getHeight())) {
			light = true;
			if(!current) {
				double editSz = 1.5;
				this.x-=size;
				this.y-=size;
				this.width+=size*2;
				this.height+=size*2;
				current = true;
				edit.setWidth((int)(edit.getWidth()*editSz));
				edit.setHeight((int)(edit.getHeight()*editSz));
				edit.setX((int)(getX()+getWidth()-35*editSz));
				edit.setY((int)(getY()+10*editSz));
				this.order = 1;
				Collections.sort(Game.entities, labelSorter);
			}
		}else {
			if(current) {
				this.order = 0;
				Collections.sort(Game.entities, labelSorter);
				double editSz = 1.5;
				this.x+=size;
				this.y+=size;
				this.width-=size*2;
				this.height-=size*2;
				current = false;
				edit.setWidth((int)(edit.getWidth()/editSz));
				edit.setHeight((int)(edit.getHeight()/editSz));
				edit.setX((int)(getX()+getWidth()-35));
				edit.setY((int)(getY()+10/editSz));
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
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		if(light) {
			
		}
		if(!edit.isEditing) {
			g.setColor(new Color(0xFF424242));
		}else {
			g.setColor(Color.WHITE);
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawImage(edit.getSprite(), edit.getX(), edit.getY(), edit.getWidth(), edit.getHeight(), null);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}

}
