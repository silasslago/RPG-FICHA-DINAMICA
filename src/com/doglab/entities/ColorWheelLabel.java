package com.doglab.entities;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Robot;

import com.doglab.graficos.ColorWheel;
import com.doglab.main.Game;
import com.doglab.main.Menu;
import com.doglab.menu.Selector;

public class ColorWheelLabel extends Label{
	
	private CloseButton close;
	private Selector selector;
	
	private int colorX = 0, colorY = 0;

	public ColorWheelLabel(double x, double y, int width, int height, String[] options) {
		super(x, y, width, height, 0, null);
		selector = new Selector(getX()+getWidth()/2 - 50, getY()+10, options) {
			
			public void render(Graphics g) {
				g.setFont(Menu.curFont.deriveFont(20.0f));
				g.setColor(new Color(0xFFE8EDEB));
				if(!show) {
					g.setColor(new Color(0xFF424242));
					g.drawRect(getX(), getY(), getWidth(), getHeight());
					for(int i = 0; i < labels.size(); i++) {
						Entity e = labels.get(i);
						e.render(g);
					}
					g.setColor(Color.white);
					g.drawString(options[current], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[current])/2, getY() + 25);
				}else {
					for(int i = 0; i < options.length+1; i++) {
						if(i>0) {
							g.setColor(new Color(0xFF000000));
							g.fillRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
							g.setColor(Color.white);
							g.drawString(options[i-1], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[i-1])/2, getY() + (getHeight()*i) + 25);
						}else {
							g.setColor(new Color(0xFF101010));
							g.fillRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
						}
						g.setColor(new Color(0xFFE8EDEB));
						g.drawRect(getX(), getY()+(getHeight()*i), getWidth(), getHeight());
					}
					g.setColor(Color.white);
					g.drawString(options[current], getX()+getWidth()/2 - g.getFontMetrics().stringWidth(options[current])/2, getY() + 25);
				}
			}
			
		};
		int widthB = 25;
		int heightB = 25;
		colorX = getX()+getWidth()/2-5;
		colorY = getY()+getHeight()/2+20;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this) {
			
			public void actionPerformed() {
				Game.entities.remove(diceLabel);
				Game.mouseController.resetPosition();
			}
			
		};
	}
	
	public static Color getPixel(int x,int y) throws AWTException{
	    Robot rb = new Robot();
	    return rb.getPixelColor(x, y);
	}
	
	public void tick() {
		
		super.tick();
		if(tick) {
			close.tick();
			selector.tick();
			double z = calculoDistance((int)Game.mouseController.getX(), (int)Game.mouseController.getY(), 
					getX()+getWidth()/2, getY()+getHeight()/2+15);
			if(z < 110) {
				Point p = new Point(Game.mouseController.getX(), Game.mouseController.getY());
				Color cur = null;
			    try {
			    	cur = getPixel(p.x, p.y);
			    } catch (AWTException e1) {
			        e1.printStackTrace();
			    }
			    switch(selector.getSelection()) {
				    case "FUNDO":
				    	Menu.bg = cur;
				    	Game.BG_COLOR = cur;
				    	break;
					case "TEXTO":
				    	TextLabel.text_color = cur;
				    	break;
					case "VIDA":
						StatsLabel.life_color = cur;
				    	break;
					case "SANIDADE":
						StatsLabel.sanity_color = cur;
				    	break;
					case "MAGIA":
				    	StatsLabel.magic_color = cur;
				    	break;
			    }
			    colorX = p.x-5;
			    colorY = p.y-5;
			    Menu.saveColors();
			    Game.mouseController.resetPosition();
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		ColorWheel.paint(g, getX(), getY()+50, getWidth(), getHeight()-50);
		g.setColor(Color.white);
		g.drawOval(getX()+34, getY()+60, 230, 229);
		g.setColor(Color.BLACK);
		g.fillOval(getX()+getWidth()/2 - 10, getY()+getHeight()/2+15, 20, 20);
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		close.render(g);
		g.setColor(Color.white);
		g.setFont(Menu.curFont.deriveFont(25.0f));
		g.setColor(Color.GRAY);
		g.drawOval(colorX, colorY, 10, 10);
		selector.render(g);
	}

}
