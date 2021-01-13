package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Pause {
	
	private int gameOverFrames = 0, gameOverMax = 50;
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.setColor(Color.BLACK);
		g.drawString("PAUSE", 100, 87);
		g.setColor(Color.WHITE);
		g.drawString("PAUSE", 100, 85);
		g.setColor(Color.BLACK);
		g.setFont(new Font("arial", Font.BOLD, 8));
		g.drawString("or ESC again to MENU", Game.WIDTH / 2 - 40, Game.HEIGHT - 8);
		g.setColor(Color.lightGray);
		g.drawString("or ESC again to MENU", Game.WIDTH / 2 - 40, Game.HEIGHT - 10);
		g.setFont(new Font("arial", Font.BOLD, 7));
		gameOverFrames++;
		if(gameOverFrames >= gameOverMax) {
			g.setColor(Color.BLACK);
			g.drawString(">PRESS ENTER<", 91, 97);
			g.setColor(Color.WHITE);
			g.drawString(">PRESS ENTER<", 91, 95);
			if(gameOverFrames >= gameOverMax * 2) {
				gameOverFrames = 0;
			}
		}
		
	}
	
}
