package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class OptionsLabel extends Label{

	private boolean show = false;
	private BufferedImage closed;
	public boolean saving, saved;
	private int timerSaved = 0, maxTimerSaved = 120;

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
		
		TextLabel dAmount = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0, true);
		TextLabel dLados = new TextLabel(getX()+getWidth()-54, getY()+80, 38, 18, 0, null, new Font("arial", Font.BOLD, 20), 
				new Color(0xFFE8EDEB), "100", 0, true);
		TextLabel nullificator = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0, true);
		Dice dice = new Dice(getX()+getWidth()-54-65, getY()+49, 45, 42, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dLados, nullificator, dAmount, false, false);
		labels.add(dice);
		labels.add(dLados);
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
		
		for(int i = 0; i < labels.size(); i++) {
			Entity e1 = labels.get(i);
			if(e1 instanceof Dice) {
				e1.setY(getY()+3 + Game.roller.getY()*Game.roller.step);
				e1.setMask(e1.getX(), e1.getY(), e1.getWidth(), e1.getHeight());
			}else if(e1 instanceof TextLabel) {
				e1.setY(getY()+18 + Game.roller.getY()*Game.roller.step);
				((TextLabel) e1).setImaginaryY(getY()+35 + Game.roller.getY()*Game.roller.step);
			}
		}
		
		if(saved) {
			this.timerSaved++;
			if(timerSaved > this.maxTimerSaved) {
				this.saving = false;
				this.saved = false;
				timerSaved = 0;
			}
		}

	}
	
	public void render(Graphics g) {
		if(show) {
			g.setFont(new Font("sitka banner", Font.BOLD, 27));
			g.setColor(new Color(0xFF000000));
			g.fillRect(getX(), getY(), getWidth(), getHeight());
			g.setColor(new Color(0xFFE8EDEB));
			g.drawLine(getX(), 45, getWidth(), 45);
			
			g.drawString("d", getX()+getWidth()-54-15, getY()+35);
			for(int i = 0; i < labels.size(); i++) {
				labels.get(i).render(g);
			}
			g.drawImage(getSprite(), getX()+getWidth()/2-12, getY()+getHeight(), 25, 25, null);
			g.setFont(new Font("sitka banner", Font.BOLD, 20));
			if(saving) {
				g.setColor(Color.red);
				g.drawString("Saving...", 10, 10);
			}else if(saved) {
				g.setColor(Color.green);
				g.drawString("Saved!", 20, 30);
			}
		}else {
			g.drawImage(closed, getX()+getWidth()/2-12, getY()+getHeight(), 25, 25, null);
		}
	}

}
