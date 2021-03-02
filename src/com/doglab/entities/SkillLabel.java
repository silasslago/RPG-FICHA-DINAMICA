package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SkillLabel extends Label{

	public SkillLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
