package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.entities.AtributosLabel;
import com.doglab.entities.CharacterIcon;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.EditButton;
import com.doglab.entities.IconLabel;
import com.doglab.entities.Roller;
import com.doglab.entities.StatsLabel;
import com.doglab.entities.TextLabel;

public class Menu {

	private final Color BLACK = new Color(0xFF000000);
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
		
		AtributosLabel atrLabel = new AtributosLabel(30, 550, 290, 300, 0, null);
		Game.entities.add(atrLabel);
	}
	
	public void render(Graphics g) {
		// Fundo
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
	}
	
	public void tick() {
		
	}
	
}
