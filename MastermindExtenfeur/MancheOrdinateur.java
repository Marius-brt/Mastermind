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
		while (!estCompat(cod1))
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

	private boolean estCompat(Code cod1) {
		for (int i = 0; i < p.nbCoups(); i++) {
			int[] res = UtMM.nbBienMalPlaces(cod1, p.getCod(i));
			if (res[0] != p.getRep(i)[0] || res[1] != p.getRep(i)[1]) {
				return false;
			}
		}
		return true;
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
				if (Partie.graphicalMode)
					Fenetre.ShowDialog("Erreur ou triche sur le nombre de BP et MP !", "Erreur");
				else
					Ut.afficherSL("Erreur ou triche !");
				/*
				 * Ut.afficher("Saisir votre code : "); String mot = Ut.saisirChaine();
				 * afficheErreurs(mot, cod, rep, nbCoups, lgCode, tabCouleurs);
				 */
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
		if (p.nbCoups() == Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			if (Partie.graphicalMode)
				Fenetre.ShowDialog("L'ordinateur n'a pas trouvé votre code !", "Manche terminée");
			else
				Ut.afficherSL("Fin manche ordi. L'ordinateur n'a pas trouvé votre code !");
			return Plateau.nbEssaisMax() + p.getRep()[1]
					+ (2 * (Code.lgCode - (p.getRep()[0] + p.getRep()[1])));
		}
		if (Partie.graphicalMode)
			Fenetre.ShowDialog(
					"L'ordinateur a trouvé votre code en " + (p.nbCoups() + 1) + " coups !",
					"Manche terminée");
		else
			Ut.afficherSL("Fin manche ordi. L'ordinateur a trouvé votre code en "
					+ (p.nbCoups() + 1) + " coups !");
		return p.nbCoups() + 1;
	}
}
