package reversi.model;

import java.awt.Color;

public class Peca {
	
	private int x;
	private int y;
	private int jogador;
	private Color cor;
	
	public Peca(int x,int y,int jogador){
		setX(x);
		setY(y);
		setJogador(jogador);		
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}


	public int getJogador() {
		return jogador;
	}


	public void setJogador(int jogador) {
		this.jogador = jogador;
		
		if (jogador == 1) {
			cor = Color.BLACK;
		} else
			cor = Color.LIGHT_GRAY;
		setCor(cor);
	}


	public Color getCor() {
		return cor;
	}


	public void setCor(Color cor) {
		this.cor = cor;
	}
	

}
