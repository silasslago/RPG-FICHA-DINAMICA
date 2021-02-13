package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class StatsLabel extends Label{

	private TextLabel life, sani, ocul, extrada, corpo, expPar, lifePlayer, maxLie, sanityPlayer,
	maxSanityPlayer, ocultismoPlayer, maxOcultismoPlayer, body, extraDamage,paranormalExp;
	private Dice dice;
	private Color lifeColor = new Color(0xFF500101),
			sanityColor = new Color(0xFF001752), 
			oculColor = new Color(0xFF2F014F);

	public StatsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		life = new TextLabel(340, 265, 30, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Vida", 0);
		lifePlayer = new TextLabel(475, 290, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxLie = new TextLabel(500, 290, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		
		sani = new TextLabel(340, 345, 60, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sanidade", 0);
		sanityPlayer = new TextLabel(450, 370, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxSanityPlayer = new TextLabel(475, 370, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		
		ocul = new TextLabel(340, 425, 70, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Ocultismo", 0);
		ocultismoPlayer = new TextLabel(475, 450, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxOcultismoPlayer = new TextLabel(500, 450, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		
		extrada = new TextLabel(370, 475, 70, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Dano Extra", 1);
		corpo = new TextLabel(490, 475, 40, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Corpo", 1);
		expPar = new TextLabel(580, 475, 60, 15, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Exp. Par.", 1);

		extraDamage = new TextLabel(400, 520, 20, 40, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1);
		body = new TextLabel(500, 520, 20, 40, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1);
		paranormalExp = new TextLabel(600, 520, 20, 40, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1);
		
		dice = new Dice(610, 340, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 10, 
				Integer.parseInt(sanityPlayer.text));
		labels.add(dice);
		labels.add(maxLie);
		labels.add(lifePlayer);
		labels.add(sanityPlayer);
		labels.add(maxSanityPlayer);
		labels.add(ocultismoPlayer);
		labels.add(maxOcultismoPlayer);
		labels.add(body);
		labels.add(paranormalExp);
		labels.add(extraDamage);
		labels.add(life);
		labels.add(sani);
		labels.add(ocul);
		labels.add(extrada);
		labels.add(corpo);
		labels.add(expPar);
	}

	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		try{
			g.setColor(lifeColor);
			g.fillRect(340, 270-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.lifePlayer.text))/Integer.parseInt(this.maxLie.text), 25);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try{
			g.setColor(sanityColor);
			g.fillRect(340, 350-Game.roller.getY()*Game.roller.step, (260*Integer.parseInt(this.sanityPlayer.text))/Integer.parseInt(this.maxSanityPlayer.text), 25);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try{
			g.setColor(oculColor);
			g.fillRect(340, 430-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.ocultismoPlayer.text))/Integer.parseInt(this.maxOcultismoPlayer.text), 25);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		g.setColor(new Color(0xFF424242));
		g.drawRect(340, 270-Game.roller.getY()*Game.roller.step, 320, 25);
		g.drawRect(340, 350-Game.roller.getY()*Game.roller.step, 260, 25);
		g.drawRect(340, 430-Game.roller.getY()*Game.roller.step, 320, 25);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 23));
		g.drawString("/", 490, 288-Game.roller.getY()*Game.roller.step);
		g.drawString("/", 490, 448-Game.roller.getY()*Game.roller.step);
		g.drawString("/", 465, 368-Game.roller.getY()*Game.roller.step);	
		g.drawLine(370, 530-Game.roller.getY()*Game.roller.step, 440, 530-Game.roller.getY()*Game.roller.step);
		g.drawLine(470, 530-Game.roller.getY()*Game.roller.step, 540, 530-Game.roller.getY()*Game.roller.step);
		g.drawLine(570, 530-Game.roller.getY()*Game.roller.step, 640, 530-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
	}
	
}
