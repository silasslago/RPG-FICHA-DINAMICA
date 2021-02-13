package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class DetailsLabel extends Label{

	private TextLabel nome, player, ocupation, idade, sex, ldM, livePlayer, ldR, namePlayer, playerPlayer, 
	ocupationPlayer, agePlayer, genderPlayer, bornPlayer, detalhes;
	
	public DetailsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		nome = new TextLabel(50, 165, 32, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Nome", 0);
		namePlayer = new TextLabel(60, 180, 30, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "nome", 0);
		
		player = new TextLabel(50, 205, 40, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Jogador", 0);
		playerPlayer = new TextLabel(60, 220, 30, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "player", 0);
		
		ocupation = new TextLabel(50, 245, 50, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Ocupação", 0);
		ocupationPlayer = new TextLabel(60, 260, 50, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "ocupation", 0);
		
		idade = new TextLabel(50, 285, 30, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Idade", 0);
		agePlayer = new TextLabel(60, 300, 18, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "age", 0);
		
		sex = new TextLabel(50, 325, 25, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Sexo", 0);
		genderPlayer = new TextLabel(60, 340, 35, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "gender", 0);
		
		ldM = new TextLabel(50, 365, 105, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Local de Nascimento", 0);
		bornPlayer = new TextLabel(60, 380, 50, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "bornPlace", 0);
		
		ldR = new TextLabel(50, 405, 95, 14, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Local de residencia", 0);
		livePlayer = new TextLabel(60, 420, 55, 14, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "livingPlace", 0);

		detalhes = new TextLabel(85, 130, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "DETALHES PESSOAIS", 1);
		
		
		labels.add(detalhes);
		labels.add(ldR);
		labels.add(ldM);
		labels.add(sex);
		labels.add(idade);
		labels.add(ocupation);
		labels.add(player);
		labels.add(nome);
		labels.add(namePlayer);
		labels.add(playerPlayer);
		labels.add(ocupationPlayer);
		labels.add(agePlayer);
		labels.add(genderPlayer);
		labels.add(bornPlayer);
		labels.add(livePlayer);
	}

	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
		int textY = 165;
		textY+=20;
		g.setColor(new Color(0xFF424242));
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(50, textY-Game.roller.getY()*Game.roller.step, 300, textY-Game.roller.getY()*Game.roller.step);
	}
	
}
