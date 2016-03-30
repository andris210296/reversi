package reversi.model;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javax.swing.JOptionPane;

import reversi.model.*;

public class TabuleiroModel  extends Observable{
	
	private int distancia;
	private int numeroCasas;
	private int espaco;

	private Casa[][] tabuleiroMatriz;
	private Casa casa;

	private List<Peca> pecas;

	private int jogador;

	public TabuleiroModel(int distancia, int numeroCasas, int espaco) {

		this.distancia = distancia;
		this.numeroCasas = numeroCasas;
		this.espaco = espaco;

		setJogador(1);

		this.pecas = new ArrayList<>();

		tabuleiroMatriz = new Casa[numeroCasas][numeroCasas];		
		
		
		
		
		adicionarCasaNaMatriz(distancia);
		pecasIniciais();

	}
			
	private void adicionarCasaNaMatriz(int distancia) {
		int x = 0, y = 0;
		for (int v = 0; v < numeroCasas; v++) {
			for (int h = 0; h < numeroCasas; h++) {
				x = (distancia / numeroCasas) * h;
				y = (distancia / numeroCasas) * v;
				casa = new Casa(x, y, x + espaco, y + espaco, 0);
				tabuleiroMatriz[v][h] = casa;
			}
		}
		
		

	}

	// Método para adicionar as 4 peças inicias
	private void pecasIniciais() {

		for (int v = 0; v < tabuleiroMatriz.length; v++) {

			for (int h = 0; h < tabuleiroMatriz[0].length; h++) {

				casa = tabuleiroMatriz[v][h];
				if (v == tabuleiroMatriz.length / 2
						&& h == tabuleiroMatriz[0].length / 2) {
					setJogador(2);
					adicionarPecaLista(casa.getxInicial(), casa.getyInicial());
				}
				if ((v + 1) == tabuleiroMatriz.length / 2
						&& (h + 1) == tabuleiroMatriz[0].length / 2) {
					setJogador(2);
					adicionarPecaLista(casa.getxInicial(), casa.getyInicial());
				}

				if (v == tabuleiroMatriz.length / 2
						&& (h + 1) == tabuleiroMatriz[0].length / 2) {
					setJogador(1);
					adicionarPecaLista(casa.getxInicial(), casa.getyInicial());
				}

				if ((v + 1) == tabuleiroMatriz.length / 2
						&& (h) == tabuleiroMatriz[0].length / 2) {
					setJogador(1);
					adicionarPecaLista(casa.getxInicial(), casa.getyInicial());
				}

			}
		}

	}

	public void adicionarPecaLista(int x, int y) {

		Peca nova = new Peca(x, y, getJogador());
		adicionarPecaMatriz(x, y, nova);
		pecas.add(nova);
		
		

	}

