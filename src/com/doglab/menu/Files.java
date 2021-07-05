package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.entities.Roller;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Files{

	private String path = "files/";
	
	public ArrayList<Label> cm;
	private ArrayList<Label> insideFolders;
	public Roller roller;
	
	public ArrayList<Label> labels;
	public int labelIAmount = 1, labelPAmount = 0, labelMAmount = 0;
	public int labelsAmount = 1;
	
	private boolean firstTime = false;
	private HomeButton btn;
	
	public Files() {
		labels = new ArrayList<Label>();
		Addition adt = new Addition(50, 130, 180, 250);
		labels.add(adt);
		int width = 10;
		roller = new Roller(Game.WIDTH-width, 0, width, 170, 4, null, false,
				Game.WIDTH-width, 0, width, Game.HEIGHT);
		cm = new ArrayList<Label>();
		insideFolders = labels;
		btn = new HomeButton(90, 100, 26, 25);
		firstTime = true;
	}

	public void tick() {
		if(firstTime) {
			File cur = new File("files");
			for(File f : cur.listFiles()) {
				String name = f.getName();
				if(name.indexOf(".txt") == -1) { // Folders
					CreationMenu cm = new CreationMenu(0, 0, 0, 0);
					this.cm.add(cm);
					cm.fileName = name;
					cm.selection = "Pasta";
					cm.addB.actionPerformed();
					this.cm.remove(cm);
					Label.tick = true;
				}else { // Fichas
					CreationMenu cm = new CreationMenu(0, 0, 0, 0);
					Game.files.cm.add(cm);
					cm.fileName = name.replace(".txt", "");
					cm.selection = "Ficha";
					cm.addB.actionPerformed();
					Game.files.cm.remove(cm);
					Label.tick = true;
				}
			}
			firstTime = false;
		}
		
		for(int i = 0; i < insideFolders.size(); i++) {
			insideFolders.get(i).tick();
		}
		roller.tick();
		
		if(!Label.tick){
			if(!CreationMenu.remove) {
				this.cm.get(0).tick();
			}else {
				this.cm.get(0).changeTickers();
				this.cm.remove(0);
			}
		}
		btn.tick();
	}
	
	public void render(Graphics g) {
		g.setFont(Menu.specialElite.deriveFont(80.0f));
		g.setColor(new Color(0xFFE8EDEB));
		g.drawString("FICHAS", Game.WIDTH*Game.SCALE/2 - g.getFontMetrics().stringWidth("FICHAS") / 2, 90-Game.files.roller.getY()*Game.files.roller.step);
		roller.render(g);
		for(int i = 0; i < insideFolders.size(); i++) {
			insideFolders.get(i).render(g);
		}
		if(!Label.tick) {
			cm.get(0).render(g);
		}
		btn.render(g);
	}

	public ArrayList<Label> getLabels() {
		return this.insideFolders;
	}

	public void setLabels(ArrayList<Label> labels) {
		this.insideFolders = labels;
	}
	
	public String getCurPath() {
		return this.path;
	}
	
	public void setCurPath(String path) {
		this.path = path;
	}
	
}
