package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class FastSkillsLabel extends Label{

	private int inLocal = 0;
	private ArrayList<Label> pericias;
	private boolean showMsg = false;
	
	
	public FastSkillsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		pericias = new ArrayList<Label>();
		TextLabel pericias = new TextLabel(getX()+120, getY()+25, 70, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Perícias", 1);
		TextLabel fastAcess = new TextLabel(getX()+118, getY()+40, 75, 10, 0, null, new Font("sitka banner", Font.BOLD, 12), 
				new Color(0xFF424242), "(acesso rápido)", 1);
		
		labels.add(pericias);
		labels.add(fastAcess);
		
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			if(current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}
			if(pericias.size() <= 0) {
				showMsg = true;
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.setFont(new Font("sitka banner", Font.BOLD, 12));
		g.drawLine(getX()+35+inLocal, getY()+50+inLocal-Game.roller.getY()*Game.roller.step, getX()+getWidth()-35-inLocal, getY()+50+inLocal-Game.roller.getY()*Game.roller.step);
		if(showMsg) {
			g.drawString("Nada adicionado no acesso rápido", getX()+70+inLocal, getY()+70+inLocal-Game.roller.getY()*Game.roller.step);
		}
	}

}
