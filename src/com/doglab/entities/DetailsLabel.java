package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class DetailsLabel extends Label{

	private TextLabel nome, player, ocupation, idade, sex, ldM, livePlayer, ldR, namePlayer, playerPlayer, 
	ocupationPlayer, agePlayer, genderPlayer, bornPlayer, detalhes;
	private int inLocal = 0;
	
	//30, 100
	public DetailsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		
		int size = 15;
		nome = new TextLabel(getX()+20, getY()+65, 32, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Nome", 0, false);
		namePlayer = new TextLabel(getX()+30, getY()+80, 30, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "nome", 0, false);
		namePlayer.canClick(true);
		
		player = new TextLabel(getX()+20, getY()+105, 40, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Jogador", 0, false);
		playerPlayer = new TextLabel(getX()+30,getY()+120, 30, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "player", 0, false);
		playerPlayer.canClick(true);
		
		ocupation = new TextLabel(getX()+20, getY()+145, 50, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Ocupação", 0, false);
		ocupationPlayer = new TextLabel(getX()+30, getY()+160, 50, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "ocupation", 0, false);
		ocupationPlayer.canClick(true);
		
		idade = new TextLabel(getX()+20, getY()+185, 30, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Idade", 0, false);
		agePlayer = new TextLabel(getX()+30, getY()+200, 18, 11, 0, null, new Font("SpecialElite-Regular", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "age", 0, false);
		agePlayer.canClick(true);
		
		sex = new TextLabel(getX()+20, getY()+225, 25, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Sexo", 0, false);
		genderPlayer = new TextLabel(getX()+30, getY()+240, 35, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "gender", 0, false);
		genderPlayer.canClick(true);
		
		ldM = new TextLabel(getX()+20, getY()+265, 105, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Local de Nascimento", 0, false);
		bornPlayer = new TextLabel(getX()+30, getY()+280, 50, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "bornPlace", 0, false);
		bornPlayer.canClick(true);
		
		ldR = new TextLabel(getX()+20, getY()+305, 95, 11, 0, null, new Font("sitka banner", Font.BOLD, size), 
				new Color(0xFFE8EDEB), "Local de residencia", 0, false);
		livePlayer = new TextLabel(getX()+30, getY()+320, 55, 11, 0, null, new Font("sitka banner", Font.PLAIN, size), 
				new Color(0xFFE8EDEB), "livingPlace", 0, false);
		livePlayer.canClick(true);
		
		detalhes = new TextLabel(getX()+45, getY()+30, 200, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "DETALHES PESSOAIS", 1, false);
		
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
		int textY = getY()+65;
		textY+=20;
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
		textY+=40;
		g.drawLine(getX()+20+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step, getX()+270+inLocal, textY+inLocal-Game.roller.getY()*Game.roller.step);
	}
	
}
