package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.doglab.entities.AtributosLabel;
import com.doglab.entities.CombatLabel;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.FastSkillsLabel;
import com.doglab.entities.IconLabel;
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
		
		AtributosLabel atrLabel = new AtributosLabel(25, 550, 300, 480, 0, null);
		Game.entities.add(atrLabel);
		
		FastSkillsLabel fastSLabel = new FastSkillsLabel(340, 550, 320, 100, 0, null);
		Game.entities.add(fastSLabel);
		
		CombatLabel combatLabel = new CombatLabel(10, 1050, Game.WIDTH*Game.SCALE-30, 200, 0, null);
		Game.entities.add(combatLabel);
	}
	
	public void render(Graphics g) {
		// Fundo
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
	}
	
	public void tick() {
		
	}
	
}
