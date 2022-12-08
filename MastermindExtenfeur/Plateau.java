public class Plateau {
	/*
	 * pré-requis : cod est une matrice, rep est une matrice à 2 colonnes, 0 <= nbCoups <
	 * cod.length, nbCoups < rep.length et les éléments de cod sont des entiers de 0 à
	 * tabCouleurs.length -1 action : affiche les nbCoups premières lignes de cod (sous forme de
	 * mots en utilisant le tableau tabCouleurs) et de rep
	 */
	public static void affichePlateau(int[][] cod, int[][] rep, int nbCoups, char[] tabCouleurs) {
		if (cod[0] == null)
			return;
		Ut.afficherSL("-".repeat(3 + cod[0].length * 2) + "  BP   MP");
		for (int i = 0; i < cod.length; i++) {
			Ut.afficher("| ");
			if (cod[i] != null) {
				for (int c : cod[i])
					Ut.afficher(tabCouleurs[c] + " ");
			} else {
				Ut.afficher(" ".repeat(cod[0].length * 2));
			}
			Ut.afficher("|");
			if (rep[i] != null)
				Ut.afficher("  " + rep[i][0] + "    " + rep[i][1]);
			Ut.afficher("\n");

		}
		Ut.afficherSL("-".repeat(3 + cod[0].length * 2));
	}
}
