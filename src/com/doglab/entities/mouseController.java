package com.doglab.entities;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.world.World;

public class mouseController extends Entity{

	public boolean isPressed = false;
	public double xTarget, yTarget;
	public double currentX, currentY;
	
	public TextLabel currentTextLabel;
	
	public mouseController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		
		if(isPressed) {
			isPressed = false;
			this.x = (int)xTarget;
			this.y = (int)yTarget;
		}
		
	}
	
	public void render(Graphics g) {
		//g.setColor(Color.blue);
		//g.fillRect(getX(), getY(), width, height);
	}

	public void resetPosition() {
		this.x = 0-width;
		this.y = 0-height;
	}
	
}
