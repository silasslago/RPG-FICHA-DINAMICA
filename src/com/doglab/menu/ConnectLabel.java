package com.doglab.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JOptionPane;

import org.json.JSONException;
import org.json.JSONObject;

import com.doglab.api.API;
import com.doglab.entities.CloseButton;
import com.doglab.entities.Entity;
import com.doglab.entities.HistoryLabel;
import com.doglab.entities.Label;
import com.doglab.entities.TextLabel;
import com.doglab.main.Game;
import com.doglab.main.Menu;

public class ConnectLabel extends Label{
	
	private CloseButton close;
	private boolean isRunning = false;
	
	private Thread httpConnect = new Thread() {
		 public void run(){
			 long lastTime = System.nanoTime();
			double amountOfTicks = 60.0; // FPS
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			double timer = System.currentTimeMillis();
			while(isRunning) {
				long now = System.nanoTime();
				delta+= (now - lastTime) / ns;
				lastTime = now;
				if(delta >= 1) { // Executar
					goToRoom();
					delta--;
				}
				if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
					timer = System.currentTimeMillis();
				}
			}
		 }
	};

	private String con = "Conectando..";
	private boolean move = true, createFirst = false;
	private int current = 200, delay = 30, currentDel = 0;
	
	public ConnectLabel(double x, double y, int width, int height) {
		super(x, y, width, height, 0, null);
		int widthB = 25;
		int heightB = 25;
		close = new CloseButton(getX()+getWidth()-(int)(widthB*1.5), getY()+(int)(heightB/2), widthB, heightB, 
				0, Game.spr_entities.getSprite(76, 181, 25, 25), this);
		
		String[] op = new String[2];
		op[0] = "Jogador";
		op[1] = "Mestre";
		Selector slct = new Selector(getX()+getWidth()/2 - 100/2, getY()+getHeight()-100, op);
		labels.add(slct);
		
		TextLabel room = new TextLabel(getX()+30, getY()+228, 50, 20, 0, null,
				new Font("Arial", Font.BOLD, 23), new Color(0xFFE8EDEB), "0123456789", 0, false);
		room.canClick(true);
		
		this.labels.add(room);
		this.changeTickers();
	}
	
	public void tick() {
		
		if(!Game.online) {
			// Se não estiver conectando, os ticks são chamados
			if(!isRunning) {
				for(int i = 0; i < labels.size(); i++) {
					Entity e = labels.get(i);
					Label.tick = true;
					e.tick();
					Label.tick = false;
				}
			}
			// Colisão com o botão de conexão
			Entity connect = new Entity(getX()+115, getY()+290, 120, 35, 0, null);
			if(this.isColliding(Game.mouseController, connect)) {
				Game.mouseController.resetPosition();
				isRunning = true;
				httpConnect = new Thread() {
					 public void run(){
						 long lastTime = System.nanoTime();
						double amountOfTicks = 60.0; // FPS
						double ns = 1000000000 / amountOfTicks;
						double delta = 0;
						double timer = System.currentTimeMillis();
						while(isRunning) {
							long now = System.nanoTime();
							delta+= (now - lastTime) / ns;
							lastTime = now;
							if(delta >= 1) { // Executar
								goToRoom();
								delta--;
							}
							if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
								timer = System.currentTimeMillis();
							}
						}
					 }
				};
				this.httpConnect.start();
			}
			
			// Colisão com o botão de criar sala
			Entity create = new Entity(getX()+115, getY()+100, 120, 35, 0, null);
			if(this.isColliding(Game.mouseController, create)) {
				Game.mouseController.resetPosition();
				createFirst = true;
				isRunning = true;
				httpConnect = new Thread() {
					 public void run(){
						 long lastTime = System.nanoTime();
						double amountOfTicks = 60.0; // FPS
						double ns = 1000000000 / amountOfTicks;
						double delta = 0;
						double timer = System.currentTimeMillis();
						while(isRunning) {
							long now = System.nanoTime();
							delta+= (now - lastTime) / ns;
							lastTime = now;
							if(delta >= 1) { // Executar
								goToRoom();
								delta--;
							}
							if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
								timer = System.currentTimeMillis();
							}
						}
					 }
				};
				this.httpConnect.start();
			}
			
			if(move) { // cima
				current--;
				if(current<180) {
					move=false;
				}
			}else { // baixo
				current++;
				if(current>220) {
					move=true;
				}
			}
			currentDel++;
			if(currentDel>delay) {
				currentDel = 0;
			}
		}else {
			Entity dc = new Entity(getX()+110, getY()+100, 140, 35, 0, null);
			if(this.isColliding(Game.mouseController, dc)) {
				Game.mouseController.resetPosition();
				Game.files.btn.actionPerformed();
				// Abre a pasta
				if(Game.actor.equals("Mestre")) {
					Folder online = null;
					for(Label l : Game.files.getLabels()) {
						if(l instanceof Folder) {
							if(((Folder) l).getName().equals("Online")) {
								online = (Folder) l;
							}
						}
					}
					online.deleteFiles();
				}else if(Game.actor.equals("Jogador")) {
					if(Game.gameState == "FICHA") {
						try {
							API.updatePage(Game.roomCode, "Vazio");
						} catch (JSONException e) { e.printStackTrace(); }
					}
				}
				isRunning = false;
				Game.online = false;
				Game.actor = "null";
				Game.roomCode = "null";
				HistoryLabel.historic = "";
				HistoryLabel.diceTable = "";
			}
		}
		close.tick();
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(0, 0, Game.WIDTH*Game.SCALE, Game.HEIGHT*Game.SCALE);
		g.setColor(new Color(0xFF000000));
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(new Color(0xFF424242));
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		// Se não estiver conectando, tudo é renderizado
		if(!Game.online) {
			if(!isRunning) {
				g.setColor(Color.WHITE);
				g.setFont(Menu.curFont.deriveFont(44.0f));
				g.drawString("SALAS", 
						(getX()+getWidth()/2)-g.getFontMetrics().stringWidth("SALAS")/2,
						getY()+g.getFontMetrics().getHeight()+30);
				g.setFont(Menu.curFont.deriveFont(34.0f));
				g.setFont(Menu.curFont.deriveFont(20.0f));
				g.drawString("Código da sala:", getX()+25, getY()+200);
				g.setColor(Color.GRAY);
				g.fillRect(getX()+115, getY()+290, 120, 35);
				g.fillRect(getX()+115, getY()+100, 120, 35);
				g.setColor(Color.black);
				g.drawString("Conectar", getX()+132, getY()+315);
				g.drawString("Criar Sala", getX()+126, getY()+125);
				g.setColor(Color.DARK_GRAY);
				g.drawLine(getX()+20, getY()+160, getX()+getWidth()-20, getY()+160);
				g.drawRect(getX()+25, getY()+205, 300, 30);
				
				for(Entity l : this.labels) {
					l.render(g);
				}
			}else { // Animação de conexão
				g.setColor(Color.WHITE);
				int size = 20;
				g.fillOval(getX()+(getWidth()/2)-size/2, getY()-65+current, size, size);
				g.setFont(Menu.curFont.deriveFont(20.0f));
				g.drawString(this.con, getX()+(getWidth()/2) - g.getFontMetrics().stringWidth(con)/2 , getY()+200);
				if(con.indexOf("...") != -1) {
					con = "Conectando";
				}else if(currentDel == delay){
					con+=".";
				}
			}
		}else {
			g.setColor(Color.WHITE);
			g.setFont(Menu.curFont.deriveFont(20.0f));
			g.drawString("Conectado!", getX()+(getWidth()/2) - g.getFontMetrics().stringWidth("Conectado!")/2 , getY()+200);
			g.setColor(Color.GRAY);
			g.fillRect(getX()+110, getY()+100, 140, 35);
			g.setColor(Color.black);
			g.drawString("Desconectar", getX()+118, getY()+125);
		}
		close.render(g);
	}

	public void goToRoom() {
		if(createFirst) {
			try {
				((TextLabel) labels.get(1)).text = API.generateNewPage();
				Game.actor = "Mestre";
				Game.roomCode = ((TextLabel) labels.get(1)).text;
			} catch (JSONException e) { e.printStackTrace(); }
			createFirst = false;
		}else {
			Game.roomCode = ((TextLabel) labels.get(1)).text;
			Game.actor = ((Selector) labels.get(0)).getSelection();
		}

		try {
			String page = API.readPage(Game.roomCode);
			if(page == null) {
				JOptionPane.showMessageDialog(null, "Error [001]! Sala não encontrada.");
				Game.roomCode = "null";
				Game.actor = "null";
				Game.online = false;
			}else {
				HistoryLabel.historic = "";
				Game.online = true;
				JSONObject jsonObject = new JSONObject(page.replace("<p>", "").replace("</p>", ""));
				if(Game.actor.equals("Mestre")) {
					Game.files.btn.actionPerformed();
					//Cria a pasta com todos os players
					CreationMenu cm = new CreationMenu(0, 0, 0, 0);
					Game.files.cm.add(cm);
					cm.fileName = "Online";
					cm.selection = "Pasta";
					cm.addB.actionPerformed();
					Game.files.cm.remove(cm);
					
					// Abre a pasta
					for(Label l : Game.files.getLabels()) {
						if(l instanceof Folder) {
							if(((Folder) l).getName().equals("Online")) {
								((Folder) l).open();
							}
						}
					}
					
					for(int i = 1; i < 7; i++) {
						String player = (String) jsonObject.get("Slot "+i);
						if(!player.equals("Vazio")) {
							//Descobre onde o player deve estar
							if((Game.files.labelIAmount != Game.files.labelPAmount) && 
									(Game.files.labelIAmount != Game.files.labelMAmount)) {
								x=250;
								y=130+(270*Game.files.labelPAmount);
								Game.files.labelPAmount++;
							}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
									(Game.files.labelIAmount != Game.files.labelMAmount)) {
								x=450; 
								y=130+(270*Game.files.labelMAmount);
								Game.files.labelMAmount++;
							}else if((Game.files.labelIAmount == Game.files.labelPAmount) && 
									(Game.files.labelIAmount == Game.files.labelMAmount)){
								x = 50; 
								y = 130+(270*Game.files.labelIAmount);
								Game.files.labelIAmount++;
							}
							
							Character current = new Character(x, y, Menu.returnName(player), player);
							Game.files.getLabels().add(current); 
						}
					}
					Game.files.btn.actionPerformed();
				}else if(Game.actor.equals("Jogador")) {
					for(int i = 1; i < 7; i++) {
						String player = (String) jsonObject.get("Slot "+i);
						if(player.equals("Vazio")) {
							API.current = i;
							API.slot = "Slot "+i;
							API.updatePage(Game.roomCode, Menu.loadGame());
							break;
						}
					}
				}
				Game.on.start();
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		isRunning = false;
	}
	
}
