package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class DiceLabel extends Label{

	private boolean animation = true;
	private int degress = 0;
	private int maxDegress = 360;
	private int times = 0;
	
	private int diceX = 0;
	private int diceY = 0;
	private int diceW = 0;
	private int diceH = 0;
	
	private int roll[];
	private String state = null;
	
	private CloseButton close;
	private String[] plus;
	private String dicePlayed;
	
	public DiceLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int[] random,
			String state, String[] plus, String dicePlayed) {
		super(x, y, width, height, speed, sprite);
		this.dicePlayed = dicePlayed;
		this.plus = plus;
		diceH = sprite.getHeight();
		diceW = sprite.getWidth();
		diceX = (int)(x+width/2-diceW/2);
		diceY = (int)(y+height/2-diceH/2);
		roll = random;
		this.state = state;
		int widthB = 25;
		int heightB = 25;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this);
		
		String valores = "";
		for(int i = 0; i < random.length; i++) {
			valores+=Integer.toString(random[i]);
			if(!(i+1==random.length)) {
				valores+="+";
			}
		}
		for(Entity e : Game.entities) {
			if(e instanceof DetailsLabel) {
				HistoryLabel.newLog(state, dicePlayed, valores, ((TextLabel) ((DetailsLabel) e).labels.get(8)).text);
				break;
			}
		}
		changeTickers();
	}
	
	public void tick() {
		this.order = 2;
		if(animation) {
			degress+=50;
			times++;
			degress-=3.1*times;
			if(degress > maxDegress) {
				animation = false;
			}
		}else {
			close.tick();
		}
	}
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFFE8EDEB));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.WHITE);
		if(animation) {
			AffineTransform old = g2D.getTransform();
			g2D.rotate(Math.toRadians(degress), diceX+diceW/2, diceY+diceH/2);
			g.drawImage(getSprite(), diceX, diceY, diceW, diceH, null);
			g2D.setTransform(old);
		}else {
			int r = -45;
			int a = (diceX+20)-(r/2)*(roll.length-1);
			int plusA = a+(r/2)+15;
			int go = 0;
			String values = "";
			for(int i = 0; i < roll.length; i++) {
				g.setFont(Menu.curFont.deriveFont(48.0f));
				String rollPlus = Integer.toString(roll[i]);
				values+=rollPlus;
				if(i+2<=roll.length) {
					values+="+";
				}
				for(int ii = 1; ii < this.plus.length; ii++) {
					if(i+1>=roll.length) {
						values+="+";
						values+="("+this.plus[ii]+")";
					}
				}
				go = i;
			}
			g.drawString(values, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(values)/2, diceY+30);
			if(roll.length>1 && go<roll.length-1) {
				g.setFont(Menu.curFont.deriveFont(23.0f));
				g.drawString("+", plusA+(r*go), diceY+25);
			}
			if(roll.length==1) {
				g.setFont(Menu.curFont.deriveFont(18.0f));
				if(state != null) {
					g.drawString(state, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(state)/2, diceY+60);
				}
			}else {
				int result = 0;
				for(int i = 0; i < roll.length; i++) {
					result+=roll[i];
				}
				g.setFont(Menu.curFont.deriveFont(18.0f));
				String resultPlus = "= "+result;
				g.drawString(resultPlus, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(resultPlus)/2, diceY+60);
			}
			
		}
		g.setFont(Menu.curFont.deriveFont(18.0f));
		g.drawString("Roll", getX()+getWidth()/2 - g.getFontMetrics().stringWidth("Roll")/2, getY()+20);
		g.setColor(new Color(0xFFE8EDEB));
		g.drawLine(getX(), getY()+30, getX()+getWidth(), getY()+30);
		close.render(g2D);
	}

}
