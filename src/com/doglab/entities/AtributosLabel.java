package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class AtributosLabel extends Label{

	private int inLocal = 0;
	
	public AtributosLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel atributos = new TextLabel(getX()+105, getY()+30, 80, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Atributos", 1);
		labels.add(atributos);
		
		TextLabel dValue = new TextLabel(0, 0, 0, 0, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), null, "20", 0);
		TextLabel dAmount = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0);
		
		TextLabel aparencia = new TextLabel(getX()+30, getY()+100, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Aparencia", 1);
		TextLabel apar = new TextLabel(getX()+55, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice aparDice = new Dice(getX()+33, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, apar, dAmount);
		labels.add(aparDice);
		labels.add(aparencia);
		labels.add(apar); 
		
		TextLabel constituicao = new TextLabel(getX()+120, getY()+100, 75, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Constituição", 1);
		TextLabel cons = new TextLabel(getX()+149, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice consDice = new Dice(getX()+128, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, cons, dAmount);
		labels.add(consDice);
		labels.add(constituicao);
		labels.add(cons); 
		
		TextLabel destreza = new TextLabel(getX()+220, getY()+100, 55, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Destreza", 1);
		TextLabel dest = new TextLabel(getX()+243, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice destDice = new Dice(getX()+221, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, dest, dAmount);
		labels.add(destDice);
		labels.add(destreza);
		labels.add(dest); 
		
		TextLabel educacao = new TextLabel(getX()+30, getY()+200, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Educação", 1);
		TextLabel educ = new TextLabel(getX()+55, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice educDice = new Dice(getX()+33, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, educ, dAmount);
		labels.add(educDice);
		labels.add(educacao);
		labels.add(educ); 
		
		TextLabel forca = new TextLabel(getX()+137, getY()+200, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Força", 1);
		TextLabel forc = new TextLabel(getX()+149, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice forcDice = new Dice(getX()+128, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, forc, dAmount);
		labels.add(forcDice);
		labels.add(forca);
		labels.add(forc); 
		
		TextLabel inteligencia = new TextLabel(getX()+212, getY()+200, 73, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Inteligência", 1);
		TextLabel inte = new TextLabel(getX()+243, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice inteDice = new Dice(getX()+221, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, inte, dAmount);
		labels.add(inteDice);
		labels.add(inteligencia);
		labels.add(inte); 
		
		TextLabel poder = new TextLabel(getX()+42, getY()+300, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Poder", 1);
		TextLabel pode = new TextLabel(getX()+55, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice podeDice = new Dice(getX()+33, getY()+240, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, pode, dAmount);
		labels.add(podeDice);
		labels.add(poder);
		labels.add(pode); 
		
		TextLabel sorte = new TextLabel(getX()+137, getY()+300, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sorte", 1);
		TextLabel sort = new TextLabel(getX()+149, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		Dice sortDice = new Dice(getX()+128, getY()+240, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, sort, dAmount);
		labels.add(sortDice);
		labels.add(sorte);
		labels.add(sort); 
		
		TextLabel movimento = new TextLabel(getX()+212, getY()+300, 70, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Movimento", 1);
		TextLabel movi = new TextLabel(getX()+243, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		labels.add(movimento);
		labels.add(movi);
		
		TextLabel tamanho = new TextLabel(getX()+32, getY()+400, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Tamanho", 1);
		TextLabel tama = new TextLabel(getX()+55, getY()+425, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1);
		labels.add(tamanho);
		labels.add(tama);
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			if(current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}	
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+35+inLocal, getY()+35-Game.roller.getY()*Game.roller.step+inLocal, getX()+width-35-inLocal, getY()+35-Game.roller.getY()*Game.roller.step+inLocal);
		g.drawLine(getX()+30+inLocal, getY()+130-Game.roller.getY()*Game.roller.step+inLocal, getX()+90+inLocal, getY()-Game.roller.getY()*Game.roller.step+130+inLocal);
		g.drawLine(getX()+120+inLocal, getY()+130-Game.roller.getY()*Game.roller.step+inLocal, getX()+190+inLocal, getY()-Game.roller.getY()*Game.roller.step+130+inLocal);
		g.drawLine(getX()+220+inLocal, getY()+130-Game.roller.getY()*Game.roller.step+inLocal, getX()+275+inLocal, getY()-Game.roller.getY()*Game.roller.step+130+inLocal);
		g.drawLine(getX()+30+inLocal, getY()+230-Game.roller.getY()*Game.roller.step+inLocal, getX()+90+inLocal, getY()-Game.roller.getY()*Game.roller.step+230+inLocal);
		g.drawLine(getX()+120+inLocal, getY()+230-Game.roller.getY()*Game.roller.step+inLocal, getX()+190+inLocal, getY()-Game.roller.getY()*Game.roller.step+230+inLocal);
		g.drawLine(getX()+220+inLocal, getY()+230-Game.roller.getY()*Game.roller.step+inLocal, getX()+275+inLocal, getY()-Game.roller.getY()*Game.roller.step+230+inLocal);
		g.drawLine(getX()+30+inLocal, getY()+330-Game.roller.getY()*Game.roller.step+inLocal, getX()+90+inLocal, getY()-Game.roller.getY()*Game.roller.step+330+inLocal);
		g.drawLine(getX()+120+inLocal, getY()+330-Game.roller.getY()*Game.roller.step+inLocal, getX()+190+inLocal, getY()-Game.roller.getY()*Game.roller.step+330+inLocal);
		g.drawLine(getX()+220+inLocal, getY()+330-Game.roller.getY()*Game.roller.step+inLocal, getX()+275+inLocal, getY()-Game.roller.getY()*Game.roller.step+330+inLocal);
		g.drawLine(getX()+30+inLocal, getY()+430-Game.roller.getY()*Game.roller.step+inLocal, getX()+90+inLocal, getY()-Game.roller.getY()*Game.roller.step+430+inLocal);
	}

}
