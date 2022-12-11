import java.awt.*;

public class Plateau {
	private static int nbEssaisMax;
	private Code[] cod;
	private int[][] rep;
	private int nbCoups = 0;
	private static Color[] colors =
			new Color[] {new Color(255, 0, 0), new Color(0, 255, 0), new Color(0, 0, 255)};

	public Plateau() {
		this.rep = new int[nbEssaisMax][];
		this.cod = new Code[nbEssaisMax];
	}

	/*
	 * Retourne le nombre d'essais max
	 */
	public static int nbEssaisMax() {
		return nbEssaisMax;
	}

	/*
	 * Modifie le nombre d'essais max
	 */
	public static void setNbEssaisMax(int i) {
		nbEssaisMax = i;
	}

	/*
	 * Ajoute un code
	 */
	public void addCod(Code newCod) {
		cod[nbCoups] = newCod;
	}

	/*
	 * Ajoute une réponse
	 */
	public void addRep(int[] newRep) {
		rep[nbCoups] = newRep;
	}

	/*
	 * Ajoute un coup joué
	 */
	public void addCoups() {
		nbCoups++;
	}

	/*
	 * Retourne le nombre de coups joués
	 */
	public int nbCoups() {
		return nbCoups;
	}

	/*
	 * Retourne le code du dernier coup
	 */
	public Code getCod() {
		return cod[nbCoups];
	}

	/*
	 * Retourne le code du dernier coup - 1
	 */
	public Code getLastCod() {
		return cod[nbCoups - 1];
	}

	/*
	 * Retourne le code au coup i
	 */
	public Code getCod(int i) {
		return cod[i];
	}

	/*
	 * Retourn le nombre de coups joués
	 */
	public int getCodCount() {
		return cod.length;
	}

	/*
	 * Retourne la réponse du dernier coup
	 */
	public int[] getRep() {
		return rep[nbCoups];
	}

	/*
	 * Retourne la réponse du dernier coup - 1
	 */
	public int[] getLastRep() {
		return rep[nbCoups - 1];
	}

	/*
	 * Retourne la réponse au coup i
	 */
	public int[] getRep(int i) {
		return rep[i];
	}

	/*
	 * Affiche le plateau de jeu en mode console ou en mode graphique
	 */
	public void affichePlateau() {
		if (Partie.graphicalMode) {
			GraphText.clear();
			Point.clear();
			new GraphText("BP", 30 * Code.lgCode + 10, 20);
			new GraphText("MP", 30 * Code.lgCode + 40, 20);
			for (int i = 0; i < Plateau.nbEssaisMax(); i++) {
				if (cod[i] != null) {
					for (int c = 0; c < Code.lgCode; c++) {
						new Point(c * 30, 30 + i * 30, colors[cod[i].cod[c]]);
					}
				}
				if (getRep(i) != null) {
					new GraphText(String.valueOf(getRep(i)[0]), 30 * Code.lgCode + 10, 45 + i * 30);
					new GraphText(String.valueOf(getRep(i)[1]), 30 * Code.lgCode + 40, 45 + i * 30);
				}

			}
		} else {
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
}
