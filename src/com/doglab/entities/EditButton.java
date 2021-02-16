package com.doglab.entities;

import java.awt.image.BufferedImage;


public class EditButton extends Button{

	public boolean isEditing = false;
	
	public EditButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		super.tick();
		
	}
	
	public void actionPerformed() {
		isEditing = !isEditing;
	}
	
	public boolean isEditing() {
		return isEditing;
	}

}
