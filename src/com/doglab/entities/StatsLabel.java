package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class StatsLabel extends Label{

	public static Color life_color = Color.RED, sanity_color = Color.BLUE, magic_color = Color.WHITE;
	
	private BufferedImage barL, barS, barO;
	private TextLabel life, sani, ocul, extrada, corpo, expPar, lifePlayer, maxLie, sanityPlayer,
	maxSanityPlayer, ocultismoPlayer, maxOcultismoPlayer, body, extraDamage,paranormalExp, arm,
	armadura;
	private Dice dice;
	private int inLocal = 0;

	public StatsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		barS = Game.bars.getSprite(0, 0, 454, 98);
		barL = Game.bars.getSprite(0, 98, 454, 98);
		barO = Game.bars.getSprite(0, 196, 454, 98);
		life = new TextLabel(getX(), getY()+15, 30, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Vida", 0, false);
		lifePlayer = new TextLabel(getX()+150, getY()+43, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2, true);
		lifePlayer.canClick(true);
		maxLie = new TextLabel(getX()+160, getY()+43, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0, true);
		
		CheckBox cb = new CheckBox(getX()+10, getY()+55, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "Lesão Grave");
		labels.add(cb);
		
		CheckBox cb2 = new CheckBox(getX()+110, getY()+55, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "Inconsciente");
		labels.add(cb2);
		
		CheckBox cb3 = new CheckBox(getX()+220, getY()+55, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "Morrendo");
		labels.add(cb3);
		
		sani = new TextLabel(getX(), getY()+95, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sanidade", 0, false);
		sanityPlayer = new TextLabel(getX()+125, getY()+123, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2, true);
		sanityPlayer.canClick(true);
		maxSanityPlayer = new TextLabel(getX()+135, getY()+123, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0, true);
		
		CheckBox cb4 = new CheckBox(getX()+30, getY()+135, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "Traumatizado");
		labels.add(cb4);
		
		CheckBox cb5 = new CheckBox(getX()+160, getY()+135, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "Enlouquecido");
		labels.add(cb5);
		
		ocul = new TextLabel(getX(), getY()+175, 70, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Pontos de Magia", 0, false);
		ocultismoPlayer = new TextLabel(getX()+150, getY()+203, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2, true);
		ocultismoPlayer.canClick(true);
		maxOcultismoPlayer = new TextLabel(getX()+160, getY()+203, 15, 29, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0, true);
		
		extrada = new TextLabel(getX(), getY()+225, 70, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Dano Extra", 1, false);
		corpo = new TextLabel(getX()+100, getY()+225, 40, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Corpo", 1, false);
		expPar = new TextLabel(getX()+165, getY()+225, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Exp. Par.", 1, false);
		armadura = new TextLabel(getX()+240, getY()+225, 60, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Armadura", 1, false);
		
		extraDamage = new TextLabel(getX()+30, getY()+270, 20, 39, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1, true);
		body = new TextLabel(getX()+110, getY()+270, 20, 39, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1, true);
		paranormalExp = new TextLabel(getX()+185, getY()+270, 20, 39, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1, true);
		arm = new TextLabel(getX()+260, getY()+270, 20, 39, 0, null, new Font("sitka banner", Font.BOLD, 41), 
				new Color(0xFFE8EDEB), "1", 1, true);
		extraDamage.canClick(true);
		body.canClick(true);
		paranormalExp.canClick(true);
		arm.canClick(true);
		
		TextLabel dAmount = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0, true);
		TextLabel dLados = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "100", 0, true);
		dice = new Dice(getX()+270, getY()+90, 54, 48, 0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dLados, paranormalExp, dAmount, true, false, sani);
		
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
		labels.add(arm);
		labels.add(armadura);
	}

	public void tick() {
		super.tick();
		if(tick) {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof CheckBox) {
					if(((CheckBox) e).getChecked() == true) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity ee = Game.entities.get(ii);
							if(ee instanceof AtributosLabel) {
								if(((AtributosLabel) ee).cons != null) {
									
									if(((AtributosLabel) ee).tama.text != "" &&
											((AtributosLabel) ee).forc.text != "") {
										int tama = Integer.parseInt(((AtributosLabel) ee).tama.text);
										int forc = Integer.parseInt(((AtributosLabel) ee).forc.text);
										
										this.extraDamage.beginToWrite();
										
										if(tama >= 18 && forc <= 14) {
											this.body.text = "2";
										}else if(tama >= 16) {
											if(forc >= 16) {
												this.body.text = "2";
												this.extraDamage.text = "1d6";
												this.extraDamage.setX(this.extraDamage.getX()-27);
											}else if(forc >= 14) {
												this.body.text = "1";
												this.extraDamage.text = "1d4";
												this.extraDamage.setX(this.extraDamage.getX()-27);
											}
										}
										
										this.extraDamage.writing = false;
									}
									
									String p = ((AtributosLabel) ee).pode.text;
									this.maxOcultismoPlayer.text = p;
									if(p != "") {
										int pp = Integer.parseInt(p);
										if(pp <= 9) {
											this.maxSanityPlayer.text = "40";
										}else if(pp <= 11) {
											this.maxSanityPlayer.text = "50";
										}else if(pp <= 13) {
											this.maxSanityPlayer.text = "60";
										}else if(pp <= 15) {
											this.maxSanityPlayer.text = "70";
										}else if(pp >= 16) {
											this.maxSanityPlayer.text = "80";
										}
										
									}
									
									String c = ((AtributosLabel) ee).cons.text;
									String t = ((AtributosLabel) ee).tama.text;
									String newS = "";
									String newS2 = "";
									for(int iii = 0; iii < (c).length(); iii++) {
										if(String.valueOf((c).charAt(iii)).equals("1") || 
												String.valueOf((c).charAt(iii)).equals("2") || 
												String.valueOf((c).charAt(iii)).equals("3") ||
												String.valueOf((c).charAt(iii)).equals("4") || 
												String.valueOf((c).charAt(iii)).equals("5") || 
												String.valueOf((c).charAt(iii)).equals("6") ||
												String.valueOf((c).charAt(iii)).equals("7") || 
												String.valueOf((c).charAt(iii)).equals("8") || 
												String.valueOf((c).charAt(iii)).equals("9") ||
												String.valueOf((c).charAt(iii)).equals("0")) {
											newS+=c.charAt(iii);
										}
									}
									for(int iii = 0; iii < (t).length(); iii++) {
										if(String.valueOf((t).charAt(iii)).equals("1") || 
												String.valueOf((t).charAt(iii)).equals("2") || 
												String.valueOf((t).charAt(iii)).equals("3") ||
												String.valueOf((t).charAt(iii)).equals("4") || 
												String.valueOf((t).charAt(iii)).equals("5") || 
												String.valueOf((t).charAt(iii)).equals("6") ||
												String.valueOf((t).charAt(iii)).equals("7") || 
												String.valueOf((t).charAt(iii)).equals("8") || 
												String.valueOf((t).charAt(iii)).equals("9") ||
												String.valueOf((t).charAt(iii)).equals("0")) {
											newS2+=t.charAt(iii);
										}
									}
									if(newS != "" && newS2 != "") {
										int maxLife = (Integer.parseInt(newS)+Integer.parseInt(newS2))/2;
										this.maxLie.text = Integer.toString(maxLife);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		try{
			if(Integer.parseInt(this.maxLie.text) != 0) {
				g.drawImage(barO,getX()+inLocal, getY()+inLocal+20-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.lifePlayer.text))/Integer.parseInt(this.maxLie.text), 25, null);
				g.setColor(new Color(life_color.getRed() ,life_color.getGreen(), life_color.getBlue(), 210));
				g.fillRect(getX()+inLocal, getY()+inLocal+20-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.lifePlayer.text))/Integer.parseInt(this.maxLie.text), 25);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try{
			if(Integer.parseInt(this.maxSanityPlayer.text) != 0) {
				g.drawImage(barO, getX()+inLocal, getY()+inLocal+100-Game.roller.getY()*Game.roller.step, (260*Integer.parseInt(this.sanityPlayer.text))/Integer.parseInt(this.maxSanityPlayer.text), 25, null);
				g.setColor(new Color(sanity_color.getRed() ,sanity_color.getGreen(), sanity_color.getBlue(), 210));
				g.fillRect(getX()+inLocal, getY()+inLocal+100-Game.roller.getY()*Game.roller.step, (260*Integer.parseInt(this.sanityPlayer.text))/Integer.parseInt(this.maxSanityPlayer.text), 25);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		try{
			if(Integer.parseInt(this.maxOcultismoPlayer.text) != 0) {
				g.drawImage(barO, getX()+inLocal, getY()+inLocal+180-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.ocultismoPlayer.text))/Integer.parseInt(this.maxOcultismoPlayer.text), 25,null);
				g.setColor(new Color(magic_color.getRed() ,magic_color.getGreen(), magic_color.getBlue(), 210));
				g.fillRect(getX()+inLocal, getY()+inLocal+180-Game.roller.getY()*Game.roller.step, (320*Integer.parseInt(this.ocultismoPlayer.text))/Integer.parseInt(this.maxOcultismoPlayer.text), 25);
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX()+inLocal, getY()+inLocal+20-Game.roller.getY()*Game.roller.step, 320, 25);
		g.drawRect(getX()+inLocal, getY()+inLocal+100-Game.roller.getY()*Game.roller.step, 260, 25);
		g.drawRect(getX()+inLocal, getY()+inLocal+180-Game.roller.getY()*Game.roller.step, 320, 25);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 23));
		g.drawString("/", getX()+inLocal+150, getY()+inLocal+38-Game.roller.getY()*Game.roller.step);
		g.drawString("/", getX()+inLocal+150, getY()+inLocal+198-Game.roller.getY()*Game.roller.step);
		g.drawString("/", getX()+inLocal+125, getY()+inLocal+118-Game.roller.getY()*Game.roller.step);	
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+10, getY()+inLocal+280-Game.roller.getY()*Game.roller.step, getX()+70, getY()+inLocal+280-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+90, getY()+inLocal+280-Game.roller.getY()*Game.roller.step, getX()+150, getY()+inLocal+280-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+165, getY()+inLocal+280-Game.roller.getY()*Game.roller.step, getX()+inLocal+225, getY()+inLocal+280-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+240, getY()+inLocal+280-Game.roller.getY()*Game.roller.step, getX()+inLocal+300, getY()+inLocal+280-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
	}
	
}
