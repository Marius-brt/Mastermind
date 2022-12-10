public class MancheOrdinateur {
	public Plateau p = new Plateau();

	private int saisirPositifGraph(String text) {
		Partie.lastText = null;
		Fenetre.numberText.setText(text);
		while (Partie.lastText == null) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int v = Integer.parseInt(Partie.lastText);
		if (v < 0) {
			Fenetre.ShowDialog("Veuillez entrer un nombre positif", "Erreur");
			return saisirPositifGraph(text);
		}
		Partie.lastText = null;
		return v;
	}

	private int[] reponseHumain() {
		int bp, mp = 0;
		if (Partie.graphicalMode) {
			bp = saisirPositifGraph("Saisir nombre de BP");
			mp = bp == Code.lgCode ? 0 : saisirPositifGraph("Saisir nombre de MP");
		} else {
			Ut.afficherSL("Nombres de bien placé");
			bp = UtMM.saisirEntierPos();
			if (bp != Code.lgCode) {
				Ut.afficherSL("Nombres de mal placé");
				mp = UtMM.saisirEntierPos();
			}
		}
		if (bp + mp > Code.lgCode) {
			Ut.afficherSL("Erreur de saisie veuillez recommencer !");
			return reponseHumain();
		}
		if (Partie.graphicalMode)
			Fenetre.numberText.setText("");
		return new int[] {bp, mp};
	}

	public boolean passeCodeSuivantLexicoCompat(Code cod1) {
		if (!passeCodeSuivantLexico(cod1))
			return false;
		while (!estCompat(cod1, p.nbCoups()))
			if (!passeCodeSuivantLexico(cod1))
				return false;
		return true;
	}

	public static boolean passeCodeSuivantLexico(Code cod1) {
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

	private boolean estCompat(Code cod1, int max) {
		for (int i = 0; i < max; i++) {
			int[] res = UtMM.nbBienMalPlaces(cod1, p.getCod(i));
			if (res[0] != p.getRep(i)[0] || res[1] != p.getRep(i)[1]) {
				return false;
			}
		}
		return true;
	}

	private void afficheErreurs() {
		Code codMot;
		if (Partie.graphicalMode)
			codMot = Fenetre.askCode();
		else {
			Ut.afficherSL("=================\nErreur ou triche !");
			Ut.afficherSL("Saisir votre code : ");
			codMot = UtMM.saisirCode();
		}
		for (int i = 0; i < p.nbCoups(); i++) {
			if (p.getCod(i) != null) {
				if (i > 0 && !estCompat(p.getCod(i), i + 1)) {
					UtMM.showMsg("Code impossible à trouver. Erreur ou triche au coups " + (i + 1)
							+ " !", "Erreur");
					return;
				}
				if (Couleur.entiersVersMot(p.getCod(i)).equals(Couleur.entiersVersMot(codMot))) {
					UtMM.showMsg("Erreur au coups " + (i + 1) + ". Le code était le bon.",
							"Erreur");
					return;
				}
			}
		}
		UtMM.showMsg("Code impossible à trouver. Erreur ou triche au coups 1 !", "Erreur");
	}

	public int Joue() {
		if (!Partie.graphicalMode)
			Ut.afficherSL("Manche ordinateur");
		p.addCod(new Code());
		p.affichePlateau();
		p.addRep(reponseHumain());
		while (p.nbCoups() < Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			p.addCoups();
			p.addCod(new Code(UtMM.copieTab(p.getLastCod().cod)));
			if (!passeCodeSuivantLexicoCompat(p.getCod())) {
				afficheErreurs();
				return 0;
			}
			if (!Partie.graphicalMode)
				Ut.clearConsole();
			p.affichePlateau();
			p.addRep(reponseHumain());
		}
		if (!Partie.graphicalMode)
			Ut.clearConsole();
		p.affichePlateau();
		if (!passeCodeSuivantLexicoCompat(p.getCod())) {
			afficheErreurs();
			return 0;
		}
		if (p.nbCoups() == Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			UtMM.showMsg("L'ordinateur n'a pas trouvé votre code !", "Manche terminée");
			return Plateau.nbEssaisMax() + p.getRep()[1]
					+ (2 * (Code.lgCode - (p.getRep()[0] + p.getRep()[1])));
		}

		UtMM.showMsg("L'ordinateur a trouvé votre code en " + (p.nbCoups() + 1) + " coups !",
				"Manche terminée");
		return p.nbCoups() + 1;
	}
}
