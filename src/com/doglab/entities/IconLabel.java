package com.doglab.entities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.world.World;

public class IconLabel extends Label{

	private CharacterIcon characterIcon;
	public String path;
	private int degress = 0;
	
	public IconLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		characterIcon = new CharacterIcon(getX()+70, getY()+80, 1, 1, 0, null);
		labels.add(characterIcon);
		
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			changeIcon();
			if(current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}
		}
		degress++;
	}

	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D)g;
		AffineTransform old = g2D.getTransform();
		
		if(current) {
			g2D.rotate(Math.toRadians(degress),  getX()+205+76/2+inLocal, getY()+inLocal+15+getY()/2+71/2 - Game.roller.getY()*Game.roller.step);
			g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), getX()+205+inLocal, getY()+inLocal+15+getY()/2 - Game.roller.getY()*Game.roller.step, 
					76, 71 , null);
		}else {
			g2D.rotate(Math.toRadians(degress),  getX()+205+76/2+inLocal, getY()+inLocal+getY()/2+71/2 - Game.roller.getY()*Game.roller.step);
			g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), getX()+205+inLocal, getY()+inLocal+getY()/2 - Game.roller.getY()*Game.roller.step, 
					76, 71 , null);
		}
		
		g2D.setTransform(old);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
	}
	
	public void setIcon(String path) {
		ImageIcon icon = new ImageIcon(path);
		Game.player.icon = icon.getImage();
	}
	
	private void changeIcon() {
		double z = World.calculoDistance((int)Game.mouseController.getX(), (int)Game.mouseController.getY(), 
				characterIcon.getX(), characterIcon.getY()-Game.roller.getY()*Game.roller.step);
		if(z < 65) {
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
	
}