	public void adicionarPecaMatriz(int x, int y, Peca peca) {
		for (int v = 0; v < tabuleiroMatriz.length; v++) {
			for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
				casa = tabuleiroMatriz[v][h];
				if (casa.getxInicial() == x && casa.getyInicial() == y) {
					casa.setPeca(peca);
				}
			}
		}
		
	}
	
	// Este método verificará em que casa a peça deverá ser pintada de acordo
		// com o X Y do click do mouse
		public Point posicaoPecaNoTabuleiro(int x, int y) {
			Point ponto = new Point();
			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					if (casa.getxInicial() < x && casa.getxFinal() > x
							&& casa.getyInicial() < y && casa.getyFinal() > y) {
						ponto.setLocation(casa.getxInicial(), casa.getyInicial());
					}
				}
			}
			return ponto;
		}

		public void pecasPorAlterar(int x, int y) {

			List<Peca> pecasIntermediarias = new ArrayList<Peca>();

			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					
					if (casa.getxInicial() == x && casa.getyInicial() == y) {
						
						
						if (v - 1 > 0 && h - 1 > 0) {
							Casa casa00 = tabuleiroMatriz[v - 1][h - 1];
							if (casa00.getPeca() != null) {
								if (casa00.getPeca().getJogador() != getJogador()) {
									int v00 = v;
									int h00 = h;
									while (v00 >= 0 || h00 >= 0) {
										if (v00 >= 0 && h00 >= 0) {
											if (tabuleiroMatriz[v00][h00].getPeca() != null) {
												if (tabuleiroMatriz[v00][h00].getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());
													}

												} else
													pecasIntermediarias.add(tabuleiroMatriz[v00][h00].getPeca());
											}
											v00 -= 1;
											h00 -= 1;
										} else {
											
											break;
										}
									}
								}

							}

						}
						pecasIntermediarias.clear();

						
						if (v - 1 > 0) {
							Casa casa01 = tabuleiroMatriz[v - 1][h];
							if (casa01.getPeca() != null) {
								if (casa01.getPeca().getJogador() != getJogador()) {
									int v01 = v;
									int h01 = h;
									while (v01 >= 0) {
										if (v01 >= 0) {
											if (tabuleiroMatriz[v01][h01].getPeca() != null) {
												if (tabuleiroMatriz[v01][h].getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());
													}
												} else
													pecasIntermediarias.add(tabuleiroMatriz[v01][h01].getPeca());
											}

											v01 -= 1;
										}

										else {
											break;
										}
									}
								}

							}

						}
						pecasIntermediarias.clear();
						
						
						
						

						if (v - 1 > 0 && h + 1 < tabuleiroMatriz.length) {

							Casa casa02 = tabuleiroMatriz[v - 1][h + 1];
							if (casa02.getPeca() != null) {
								if (casa02.getPeca().getJogador() != getJogador()) {
									int v02 = v;
									int h02 = h;
									while (v02 >= 0 || h02 < tabuleiroMatriz.length) {
										if (v02 >= 0
												&& h02 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v02][h02].getPeca() != null) {
												if (tabuleiroMatriz[v02][h02]
														.getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());

													}

												} else
													pecasIntermediarias
															.add(tabuleiroMatriz[v02][h02]
																	.getPeca());
											}
											v02 -= 1;
											h02 += 1;
										} else {
											
											break;
										}
									}
								}

							}
						}
						pecasIntermediarias.clear();

						if (h - 1 > 0) {
							
							Casa casa10= tabuleiroMatriz[v][h - 1];
							if (casa10.getPeca() != null) {
								if (casa10.getPeca().getJogador() != getJogador()) {
									int v10 = v;
									int h10 = h;
									while (h10 >= 0) {
										if (h10 >= 0) {
											if (tabuleiroMatriz[v10][h10].getPeca() != null) {
												if (tabuleiroMatriz[v10][h10].getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());
													}
												} else
													pecasIntermediarias.add(tabuleiroMatriz[v10][h10].getPeca());
											}

											h10 -= 1;
										}

										else {
											break;
										}
									}
								}

							}
						}

						pecasIntermediarias.clear();

						if (h + 1 < tabuleiroMatriz.length) {
							Casa casa12 = tabuleiroMatriz[v][h + 1];
							if (casa12.getPeca() != null) {
								if (casa12.getPeca().getJogador() != getJogador()) {
									int v12 = v;
									int h12 = h;
									while (h12 < tabuleiroMatriz.length) {
										if (h12 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v12][h12].getPeca() != null) {
												if (tabuleiroMatriz[v12][h12]
														.getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());
													}
												} else
													pecasIntermediarias
															.add(tabuleiroMatriz[v12][h12]
																	.getPeca());
											}

											h12 += 1;
										}

										else {
											break;
										}
									}
								}
							}
						}

						

						pecasIntermediarias.clear();

						if (h - 1 > 0 && v + 1 < tabuleiroMatriz.length) {
							Casa casa20 = tabuleiroMatriz[v + 1][h - 1];
							if (casa20.getPeca() != null) {
								if (casa20.getPeca().getJogador() != getJogador()) {
									int v20 = v;
									int h20 = h;
									while (v20 < tabuleiroMatriz.length || h20 >= 0) {
										if (v20 < tabuleiroMatriz.length
												&& h20 >= 0) {
											if (tabuleiroMatriz[v20][h20].getPeca() != null) {
												if (tabuleiroMatriz[v20][h20]
														.getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());

													}

												} else
													pecasIntermediarias
															.add(tabuleiroMatriz[v20][h20]
																	.getPeca());
											}
											v20 += 1;
											h20 -= 1;
										} else {
											
											break;
										}
									}
								}

							}

						}
						pecasIntermediarias.clear();

						if (v + 1 < tabuleiroMatriz.length) {
							Casa casa21 = tabuleiroMatriz[v + 1][h];
							if (casa21.getPeca() != null) {
								if (casa21.getPeca().getJogador() != getJogador()) {
									int v21 = v;
									int h21 = h;
									while (v21 < tabuleiroMatriz.length) {
										if (v21 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v21][h21].getPeca() != null) {
												if (tabuleiroMatriz[v21][h21]
														.getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());
													}
												} else
													pecasIntermediarias
															.add(tabuleiroMatriz[v21][h21]
																	.getPeca());
											}

											v21 += 1;
										}

										else {
											break;
										}
									}
								}
							}
						}

						pecasIntermediarias.clear();

						if (v + 1 < tabuleiroMatriz.length
								&& h + 1 < tabuleiroMatriz.length) {
							Casa casa22 = tabuleiroMatriz[v + 1][h + 1];
							if (casa22.getPeca() != null) {
								if (casa22.getPeca().getJogador() != getJogador()) {
									int v22 = v;
									int h22 = h;
									while (v22 < tabuleiroMatriz.length
											|| h22 < tabuleiroMatriz.length) {
										if (v22 < tabuleiroMatriz.length
												&& h22 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v22][h22].getPeca() != null) {
												if (tabuleiroMatriz[v22][h22]
														.getPeca().getJogador() == getJogador()) {
													for (Peca peca : pecasIntermediarias) {
														peca.setJogador(getJogador());

													}

												} else
													pecasIntermediarias
															.add(tabuleiroMatriz[v22][h22]
																	.getPeca());
											}
											v22 += 1;
											h22 += 1;
										} else {
											pecasIntermediarias.clear();
											break;
										}
									}
								}

							}
						}
						pecasIntermediarias.clear();
					}

				}

			}
			
			
			
			setChanged();
			notifyObservers(tabuleiroMatriz);

		}

		public boolean validaJogada(int x, int y) {
			if (verificarCasaVazia(x, y) == true
					&& movimentacaoPossivel(x, y) == true) {
				return true;

			} else
				return false;

		}

		// Método que verificará se há alguma peça na casa
		private boolean verificarCasaVazia(int x, int y) {
			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					if (casa.getxInicial() < x && casa.getxFinal() > x
							&& casa.getyInicial() < y && casa.getyFinal() > y) {
						if (casa.getPeca() == null) {
							return true;
						}
					}

				}
			}

			JOptionPane.showMessageDialog(null,"Casa ocupada, selecione outra casa");

			return false;

		}

		private boolean movimentacaoPossivel(int x, int y) {

			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					if (casa.getxInicial() < x && casa.getxFinal() > x
							&& casa.getyInicial() < y && casa.getyFinal() > y) {

						if (v - 1 > 0 && h - 1 > 0) {

							Casa casa00 = tabuleiroMatriz[v - 1][h - 1];
							if (casa00.getPeca() != null) {
								if (casa00.getPeca().getJogador() != getJogador()) {
									int v00 = v;
									int h00 = h;
									while (v00 >= 0 || h00 >= 0) {
										if (v00 >= 0 && h00 >= 0) {
											if (tabuleiroMatriz[v00][h00].getPeca() != null) {
												if (tabuleiroMatriz[v00][h00]
														.getPeca().getJogador() == getJogador()) {
													return true;
												}
											}
											v00 -= 1;
											h00 -= 1;
										} else
											break;
									}
								}

							}

						}

						if (v - 1 > 0) {
							Casa casa01 = tabuleiroMatriz[v - 1][h];
							if (casa01.getPeca() != null) {
								if (casa01.getPeca().getJogador() != getJogador()) {

									for (int v01 = 0; v01 < v; v01++) {
										if (tabuleiroMatriz[v01][h].getPeca() != null) {
											if (tabuleiroMatriz[v01][h].getPeca()
													.getJogador() == getJogador()) {
												return true;
											}
										}
									}
								}
							}
						}

						if (v - 1 > 0 && h + 1 < tabuleiroMatriz.length) {

							Casa casa02 = tabuleiroMatriz[v - 1][h + 1];
							if (casa02.getPeca() != null) {
								if (casa02.getPeca().getJogador() != getJogador()) {
									int v02 = v;
									int h02 = h;
									while (v02 >= 0 || h02 < tabuleiroMatriz.length) {
										if (v02 >= 0
												&& h02 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v02][h02].getPeca() != null) {
												if (tabuleiroMatriz[v02][h02]
														.getPeca().getJogador() == getJogador()) {
													return true;
												}
											}
											v02 -= 1;
											h02 += 1;
										} else
											break;
									}
								}

							}

						}

						if (h - 1 > 0) {
							Casa casa10 = tabuleiroMatriz[v][h - 1];
							if (casa10.getPeca() != null) {
								if (casa10.getPeca().getJogador() != getJogador()) {
									for (int h10 = h; h10 >= 0; h10--) {
										if (tabuleiroMatriz[v][h10].getPeca() != null) {
											if (tabuleiroMatriz[v][h10].getPeca()
													.getJogador() == getJogador()) {
												return true;
											}
										}
									}

								}
							}
						}

						if (h + 1 < tabuleiroMatriz.length) {
							Casa casa12 = tabuleiroMatriz[v][h + 1];
							if (casa12.getPeca() != null) {
								if (casa12.getPeca().getJogador() != getJogador()) {

									for (int h12 = h; h12 < tabuleiroMatriz[0].length; h12++) {
										if (tabuleiroMatriz[v][h12].getPeca() != null) {
											if (tabuleiroMatriz[v][h12].getPeca()
													.getJogador() == getJogador()) {
												return true;
											}
										}
									}
								}
							}
						}

						if (h - 1 > 0 && v + 1 < tabuleiroMatriz.length) {

							Casa casa20 = tabuleiroMatriz[v + 1][h - 1];
							if (casa20.getPeca() != null) {
								if (casa20.getPeca().getJogador() != getJogador()) {
									int v20 = v;
									int h20 = h;
									while (v20 < tabuleiroMatriz.length || h20 >= 0) {
										if (v20 < tabuleiroMatriz.length
												&& h20 >= 0) {
											if (tabuleiroMatriz[v20][h20].getPeca() != null) {
												if (tabuleiroMatriz[v20][h20]
														.getPeca().getJogador() == getJogador()) {
													return true;
												}
											}
											v20 += 1;
											h20 -= 1;
										} else
											break;
									}
								}

							}

						}

						if (v + 1 < tabuleiroMatriz.length) {
							Casa casa21 = tabuleiroMatriz[v + 1][h];
							if (casa21.getPeca() != null) {
								if (casa21.getPeca().getJogador() != getJogador()) {
									for (int v21 = v; v21 < tabuleiroMatriz.length; v21++) {
										if (tabuleiroMatriz[v21][h].getPeca() != null) {
											if (tabuleiroMatriz[v21][h].getPeca()
													.getJogador() == getJogador()) {
												return true;
											}
										}
									}
								}
							}
						}

						if (v + 1 < tabuleiroMatriz.length
								&& h + 1 < tabuleiroMatriz.length) {
							Casa casa22 = tabuleiroMatriz[v + 1][h + 1];
							if (casa22.getPeca() != null) {
								if (casa22.getPeca().getJogador() != getJogador()) {
									int v22 = v;
									int h22 = h;
									while (v22 < tabuleiroMatriz.length
											|| h22 < tabuleiroMatriz.length) {
										if (v22 < tabuleiroMatriz.length
												&& h22 < tabuleiroMatriz.length) {
											if (tabuleiroMatriz[v22][h22].getPeca() != null) {
												if (tabuleiroMatriz[v22][h22]
														.getPeca().getJogador() == getJogador()) {
													return true;
												}
											}
											v22 += 1;
											h22 += 1;
										} else
											break;
									}
								}

							}
						}

					}
				}

			}

			JOptionPane.showMessageDialog(null, "Movimento não permitido");
			return false;
		}

		public int alterarJogador() {
			if (getJogador() == 1) {
				setJogador(2);
				return 2;
			} else {
				setJogador(1);
				return 1;
			}
			
			
			
			
			
			
		}

		public void exibirmatriz() {
			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					if (casa.getPeca() != null) {
						System.out.print(casa.getPeca().getJogador());
					} else
						System.out.print("0");
				}
				System.out.println("");
			}
			System.out.println("");

		}

		public void fim() {
			int qtd1 = 0;
			int qtd2 = 0;
			for (int v = 0; v < tabuleiroMatriz.length; v++) {
				for (int h = 0; h < tabuleiroMatriz[0].length; h++) {
					casa = tabuleiroMatriz[v][h];
					if (casa.getPeca() != null) {

						if (casa.getPeca().getJogador() == 1) {
							qtd1++;
						}
						if (casa.getPeca().getJogador() == 2) {
							qtd2++;
						}
					}

				}
			}

			if (qtd1 + qtd2 == numeroCasas * numeroCasas) {
				JOptionPane.showMessageDialog(null, "Jogador 1 = " + qtd1
						+ "\n Jogador 2 = " + qtd2);
			}

		}






		public int getJogador() {
			return jogador;
		}






		public void setJogador(int jogador) {
			this.jogador = jogador;
		}

		public Casa[][] getTabuleiroMatriz() {
			return tabuleiroMatriz;
		}

		public void setTabuleiroMatriz(Casa[][] tabuleiroMatriz) {
			this.tabuleiroMatriz = tabuleiroMatriz;
		}

		public Casa getCasa() {
			return casa;
		}

		public void setCasa(Casa casa) {
			this.casa = casa;
		}

		public int getDistancia() {
			return distancia;
		}

		public void setDistancia(int distancia) {
			this.distancia = distancia;
		}

		public int getNumeroCasas() {
			return numeroCasas;
		}

		public void setNumeroCasas(int numeroCasas) {
			this.numeroCasas = numeroCasas;
		}

		public int getEspaco() {
			return espaco;
		}

		public void setEspaco(int espaco) {
			this.espaco = espaco;
		}

		public List<Peca> getPecas() {
			return pecas;
		}

		public void setPecas(List<Peca> pecas) {
			this.pecas = pecas;
		}

}
