public class Couleur {
	private static char[] tabCouleurs;

	public static void setTabCouleurs(char[] colors) {
		tabCouleurs = colors;
	}

	public static int nbCouleurs() {
		return tabCouleurs.length;
	}

	public static int[] motVersEntiers(String codMot) {
		int[] res = new int[codMot.length()];
		for (int x = 0; x < codMot.length(); x++)
			for (int y = 0; y < tabCouleurs.length; y++)
				if (codMot.charAt(x) == tabCouleurs[y])
					res[x] = y;
		return res;
	}

	public static String entiersVersMot(Code cod) {
		String s = "";
		for (int i = 0; i < Code.lgCode; i++)
			s += tabCouleurs[cod.cod[i]];
		return s;
	}

	public static char charAt(int i) {
		return tabCouleurs[i];
	}

	public static char[] saisirCouleurs() {
		Ut.afficherSL("Renseignez le nombre de couleurs");
		int n = UtMM.saisirEntierPositif();
		char[] cs = new char[n];
		for (int x = 0; x < n; x++) {
			Ut.afficher("Saisir une couleur : ");
			char c = Ut.saisirCaractere();
			if (UtMM.estPresent(cs, c)) {
				Ut.afficherSL("Couleur déjà renseigné !");
				x--;
			} else {
				cs[x] = c;
			}
		}
		return cs;
	}

	public static String listElem(Code cod) {
		String r = "(";
		for (int c : cod.cod)
			r += tabCouleurs[c] + ",";
		if (r.length() > 1)
			r = r.substring(0, r.length() - 1);
		return r + ")";
	}
}
