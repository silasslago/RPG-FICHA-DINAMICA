package com.doglab.api;

import org.json.JSONException;
import org.json.JSONObject;

import com.doglab.main.Game;
import com.doglab.main.Menu;
import com.doglab.menu.Character;

public class Online implements Runnable{

	private Thread thread = new Thread();
	
	public synchronized void start() {
		Game.online = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public synchronized void stop() {
		Game.online = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
						String page = API.readPage(Game.roomCode);
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
						
					} catch (JSONException e) { e.printStackTrace(); }
					
				}else if(Game.actor.equals("Jogador")) {
					API.slot = "Slot "+ API.current;
					try {
						if(Game.gameState == "FICHA") {
							API.updatePage(Game.roomCode, Menu.loadGame());
						}
					} catch (JSONException e) { e.printStackTrace(); }
				}
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
				timer = System.currentTimeMillis();
			}
		}
		stop();
	}

}
