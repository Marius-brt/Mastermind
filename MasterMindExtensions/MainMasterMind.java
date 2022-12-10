public class MainMasterMind {
	public static void main(String[] args) {
		Ut.clearConsole();
		Ut.afficher("Lancer la version graphique ? (Y/N) : ");
		char c = Ut.saisirCaractere();
		Ut.clearConsole();
		Ut.afficherSL("Nombre de manches");
		int manches = UtMM.saisirEntierPairPositif();
		Ut.clearConsole();

		if (Character.toLowerCase(c) == 'y') {
			Couleur.setTabCouleurs(new char[] {'r', 'v', 'b'});
			Ut.afficherSL("Longueur code");
			Code.lgCode = UtMM.saisirEntierMinMax(3, 6);
			Ut.clearConsole();
			Ut.afficherSL("Essais max");
			Plateau.setNbEssaisMax(UtMM.saisirEntierMinMax(3, 10));
			Ut.clearConsole();

			Partie.graphicalMode = true;
			Fenetre.createFenetre(300, 500, "MasterMind");
			Fenetre.score.setText("Ordinateur 0 - Humain 0");
		} else {
			Ut.afficherSL("Essais max");
			Plateau.setNbEssaisMax(UtMM.saisirEntierPositif());
			Ut.clearConsole();
			Ut.afficherSL("Longueur code");
			Code.lgCode = UtMM.saisirEntierPositif();
			Ut.clearConsole();
			Couleur.setTabCouleurs(Couleur.saisirCouleurs());
			Ut.clearConsole();
		}

		Thread t = new Partie(manches);
		t.run();
	}
}
