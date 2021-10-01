package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class EditTextLabel extends Label{

	public boolean selected = false;
	
	public EditTextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		changeTickers();
	}
	
	public void tick() {
		Entity e1 = new Entity(Game.mouseController.getX(), Game.mouseController.getY(), Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
		Entity e2 = new Entity(getX()+75, getY()+105, 43, 27, 0, null);
		Entity e3 = new Entity(getX()+235, getY()+105, 43, 27, 0, null);
		if(this.isColliding(e1, e2)) { // Botão SIM
			this.selected = true;
			changeTickers();
			Game.mouseController.resetPosition();
		}else if(this.isColliding(e1, e3)) { // Botão NÃO
			this.selected = false;
			changeTickers();
			Game.mouseController.resetPosition();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setFont(Menu.curFont.deriveFont(18.0f));
		g.drawString("Editar", getX()+150, getY()+20);
		g.drawString("Deseja alterar o texto selecionado?", getX()+12, getY()+70);
		g.drawString("Sim", getX()+80, getY()+125);
		g.drawString("Não", getX()+240, getY()+125);
		g.drawRect(getX()+75, getY()+105, 43, 27);
		g.drawRect(getX()+235, getY()+105, 43, 27);
		g.drawLine(getX(), getY()+30, getX()+getWidth(), getY()+30);
	}

}
