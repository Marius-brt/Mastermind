public class UtMM {
	public static int[] initTab(int nb, int val) {
		int[] t = new int[nb];
		for (int i = 0; i < nb; i++)
			t[i] = val;
		return t;
	}

	public static int[] copieTab(int[] tab) {
		int[] c = new int[tab.length];
		for (int i = 0; i < tab.length; i++)
			c[i] = tab[i];
		return c;
	}

	public static int[] codeAleat() {
		int[] c = new int[Code.lgCode];
		for (int i = 0; i < Code.lgCode; i++)
			c[i] = Ut.randomMinMax(0, Couleur.nbCouleurs() - 1);
		return c;
	}

	public static int[] nbBienMalPlaces(Code cod1, Code cod2) {
		int bp = nbBienPlaces(cod1, cod2);
		int mp = nbCommuns(cod1, cod2) - bp;
		return new int[] {bp, mp < 0 ? 0 : mp};
	}

	public static int nbBienPlaces(Code cod1, Code cod2) {
		int c = 0;
		for (int x = 0; x < Code.lgCode; x++)
			if (cod1.cod[x] == cod2.cod[x])
				c++;
		return c;
	}

	public static int[] tabFrequence(Code cod) {
		int[] r = new int[Couleur.nbCouleurs()];
		for (int x = 0; x < Code.lgCode; x++)
			r[cod.cod[x]]++;
		return r;
	}

	public static int nbCommuns(Code cod1, Code cod2) {
		int[] f1 = tabFrequence(cod1);
		int[] f2 = tabFrequence(cod2);
		int c = 0;
		for (int x = 0; x < f1.length; x++)
			if (f1[x] >= 0 && f2[x] >= 0) {
				if (f1[x] <= f2[x])
					c += f1[x];
				else
					c += f2[x];
			}
		return c;
	}

	public static boolean codeCorrect(String codMot) {
		if (codMot.length() != Code.lgCode) {
			if (!Partie.graphicalMode)
				Ut.afficherSL("La longueur du code est incorrecte.");
			return false;
		}
		for (int i = 0; i < codMot.length(); i++) {
			boolean exist = false;
			for (int x = 0; x < Couleur.nbCouleurs(); x++)
				if (codMot.charAt(i) == Couleur.charAt(x))
					exist = true;
			if (!exist) {
				if (!Partie.graphicalMode)
					Ut.afficherSL("La couleur n'existe pas.");
				return false;
			}
		}
		return true;
	}

	/*
	 * Saisir entier strictement positif
	 */

	public static int saisirEntierPositif() {
		Ut.afficher("Saisir un entier positif : ");
		String s = Ut.saisirChaine();
		try {
			int v = Integer.parseInt(s);
			if (v > 0)
				return v;
		} catch (Exception e) {

		}
		Ut.afficherSL("Veuillez saisir un entier strictement positif.");
		return saisirEntierPositif();

	}

	/*
	 * Saisir entier positif
	 */

	public static int saisirEntierPos() {
		Ut.afficher("Saisir un entier positif : ");
		String s = Ut.saisirChaine();
		try {
			int v = Integer.parseInt(s);
			if (v >= 0)
				return v;
		} catch (Exception e) {

		}
		Ut.afficherSL("Veuillez saisir un entier positif.");
		return saisirEntierPos();
	}

	/*
	 * Saisir entier positif et pair
	 */

	public static int saisirEntierPairPositif() {
		Ut.afficher("Saisir un entier positif pair : ");
		String s = Ut.saisirChaine();
		try {
			int v = Integer.parseInt(s);
			if (v % 2 == 0 && v > 0)
				return v;
		} catch (Exception e) {
		}
		Ut.afficherSL("Veuillez saisir un entier strictement positif pair.");
		return saisirEntierPairPositif();
	}

	public static Code saisirCode() {
		Ut.afficher("Veuillez saisir un code : ");
		String c = Ut.saisirChaine();
		while (!codeCorrect(c)) {
			Ut.afficherSL("Code incorrecte !");
			Ut.afficher("Veuillez saisir un code : ");
			c = Ut.saisirChaine();
		}
		return new Code(c);
	}

	public static boolean estPresent(char[] t, char c) {
		for (char e : t)
			if (e == c)
				return true;
		return false;
	}

	public static int saisirEntierMinMax(int min, int max) {
		Ut.afficher("Saisir un entier entre " + min + " et " + max + " : ");
		int i = Ut.saisirEntier();
		if (i < min) {
			Ut.afficherSL("Entier trop petit !");
			return saisirEntierMinMax(min, max);
		}
		if (i > max) {
			Ut.afficherSL("Entier trop grand !");
			return saisirEntierMinMax(min, max);
		}
		return i;
	}

	public static void showMsg(String msg, String title) {
		if (Partie.graphicalMode)
			Fenetre.ShowDialog(msg, title);
		else
			Ut.afficherSL(msg);
	}
}
