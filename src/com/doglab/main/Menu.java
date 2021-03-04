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
import com.doglab.entities.CharacterLabel;
import com.doglab.entities.CheckBox;
import com.doglab.entities.CombatLabel;
import com.doglab.entities.DetailsLabel;
import com.doglab.entities.Entity;
import com.doglab.entities.FastSkillsLabel;
import com.doglab.entities.GunLabel;
import com.doglab.entities.IconLabel;
import com.doglab.entities.InventoryLabel;
import com.doglab.entities.ItemLabel;
import com.doglab.entities.OptionsLabel;
import com.doglab.entities.ReadmeLabel;
import com.doglab.entities.SkillLabel;
import com.doglab.entities.Skills;
import com.doglab.entities.SquareTextLabel;
import com.doglab.entities.StatsLabel;
import com.doglab.entities.TextLabel;

public class Menu {

	private static String version = "v4.3";
	public static boolean showReadme = true;
	
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
				new Color(0xFFE8EDEB), "Perfil do Jogador", 1, false);
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
		CheckBox cb = new CheckBox(30, 83, 12, 12, 0, Game.spr_entities.getSprite(101, 231, 25, 25),
				Game.spr_entities.getSprite(126, 231, 25, 25), "COC System");
		Game.entities.add(cb);
		OptionsLabel optionsLabel = new OptionsLabel(0, 0-45, Game.WIDTH, 45, 0, Game.spr_entities.getSprite(26, 231, 25, 25));
		Game.entities.add(optionsLabel);
		CharacterLabel characterLabel = new CharacterLabel(10, 2210, Game.WIDTH*Game.SCALE-30, 260, 0, null);
		Game.entities.add(characterLabel);
		File file = new File("info.txt");
		if(file.exists()) {
			String spr = loadGame();
			String[] sprD = spr.split("/");
			if(sprD[0].equals("v4.0")) {
				aplySaveOlder(loadGame());
			}else if(sprD[0].equals("v4.1")) {
				aplySaveOlder1(loadGame());
			}else if(sprD[0].equals("v4.2")) {
				aplySaveOlder2(loadGame());
			}else if(sprD[0].equals("v4.3")) {
				if(sprD[1].equals("false")) {
					showReadme = false;
				}
				aplySaveCurrent(loadGame());
			}
		}
		if(showReadme) {
			ReadmeLabel rL = new ReadmeLabel(30, 30, Game.WIDTH*Game.SCALE-70, Game.HEIGHT*Game.SCALE-40, 0, null);
			Game.entities.add(rL);
		}
	}

	public static void saveGame(String[] val1, int[] val2, int[] val3, String[] val4) {
		BufferedWriter write = null;
		try {
			write = new BufferedWriter(new FileWriter("info.txt"));
		}catch(IOException e) {
			e.printStackTrace();
		}
		try {
			write.write(version);
			write.newLine();
			write.write(Boolean.toString(showReadme));
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
	
	public static void aplySaveOlder(String spr) {
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
								for(int iii = 5; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii-5 == index) {
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
										System.out.println("iii: "+iii);
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
	
	public static void aplySaveOlder1(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
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
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
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
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
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
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
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
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
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
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).setIcon(str2[1]);
								((IconLabel) e).path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
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
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 81) {
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
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
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
	}

	public static void aplySaveOlder2(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
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
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
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
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
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
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
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
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
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
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).setIcon(str2[1]);
								((IconLabel) e).path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
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
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 82) {
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
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 86) {
				str2Times++;
				for(int index = 0; index < 4; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof FastSkillsLabel) {
								for(int iii = 0; iii < ((FastSkillsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((FastSkillsLabel) e).labels.get(iii) instanceof TextLabel) {
											((FastSkillsLabel) e).labels.get(iii).setX(Integer.parseInt(str2[2]));
											((TextLabel)((FastSkillsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4){
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof FastSkillsLabel) {
						ArrayList<SkillLabel> sLL = new ArrayList<SkillLabel>();
						sLL = ((FastSkillsLabel) e).getSkillArrayList();
						SkillLabel sL = new SkillLabel(((FastSkillsLabel) e).addB.labelX, 
								((FastSkillsLabel) e).addB.labelY+10+((((FastSkillsLabel) e).addB.labelH+5)*((FastSkillsLabel) e).addB.labelsAmount),
								((FastSkillsLabel) e).addB.labelW, ((FastSkillsLabel) e).addB.labelH-5, 0, null, Integer.parseInt(str2[1]), ((FastSkillsLabel) e).addB.labelY+10);
						sLL.add(sL);
						((FastSkillsLabel) e).setSkillArrayList(sLL);
					}
				}
			}
		}
	}

	public static void aplySaveCurrent(String spr) {
		String[] str = spr.split("/");
		int str2Times = 0;
		int lenght2Times = 0;
		int labelsCAmount = 0;
		int labelsIAmount = 0;
		int lenght3Times = 0;
		int length1 = 12, length2 = 5, length3 = 4, length4 = 3, length5 = 6;
		for(int i = 0; i < str.length; i++) {
			String[] str2 = str[i].split(";");
			if(str2.length == length1) {
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
			}else if(str2.length == length2 && lenght2Times < 48) {
				lenght2Times++;
				for(int index = 0; index < 48; index++) {
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
										((TextLabel)sTL.labels.get(0)).setX(Integer.parseInt(str2[length2-2]));
										((TextLabel)sTL.labels.get(1)).setX(Integer.parseInt(str2[length2-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length5) { 
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
								ee.setX(Integer.parseInt(str2[length2-1]));
								((TextLabel) ee).text = str2[index+1];
								index++;
							}
						}
						labelsIAmount++;
						((InventoryLabel) e).setItemArrayList(iLL);
					}
				}
			}else if(str2.length == length3 && lenght3Times < 5) {
				lenght3Times++;
				for(int index = 0; index < 5; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof CheckBox) {
											if(str2[1].equals("true")) {
												((CheckBox)((StatsLabel) e).labels.get(iii)).actionPerformed();
											}
											((CheckBox)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length3-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length3) {
				lenght3Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof CheckBox) {
								if(str2[1].equals("true")) {
									((CheckBox) e).actionPerformed();
								}
								((CheckBox) e).setX(Integer.parseInt(str2[length3-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 15) {
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
										((TextLabel)((DetailsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 31) {
				str2Times++;
				for(int index = 5; index < 21; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof StatsLabel) {
								for(int iii = 0; iii < ((StatsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
											((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
											if(iii > 4) {
												if(((StatsLabel) e).labels.get(iii) instanceof TextLabel) {
													((TextLabel)((StatsLabel) e).labels.get(iii)).text = str2[1];
													((TextLabel)((StatsLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 60) {
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
											((TextLabel)((AtributosLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 61) {
				str2Times++;
				for(int index = 0; index < 1; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof IconLabel) {
								((IconLabel) e).setIcon(str2[1]);
								((IconLabel) e).path = str2[1];
								e.setX(Integer.parseInt(str2[length4-1]));
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 73) {
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
											((TextLabel)((CombatLabel) e).labels.get(iii)).setX(Integer.parseInt(str2[length4-1]));
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 82) {
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
											if(((InventoryLabel) e).getItemArrayList().size() > 0) {
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(1)).setX(Integer.parseInt(str2[length4-2]));
												((TextLabel)(((InventoryLabel) e).getItemArrayList().get(iii)).labels.get(2)).setX(Integer.parseInt(str2[length4-1]));
											}
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2Times < 86) {
				str2Times++;
				for(int index = 0; index < 4; index++) {
					String indexX = Integer.toString(index);
					if(indexX.equals(str2[0])) {
						for(int ii = 0; ii < Game.entities.size(); ii++) {
							Entity e = Game.entities.get(ii);
							if(e instanceof FastSkillsLabel) {
								for(int iii = 0; iii < ((FastSkillsLabel)e).labels.size(); iii++) {
									if(iii == index) {
										if(((FastSkillsLabel) e).labels.get(iii) instanceof TextLabel) {
											((FastSkillsLabel) e).labels.get(iii).setX(Integer.parseInt(str2[2]));
											((TextLabel)((FastSkillsLabel) e).labels.get(iii)).text = str2[1];
										}
									}
								}
							}
						}
					}
				}
			}else if(str2.length == length4 && str2[1].length() < 3){
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof FastSkillsLabel) {
						ArrayList<SkillLabel> sLL = new ArrayList<SkillLabel>();
						sLL = ((FastSkillsLabel) e).getSkillArrayList();
						SkillLabel sL = new SkillLabel(((FastSkillsLabel) e).addB.labelX, 
								((FastSkillsLabel) e).addB.labelY+10+((((FastSkillsLabel) e).addB.labelH+5)*((FastSkillsLabel) e).addB.labelsAmount),
								((FastSkillsLabel) e).addB.labelW, ((FastSkillsLabel) e).addB.labelH-5, 0, null, Integer.parseInt(str2[1]), ((FastSkillsLabel) e).addB.labelY+10);
						sLL.add(sL);
						((FastSkillsLabel) e).setSkillArrayList(sLL);
					}
				}
			}else if(str2.length == length4) {
				for(int ii = 0; ii < Game.entities.size(); ii++) {
					Entity e = Game.entities.get(ii);
					if(e instanceof CharacterLabel) {
						((CharacterLabel) e).setIcon(str2[1]);
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
