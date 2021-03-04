package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class MouseEditorLabel extends Label{

	private String d;
	private CloseButton close;
	private TextLabel currentDice;
	
	public MouseEditorLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, String d,
			Dice dice) {
		super(x, y, width, height, speed, sprite);
		this.d = d;
		labels.add(dice);
		currentDice = new TextLabel(this.getX()+145, this.getY()+85, 50, 50, 0, null, 
				new Font("sitka banner", Font.BOLD, 50), Color.WHITE, d, 0, true);
		int widthB = 25;
		int heightB = 25;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this);
		this.labels.add(currentDice);
		changeTickers();
	}
	
	public void tick() {
		for(int i = 0; i < labels.size(); i++) {
			Entity l = labels.get(i);
			l.tick();
		}
		if(!currentDice.text.equals("")) {
			this.d = currentDice.text;
		}
		for(int i = 0; i < labels.size(); i++) {
			Entity e1 = labels.get(i);
			if(e1 instanceof TextLabel) {
				e1.setY(this.getY()+48 + Game.roller.getY()*Game.roller.step);
				((TextLabel) e1).setImaginaryY(this.getY()+85 + Game.roller.getY()*Game.roller.step);
			}
		}
		
		close.tick();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			if(e instanceof Dice) {
				e.render(g);
			}
		}
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setFont(new Font("sitka banner", Font.BOLD, 20));
		g.drawString("Dice Editor", getX()+130, getY()+20);
		g.setFont(new Font("sitka banner", Font.BOLD, 50));
		g.drawString("D", getX()+110, getY()+90);
		g.drawLine(getX(), getY()+30, getX()+getWidth(), getY()+30);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			if(e instanceof TextLabel) {
				e.render(g);
			}
		}
		close.render(g);
	}
	
	public String returnD() {
		return this.d;
	}

}
