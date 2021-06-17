package com.doglab.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class CharacterIcon extends Label{

	private BufferedImage barrier;
	private int barrierX, barrierY;
	private int iconX, iconY;
	private BufferedImage camera;
	public String path;
	
	public CharacterIcon(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		barrierX = (int)(x-77);
		barrierY = (int)(y-77);
		iconX = (int)(x-45);
		iconY = (int)(y-65);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
	}
	
	public void tick() {
		if(tick) {
			changeIcon();
		}
	}
	
	public void render(Graphics g) {
		g.drawImage(camera, this.getX(), this.getY()-15-Game.roller.getY()*Game.roller.step, null);
		g.drawImage(Game.player.icon, iconX, iconY-Game.roller.getY()*Game.roller.step, 130, 130, null);
		g.drawImage(barrier, barrierX, barrierY-Game.roller.getY()*Game.roller.step, 196, 156, null);
	}
	
	public void setIcon(String path) {
		this.path = path;
		ImageIcon icon = new ImageIcon(path);
		Game.player.icon = icon.getImage();
		Menu.save();
	}
	
	public void setIcon2(String path) {
		this.path = path;
		ImageIcon icon = new ImageIcon(path);
		Game.player.icon = icon.getImage();
	}
	
	private void changeIcon() {
		double z = calculoDistance((int)Game.mouseController.getX(), (int)Game.mouseController.getY(), 
				this.getX(), this.getY()-Game.roller.getY()*Game.roller.step);
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
