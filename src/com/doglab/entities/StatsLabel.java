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
			sanityColor = new Color(0xFF001752), oculColor = new Color(0xFF2F014F);
	
	public StatsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		dice = new Dice(545, 390, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71));
		life = new TextLabel(330, 280, 30, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Vida", 0);
		sani = new TextLabel(330, 390, 60, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sanidade", 0);
		ocul = new TextLabel(330, 500, 70, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Ocultismo", 0);
		extrada = new TextLabel(330, 560, 70, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Dano Extra", 1);
		corpo = new TextLabel(450, 560, 40, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Corpo", 1);
		expPar = new TextLabel(540, 560, 60, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Exp. Par.", 1);
		lifePlayer = new TextLabel(450, 313, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxLie = new TextLabel(485, 313, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		sanityPlayer = new TextLabel(410, 423, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxSanityPlayer = new TextLabel(440, 423, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		ocultismoPlayer = new TextLabel(450, 533, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		maxOcultismoPlayer = new TextLabel(485, 533, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		extraDamage = new TextLabel(355, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
		body = new TextLabel(460, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
		paranormalExp = new TextLabel(560, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
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

	public void render(Graphics g) {
		super.render(g);
		try{
			g.setColor(lifeColor);
			g.fillRect(325, 290, (275*Integer.parseInt(this.lifePlayer.text))/Integer.parseInt(this.maxLie.text), 30);
			g.setColor(sanityColor);
			g.fillRect(325, 400, (200*Integer.parseInt(this.sanityPlayer.text))/Integer.parseInt(this.maxSanityPlayer.text), 30);
			g.setColor(oculColor);
			g.fillRect(325, 510, (275*Integer.parseInt(this.ocultismoPlayer.text))/Integer.parseInt(this.maxOcultismoPlayer.text), 30);
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		g.setColor(new Color(0xFF424242));
		g.drawRect(325, 290, 275, 30);
		g.drawRect(325, 400, 200, 30);
		g.drawRect(325, 510, 275, 30);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 31));
		g.drawString("/", 470, 313);
		g.drawString("/", 470, 533);
		g.drawString("/", 425, 423);	
		g.drawLine(330, 630, 400, 630);
		g.drawLine(430, 630, 510, 630);
		g.drawLine(530, 630, 600, 630);
		
		if(!edit.isEditing) {
			g.setColor(new Color(0xFF424242));
		}else {
			g.setColor(Color.WHITE);
		}
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawImage(edit.getSprite(), edit.getX(), edit.getY(), edit.getWidth(), edit.getHeight(), null);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
	}
	
}
