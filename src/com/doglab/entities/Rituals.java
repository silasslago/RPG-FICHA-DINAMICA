package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class Rituals extends Label{

	private BufferedImage signD, signE;
	private ArrayList<RitualsLabel> rituals;

	private int maxPage = 0;
	private int pageRituals = 0;
	private int initY;
	public int labelX, labelY, labelW, labelH;
	
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
		
		private ArrayList<RitualsLabel> labelR = new ArrayList<RitualsLabel>();

		public void actionPerformed(){
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Rituals) {
					labelR = ((Rituals) e).getRitualsList();
					RitualsLabel ritualLabel = new RitualsLabel(0, 0, 0, 0, 0, null, 0);
					labelsAmount++;
					if(labelsAmount%2 == 0) {
						ritualLabel = new RitualsLabel(labelX+this.labelW+10, labelY, 
								this.labelW, this.labelH, 0, null, this.labelY+this.labelH+5);
					}else {
						ritualLabel = new RitualsLabel(labelX, labelY, 
								this.labelW, this.labelH, 0, null, this.labelY+this.labelH+5);
					}
					labelR.add(ritualLabel);
					((Rituals) e).setRitualsList(labelR);
					return;
				}
			}
		}
	};
	
	public Rituals(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		rituals = new ArrayList<RitualsLabel>();
		TextLabel rituals = new TextLabel(getX()+285, getY()+30, 75, 19, 0, null, new Font("sitka banner", Font.BOLD, 26), 
				new Color(0xFFE8EDEB), "RITUAIS", 1, false);
		labels.add(rituals);
		
		initY = getY()+60;
		addB.setX(getX()+getWidth()-35);
		addB.setY(getY()+10);
		addB.setWidth(25);
		addB.setHeight(25);
		addB.setSprite(Game.spr_entities.getSprite(76, 206, 25, 25));
		labels.add(addB);
		this.labelX = getX()+10;
		this.labelY = initY;
		this.labelW = getWidth()/2-15;
		this.labelH = 330;
		addB.labelX = this.labelX;
		addB.labelY = this.labelY;
		addB.labelW = this.labelW;
		addB.labelH = this.labelH;
		addB.speed = 0;
		
		signD = Game.spr_entities.getSprite(176, 231, 25, 25);
		signE = Game.spr_entities.getSprite(201, 231, 25, 25);
	}
	
	protected void setRitualsList(ArrayList<RitualsLabel> labelR) {
		this.rituals = labelR;
	}

	protected ArrayList<RitualsLabel> getRitualsList() {
		return this.rituals;
	}

	public void tick() {
		super.tick();
		
		int s = rituals.size();
		if(s % 2 == 0) {
			this.maxPage = s/2-1;
		}else {
			this.maxPage = ((s-1)/2);
		}
		
		Entity e = new Entity(getX()+200, getY()+10-Game.roller.getY()*Game.roller.step, 25, 25, 0, null);
		Entity d = new Entity(getX()+440, getY()+10-Game.roller.getY()*Game.roller.step, 25, 25, 0, null);
		if(this.isColliding(Game.mouseController, e)) { // Esquerda
			Game.mouseController.resetPosition();
			pageRituals--;
			if(pageRituals<0) {
				pageRituals = maxPage;
			}
		}else if(this.isColliding(Game.mouseController, d)) { // Direita
			Game.mouseController.resetPosition();
			pageRituals++;
			if(pageRituals>maxPage) {
				pageRituals = 0;
			}
		}

		int current = pageRituals*2;
		for(int i = current; i < current+2; i++) {
			if(i>=this.rituals.size()) {
				break;
			}
			RitualsLabel r = this.rituals.get(i);
			r.tick();
		}
		
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
		int current = pageRituals*2;
		for(int i = current; i < current+2; i++) {
			if(i>=this.rituals.size()) {
				break;
			}
			RitualsLabel r = this.rituals.get(i);
			r.render(g);
		}
		g.drawImage(signE, getX()+200, getY()+10-Game.roller.getY()*Game.roller.step, null);
		g.drawImage(signD, getX()+440, getY()+10-Game.roller.getY()*Game.roller.step, null);
	}

	public ArrayList<RitualsLabel> getRituals() {
		return rituals;
	}

	public void setRituals(ArrayList<RitualsLabel> rituals) {
		this.rituals = rituals;
	}
	
}
