package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class TextLabel extends Label{

	private int imaginaryY;
	public Font font;
	protected Color color;
	public String text, showText;
	public boolean writing = false;
	public int typeText;
	
	private int timer = 0;
	private int maxTimer = 30;
	private boolean show = true;
	
	public String phrase = "";
	public boolean throwPhrase = false;
	
	public double initX, initW;
	
	public static boolean showSpace = true;
	public EditTextLabel eTL;
	
	private boolean limiter, canClick = false;
	
	private boolean goDown = false;
	private int maxLine = 0;
	private int maxChars = 0;
	private int maxW = 0;	
	
	private int size;
	private boolean firstTime = false;
	
	public static Color text_color = Color.white;
	
	public TextLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, Font font, 
			Color color, String text, int typeText, boolean limiter) {
		super(x, y-font.getSize2D()/1.5, width, height, speed, sprite);
		this.limiter = limiter;
		initX = x;
		size = 0;
		initW = width;
		this.font = Menu.curFont.deriveFont(font.getSize2D()-2);
		this.color = color;
		this.text = text;
		this.showText = text;
		this.imaginaryY = (int)y;
		this.typeText = typeText;
	}

	public void tick() {
		if(Game.mouseController.isPressed) {
			this.resetPhrase();
		}
		this.showText = this.text;
		if(tick && (!Game.actor.equals("Mestre"))) {
			if((Game.mouseController.currentX > this.getX() && Game.mouseController.currentY > this.getY()-Game.roller.getY()*Game.roller.step) &&
					(Game.mouseController.currentX < this.getX()+this.getWidth() && 
							Game.mouseController.currentY < this.getY()-Game.roller.getY()*Game.roller.step+this.getHeight())) {
				if(!current) {
					size = font.getSize();
					current = true;
				}
			}else {
				if(current) {
					size = 0;
					current = false;
				}
			}
			
			if(eTL != null) {
				if(eTL.selected) {
					edit();
				}
				Game.entities.remove(eTL);
				eTL = null;
			}
			changeLabel();
			if(writing) {
				timer++;
				if(timer > this.maxTimer) {
					timer = 0;
					show = !show;
				}
			}
			if(goDown) {
				String formatedString = "";
				int cc = 0;
				for(int i = 0; i < showText.length(); i++) {
					if(i>maxChars) {
						break;
					}
					formatedString+=showText.charAt(i);
					if(cc == maxLine) {
						formatedString+="==";
						cc = 0;
					}
					cc++;
				}
				showText = formatedString;
				if(width>this.maxW) {
					width = maxW;
				}
			}
		}
		
		if(this.typeText == 0) {
			this.setX((int)initX);
		}else if(this.typeText == 1) {
			this.setX((int)(initX+initW/2-this.getWidth()/2));
		}else if(this.typeText == 2) {
			this.setX((int)(initX-this.getWidth()));
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.setFont(new Font(font.getFontName(), font.getStyle(), font.getSize()+size/3));
		if(!firstTime) {
			this.initW = g.getFontMetrics().stringWidth(showText);
			firstTime=true;
		}
		g.setColor(text_color);
		if(!goDown) {
			g.drawString(showText, getX(), imaginaryY - Game.roller.getY()*Game.roller.step);
			if((!writing) && text.equals("")){
				this.width = 150;
			}else {
				this.width = g.getFontMetrics().stringWidth(showText);
			}
		}else {
			drawStringT(g, showText, getX(), imaginaryY 
					- g.getFontMetrics().getHeight()
					-Game.roller.getY()*Game.roller.step);
		}
		if(writing && show && !goDown) {
			g.setColor(Color.white);
			g.drawLine(getX()+width, getY()-Game.roller.getY()*Game.roller.step, getX()+width, getY()+height-Game.roller.getY()*Game.roller.step);
		}
		if(size == font.getSize() && text.equals("")) {
			g.drawRect((int)initX, getY()-Game.roller.getY()*Game.roller.step, (int)initW, getHeight());
		}	
	}
	
	private void drawStringT(Graphics g, String text, int x, int y) {
		this.height = 0;
		int i = 0;
		int maxLines = maxChars/maxLine;
		for (String line : text.split("==")) {
			i++;
			if(i > maxLines) {
				return;
			}else if(i==1) {
				if((!writing) && text.equals("")){
					this.width = 150;
				}else {
					this.width = g.getFontMetrics().stringWidth(line);
				}
			}
			g.drawString(line, x, y += g.getFontMetrics().getHeight());
			if(writing && show && (i+1 > text.split("==").length)) {
				g.setColor(Color.white);
				g.drawLine(x + g.getFontMetrics().stringWidth(line), y, x + g.getFontMetrics().stringWidth(line), y - g.getFontMetrics().getHeight());
			}
			height+=g.getFontMetrics().getHeight();
		}
	}
	
	public static void drawString(Graphics g, String text, int x, int y) {
		 for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	 }
	
	public void textBox(boolean goDown, int maxLine, int maxChars, int maxW) {
		this.maxLine = maxLine;
		this.goDown = goDown;
		this.maxChars = maxChars;
		this.maxW = maxW;
	}

	private void changeLabel() {
		Entity tL = new Entity(getX(), getY()-Game.roller.getY()*Game.roller.step, getWidth(), getHeight(), speed, getSprite());
		if(this.isColliding(tL, Game.mouseController)) {
			Game.mouseController.resetPosition();
			if(canClick){
				edit();
				return;
			}
			if(eTL == null && text != "") {
				int wLabel = 350;
				int hLabel = 150;
				int xLabel = ((Game.WIDTH*Game.SCALE)/2)-wLabel/2;
				int yLabel = ((Game.HEIGHT*Game.SCALE)/2)-hLabel/2;
				eTL = new EditTextLabel(xLabel, yLabel, wLabel, hLabel, 0, null);
				Game.entities.add(eTL);
			}
			if(text == "") {
				edit();
			}
		}
	}
	
	private void edit() {
		for(int ii = 0; ii < Game.entities.size(); ii++) {
			Entity ee = Game.entities.get(ii);
			if(ee instanceof TextLabel) {
				((TextLabel) ee).writing = false;
			}
		}
		Game.mouseController.currentTextLabel = this;
		phrase = text;
		this.beginToWrite();
		throwPhrase = true;
	}
	
	public void beginToWrite() {
		writing = true;
		if(text.equals("")) {
			return;
		}
		if(typeText == 0) {
			setX((int)initX);
		}else if(typeText == 1){
			setX((int)(initX+initW/2-this.getWidth()/2));
		}else if(typeText == 2) {
			setX((int)initX+this.getWidth());
		}
		//width = 10;
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
		if(e == KeyEvent.VK_ENTER) {
			if(this.goDown) {
				phrase+="==";
				throwText();
			}
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
				letter.equals("4") || letter.equals("5") || letter.equals("6") || letter.equals("7") ||
				letter.equals("8") || letter.equals("9") || letter.equals("(") || letter.equals(")") ||
				letter.equals("+") || letter.equals(".") || letter.equals(",")){
			if(this.limiter) {
				if(phrase.length() < 5) {
					phrase+=e;
					throwText();
				}
			}else {
				phrase+=e;
				throwText();
			}
		}
	}
	
	public void delete(String newString) {
		if(phrase == "") {
			return;
		}
		phrase = newString;
		this.text = newString;
		if(this.typeText == 1) {
			this.setX((int)(initX+initW/2-this.getWidth()/2));
		}else if(this.typeText == 2) {
			this.setX((int)(initX-this.getWidth()));
		}
	}
	
	public void throwText() {
		this.text = phrase;
		if(this.typeText == 1) {
			this.setX((int)(initX+initW/2-this.getWidth()/2));
		}else if(this.typeText == 2) {
			this.setX((int)(initX-this.getWidth()));
		}
		if(Game.gameState == "FICHA") {
			Menu.save();
		}
	}
	
	public void resetPhrase() {
		throwPhrase = false;
		this.writing = false;
		phrase = "";
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void canClick(boolean canClick) {
		this.canClick = canClick;
	}
	
}
