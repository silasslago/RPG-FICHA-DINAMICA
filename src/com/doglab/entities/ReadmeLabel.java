package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ReadmeLabel extends Label{

	public ReadmeLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		order = 10;
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
