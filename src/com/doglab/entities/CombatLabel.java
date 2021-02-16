package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class CombatLabel extends Label{

	private int inLocal = 0;
	public AddButton addB;
	public Roller roller;
	
	public CombatLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel combate = new TextLabel(getX()+270, getY()+30, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Combate", 1);
		TextLabel nome = new TextLabel(getX()+60, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Nome", 0);
		TextLabel tipo = new TextLabel(getX()+140, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Tipo", 0);
		TextLabel dano = new TextLabel(getX()+230, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Dano", 0);
		TextLabel munAtual = new TextLabel(getX()+320, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Mun. Atual", 0);
		TextLabel munMaxima = new TextLabel(getX()+390, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Mun. Máx.", 0);
		TextLabel ataque = new TextLabel(getX()+455, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Ataque", 0);
		TextLabel alcance = new TextLabel(getX()+505, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Alcance", 0);
		TextLabel defeito = new TextLabel(getX()+560, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Defeito", 0);
		TextLabel area = new TextLabel(getX()+610, getY()+65, 200, 26, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Area", 0);
		
		addB = new AddButton(getX()+getWidth()-35, getY()+10, 25, 25, 0, 
				Game.spr_entities.getSprite(76, 206, 25, 25), getX()+5, getY()+50, getWidth()-10, 20, getY()+getHeight());
		
		roller = new Roller(getX()+getWidth()-5, getY(), 5, 15, 10, null, true, getX()+getWidth()-5, getY(), 5, getHeight());
		
		labels.add(roller);
		labels.add(combate);
		labels.add(addB);
		labels.add(nome);
		labels.add(tipo);
		labels.add(dano);
		labels.add(munAtual);
		labels.add(munMaxima);
		labels.add(ataque);
		labels.add(alcance);
		labels.add(defeito);
		labels.add(area);
	}
	
	public void tick() {
		super.tick();
		if(current) {
			inLocal = this.size;
		}else {
			inLocal = 0;
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+75, getX()+getWidth()-inLocal-15, getY()+inLocal+75-Game.roller.getY()*Game.roller.step);
	}

}
