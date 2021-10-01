package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class Skills extends Label{

	private Roller roller;
	private int initY, initX, firstYRoller;
	public ArrayList<SquareTextLabel> squares, currents;
	private TextLabel searchBar;
	private BufferedImage lupa;
	
	@SuppressWarnings("unchecked")
	public Skills(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		lupa = Game.spr_entities.getSprite(201, 206, 25, 25);
		squares = new ArrayList<SquareTextLabel>();

		this.searchBar = new TextLabel(getX()+getWidth()-170, getY()+30, 70, 19, 0, null, 
				new Font("sitka banner", Font.BOLD, 21), new Color(0xFFE8EDEB), "", 0, true);
		searchBar.canClick(true);
		labels.add(searchBar);
		
		int w = 12;
		roller = new Roller(getX()+getWidth()-w, getY(), w, 165, 4, null, true, getX()+getWidth()-w, 
				getY(), w, getHeight());
		firstYRoller = roller.getY();
		labels.add(roller);
		
		TextLabel pericias = new TextLabel(getX()+290, getY()+30, 70, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "PERÍCIAS", 1, false);
		labels.add(pericias);
		
		SquareTextLabel antro = new SquareTextLabel(getX()+20, getY()+60, 110, 95, 0, null, 1, "Antropologia");
		squares.add(antro);
		
		SquareTextLabel fireGuns = new SquareTextLabel(getX()+170, getY()+60, 110, 95, 0, null, 1, "Armas de Fogo(P)");
		squares.add(fireGuns);
		
		SquareTextLabel fireGunsRE = new SquareTextLabel(getX()+340, getY()+60,110, 95, 0, null, 1, "Armas de Fogo(R|E)");
		squares.add(fireGunsRE);
		
		SquareTextLabel fireGunsAB = new SquareTextLabel(getX()+510, getY()+60, 110, 95, 0, null, 1, "Armas de Fogo(A|B)");
		squares.add(fireGunsAB);
		
		SquareTextLabel arque = new SquareTextLabel(getX()+20, getY()+170, 110, 95, 0, null, 1, "Arqueologia");
		squares.add(arque);
		
		SquareTextLabel arrem = new SquareTextLabel(getX()+170, getY()+170, 110, 95, 0, null, 1, "Arremessar");
		squares.add(arrem);
		
		SquareTextLabel artO = new SquareTextLabel(getX()+340, getY()+170, 110, 95, 0, null, 1, "Arte|Ofício");
		squares.add(artO);
		
		SquareTextLabel aval = new SquareTextLabel(getX()+510, getY()+170, 110, 95, 0, null, 1, "Avaliação");
		squares.add(aval);
		
		SquareTextLabel charm = new SquareTextLabel(getX()+20, getY()+280, 110, 95, 0, null, 1, "Charme");
		squares.add(charm);
		
		SquareTextLabel chav = new SquareTextLabel(getX()+170, getY()+280, 110, 95, 0, null, 1, "Chaveiro");
		squares.add(chav);
		
		SquareTextLabel cienF = new SquareTextLabel(getX()+340, getY()+280, 110, 95, 0, null, 1, "Ciência Forense");
		squares.add(cienF);
		
		SquareTextLabel consE = new SquareTextLabel(getX()+510, getY()+280, 110, 95, 0, null, 1, "Eletrônica");
		squares.add(consE);
		
		SquareTextLabel consM = new SquareTextLabel(getX()+20, getY()+390, 110, 95, 0, null, 1, "Mecânica");
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
		
		SquareTextLabel lab = new SquareTextLabel(getX()+170, getY()+610, 110, 95, 0, null, 1, "Lábia");
		squares.add(lab);
		
		SquareTextLabel inti = new SquareTextLabel(getX()+340, getY()+610, 110, 95, 0, null, 1, "Intimidação");
		squares.add(inti);
		
		SquareTextLabel hist = new SquareTextLabel(getX()+510, getY()+610, 110, 95, 0, null, 1, "História");
		squares.add(hist);
		
		SquareTextLabel furt = new SquareTextLabel(getX()+20, getY()+720, 110, 95, 0, null, 1, "Furtividade");
		squares.add(furt);
		
		SquareTextLabel lut = new SquareTextLabel(getX()+170, getY()+720, 110, 95, 0, null, 1, "Lutar(Brigar)");
		squares.add(lut);
		
		SquareTextLabel med = new SquareTextLabel(getX()+340, getY()+720, 110, 95, 0, null, 1, "Medicina");
		squares.add(med);
		
		SquareTextLabel exp = new SquareTextLabel(getX()+510, getY()+720, 110, 95, 0, null, 1, "Exp. Paranormal");
		squares.add(exp);
		
		SquareTextLabel mund = new SquareTextLabel(getX()+20, getY()+830, 110, 95, 0, null, 1, "Mundo Natural");
		squares.add(mund);
		
		SquareTextLabel nat = new SquareTextLabel(getX()+170, getY()+830, 110, 95, 0, null, 1, "Natação");
		squares.add(nat);
		
		SquareTextLabel nav = new SquareTextLabel(getX()+340, getY()+830, 110, 95, 0, null, 1, "Navegação");
		squares.add(nav);
		
		SquareTextLabel cred = new SquareTextLabel(getX()+510, getY()+830, 110, 95, 0, null, 1, "Nível de Crédito");
		squares.add(cred);
		
		SquareTextLabel ocul = new SquareTextLabel(getX()+20, getY()+940, 110, 95, 0, null, 1, "Ocultismo");
		squares.add(ocul);
		
		SquareTextLabel opm = new SquareTextLabel(getX()+170, getY()+940, 110, 95, 0, null, 1, "Operar Máquinario");
		squares.add(opm);
		
		SquareTextLabel per = new SquareTextLabel(getX()+340, getY()+940, 110, 95, 0, null, 1, "Persuasão");
		squares.add(per);
		
		SquareTextLabel pil = new SquareTextLabel(getX()+510, getY()+940, 110, 95, 0, null, 1, "Pilotar");
		squares.add(pil);
		
		SquareTextLabel pres = new SquareTextLabel(getX()+20, getY()+1050, 110, 95, 0, null, 1, "Prestidigitação");
		squares.add(pres);
		
		SquareTextLabel prs = new SquareTextLabel(getX()+170, getY()+1050, 110, 95, 0, null, 1, "Primeiros Socorros");
		squares.add(prs);
		
		SquareTextLabel psic = new SquareTextLabel(getX()+340, getY()+1050, 110, 95, 0, null, 1, "Psicanálise");
		squares.add(psic);
		
		SquareTextLabel psico = new SquareTextLabel(getX()+510, getY()+1050, 110, 95, 0, null, 1, "Psicologia");
		squares.add(psico);
		
		SquareTextLabel salt = new SquareTextLabel(getX()+20, getY()+1160, 110, 95, 0, null, 1, "Saltar");
		squares.add(salt);
		
		SquareTextLabel rast = new SquareTextLabel(getX()+170, getY()+1160, 110, 95, 0, null, 1, "Rastrear");
		squares.add(rast);
		
		SquareTextLabel sob = new SquareTextLabel(getX()+340, getY()+1160, 110, 95, 0, null, 1, "Sobrevivência");
		squares.add(sob);
		
		SquareTextLabel usb = new SquareTextLabel(getX()+510, getY()+1160, 110, 95, 0, null, 1, "Usar Biblioteca");
		squares.add(usb);
		
		SquareTextLabel usc = new SquareTextLabel(getX()+20, getY()+1270, 110, 95, 0, null, 1, "Usar Computador");
		squares.add(usc);
		
		SquareTextLabel lino = new SquareTextLabel(getX()+170, getY()+1270, 110, 95, 0, null, 1, "Língua Outra");
		squares.add(lino);
		
		SquareTextLabel cienQ = new SquareTextLabel(getX()+340, getY()+1270, 110, 95, 0, null, 1, "Ciência Química");
		squares.add(cienQ);
		
		SquareTextLabel cienFa = new SquareTextLabel(getX()+510, getY()+1270, 110, 95, 0, null, 1, "Ciência Farmácia");
		squares.add(cienFa);

		SquareTextLabel acrobacia = new SquareTextLabel(getX()+20, getY()+1380, 110, 95, 0, null, 1, "Acrobacia");
		squares.add(acrobacia);
		
		SquareTextLabel cavalgar = new SquareTextLabel(getX()+170, getY()+1380, 110, 95, 0, null, 1, "Cavalgar");
		squares.add(cavalgar);
		
		SquareTextLabel cienBio = new SquareTextLabel(getX()+340, getY()+1380, 110, 95, 0, null, 1, "Ciência Biologia");
		squares.add(cienBio);
		
		SquareTextLabel demolicao = new SquareTextLabel(getX()+510, getY()+1380, 110, 95, 0, null, 1, "Demolição");
		squares.add(demolicao);
		
		SquareTextLabel hipnose = new SquareTextLabel(getX()+20, getY()+1490, 110, 95, 0, null, 1, "Hipnose");
		squares.add(hipnose);
		
		SquareTextLabel letLab = new SquareTextLabel(getX()+170, getY()+1490, 110, 95, 0, null, 1, "Leitura Lábial");
		squares.add(letLab);
		
		SquareTextLabel lingNat = new SquareTextLabel(getX()+340, getY()+1490, 110, 95, 0, null, 1, "Língua Nativa");
		squares.add(lingNat);
		
		SquareTextLabel mergulho = new SquareTextLabel(getX()+510, getY()+1490, 110, 95, 0, null, 1, "Mergulho");
		squares.add(mergulho);
		
		SquareTextLabel pilAer = new SquareTextLabel(getX()+20, getY()+1600, 110, 95, 0, null, 1, "Pilotar (Aeronave)");
		squares.add(pilAer);
		
		SquareTextLabel pilBar = new SquareTextLabel(getX()+170, getY()+1600, 110, 95, 0, null, 1, "Pilotar (Barcos)");
		squares.add(pilBar);
		
		SquareTextLabel treiAni = new SquareTextLabel(getX()+340, getY()+1600, 110, 95, 0, null, 1, "Treinar Animais");
		squares.add(treiAni);
		
		SquareTextLabel mat = new SquareTextLabel(getX()+510, getY()+1600, 110, 95, 0, null, 1, "Matemática");
		squares.add(mat);
		
		SquareTextLabel zoo = new SquareTextLabel(getX()+20, getY()+1710, 110, 95, 0, null, 1, "Zoologia");
		squares.add(zoo);
		
		currents = (ArrayList<SquareTextLabel>) squares.clone();
		
		initY = getY()-90;
		initX = getX()+20;
	}
	
	@SuppressWarnings("unchecked")
	public void tick() {
		super.tick();
		if(tick) {
			searchBar.setHeight(19);
			searchBar.setWidth(140);
		
			if(!searchBar.text.equals("")) {
				roller.setY(getY());
				String bar = searchBar.text;
				currents.clear();
				for(SquareTextLabel sTL : squares) {
					String cSkill = ((TextLabel)sTL.labels.get(1)).text;
					String curName = "";
					for(int i = 0; i < bar.length(); i++) {
						curName+=cSkill.charAt(i);
					}
					if(curName.toLowerCase().equals(bar.toLowerCase())) {
						currents.add(sTL);
					}
				}
			}else {
				currents = (ArrayList<SquareTextLabel>) squares.clone();
			}
			for(int i = 0; i < currents.size(); i++) {
				SquareTextLabel l = currents.get(i);
				if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+49+inLocal*2) {
					l.tick();
				}
			}
			
			int newTimes = 1;
			for(int i = 0; i < currents.size(); i++) {
				SquareTextLabel l = currents.get(i);
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
			
			int[] newX = {getX()+20, getX()+170, getX()+340, getX()+510};
			int current = 0;
			for(int i = 0; i < currents.size(); i++) {
				SquareTextLabel l = currents.get(i);
				l.setX(newX[current]);
				current++;
				if(current>=4) {
					current=0;
				}
			}
			
		}
	}

	public void render(Graphics g){
		super.render(g);
		g.setColor(new Color(0xFFE8EDEB));
		//g.fillRect(getX()+getWidth()-180-inLocal, getY()+10+inLocal-Game.roller.getY()*Game.roller.step, 160, 29);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(getX()+getWidth()-180-inLocal, getY()+10+inLocal-Game.roller.getY()*Game.roller.step, 160, 29);
		g.drawImage(lupa, getX()+getWidth()-215-inLocal, 
				getY()+15+inLocal-Game.roller.getY()*Game.roller.step, null);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+50, 
				getX()+getWidth()-inLocal-15, getY()+inLocal+50-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < currents.size(); i++) {
			SquareTextLabel l = currents.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+49+inLocal*2) {
				l.render(g);
			}
		}
		this.searchBar.render(g);
	}
	
	public ArrayList<SquareTextLabel> getSkills(){
		return this.squares;
	}
	
	public void setSkills(ArrayList<SquareTextLabel> pericias) {
		this.squares = pericias;
	}

}
