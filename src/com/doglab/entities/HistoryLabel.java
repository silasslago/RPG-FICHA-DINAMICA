package com.doglab.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONException;

import com.doglab.api.API;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class HistoryLabel extends Label{

	public static String historic = "";
	public static String defString = "{NOME}@ -Teste: {TESTE}@ -Valor do dado: {VALOR}@ -Resultado: {RESULTADO}@ -Horas: {HORARIO}@@";
	public static String diceTable = "";
	
	private Roller roller;
	private BufferedImage clear;
	
	public HistoryLabel(double x, double y, int width, int height) {
		super(x, y, width, height, 0, null);
		int w = 12;
		roller = new Roller(getX()+getWidth()-w, getY(), w, 165, 6, null, false, getX()+getWidth()-w, 
				getY(), w, getHeight());
		clear = Game.spr_entities.getSprite(226, 131, 25, 25);
	}

	public static void newLog(String state, String dicePlayed, String value, String name) {
		String s = defString;
		s = s.replace("{NOME}", name.toUpperCase());
		s = s.replace("{VALOR}", value);
		s = s.replace("{TESTE}", dicePlayed);
		if(state!=null) {
			s = s.replace("{RESULTADO}", state);
		}else {
			s = s.replace("{RESULTADO}", "?");
		}
		Date d = new Date();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(d);
		s = s.replace("{HORARIO}", 
				Integer.toString(calendar.get(Calendar.HOUR_OF_DAY))+
				":"+Integer.toString(calendar.get(Calendar.MINUTE))+
				":"+Integer.toString(calendar.get(Calendar.SECOND)));
		historic+=s;
		// Atualiza a mesa
		if(Game.online) {
			if(Game.actor.equals("Jogador")) {
				try {
					String rolls = API.readPage(diceTable).replace("</p>", "");
					API.updateDiceTable(diceTable, "<p>"+rolls+s+"</p>");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void tick() {
		super.tick();
		roller.tick();
		Entity cleaner = new Entity(getX()+getWidth()-50, getY()+30, 26, 25, 0, null);
		if(this.isColliding(cleaner, Game.mouseController)) {
			Game.mouseController.resetPosition();
			historic = "";
			if(Game.online) {
				try {
					API.updateDiceTable(diceTable, "<p>@</p>");
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			roller.setY(getY());
		}
	}
	
	public void drawString(Graphics g, String text, int x, int y) {
		for (String line : text.split("@")) {
			if(y > getY()+70 && y < getY()+getHeight()) {
				g.drawString(line, x, y);	
			}
			y += g.getFontMetrics().getHeight();
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+20, getY()+60, getX()+getWidth()-20, getY()+60);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		g.setColor(TextLabel.text_color);
		g.setFont(Menu.curFont.deriveFont(25.0f));
		g.drawString("HISTÓRICO", 
				getX()+getWidth()/2 - g.getFontMetrics().stringWidth("HISTÓRICO")/2, 
				getY()+50);
		g.setFont(Menu.curFont.deriveFont(15.0f));
		drawString(g, historic, getX()+20, getY()+155- roller.getY()*roller.step);	
		roller.render(g);
		g.drawImage(clear, getX()+getWidth()-50, getY()+30, 26, 25, null);
	}
}
