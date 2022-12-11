public class Partie extends Thread {
	private int nbManches;
	private int numManche = 1;
	private int scoreJoueur = 0;
	private int scoreOrdi = 0;
	public static boolean graphicalMode;
	public static String lastText = null;

	public Partie(int nbManches) {
		this.nbManches = nbManches;
	}

	/*
	 * Lancement de la partie. Override de la méthode run() de la classe Thread
	 */
	@Override
	public void run() {
		for (numManche = 1; numManche <= nbManches; numManche++) {
			if (graphicalMode)
				Fenetre.setRoundType(numManche % 2 == 0);
			if (numManche % 2 == 0) {
				MancheOrdinateur mo = new MancheOrdinateur();
				scoreOrdi = mo.Joue();
			} else {
				MancheHumain hm = new MancheHumain();
				scoreJoueur = hm.Joue();

			}
			if (graphicalMode)
				Fenetre.score.setText("Ordinateur " + scoreOrdi + " - Humain " + scoreJoueur);
			else
				Ut.afficherSL("-----------------\nScore: Ordinateur " + scoreOrdi + " - Humain "
						+ scoreJoueur + "\n-----------------");
		}
		String result = "Egalité !";
		if (graphicalMode)
			Fenetre.showEnd(scoreOrdi, scoreJoueur);
		else {
			if (scoreOrdi < scoreJoueur)
				result = "Victoire de l'ordinateur !";
			if (scoreJoueur < scoreOrdi)
				result = "Vous avez gagné !";
			Ut.afficherSL(result);
		}

	}
}
