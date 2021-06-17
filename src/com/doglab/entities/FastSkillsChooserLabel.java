package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class FastSkillsChooserLabel extends Label{

	private ArrayList<SelectionSquareTextLabel> pericias;
	private Roller roller;
	private int firstYRoller, initY;
	
	public FastSkillsChooserLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		pericias = new ArrayList<SelectionSquareTextLabel>();
		int yS = 0;
		for(int i = 0; i < Game.entities.size(); i++) {
			Entity e = Game.entities.get(i);
			if(e instanceof Skills) {
				for(int ii = 0; ii < ((Skills) e).getSkills().size(); ii++) {
					yS = e.getY();
					SquareTextLabel sTL = ((Skills) e).getSkills().get(ii);
					SelectionSquareTextLabel sSTL = new SelectionSquareTextLabel(sTL.getX(), sTL.getY()-yS,
							sTL.getWidth(), sTL.getHeight(), sTL.speed, null, ii);
					for(int iii = 0; iii < sTL.labels.size(); iii++) {
						TextLabel tL = (TextLabel) sTL.labels.get(iii);
						((TextLabel) sSTL.labels.get(iii)).text = tL.text;
						((TextLabel) sSTL.labels.get(iii)).setX(tL.getX());
					}
					pericias.add(sSTL);
				}
			}
		}
		int w = 12;
		roller = new Roller(getX()+getWidth()-w, getY(), w, 180, 3, null, false, getX()+getWidth()-w, 
				getY(), w, getHeight());
		firstYRoller = roller.getY();
		initY = getY()-20;
		labels.add(roller);
		CloseButton cB = new CloseButton(getX()+getWidth()-40, getY()+15, 25, 25, 0, 
				Game.spr_entities.getSprite(76, 181, 25, 25), this);
		labels.add(cB);
		
		changeTickers();
	}
	
	public void tick() {
		this.order = 2;
		for(int i = 0; i < labels.size(); i++) {
			labels.get(i).tick();
		}
		for(int i = 0; i < pericias.size(); i++) {
			if(pericias.get(i).getY()+pericias.get(i).getHeight() < getY()+getHeight() && pericias.get(i).getY()+pericias.get(i).inLocal+inLocal>getY()+49+inLocal*2) {
				pericias.get(i).tick();
			}
		}
		
		int newTimes = 1;
		for(int i = 0; i < pericias.size(); i++) {
			SelectionSquareTextLabel l = pericias.get(i);
			int times = i+1;
			int height = 0;
			if(i < 4) {
				height = l.getHeight()+50;
			}else {
				height = l.getHeight()+50;
			}
			int calc = initY+height*newTimes;
			l.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
			if(times%4==0) {
				newTimes++;
			}
		}
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.DARK_GRAY);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawLine(getX()+15, getY()+100, getX()+getWidth()-15, getY()+100);
		g.setFont(new Font("sitka banner", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("Escolha uma pericia", getX()+getWidth()/2-100, getY()+60);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		for(int i = 0; i < pericias.size(); i++) {
			if(pericias.get(i).getY()+pericias.get(i).getHeight() < getY()+getHeight() && pericias.get(i).getY()+pericias.get(i).inLocal+inLocal>getY()+99+inLocal*2) {
				pericias.get(i).render(g);
			}
		}
	}

}
