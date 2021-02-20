package com.doglab.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.doglab.entities.*;
import com.doglab.graficos.Spritesheet;
import com.doglab.graficos.UI;
import com.doglab.world.Camera;
import com.doglab.world.World;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener, MouseListener, MouseWheelListener {

	private static final long serialVersionUID = 1L;
	public static Spritesheet spr_entities, spr_tiles;
	public static UI ui;
	private Bootsplash bootsplash;
	private Pause pause;
	private Credits end;
	public static List<Entity> entities;
	public static int WIDTH = 680;
	public static int HEIGHT = 698;
	private JFrame frame;
	public static JFileChooser fileChooser;
	private boolean isRunning;
	public static final int SCALE = 1;
	private Thread thread;
	public static BufferedImage image;
	public static String gameState = "MENU";
	public static World world;
	public static Menu menu;
	public static Player player;
	public static mouseController mouseController;
	public static Game game;
	public static Roller roller;

	private boolean setF = false;
	private boolean fullscreen = true;

	public static int portugues = 0, english = 1;
	public static int language = portugues;
	public static int maxLanguage = english;

	public Game() {
		addKeyListener(this);
		addMouseListener(this);
		addMouseWheelListener(this);
		setPreferredSize(new Dimension(getThisWidth()*getScale(), getThisHeight()*getScale()));
		initFrame();
		image = new BufferedImage(getThisWidth(), getThisHeight(), BufferedImage.TYPE_INT_RGB);
		// Objetos
		spr_entities = new Spritesheet("/sheet_interface.png");
		spr_tiles = new Spritesheet("/texture.jpg");
		entities = new ArrayList<Entity>();
		world = new World();
		ui = new UI();
		pause = new Pause();
		bootsplash = new Bootsplash();
		end = new Credits();
		player = new Player(0,0,0,0,0, null);
		menu = new Menu();
		mouseController = new mouseController(0,0,6,6,0, null);
		int width = 10;
		roller = new Roller(Game.WIDTH-width, 0, width, 70, 10, null, false, Game.WIDTH-width, 0, width, Game.HEIGHT);
		entities.add(roller);
		entities.add(player);
		entities.add(mouseController);
	}
	
	public void initFrame() {
		frame = new JFrame("Role Play Game");
		frame.add(this);
		fileChooser = new JFileChooser();
		//frame.setUndecorated(true);
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
		
		//Toolkit tool = Toolkit.getDefaultToolkit();
		//Cursor c = tool.createCustomCursor(icon, new Point(0,0), "img");
		
		//frame.setCursor(c);
		
		frame.setVisible(true);
		
	}
	
	public static void main(String[] args) {
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
		}else if(gameState == "PAUSE") {
			pause.tick();
		}else if(gameState == "GAME_OVER") {
			
		}else if(gameState == "CREDITS") {
			end.tick();
		}else if(gameState == "MENU") {
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
			menu.tick();	
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
		g.setColor(new Color(0xFF000000));
		g.fillRect(0, 0, getThisWidth(), getThisHeight());
		

		// Renderização do jogo
		if(gameState == "NORMAL" || gameState == "PAUSE" || gameState == "TUTORIAL") {
			world.render(g);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}	
			ui.render(g);
			if(gameState == "PAUSE") {
				pause.render(g);
			}
		}else if(gameState == "BOOTSPLASH"){
			bootsplash.render(g);
		}else if(gameState == "CREDITS") {
			end.render(g);
		}else if(gameState == "MENU") {
			menu.render(g);
			for(int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}
		}
	
		g.dispose();
		g = bs.getDrawGraphics();
		if(fullscreen) {
			g.drawImage(getBI(), 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
		}else {
			g.drawImage(getBI(), 0, 0, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
					(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight(), null);
		}
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
			requestFocus();
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
		if(mouseController.currentTextLabel.throwPhrase) {
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {
				mouseController.currentTextLabel.resetPhrase();
				return;
			}
			mouseController.currentTextLabel.buildPhrase(e.getKeyChar());	
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
		this.frame = f;
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
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		roller.setY(roller.getY()+e.getWheelRotation()*6);
		Game.mouseController.resetPosition();
	}

}
