package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Selector extends Label{

	private String[] options;
	protected int current = 0;
	protected boolean show = false;
	
	public Selector(double x, double y, String[] options) {
		super(x, y, 100, 30, 0, null);
		this.options = options;
	}

	public void tick() {
		super.tick();
		
		if(this.isColliding(Game.mouseController, this)) {
			Game.mouseController.resetPosition();
			this.show = !this.show;
		}
		
		if(show) {
			for(int i = 1; i < options.length+1; i++) {
				Entity e2 = new Entity(getX(), getY()+(getHeight()*i), getWidth(), getHeight(), 0, null);
				if(this.isColliding(Game.mouseController, e2)) {
					Game.mouseController.resetPosition();
					show = false;
					current = i-1;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setFont(Menu.curFont.deriveFont(20.0f));
		g.setColor(new Color(0xFFE8EDEB));
		if(!show) {
			super.render(g);
			g.setColor(Color.white);
			g.drawString(options[current], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[current])/2, getY() + 25);
		}else {
			for(int i = 0; i < options.length+1; i++) {
				if(i>0) {
					g.setColor(new Color(0xFF000000));
					g.fillRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
					g.setColor(Color.white);
					g.drawString(options[i-1], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[i-1])/2, getY() + (getHeight()*i) + 25);
				}else {
					g.setColor(new Color(0xFF101010));
					g.fillRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
				}
				g.setColor(new Color(0xFFE8EDEB));
				g.drawRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
			}
			g.setColor(Color.white);
			g.drawString(options[current], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[current])/2, getY() + 25);
		}
	}
	
	public String getSelection() {
		return this.options[current];
	}
	
}
