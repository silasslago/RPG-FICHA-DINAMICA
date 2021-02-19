package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class InventoryLabel extends Label{

	private ArrayList<ItemLabel> itemLabels;
	private Roller roller;
	private int initY, firstYRoller;
	private TextLabel pesoAmount;
	public AddButton addB = new AddButton(0, 0, 0, 0, 0, null, 0, 0, 0, 0) {
		
		private ArrayList<ItemLabel> labels = new ArrayList<ItemLabel>();
		private int labelIAmount = 0, labelPAmount = 0;
		
		public void actionPerformed(){
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof InventoryLabel) {
					labels = ((InventoryLabel) e).getItemArrayList();
					ItemLabel itemLabel =  new ItemLabel(0,0,0,0,0,null,0);
					labelsAmount++;
					if(labelsAmount%2 == 0) {
						labelPAmount++;
						itemLabel = new ItemLabel(labelX+((this.labelW+5)), labelY+((this.labelH+5)*(labelPAmount)), 
								labelW, labelH, 0, null, this.labelY+this.labelH+5);
					}else {
						labelIAmount++;
						itemLabel = new ItemLabel(labelX, labelY+((this.labelH+5)*(labelIAmount)), 
								labelW, labelH, 0, null, this.labelY+this.labelH+5);
					}
					labels.add(itemLabel);
					((InventoryLabel) e).setItemArrayList(labels);
					return;
				}
			}
		}
		
	};
	
	public InventoryLabel(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
		itemLabels = new ArrayList<ItemLabel>();
		TextLabel inventario = new TextLabel(getX()+270, getY()+30, 90, 19, 0, null, new Font("sitka banner", Font.BOLD, 21), 
				new Color(0xFFE8EDEB), "Inventário", 1);
		labels.add(inventario);
		
		TextLabel peso = new TextLabel(getX()+275, getY()+49, 57, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Peso Total: ", 1);
		pesoAmount = new TextLabel(getX()+340, getY()+49, 10, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "0", 0);
		labels.add(peso);
		labels.add(pesoAmount);
		
		TextLabel dinheiro = new TextLabel(getX()+15, getY()+75, 50, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Dinheiro: ", 0);
		TextLabel dinheiroAmount = new TextLabel(getX()+83, getY()+75, 10, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "0", 0);
		labels.add(dinheiro);
		labels.add(dinheiroAmount);
		
		TextLabel patrimonio = new TextLabel(getX()+15, getY()+90, 62, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "Patrimonio: ", 0);
		TextLabel patrimonioAmount = new TextLabel(getX()+100, getY()+90, 10, 11, 0, null, new Font("sitka banner", Font.BOLD, 13), 
				new Color(0xFFE8EDEB), "0", 0);
		labels.add(patrimonio);
		labels.add(patrimonioAmount);
		
		roller = new Roller(getX()+getWidth()-5, getY(), 5, 25, 20, null, true, getX()+getWidth()-5, 
				getY(), 5, getHeight());
		
		firstYRoller = roller.getY();
		
		labels.add(roller);
		
		addB.setX(getX()+getWidth()-35);
		addB.setY(getY()+10);
		addB.setWidth(25);
		addB.setHeight(25);
		addB.setSprite(Game.spr_entities.getSprite(76, 206, 25, 25));
		addB.labelX = getX()+15;
		addB.labelY = getY()+70;
		addB.labelW = getWidth()/2-20;
		addB.labelH = 30;
		addB.speed = 0;
		
		labels.add(addB);
		initY = getY()+70;
	}
	
	public void tick() {
		super.tick();
		if(current) {
			inLocal = this.size;
		}else {
			inLocal = 0;
		}
		
		for(int i = 0; i < itemLabels.size(); i++) {
			ItemLabel l = itemLabels.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+99+inLocal*2) {
				if(i>0) {
					ItemLabel l2 = itemLabels.get(i-1);
					if(l2.inLocal == 0) {
						l.tick();
					}
				}else {
					l.tick();
				}
			}
		}
		
		int pesoAmount = 0;
		for(int i = 0; i < itemLabels.size(); i++) {
			ItemLabel l = itemLabels.get(i);
			pesoAmount += l.getPeso();
		}
		this.pesoAmount.text = Integer.toString(pesoAmount);
		
		int timesP = 0;
		int timesI = 0;
		for(int i = 0; i < itemLabels.size(); i++) {
			ItemLabel l = itemLabels.get(i);
			int times = i+1;
			if(times%2 != 0) {
				timesI++;
				int height = l.getHeight()+5-l.inLocal*2;
				int calc = initY-l.inLocal+height*timesI;
				l.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
			}else {
				timesP++;
				int height = l.getHeight()+5-l.inLocal*2;
				int calc = initY-l.inLocal+height*timesP;
				l.setY((calc) + ((firstYRoller - roller.getY())*roller.step));
			}
			
		}
		
	}
	
	public void render(Graphics g){
		super.render(g);
		g.setColor(new Color(0xFF424242));
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+55, getX()+getWidth()-inLocal-15, getY()+inLocal+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+inLocal+15, getY()+inLocal-Game.roller.getY()*Game.roller.step+100, getX()+getWidth()-inLocal-15, getY()+inLocal+100-Game.roller.getY()*Game.roller.step);
		for(int i = 0; i < itemLabels.size(); i++) {
			ItemLabel l = itemLabels.get(i);
			if(l.getY()+l.getHeight() < getY()+getHeight() && l.getY()+l.inLocal+inLocal>getY()+99+inLocal*2) {
				if(i>1) {
					ItemLabel l2 = itemLabels.get(i-2);
					if(l2.inLocal == 0) {
						l.render(g);
					}
				}else {
					l.render(g);
				}
			}
		}
	}
	
	public ArrayList<ItemLabel> getItemArrayList() {
		return itemLabels;
	}
	
	public void setItemArrayList(ArrayList<ItemLabel> itemLabels) {
		this.itemLabels = itemLabels;
	}

}
