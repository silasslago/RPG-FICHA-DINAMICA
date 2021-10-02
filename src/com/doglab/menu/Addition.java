package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Addition extends Label{

	public Addition(double x, double y, int width, int height) {
		super(x, y, width, height, 0, null);	
	}
	
	public void tick() {
		if(!Game.online) {
			super.tick();
			Entity e = new Entity(Game.mouseController.getX(),
					Game.mouseController.getY()+Game.files.roller.getY()*Game.files.roller.step,
					5, 5, 0, null);
			if(this.isColliding(this, e) && Label.tick) { // Adição de nova pasta/personagem
				Game.mouseController.resetPosition();
				int wLabel = 350;
				int hLabel = 150;
				int xLabel = ((Game.WIDTH*Game.SCALE)/2)-wLabel/2;
				int yLabel = ((Game.HEIGHT*Game.SCALE)/2)-hLabel/2;
				CreationMenu cm = new CreationMenu(xLabel, yLabel, wLabel, hLabel);
				Game.files.cm.clear();
				Game.files.cm.add(cm);
				System.out.println(Game.files.cm.size());
			}
		}
	}
	
	public void render(Graphics g) {
		// Super
		g.setColor(new Color(0xFF151515));
		g.fillRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFFFF4246));
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		// ------------------------
		g.setFont(Menu.curFont.deriveFont(100.0f));
		int xx = g.getFontMetrics().stringWidth("+");
		g.setColor(new Color(0xFFE8EDEB));
		g.drawString("+", getX()+getWidth()/2 - xx/2, getY()+getHeight()/2 + xx/2 -Game.files.roller.getY()*Game.files.roller.step);
	}

}
