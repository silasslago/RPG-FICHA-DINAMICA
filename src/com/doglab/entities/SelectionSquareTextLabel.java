package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Collections;

import com.doglab.main.Game;

public class SelectionSquareTextLabel extends Label{
	
	private Color color;
	private int plusY;
	private int id;
	
	public SelectionSquareTextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			int id) {
		super(x, y, width, height, speed, sprite);
		this.id = id;
		plusY = getHeight()/2+21;
		SquareTextLabel sTL = new SquareTextLabel(0,0,0,0,0,null,0,"");
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Skills) {
				sTL = ((Skills) e).getSkills().get(id);
			}
		}
		String text0 = ((TextLabel)sTL.labels.get(0)).text;
		String text1 = ((TextLabel)sTL.labels.get(1)).text;
		TextLabel valueL = new TextLabel(getX()+getWidth()/2-7, getY()+getHeight()/2+21, 
				text0.length()*14, 22, 0, null, 
				new Font("sitka banner", Font.BOLD, 39), 
				new Color(0xFFE8EDEB), text0, 1, true);
		labels.add(valueL);
		TextLabel textL = new TextLabel((getX()+getWidth()/2)-text1.length()*4, getY()+getHeight()/2+46, 
				text1.length()*8, 11, 0, null, 
				new Font("sitka banner", Font.BOLD, 17), new Color(0xFFE8EDEB), text1, 1, false);
		labels.add(textL);
	}

	private void superTick() {
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.tick();
		}
	}
	
	public void tick() {
		superTick();
		if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()) &&
				(Game.mouseController.currentX < this.getX()+this.getWidth() && 
						Game.mouseController.currentY < this.getY()+this.getHeight())) {
			if(!current) {
				current = true;
			}
		}else {
			if(current) {
				current = false;
			}
		}
		if(current) {
			color = new Color(0xFF121212);
		}else {
			color = new Color(0xFFF0F0F0);
		}
		
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			if(e instanceof TextLabel) {
				((TextLabel)e).setColor(color);
			}
		}
		int plusY = 0;
		for(int i = 0; i < labels.size(); i++) {
			Entity l = labels.get(i);
			if(l instanceof TextLabel) {
				if(i == 0) {
					plusY = getY()+this.plusY+inLocal+Game.roller.getY()*Game.roller.step;
					if(plusY != l.getY()-inLocal) {
						l.setY(plusY-this.plusY/2+2+10);
						((TextLabel) l).setImaginaryY(plusY);
					}
				}else {
					plusY = getY()+this.plusY+inLocal+25+Game.roller.getY()*Game.roller.step;
					if(plusY != l.getY()-inLocal) {
						l.setY(plusY-this.plusY/2+12+12);
						((TextLabel) l).setImaginaryY(plusY);
					}
				}
			}
		}	
		
		if(this.isColliding(this, Game.mouseController)) {
			Game.mouseController.resetPosition();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof FastSkillsLabel) {
					((FastSkillsLabel) e).createLabel(id);
				}else if(e instanceof FastSkillsChooserLabel) {
					for(int ii = 0; ii < ((FastSkillsChooserLabel) e).labels.size(); ii++) {
						Entity ee = ((FastSkillsChooserLabel) e).labels.get(ii);
						if(ee instanceof CloseButton) {
							((CloseButton) ee).actionPerformed();
						}
					}
				}
			}
		}
		
	}
	
	public void render(Graphics g) {
		if(current) {
			g.setColor(new Color(0xFFE0E0E0));
		}else {
			g.setColor(new Color(0xFF000000));
		}
		g.fillRect(getX()+inLocal, getY()+inLocal+20, getWidth()-inLocal*2, getHeight()-inLocal*2-20);
		g.setColor(new Color(0xFF424242));
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		if(current) {
			g.setColor(new Color(0xFF121212));
		}else {
			g.setColor(new Color(0xFFF0F0F0));
		}
		g.fillRect(getX()+getWidth()/2-5 , getY()+inLocal/2-5+getHeight()/3, 8, 8);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+getHeight()/2+31, getX()-inLocal+getWidth()-15, getY()+getHeight()/2+31);
	}

}