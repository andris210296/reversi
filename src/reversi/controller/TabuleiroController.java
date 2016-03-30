package reversi.controller;

import java.awt.*;
import java.awt.event.*;

import reversi.model.Casa;
import reversi.model.TabuleiroModel;

public class TabuleiroController  implements MouseListener {

	
	
	
	private TabuleiroModel tbModel;
	
	
	private int distancia;
	private int numeroCasas;
	private int espaco;
	
	
	
	public TabuleiroController(TabuleiroModel tbModel){
		this.tbModel = tbModel;
		
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		tbModel.exibirmatriz();

		if (tbModel.validaJogada(e.getX(), e.getY()) == false) {

			System.out.println("Inválido");

		}

		else {

			Point ponto = tbModel.posicaoPecaNoTabuleiro(e.getX(), e.getY());
			int x = (int) ponto.getX();
			int y = (int) ponto.getY();
			tbModel.adicionarPecaLista(x, y);

			tbModel.pecasPorAlterar(x, y);
			

			tbModel.fim();

			tbModel.setJogador(tbModel.alterarJogador()); // Verificar quem deve jogar

		}
		
	}
	
	public Casa[][] getTabuleiroMatriz(){
		return tbModel.getTabuleiroMatriz();
	}
	public Casa getCasa(){
		return tbModel.getCasa();
	}
	public void setCasa(Casa casa){
		tbModel.setCasa(casa);
	}
	
	public int getDistancia() {
		return tbModel.getDistancia();
	}

	public void setDistancia(int distancia) {
		tbModel.setDistancia(distancia);;
	}

	public int getNumeroCasas() {
		return tbModel.getNumeroCasas();
	}

	public void setNumeroCasas(int numeroCasas) {
		tbModel.setNumeroCasas(numeroCasas);
	}

	public int getEspaco() {
		return tbModel.getEspaco();
	}

	public void setEspaco(int espaco) {
		tbModel.setEspaco(espaco);
	}
	
	
	
	

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
