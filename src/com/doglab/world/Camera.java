package com.doglab.world;

public class Camera {
	
	public static int x = 0;
	public static int y = 0;
	
	// Método que faz a camera não mostrar a parte preta fora do mapa.
	public static int clamp(int xAtual, int xMin, int xMax) {
		if(xAtual > xMax) {
			xAtual = xMax;
		}else if(xAtual < xMin) {
			xAtual = xMin;
		}
		return xAtual;
	}
	
	public void setX(int x) {
		Camera.x = x;
	}
	
	public void setY(int y) {
		Camera.y = y;
	}
	
	public int getX() {
		return Camera.x;
	}
	
	public int getY() {
		return Camera.y;
	}
	
}
