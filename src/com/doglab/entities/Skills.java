package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class Skills extends Label{

	private Roller roller;
	private int initY, firstYRoller;
	private ArrayList<SquareTextLabel> squares;
	
	public Skills(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		squares = new ArrayList<SquareTextLabel>();
		
		roller = new Roller(getX()+getWidth()-8, getY(), 8, 40, 10, null, true, getX()+getWidth()-8, 
				getY(), 8, getHeight());
		firstYRoller = roller.getY();
		labels.add(roller);
		
		TextLabel pericias = new TextLabel(getX()+290, getY()+30, 70, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Per�cias", 1);
		labels.add(pericias);
		
		SquareTextLabel antro = new SquareTextLabel(getX()+20, getY()+60, 110, 95, 0, null, 1, "Antropologia");
		squares.add(antro);
		
		SquareTextLabel fireGuns = new SquareTextLabel(getX()+170, getY()+60, 110, 95, 0, null, 1, "Armas de Fogo(P)");
		squares.add(fireGuns);
		
		SquareTextLabel fireGunsRE = new SquareTextLabel(getX()+340, getY()+60,110, 95, 0, null, 1, "Armas de Fogo(R/E)");
		squares.add(fireGunsRE);
		
		SquareTextLabel fireGunsAB = new SquareTextLabel(getX()+510, getY()+60, 110, 95, 0, null, 1, "Armas de Fogo(A/B)");
		squares.add(fireGunsAB);
		
		SquareTextLabel arque = new SquareTextLabel(getX()+20, getY()+170, 110, 95, 0, null, 1, "Arqueologia");
		squares.add(arque);
		
		SquareTextLabel arrem = new SquareTextLabel(getX()+170, getY()+170, 110, 95, 0, null, 1, "Arremessar");
		squares.add(arrem);
		
		SquareTextLabel artO = new SquareTextLabel(getX()+340, getY()+170, 110, 95, 0, null, 1, "Arte/Of�cio");
		squares.add(artO);
		
		SquareTextLabel aval = new SquareTextLabel(getX()+510, getY()+170, 110, 95, 0, null, 1, "Avalia��o");
		squares.add(aval);
		
		SquareTextLabel charm = new SquareTextLabel(getX()+20, getY()+280, 110, 95, 0, null, 1, "Charme");
		squares.add(charm);
		
		SquareTextLabel chav = new SquareTextLabel(getX()+170, getY()+280, 110, 95, 0, null, 1, "Chaveiro");
		squares.add(chav);
		
		SquareTextLabel cienF = new SquareTextLabel(getX()+340, getY()+280, 110, 95, 0, null, 1, "Ci�ncia Forense");
		squares.add(cienF);
		
		SquareTextLabel consE = new SquareTextLabel(getX()+510, getY()+280, 110, 95, 0, null, 1, "Conserto Eletr�nico");
		squares.add(consE);
		
		SquareTextLabel consM = new SquareTextLabel(getX()+20, getY()+390, 110, 95, 0, null, 1, "Conserto Mec�nico");
		squares.add(consM);
		
		SquareTextLabel cont = new SquareTextLabel(getX()+170, getY()+390, 110, 95, 0, null, 1, "Contabilidade");
		squares.add(cont);
		
		SquareTextLabel dir = new SquareTextLabel(getX()+340, getY()+390, 110, 95, 0, null, 1, "Direito");
		squares.add(dir);
		
		SquareTextLabel diri = new SquareTextLabel(getX()+510, getY()+390, 110, 95, 0, null, 1, "Dirigir");
		squares.add(diri);
		
		SquareTextLabel disf = new SquareTextLabel(getX()+20, getY()+500, 110, 95, 0, null, 1, "Disfarce");
		squares.add(disf);
		
		SquareTextLabel enc = new SquareTextLabel(getX()+170, getY()+500, 110, 95, 0, null, 1, "Encontrar");
		squares.add(enc);
		
		SquareTextLabel escu = new SquareTextLabel(getX()+340, getY()+500, 110, 95, 0, null, 1, "Escutar");
		squares.add(escu);
		
		SquareTextLabel esca = new SquareTextLabel(getX()+510, getY()+500, 110, 95, 0, null, 1, "Escalar");
		squares.add(esca);
		
		SquareTextLabel esq = new SquareTextLabel(getX()+20, getY()+610, 110, 95, 0, null, 1, "Esquivar");
		squares.add(esq);
		
		SquareTextLabel lab = new SquareTextLabel(getX()+170, getY()+610, 110, 95, 0, null, 1, "L�bia");
		squares.add(lab);
		
		SquareTextLabel inti = new SquareTextLabel(getX()+340, getY()+610, 110, 95, 0, null, 1, "Intimida��o");
		squares.add(inti);
		
		SquareTextLabel hist = new SquareTextLabel(getX()+510, getY()+610, 110, 95, 0, null, 1, "Hist�ria");
		squares.add(hist);
		
		SquareTextLabel furt = new SquareTextLabel(getX()+20, getY()+720, 110, 95, 0, null, 1, "Furtividade");
		squares.add(furt);
		
		SquareTextLabel lut = new SquareTextLabel(getX()+170, getY()+720, 110, 95, 0, null, 1, "Lutar(Brigar)");
		squares.add(lut);
		
		SquareTextLabel med = new SquareTextLabel(getX()+340, getY()+720, 110, 95, 0, null, 1, "Medicina");
		squares.add(med);
		
		SquareTextLabel exp = new SquareTextLabel(getX()+510, getY()+720, 110, 95, 0, null, 1, "Exposi��o Paranormal");
		squares.add(exp);
		
		SquareTextLabel mund = new SquareTextLabel(getX()+20, getY()+830, 110, 95, 0, null, 1, "Mundo Natural");
		squares.add(mund);
		
		SquareTextLabel nat = new SquareTextLabel(getX()+170, getY()+830, 110, 95, 0, null, 1, "Nata��o");
		squares.add(nat);
		
		SquareTextLabel nav = new SquareTextLabel(getX()+340, getY()+830, 110, 95, 0, null, 1, "Navega��o");
		squares.add(nav);
		
		SquareTextLabel cred = new SquareTextLabel(getX()+510, getY()+830, 110, 95, 0, null, 1, "N�vel de Cr�dito");
		squares.add(cred);
		
		SquareTextLabel ocul = new SquareTextLabel(getX()+20, getY()+940, 110, 95, 0, null, 1, "Ocultismo");
		squares.add(ocul);
		
		SquareTextLabel opm = new SquareTextLabel(getX()+170, getY()+940, 110, 95, 0, null, 1, "Operar M�quinario");
		squares.add(opm);
		
		SquareTextLabel per = new SquareTextLabel(getX()+340, getY()+940, 110, 95, 0, null, 1, "Persuas�o");
		squares.add(per);
		
		SquareTextLabel pil = new SquareTextLabel(getX()+340, getY()+940, 110, 95, 0, null, 1, "Pilotar");
		squares.add(pil);
		
		SquareTextLabel pres = new SquareTextLabel(getX()+20, getY()+1050, 110, 95, 0, null, 1, "Prestidigita��o");
		squares.add(pres);
		
		SquareTextLabel prs = new SquareTextLabel(getX()+170, getY()+1050, 110, 95, 0, null, 1, "Primeiros Socorros");
		squares.add(prs);
		
		SquareTextLabel psic = new SquareTextLabel(getX()+340, getY()+1050, 110, 95, 0, null, 1, "Psican�lise");
		squares.add(psic);
		
		SquareTextLabel psico = new SquareTextLabel(getX()+510, getY()+1050, 110, 95, 0, null, 1, "Psicologia");
		squares.add(psico);
		
		SquareTextLabel salt = new SquareTextLabel(getX()+20, getY()+1160, 110, 95, 0, null, 1, "Saltar");
		squares.add(salt);
		
		SquareTextLabel rast = new SquareTextLabel(getX()+170, getY()+1160, 110, 95, 0, null, 1, "Rastrear");
		squares.add(rast);
		
		SquareTextLabel sob = new SquareTextLabel(getX()+340, getY()+1160, 110, 95, 0, null, 1, "Sobreviv�ncia");
		squares.add(sob);
		
		SquareTextLabel usb = new SquareTextLabel(getX()+510, getY()+1160, 110, 95, 0, null, 1, "Usar Biblioteca");
		squares.add(usb);
		
		SquareTextLabel usc = new SquareTextLabel(getX()+20, getY()+1270, 110, 95, 0, null, 1, "Usar Computador");
		squares.add(usc);
		
		SquareTextLabel lino = new SquareTextLabel(getX()+170, getY()+1270, 110, 95, 0, null, 1, "L�ngua Outra");
		squares.add(lino);
		
		SquareTextLabel cienQ = new SquareTextLabel(getX()+340, getY()+1270, 110, 95, 0, null, 1, "Ci�ncia Qu�mica");
		squares.add(cienQ);
		
		SquareTextLabel cienFa = new SquareTextLabel(getX()+510, getY()+1270, 110, 95, 0, null, 1, "Ci�ncia Farm�cia");
		squares.add(cienFa);
		
		initY = getY()-90;
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			if(current) {
				inLocal = this.size;
			}else {
				inLocal = 0;
			}
			
			for(int i = 0; i < squares.size(); i++) {
				SquareTextLabel l = squares.get(i);
				if(l.getY()+l.getHeight() < getY()+getHeight() ) {
					l.tick();
				}
			}
			
			int newTimes = 1;
			for(int i = 0; i < squares.size(); i++) {
				SquareTextLabel l = squares.get(i);
				int times = i+1;
				int height = 0;
				if(i < 4) {
					height = l.getHeight()+50-l.inLocal*2;
				}else {
					height = l.getHeight()+50-l.inLocal*2;
				}
				int calc = initY-l.inLocal+height*newTimes;
				l.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
				if(times%4==0) {
					newTimes++;
				}
			}
		}
	}
	
	public void render(Graphics g){
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < squares.size(); i++) {
			SquareTextLabel l = squares.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+49+inLocal*2) {
				l.render(g);
			}
		}
	}

}
