public class MainMasterMind {
	public static void main(String[] args) {
		Ut.clearConsole();
		Ut.afficher("Lancer la version graphique ? (Y/N) : ");
		char c = Ut.saisirCaractere();
		Ut.afficherSL("Nombre de manches");
		int manches = UtMM.saisirEntierPairPositif();

		if (Character.toLowerCase(c) == 'y') {
			Couleur.setTabCouleurs(new char[] {'r', 'v', 'b'});
			Code.lgCode = 3;
			Plateau.setNbEssaisMax(4);

			Partie.graphicalMode = true;
			Fenetre.createFenetre(300, 500, "MasterMind");
			Fenetre.score.setText("Ordinateur 0 - Humain 0");
		} else {
			Ut.afficherSL("Essais max");
			Plateau.setNbEssaisMax(UtMM.saisirEntierPositif());
			Ut.afficherSL("Longueur code");
			Code.lgCode = UtMM.saisirEntierPositif();
			Couleur.setTabCouleurs(Couleur.saisirCouleurs());
		}

		Thread t = new Partie(manches);
		t.run();
	}
}
