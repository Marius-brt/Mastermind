public class MancheHumain {
	private Plateau p;

	public MancheHumain(Plateau p) {
		this.p = p;
	}

	private Code propositionCodeHumain() {
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

	public int Joue() {
		if (Plateau.nbEssaisMax() == 0)
			return 0;
		Ut.afficherSL("Manche humain");
		Code s = new Code(UtMM.codeAleat());
		p.affichePlateau();
		p.addCod(propositionCodeHumain());
		p.addRep(UtMM.nbBienMalPlaces(s, p.getCod()));
		Ut.clearConsole();
		p.affichePlateau();
		while (p.nbCoups() < Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			p.addCoups();
			p.addCod(propositionCodeHumain());
			p.addRep(UtMM.nbBienMalPlaces(s, p.getCod()));
			Ut.clearConsole();
			p.affichePlateau();
			Ut.afficherSL("Résultat : " + p.getRep()[0] + " bien placé(s) et " + p.getRep()[1]
					+ " mal placé(s)");
		}
		Ut.clearConsole();
		p.affichePlateau();
		if (p.nbCoups() == Plateau.nbEssaisMax() - 1 && p.getRep()[0] != Code.lgCode) {
			Ut.afficherSL("Fin manche. Vous n'avez pas trouvé. Le code était "
					+ Couleur.entiersVersMot(s));
			return Plateau.nbEssaisMax() + p.getRep()[1]
					+ (2 * (Code.lgCode - (p.getRep()[0] + p.getRep()[1])));
		}
		Ut.afficherSL("Fin manche. Vous avez trouvé ! " + Couleur.entiersVersMot(s));
		return p.nbCoups() + 1;
	}
}
