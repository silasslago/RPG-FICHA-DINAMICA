package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class FastSkillsLabel extends Label{

	private int inLocal = 0;
	private ArrayList<SkillLabel> pericias;
	private boolean showMsg = false;
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
	
		private ArrayList<SkillLabel> labels = new ArrayList<SkillLabel>();

		public void actionPerformed(){
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof FastSkillsLabel) {
					labels = ((FastSkillsLabel) e).getSkillArrayList();
					SkillLabel skillLabel = new SkillLabel(labelX, labelY+5+(labelH*labelsAmount),
							labelW, labelH-5, 0, null);
					labelsAmount++;
					labels.add(skillLabel);
					((FastSkillsLabel) e).setSkillArrayList(labels);
					return;
				}
			}
		}
	};
	
	public FastSkillsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		pericias = new ArrayList<SkillLabel>();
		TextLabel pericias = new TextLabel(getX()+120, getY()+25, 70, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Perícias", 1, false);
		TextLabel fastAcess = new TextLabel(getX()+118, getY()+40, 75, 10, 0, null, new Font("sitka banner", Font.BOLD, 12), 
				new Color(0xFF424242), "(acesso rápido)", 1, false);
		
		addB.setX(getX()+getWidth()-35);
		addB.setY(getY()+10);
		addB.setWidth(25);
		addB.setHeight(25);
		addB.setSprite(Game.spr_entities.getSprite(76, 206, 25, 25));
		addB.labelX = getX()+15;
		addB.labelY = getY()+50;
		addB.labelW = getWidth()-30;
		addB.labelH = 30;
		addB.speed = 0;
		
		//labels.add(addB);
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
			}else {
				showMsg = false;
			}
			
			if(this.pericias.size() < 12) {
				int plusH = 0;
				for(int i = 0; i < this.pericias.size(); i++) {
					plusH+=this.pericias.get(i).getHeight()+5;
				}
				this.setHeight(100+inLocal*2+plusH);
			}

			for(int i = 0; i < this.pericias.size(); i++) {
				pericias.get(i).tick();
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
		for(int i = 0; i < this.pericias.size(); i++) {
			pericias.get(i).render(g);
		}
	}
	
	public ArrayList<SkillLabel> getSkillArrayList() {
		return this.pericias;
	}
	
	public void setSkillArrayList(ArrayList<SkillLabel> pericias) {
		this.pericias = pericias;
	}

}
