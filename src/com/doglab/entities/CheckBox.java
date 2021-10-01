package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class CheckBox extends Entity{

	private ArrayList<TextLabel> texts;
	private boolean checked = false;
	private BufferedImage emptyBox, fullBox;
	
	public CheckBox(double x, double y, int width, int height, double speed, BufferedImage sprite, 
			BufferedImage checked, String text) {
		super(x, y, width, height, speed, sprite);
		this.fullBox = checked;
		this.emptyBox = sprite;
		texts = new ArrayList<TextLabel>();
		TextLabel tLabel = new TextLabel(getX()+width+5, getY()+height, (height*text.length())/2, height, speed, null, 
				new Font("sitka banner", Font.BOLD, 15), new Color(0xFFE8EDEB), text, 0, false);
		texts.add(tLabel);
	}
	
	public void actionPerformed() {
		Game.mouseController.resetPosition();
		this.setChecked(!checked);
		if(checked) {
			this.setSprite(this.fullBox);
		}else {
			this.setSprite(this.emptyBox);
		}
		Menu.save();
	}
	
	public void tick() {
		super.tick();
		Entity e = new Entity(getX(), getY() - Game.roller.getY()*Game.roller.step, getWidth(), 
				getHeight(), speed, null);
		if(this.isColliding(e, Game.mouseController)) {
			actionPerformed();
		}
		for(int i = 0; i < texts.size(); i++) {
			texts.get(i).tick();
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		for(int i = 0; i < texts.size(); i++) {
			texts.get(i).render(g);
		}
	}
	
	public ArrayList<TextLabel> getArrayList() {
		return this.texts;
	}
	
	public void setArrayList(ArrayList<TextLabel> texts) {
		this.texts = texts;
	}
	
	public boolean getChecked() {
		return this.checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
