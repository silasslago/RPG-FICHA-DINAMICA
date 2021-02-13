package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.entities.CharacterIcon;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.EditButton;
import com.doglab.entities.IconLabel;
import com.doglab.entities.StatsLabel;
import com.doglab.entities.TextLabel;

public class Menu {

	private final Color BLACK = new Color(0xFF000000), 
			ORANGE = new Color(0xFFFCC988), 
			PURPLE = new Color(0xFFE688FC), 
			PINK = new Color(0xFFFC88F2), 
			CYAN = new Color(0xFF88F8FC), 
			BLUE = new Color(0xFF8892FC),
			YELLOW = new Color(0xFFFCF688), 
			RED = new Color(0xFFFC8988), 
			GREEN = new Color(0xFF88FC99);
	private Color bg = BLACK;
	
	public Menu() {
		
		DetailsLabel detailsLabel = new DetailsLabel(30, 100, Game.WIDTH/2-50, 370, 0, null);
		Game.entities.add(detailsLabel);
		
		IconLabel iconLabel = new IconLabel(346, 80, 285, 160, 0, null);
		Game.entities.add(iconLabel);
		
		StatsLabel statsLabel = new StatsLabel(340, 250,(int)(Game.WIDTH/2.18), 440, 0, null);
		Game.entities.add(statsLabel);
		
		TextLabel title = new TextLabel(240, 60, 200, 31, 0, null, new Font("sitka banner", Font.PLAIN, 31), 
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1);
		Game.entities.add(title);
	}
	
	public void render(Graphics g) {
		// Fundo
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
		
		/*g.setColor(ORANGE);
		g.fillRect(10, 40, 25, 25);
		g.setColor(PURPLE);
		g.fillRect(45, 40, 25, 25);
		g.setColor(PINK);
		g.fillRect(80, 40, 25, 25);
		g.setColor(CYAN);
		g.fillRect(115, 40, 25, 25);
		g.setColor(BLUE);
		g.fillRect(150, 40, 25, 25);
		g.setColor(YELLOW);
		g.fillRect(185, 40, 25, 25);
		*/
	}
	
	public void tick() {
		
	}
	
}
