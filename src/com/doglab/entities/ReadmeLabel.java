package com.doglab.entities;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

import javax.imageio.ImageIO;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class ReadmeLabel extends Label{

	private Color discord = new Color(0xFF4FF3FF), 
			dogoso = new Color(0xFF4FF3FF), 
			dutrag = new Color(0xFF4FF3FF), 
			cellbit = new Color(0xFF4FF3FF);
	
	public ReadmeLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		order = 10;
		Entity mouse = new Entity(Game.mouseController.getX(), Game.mouseController.getY(), Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
		Entity dogoso = new Entity(getX()+501, getY()+622, 80, 18, 0, null);
		Entity dutrag = new Entity(getX()+107, getY()+231, 80, 18, 0, null);
		Entity discord = new Entity(getX()+30, getY()+576, 325, 18, 0, null);
		Entity cellbit = new Entity(getX()+361, getY()+231, 55, 18, 0, null);
		Desktop desk = Desktop.getDesktop();
		if(this.isColliding(mouse, dogoso)) {
			Game.mouseController.resetPosition();
			this.dogoso = new Color(0xFFCA44DC);
			try {
				desk.browse(new URI("https://twitter.com/Dogoso4"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}else if(this.isColliding(mouse, dutrag)) {
			Game.mouseController.resetPosition();
			this.dutrag = new Color(0xFFCA44DC);
			try {
				desk.browse(new URI("https://twitter.com/jvdutrag"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
		}else if(this.isColliding(mouse, discord)) {
			Game.mouseController.resetPosition();
			this.discord = new Color(0xFFCA44DC);
			try {
				desk.browse(new URI(""));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}else if(this.isColliding(mouse, cellbit)) {
			Game.mouseController.resetPosition();
			this.cellbit = new Color(0xFFCA44DC);
			try {
				desk.browse(new URI("https://www.twitch.tv/cellbit"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
		Entity mouseCurrent = new Entity(Game.mouseController.currentX, Game.mouseController.currentY, Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
		if(this.isColliding(mouseCurrent, cellbit) ||this.isColliding(mouseCurrent, dogoso) || this.isColliding(mouseCurrent, dutrag) || this.isColliding(mouseCurrent, discord)) {
			Game.mouseController.resetPosition();
			mouseFinger();
		}else {
			Game.mouseController.resetPosition();
			mouseSign();
		}	
		
		Entity getOut = new Entity(getX()+210, getY()+getHeight()-25, 180, 20, 0, null);
		if(this.isColliding(getOut, mouse)) {
			Menu.showReadme = false;
			Game.mouseController.resetPosition();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof ReadmeLabel) {
					Game.entities.remove(e);
				}
			}
		}
		
	}
	
	private void mouseFinger() {
		Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/hand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game.setCursor(icon);
	}
	
	private void mouseSign() {
		Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/sign.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Game.setCursor(icon);
	}
	
	public void render(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		super.render(g);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 51));
		g.drawString("IMPORTANTE!", getX()+135, getY()+65);
		g.drawLine(getX()+15, getY()+70, getX()+getWidth()-15, getY()+70);
		Font font = new Font("sitka banner", Font.BOLD, 18);
		g.setFont(font);
		String s = "ATENÇÃO: A DOG LAB apenas representa a recriação da ficha para uso da \n"
				+ "comunidade sem nenhum objetivo comercial a ser alcançado, pedimos aos \n"
				+ "fans que acreditarem em nosso projeto ajudarem com contribuições para \n"
				+ "melhoria e constante atualização dessa ficha, buscamos atender o desejo \n"
				+ "dos fans, pois nós da DOG LAB somos parte da comunidade. Nós não somos \n"
				+ "os criadores originais da ficha, toda a ideia base por trás veio da ficha \n"
				+ "criada por @jvdutrag utilizada pelo streamer CELLBIT em suas lives de RPG \n"
				+ "'Ordem Paranormal Desconjuração', por isso, todos os respectivos créditos \n"
				+ "aos mesmos, REPITO, nós da DOG LAB apenas recriamos a ficha para que \n"
				+ "qualquer um possa utilizar na hora de jogar suas sessões de AOP ou qualquer \n"
				+ "outro RPG que desejarem, já que a ficha é totalmente adaptavel a seus gostos \n"
				+ "com algumas limitações.\n"
				+ "\n"
				+ "Recentemente pensamos muito sobre e decidimos abrir um projeto de \n"
				+ "financiamento para nossos projetos da DOG LAB, não ganhamos nada \n"
				+ "atualizando essa ficha e por isso quase fechamos esse projeto, então pedimos \n"
				+ "que se deseja contribuir com qualquer valor, nós ajude, nosso trabalho sendo \n"
				+ "reconhecido nos da energia para continuar trabalhando na criação de grandes \n"
				+ "projetos como esse. Todas as informações de recompensas por contribuição \n"
				+ "estarão logo abaixo: \n"
				+ "\n"
				+ "https://dogoso.github.io/doglab/index.html \n"
				+ "\n"
				+ "Agradecemos desde já por sua atenção :) - Sigam-nos no twitter! @Dogoso4";
		TextLabel.drawString(g, s, getX()+30, getY()+80);
		g.setColor(new Color(0xFF424242));
		g.drawString("(clique aqui para fechar!)", getX()+210, getY()+getHeight()-10);
		String s2 = "@Dogoso4";
		String s3 = "@jvdutrag";
		String s4 = "https://dogoso.github.io/doglab/index.html";
		String s5 = "CELLBIT";
		drawWithUnderline(g2D, s2, font, this.dogoso, getX()+501, getY()+632);
		drawWithUnderline(g2D, s3, font, this.dutrag, getX()+107, getY()+241);
		drawWithUnderline(g2D, s4, font, this.discord, getX()+30, getY()+586);
		drawWithUnderline(g2D, s5, font, this.cellbit, getX()+361, getY()+241);
	}
	
	public void drawWithUnderline(Graphics2D g2D, String s, Font font, Color color, int x, int y) {
		g2D.setRenderingHint(RenderingHints.KEY_RENDERING,
		        RenderingHints.VALUE_RENDER_DEFAULT);
		g2D.setColor(color);
		AttributedString as = new AttributedString(s);
		as.addAttribute(TextAttribute.FONT, font);
		as.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON, 0, s.length());
		g2D.drawString(as.getIterator(), x, y);
	}

}