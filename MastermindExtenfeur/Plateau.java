public class Plateau {
	private static int nbEssaisMax;
	private Code[] cod;
	private int[][] rep;
	private int nbCoups = 0;

	public Plateau(int nbEssaisMax) {
		Plateau.nbEssaisMax = nbEssaisMax;
		this.rep = new int[nbEssaisMax][];
		this.cod = new Code[nbEssaisMax];
	}

	public static int nbEssaisMax() {
		return nbEssaisMax;
	}

	public void addCod(Code newCod) {
		cod[nbCoups] = newCod;
	}

	public void addRep(int[] newRep) {
		rep[nbCoups] = newRep;
	}

	public void addCoups() {
		nbCoups++;
	}

	public int nbCoups() {
		return nbCoups;
	}

	public Code getCod() {
		return cod[nbCoups];
	}

	public Code getLastCod() {
		return cod[nbCoups - 1];
	}

	public Code getCode(int i) {
		return cod[i];
	}

	public int[] getRep() {
		return rep[nbCoups];
	}

	public int[] getRep(int i) {
		return rep[i];
	}

	public void affichePlateau() {
		Ut.afficherSL("-".repeat(3 + Code.lgCode * 2) + "  BP   MP");
		for (int i = 0; i < Plateau.nbEssaisMax(); i++) {
			Ut.afficher("| ");
			if (cod[i] != null) {
				for (int c : cod[i].cod)
					Ut.afficher(Couleur.charAt(c) + " ");
			} else {
				Ut.afficher(" ".repeat(Code.lgCode * 2));
			}
			Ut.afficher("|");
			if (rep[i] != null)
				Ut.afficher("  " + rep[i][0] + "    " + rep[i][1]);
			Ut.afficher("\n");

		}
		Ut.afficherSL("-".repeat(3 + Code.lgCode * 2));
	}
}
