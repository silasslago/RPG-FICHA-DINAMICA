package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Credits {

	private double y = 0;
	private int dogTimer = 0;
	private BufferedImage doglab;
	private boolean plus;
	private int color = 240;
	private int backX = 0;
	
	public Credits() {
		try {
			doglab = ImageIO.read(getClass().getResource("/bootsplash.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void tick() {
		backX--;
		if(backX < -200) {
			backX = 0;
		}
		if(color == 0) {
			plus = true;
		}
		if(plus == false) {
			color--;
		}else if(color < 250 && dogTimer >= 50){
			color++;
		}
		if(y < 400) {
			y+=0.3;
		}else {
			dogTimer++;
			if(dogTimer > 300) {
				System.exit(0);
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(Color.white);
		g.setFont(new Font("Dialog", Font.PLAIN, 12));
		g.drawString("Game Designer:", 75, 15 + Game.HEIGHT - (int)y);
		g.drawString("Programmer:", 85, 60 + Game.HEIGHT - (int)y);
		g.drawString("Artist:", 105, 105 + Game.HEIGHT - (int)y);
		g.drawString("History:", 100, 150 + Game.HEIGHT - (int)y);
		g.drawString("SFX:", 108, 195 + Game.HEIGHT - (int)y);
		g.setColor(Color.white);
		g.drawString("Dogoso", 98, 30 + Game.HEIGHT - (int)y);
		g.drawString("Dogoso", 98, 75 + Game.HEIGHT - (int)y);
		g.drawString("Dogoso", 98, 120 + Game.HEIGHT - (int)y);
		g.drawString("Dogoso", 98, 210 + Game.HEIGHT - (int)y);
		g.drawString("Dogoso & Leb1nho", 70, 165 + Game.HEIGHT - (int)y);
		g.drawImage(doglab, 55, 255 + Game.HEIGHT - (int)y, 128, 128, null);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(new Color(0,0,0,color));
		g2d.fillRect(0,0, Game.WIDTH, Game.HEIGHT);
	}
	
}
