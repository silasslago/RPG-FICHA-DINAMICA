package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Folder extends Label{

	private String fileName;
	private BufferedImage folder;
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
		Addition adt = new Addition(50, 130, 180, 250);
		files.add(adt);
	}

	public void tick() {
		if(tick) {
			Entity e = new Entity(Game.mouseController.getX(), 
					Game.mouseController.getY() + Game.files.roller.getY()*Game.files.roller.step
					, 5, 5, 0, null);
			if(this.isColliding(this, e)) {
				Game.mouseController.resetPosition();
				this.setNewFolder();
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
						cm.fileName = name;
						cm.selection = "Ficha";
						cm.addB.actionPerformed();
						Game.files.cm.remove(cm);
						Label.tick = true;
						
					}
				}
				
			}
		}
	}
	
	public void setNewFolder() {
		Game.files.setLabels(files);
	}
	
	public void render(Graphics g) {
		// Super
		g.setColor(new Color(0xFF151515));
		g.fillRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFFFF4246));
		//g.drawRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
		// ------
		g.setColor(new Color(0xFFE8EDEB));
		g.drawImage(folder, getX()+getWidth()/2 - folder.getWidth()*2/2, getY()+getHeight()/2 - folder.getHeight()*2 -Game.files.roller.getY()*Game.files.roller.step, 114, 72, null);
		g.setFont(Menu.specialElite.deriveFont(25.0f));
		g.drawString(fileName, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(fileName)/2, getY()+getHeight()-80 -Game.files.roller.getY()*Game.files.roller.step);
	}

	public String getName() {
		return this.fileName;
	}
	
}
