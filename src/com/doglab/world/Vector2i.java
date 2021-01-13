package com.doglab.world;

public class Vector2i {

	// Posição de uma determinada célula no meu mapa do mundo.
	
	public int x, y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Object object) {
		Vector2i vec = (Vector2i) object;
		if(vec.x == this.x && vec.y == this.y) {
			return true;
		}
		return false;
	}
	
}
