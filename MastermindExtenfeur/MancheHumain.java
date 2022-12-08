public class MancheHumain {
	/**
	 * pré-requis : numMache >= 1 action : effectue la (numManche)ème manche où l'ordinateur est le
	 * codeur et l'humain le décodeur (le paramètre numManche ne sert que pour l'affichage) résultat
	 * : - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain
	 * (cf. sujet), s'il n'a toujours pas trouvé au bout du nombre maximum d'essais - sinon le
	 * nombre de codes proposés par le joueur humain
	 */
	public int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
		if (nbEssaisMax == 0)
			return 0;
		Ut.afficherSL("Manche humain");
		int[] s = Code.codeAleat(lgCode, tabCouleurs.length);
		int nbCoups = 0;
		int[][] cod = new int[nbEssaisMax][];
		int[][] rep = new int[nbEssaisMax][];
		cod[0] = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
		rep[0] = nbBienMalPlaces(s, cod[0], tabCouleurs.length);
		Plateau.affichePlateau(cod, rep, nbCoups, tabCouleurs);
		while (nbCoups < nbEssaisMax - 1 && rep[nbCoups][0] != lgCode) {
			nbCoups++;
			cod[nbCoups] = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
			rep[nbCoups] = nbBienMalPlaces(s, cod[nbCoups], tabCouleurs.length);
			Ut.clearConsole();
			Plateau.affichePlateau(cod, rep, nbCoups, tabCouleurs);
			Ut.afficherSL("Résultat : " + rep[nbCoups][0] + " bien placé(s) et " + rep[nbCoups][1]
					+ " mal placé(s)");
		}
		Ut.clearConsole();
		Plateau.affichePlateau(cod, rep, nbCoups, tabCouleurs);
		if (nbCoups == nbEssaisMax - 1 && rep[nbCoups][0] != lgCode) {
			Ut.afficherSL("Fin manche. Vous n'avez pas trouvé. Le code était "
					+ entiersVersMot(s, tabCouleurs));
			return nbEssaisMax + rep[nbCoups][1]
					+ (2 * (lgCode - (rep[nbCoups][0] + rep[nbCoups][1])));
		}
		Ut.afficherSL("Fin manche. Vous avez trouvé ! " + entiersVersMot(s, tabCouleurs));
		return nbCoups + 1;
	}
}
