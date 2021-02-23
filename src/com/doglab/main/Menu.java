package com.doglab.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import com.doglab.entities.AtributosLabel;
import com.doglab.entities.CombatLabel;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.Entity;
import com.doglab.entities.FastSkillsLabel;
import com.doglab.entities.GunLabel;
import com.doglab.entities.IconLabel;
import com.doglab.entities.InventoryLabel;
import com.doglab.entities.ItemLabel;
import com.doglab.entities.SaveButton;
import com.doglab.entities.Skills;
import com.doglab.entities.SquareTextLabel;
import com.doglab.entities.StatsLabel;
import com.doglab.entities.TextLabel;
import com.doglab.world.World;

public class Menu {

	private static String version = "v4.1";
	
	private final Color BLACK = new Color(0xFF000000);
	private Color bg = BLACK;
	
	public Menu() {
		DetailsLabel detailsLabel = new DetailsLabel(30, 100, Game.WIDTH/2-50, 370, 0, null);
		Game.entities.add(detailsLabel);
		IconLabel iconLabel = new IconLabel(346, 80, 285, 160, 0, null);
		Game.entities.add(iconLabel);
		StatsLabel statsLabel = new StatsLabel(340, 250,(int)(Game.WIDTH/2.18), 440, 0, null);
		Game.entities.add(statsLabel);
		TextLabel title = new TextLabel(240, 60, 200, 29, 0, null, new Font("sitka banner", Font.PLAIN, 31), 
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1);
		Game.entities.add(title);
		AtributosLabel atrLabel = new AtributosLabel(25, 550, 300, 480, 0, null);
		Game.entities.add(atrLabel);
		FastSkillsLabel fastSLabel = new FastSkillsLabel(340, 550, 320, 100, 0, null);
		Game.entities.add(fastSLabel);
		CombatLabel combatLabel = new CombatLabel(10, 1050, Game.WIDTH*Game.SCALE-30, 200, 0, null);
		Game.entities.add(combatLabel);
		Skills skills = new Skills(10, 1270, Game.WIDTH*Game.SCALE-30, 600, 0, null);
		Game.entities.add(skills);
		InventoryLabel inventory = new InventoryLabel(10, 1890, Game.WIDTH*Game.SCALE-30, 300, 0, null);
		Game.entities.add(inventory);
		SaveButton saveButton = new SaveButton(50, 70, 25, 25, 0, Game.spr_entities.getSprite(76, 231, 25, 25));
		Game.entities.add(saveButton);
		File file = new File("info.txt");
		if(file.exists()) {
			aplySave(loadGame());
		}
	}

	public static void saveGame(String[] val1, int[] val2, int[] val3, int[] val4) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("info.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			write.write(version);
			write.newLine();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		for(int i = 0; i < val1.length; i++) {
			String current = Integer.toString(val3[i]);
			current+=";";
			current+=val1[i];
			if(i < val2.length) {
				current+=";";
				current+=val2[i];
			}
			current+=";";
			current+=val4[i];
			try {
				write.write(current);
				if(i < val1.length - 1) {
					write.newLine();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		try {
			write.flush();
			write.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String loadGame() {
		String line = "";
		File file = new File("info.txt");
		if(file.exists()) {
			try {
				String singleLine = null;
				BufferedReader reader = new BufferedReader(new FileReader("info.txt"));
				try {
					while((singleLine = reader.readLine()) != null) {
						line+=singleLine;
						line+="/";
					}	
				}catch(IOException e) {}
			}catch(FileNotFoundException e) {}
		}
		return line;
	}
	
	public static void aplySave(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == 11) {
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CombatLabel) {
						ArrayList<GunLabel> gLL = new ArrayList<GunLabel>();
					
						gLL = ((CombatLabel)e).getGunArrayList();
						
						GunLabel gL = new GunLabel(((CombatLabel) e).labelX, 
								((CombatLabel) e).labelY+(((CombatLabel) e).labelY*labelsCAmount), 
								((CombatLabel) e).labelW, ((CombatLabel) e).labelH, 
								0, null, ((CombatLabel) e).labelY+((CombatLabel) e).labelH+5);
						
						labelsCAmount++;
						for(int iii = 0; iii < gL.labels.size(); iii++) {
							Entity ee = gL.labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						gLL.add(gL);
						((CombatLabel) e).setGunArrayList(gLL);
						((CombatLabel) e).addB.labels = ((CombatLabel)e).getGunArrayList();
					}
				}
			}else if(str2.length == 4) { 
				int index = 0;
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof InventoryLabel) {
						ArrayList<ItemLabel> iLL = new ArrayList<ItemLabel>();
						((InventoryLabel) e).addB.actionPerformed();
						iLL = ((InventoryLabel)e).getItemArrayList();
						for(int iii = 0; iii < iLL.get(labelsIAmount).labels.size(); iii++) {
							Entity ee = iLL.get(labelsIAmount).labels.get(iii);
							if(ee instanceof TextLabel) {
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == 3) {
				for(int index = 0; index < 47; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof Skills) {
								for(int iii = 0; iii < ((Skills)e).squares.size(); iii++) {
									SquareTextLabel sTL = ((Skills) e).squares.get(iii);
									if(iii == index) {
										((TextLabel)sTL.labels.get(0)).text = str2[2];
										((TextLabel)sTL.labels.get(1)).text = str2[1];
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 15) {
				str2Times++;
				for(int index = 0; index < 15; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof DetailsLabel) {
								for(int iii = 0; iii < ((DetailsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										((TextLabel)((DetailsLabel) e).labels.get(iii)).text = str2[1];
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 31) {
				str2Times++;
				for(int index = 0; index < 16; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 60) {
				str2Times++;
				for(int index = 0; index < 29; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof AtributosLabel) {
								for(int iii = 0; iii < ((AtributosLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((AtributosLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((AtributosLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).setIcon(str2[1]);
								((IconLabel) e).path = str2[1];
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 73) {
				str2Times++;
				for(int index = 0; index < 12; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CombatLabel) {
								for(int iii = 0; iii < ((CombatLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((CombatLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((CombatLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == 2 && str2Times < 81) {
				str2Times++;
				for(int index = 0; index < 8; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof InventoryLabel) {
								for(int iii = 0; iii < ((InventoryLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((InventoryLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((InventoryLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(bg);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
	}
	
	public void tick() {
		
	}
	
}
