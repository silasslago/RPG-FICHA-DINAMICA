package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class DetailsLabel extends Label{

	private TextLabel nome, player, ocupation, idade, sex, ldM, livePlayer, ldR, namePlayer, playerPlayer, 
	ocupationPlayer, agePlayer, genderPlayer, bornPlayer, detalhes;
	
	public DetailsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		nome = new TextLabel(30, 165, 32, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Nome", 0);
		player = new TextLabel(30, 220, 40, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Jogador", 0);
		ocupation = new TextLabel(30, 275, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Ocupação", 0);
		idade = new TextLabel(30, 330, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Idade", 0);
		sex = new TextLabel(30, 385, 25, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Sexo", 0);
		ldM = new TextLabel(30, 440, 105, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Local de Nascimento", 0);
		ldR = new TextLabel(30, 495, 95, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Local de residencia", 0);
		namePlayer = new TextLabel(30, 190, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "nome", 0);
		playerPlayer = new TextLabel(30, 245, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "player", 0);
		ocupationPlayer = new TextLabel(30, 300, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "ocupation", 0);
		agePlayer = new TextLabel(30, 355, 18, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "age", 0);
		genderPlayer = new TextLabel(30, 410, 35, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "gender", 0);
		bornPlayer = new TextLabel(30, 465, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "bornPlace", 0);
		detalhes = new TextLabel(65, 130, 200, 31, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "DETALHES PESSOAIS", 1);
		livePlayer = new TextLabel(30, 520, 55, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "livingPlace", 0);
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
		textY+=30;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
	}
	
}
