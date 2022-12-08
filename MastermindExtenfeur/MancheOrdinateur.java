public class MancheOrdinateur {
	private Plateau p;

	public MancheOrdinateur(Plateau p) {
		this.p = p;
	}

	private int[] reponseHumain() {
		Ut.afficherSL("Nombres de bien placé");
		int bp = UtMM.saisirEntierPos();
		Ut.afficherSL("Nombres de mal placé");
		int mp = UtMM.saisirEntierPos();
		if (bp + mp > Code.lgCode) {
			Ut.afficherSL("Erreur de saisie veuillez recommencer !");
			return reponseHumain();
		}
		return new int[] {bp, mp};
	}

	private boolean passeCodeSuivantLexicoCompat(Code cod1) {
		if (!passeCodeSuivantLexico(cod1))
			return false;
		while (!estCompat(cod1))
			if (!passeCodeSuivantLexico(cod1))
				return false;
		return true;
	}

	private boolean passeCodeSuivantLexico(Code cod1) {
		int i = Code.lgCode - 1;
		while (i >= 0 && cod1.cod[i] == Couleur.nbCouleurs() - 1) {
			i--;
		}
		if (i < 0) {
			for (int j = 0; j < Code.lgCode; j++) {
				cod1.cod[j] = 0;
			}
			return false;
		}
		cod1.cod[i]++;
		for (int j = i + 1; j < Code.lgCode; j++) {
			cod1.cod[j] = 0;
		}
		return true;
	}

	private boolean estCompat(Code cod1) {
		for (int i = 0; i < p.nbCoups(); i++) {
			int[] res = UtMM.nbBienMalPlaces(cod1, p.getCode(i));
			if (res[0] != p.getRep(i)[0] || res[1] != p.getRep(i)[1]) {
				return false;
			}
		}
		return true;
	}

	public int Joue() {
		Ut.afficherSL("Manche ordinateur");
		p.addCod(new Code());
		p.affichePlateau();
		p.addRep(reponseHumain());
		while (p.nbCoups() < Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			p.addCoups();
			p.addCod(new Code(UtMM.copieTab(p.getLastCod().cod)));
			if (!passeCodeSuivantLexicoCompat(p.getCod())) {
				Ut.afficherSL("Erreur ou triche !");
				/*
				 * Ut.afficher("Saisir votre code : "); String mot = Ut.saisirChaine();
				 * afficheErreurs(mot, cod, rep, nbCoups, lgCode, tabCouleurs);
				 */
				return 0;
			}
			Ut.clearConsole();
			p.affichePlateau();
			p.addRep(reponseHumain());
		}
		Ut.clearConsole();
		p.affichePlateau();
		if (p.nbCoups() == Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			Ut.afficherSL("Fin manche ordi. L'ordi n'a pas trouvé.");
			return Plateau.nbEssaisMax() + p.getRep()[1]
					+ (2 * (Code.lgCode - (p.getRep()[0] + p.getRep()[1])));
		}
		Ut.afficherSL("Fin manche ordi. L'ordi a trouvé.");
		return p.nbCoups() + 1;
	}
}
