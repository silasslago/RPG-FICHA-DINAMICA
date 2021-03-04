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

public class CharacterLabel extends Label{

	private String path;
	private ArrayList<Image> siluett;
	private BufferedImage signE, signD;
	private int current = 0;
	
	public CharacterLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		siluett = new ArrayList<Image>();
		siluett.add(Game.spr_entities.getSprite(151, 155, 45, 73));
		signD = Game.spr_entities.getSprite(176, 231, 25, 25);
		signE = Game.spr_entities.getSprite(201, 231, 25, 25);
	}
	
	public void tick() {
		if(tick) {
			super.tick();
			if(super.current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}
			Entity e1 = new Entity(Game.mouseController.getX(), Game.mouseController.getY(), Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
			Entity e2 = new Entity(getX()+getWidth()/2-95, getY()+5 - Game.roller.getY()*Game.roller.step, (int)(45*3.5), (int)(73*3.5), 0, null);
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
		g.drawImage(siluett.get(current), getX()+getWidth()/2-95, getY()+inLocal+5 - Game.roller.getY()*Game.roller.step, (int)(45*3.5), (int)(73*3.5), null);
		g.setColor(Color.red);
		g.fillRect(getX()+getWidth()-35-inLocal, getY()+10+inLocal - Game.roller.getY()*Game.roller.step, 25, 25);
		g.drawImage(signE, getX()+30+inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, null);
		g.drawImage(signD, getX()+getWidth()-30-inLocal, getY()+getHeight()/2-25/2 - Game.roller.getY()*Game.roller.step, 25, 25, null);
	}

	public void setIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		siluett.add(icon.getImage());
		current++;
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
}
