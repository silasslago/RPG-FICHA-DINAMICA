package com.doglab.menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Character extends Label{

	private String fileName;
	private String life;
	private BufferedImage bar, barrier, camera, del;
	
	private Image icon;
	
	public Character(double x, double y, String fileName) {
		super(x, y, 180, 250, 0, null);
		this.fileName = fileName;
		File file = new File(Game.files.getCurPath()+fileName+".txt"+"/");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Menu.current = Game.files.getCurPath()+fileName+".txt";
			String line = "Details%DETALHES PESSOAIS%Local de residencia%Local de Nascimento%Sexo%Idade%Ocupação%Jogador%Nome%nome%player%ocupation%age%gender%bornPlace%livingPlace%/DetailsX%85%50%50%50%50%50%50%50%60%60%60%60%60%60%60%/Icon%null/Stats%Lesão Grave<>false%Inconsciente<>false%Morrendo<>false%Traumatizado<>false%Enlouquecido<>false%1%1%1%1%1%1%1%1%1%Vida%Sanidade%Ocultismo%Dano Extra%Corpo%Exp. Par.%/StatsX%350%450%560%370%500%500%473%448%475%473%500%500%600%400%340%340%340%370%490%580%/Atributes%ATRIBUTOS%Aparência%1%Constituição%1%Destreza%1%Educação%1%Força%1%Inteligência%1%Poder%1%Sorte%1%Movimento%1%Tamanho%1%/AtributesX%130%55%80%145%174%245%268%55%80%162%174%237%268%67%80%162%174%237%268%57%80%/FastSkillsLabel%Perícias%(acesso rápido)%/FastSkillsLabelX%460%458%/FastSkills%/CharacterLabel%/CombatLabel%COMBATE%Nome%Tipo%Dano%Mun. Atual%Mun. Máx.%Ataque%Alcance%Defeito%Area%/CombatLabelX%300%45%150%240%330%400%465%515%570%620%/Guns%0%/RitualsLabel%RITUAIS%/RitualsLabelX%295%/Rituals%0%/Skills%PERÍCIAS%/SkillsX%300%/SkillsRoll%1<>Antropologia<>%1<>Armas de Fogo(P)<>%1<>Armas de Fogo(R|E)<>%1<>Armas de Fogo(A|B)<>%1<>Arqueologia<>%1<>Arremessar<>%1<>Arte|Ofício<>%1<>Avaliação<>%1<>Charme<>%1<>Chaveiro<>%1<>Ciência Forense<>%1<>Eletrônica<>%1<>Mecânica<>%1<>Contabilidade<>%1<>Direito<>%1<>Dirigir<>%1<>Disfarce<>%1<>Encontrar<>%1<>Escutar<>%1<>Escalar<>%1<>Esquivar<>%1<>Lábia<>%1<>Intimidação<>%1<>História<>%1<>Furtividade<>%1<>Lutar(Brigar)<>%1<>Medicina<>%1<>Exp. Paranormal<>%1<>Mundo Natural<>%1<>Natação<>%1<>Navegação<>%1<>Nível de Crédito<>%1<>Ocultismo<>%1<>Operar Máquinario<>%1<>Persuasão<>%1<>Pilotar<>%1<>Prestidigitação<>%1<>Primeiros Socorros<>%1<>Psicanálise<>%1<>Psicologia<>%1<>Saltar<>%1<>Rastrear<>%1<>Sobrevivência<>%1<>Usar Biblioteca<>%1<>Usar Computador<>%1<>Língua Outra<>%1<>Ciência Química<>%1<>Ciência Farmácia<>%1<>Acrobacia<>%1<>Cavalgar<>%1<>Ciência Biologia<>%1<>Demolição<>%1<>Hipnose<>%1<>Leitura Lábial<>%1<>Língua Nativa<>%1<>Mergulho<>%1<>Pilotar (Aeronave)<>%1<>Pilotar (Barcos)<>%1<>Treinar Animais<>%/Inventory%INVENTÁRIO%Peso Total: %0.0%Dinheiro: %0%Patrimonio: %0%//Itens%/Habilidades%HABILIDADES%Sem habilidades%/Antecedentes%ANTECEDENTES%Sem descrição%Descrição Pessoal%Caractéristicas%Sem caracteristicas%Fobias e Manias%Nenhuma%Pessoas Importantes%Ninguém%Pertences Queridos%Nenhum%Nenhum%Locais Importantes%Ferimentos e Cicatrizes%Nenhum%Encontro com o Paranormal%Nenhum%Doenças%Nenhuma%/Anotacoes%ANOTAÇÕES%Sem anotações%/\r\n";
			Menu.saveGame(line);
			life = "1/1";
		}else {
			Menu.current = Game.files.getCurPath()+fileName+".txt";
			String[] parts = Menu.returnLife(Menu.loadGame()).split("/");
			life = "";
			if(parts.length > 1) {
				life+=parts[1]+"/"+parts[0];
			}else {
				life = "?/?";
			}
			if(Menu.returnIcon(Menu.loadGame()) != null) {
				icon = Menu.returnIcon(Menu.loadGame()).getImage();
			}
			
		}
		bar = Game.bars.getSprite(0, 98, 454, 98);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
		del = Game.spr_entities.getSprite(76, 181, 25, 25);
	}

	public Character(double x, double y, String fileName, String save) {
		super(x, y, 180, 250, 0, null);
		this.fileName = fileName;
		File file = new File(Game.files.getCurPath()+fileName+".txt"+"/");
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Menu.current = Game.files.getCurPath()+fileName+".txt";
			String line = save;
			Menu.saveGame(line);
			life = "1/1";
		}else {
			Menu.current = Game.files.getCurPath()+fileName+".txt";
			String[] parts = Menu.returnLife(Menu.loadGame()).split("/");
			life = "";
			if(parts.length > 1) {
				life+=parts[1]+"/"+parts[0];
			}else {
				life = "?/?";
			}
			if(Menu.returnIcon(Menu.loadGame()) != null) {
				icon = Menu.returnIcon(Menu.loadGame()).getImage();
			}
			
		}
		bar = Game.bars.getSprite(0, 98, 454, 98);
		barrier = Game.spr_entities.getSprite(0, 0, 196, 156);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
		del = Game.spr_entities.getSprite(76, 181, 25, 25);
	}
	
	public void tick() {
		if(tick) {
			if(!Game.online) {
				Entity exc = new Entity(getX()+getWidth()/2-25/2, 
						getY()+getHeight()-25-5-Game.files.roller.getY()*Game.files.roller.step, 
						25, 25, 0, null);
				if(this.isColliding(Game.mouseController, exc)) {
					Menu.current = Game.files.getCurPath()+fileName+".txt";
					File f = new File(Menu.current);
					Menu.current = Game.files.getCurPath().replace(fileName+".txt", "");
					System.gc();
					if(f.exists()) {
						f.delete();
					}
					reload();
					return;
				}
			}
			
			
			Entity e = new Entity(Game.mouseController.getX(), 
					Game.mouseController.getY() + Game.files.roller.getY()*Game.files.roller.step
					, 5, 5, 0, null);
			if(this.isColliding(this, e)) {
				Game.mouseController.resetPosition();
				Game.gameState = "FICHA";
				Menu.current = Game.files.getCurPath()+fileName+".txt";
				Menu.loadSave(Menu.loadGame());
			}
		}
	}
	
	public void reload() {
		Game.mouseController.resetPosition();
	
		Addition adt = (Addition) Game.files.getLabels().get(0);
		Game.files.getLabels().clear();
		Game.files.getLabels().add(adt);
		int size = Game.files.getLabels().size();
		Game.files.labelsAmount = size;
		int cont = 1;
		Game.files.labelIAmount = 0;
		Game.files.labelPAmount = 0;
		Game.files.labelMAmount = 0;
		for(int i = 0; i < size; i++) {
			if(cont == 1) {
				cont++;
				Game.files.labelIAmount++;
			}else if(cont == 2) {
				cont++;
				Game.files.labelPAmount++;
			}else if(cont == 3) {
				cont = 1;
				Game.files.labelMAmount++;
			}
		}
		
		File cur = new File(Game.files.getCurPath());
		for(File f : cur.listFiles()) {
			String name = f.getName();
			if(name.indexOf(".txt") == -1) { // Folders
				
				CreationMenu cm = new CreationMenu(0, 0, 0, 0);
				Game.files.cm.add(cm);
				cm.fileName = name;
				
				cm.selection = "Pasta";
				cm.addB.actionPerformed();
				Game.files.cm.remove(cm);
				Label.tick = true;
				
			}else { // Fichas
				
				CreationMenu cm = new CreationMenu(0, 0, 0, 0);
				Game.files.cm.add(cm);
				cm.fileName = name.replace(".txt", "");
				cm.selection = "Ficha";
				cm.addB.actionPerformed();
				Game.files.cm.remove(cm);
				Label.tick = true;
			}
		}
	}
	
	public void render(Graphics g) {
		// Super
		g.setColor(new Color(0xFF151515));
		g.fillRect(getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, getWidth(), getHeight());
		g.setColor(new Color(0xFFFF4246));
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		// ------
		g.drawImage(bar, getX()+15, getY()+185-Game.files.roller.getY()*Game.files.roller.step, 150, 20, null);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(Menu.curFont.deriveFont(25.0f));
		g.drawString(fileName.replace(".txt", ""), getX()+getWidth()/2 - g.getFontMetrics().stringWidth(fileName.replace(".txt", ""))/2, getY()+getHeight()-75 -Game.files.roller.getY()*Game.files.roller.step);
		g.setColor(Color.WHITE);
		
		g.drawString(this.life, getX()+getWidth()/2 - g.getFontMetrics().stringWidth(this.life)/2, getY()+getHeight()-45 -Game.files.roller.getY()*Game.files.roller.step);
		g.drawImage(camera, getX()+70, getY()+60-Game.files.roller.getY()*Game.files.roller.step, null);
		g.drawImage(icon, getX()+15, getY()-Game.files.roller.getY()*Game.files.roller.step, 150, 140, null);
		g.drawImage(barrier, getX(), getY()-Game.files.roller.getY()*Game.files.roller.step, 196-16, 156-12, null);
		if(!Game.online) {
			g.drawImage(del, getX()+getWidth()/2-25/2, getY()+getHeight()-25-5-Game.files.roller.getY()*Game.files.roller.step, 25, 25, null);
		}
	}
	
	public String getName() {
		return this.fileName;
	}
	
}
