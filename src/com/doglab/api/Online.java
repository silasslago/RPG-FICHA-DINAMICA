package com.doglab.api;

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
					}
				}else if(Game.actor.equals("Jogador")) {
					API.slot = "Slot "+ API.current;
					try { // Descobrindo onde está a mesa!
						String[] both = API.readPageWithTitle(Game.roomCode).split("###");
						HistoryLabel.diceTable = both[1].replace("<p>", "").replace("</p>","");
					} catch (JSONException e1) {
						disconnect();
					}
					try {
						if(Game.gameState == "FICHA") {
							API.updatePage(Game.roomCode, Menu.loadGame());
						}
					} catch (JSONException e) { 
						disconnect();
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

	public static void disconnect() {
		Game.online = false;
		Game.actor = "null";
		Game.roomCode = "null";
		HistoryLabel.historic = "";
		try {
			API.updatePage(Game.roomCode, "Vazio");
		} catch (JSONException eee) { eee.printStackTrace(); }
	}
	
}
