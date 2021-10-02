package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.event.MenuDragMouseEvent;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

import org.apache.commons.lang3.ArrayUtils;

public class Folder extends Label{

	private String fileName;
	private BufferedImage folder, del;
	private ArrayList<Label> files;
	
	public int labelIAmount = 1, labelPAmount = 0, labelMAmount = 0;
	public int labelsAmount = 1;
	
	public Folder(double x, double y, String fileName) {
		super(x, y, 180, 250, 0, null);
		files = new ArrayList<Label>();
		this.fileName = fileName;
		folder = Game.spr_entities.getSprite(196, 0, 57, 36);
		File folder = new File(Game.files.getCurPath()+fileName+"/");
		folder.mkdirs();
		Addition adt = new Addition(50+Menu.margin, 130, 180, 250);
		files.add(adt);
		del = Game.spr_entities.getSprite(76, 181, 25, 25);
	}

	public void tick() {
		if(tick) {
			if(!Game.online) {
				Entity exc = new Entity(getX()+getWidth()/2-25/2, 
						getY()+getHeight()-25-5-Game.files.roller.getY()*Game.files.roller.step, 
						25, 25, 0, null);
				if(this.isColliding(Game.mouseController, exc)) {
					deleteFiles();
					return;
				}
			}
			
			Entity e = new Entity(Game.mouseController.getX(), 
					Game.mouseController.getY() + Game.files.roller.getY()*Game.files.roller.step
					, 5, 5, 0, null);
			if(this.isColliding(this, e)) {
				open();
			}
		}
	}
	
	public void open() {
		Game.mouseController.resetPosition();
		Addition adt = (Addition) files.get(0);
		files.clear();
		files.add(adt);
		Game.files.setLabels(files);
		int size = files.size();
		Game.files.labelsAmount = size;
		int cont = 1;
		Game.files.labelIAmount = 0;
		Game.files.labelPAmount = 0;
		Game.files.labelMAmount = 0;
		for(int i = 0; i < size; i++) {
			if(cont == 1) {
				cont++;
				Game.files.labelIAmount++;
			}else if(cont == 2) {
				cont++;
				Game.files.labelPAmount++;
			}else if(cont == 3) {
				cont = 1;
				Game.files.labelMAmount++;
			}
		}
		Game.files.setCurPath(Game.files.getCurPath()+this.fileName+"/");
		
		File cur = new File(Game.files.getCurPath());
		for(File f : cur.listFiles()) {
			String name = f.getName();
			if(name.indexOf(".txt") == -1) { // Folder
				CreationMenu cm = new CreationMenu(0, 0, 0, 0);
				Game.files.cm.add(cm);
				cm.fileName = name;
				cm.selection = "Pasta";
				cm.addB.actionPerformed();
				Game.files.cm.remove(cm);
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
	}
	
	public void reload() {
		Game.mouseController.resetPosition();
	
		Addition adt = (Addition) Game.files.getLabels().get(0);
		Game.files.getLabels().clear();
		Game.files.getLabels().add(adt);
		int size = Game.files.getLabels().size();
		Game.files.labelsAmount = size;
		int cont = 1;
		Game.files.labelIAmount = 0;
		Game.files.labelPAmount = 0;
		Game.files.labelMAmount = 0;
		for(int i = 0; i < size; i++) {
			if(cont == 1) {
				cont++;
				Game.files.labelIAmount++;
			}else if(cont == 2) {
				cont++;
				Game.files.labelPAmount++;
			}else if(cont == 3) {
				cont = 1;
				Game.files.labelMAmount++;
			}
		}
		
		File cur = new File(Game.files.getCurPath());
		for(File f : cur.listFiles()) {
			String name = f.getName();
			if(name.indexOf(".txt") == -1) { // Folders
				
				CreationMenu cm = new CreationMenu(0, 0, 0, 0);
				Game.files.cm.add(cm);
				cm.fileName = name;
				
				cm.selection = "Pasta";
				cm.addB.actionPerformed();
				Game.files.cm.remove(cm);
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
	}
	
	public void deleteFiles() {
		Menu.current = Game.files.getCurPath()+fileName;
		File f = new File(Menu.current);
		Menu.current = Game.files.getCurPath().replace(fileName, "");
		
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
		reload();
	}
	
	public void render(Graphics g) {
		// Super
		g.setColor(new Color(0xFF151515));
		g.fillRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFFE8EDEB));
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
		// ------
		g.drawImage(folder, getX()+getWidth()/2 - folder.getWidth()*2/2, getY()+getHeight()/2 - folder.getHeight()*2 -Game.files.roller.getY()*Game.files.roller.step, 114, 72, null);
		g.setFont(Menu.curFont.deriveFont(25.0f));
		g.drawString(fileName, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(fileName)/2, getY()+getHeight()-80 -Game.files.roller.getY()*Game.files.roller.step);
		if(!Game.online) {
			g.drawImage(del, getX()+getWidth()/2-25/2, getY()+getHeight()-25-5-Game.files.roller.getY()*Game.files.roller.step, 25, 25, null);
		}
	}

	public String getName() {
		return this.fileName;
	}
	
}
