package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.world.World;

public class mouseController extends Entity{

	public boolean isPressed = false;
	public double xTarget, yTarget;
	
	public TextLabel textLabel;
	public String phrase = "";
	public boolean throwPhrase = false;
	
	public mouseController(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void buildPhrase(char e) {

		String letter = "";
		if(e == KeyEvent.VK_BACK_SPACE) {
			int lenght = phrase.length();
			String newString = "";
			for(int i = 0; i <lenght-1; i++) {
				newString+=phrase.charAt(i);
			}
			delete(newString);
			return;
		}
		letter+=e;
		
		letter = letter.toLowerCase();
		if(letter.equals("a") || letter.equals("b") || letter.equals("c") || letter.equals("d") ||
				letter.equals("e") || letter.equals("f") || letter.equals("g") || letter.equals("h") ||
				letter.equals("i") || letter.equals("j") || letter.equals("k") || letter.equals("l") ||
				letter.equals("m") || letter.equals("n") || letter.equals("o") || letter.equals("p") ||
				letter.equals("q") || letter.equals("r") || letter.equals("s") || letter.equals("t") ||
				letter.equals("u") || letter.equals("v") || letter.equals("w") || letter.equals("x") ||
				letter.equals("y") || letter.equals("z") || letter.equals("ç") || letter.equals(" ") ||
				letter.equals("0") || letter.equals("1") || letter.equals("2") || letter.equals("3") ||
				letter.equals("4") || letter.equals("5") ||letter.equals("6") || letter.equals("7") ||
				letter.equals("8") || letter.equals("9")){
			phrase+=e;
			throwText();
		}
	}
	
	public void delete(String newString) {
		phrase = newString;
		textLabel.text = newString;
		int jump = (int)(textLabel.font.getSize()/4);
		textLabel.setWidth(textLabel.getWidth()-(int)(jump*1.9));
		if(textLabel.typeText == 1) {
			textLabel.setX(textLabel.getX()+jump);
		}else if(textLabel.typeText == 2) {
			textLabel.setX((int)(textLabel.getX()+jump*1.7));
		}
	}
	
	public void throwText() {
		textLabel.text = phrase;
		int jump = (int)(textLabel.font.getSize()/4);
		if(textLabel.typeText == 1) {
			textLabel.setX(textLabel.getX()-jump);
		}else if(textLabel.typeText == 2) {
			textLabel.setX((int)(textLabel.getX()-jump*1.5));
		}
		textLabel.setWidth(textLabel.getWidth()+(int)(jump*1.9));
	}
	
	public void resetPhrase() {
		throwPhrase = false;
		textLabel.writing = false;
		phrase = "";
	}
	
	public void tick() {
		
		if(isPressed) {
			isPressed = false;
			this.x = (int)xTarget;
			this.y = (int)yTarget;
			changeIcon();
			changeLabel();
		}
		
	}
	
	private void changeIcon() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof CharacterIcon) {
				double z = World.calculoDistance((int)xTarget, (int)yTarget, e.getX(), e.getY());
				if(z < 65) {
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
	}

	private void changeLabel() {
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof TextLabel) {
				if(isColliding(this, e)) {
					
					for(int ii = 0; ii < Game.entities.size(); ii++) {
						Entity ee = Game.entities.get(ii);
						if(ee instanceof TextLabel) {
							((TextLabel) ee).writing = false;
						}
					}
					
					phrase = "";
					((TextLabel) e).beginToWrite();
					textLabel = (TextLabel) e;
					throwPhrase = true;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		
		//g.setColor(Color.blue);
		//g.fillRect(getX(), getY(), width, height);
	}

}
