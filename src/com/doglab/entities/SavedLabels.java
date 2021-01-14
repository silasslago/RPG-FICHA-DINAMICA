package com.doglab.entities;

import java.awt.image.BufferedImage;

public class SavedLabels extends Entity{

	public String perfilJogador = "Perfil do Jogador", detalhesPessoais = "DETALHES PESSOAIS", vida = "Vida",
			sanidade = "Sanidade", ocultismo = "Ocultismo", danoExtra = "Dano Extra", corpo = "Corpo",
			expPara = "Exp. Par.", nome = "Nome", jogador = "Jogador", ocupacao = "Ocupação", idade = "Idade",
			sexo = "Sexo", lclNasc = "Local de Nascimento", lclRes = "Local de Residência";
	
	public SavedLabels(double x, double y, int width, int height, double speed, BufferedImage sprite) {
		super(x, y, width, height, speed, sprite);
	}

}
