package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class CharacterLabel extends Label{

	private String path;
	private ArrayList<Image> siluett;
	private ArrayList<String> paths;
	private BufferedImage signE, signD;
	private int current = 0;
	
	public CharacterLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		siluett = new ArrayList<Image>();
		paths = new ArrayList<String>();
		siluett.add(Game.spr_entities.getSprite(151, 155, 45, 73));
		signD = Game.spr_entities.getSprite(176, 231, 25, 25);
		signE = Game.spr_entities.getSprite(201, 231, 25, 25);
	}
	
	public void tick() {
		if(tick) {
			super.tick();
			Entity e1 = new Entity(Game.mouseController.getX(), Game.mouseController.getY(), Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
			Entity e2 = new Entity(getX()+getWidth()/2-70, getY()+5 - Game.roller.getY()*Game.roller.step, (int)(45*2.8), (int)(73*2.8), 0, null);
			Entity e3 = new Entity(getX()+30+inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, 0, null);
			Entity e4 = new Entity(getX()+getWidth()-30-inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, 0, null);
			Entity e5 = new Entity(getX()+getWidth()-35-inLocal, getY()+inLocal+10 - Game.roller.getY()*Game.roller.step, 25, 25, 0, null);
			if(this.isColliding(e1, e2)) {
				changeIcon();
			}
			if(this.isColliding(e3, e1)) {
				Game.mouseController.resetPosition();
				current--;
				if(current<0) {
					current = siluett.size()-1;
				}
			}
			if(this.isColliding(e4, e1)) {
				Game.mouseController.resetPosition();
				current++;
				if(current>=siluett.size()) {
					current = 0;
				}
			}
			if(this.isColliding(e5, e1)) {
				Game.mouseController.resetPosition();
				if(siluett.size()>1) {
					siluett.remove(current);
					paths.remove(current);
					if(current>0) {
						current--;
					}else {
						current = 0;
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(siluett.get(current), getX()+getWidth()/2-70, getY()+inLocal+5 - Game.roller.getY()*Game.roller.step, (int)(45*2.8), (int)(73*2.8), null);
		g.setColor(Color.red);
		g.drawImage(Game.spr_entities.getSprite(76, 181, 25, 25) ,getX()+getWidth()-35-inLocal, getY()+10+inLocal - Game.roller.getY()*Game.roller.step, 25, 25, null);
		g.drawImage(signE, getX()+10+inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, null);
		g.drawImage(signD, getX()+getWidth()-30-inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, null);
	}

	public void setIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		siluett.add(icon.getImage());
		paths.add(path);
		current++;
		Menu.save();
	}
	
	private void changeIcon() {
		Game.mouseController.resetPosition();
		Game.fileChooser.setDialogTitle("");
		Game.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "png", "jpg");
		Game.fileChooser.setFileFilter(filter);
		int fileSelected = Game.fileChooser.showOpenDialog(Game.game);
		if(fileSelected == JFileChooser.APPROVE_OPTION) {
			File file = Game.fileChooser.getSelectedFile();
			this.path = file.getPath();
			setIcon(this.path);
		}
	}
	
	public void setPaths(ArrayList<String> paths) {
		this.paths = paths;
	}
	
	public ArrayList<String> getPaths() {
		return this.paths;
	}
	
	public void addPath(String path) {
		paths.add(path);
		ImageIcon icon = new ImageIcon(path);
		siluett.add(icon.getImage());
		paths.add(path);
		current++;
	}
	
}
