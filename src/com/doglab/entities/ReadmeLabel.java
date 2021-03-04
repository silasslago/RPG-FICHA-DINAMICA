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

	private Color discord = new Color(0xFF4FF3FF), dogoso = new Color(0xFF4FF3FF), dutrag = new Color(0xFF4FF3FF);
	
	public ReadmeLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}
	
	public void tick() {
		order = 10;
		Entity mouse = new Entity(Game.mouseController.getX(), Game.mouseController.getY(), Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
		Entity dogoso = new Entity(getX()+267, getY()+549, 80, 18, 0, null);
		Entity dutrag = new Entity(getX()+113, getY()+113, 80, 18, 0, null);
		Entity discord = new Entity(getX()+252, getY()+595, 55, 18, 0, null);
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
				desk.browse(new URI("https://discord.gg/sajZFsU556"));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
		Entity mouseCurrent = new Entity(Game.mouseController.currentX, Game.mouseController.currentY, Game.mouseController.getWidth(), Game.mouseController.getHeight(), 0, null);
		if(this.isColliding(mouseCurrent, dogoso) || this.isColliding(mouseCurrent, dutrag) || this.isColliding(mouseCurrent, discord)) {
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
	
	 private void drawString(Graphics g, String text, int x, int y) {
		 for (String line : text.split("\n"))
	            g.drawString(line, x, y += g.getFontMetrics().getHeight());
	 }
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 51));
		g.drawString("LEIA-ME", getX()+200, getY()+65);
		g.drawLine(getX()+15, getY()+70, getX()+getWidth()-15, getY()+70);
		Font font = new Font("sitka banner", Font.BOLD, 18);
		g.setFont(font);
		String s = "A Dog Lab apenas representa a recriação em Java da ficha originalmente\n"
				 + "criada pelo @jvdutrag(Twitter) para uso exclusivo do streamer Cellbit\n"
				 + "em sua série original de RPG \"Ordem Paranormal Desconjuração\", por\n"
				 + "isso antes de mim, todos os respectivos créditos a esse grande\n"
				 + "programador.\n"
				 + "\n"
				 + "ATENÇÃO: NÃO ALTERE EM NENHUMA POSSIBILIDADE SEU ARQUIVO \n"
				 + "\"info.txt\"gerado pelo app, isso pode ocasionar em futuros crash's\n"
				 + "e bugs que só poderam ser corrigidos ao excluir o seu próprio arquivo\n"
				 + "\"info.txt\" além de ocasionar na perda de todos os dados alterados em\n"
				 + "sua ficha.\n"
				 + "\n"
				 + "A opção de ativação do \"COD System\" é nada mais nada menos que deixar\n"
				 + "a sua ficha totalmente automatizada para o sistema de RPG de Call Of \n"
				 + "Ctchulu, impossibilitando com que alguns valores sejam alterados, já que\n"
				 + "os mesmos são calculados automaticamente usando o sistema de COD, como a \n"
				 + "vida máxima, movimento, entre outros.\n"
				 + "\n"
				 + "Aos amigos programadores que quiserem analisar o código para estudo\n"
				 + "ou até mesmo para modificação, sinta-sem livres para visitar meu github\n"
				 + "disponibilizado no meu Twitter(@Dogoso4), essa ficha tem o código aberto\n"
				 + "a todos que se sentirem inspirados a modifica-lo ou analisa-lo e foi criada\n"
				 + "em parceria com o servidor do discord \"Ordem da Realidade\".\n"
				 + "\n";
		drawString(g, s, getX()+30, getY()+80);
		g.setColor(new Color(0xFF424242));
		g.drawString("(clique aqui para fechar)", getX()+210, getY()+getHeight()-10);
		Graphics2D g2D = (Graphics2D)g;
		String s2 = "@Dogoso4";
		String s3 = "@jvdutrag";
		String s4 = "discord";
		drawWithUnderline(g2D, s2, font, this.dogoso, getX()+267, getY()+563);
		drawWithUnderline(g2D, s3, font, this.dutrag, getX()+113, getY()+126);
		drawWithUnderline(g2D, s4, font, this.discord, getX()+252, getY()+609);
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
