package com.doglab.entities;

import java.awt.image.BufferedImage;
import java.util.Random;

public class Dice extends Entity{

	private Random random;
	
	public Dice(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		random = new Random();
	}

}
