package com.doglab.api;

import java.io.File;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.doglab.entities.HistoryLabel;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class Online implements Runnable{

	private Thread thread = new Thread();
	
	public synchronized void start() {
		Game.online = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 0.25; // FPS
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		double timer = System.currentTimeMillis();
		while(Game.online) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				if(Game.actor.equals("Mestre")) {
					try {
						// JSON e o Mesa de Dados
						String[] both = API.readPageWithTitle(Game.roomCode).split("###");
						String page = both[0];
						HistoryLabel.historic = API.readPage(both[1]).replace("<p>", "").replace("</p>", "");
						JSONObject jsonObject = new JSONObject(page.replace("<p>", "").replace("</p>", ""));
						deleteUnknowCharacters(jsonObject);
						for(int i = 1; i < 7; i++) {
							String player = (String) jsonObject.get("Slot "+i);
							if(!player.equals("Vazio")) {
								String pName = Menu.returnName(player);
								String current = Menu.current;
								Menu.current = "files/Online/"+pName+".txt";
								if(current.equals(Menu.current)) {
									Menu.loadSave(player);
								}
								Menu.saveGame(player);
								Menu.current = current;
							}
						}
						
					} catch (JSONException e) {
						disconnect();
						e.printStackTrace();
					}
				}else if(Game.actor.equals("Jogador")) {
					API.slot = "Slot "+ API.current;
					try { // Descobrindo onde está a mesa!
						String[] both = API.readPageWithTitle(Game.roomCode).split("###");
						HistoryLabel.diceTable = both[1].replace("<p>", "").replace("</p>","");
					} catch (JSONException e1) {
						disconnect();
						e1.printStackTrace();
					}
					try {
						if(Game.gameState == "FICHA") {
							API.updatePage(Game.roomCode, Menu.loadGame());
						}
					} catch (JSONException e) { 
						disconnect();
						e.printStackTrace();
					}
				}
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
				timer = System.currentTimeMillis();
			}
		}
		disconnect();
	}

	public void deleteUnknowCharacters(JSONObject characters) {
		
		File onlineFolder = new File("files/Online");
		String[] fileNames = onlineFolder.list();
		boolean[] deletes = new boolean[fileNames.length];
		Arrays.fill(deletes, true);
		for(int i = 1; i < 20; i++) {
			String g = "";
			try {
				g = (String) characters.get("Slot "+i);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			
			String charName = Menu.returnName(g);

			filesNames:
			for(int ii = 0; ii < fileNames.length; ii++) {
				String curFile = fileNames[ii].replace(".txt", "");
				if(charName.equals(curFile)) {
					deletes[ii] = false;
					break filesNames;
				}
			}
		}
		
		for(int i = 0; i < deletes.length; i++) {
			if(deletes[i]) {
				File file = new File("files/Online/"+fileNames[i]);
				file.delete();
				Game.files.btn.actionPerformed();
			}
		}
		
	}
	
	public static void disconnect() {
		try {
			API.updatePage(Game.roomCode, "Vazio");
		} catch (JSONException e) { 
			e.printStackTrace(); 
		}
		Game.online = false;
		Game.actor = "null";
		Game.roomCode = "null";
		HistoryLabel.historic = "";
	}
	
}
