package com.doglab.entities;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Player extends Entity{

	public Image icon;
	public String name = "name", player = "player", ocupation = "ocupation", age = "age", 
			gender = "gender", bornPlace = "bornPlace", livingPlace = "livingPlace";
	public int currentLife = 1, maxLife = 1, currentSanity = 1, maxSanity = 1, currentOcutism = 1,
			maxOcutism = 1, extraDamage = 1, body = 1, paranormalExposition = 1;
	
	public Player(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

}
