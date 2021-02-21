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
		
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Skills) {
				values = new int[((Skills)e).squares.size()];
				skills = new String[((Skills)e).squares.size()];
				id = new int[((Skills)e).squares.size()];
				for(int ii = 0; ii < ((Skills)e).squares.size(); ii++) {
					SquareTextLabel sTL = ((Skills)e).squares.get(ii);
					for(int iii = 0; iii < sTL.labels.size(); iii++) {
						TextLabel l = (TextLabel)sTL.labels.get(iii);
						id[ii] = ii;
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
		String[] details = new String[1];
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof DetailsLabel) {
				id2 = new int[((DetailsLabel) e).labels.size()];
				details = new String[((DetailsLabel) e).labels.size()];
				for(int ii = 0; ii < ((DetailsLabel)e).labels.size(); ii++) {
					id2[ii] = ii;
					details[ii] = ((TextLabel)((DetailsLabel) e).labels.get(ii)).text;
				}
			}
		}
		
		int[] id3 = new int[1];
		String[] stats = new String[1];
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof StatsLabel) {
				id3 = new int[((StatsLabel) e).labels.size()];
				stats = new String[((StatsLabel) e).labels.size()];
				for(int ii = 0; ii < ((StatsLabel)e).labels.size(); ii++) {
					if(((StatsLabel)e).labels.get(ii) instanceof TextLabel) {
						id3[ii] = ii;
						stats[ii] = ((TextLabel)((StatsLabel) e).labels.get(ii)).text;
					}else {
						id3[ii] = 99999999;
					}
				}
			}
		}
		
		String[] atributos = new String[1];
		int[] id4 = new int[1];
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof AtributosLabel) {
				atributos = new String[((AtributosLabel)e).labels.size()];
				id4 = new int[((AtributosLabel)e).labels.size()];
				for(int ii = 0; ii < ((AtributosLabel)e).labels.size(); ii++) {
					if(((AtributosLabel)e).labels.get(ii) instanceof TextLabel) {
						id4[ii] = ii;
						atributos[ii] = ((TextLabel)((AtributosLabel)e).labels.get(ii)).text;
					}else {
						id4[ii] = 99999999;
					}
				}
			}
		}
		
		String[] image = new String[1];
		int id5[] = new int[1];
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof IconLabel) {
				image[0] = ((IconLabel)e).path;
				id5[0] = 0;
			}
		}
		
		
		
		String[] labels = new String[details.length + skills.length + stats.length + atributos.length + image.length];
		int[] ids = new int[id.length + id2.length + id3.length + id4.length + id5.length];
		int[] valuesLabels = new int[values.length];
		for(int i = 0; i < labels.length; i++) {
			if(i < skills.length) {
				labels[i] = skills[i];
				ids[i] = id[i];
				valuesLabels[i] = values[i];
			}else if(i < skills.length + details.length) {
				labels[i] = details[i-skills.length];
				ids[i] = id2[i-skills.length];
			}else if(i < skills.length + details.length + stats.length) {
				labels[i] = stats[i-skills.length-details.length];
				ids[i] = id3[i-skills.length-details.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length) {
				labels[i] = atributos[i-skills.length-details.length-stats.length];
				ids[i] = id4[i-skills.length-details.length-stats.length];
			}else if(i < skills.length + details.length + stats.length + atributos.length + image.length){
				labels[i] = image[i-skills.length-details.length-stats.length-atributos.length];
				ids[i] = id5[i-skills.length-details.length-stats.length-atributos.length];
			}
		}
		Menu.saveGame(labels, valuesLabels, ids);
	}

}
