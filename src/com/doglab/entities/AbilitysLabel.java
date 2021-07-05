package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class AbilitysLabel extends Label{

	public AbilitysLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel habilidades = new TextLabel(getX()+270, getY()+38, 90, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "HABILIDADES", 1, false);
		labels.add(habilidades);
		
		TextLabel desc = new TextLabel(getX()+20, getY()+80, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Sem habilidades", 0, false);
		desc.textBox(true, 67, 850, getWidth()-20);
		desc.canClick(true);
		labels.add(desc);
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+15, getY()-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()-80+getHeight()-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+getHeight()-80+inLocal+55-Game.roller.getY()*Game.roller.step);
	}
	
}
