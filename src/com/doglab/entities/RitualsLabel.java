package com.doglab.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.doglab.main.Game;
import com.doglab.main.Menu;

public class RitualsLabel extends Label{
	
	private Image ritual;
	private BufferedImage camera;
	public String path;
	private boolean hasImage = false;
	
	private DeleteButton deleteB = new DeleteButton(0,0,0,0,0,null,0){
		
		public void labelSorter(ArrayList<RitualsLabel> list, int beginning, int iX) {
			for(int i = beginning; i < list.size(); i++) {
				Label l = list.get(i);
				if(i%2==0) {
					l.setX(iX+10);
				}else {
					l.setX(iX+20 + l.getWidth());
				}
			}
		}
		
		public void actionPerformed() {
			Game.mouseController.resetPosition();
			for(int i = 0; i < Game.entities.size(); i++) {
				Entity e = Game.entities.get(i);
				if(e instanceof Rituals) {
					for(int ii = 0; ii < ((Rituals) e).getRitualsList().size(); ii++) {
						RitualsLabel l = ((Rituals) e).getRitualsList().get(ii);
						if(l.deleteB.equals(this)){
							int id = 0;
							id = ((Rituals)e).getRitualsList().indexOf(l);
							((Rituals)e).getRitualsList().remove(l);
							((Rituals)e).addB.labelsAmount--;
							labelSorter(((Rituals)e).getRitualsList(), id, e.getX());
						}
					}
					return;
				}
			}
		}
	};

	public RitualsLabel(double x, double y, int width, int height, double speed, BufferedImage sprite, int minY) {
		super(x, y, width, height, speed, sprite);
		
		TextLabel name = new TextLabel(getX()+15, getY()+30, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Nome", 0, false);
		TextLabel ritualName = new TextLabel(getX()+20, getY()+50, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Nome do ritual", 0, false);
		TextLabel sanCust = new TextLabel(getX()+170, getY()+30, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Custo San.", 0, false);
		TextLabel sanPower = new TextLabel(getX()+175, getY()+50, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "0d0", 0, false);
		TextLabel oculCust = new TextLabel(getX()+245, getY()+30, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Custo", 0, false);
		TextLabel oculPower = new TextLabel(getX()+250, getY()+50, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "0", 0, false);
		TextLabel efecct = new TextLabel(getX()+15, getY()+80, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Efeito", 0, false);
		TextLabel currentEfe = new TextLabel(getX()+20, getY()+100, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sem efeito", 0, false);
		TextLabel description = new TextLabel(getX()+20, getY()+170, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Descrição", 0, false);
		TextLabel desc = new TextLabel(getX()+20, getY()+200, 32, 13, 0, null, new Font("sitka banner", Font.BOLD, 15), 
				new Color(0xFFE8EDEB), "Sem descrição", 0, false);
		
		desc.textBox(true, 35, 210, 265);
		
		labels.add(efecct);
		labels.add(currentEfe);
		labels.add(name);
		labels.add(ritualName);
		labels.add(sanCust);
		labels.add(sanPower);
		labels.add(oculPower);
		labels.add(oculCust);
		labels.add(description);
		labels.add(desc);
		currentEfe.canClick(true);
		oculPower.canClick(true);
		sanPower.canClick(true);
		ritualName.canClick(true);
		desc.canClick(true);
		
		deleteB.setX(getX()+getWidth()/2-10);
		deleteB.setY(getY()+getHeight()-30);
		deleteB.setWidth(20);
		deleteB.setHeight(20);
		deleteB.speed = 0;
		deleteB.setSprite(Game.spr_entities.getSprite(76, 181, 25, 25));
		deleteB.minY = minY;
		
		labels.add(deleteB);
		camera = Game.spr_entities.getSprite(101, 192, 43, 31);
	}
	
	public void tick() {
		super.tick();
		if(tick) {
			Entity e = new Entity(getX()+180, getY()+70-Game.roller.getY()*Game.roller.step, 110, 110, 0, null);
			setSymbol(this.isColliding(e, Game.mouseController));
			
			int[]plus = {15, 20, 15, 20, 170, 175, 250, 245, 20, 20, getWidth()/2-10};
			int i = 0;
			for(Entity label : labels) {
				label.setX(getX()+plus[i]);
				i++;
			}
			
		}
	}
	
	public void setSymbol(boolean collision) {
		if(collision) {
			Game.mouseController.resetPosition();
			Game.fileChooser.setDialogTitle("");
			Game.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagem", "png", "jpg");
			Game.fileChooser.setFileFilter(filter);
			int fileSelected = Game.fileChooser.showOpenDialog(Game.game);
			if(fileSelected == JFileChooser.APPROVE_OPTION) {
				File file = Game.fileChooser.getSelectedFile();
				this.path = file.getPath();
				setIcon(this.path);
			}
		}
	}
	
	public void setIcon(String path) {
		hasImage = true;
		this.path = path;
		ImageIcon icon = new ImageIcon(path);
		this.ritual = icon.getImage();
		Menu.save();
	}
	
	public void setIcon2(String path) {
		hasImage = true;
		this.path = path;
		ImageIcon icon = new ImageIcon(path);
		this.ritual = icon.getImage();
	}
	
	public void render(Graphics g) {
		super.render(g);
		g.setColor(Color.DARK_GRAY);
		g.drawLine(getX()+15, getY()+55-Game.roller.getY()*Game.roller.step, getX()+160, getY()+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+170, getY()+55-Game.roller.getY()*Game.roller.step, getX()+235, getY()+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+245, getY()+55-Game.roller.getY()*Game.roller.step, getX()+300, getY()+55-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+105-Game.roller.getY()*Game.roller.step, getX()+160, getY()+105-Game.roller.getY()*Game.roller.step);
		g.drawLine(getX()+15, getY()+290-Game.roller.getY()*Game.roller.step, getX()+300, getY()+290-Game.roller.getY()*Game.roller.step);
		g.drawRect(getX()+180, getY()+70-Game.roller.getY()*Game.roller.step, 110, 110);
		if(hasImage) {
			g.drawImage(ritual, getX()+180, getY()+70-Game.roller.getY()*Game.roller.step, 110, 110, null);
		}else {
			g.drawImage(camera, getX()+215, getY()+110-Game.roller.getY()*Game.roller.step, null);
		}
	}

}
