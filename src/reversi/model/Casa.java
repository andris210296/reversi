package reversi.model;

public class Casa {
	
	private int xInicial;
	private int yInicial;
	
	private int xFinal;
	private int yFinal;
	
	private int jogador;
	
	private Peca peca;
	
	
	public Casa (int xInicial, int yInicial,int xFinal,int yFinal,int jogador){
		setxInicial(xInicial);
		setyInicial(yInicial);
		
		setxFinal(xFinal);
		setyFinal(yFinal);
		
		setJogador(jogador);
		
		
	}


	public int getxInicial() {
		return xInicial;
	}


	public void setxInicial(int xInicial) {
		this.xInicial = xInicial;
	}


	public int getyInicial() {
		return yInicial;
	}


	public void setyInicial(int yInicial) {
		this.yInicial = yInicial;
	}


	public int getxFinal() {
		return xFinal;
	}


	public void setxFinal(int xFinal) {
		this.xFinal = xFinal;
	}


	public int getyFinal() {
		return yFinal;
	}


	public void setyFinal(int yFinal) {
		this.yFinal = yFinal;
	}


	public int getJogador() {
		return jogador;
	}


	public void setJogador(int jogador) {
		this.jogador = jogador;
	}


	public Peca getPeca() {
		return peca;
	}


	public void setPeca(Peca peca) {
		this.peca = peca;
	}
	
	


}
