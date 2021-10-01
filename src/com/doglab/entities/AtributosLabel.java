package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class AtributosLabel extends Label{

	private int inLocal = 0;
	public TextLabel tama, cons, forc, dest, movi, pode; 
	
	public AtributosLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		TextLabel atributos = new TextLabel(getX()+105, getY()+30, 80, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "ATRIBUTOS", 1, false);
		labels.add(atributos);
		
		TextLabel dValue = new TextLabel(0, 0, 0, 0, 0, null, new Font("sitka banner", 
				Font.BOLD, 13), null, "20", 0, true);
		
		TextLabel dAmount = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0, true);
		
		TextLabel aparencia = new TextLabel(getX()+30, getY()+100, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Aparência", 1, false);
		TextLabel apar = new TextLabel(getX()+55, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice aparDice = new Dice(getX()+33, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, apar, dAmount, true, true, aparencia);
		labels.add(aparDice);
		labels.add(aparencia);
		labels.add(apar); 
		
		TextLabel constituicao = new TextLabel(getX()+120, getY()+100, 75, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Constituição", 1, false);
		cons = new TextLabel(getX()+149, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice consDice = new Dice(getX()+128, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, cons, dAmount, true, true, constituicao);
		labels.add(consDice);
		labels.add(constituicao);
		labels.add(cons); 
		
		TextLabel destreza = new TextLabel(getX()+220, getY()+100, 55, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Destreza", 1, false);
		dest = new TextLabel(getX()+243, getY()+125, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice destDice = new Dice(getX()+221, getY()+40, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, dest, dAmount, true, true, destreza);
		labels.add(destDice);
		labels.add(destreza);
		labels.add(dest); 
		
		TextLabel educacao = new TextLabel(getX()+30, getY()+200, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Educação", 1, false);
		TextLabel educ = new TextLabel(getX()+55, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice educDice = new Dice(getX()+33, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, educ, dAmount, true, true, educacao);
		labels.add(educDice);
		labels.add(educacao);
		labels.add(educ); 
		
		TextLabel forca = new TextLabel(getX()+137, getY()+200, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Força", 1, false);
		forc = new TextLabel(getX()+149, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice forcDice = new Dice(getX()+128, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, forc, dAmount, true, true, forca);
		labels.add(forcDice);
		labels.add(forca);
		labels.add(forc); 
		
		TextLabel inteligencia = new TextLabel(getX()+212, getY()+200, 73, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Inteligência", 1, false);
		TextLabel inte = new TextLabel(getX()+243, getY()+225, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice inteDice = new Dice(getX()+221, getY()+140, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, inte, dAmount, true, true, inteligencia);
		labels.add(inteDice);
		labels.add(inteligencia);
		labels.add(inte); 
		
		TextLabel poder = new TextLabel(getX()+42, getY()+300, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Poder", 1, false);
		pode = new TextLabel(getX()+55, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice podeDice = new Dice(getX()+33, getY()+240, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, pode, dAmount, true, true, poder);
		labels.add(podeDice);
		labels.add(poder);
		labels.add(pode); 
		
		TextLabel sorte = new TextLabel(getX()+137, getY()+300, 35, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sorte", 1, true);
		TextLabel sort = new TextLabel(getX()+149, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		Dice sortDice = new Dice(getX()+128, getY()+240, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, sort, dAmount, true, true, sorte);
		labels.add(sortDice);
		labels.add(sorte);
		labels.add(sort); 
		
		TextLabel movimento = new TextLabel(getX()+212, getY()+300, 70, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Movimento", 1, false);
		movi = new TextLabel(getX()+243, getY()+325, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, true);
		labels.add(movimento);
		labels.add(movi);
		
		TextLabel tamanho = new TextLabel(getX()+32, getY()+400, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Tamanho", 1, true);
		tama = new TextLabel(getX()+55, getY()+425, 13, 29, 0, null, 
				new Font("sitka banner", Font.BOLD, 31), new Color(0xFFE8EDEB), "1", 1, false);
		labels.add(tamanho);
		labels.add(tama);
	}
	
	public void tick() {
		if(tick) {
			super.tick();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof CheckBox) {
					if(((CheckBox) e).getChecked() == true) {
						if(this.cons != null) {
							String d = this.dest.text;
							String f = this.forc.text;
							String t = this.tama.text;
							String newSF = "";
							String newSD = "";
							String newST = "";
							for(int ii = 0; ii < (f).length(); ii++) {
								if(String.valueOf((f).charAt(ii)).equals("1") || 
										String.valueOf((f).charAt(ii)).equals("2") || 
										String.valueOf((f).charAt(ii)).equals("3") ||
										String.valueOf((f).charAt(ii)).equals("4") || 
										String.valueOf((f).charAt(ii)).equals("5") || 
										String.valueOf((f).charAt(ii)).equals("6") ||
										String.valueOf((f).charAt(ii)).equals("7") || 
										String.valueOf((f).charAt(ii)).equals("8") || 
										String.valueOf((f).charAt(ii)).equals("9") ||
										String.valueOf((f).charAt(ii)).equals("0")) {
									newSF+=f.charAt(ii);
								}
							}
							for(int ii = 0; ii < (t).length(); ii++) {
								if(String.valueOf((t).charAt(ii)).equals("1") || 
										String.valueOf((t).charAt(ii)).equals("2") || 
										String.valueOf((t).charAt(ii)).equals("3") ||
										String.valueOf((t).charAt(ii)).equals("4") || 
										String.valueOf((t).charAt(ii)).equals("5") || 
										String.valueOf((t).charAt(ii)).equals("6") ||
										String.valueOf((t).charAt(ii)).equals("7") || 
										String.valueOf((t).charAt(ii)).equals("8") || 
										String.valueOf((t).charAt(ii)).equals("9") ||
										String.valueOf((t).charAt(ii)).equals("0")) {
									newST+=t.charAt(ii);
								}
							}
							for(int ii = 0; ii < (d).length(); ii++) {
								if(String.valueOf((d).charAt(ii)).equals("1") || 
										String.valueOf((d).charAt(ii)).equals("2") || 
										String.valueOf((d).charAt(ii)).equals("3") ||
										String.valueOf((d).charAt(ii)).equals("4") || 
										String.valueOf((d).charAt(ii)).equals("5") || 
										String.valueOf((d).charAt(ii)).equals("6") ||
										String.valueOf((d).charAt(ii)).equals("7") || 
										String.valueOf((d).charAt(ii)).equals("8") || 
										String.valueOf((d).charAt(ii)).equals("9") ||
										String.valueOf((d).charAt(ii)).equals("0")) {
									newSD+=d.charAt(ii);
								}
							}
							if(newSF != "" && newSD != "" && newST != "") {
								if(Integer.parseInt(newSF) < Integer.parseInt(newST) && Integer.parseInt(newSD) < Integer.parseInt(newST)) {
									this.movi.text = "7";
								}else if(Integer.parseInt(newSF) > Integer.parseInt(newST) && Integer.parseInt(newSD) > Integer.parseInt(newST)) {
									this.movi.text = "9";
								}else if(Integer.parseInt(newSF) >= Integer.parseInt(newST) || Integer.parseInt(newSD) >= Integer.parseInt(newST)) {
									this.movi.text = "8";
								}
							}
						}
					}
				}
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
