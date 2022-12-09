public class MancheHumain {
	private Plateau p = new Plateau();

	private Code propositionCodeHumain() {
		Partie.lastText = null;
		if (Partie.graphicalMode) {
			while (Partie.lastText == null) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			if (!UtMM.codeCorrect(Partie.lastText)) {
				Fenetre.ShowDialog("Code incorrect !", "Erreur");
				return propositionCodeHumain();
			}
			Code c = new Code(Partie.lastText);
			Partie.lastText = null;
			return c;
		} else {
			int cp = p.nbCoups() + 1;
			Ut.afficherSL(((Integer) cp).toString() + (cp == 1 ? "ère" : "ème") + " proposition");
			Ut.afficher("Veuillez saisir un code : ");
			String c = Ut.saisirChaine();
			while (!UtMM.codeCorrect(c)) {
				Ut.afficherSL("Code incorrecte !");
				Ut.afficher("Veuillez saisir un code : ");
				c = Ut.saisirChaine();
			}
			return new Code(c);
		}
	}

	public int Joue() {
		if (!Partie.graphicalMode)
			Ut.afficherSL("Manche humain");
		Code s = new Code(UtMM.codeAleat());
		p.affichePlateau();
		p.addCod(propositionCodeHumain());
		p.addRep(UtMM.nbBienMalPlaces(s, p.getCod()));
		if (!Partie.graphicalMode)
			Ut.clearConsole();
		p.affichePlateau();
		while (p.nbCoups() < Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			p.addCoups();
			p.addCod(propositionCodeHumain());
			p.addRep(UtMM.nbBienMalPlaces(s, p.getCod()));
			if (!Partie.graphicalMode)
				Ut.clearConsole();
			p.affichePlateau();
			if (!Partie.graphicalMode)
				Ut.afficherSL("Résultat : " + p.getRep()[0] + " bien placé(s) et " + p.getRep()[1]
						+ " mal placé(s)");
		}
		if (!Partie.graphicalMode)
			Ut.clearConsole();
		p.affichePlateau();
		if (p.nbCoups() == Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			if (Partie.graphicalMode)
				Fenetre.ShowDialog("Vous n'avez pas trouvé le code de l'ordinateur ! Le code était "
						+ Couleur.entiersVersMot(s), "Manche terminée");
			else
				Ut.afficherSL("Fin manche. Vous n'avez pas trouvé. Le code était "
						+ Couleur.entiersVersMot(s));
			return Plateau.nbEssaisMax() + p.getRep()[1]
					+ (2 * (Code.lgCode - (p.getRep()[0] + p.getRep()[1])));
		}
		if (Partie.graphicalMode)
			Fenetre.ShowDialog("Bravo vous avez trouvé le code de l'ordinateur en "
					+ (p.nbCoups() + 1) + " coups ! (" + Couleur.entiersVersMot(s) + ")",
					"Manche terminée");
		else
			Ut.afficherSL("Bravo vous avez trouvé le code de l'ordinateur en " + (p.nbCoups() + 1)
					+ " coups ! (" + Couleur.entiersVersMot(s) + ")");
		return p.nbCoups() + 1;
	}
}
