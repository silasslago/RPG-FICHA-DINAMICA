package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class AddButton extends Button{

	public int labelX, labelY, labelsAmount, labelW, labelH;
	public ArrayList<Label> labels;
	private int maxShow;
	
	public AddButton(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			int labelX, int labelY, int labelW, int labelH, int maxShow) {
		super(x, y, width, height, speed, sprite);
		labels = new ArrayList<Label>();
		this.labelX = labelX;
		this.labelY = labelY;
		this.labelH = labelH;
		this.labelW = labelW;
		this.maxShow = maxShow;
	}
	
	public void tick() {
		super.tick();
		for(int i = 0; i < labels.size(); i++) {
			Label l = labels.get(i);
			if(l.getY()+l.getHeight() < maxShow) {
				l.tick();
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		for(int i = 0; i < labels.size(); i++) {
			Label ll = labels.get(i);
			if(ll.getY()+ll.getHeight() < maxShow) {
				ll.render(g);
			}
		}
	}
	
	public void actionPerformed(){
		labelsAmount++;
		GunLabel gunLabel = new GunLabel(labelX, labelY+((this.labelH+5)*labelsAmount), this.labelW, this.labelH, 0, 
				null, this.labelY+this.labelH+5);
		labels.add(gunLabel);
	}

}
