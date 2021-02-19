package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class Skills extends Label{

	public Skills(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel pericias = new TextLabel(getX()+290, getY()+30, 70, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Perícias", 1);
		labels.add(pericias);
	}
	
	public void tick() {
		super.tick();
		if(current) {
			inLocal = this.size;
		}else {
			inLocal = 0;
		}
		
	}
	
	public void render(Graphics g){
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
	}

}
