package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class MouseEditorLabel extends Label{

	private int d;
	private CloseButton close;
	
	public MouseEditorLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int d,
			Dice dice) {
		super(x, y, width, height, speed, sprite);
		this.d = d;
		labels.add(dice);
		TextLabel currentDice = new TextLabel(this.getX()+145, this.getY()+85, 50, 50, 0, null, 
				new Font("sitka banner", Font.BOLD, 50), Color.WHITE, Integer.toString(d), 0);
		int widthB = 25;
		int heightB = 25;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this);
		this.labels.add(currentDice);
		changeTickers();
	}
	
	public void tick() {
		order = 2;
		for(int i = 0; i < labels.size(); i++) {
			Entity l = labels.get(i);
			if(l instanceof TextLabel){
				((Label) l).edit.isEditing = true;
				if(((TextLabel) l).text != "") {
					d = Integer.parseInt(((TextLabel) l).text);
				}
			}
		}
		for(int i = 0; i < labels.size(); i++) {
			Entity l = labels.get(i);
			l.tick();
		}
		close.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setFont(new Font("sitka banner", Font.BOLD, 20));
		g.drawString("Dice Editor", getX()+130, getY()+20);
		g.drawLine(getX(), getY()+30, getX()+getWidth(), getY()+30);
		for(int i = 0; i < labels.size(); i++) {
			labels.get(i).render(g);
		}
		g.drawString("D", getX()+110, getY()+90);
		close.render(g);
	}
	
	public int returnD() {
		return this.d;
	}

}
