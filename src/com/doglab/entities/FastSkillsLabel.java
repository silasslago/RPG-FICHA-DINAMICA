package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class FastSkillsLabel extends Label{

	private int inLocal = 0, initY, firstYRoller;
	private Roller roller;
	private ArrayList<SkillLabel> pericias;
	private boolean showMsg = false;
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
		public void actionPerformed() {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Skills) {
					FastSkillsChooserLabel fSCL = new FastSkillsChooserLabel(e.getX(), 50, 
							e.getWidth(), e.getHeight(), 0, null);
					Game.entities.add(fSCL);
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
		labels.add(pericias);
		labels.add(fastAcess);

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
		labels.add(addB);
		
		initY = getY()+25;
		
		int w = 8;
		roller = new Roller(getX()+getWidth()-w, getY(), w, getHeight(), 2, null, true, getX()+getWidth()-w, getY(), w, getHeight());
		firstYRoller = roller.getY();
		labels.add(roller);
	}
	
	public void createLabel(int value) {
		boolean create = true;
		for(int i = 0; i < pericias.size(); i++) {
			SkillLabel sL = pericias.get(i);
			if(sL.getID() == value) {
				create = false;
			}
		}
		if(create && pericias.size() <= 32) {
			SkillLabel skillLabel = new SkillLabel(addB.labelX, addB.labelY+10+((addB.labelH+5)*addB.labelsAmount),
					addB.labelW, addB.labelH-5, 0, null, value, addB.labelY+10);
			addB.labelsAmount++;
			pericias.add(skillLabel);
		}
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			if(pericias.size() <= 0) {
				showMsg = true;
			}else {
				showMsg = false;
			}
			
			if(this.pericias.size() < 6) {
				int plusH = 0;
				for(int i = 0; i < this.pericias.size(); i++) {
					plusH+=this.pericias.get(i).getHeight()+5-this.pericias.get(i).inLocal;
				}
				this.setHeight(100+inLocal*2+plusH);
				this.roller.setBars(roller.getX(), roller.getY(), roller.getWidth(), getHeight()-inLocal*2);
			}

			for(int i = 0; i < this.pericias.size(); i++) {
				SkillLabel l = this.pericias.get(i);
				if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+49+inLocal*2) {
					l.tick();
				}
			}
			
			for(int i = 0; i < pericias.size(); i++) {
				SkillLabel sL = pericias.get(i);
				int times = i+1;
				int height = sL.getHeight()+10-sL.inLocal*2;
				int calc = initY-sL.inLocal+height*times;
				sL.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
			}
			
			int plus = 0;
			if(pericias.size()>5 && pericias.size() < 32) {
				boolean minus = false;
				for(int i = 0; i < pericias.size(); i++) {
					if(pericias.get(i).inLocal != 0) {
						minus = true;
					}
				}
				int minusAnao = 0;
				if(minus) {
					minusAnao = pericias.get(0).inLocal*2;
				}
				plus = (int)(((pericias.get(0).getHeight()-minusAnao)*(pericias.size()-5))/1.8);
				this.roller.setHeight(getHeight()-plus-inLocal*2);
				this.roller.maskh = getHeight()-plus-inLocal*2;
			}else if(pericias.size()<=5) {
				this.roller.setHeight(getHeight()-inLocal*2);
				this.roller.maskh = getHeight()-inLocal*2;
			}	
			
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.setFont(new Font("sitka banner", Font.BOLD, 12));
		g.setColor(Color.DARK_GRAY);
		g.drawLine(getX()+35+inLocal, getY()+50+inLocal-Game.roller.getY()*Game.roller.step, getX()+getWidth()-35-inLocal, getY()+50+inLocal-Game.roller.getY()*Game.roller.step);
		if(showMsg) {
			g.drawString("Nada adicionado no acesso rápido", getX()+70+inLocal, getY()+70+inLocal-Game.roller.getY()*Game.roller.step);
		}
		for(int i = 0; i < this.pericias.size(); i++) {
			SkillLabel l = this.pericias.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+49+inLocal*2) {
				l.render(g);
			}
		}
	}
	
	public ArrayList<SkillLabel> getSkillArrayList() {
		return this.pericias;
	}
	
	public void setSkillArrayList(ArrayList<SkillLabel> pericias) {
		this.pericias = pericias;
	}

}
