package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.doglab.main.Game;

public class ItemLabel extends Label{
	
	public int plusY = 20, inLocal = 0;
	private DeleteButton deleteB = new DeleteButton(0,0,0,0,0,null,0){
		
		public void labelSorter(ArrayList<ItemLabel> list, int beginning, int iX) {
			for(int i = beginning; i < list.size(); i++) {
				Label l = list.get(i);
				if(i%2==0) {
					if(l.getY() > minY || l.getX() > iX+15) {
						l.setY(l.getY()-l.height-5);
						l.setX(l.getX()-l.getWidth()-5);
					}
				}else {
					if(l.getY() > minY || l.getX() > iX+15) {
						l.setY(l.getY()-l.height-5);
						l.setX(l.getX()+l.getWidth()+5);
					}
				}
			}
		}
		
		public void actionPerformed() {
			Game.mouseController.resetPosition();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof InventoryLabel) {
					for(int ii = 0; ii < ((InventoryLabel) e).getItemArrayList().size(); ii++) {
						ItemLabel l = ((InventoryLabel) e).getItemArrayList().get(ii);
						if(l.deleteB.equals(this)){
							int id = 0;
							id = ((InventoryLabel)e).getItemArrayList().indexOf(l);
							((InventoryLabel)e).getItemArrayList().remove(l);
							((InventoryLabel)e).addB.labelsAmount--;
							labelSorter(((InventoryLabel)e).getItemArrayList(), id, e.getX());
						}
					}
					return;
				}
			}
		}
		
	};

	public ItemLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int minY) {
		super(x, y, width, height, speed, sprite);
		
		deleteB.setX(getX()+10);
		deleteB.setY(getY()+5);
		deleteB.setWidth(20);
		deleteB.setHeight(20);
		deleteB.speed = 0;
		deleteB.setSprite(Game.spr_entities.getSprite(76, 181, 25, 25));
		deleteB.minY = minY;
		
		labels.add(deleteB);
		
		TextLabel itemName = new TextLabel(getX()+45, getY()+20, 28, 20, 0, null, new Font("sitka banner", 
				Font.BOLD, 18), new Color(0xFFE8EDEB), "???", 0, false);
		labels.add(itemName);
		itemName.canClick(true);
		
		TextLabel itemPeso = new TextLabel(getX()+getWidth()-75, getY()+20, 28, 20, 0, null, new Font("sitka banner", 
				Font.BOLD, 18), new Color(0xFFE8EDEB), "???", 2, true);
		labels.add(itemPeso);
		itemPeso.canClick(true);
		
		TextLabel kg = new TextLabel(getX()+getWidth()-35, getY()+20, 20, 22, 0, null, new Font("sitka banner", 
				Font.BOLD, 18), new Color(0xFFE8EDEB), "Kg", 2, false);
		labels.add(kg);
	}
	
	public void tick() {
		super.tick();
		if(tick) {		
			for(int i = 0; i < labels.size(); i++) {
				Entity l = labels.get(i);
				if(l instanceof TextLabel) {
					int plusY = getY()+this.plusY+inLocal;
					int plusX = 0;
					if(i == 1) {
						plusX = 45;
					}else if(i == 2) {
						plusX = getWidth() - inLocal*2 - 75;
					}else if(i == 3) {
						plusX = getWidth()- inLocal*2 - 35;
					}
					if(plusY != l.getY()-inLocal) {
						l.setY(plusY-this.plusY+2);
						((TextLabel) l).setImaginaryY(plusY);
						l.setX(getX()+plusX+inLocal);
					}
				}else if(l instanceof Button) {
					int plusY = getY()+5+inLocal;
					if(plusY != l.getY()-inLocal) {
						l.setY(plusY);
						l.setX(getX()+10+inLocal);
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(new Color(0xFFE8EDEB));
		g.setFont(new Font("sitka banner", Font.BOLD, 26));
	}
	
	public float getPeso() {
		String s = ((TextLabel)labels.get(2)).text;
		String newS = "";
		for(int i = 0; i < s.length(); i++) {
			if(String.valueOf((s).charAt(i)).equals("1") || 
					String.valueOf((s).charAt(i)).equals("2") || 
					String.valueOf((s).charAt(i)).equals("3") ||
					String.valueOf((s).charAt(i)).equals("4") || 
					String.valueOf((s).charAt(i)).equals("5") || 
					String.valueOf((s).charAt(i)).equals("6") ||
					String.valueOf((s).charAt(i)).equals("7") || 
					String.valueOf((s).charAt(i)).equals("8") || 
					String.valueOf((s).charAt(i)).equals("9") ||
					String.valueOf((s).charAt(i)).equals("0") ||
					String.valueOf((s).charAt(i)).equals(".")) {
				newS+=(s).charAt(i);
			}
		}
		if(newS == "") {
			return 0.0f;
		}
		return Float.parseFloat(newS);
	}

}
