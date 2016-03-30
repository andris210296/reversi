package iniciar;

import reversi.controller.TabuleiroController;
import reversi.model.TabuleiroModel;
import reversi.view.TabuleiroView;

public class Iniciar {

	
	
	public static void main(String[] args) {
		
		int qtdCasas = 8;
		int espacoCasa = 60;
		int distancia = qtdCasas * espacoCasa;
		
		
		TabuleiroModel tbModel = new TabuleiroModel(distancia,qtdCasas, espacoCasa);
		
		TabuleiroController tbCtrl = new TabuleiroController(tbModel);
		
		
		TabuleiroView tbView = new TabuleiroView(tbCtrl);
		
		
		tbModel.addObserver(tbView);
		
	}
	
	
}
