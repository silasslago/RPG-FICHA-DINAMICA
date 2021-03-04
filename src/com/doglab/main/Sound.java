package com.doglab.main;

import java.applet.Applet;
import java.applet.AudioClip;

public class Sound {

	public static boolean sound = true;
	
	private AudioClip clip;
	
	public static final Sound bark = new Sound("/bark.wav");

	// Construtor basicamente ta pegando as musicas.
	private Sound(String name) { 
		
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(name));
		}catch(Throwable e) {}
		
	}
	
	public void play() {
		if(sound) {
			try {
				/* Basicamente oque esse codigo abaixo ta fazendo é criar um método local, que somente poderá ser usado aqui
				 não o adicionando permanentemente na classe. Ao criar uma nova Thread() e dar .start() nela, seria necessario
				 um método run(), e como nós não vamos querer o método run na classe Sound, nós o colocamos na própria classe
				 Thread que a executará apenas uma vez.
				 */
				new Thread() {
					public void run() {
						//clip.play();
					}
				}.start();
				
			}catch(Throwable e) {}
		}	
	}
	
	public void loop() {
		if(sound) {
			try {
				/* Basicamente oque esse codigo abaixo ta fazendo é criar um método local, que somente poderá ser usado aqui
				 não o adicionando permanentemente na classe. Ao criar uma nova Thread() e dar .start() nela, seria necessario
				 um método run(), e como nós não vamos querer o método run na classe Sound, nós o colocamos na própria classe
				 Thread que a executará apenas uma vez, nesse caso, como é um loop, ficará executando eternamente.
				 */
				new Thread() {
					public void run() {
						clip.loop();
					}
				}.start();
				
			}catch(Throwable e) {}
		}
	}
	
	public void stop() {
		if(sound) {
			try {
				/* Basicamente oque esse codigo abaixo ta fazendo é criar um método local, que somente poderá ser usado aqui
				 não o adicionando permanentemente na classe. Ao criar uma nova Thread() e dar .start() nela, seria necessario
				 um método run(), e como nós não vamos querer o método run na classe Sound, nós o colocamos na própria classe
				 Thread que a executará apenas uma vez.
				 */
				new Thread() {
					public void run() {
						clip.stop();
					}
				}.start();
				
			}catch(Throwable e) {}
		}	
	}
}
