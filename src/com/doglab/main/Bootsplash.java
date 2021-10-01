package com.doglab.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bootsplash {

	private BufferedImage bootsplash;
	private boolean latidao = true;
	private int timer = 0, maxTime = 200;
	
	private int imgWidth, imgHeight;
	
	public Bootsplash() {
		try {
			bootsplash = ImageIO.read(getClass().getResource("/bootsplash.png"));
			imgWidth = bootsplash.getWidth();
			imgHeight = bootsplash.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		
		timer++;
		if(latidao) {
			Sound.bark.play();
			latidao = false;
		}
		if(timer > maxTime) {
			timer = 0;
			Game.gameState = "MENU";
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFFECECEC));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(bootsplash, -15+Menu.margin, 120, imgWidth-400, imgHeight-320, null);
	}
	
}
