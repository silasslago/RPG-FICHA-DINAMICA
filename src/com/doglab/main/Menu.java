package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.doglab.entities.CharacterIcon;
import com.doglab.entities.TextLabel;

public class Menu {

	public Menu() {
		CharacterIcon characterIcon = new CharacterIcon(350+65, 95+65, 1, 1, 0, null);
		Game.entities.add(characterIcon);
		
		TextLabel title = new TextLabel(210, 60, 200, 31, 0, null, new Font("sitka banner", Font.PLAIN, 31), 
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1);
		TextLabel detalhes = new TextLabel(65, 130, 200, 31, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "DETALHES PESSOAIS", 1);
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
		TextLabel nome = new TextLabel(30, 165, 32, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Nome", 0);
		TextLabel player = new TextLabel(30, 220, 40, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Jogador", 0);
		TextLabel ocupation = new TextLabel(30, 275, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Ocupação", 0);
		TextLabel idade = new TextLabel(30, 330, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Idade", 0);
		TextLabel sex = new TextLabel(30, 385, 25, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Sexo", 0);
		TextLabel ldM = new TextLabel(30, 440, 105, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Local de Nascimento", 0);
		TextLabel ldR = new TextLabel(30, 495, 95, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "Local de residencia", 0);
		TextLabel namePlayer = new TextLabel(30, 190, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "nome", 0);
		TextLabel playerPlayer = new TextLabel(30, 245, 30, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "player", 0);
		TextLabel ocupationPlayer = new TextLabel(30, 300, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "ocupation", 0);
		TextLabel agePlayer = new TextLabel(30, 355, 18, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "age", 0);
		TextLabel genderPlayer = new TextLabel(30, 410, 35, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "gender", 0);
		TextLabel bornPlayer = new TextLabel(30, 465, 50, 20, 0, null, new Font("sitka banner", Font.PLAIN, 13), 
				new Color(0xFFE8EDEB), "bornPlace", 0);
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
		Game.entities.add(namePlayer);
		Game.entities.add(playerPlayer);
		Game.entities.add(ocupationPlayer);
		Game.entities.add(agePlayer);
		Game.entities.add(genderPlayer);
		Game.entities.add(bornPlayer);
		Game.entities.add(livePlayer);
		Game.entities.add(ldR);
		Game.entities.add(ldM);
		Game.entities.add(sex);
		Game.entities.add(idade);
		Game.entities.add(ocupation);
		Game.entities.add(player);
		Game.entities.add(nome);
		Game.entities.add(title);
		Game.entities.add(detalhes);
		Game.entities.add(life);
		Game.entities.add(sani);
		Game.entities.add(ocul);
		Game.entities.add(extrada);
		Game.entities.add(corpo);
		Game.entities.add(expPar);
	}
	
	public void render(Graphics g) {
		
		// Fundo
		g.setColor(new Color(0xFF000000));
		g.fillRect(0, 0, Game.WIDTH, Game.WIDTH);
		
		// Imagens
		g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), 510, 125, null);
		g.drawImage(Game.spr_entities.getSprite(0, 156, 76, 71), 545, 390, 54, 48, null);
		g.drawImage(Game.player.icon, 350, 95, 130, 130, null);
		
		// Barras
		g.setColor(new Color(0xFF500101));
		g.fillRect(325, 290, (280*Game.player.currentLife)/Game.player.maxLife, 30);
		g.setColor(new Color(0xFF001752));
		g.fillRect(325, 400, (200*Game.player.currentLife)/Game.player.maxLife, 30);
		g.setColor(new Color(0xFF2F014F));
		g.fillRect(325, 510, (280*Game.player.currentLife)/Game.player.maxLife, 30);
		
		
		// Paineis
		g.setColor(new Color(0xFF424242));
		g.drawRect(10, 100, Game.WIDTH/2-10, 470);
		g.drawRect(325, 290, 280, 30);
		g.drawRect(325, 400, 200, 30);
		g.drawRect(325, 510, 280, 30);
		g.drawOval(350, 95, 130, 130);
		g.drawImage(Game.spr_entities.getSprite(0, 0, 196, 156), 318, 83, null);
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
