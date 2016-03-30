package reversi.view;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import reversi.controller.TabuleiroController;
import reversi.model.Casa;
import reversi.model.TabuleiroModel;

public class TabuleiroView extends JComponent implements Observer{
	
	
	private TabuleiroController tbCtrl;
	
	
	
	private JFrame janela;
	
	
	private Casa[][] tabuleiroMatriz;
	
	private Graphics2D g2d;
	
	
	public TabuleiroView(TabuleiroController tbCtrl) {
			
		
		this.tbCtrl = tbCtrl;
		
		this.janela = new JFrame("Reversi");			
		this.janela.getContentPane().add(this);
		this.janela.setResizable(false);
		this.janela.pack();
		this.janela.setVisible(true);
		this.janela.setDefaultCloseOperation(3);
		addMouseListener(tbCtrl);
		this.setFocusable(true);	
		
	}
		
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(new Color(245, 245, 255));
		g2d.fillRect(0, 0, tbCtrl.getDistancia(), tbCtrl.getDistancia());
		g2d.setColor(new Color(200, 220, 220));

		desenharTabuleiro(g2d);

		desenharPeca(g2d);

	}
	
	public void desenharTabuleiro(Graphics2D g2d) {
		for (int v = 0; v < tbCtrl.getTabuleiroMatriz().length; v++) {
			for (int h = 0; h < tbCtrl.getTabuleiroMatriz()[0].length; h++) {
				tbCtrl.setCasa(tbCtrl.getTabuleiroMatriz()[v][h]);
				g2d.drawRect(tbCtrl.getCasa().getxInicial(), tbCtrl.getCasa().getyInicial(), tbCtrl.getEspaco(),tbCtrl.getEspaco());
			}
		}
	}

	public void desenharPeca(Graphics2D g2d) {
		for (int v = 0; v < tbCtrl.getTabuleiroMatriz().length; v++) {
			for (int h = 0; h < tbCtrl.getTabuleiroMatriz()[0].length; h++) {
				
				
				
				
				tbCtrl.setCasa(tbCtrl.getTabuleiroMatriz()[v][h]);
				if (tbCtrl.getTabuleiroMatriz()[v][h].getPeca() != null) {
					
					
					g2d.setColor(tbCtrl.getTabuleiroMatriz()[v][h].getPeca().getCor());
					g2d.fillOval(tbCtrl.getTabuleiroMatriz()[v][h].getPeca().getX(), tbCtrl.getTabuleiroMatriz()[v][h].getPeca().getY(),
							tbCtrl.getEspaco(),tbCtrl.getEspaco());
				}
			}
		}

	}
	
	
	
	
	
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}

	public Dimension getMinimumSize() {
		return getPreferredSize();
	}

	public Dimension getPreferredSize() {
		return new Dimension(tbCtrl.getDistancia(), tbCtrl.getDistancia());
	}


	@Override
	public void update(Observable o, Object arg) {
		TabuleiroModel tbModel = (TabuleiroModel)o;
		
		tabuleiroMatriz = tbModel.getTabuleiroMatriz();
		
		
		
		
		this.revalidate();
		this.repaint();
	}
	
	

}
