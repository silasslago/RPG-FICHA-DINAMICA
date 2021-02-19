package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;

public class TextLabel extends Label{

	private int imaginaryY;
	public Font font;
	protected Color color;
	public String text;
	public boolean writing = false;
	public int typeText;
	
	private int timer = 0;
	private int maxTimer = 30;
	private boolean show = true;
	
	public String phrase = "";
	public boolean throwPhrase = false;
	
	private double initX;
	
	public TextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, Font font, 
			Color color, String text, int typeText) {
		super(x, y-font.getSize()/1.5, width, height, speed, sprite);
		size = 0;
		initX = x;
		this.font = font;
		this.color = color;
		this.text = text;
		this.imaginaryY = (int)y;
		this.typeText = typeText;
	}

	public void tick() {
		if(Game.mouseController.isPressed) {
			this.resetPhrase();
		}
		if(tick) {
			if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()-Game.roller.getY()*Game.roller.step) &&
					(Game.mouseController.currentX < this.getX()+this.getWidth() && 
							Game.mouseController.currentY < this.getY()-Game.roller.getY()*Game.roller.step+this.getHeight())) {
				if(!current) {
					size = font.getSize();
					current = true;
				}
			}else {
				if(current) {
					current = false;
					size = 0;
				}
			}
			changeLabel();
			if(writing) {
				timer++;
				if(timer > this.maxTimer) {
					timer = 0;
					show = !show;
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize()+size/3));
		g.drawString(text, getX(), imaginaryY-Game.roller.getY()*Game.roller.step);
		if(writing && show) {
			g.setColor(Color.white);
			g.drawLine(getX()+width, getY()-Game.roller.getY()*Game.roller.step, getX()+width, getY()+height-Game.roller.getY()*Game.roller.step);
		}
		if(this.text == "") {
			g.setColor(Color.red);
			g.drawRect(getX(), getY()-Game.roller.getY()*Game.roller.step, width, height);
		}
	}
	
	private void changeLabel() {
		Entity tL = new Entity(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(tL, Game.mouseController)) {
			for(int ii = 0; ii < Game.entities.size(); ii++) {
				Entity ee = Game.entities.get(ii);
				if(ee instanceof TextLabel) {
					((TextLabel) ee).writing = false;
				}
			}
			Game.mouseController.currentTextLabel = this;
			Game.mouseController.resetPosition();
			phrase = "";
			this.beginToWrite();
			throwPhrase = true;
		}
	}
	
	public void beginToWrite() {
		writing = true;
		if(text.equals("")) {
			return;
		}
		text = "";
		if(typeText == 0) {
			setX((int)initX);
		}else if(typeText == 1) {
			int newX = this.getX() + this.getWidth()/2;
			setX(newX);
		}else if(typeText == 2) {
			setX((int)initX+width-this.font.getSize());
		}
		width = 10;
	}
	
	public void setImaginaryY(int realY) {
		this.imaginaryY = realY;
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
		if(phrase == "") {
			return;
		}
		phrase = newString;
		this.text = newString;
		int jump = (int)(this.font.getSize()/4);
		this.setWidth(this.getWidth()-(int)(jump*1.9));
		if(this.typeText == 1) {
			this.setX((int)(this.getX()+jump));
		}else if(this.typeText == 2) {
			this.setX((int)(this.getX()+jump*1.28));
		}
	}
	
	public void throwText() {
		this.text = phrase;
		int jump = (int)(this.font.getSize()/4);
		if(this.typeText == 1) {
			this.setX(this.getX()-jump);
		}else if(this.typeText == 2) {
			this.setX((int)(this.getX()-jump*1.05));
		}
		this.setWidth(this.getWidth()+(int)(jump*1.9));
	}
	
	public void resetPhrase() {
		throwPhrase = false;
		this.writing = false;
		phrase = "";
	}
	
}
