package com.doglab.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doglab.api.Online;
import com.doglab.entities.*;
import com.doglab.graficos.Spritesheet;
import com.doglab.menu.CreationMenu;
import com.doglab.menu.Files;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;
	public static Spritesheet spr_entities, bars;
	private Bootsplash bootsplash;
	public static List<Entity> entities;
	public static int WIDTH = 0;
	public static int HEIGHT = 0;
	private static JFrame frame;
	public static JFileChooser fileChooser;
	private boolean isRunning;
	public static final int SCALE = 1;
	private Thread thread;
	public static BufferedImage image;
	public static String gameState = "BOOTSPLASH";
	public static Online on;
	
	public static Menu menu;
	public static Player player;
	public static mouseController mouseController;
	public static Game game;
	public static Roller roller;
	public static Files files;
	public static boolean tick = true;

	private boolean setF = true;
	private boolean fullscreen = false;

	public static int portugues = 0, english = 1;
	public static int language = portugues;
	public static int maxLanguage = english;

	public static String roomCode = "null", actor = "null";
	public static boolean online = false;
	
	public static Color BG_COLOR = new Color(0xFF000000);
	
	public Game() {
		WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		Menu.margin = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/4;
		File filesFolder = new File("files");
		if(!filesFolder.exists()) {
			filesFolder.mkdir();
		}
		addKeyListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		setPreferredSize(new Dimension(getThisWidth()*getScale(), getThisHeight()*getScale()));
		initFrame();
		image = new BufferedImage(getThisWidth(), getThisHeight(), BufferedImage.TYPE_INT_RGB);
		// Objetos
		entities = new ArrayList<Entity>();
		mouseController = new mouseController(0,0,6,6,0, null);
		entities.add(mouseController);
		spr_entities = new Spritesheet("/sheet_interface.png");
		bars = new Spritesheet("/bars.png");
		bootsplash = new Bootsplash();
		player = new Player(0,0,0,0,0, null);
		menu = new Menu(0, 0);
		int width = 10;
		roller = new Roller(Game.WIDTH-width, 0, width, 195, 7, null, false,
				Game.WIDTH-width, 0, width, Game.HEIGHT);
		entities.add(roller);
		entities.add(player);
		files = new Files();
		on = new Online();
	}
	
	public void initFrame() {
		frame = new JFrame("Dicer");
		frame.add(this);
		fileChooser = new JFileChooser();
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = null;
		try {
			icon = ImageIO.read(getClass().getResource("/icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		frame.setIconImage(icon);
	}
	
	public static void setCursor(Image icon) {
		Toolkit tool = Toolkit.getDefaultToolkit();
		Cursor c = tool.createCustomCursor(icon, new Point(0,0), "img");
		frame.setCursor(c);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		game = new Game();
		game.start();
	}

	public synchronized void start() {
		setRunning(true);
		thread = new Thread(this);
		getThread().start();
	}
	
	public synchronized void stop() {
		setRunning(false);
		try {
			getThread().join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void setOrRemoveFullscreen() {
		frame.setVisible(false);
		if(fullscreen) {
			setPreferredSize(new Dimension(getThisWidth()*getScale(), getThisHeight()*getScale()));
			initFrame();
			frame.dispose();
			frame.setUndecorated(false);
		}else {
			setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize()));
			initFrame();
			frame.dispose();
			frame.setUndecorated(true);
		}
		frame.setVisible(true);
	}
	
	public void tick() {

		if(setF) {
			setF = false;
			this.setOrRemoveFullscreen();
		}

		if(gameState == "NORMAL" || gameState == "TUTORIAL") {

			if(frame.getMousePosition() != null) {
				try{
					mouseController.currentX = getMousePosition().x;
					mouseController.currentY = getMousePosition().y;
				}catch(NullPointerException e ){
				      e.printStackTrace();
				}
			}
			
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i); 
				e.tick();
			}	
		}else if(gameState == "BOOTSPLASH") {
			bootsplash.tick();
		}else if(gameState == "FICHA") {
			if(frame.getMousePosition() != null) {
				try{
					mouseController.currentX = getMousePosition().x;
					mouseController.currentY = getMousePosition().y;
				}catch(NullPointerException e ){}
			}
		
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				if(!Menu.showReadme) {
					e.tick();
				}else {
					if(e instanceof ReadmeLabel) {
						mouseController.tick();
						e.tick();
					}
				}
			}

			menu.tick();	
		}else if(gameState == "MENU") {
			if(frame.getMousePosition() != null) {
				try{
					mouseController.currentX = getMousePosition().x;
					mouseController.currentY = getMousePosition().y;
				}catch(NullPointerException e ){}
			}
			mouseController.tick();
			for(Entity e : entities) {
				if(e instanceof CreationMenu) {
					e.tick();
				}
			}
			
			files.tick();
		}
	}	
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = getBI().getGraphics();
		// Background
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, getThisWidth(), getThisHeight());

		// Renderização do jogo
		if(gameState == "NORMAL" || gameState == "PAUSE" || gameState == "TUTORIAL") {
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}	
		}else if(gameState == "BOOTSPLASH"){
			bootsplash.render(g);
		}else if(gameState == "FICHA") {
			menu.render(g);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
		}else if(gameState == "MENU") {
			files.render(g);
			for(Entity e : entities) {
				if(e instanceof CreationMenu) {
					e.render(g);
				}
			}
		}
		
		if(online && (gameState == "MENU" || gameState == "FICHA")) {
			g.setColor(new Color(0, 255, 0, 180));
			int size = 140;
			g.fillRect(Game.WIDTH-size, 20, size, 70);
			g.setColor(new Color(255, 255, 255));
			g.setFont(Menu.curFont.deriveFont(16.0f));
			g.drawLine(Game.WIDTH-size+10, 25, Game.WIDTH-size+10, 85);
			g.drawString("Sala: ", Game.WIDTH-size+20, 60);
			g.drawString(Game.roomCode, Game.WIDTH-size+20, 80);
			g.drawString(Game.actor.toUpperCase(), Game.WIDTH-size+20, 40);
		}
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(getBI(), 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		bs.show();
	}
	
	@Override
	public void run() { // Game loop
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0; // FPS
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) { // Monstrar o FPS no console
				System.out.println("FPS: " + frames);
				frames = 0;
				timer = System.currentTimeMillis();
			}
		}
		stop();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(mouseController.currentTextLabel != null) {
			if(mouseController.currentTextLabel.throwPhrase) {
				mouseController.currentTextLabel.buildPhrase(e.getKeyChar());
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
	
	// SETTERS
	public void setFrame(JFrame f) {
		frame = f;
	}
	
	public void setRunning(boolean r) {
		this.isRunning = r;
	}
	
	public void setThread(Thread t) {
		this.thread = t;
	}
	
	
	// GETTERS
	public JFrame getFrame() {
		return frame;
	}
	
	public boolean isRunning() {
		return isRunning;
	}
	
	public int getThisWidth() {
		return Game.WIDTH;
	}
	
	public int getThisHeight() {
		return Game.HEIGHT;
	}
	
	public int getScale() {
		return SCALE;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public BufferedImage getBI() {
		return image;
	}
	
	@Override
	public void mouseClicked(MouseEvent m) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent m) {
		mouseController.xTarget = m.getX();
		mouseController.yTarget = m.getY();
		mouseController.isPressed = true;
	}

	@Override
	public void mouseReleased(MouseEvent m) {
		mouseController.isPressed = false;
		mouseController.currentRoller = null;
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		if(Label.tick && gameState == "FICHA" && (!Menu.showReadme)) {
			roller.setY(roller.getY()+e.getWheelRotation()*6);
			Game.mouseController.resetPosition();
		}else if(Label.tick && gameState == "MENU") {
			Game.files.roller.setY(Game.files.roller.getY()+e.getWheelRotation()*6);
			Game.mouseController.resetPosition();
		}
	}

}
