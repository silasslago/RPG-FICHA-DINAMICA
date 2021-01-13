package com.doglab.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.doglab.entities.Entity;

public class Bootsplash {

	private BufferedImage bootsplash;
	private boolean latidao = true;
	private int timer = 0, maxTime = 600;
	private int alpha = 250;
	private int backX = 0;
	
	public Bootsplash() {
		try {
			bootsplash = ImageIO.read(getClass().getResource("/bootsplash.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		backX = 0;
	}
	
	public void tick() {
		timer++;
		backX--;
		if(backX < -200) {
			backX = 0;
		}
		if(timer < 250) {
			alpha--;
		}
		if(timer > 357) {
			alpha++;
		}
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
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(bootsplash, 50, 15, 128, 128, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0,0,0,alpha));
		g2d.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
	}
	
}
