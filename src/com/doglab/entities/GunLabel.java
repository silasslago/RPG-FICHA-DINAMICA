package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class GunLabel extends Label{

	public DeleteButton deleteB;
	private int plusY = 15, inLocal = 0;
	
	public GunLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int minY) {
		super(x, y, width, height, speed, sprite);
		deleteB = new DeleteButton(getX()+5, getY()+3, 15, 15, 0,
				Game.spr_entities.getSprite(76, 181, 25, 25), minY);
		labels.add(deleteB);
		
		TextLabel gunName = new TextLabel(getX()+60-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunName);
		
		TextLabel gunType = new TextLabel(getX()+140-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunType);
		
		TextLabel gunMunAtual = new TextLabel(getX()+320-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunMunAtual);

		TextLabel gunMunMax = new TextLabel(getX()+390-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunMunMax);
		
		TextLabel gunAtaque = new TextLabel(getX()+455-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunAtaque);

		TextLabel gunAlcance = new TextLabel(getX()+505-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunAlcance);
		
		TextLabel gunDefeito = new TextLabel(getX()+560-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunDefeito);
		
		TextLabel gunArea = new TextLabel(getX()+610-5, getY()+plusY, 50, 50, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), new Color(0xFFE8EDEB), "???", 0);
		labels.add(gunArea);
	}
	
	public void tick() {
		super.tick();
		if(current) {
			inLocal = this.size;
		}else {
			inLocal = 0;
		}
		for(int i = 0; i < labels.size(); i++) {
			Entity l = labels.get(i);
			if(l instanceof TextLabel) {
				int plusY = getY()+this.plusY;
				if(plusY != l.getY()-inLocal) {
					l.setY(plusY);
					((TextLabel) l).setImaginaryY(plusY);
				}
			}else if(l instanceof Button) {
				int plusY = getY()+3;
				if(plusY != l.getY()-inLocal) {
					l.setY(plusY);
				}
				
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
	}

}
