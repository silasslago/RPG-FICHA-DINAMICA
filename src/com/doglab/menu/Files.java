package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

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
	
	public boolean firstTime = false, connect = false;
	public HomeButton btn;
	
	public Files() {
		labels = new ArrayList<Label>();
		Addition adt = new Addition(50+Menu.margin, 130, 180, 250);
		labels.add(adt);
		int width = 10;
		roller = new Roller(Game.WIDTH-width, 0, width, 170, 4, null, false,
				Game.WIDTH-width, 0, width, Game.HEIGHT);
		cm = new ArrayList<Label>();
		insideFolders = labels;
		btn = new HomeButton(90+Menu.margin, 100, 26, 25);
		firstTime = true;
		
		File online = new File("files/Online");
		if(online.exists()) {
			Menu.current = "files/Online";
			File f = new File(Menu.current);
			Menu.current = "files/";
			
			String[]entries = f.list();
			for(String s: entries){
			    File currentFile = new File(f.getPath(), s);
			    if(!currentFile.isDirectory()) {
			    	currentFile.delete();
			    }else {
			    	String[] both = ArrayUtils.addAll(entries, currentFile.list());
			    	entries = both;
			    }
			}
			f.delete();
		}
		
	}

	public void tick() {
		// Cria as labels de pastas e personagens assim que o app for iniciado
		if(firstTime) {
			labelIAmount = 1;
			labelPAmount = 0;
			labelMAmount = 0;
			Addition adt = new Addition(50+Menu.margin, 130, 180, 250);
			insideFolders.clear();
			insideFolders.add(adt);
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
			if(!CreationMenu.remove || this.connect) {
				this.cm.get(0).tick();
			}else{
				this.cm.get(0).changeTickers();
				this.cm.clear();
				this.connect = false;
			}
		}
		Entity e = new Entity(126+Menu.margin, 100 - roller.getY()*roller.step, 26, 26, 0, null);
		if(e.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			this.connect = true;
			int wLabel = 350;
			int hLabel = 350;
			int xLabel = ((Game.WIDTH*Game.SCALE)/2)-wLabel/2;
			int yLabel = ((Game.HEIGHT*Game.SCALE)/2)-hLabel/2;
			ConnectLabel conet = new ConnectLabel(xLabel, yLabel, wLabel, hLabel);
			cm.clear();
			cm.add(conet);
		}
		btn.tick();
	}
	
	public void render(Graphics g) {
		g.setFont(Menu.curFont.deriveFont(80.0f));
		g.setColor(new Color(0xFFE8EDEB));
		g.drawString("FICHAS", Game.WIDTH*Game.SCALE/2 - g.getFontMetrics().stringWidth("FICHAS") / 2, 90-Game.files.roller.getY()*Game.files.roller.step);
		roller.render(g);
		for(int i = 0; i < insideFolders.size(); i++) {
			insideFolders.get(i).render(g);
		}
		btn.render(g);
		g.drawImage(Game.spr_entities.getSprite(200, 130, 26, 26), 126+Menu.margin, 100-Game.files.roller.getY()*Game.files.roller.step, 26, 25, null);
		if(!Label.tick) {
			cm.get(0).render(g);
		}
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
