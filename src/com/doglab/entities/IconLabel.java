package com.doglab.entities;

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
	
	public IconLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		characterIcon = new CharacterIcon(415, 160, 1, 1, 0, null);
		dice = new Dice(510, 125, 76, 71 ,0, Game.spr_entities.getSprite(0, 156, 76, 71), 100, 50);
		labels.add(dice);
		labels.add(characterIcon);
	}
	
	public void tick() {
		super.tick();
		if(edit.isEditing) {
			changeIcon();
		}
	}

	public void render(Graphics g) {
		super.render(g);
	}
	
	private void changeIcon() {
		double z = World.calculoDistance((int)Game.mouseController.getX(), (int)Game.mouseController.getY(), 
				characterIcon.getX(), characterIcon.getY());
		if(z < 65) {
			Game.mouseController.resetPosition();
			Game.fileChooser.setDialogTitle("avatar");
			Game.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "png", "jpg");
			Game.fileChooser.setFileFilter(filter);
			int fileSelected = Game.fileChooser.showOpenDialog(Game.game);
			if(fileSelected == JFileChooser.APPROVE_OPTION) {
				File file = Game.fileChooser.getSelectedFile();
				String path = file.getPath();
				ImageIcon icon = new ImageIcon(path);
				Game.player.icon = icon.getImage();
			}
		}
	}
	
}
