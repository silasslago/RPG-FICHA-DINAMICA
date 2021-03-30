package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class SaveButton extends Button{

	public SaveButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		Entity e = new Entity(getX(), getY(), getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		
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
		
	}

	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY(), width, height, null);
	}
	
}
