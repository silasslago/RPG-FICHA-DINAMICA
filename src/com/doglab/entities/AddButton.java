package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class AddButton extends Button{

	public int labelX, labelY, labelsAmount, labelW, labelH;
	public ArrayList<GunLabel> labels;
	
	public AddButton(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			int labelX, int labelY, int labelW, int labelH) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<GunLabel>();
		this.labelX = labelX;
		this.labelY = labelY;
		this.labelH = labelH;
		this.labelW = labelW;
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
	}
	
	public void actionPerformed(){
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof CombatLabel) {
				labels = ((CombatLabel) e).getGunArrayList();
				labelsAmount++;
				GunLabel gunLabel = new GunLabel(labelX, labelY+((this.labelH+5)*labelsAmount), this.labelW, this.labelH, 0, 
						null, this.labelY+this.labelH+5);
				labels.add(gunLabel);
				((CombatLabel) e).setGunArrayList(labels);
				return;
			}
		}
	}

}
