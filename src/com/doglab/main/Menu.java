package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.doglab.entities.CharacterIcon;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.EditButton;
import com.doglab.entities.IconLabel;
import com.doglab.entities.TextLabel;

public class Menu {

	private final Color BLACK = new Color(0xFF000000), 
			ORANGE = new Color(0xFFFCC988), 
			PURPLE = new Color(0xFFE688FC), 
			PINK = new Color(0xFFFC88F2), 
			CYAN = new Color(0xFF88F8FC), 
			BLUE = new Color(0xFF8892FC),
			YELLOW = new Color(0xFFFCF688), 
			RED = new Color(0xFFFC8988), 
			GREEN = new Color(0xFF88FC99);
	
	private Color bg = BLACK, life = new Color(0xFF500101),
			sanity = new Color(0xFF001752), ocul = new Color(0xFF2F014F);
	
	public Menu() {
		
		DetailsLabel infoLabel = new DetailsLabel(10, 100, Game.WIDTH/2-10, 470, 0, null);
		Game.entities.add(infoLabel);
		IconLabel iconLabel = new IconLabel(325, 80, 280, 160, 0, null);
		Game.entities.add(iconLabel);
		
		TextLabel title = new TextLabel(210, 60, 200, 31, 0, null, new Font("sitka banner", Font.PLAIN, 31), 
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1);
		
		TextLabel life = new TextLabel(330, 280, 30, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Vida", 0);
		TextLabel sani = new TextLabel(330, 390, 60, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sanidade", 0);
		TextLabel ocul = new TextLabel(330, 500, 70, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Ocultismo", 0);
		TextLabel extrada = new TextLabel(330, 560, 70, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Dano Extra", 1);
		TextLabel corpo = new TextLabel(450, 560, 40, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Corpo", 1);
		TextLabel expPar = new TextLabel(540, 560, 60, 20, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Exp. Par.", 1);
		
		TextLabel livePlayer = new TextLabel(30, 520, 55, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "livingPlace", 0);
		TextLabel lifePlayer = new TextLabel(450, 313, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		TextLabel maxLie = new TextLabel(485, 313, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		TextLabel sanityPlayer = new TextLabel(410, 423, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		TextLabel maxSanityPlayer = new TextLabel(440, 423, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		TextLabel ocultismoPlayer = new TextLabel(450, 533, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 2);
		TextLabel maxOcultismoPlayer = new TextLabel(485, 533, 15, 30, 0, null, new Font("sitka banner", Font.BOLD, 31), 
				new Color(0xFFE8EDEB), "1", 0);
		TextLabel extraDamage = new TextLabel(355, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
		TextLabel body = new TextLabel(460, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
		TextLabel paranormalExp = new TextLabel(560, 605, 20, 30, 0, null, new Font("sitka banner", Font.BOLD, 51), 
				new Color(0xFFE8EDEB), "1", 1);
		
		Game.entities.add(maxLie);
		Game.entities.add(lifePlayer);
		Game.entities.add(sanityPlayer);
		Game.entities.add(maxSanityPlayer);
		Game.entities.add(ocultismoPlayer);
		Game.entities.add(maxOcultismoPlayer);
		Game.entities.add(body);
		Game.entities.add(paranormalExp);
		Game.entities.add(extraDamage);

		Game.entities.add(livePlayer);

		Game.entities.add(title);
		
		Game.entities.add(life);
		Game.entities.add(sani);
		Game.entities.add(ocul);
		Game.entities.add(extrada);
		Game.entities.add(corpo);
		Game.entities.add(expPar);
	}
	
	public void render(Graphics g) {
		
		// Fundo
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
		
		/*g.setColor(ORANGE);
		g.fillRect(10, 40, 25, 25);
		g.setColor(PURPLE);
		g.fillRect(45, 40, 25, 25);
		g.setColor(PINK);
		g.fillRect(80, 40, 25, 25);
		g.setColor(CYAN);
		g.fillRect(115, 40, 25, 25);
		g.setColor(BLUE);
		g.fillRect(150, 40, 25, 25);
		g.setColor(YELLOW);
		g.fillRect(185, 40, 25, 25);
		*/
		
		// Imagens
		g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), 545, 390, 54, 48, null);

		// Barras
		g.setColor(life);
		g.fillRect(325, 290, (280*Game.player.currentLife)/Game.player.maxLife, 30);
		g.setColor(sanity);
		g.fillRect(325, 400, (200*Game.player.currentLife)/Game.player.maxLife, 30);
		g.setColor(ocul);
		g.fillRect(325, 510, (280*Game.player.currentLife)/Game.player.maxLife, 30);
		
		
		// Paineis
		g.setColor(new Color(0xFF424242));
		g.drawRect(325, 290, 280, 30);
		g.drawRect(325, 400, 200, 30);
		g.drawRect(325, 510, 280, 30);
		g.drawOval(350, 95, 130, 130);
		g.fillRect(575, 20, 25, 25);
		
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 31));
		g.drawString("/", 470, 313);
		g.drawString("/", 470, 533);
		g.drawString("/", 425, 423);
		int textY = 165;
		textY+=30;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		textY+=55;
		g.drawLine(30, textY, 280, textY);
		
		g.drawLine(330, 630, 400, 630);
		g.drawLine(430, 630, 510, 630);
		g.drawLine(530, 630, 610, 630);
	}
	
	public void tick() {
		
	}
	
}
