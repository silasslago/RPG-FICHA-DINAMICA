package com.doglab.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import com.doglab.entities.AddButton;
import com.doglab.entities.CloseButton;
import com.doglab.entities.Entity;
import com.doglab.entities.Label;
import com.doglab.entities.TextLabel;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class CreationMenu extends Label{

	private int labelX, labelY, labelW, labelH;
	private int initY;
	public static boolean remove;
	
	public String selection, fileName;
	private CloseButton close;
	
	protected boolean changeColor;
	
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
		
		private ArrayList<Label> labelsF = new ArrayList<Label>();
		public String file = "", option = "Ficha";
		
		private int number = 20;
		
		public void actionPerformed(){
			labelsF = Game.files.getLabels();
			Game.files.labelsAmount++;
			Label l = null;
			for(Label c : Game.files.cm) {
				if(c instanceof CreationMenu) {
					option = ((CreationMenu) c).selection;
					file = ((CreationMenu) c).fileName;
					break;
				}
			}
			for(int i = 0; i < Game.files.getLabels().size(); i++) {
				Label file = Game.files.getLabels().get(i);
				if(file instanceof Folder) {
					if(((Folder) file).getName().equals(this.file)) {
						CreationMenu.remove = false;
						((TextLabel) CreationMenu.this.labels.get(0)).text = "NOME DUPLICADO!";
						((TextLabel) CreationMenu.this.labels.get(0)).setColor(Color.red);
						changeColor = true;
						return;
					}
				}else if(file instanceof Character) {
					if(((Character) file).getName().equals(this.file)) {
						CreationMenu.remove = false;
						((TextLabel) CreationMenu.this.labels.get(0)).text = "NOME DUPLICADO!";
						((TextLabel) CreationMenu.this.labels.get(0)).setColor(Color.red);
						changeColor = true;
						return;
					}
				}
			}
			// I = 1 P = 0 M = 0 - SEGUNDO
			// I = 1 P = 1 M = 0 - TERCEIRO
			// I = 1 P = 1 M = 1 - PRIMEIRO
			if(option.equals("Pasta")) { // Folder
				if((Game.files.labelIAmount != Game.files.labelPAmount) && 
						(Game.files.labelIAmount != Game.files.labelMAmount)) {
					l = new Folder(labelX+((this.labelW+number))+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelPAmount)), file);
					Game.files.labelPAmount++;
				}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
						(Game.files.labelIAmount != Game.files.labelMAmount)) {
					l = new Folder(labelX+((this.labelW*2+number*2))+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelMAmount)), file);
					Game.files.labelMAmount++;
				}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
						(Game.files.labelIAmount == Game.files.labelMAmount)){
					l = new Folder(labelX+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelIAmount)), file);
					Game.files.labelIAmount++;
				}
			}else if(option.equals("Ficha")) { // Character
				if((Game.files.labelIAmount != Game.files.labelPAmount) && 
						(Game.files.labelIAmount != Game.files.labelMAmount)) {
					l = new Character(labelX+((this.labelW+number))+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelPAmount)), file);
					Game.files.labelPAmount++;
				}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
						(Game.files.labelIAmount != Game.files.labelMAmount)) {
					l = new Character(labelX+((this.labelW*2+number*2))+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelMAmount)), file);
					Game.files.labelMAmount++;
				}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
						(Game.files.labelIAmount == Game.files.labelMAmount)){
					l = new Character(labelX+Menu.margin, 
							labelY+((this.labelH+number)*(Game.files.labelIAmount)), file);
					Game.files.labelIAmount++;
				}
			}
			labelsF.add(l);
			Game.files.setLabels(labelsF);
			CreationMenu.remove = true;
		}
	};
	
	public CreationMenu(double x, double y, int width, int height) {
		super(x, y, width, height, 0, null);
		TextLabel archive = new TextLabel(getX()+30, getY()+75, 50, 20, 0, null,
				new Font("Arial", Font.BOLD, 23), new Color(0xFFE8EDEB), "Novo arquivo", 0, false);
		archive.canClick(true);
		this.labels.add(archive);
		remove = false;
		initY = 130;
		addB.setX(getX()+getWidth()-80);
		addB.setY(getY()+getHeight()-50);
		addB.setWidth(25);
		addB.setHeight(25);
		addB.setSprite(Game.spr_entities.getSprite(76, 206, 25, 25));
		this.labelX = 50;
		this.labelY = initY;
		this.labelW = 180;
		this.labelH = 250;
		addB.labelX = this.labelX;
		addB.labelY = this.labelY;
		addB.labelW = this.labelW;
		addB.labelH = this.labelH;
		labels.add(addB);
		
		// Seletor
		String[] op = new String[2];
		op[0] = "Ficha";
		op[1] = "Pasta";
		Selector slct = new Selector(getX()+40, getY()+getHeight()-50, op);
		labels.add(slct);
		
		int widthB = 25;
		int heightB = 25;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this);
		
		this.changeTickers();
	}
	
	public void tick() {
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			Label.tick = true;
			e.tick();
			Label.tick = false;
		}
		if(changeColor) {
			if(((TextLabel) labels.get(0)).text != "NOME DUPLICADO!") {
				changeColor = false;
				((TextLabel) labels.get(0)).setColor(Color.white);
			}
		}
		this.selection = ((Selector) this.labels.get(2)).getSelection();
		this.fileName = ((TextLabel) this.labels.get(0)).text;
		close.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);

		g.setColor(Color.BLACK);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.drawRect(getX()+1, getY()+1, getWidth()-2, getHeight()-2);
		for(int i = 0; i < labels.size(); i++) {
			Entity e = labels.get(i);
			e.render(g);
		}
		
		g.setColor(Color.WHITE);
		g.setFont(Menu.curFont.deriveFont(14.0f));
		g.drawString("Nome do arquivo: ", getX()+25, getY()+45);
		g.setColor(Color.DARK_GRAY);
		g.drawRect(getX()+25, getY()+50, 300, 30);
		for(Entity l : this.labels) {
			l.render(g);
		}
		close.render(g);
	}

}
