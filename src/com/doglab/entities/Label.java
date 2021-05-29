package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import com.doglab.main.Game;

public class Label extends Entity{

	public ArrayList<Entity> labels; 
	private BufferedImage lightImage;
	protected boolean current = false;
	public static boolean tick = true;
	
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
		lightImage = Game.spr_entities.getSprite(101, 156, 35, 36);
	}
	
	public void tick() {
		if(tick) {
			for(int i = 0; i < labels.size(); i++) {
				Entity e = labels.get(i);
				e.tick();
			}
		}
	}
	
	public void changeTickers() {
		Label.tick = !tick;	
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}

}
