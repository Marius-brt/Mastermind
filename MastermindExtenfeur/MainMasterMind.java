import Graphical.Fenetre;

public class MainMasterMind {
	public static void main(String[] args) {
		Ut.clearConsole();
		Couleur.setTabCouleurs(new char[] {'a', 'b', 'c'});
		Code.lgCode = 3;
		int scoreOrdi = 0, scoreHum = 0;
		Fenetre.createFenetre(300, 500, "MasterMind");
		Fenetre.score.setText("Ordinateur " + scoreOrdi + " - Humain " + scoreHum);


		for (int i = 1; i <= 2; i++) {
			Fenetre.setRoundType(i % 2 == 0);
			if (i % 2 == 0) {
				MancheOrdinateur mo = new MancheOrdinateur(new Plateau(5));
				scoreOrdi = mo.Joue();
			} else {
				MancheHumain hm = new MancheHumain(new Plateau(4));
				scoreHum = hm.Joue();

			}
			Fenetre.score.setText("Ordinateur " + scoreOrdi + " - Humain " + scoreHum);
		}



		/*
		 * MancheOrdinateur mo = new MancheOrdinateur(new Plateau(5)); mo.Joue();
		 */
		Fenetre.showEnd(3, 2);
		// Fenetre.getInstance().dispose();
	}
}
