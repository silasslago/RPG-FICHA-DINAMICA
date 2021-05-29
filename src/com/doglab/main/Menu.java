package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.doglab.entities.AtributosLabel;
import com.doglab.entities.CharacterLabel;
import com.doglab.entities.CheckBox;
import com.doglab.entities.CombatLabel;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.Entity;
import com.doglab.entities.FastSkillsLabel;
import com.doglab.entities.GunLabel;
import com.doglab.entities.IconLabel;
import com.doglab.entities.InventoryLabel;
import com.doglab.entities.ItemLabel;
import com.doglab.entities.OptionsLabel;
import com.doglab.entities.ReadmeLabel;
import com.doglab.entities.SkillLabel;
import com.doglab.entities.Skills;
import com.doglab.entities.SquareTextLabel;
import com.doglab.entities.StatsLabel;
import com.doglab.entities.TextLabel;

public class Menu {

	private static String version = "v4.3";
	public static boolean showReadme = true;
	
	private final Color BLACK = new Color(0xFF000000);
	private Color bg = BLACK;
	public static Font specialElite;
	private BufferedImage icon;
	
	public Menu() {
		try {
			specialElite = Font.createFont(Font.TRUETYPE_FONT, new File("res/SpecialElite-Regular.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/SpecialElite-Regular.ttf")));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		DetailsLabel detailsLabel = new DetailsLabel(30, 100, Game.WIDTH/2-50, 370, 0, null);
		Game.entities.add(detailsLabel);
		IconLabel iconLabel = new IconLabel(346, 80, 285, 160, 0, null);
		Game.entities.add(iconLabel);
		StatsLabel statsLabel = new StatsLabel(340, 250,(int)(Game.WIDTH/2.18), 440, 0, null);
		Game.entities.add(statsLabel);
		TextLabel title = new TextLabel(240, 60, 200, 29, 0, null, specialElite.deriveFont(29.0f), 
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1, false);
		Game.entities.add(title);
		AtributosLabel atrLabel = new AtributosLabel(25, 550, 300, 480, 0, null);
		Game.entities.add(atrLabel);
		FastSkillsLabel fastSLabel = new FastSkillsLabel(340, 550, 320, 100, 0, null);
		Game.entities.add(fastSLabel);
		CombatLabel combatLabel = new CombatLabel(10, 1050, Game.WIDTH*Game.SCALE-30, 200, 0, null);
		Game.entities.add(combatLabel);
		Skills skills = new Skills(10, 1270, Game.WIDTH*Game.SCALE-30, 600, 0, null);
		Game.entities.add(skills);
		InventoryLabel inventory = new InventoryLabel(10, 1890, Game.WIDTH*Game.SCALE-30, 300, 0, null);
		Game.entities.add(inventory);
		CheckBox cb = new CheckBox(30, 83, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "COC System");
		Game.entities.add(cb);
		CharacterLabel characterLabel = new CharacterLabel(340, 820, 320, 210, 0, null);
		Game.entities.add(characterLabel);
		OptionsLabel optionsLabel = new OptionsLabel(0, 0-45, Game.WIDTH, 45, 0, Game.spr_entities.getSprite(26, 231, 25, 25));
		Game.entities.add(optionsLabel);
		File file = new File("info.txt");
		if(file.exists()) {
			String spr = loadGame();
			String[] sprD = spr.split("/");
			if(sprD[0].equals("v4.0")) {
				aplySaveOlder(loadGame());
			}else if(sprD[0].equals("v4.1")) {
				aplySaveOlder1(loadGame());
			}else if(sprD[0].equals("v4.2")) {
				aplySaveOlder2(loadGame());
			}else if(sprD[0].equals("v4.3")) {
				if(sprD[1].equals("false")) {
					showReadme = false;
				}
				aplySaveCurrent(loadGame());
			}
		}
		if(showReadme) {
			ReadmeLabel rL = new ReadmeLabel(30, 30, Game.WIDTH*Game.SCALE-70, Game.HEIGHT*Game.SCALE-40, 0, null);
			Game.entities.add(rL);
		}
		try {
			icon = ImageIO.read(getClass().getResource("/dogcon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void saveGame(String[] val1, int[] val2, int[] val3, String[] val4) {
		
		File file = new File("info.txt");
		if(file.exists()) {
			file.delete();
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("info.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			write.write(version);
			write.newLine();
			write.write(Boolean.toString(showReadme));
			write.newLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < val1.length; i++) {
			String current = Integer.toString(val3[i]);
			current+=";";
			current+=val1[i];
			if(i < val2.length) {
				current+=";";
				current+=val2[i];
			}
			current+=";";
			current+=val4[i];
			try {
				write.write(current);
				if(i < val1.length - 1) {
					write.newLine();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void save() {
		try {
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof OptionsLabel) {
					((OptionsLabel) e).saving = true;
				}
			}
			
			int[] values = new int[1];
			String[] skills = new String[1];
			int[] id = new int[1];
			String[] x = new String[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Skills) {
					values = new int[((Skills)e).squares.size()];
					skills = new String[((Skills)e).squares.size()];
					id = new int[((Skills)e).squares.size()];
					x = new String[((Skills)e).squares.size()];
					for(int ii = 0; ii < ((Skills)e).squares.size(); ii++) {
						SquareTextLabel sTL = ((Skills)e).squares.get(ii);
						String s = "";
						for(int iii = 0; iii < sTL.labels.size(); iii++) {
							TextLabel l = (TextLabel)sTL.labels.get(iii);
							id[ii] = ii;
							if(iii < 1) {
								s+= l.getX();
								s+=";";
								values[ii] = Integer.parseInt(l.text);
							}else {
								s+= l.getX();
								skills[ii] = l.text;
							}
						}
						x[ii] = s;
					}
				}
			}
			int[] id2 = new int[1];
			int[] x2 = new int[1];
			String[] details = new String[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof DetailsLabel) {
					id2 = new int[((DetailsLabel) e).labels.size()];
					details = new String[((DetailsLabel) e).labels.size()];
					x2 = new int[((DetailsLabel) e).labels.size()];
					for(int ii = 0; ii < ((DetailsLabel)e).labels.size(); ii++) {
						id2[ii] = ii;
						x2[ii] = ((TextLabel)((DetailsLabel) e).labels.get(ii)).getX();
						details[ii] = ((TextLabel)((DetailsLabel) e).labels.get(ii)).text;
					}
				}
			}
			
			int[] id3 = new int[1];
			int[] x3 = new int[1];
			String[] stats = new String[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof StatsLabel) {
					id3 = new int[((StatsLabel) e).labels.size()];
					stats = new String[((StatsLabel) e).labels.size()];
					x3 = new int[((StatsLabel) e).labels.size()];
					for(int ii = 0; ii < ((StatsLabel)e).labels.size(); ii++) {
						if(((StatsLabel)e).labels.get(ii) instanceof TextLabel) {
							id3[ii] = ii;
							x3[ii] = ((TextLabel)((StatsLabel) e).labels.get(ii)).getX();
							stats[ii] = ((TextLabel)((StatsLabel) e).labels.get(ii)).text;
						}else if(((StatsLabel)e).labels.get(ii) instanceof CheckBox) {
							id3[ii] = ii;
							x3[ii] = ((StatsLabel) e).labels.get(ii).getX();
							if(((CheckBox)((StatsLabel)e).labels.get(ii)).getChecked() == true) {
								stats[ii] = "true";
							}else {
								stats[ii] = "false";
							}
							for(int iii = 0; iii < ((CheckBox)((StatsLabel)e).labels.get(ii)).getArrayList().size(); iii++) {
								stats[ii]+=";";
								stats[ii]+=((CheckBox)((StatsLabel)e).labels.get(ii)).getArrayList().get(iii).text;
							}
						}else {
							id3[ii] = 99999999;
						}
					}
				}
			}
			
			String[] atributos = new String[1];
			int[] x4 = new int[1];
			int[] id4 = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof AtributosLabel) {
					atributos = new String[((AtributosLabel)e).labels.size()];
					id4 = new int[((AtributosLabel)e).labels.size()];
					x4 = new int[((AtributosLabel)e).labels.size()];
					for(int ii = 0; ii < ((AtributosLabel)e).labels.size(); ii++) {
						if(((AtributosLabel)e).labels.get(ii) instanceof TextLabel) {
							id4[ii] = ii;
							x4[ii] = ((TextLabel)((AtributosLabel)e).labels.get(ii)).getX();
							atributos[ii] = ((TextLabel)((AtributosLabel)e).labels.get(ii)).text;
						}else {
							id4[ii] = 99999999;
						}
					}
				}
			}
			
			String[] image = new String[1];
			int[] x5 = new int[1];
			int id5[] = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof IconLabel) {
					image[0] = ((IconLabel)e).characterIcon.path;
					x5[0] = e.getX();
					id5[0] = 0;
					System.out.println(image[0]);
				}
			}
			
			String[] combate = new String[1];
			int[] id6 = new int[1];
			int[] x6 = new int[1];
			String[] gunLabels = new String[1];
			int[] id7 = new int[1];
			int[] x7 = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof CombatLabel) {
					combate = new String[((CombatLabel)e).labels.size()];
					id6 = new int[((CombatLabel)e).labels.size()];
					x6 = new int[((CombatLabel)e).labels.size()];
					for(int ii = 0; ii < ((CombatLabel)e).labels.size(); ii++) {
						if(((CombatLabel)e).labels.get(ii) instanceof TextLabel) {
							combate[ii] = ((TextLabel)((CombatLabel)e).labels.get(ii)).text;
							id6[ii] = ii;
							x6[ii] = ((TextLabel)((CombatLabel)e).labels.get(ii)).getX();
						}else {
							id6[ii] = 99999999;
						}
					}
					id7 = new int[((CombatLabel)e).getGunArrayList().size()];
					gunLabels = new String[((CombatLabel)e).getGunArrayList().size()];
					x7 = new int[((CombatLabel)e).getGunArrayList().size()];
					for(int ii = 0; ii < ((CombatLabel)e).getGunArrayList().size(); ii++) {
						id7[ii] = ii;
						x7[ii] = (((CombatLabel)e).getGunArrayList().get(ii).labels.get(ii)).getX();
						for(int iii = 0; iii < ((CombatLabel)e).getGunArrayList().get(ii).labels.size()-1; iii++) {
							if(((CombatLabel)e).getGunArrayList().get(ii).labels.get(iii+1) instanceof TextLabel){
								String s = ((TextLabel)((CombatLabel)e).getGunArrayList().get(ii).labels.get(iii+1)).text;
								if(iii == 0) {
									gunLabels[ii]=s;
									gunLabels[ii]+=";";
								}else {
									gunLabels[ii]+=s;
									if(iii < ((CombatLabel)e).getGunArrayList().get(ii).labels.size()-2) {
										gunLabels[ii]+=";";
									}
								
								}
							}
						}
					}
				}
			}
			
			String[] inventory = new String[1];
			int[] id8 = new int[1];
			int[] x8 = new int[1];
			String[] itemLabels = new String[1];
			int[] id9 = new int[1];
			String[] x9 = new String[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof InventoryLabel) {
					inventory = new String[((InventoryLabel)e).labels.size()];
					id8 = new int[((InventoryLabel)e).labels.size()];
					x8 = new int[((InventoryLabel)e).labels.size()];
					for(int ii = 0; ii < ((InventoryLabel)e).labels.size(); ii++) {
						if(((InventoryLabel)e).labels.get(ii) instanceof TextLabel) {
							inventory[ii] = ((TextLabel)((InventoryLabel)e).labels.get(ii)).text;
							id8[ii] = ii;
							x8[ii] = ((TextLabel)((InventoryLabel)e).labels.get(ii)).getX();
						}else {
							id8[ii] = 99999999;
						}
					}
					
					id9 = new int[((InventoryLabel)e).getItemArrayList().size()];
					itemLabels = new String[((InventoryLabel)e).getItemArrayList().size()];
					x9 = new String[((InventoryLabel)e).getItemArrayList().size()];
					for(int ii = 0; ii < ((InventoryLabel)e).getItemArrayList().size(); ii++) {
						id9[ii] = ii;
						x9[ii] = Integer.toString((((InventoryLabel)e).getItemArrayList().get(ii).labels.get(1)).getX());
						x9[ii]+=";";
						x9[ii]+=Integer.toString((((InventoryLabel)e).getItemArrayList().get(ii).labels.get(2)).getX());
						for(int iii = 0; iii < ((InventoryLabel)e).getItemArrayList().get(ii).labels.size()-1; iii++) {
							if(((InventoryLabel)e).getItemArrayList().get(ii).labels.get(iii+1) instanceof TextLabel){
								String s = ((TextLabel)((InventoryLabel)e).getItemArrayList().get(ii).labels.get(iii+1)).text;
								if(iii == 0) {
									itemLabels[ii]=s;
									itemLabels[ii]+=";";
								}else {
									itemLabels[ii]+=s;
									if(iii<((InventoryLabel)e).getItemArrayList().get(ii).labels.size()-2) {
										itemLabels[ii]+=";";	
									}
								}
							}
						}
					}
				}
			}
			
			String[] systemValue = new String[1];
			int[] id10 = new int[1];
			int[] x10 = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof CheckBox) {
					id10[0] = 0;
					x10[0] = e.getX();
					if(((CheckBox) e).getChecked() == true) {
						systemValue[0] = "true";
					}else {
						systemValue[0] = "false";
					}
					for(int ii = 0; ii < ((CheckBox) e).getArrayList().size(); ii++) {
						systemValue[0]+=";";
						systemValue[0]+=((CheckBox) e).getArrayList().get(ii).text;
					}
				}
			}
			
			String[] fastSkills = new String[1];
			int[] id11 = new int[1];
			int[] x11 = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof FastSkillsLabel) {
					fastSkills = new String[((FastSkillsLabel) e).labels.size()];
					id11 = new int[((FastSkillsLabel) e).labels.size()];
					x11 = new int[((FastSkillsLabel) e).labels.size()];
					for(int ii = 0; ii < ((FastSkillsLabel) e).labels.size(); ii++) {
						if(((FastSkillsLabel) e).labels.get(ii) instanceof TextLabel) {
							id11[ii] = ii;
							x11[ii] = ((FastSkillsLabel) e).labels.get(ii).getX();
							fastSkills[ii] = ((TextLabel)((FastSkillsLabel) e).labels.get(ii)).text;
						}else {
							id11[ii] = 99999999;
						}
					}
				}
			}
			
			String[] value = new String[1];
			int[] id12 = new int[1];
			int[] x12 = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof FastSkillsLabel) {
					value = new String[((FastSkillsLabel) e).getSkillArrayList().size()];
					id12 = new int[((FastSkillsLabel) e).getSkillArrayList().size()];
					x12 = new int[((FastSkillsLabel) e).getSkillArrayList().size()];
					for(int ii = 0; ii < ((FastSkillsLabel) e).getSkillArrayList().size(); ii++) {
						SkillLabel sL = ((FastSkillsLabel) e).getSkillArrayList().get(ii);
						id12[ii] = ii;
						x12[ii] = sL.labels.get(0).getX();
						value[ii] = Integer.toString(sL.getID());
					}
				}
			}
			
			String[] characters = new String[1];
			int[] x13 = new int[1];
			int id13[] = new int[1];
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof CharacterLabel) {
					x13 = new int[((CharacterLabel) e).getPaths().size()];
					id13 = new int[((CharacterLabel) e).getPaths().size()];
					characters = new String[((CharacterLabel) e).getPaths().size()];
					for(int ii = 0; ii < ((CharacterLabel) e).getPaths().size(); ii++) {
						id13[ii] = ii;
						x13[ii] = 0;
						characters[ii] = ((CharacterLabel) e).getPaths().get(ii);
					}
				}
			}
			
			String[] labels = new String[details.length + skills.length + stats.length + atributos.length + 
			                             image.length + combate.length + gunLabels.length + inventory.length 
			                             + itemLabels.length + systemValue.length + fastSkills.length + value.length +
			                             characters.length];
			int[] ids = new int[id.length + id2.length + id3.length + id4.length + id5.length + id6.length + 
			                    id7.length + id8.length + id9.length + id10.length + id11.length + id12.length +
			                    id13.length];
			int[] valuesLabels = new int[values.length];
			String[] xLabels = new String[x.length +  x2.length + x3.length + x4.length + x5.length + x6.length + x7.length +
			                        x8.length + x9.length + x10.length + x11.length + x12.length + x13.length];
			for(int i = 0; i < labels.length; i++) {
				if(i < skills.length) {
					labels[i] = skills[i];
					ids[i] = id[i];
					valuesLabels[i] = values[i];
					xLabels[i] = x[i];
				}else if(i < skills.length + details.length) {
					xLabels[i] = Integer.toString(x2[i-skills.length]);
					labels[i] = details[i-skills.length];
					ids[i] = id2[i-skills.length];
				}else if(i < skills.length + details.length + stats.length) {
					xLabels[i] = Integer.toString(x3[i-skills.length-details.length]);
					labels[i] = stats[i-skills.length-details.length];
					ids[i] = id3[i-skills.length-details.length];
				}else if(i < skills.length + details.length + stats.length + atributos.length) {
					labels[i] = atributos[i-skills.length-details.length-stats.length];
					ids[i] = id4[i-skills.length-details.length-stats.length];
					xLabels[i] = Integer.toString(x4[i-skills.length-details.length-stats.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length){
					labels[i] = image[i-skills.length-details.length-stats.length-atributos.length];
					ids[i] = id5[i-skills.length-details.length-stats.length-atributos.length];
					xLabels[i] = Integer.toString(x5[i-skills.length-details.length-stats.length-atributos.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length) {
					labels[i] = combate[i-skills.length-details.length-stats.length-atributos.length-image.length];
					ids[i] = id6[i-skills.length-details.length-stats.length-atributos.length-image.length];
					xLabels[i] = Integer.toString(x6[i-skills.length-details.length-stats.length-atributos.length-image.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length) {
					labels[i] = gunLabels[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length];
					ids[i] = id7[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length];
					xLabels[i] = Integer.toString(x7[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length) {
					labels[i] = inventory[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length];
					ids[i] = id8[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length];
					xLabels[i] = Integer.toString(x8[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length) {
					labels[i] = itemLabels[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
					ids[i] = id9[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
					xLabels[i] = x9[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length + systemValue.length){
					labels[i] = systemValue[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length];
					ids[i] = id10[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length];
					xLabels[i] = Integer.toString(x10[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length + systemValue.length + fastSkills.length) {
					labels[i] = fastSkills[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length];
					ids[i] = id11[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length];
					xLabels[i] = Integer.toString(x11[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length + systemValue.length + fastSkills.length + value.length) {
					labels[i] = value[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length];
					ids[i] = id12[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length];
					xLabels[i] = Integer.toString(x12[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length]);
				}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length + systemValue.length + fastSkills.length + value.length + characters.length) {
					labels[i] = characters[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length-value.length];
					ids[i] = id13[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length-value.length];
					xLabels[i] = Integer.toString(x13[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length-itemLabels.length-systemValue.length-fastSkills.length-value.length]);
				}
			}
			Menu.saveGame(labels, valuesLabels, ids, xLabels);
			
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof OptionsLabel) {
					((OptionsLabel) e).saving = false;
					((OptionsLabel) e).saved = true;
				}
			}
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
			
	}

	public static String loadGame() {
		String line = "";
		File file = new File("info.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("info.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						line+=singleLine;
						line+="/";
					}	
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void aplySaveOlder(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == 11) {
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CombatLabel) {
						ArrayList<GunLabel> gLL = new ArrayList<GunLabel>();
					
						gLL = ((CombatLabel)e).getGunArrayList();
						
						GunLabel gL = new GunLabel(((CombatLabel) e).labelX, 
								((CombatLabel) e).labelY+(((CombatLabel) e).labelY*labelsCAmount), 
								((CombatLabel) e).labelW, ((CombatLabel) e).labelH, 
								0, null, ((CombatLabel) e).labelY+((CombatLabel) e).labelH+5);
						
						labelsCAmount++;
						for(int iii = 0; iii < gL.labels.size(); iii++) {
							Entity ee = gL.labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						gLL.add(gL);
						((CombatLabel) e).setGunArrayList(gLL);
						((CombatLabel) e).addB.labels = ((CombatLabel)e).getGunArrayList();
					}
				}
			}else if(str2.length == 4) { 
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof InventoryLabel) {
						ArrayList<ItemLabel> iLL = new ArrayList<ItemLabel>();
						((InventoryLabel) e).addB.actionPerformed();
						iLL = ((InventoryLabel)e).getItemArrayList();
						for(int iii = 0; iii < iLL.get(labelsIAmount).labels.size(); iii++) {
							Entity ee = iLL.get(labelsIAmount).labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == 3) {
				for(int index = 0; index < 47; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof Skills) {
								for(int iii = 0; iii < ((Skills)e).squares.size(); iii++) {
									SquareTextLabel sTL = ((Skills) e).squares.get(iii);
									if(iii == index) {
										((TextLabel)sTL.labels.get(0)).text = str2[2];
										((TextLabel)sTL.labels.get(1)).text = str2[1];
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 15) {
				str2Times++;
				for(int index = 0; index < 15; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof DetailsLabel) {
								for(int iii = 0; iii < ((DetailsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										((TextLabel)((DetailsLabel) e).labels.get(iii)).text = str2[1];
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 31) {
				str2Times++;
				for(int index = 0; index < 16; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 5; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii-5 == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 60) {
				str2Times++;
				for(int index = 0; index < 29; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof AtributosLabel) {
								for(int iii = 0; iii < ((AtributosLabel)e).labels.size(); iii++) {
									if(iii == index) {
										System.out.println("iii: "+iii);
										if(((AtributosLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((AtributosLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).characterIcon.setIcon(str2[1]);
								((IconLabel) e).characterIcon.path = str2[1];
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 73) {
				str2Times++;
				for(int index = 0; index < 12; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CombatLabel) {
								for(int iii = 0; iii < ((CombatLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((CombatLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((CombatLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 81) {
				str2Times++;
				for(int index = 0; index < 8; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof InventoryLabel) {
								for(int iii = 0; iii < ((InventoryLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((InventoryLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((InventoryLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}
			
		}
	}
	
	public static void aplySaveOlder1(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CombatLabel) {
						ArrayList<GunLabel> gLL = new ArrayList<GunLabel>();
						gLL = ((CombatLabel)e).getGunArrayList();
						GunLabel gL = new GunLabel(((CombatLabel) e).labelX, 
								((CombatLabel) e).labelY+(((CombatLabel) e).labelY*labelsCAmount), 
								((CombatLabel) e).labelW, ((CombatLabel) e).labelH, 
								0, null, ((CombatLabel) e).labelY+((CombatLabel) e).labelH+5);
						labelsCAmount++;
						for(int iii = 0; iii < gL.labels.size(); iii++) {
							Entity ee = gL.labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						gLL.add(gL);
						((CombatLabel) e).setGunArrayList(gLL);
						((CombatLabel) e).addB.labels = ((CombatLabel)e).getGunArrayList();
					}
				}
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof Skills) {
								for(int iii = 0; iii < ((Skills)e).squares.size(); iii++) {
									SquareTextLabel sTL = ((Skills) e).squares.get(iii);
									if(iii == index) {
										((TextLabel)sTL.labels.get(0)).text = str2[2];
										((TextLabel)sTL.labels.get(1)).text = str2[1];
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof InventoryLabel) {
						ArrayList<ItemLabel> iLL = new ArrayList<ItemLabel>();
						((InventoryLabel) e).addB.actionPerformed();
						iLL = ((InventoryLabel)e).getItemArrayList();
						for(int iii = 0; iii < iLL.get(labelsIAmount).labels.size(); iii++) {
							Entity ee = iLL.get(labelsIAmount).labels.get(iii);
							if(ee instanceof TextLabel) {
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
				str2Times++;
				for(int index = 0; index < 15; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof DetailsLabel) {
								for(int iii = 0; iii < ((DetailsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										((TextLabel)((DetailsLabel) e).labels.get(iii)).text = str2[1];
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
				str2Times++;
				for(int index = 0; index < 29; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof AtributosLabel) {
								for(int iii = 0; iii < ((AtributosLabel)e).labels.size(); iii++) {
									if(iii == index) {
										
										if(((AtributosLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((AtributosLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).characterIcon.setIcon(str2[1]);
								((IconLabel) e).characterIcon.path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
				str2Times++;
				for(int index = 0; index < 12; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CombatLabel) {
								for(int iii = 0; iii < ((CombatLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((CombatLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((CombatLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 81) {
				str2Times++;
				for(int index = 0; index < 8; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof InventoryLabel) {
								for(int iii = 0; iii < ((InventoryLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((InventoryLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((InventoryLabel) e).labels.get(iii)).text = str2[1];
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public static void aplySaveOlder2(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CombatLabel) {
						ArrayList<GunLabel> gLL = new ArrayList<GunLabel>();
						gLL = ((CombatLabel)e).getGunArrayList();
						GunLabel gL = new GunLabel(((CombatLabel) e).labelX, 
								((CombatLabel) e).labelY+(((CombatLabel) e).labelY*labelsCAmount), 
								((CombatLabel) e).labelW, ((CombatLabel) e).labelH, 
								0, null, ((CombatLabel) e).labelY+((CombatLabel) e).labelH+5);
						labelsCAmount++;
						for(int iii = 0; iii < gL.labels.size(); iii++) {
							Entity ee = gL.labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						gLL.add(gL);
						((CombatLabel) e).setGunArrayList(gLL);
						((CombatLabel) e).addB.labels = ((CombatLabel)e).getGunArrayList();
					}
				}
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof Skills) {
								for(int iii = 0; iii < ((Skills)e).squares.size(); iii++) {
									SquareTextLabel sTL = ((Skills) e).squares.get(iii);
									if(iii == index) {
										((TextLabel)sTL.labels.get(0)).text = str2[2];
										((TextLabel)sTL.labels.get(1)).text = str2[1];
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof InventoryLabel) {
						ArrayList<ItemLabel> iLL = new ArrayList<ItemLabel>();
						((InventoryLabel) e).addB.actionPerformed();
						iLL = ((InventoryLabel)e).getItemArrayList();
						for(int iii = 0; iii < iLL.get(labelsIAmount).labels.size(); iii++) {
							Entity ee = iLL.get(labelsIAmount).labels.get(iii);
							if(ee instanceof TextLabel) {
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
				str2Times++;
				for(int index = 0; index < 15; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof DetailsLabel) {
								for(int iii = 0; iii < ((DetailsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										((TextLabel)((DetailsLabel) e).labels.get(iii)).text = str2[1];
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
				str2Times++;
				for(int index = 0; index < 29; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof AtributosLabel) {
								for(int iii = 0; iii < ((AtributosLabel)e).labels.size(); iii++) {
									if(iii == index) {
										
										if(((AtributosLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((AtributosLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).characterIcon.setIcon(str2[1]);
								((IconLabel) e).characterIcon.path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
				str2Times++;
				for(int index = 0; index < 12; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CombatLabel) {
								for(int iii = 0; iii < ((CombatLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((CombatLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((CombatLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 82) {
				str2Times++;
				for(int index = 0; index < 8; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof InventoryLabel) {
								for(int iii = 0; iii < ((InventoryLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((InventoryLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((InventoryLabel) e).labels.get(iii)).text = str2[1];
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 86) {
				str2Times++;
				for(int index = 0; index < 4; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof FastSkillsLabel) {
								for(int iii = 0; iii < ((FastSkillsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((FastSkillsLabel) e).labels.get(iii) instanceof TextLabel) {
											((FastSkillsLabel) e).labels.get(iii).setX(Integer.parseInt(str2[2]));
											((TextLabel)((FastSkillsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4){
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof FastSkillsLabel) {
						ArrayList<SkillLabel> sLL = new ArrayList<SkillLabel>();
						sLL = ((FastSkillsLabel) e).getSkillArrayList();
						SkillLabel sL = new SkillLabel(((FastSkillsLabel) e).addB.labelX, 
								((FastSkillsLabel) e).addB.labelY+10+((((FastSkillsLabel) e).addB.labelH+5)*((FastSkillsLabel) e).addB.labelsAmount),
								((FastSkillsLabel) e).addB.labelW, ((FastSkillsLabel) e).addB.labelH-5, 0, null, Integer.parseInt(str2[1]), ((FastSkillsLabel) e).addB.labelY+10);
						sLL.add(sL);
						((FastSkillsLabel) e).setSkillArrayList(sLL);
					}
				}
			}
		}
	}

	public static void aplySaveCurrent(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CombatLabel) {
						ArrayList<GunLabel> gLL = new ArrayList<GunLabel>();
						gLL = ((CombatLabel)e).getGunArrayList();
						GunLabel gL = new GunLabel(((CombatLabel) e).labelX, 
								((CombatLabel) e).labelY+(((CombatLabel) e).labelY*labelsCAmount), 
								((CombatLabel) e).labelW, ((CombatLabel) e).labelH, 
								0, null, ((CombatLabel) e).labelY+((CombatLabel) e).labelH+5);
						labelsCAmount++;
						for(int iii = 0; iii < gL.labels.size(); iii++) {
							Entity ee = gL.labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						gLL.add(gL);
						((CombatLabel) e).setGunArrayList(gLL);
						((CombatLabel) e).addB.labels = ((CombatLabel)e).getGunArrayList();
					}
				}
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof Skills) {
								for(int iii = 0; iii < ((Skills)e).squares.size(); iii++) {
									SquareTextLabel sTL = ((Skills) e).squares.get(iii);
									if(iii == index) {
										((TextLabel)sTL.labels.get(0)).text = str2[2];
										((TextLabel)sTL.labels.get(1)).text = str2[1];
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof InventoryLabel) {
						ArrayList<ItemLabel> iLL = new ArrayList<ItemLabel>();
						((InventoryLabel) e).addB.actionPerformed();
						iLL = ((InventoryLabel)e).getItemArrayList();
						for(int iii = 0; iii < iLL.get(labelsIAmount).labels.size(); iii++) {
							Entity ee = iLL.get(labelsIAmount).labels.get(iii);
							if(ee instanceof TextLabel) {
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
				str2Times++;
				for(int index = 0; index < 15; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof DetailsLabel) {
								for(int iii = 0; iii < ((DetailsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										((TextLabel)((DetailsLabel) e).labels.get(iii)).text = str2[1];
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
				str2Times++;
				for(int index = 0; index < 29; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof AtributosLabel) {
								for(int iii = 0; iii < ((AtributosLabel)e).labels.size(); iii++) {
									if(iii == index) {
										
										if(((AtributosLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((AtributosLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).characterIcon.setIcon(str2[1]);
								((IconLabel) e).characterIcon.path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
				str2Times++;
				for(int index = 0; index < 12; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CombatLabel) {
								for(int iii = 0; iii < ((CombatLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((CombatLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((CombatLabel) e).labels.get(iii)).text = str2[1];
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 82) {
				str2Times++;
				for(int index = 0; index < 8; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof InventoryLabel) {
								for(int iii = 0; iii < ((InventoryLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((InventoryLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((InventoryLabel) e).labels.get(iii)).text = str2[1];
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 86) {
				str2Times++;
				for(int index = 0; index < 4; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof FastSkillsLabel) {
								for(int iii = 0; iii < ((FastSkillsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((FastSkillsLabel) e).labels.get(iii) instanceof TextLabel) {
											((FastSkillsLabel) e).labels.get(iii).setX(Integer.parseInt(str2[2]));
											((TextLabel)((FastSkillsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2[1].length() < 3){
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof FastSkillsLabel) {
						ArrayList<SkillLabel> sLL = new ArrayList<SkillLabel>();
						sLL = ((FastSkillsLabel) e).getSkillArrayList();
						SkillLabel sL = new SkillLabel(((FastSkillsLabel) e).addB.labelX, 
								((FastSkillsLabel) e).addB.labelY+10+((((FastSkillsLabel) e).addB.labelH+5)*((FastSkillsLabel) e).addB.labelsAmount),
								((FastSkillsLabel) e).addB.labelW, ((FastSkillsLabel) e).addB.labelH-5, 0, null, Integer.parseInt(str2[1]), ((FastSkillsLabel) e).addB.labelY+10);
						sLL.add(sL);
						((FastSkillsLabel) e).setSkillArrayList(sLL);
					}
				}
			}else if(str2.length == length4) {
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CharacterLabel) {
						((CharacterLabel) e).setIcon(str2[1]);
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.drawImage(icon, 230, 2200-Game.roller.getY()*Game.roller.step, null);
	}
	
	public void tick() {
		
	}
	
}
