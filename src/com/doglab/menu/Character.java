package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Character extends Label{

	private String fileName;
	private String life;
	private ArrayList<Label> files;
	private BufferedImage bar, barrier, camera;
	
	private Image icon;
	
	public Character(double x, double y, String fileName) {
		super(x, y, 180, 250, 0, null);
		files = new ArrayList<Label>();
		this.fileName = fileName;
		File file = new File(Game.files.getCurPath()+fileName+"/");
		if(!file.exists()) {
			Menu.current = Game.files.getCurPath()+fileName+".txt";
			String line = "Details%DETALHES PESSOAIS%Local de residencia%Local de Nascimento%Sexo%Idade%Ocupação%Jogador%Nome%nome%player%ocupation%age%gender%bornPlace%livingPlace%/DetailsX%85%50%50%50%50%50%50%50%60%60%60%60%60%60%60%/Icon%null/Stats%Lesão Grave<>false%Inconsciente<>false%Morrendo<>false%Traumatizado<>false%Enlouquecido<>false%1%1%1%1%1%1%1%1%1%Vida%Sanidade%Ocultismo%Dano Extra%Corpo%Exp. Par.%/StatsX%350%450%560%370%500%500%473%448%475%473%500%500%600%400%340%340%340%370%490%580%/Atributes%Atributos%Aparência%1%Constituição%1%Destreza%1%Educação%1%Força%1%Inteligência%1%Poder%1%Sorte%1%Movimento%1%Tamanho%1%/AtributesX%130%55%80%145%174%245%268%55%80%162%174%237%268%67%80%162%174%237%268%57%80%/FastSkillsLabel%Perícias%(acesso rápido)%/FastSkillsLabelX%460%458%/FastSkills%/CharacterLabel%/CombatLabel%Combate%Nome%Tipo%Dano%Mun. Atual%Mun. Máx.%Ataque%Alcance%Defeito%Area%/CombatLabelX%280%45%150%240%330%400%465%515%570%620%/Guns%0%/RitualsLabel%Rituais%/RitualsLabelX%295%/Rituals%0%/Skills%Perícias%/SkillsX%300%/SkillsRoll%1<>Antropologia<>%1<>Armas de Fogo(P)<>%1<>Armas de Fogo(R|E)<>%1<>Armas de Fogo(A|B)<>%1<>Arqueologia<>%1<>Arremessar<>%1<>Arte|Ofício<>%1<>Avaliação<>%1<>Charme<>%1<>Chaveiro<>%1<>Ciência Forense<>%1<>Eletrônica<>%1<>Mecânica<>%1<>Contabilidade<>%1<>Direito<>%1<>Dirigir<>%1<>Disfarce<>%1<>Encontrar<>%1<>Escutar<>%1<>Escalar<>%1<>Esquivar<>%1<>Lábia<>%1<>Intimidação<>%1<>História<>%1<>Furtividade<>%1<>Lutar(Brigar)<>%1<>Medicina<>%1<>Exp. Paranormal<>%1<>Mundo Natural<>%1<>Natação<>%1<>Navegação<>%1<>Nível de Crédito<>%1<>Ocultismo<>%1<>Operar Máquinario<>%1<>Persuasão<>%1<>Pilotar<>%1<>Prestidigitação<>%1<>Primeiros Socorros<>%1<>Psicanálise<>%1<>Psicologia<>%1<>Saltar<>%1<>Rastrear<>%1<>Sobrevivência<>%1<>Usar Biblioteca<>%1<>Usar Computador<>%1<>Língua Outra<>%1<>Ciência Química<>%1<>Ciência Farmácia<>%/Inventory%Inventário%Peso Total: %0.0%Dinheiro: %0%Patrimonio: %0%//Itens%/\r\n";
			Menu.saveGame(line);
			life = "";
		}else {
			Menu.current = Game.files.getCurPath()+fileName;
			String[] parts = Menu.returnLife(Menu.loadGame()).split("/");
			life = "";
			life+=parts[1]+"/"+parts[0];
			icon = Menu.returnIcon(Menu.loadGame()).getImage();
		}
		Addition adt = new Addition(80, 130, 250, 350);
		files.add(adt);
		bar = Game.bars.getSprite(0, 98, 454, 98);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
	}
	
	public void tick() {
		if(tick) {
			Entity e = new Entity(Game.mouseController.getX(), 
					Game.mouseController.getY() + Game.files.roller.getY()*Game.files.roller.step
					, 5, 5, 0, null);
			if(this.isColliding(this, e)) {
				Game.mouseController.resetPosition();
				Game.gameState = "FICHA";
				Menu.current = Game.files.getCurPath()+fileName;
				Menu.loadSave(Menu.loadGame());
			}
		}
	}
	
	public void render(Graphics g) {
		// Super
		g.setColor(new Color(0xFF151515));
		g.fillRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFFFF4246));
		//g.drawRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		// ------
		g.drawImage(bar, getX()+15, getY()+195-Game.files.roller.getY()*Game.files.roller.step, 150, 20, null);
		g.setColor(new Color(0xFFE8EDEB));
		//g.drawRect(getX()+5, getY()+222-Game.files.roller.getY()*Game.files.roller.step, 240, 35);
		g.setFont(Menu.specialElite.deriveFont(25.0f));
		g.drawString(fileName.replace(".txt", ""), getX()+getWidth()/2 - g.getFontMetrics().stringWidth(fileName.replace(".txt", ""))/2, getY()+getHeight()-65 -Game.files.roller.getY()*Game.files.roller.step);
		g.setColor(Color.WHITE);
		
		g.drawString(this.life, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(this.life)/2, getY()+getHeight()-35 -Game.files.roller.getY()*Game.files.roller.step);
		g.drawImage(camera, getX()+70, getY()+60-Game.files.roller.getY()*Game.files.roller.step, null);
		g.drawImage(icon, getX()+15, getY()-Game.files.roller.getY()*Game.files.roller.step, 150, 140, null);
		g.drawImage(barrier, getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, 196-16, 156-12, null);
	}
	
	public String getName() {
		return this.fileName;
	}
	
}
