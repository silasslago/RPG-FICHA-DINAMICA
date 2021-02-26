package com.doglab.entities;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.world.World;

public class IconLabel extends Label{

	private CharacterIcon characterIcon;
	private Dice dice;
	public String path;
	
	public IconLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		characterIcon = new CharacterIcon(getX()+70, getY()+80, 1, 1, 0, null);
		TextLabel tL = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "50", 0);
		TextLabel dValue = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "100", 0);
		TextLabel dAmount = new TextLabel(0, 0, 0, 0, 0, null, new Font("arial", Font.BOLD, 1), null, "1", 0);
		
		dice = new Dice(getX()+220, getY()+getY()/2, 76, 71 ,0, Game.spr_entities.getSprite(0, 156, 76, 71), 
				dValue, tL, dAmount, true, true);
		labels.add(dice);
		labels.add(characterIcon);
		
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			changeIcon();
		}
	}

	public void render(Graphics g) {
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
