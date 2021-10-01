package com.doglab.menu;

import java.awt.Graphics;

import com.doglab.entities.Button;
import com.doglab.entities.Entity;
import com.doglab.main.Game;

public class HomeButton extends Button{

	public HomeButton(double x, double y, int width, int height) {
		super(x, y, width, height, 0, Game.spr_entities.getSprite(200, 181, 26, 25));
	}

	public void tick() {
		Entity e = new Entity(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(e, Game.mouseController)) {
			Game.mouseController.resetPosition();
			actionPerformed();
		}
	}
	
	public void actionPerformed() {
		Game.roller.setY(0);
		Game.gameState = "MENU";
		Game.files.setLabels(Game.files.labels);
		int size = Game.files.labels.size();
		Game.files.labelsAmount = Game.files.labels.size();
		Game.files.setCurPath("files/");
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
	}
	
	public void render(Graphics g) {
		g.drawImage(this.getSprite(), this.getX(), this.getY()-Game.files.roller.getY()*Game.files.roller.step, width, height, null);
	}
	
}
