public class Code {
	/**
	 * pré-requis : aucun résultat : un tableau de lgCode entiers choisis aléatoirement entre 0 et
	 * nbCouleurs-1
	 */
	public static int[] codeAleat(int lgCode, int nbCouleurs) {
		int[] c = new int[lgCode];
		for (int i = 0; i < lgCode; i++)
			c[i] = Ut.randomMinMax(0, nbCouleurs - 1);
		return c;
	}
}
