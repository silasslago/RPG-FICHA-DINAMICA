package com.doglab.entities;

import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class SaveButton extends Button{

	public SaveButton(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void actionPerformed() {
		
		int[] values = new int[1];
		String[] skills = new String[1];
		int[] id = new int[1];
		int[] x = new int[1];
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Skills) {
				values = new int[((Skills)e).squares.size()];
				skills = new String[((Skills)e).squares.size()];
				id = new int[((Skills)e).squares.size()];
				x = new int[((Skills)e).squares.size()];
				for(int ii = 0; ii < ((Skills)e).squares.size(); ii++) {
					SquareTextLabel sTL = ((Skills)e).squares.get(ii);
					for(int iii = 0; iii < sTL.labels.size(); iii++) {
						TextLabel l = (TextLabel)sTL.labels.get(iii);
						id[ii] = ii;
						x[ii] = l.getX();
						if(iii < 1) {
							values[ii] = Integer.parseInt(l.text);
						}else {
							skills[ii] = l.text;
						}
					}
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
				image[0] = ((IconLabel)e).path;
				x5[0] = e.getX();
				id5[0] = 0;
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
					x7[ii] = ((TextLabel)((CombatLabel)e).getGunArrayList().get(ii).labels.get(ii)).getX();
					for(int iii = 0; iii < ((CombatLabel)e).getGunArrayList().get(ii).labels.size()-1; iii++) {
						if(((CombatLabel)e).getGunArrayList().get(ii).labels.get(iii+1) instanceof TextLabel){
							String s = ((TextLabel)((CombatLabel)e).getGunArrayList().get(ii).labels.get(iii+1)).text;
							if(iii == 0) {
								gunLabels[ii]=s;
								gunLabels[ii]+=";";
							}else {
								gunLabels[ii]+=s;
								gunLabels[ii]+=";";
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
		int[] x9 = new int[1];
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
				x9 = new int[((InventoryLabel)e).getItemArrayList().size()];
				for(int ii = 0; ii < ((InventoryLabel)e).getItemArrayList().size(); ii++) {
					id9[ii] = ii;
					x9[ii] = ((TextLabel)((InventoryLabel)e).getItemArrayList().get(ii).labels.get(ii)).getX();
					for(int iii = 0; iii < ((InventoryLabel)e).getItemArrayList().get(ii).labels.size()-1; iii++) {
						if(((InventoryLabel)e).getItemArrayList().get(ii).labels.get(iii+1) instanceof TextLabel){
							String s = ((TextLabel)((InventoryLabel)e).getItemArrayList().get(ii).labels.get(iii+1)).text;
							if(iii == 0) {
								itemLabels[ii]=s;
								
								itemLabels[ii]+=";";
							}else {
								itemLabels[ii]+=s;
								itemLabels[ii]+=";";
							}
						}
					}
				}
			}
		}
		
		String[] labels = new String[details.length + skills.length + stats.length + atributos.length + 
		                             image.length + combate.length + gunLabels.length + inventory.length 
		                             + itemLabels.length];
		int[] ids = new int[id.length + id2.length + id3.length + id4.length + id5.length + id6.length + 
		                    id7.length + id8.length + id9.length];
		int[] valuesLabels = new int[values.length];
		int[] xLabels = new int[x.length +  x2.length + x3.length + x4.length + x5.length + x6.length + x7.length +
		                        x8.length + x9.length];
		for(int i = 0; i < labels.length; i++) {
			if(i < skills.length) {
				labels[i] = skills[i];
				ids[i] = id[i];
				valuesLabels[i] = values[i];
				xLabels[i] = x[i];
			}else if(i < skills.length + details.length) {
				xLabels[i] = x2[i-skills.length];
				labels[i] = details[i-skills.length];
				ids[i] = id2[i-skills.length];
			}else if(i < skills.length + details.length + stats.length) {
				xLabels[i] = x3[i-skills.length-details.length];
				labels[i] = stats[i-skills.length-details.length];
				ids[i] = id3[i-skills.length-details.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length) {
				labels[i] = atributos[i-skills.length-details.length-stats.length];
				ids[i] = id4[i-skills.length-details.length-stats.length];
				xLabels[i] = x4[i-skills.length-details.length-stats.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length){
				labels[i] = image[i-skills.length-details.length-stats.length-atributos.length];
				ids[i] = id5[i-skills.length-details.length-stats.length-atributos.length];
				xLabels[i] = x5[i-skills.length-details.length-stats.length-atributos.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length) {
				labels[i] = combate[i-skills.length-details.length-stats.length-atributos.length-image.length];
				ids[i] = id6[i-skills.length-details.length-stats.length-atributos.length-image.length];
				xLabels[i] = x6[i-skills.length-details.length-stats.length-atributos.length-image.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length) {
				labels[i] = gunLabels[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length];
				ids[i] = id7[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length];
				xLabels[i] = x7[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length) {
				labels[i] = inventory[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length];
				ids[i] = id8[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length];
				xLabels[i] = x8[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length + combate.length + gunLabels.length + inventory.length + itemLabels.length) {
				labels[i] = itemLabels[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
				ids[i] = id9[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
				xLabels[i] = x9[i-skills.length-details.length-stats.length-atributos.length-image.length-combate.length-gunLabels.length-inventory.length];
			}
		}
		Menu.saveGame(labels, valuesLabels, ids, xLabels);
	}

}
