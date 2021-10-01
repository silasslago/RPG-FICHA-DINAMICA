package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class SkillLabel extends Label{

	private String skillName;
	private Dice dice;
	private Color color;
	private int id;
	
	private ArrayList<TextLabel> toDice;
	
	private DeleteButton deleteB = new DeleteButton(0,0,0,0,0,null,0) {
		
		public void labelSorter(ArrayList<SkillLabel> list, int beginning, int iX) {
			for(int i = beginning; i < list.size(); i++) {
				Label l = list.get(i);
				if(l.getY() > minY) {
					l.setY(l.getY()-l.height-10);
				}
			}
		}
		
		public void actionPerformed() {
			Game.mouseController.resetPosition();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof FastSkillsLabel) {
					for(int ii = 0; ii < ((FastSkillsLabel) e).getSkillArrayList().size(); ii++) {
						SkillLabel l = ((FastSkillsLabel) e).getSkillArrayList().get(ii);
						if(l.deleteB.equals(this)){
							int id = 0;
							id = ((FastSkillsLabel)e).getSkillArrayList().indexOf(l);
							((FastSkillsLabel)e).getSkillArrayList().remove(l);
							((FastSkillsLabel)e).addB.labelsAmount--;
							labelSorter(((FastSkillsLabel)e).getSkillArrayList(), id, e.getX());
						}
					}
					return;
				}
			}
		}
	};
	private int plusY;
	
	public SkillLabel(double x, double y, int width, int height, double speed, BufferedImage sprite,
			int id, int minY) {
		super(x, y, width, height, speed, sprite);
		this.id = id;
		SquareTextLabel sTL = new SquareTextLabel(0,0,0,0,0,null,0,"");
		toDice = new ArrayList<TextLabel>();
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Skills) {
				sTL = ((Skills) e).squares.get(id);
				skillName = ((TextLabel) sTL.labels.get(1)).text;
				toDice.add((TextLabel)((Skills) e).squares.get(id).labels.get(1));
				i = 99999;
			}
		}
		TextLabel tNome = new TextLabel(getX()+getWidth()/2-(8*skillName.length())/2, getY()+16, (7*skillName.length()), 14, 0, null, new Font("sitka banner", 
				Font.BOLD, 16), new Color(0xFFE8EDEB) ,skillName, 1, false);
		labels.add(tNome);
		dice = new Dice(getX(), getY(), getWidth(), getHeight(), 0, 
				Game.spr_entities.getSprite(0, 156, 76, 71), (TextLabel)sTL.dice.labels.get(1), 
				(TextLabel)sTL.dice.labels.get(0), (TextLabel)sTL.dice.labels.get(2), 
				true, true, tNome);
	
		deleteB.setX(getX()+getWidth()-21);
		deleteB.setY(getY()+5);
		deleteB.setWidth(16);
		deleteB.setHeight(16);
		deleteB.speed = 0;
		deleteB.setSprite(Game.spr_entities.getSprite(76, 181, 25, 25));
		deleteB.minY = minY;
		labels.add(deleteB);
	}
	
	public void tick() {
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
			for(int i = 0; i < this.labels.size(); i++) {
				this.labels.get(i).tick();
			}
			dice.tick();
			TextLabel tL = this.toDice.get(0);
			((TextLabel)this.labels.get(0)).text = tL.text;
			
			int plusY = 0;
			this.plusY = 18;
			for(int i = 0; i < labels.size(); i++) {
				Entity l = labels.get(i);
				if(l instanceof TextLabel) {
					if(i == 0) {
						plusY = getY()+this.plusY+inLocal;
						if(plusY != l.getY()-inLocal) {
							l.setY(plusY-this.plusY/2-3);
							((TextLabel) l).setImaginaryY(plusY);
						}
					}
				}else if(l instanceof DeleteButton) {
					plusY = getY()+this.plusY+inLocal;
					if(plusY != l.getY()-inLocal) {
						l.setY(plusY-this.plusY/2-4);
					}
				}
			}
			this.dice.setX(getX());
			this.dice.setY(getY());
			this.dice.setMask(getX(), getY(), getWidth(), getHeight());
		}
	}
	
	public void render(Graphics g) {
		if(current) {
			g.setColor(new Color(0xFFE0E0E0));
		}else {
			g.setColor(new Color(0xFF000000));
		}
		g.fillRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		if(current) {
			g.setColor(new Color(0xFF000000));
		}else {
			g.setColor(new Color(0xFFE0E0E0));
		}
		g.drawRect(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight());
		for(int i = 0; i < this.labels.size(); i++) {
			this.labels.get(i).render(g);
		}
	}
	
	public int getID() {
		return this.id;
	}

}
