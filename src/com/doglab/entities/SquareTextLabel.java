package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class SquareTextLabel extends Label{

	public Dice dice;
	private Color color;
	private int plusY;
	
	public SquareTextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			int value, String text) {
		super(x, y, width, height, speed, sprite);
		
		plusY = getHeight()/2+21;
		
		TextLabel valueL = new TextLabel(getX()+getWidth()/2-7, getY()+getHeight()/2+21, 
				Integer.toString(value).length()*14, 22, 0, null, 
				new Font("sitka banner", Font.BOLD, 39), 
				new Color(0xFFE8EDEB), Integer.toString(value), 1, true);
		
		labels.add(valueL);
		
		TextLabel textL = new TextLabel((getX()+getWidth()/2)-text.length()*4, getY()+getHeight()/2+46, 
				text.length()*8, 11, 0, null, 
				new Font("sitka banner", Font.BOLD, 17), new Color(0xFFE8EDEB), text, 1, false);
		
		labels.add(textL);
		
		TextLabel rollTimes = new TextLabel(0,0,0,0,0,null,new Font("sitka banner", Font.BOLD, 17), null, "1", 0, true);
		TextLabel dValue = new TextLabel(0,0,0,0,0,null,new Font("sitka banner", Font.BOLD, 17), null, "20", 0, true);
		dice = new Dice(getX()-20, getY(), getWidth()+35, getHeight()-20, 0, 
				Game.spr_entities.getSprite(0, 156, 76, 71), dValue, valueL, rollTimes, 
				true, true, textL);
	}

	public void tick() {
		super.tick();
		if(tick) {
			
			if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()-Game.roller.getY()*Game.roller.step) &&
					(Game.mouseController.currentX < this.getX()+this.getWidth() && 
							Game.mouseController.currentY < this.getY()-Game.roller.getY()*Game.roller.step+this.getHeight())) {
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
			
			dice.tick();
			
			for(int i = 0; i < labels.size(); i++) {
				Entity l = labels.get(i);
				if(l instanceof TextLabel) {
					int plusY = 0;
					if(i == 0) {
						plusY = getY()+this.plusY+inLocal;
						if(plusY != l.getY()-inLocal) {
							l.setY(plusY-this.plusY/2+2+10);
							((TextLabel) l).setImaginaryY(plusY);
						}
					}else {
						plusY = getY()+this.plusY+inLocal+25;
						if(plusY != l.getY()-inLocal) {
							l.setY(plusY-this.plusY/2+12+12);
							((TextLabel) l).setImaginaryY(plusY);
						}
					}
					
				}
			}
			
			int plusY = getY()+inLocal;
			if(plusY != dice.getY()-inLocal) {
				dice.setY(plusY+13);
				dice.masky = plusY+13;
			}
			for(int i = 0; i < labels.size(); i++) {
				Entity l = labels.get(i);
				if(l instanceof TextLabel) {
					l.setX(getX()+getWidth()/2 - l.getWidth()/2);
					((TextLabel) l).initX = l.getX();
				}
			}
			dice.setX(getX()-20);
			dice.maskx = dice.getX();
		}
	}
	
	public void render(Graphics g) {
		if(current) {
			g.setColor(new Color(0xFFE0E0E0));
		}else {
			g.setColor(Color.BLACK);
		}
		g.fillRect(getX()+inLocal, getY()+inLocal+20-Game.roller.getY()*Game.roller.step, getWidth()-inLocal*2, getHeight()-inLocal*2-20);
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
		g.fillRect(getX()+getWidth()/2-5 , getY()+inLocal/2-5+getHeight()/3 - Game.roller.getY()*Game.roller.step, 8, 8);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+getHeight()/2+31 - Game.roller.getY()*Game.roller.step, getX()-inLocal+getWidth()-15, getY()+getHeight()/2+31  - Game.roller.getY()*Game.roller.step);
	}

}
