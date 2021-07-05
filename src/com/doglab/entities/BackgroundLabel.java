package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class BackgroundLabel extends Label{

	public BackgroundLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel habilidades = new TextLabel(getX()+260, getY()+38, 90, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "ANTECEDENTES", 1, false);
		labels.add(habilidades);
		
		TextLabel desc = new TextLabel(getX()+20, getY()+80, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Descrição Pessoal", 0, false);
		TextLabel descP = new TextLabel(getX()+25, getY()+105, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Sem descrição", 0, false);
		descP.textBox(true, 67, 390, getWidth()-20);
		descP.canClick(true);
		labels.add(descP);
		labels.add(desc);
		
		TextLabel carac = new TextLabel(getX()+20, getY()+210, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Caractéristicas", 0, false);
		TextLabel caracs = new TextLabel(getX()+25, getY()+235, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Sem caracteristicas", 0, false);
		caracs.textBox(true, 67, 390, getWidth()-20);
		caracs.canClick(true);
		labels.add(carac);
		labels.add(caracs);
		
		TextLabel fobman = new TextLabel(getX()+20, getY()+340, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Fobias e Manias", 0, false);
		TextLabel fobmans = new TextLabel(getX()+25, getY()+365, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhuma", 0, false);
		fobmans.textBox(true, 67, 390, getWidth()-20);
		fobmans.canClick(true);
		labels.add(fobman);
		labels.add(fobmans);
		
		TextLabel people = new TextLabel(getX()+20, getY()+470, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Pessoas Importantes", 0, false);
		TextLabel peoples = new TextLabel(getX()+25, getY()+495, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Ninguém", 0, false);
		peoples.textBox(true, 67, 390, getWidth()-20);
		peoples.canClick(true);
		labels.add(people);
		labels.add(peoples);
		
		TextLabel pertence = new TextLabel(getX()+20, getY()+600, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Pertences Queridos", 0, false);
		TextLabel pertences = new TextLabel(getX()+25, getY()+625, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhum", 0, false);
		pertences.textBox(true, 67, 390, getWidth()-20);
		pertences.canClick(true);
		labels.add(pertence);
		labels.add(pertences);
		
		TextLabel local = new TextLabel(getX()+20, getY()+730, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Locais Importantes", 0, false);
		TextLabel locals = new TextLabel(getX()+25, getY()+755, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhum", 0, false);
		locals.textBox(true, 67, 390, getWidth()-20);
		locals.canClick(true);
		labels.add(locals);
		labels.add(local);
		
		TextLabel blood = new TextLabel(getX()+20, getY()+860, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Ferimentos e Cicatrizes", 0, false);
		TextLabel bloods = new TextLabel(getX()+25, getY()+885, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhum", 0, false);
		bloods.canClick(true);
		bloods.textBox(true, 67, 390, getWidth()-20);
		labels.add(blood);
		labels.add(bloods);
		
		TextLabel paranor = new TextLabel(getX()+20, getY()+990, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Encontro com o Paranormal", 0, false);
		TextLabel paranormas = new TextLabel(getX()+25, getY()+1015, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhum", 0, false);
		paranormas.canClick(true);
		paranormas.textBox(true, 67, 390, getWidth()-20);
		labels.add(paranor);
		labels.add(paranormas);
		
		TextLabel doen = new TextLabel(getX()+20, getY()+1120, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Doenças", 0, false);
		TextLabel doens = new TextLabel(getX()+25, getY()+1145, 160, 13, 0, null, new Font("sitka banner", Font.BOLD, 17), 
				new Color(0xFFE8EDEB), "Nenhuma", 0, false);
		doens.canClick(true);
		doens.textBox(true, 67, 390, getWidth()-20);
		labels.add(doen);
		labels.add(doens);
	}
	
	public void tick() {
		super.tick();
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+15, getY()-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()-80+getHeight()-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+getHeight()-80+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+135-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+135+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+265-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+265+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+395-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+395+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+525-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+525+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+655-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+655+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+785-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+785+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+915-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+915+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+1045-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+1045+inLocal+55-Game.roller.getY()*Game.roller.step);
	}
	
}
