import java.util.function.Function;

public class Statistics {
	/*
	 * Algorithme de base
	 */
	private static int BasicSolver(Code s) {
		MancheOrdinateur mo = new MancheOrdinateur();
		mo.p.addCod(new Code());
		mo.p.addRep(UtMM.nbBienMalPlaces(s, mo.p.getCod()));
		while (mo.p.getRep()[0] != Code.lgCode) {
			mo.p.addCoups();
			mo.p.addCod(new Code(UtMM.copieTab(mo.p.getLastCod().cod)));
			mo.passeCodeSuivantLexicoCompat(mo.p.getCod());
			mo.p.addRep(UtMM.nbBienMalPlaces(s, mo.p.getCod()));
		}
		return mo.p.nbCoups() + 1;
	}

	/*
	 * Met une couleur en position i
	 */
	private static Code putColor(int bg, int cursor, int i) {
		int[] c = UtMM.initTab(Code.lgCode, bg);
		c[i] = cursor;
		return new Code(c);
	}

	/*
	 * Algorithme CFC
	 */
	private static int CFCsolver(Code s) {
		MancheOrdinateur mo = new MancheOrdinateur();
		int[] colorsCount = new int[Couleur.nbCouleurs()];
		mo.p.addCod(new Code());
		mo.p.addRep(UtMM.nbBienMalPlaces(s, mo.p.getCod()));
		while (mo.p.getRep()[0] + mo.p.getRep()[1] != Code.lgCode) {
			int sum = (int) Math.round((1 - Math.pow(Couleur.nbCouleurs(),
					Code.lgCode - (mo.p.getRep()[0] + mo.p.getRep()[1])))
					/ (1 - Couleur.nbCouleurs()));
			mo.p.addCoups();
			mo.p.addCod(new Code(UtMM.copieTab(mo.p.getLastCod().cod)));
			for (int i = 0; i < sum; i++)
				MancheOrdinateur.passeCodeSuivantLexico(mo.p.getCod());
			mo.p.addRep(UtMM.nbBienMalPlaces(s, mo.p.getCod()));
		}
		if (mo.p.getRep()[0] == Code.lgCode)
			return mo.p.nbCoups() + 1;
		for (int c : mo.p.getCod().cod)
			colorsCount[c]++;
		int iCurs = 0, iFond = 0, cursorPos = 0, max = 0;
		int[] founds = UtMM.initTab(Code.lgCode, -1);
		for (int i = 0; i < colorsCount.length; i++)
			if (colorsCount[i] > max) {
				max = colorsCount[i];
				iFond = i;
			}
		mo.p.addCoups();
		mo.p.addCod(new Code(String.valueOf(Couleur.charAt(iFond)).repeat(Code.lgCode)));
		int[] fondRep = UtMM.nbBienMalPlaces(s, mo.p.getCod());
		mo.p.addRep(fondRep);
		while (mo.p.getRep()[0] != Code.lgCode && cursorPos < Code.lgCode
				&& iCurs < Couleur.nbCouleurs()) {
			if (iCurs == iFond || colorsCount[iCurs] == 0) {
				iCurs++;
			} else {
				if (founds[cursorPos] != -1) {
					cursorPos++;
				} else if (iCurs < Couleur.nbCouleurs()) {
					mo.p.addCoups();
					mo.p.addCod(putColor(iFond, iCurs, cursorPos));
					int[] rep = UtMM.nbBienMalPlaces(s, mo.p.getCod());
					mo.p.addRep(rep);
					if (mo.p.getRep()[0] > fondRep[0]) {
						founds[cursorPos] = iCurs;
						cursorPos = 0;
						colorsCount[iCurs]--;
						if (colorsCount[iCurs] == 0)
							iCurs++;
					} else {
						if (mo.p.getRep()[0] < fondRep[0])
							founds[cursorPos] = iFond;
						cursorPos++;
					}
				}
			}
		}
		int[] res = new int[Code.lgCode];
		for (int i = 0; i < founds.length; i++)
			res[i] = founds[i] == -1 ? iFond : founds[i];
		mo.p.getCod().cod = new Code(res).cod;
		return mo.p.nbCoups() + 1;
	}

	public static void main(String[] args) {
		Couleur.setTabCouleurs(Couleur.saisirCouleurs());
		Plateau.setNbEssaisMax(20);
		Ut.afficherSL("Longueur code");
		Code.lgCode = UtMM.saisirEntierPositif();

		int codSum = (int) Math.pow(Couleur.nbCouleurs(), Code.lgCode);
		Ut.clearConsole();
		Ut.afficherSL(codSum + " codes à tester...");
		Ut.afficherSL("[RESULTATS]\n");
		Test("Algo Basic", Statistics::BasicSolver);
		Test("Algo CFC", Statistics::CFCsolver);
		Ut.afficherSL("[TOTAL] " + codSum + " codes");
	}

	/*
	 * Permet de tester les algorithmes
	 */
	private static void Test(String name, Function<Code, Integer> func) {
		Code cod = new Code();
		int c = 0, sum = 0, max = 0;
		String maxCod = "";

		long startTime = System.currentTimeMillis();
		while (c == 0 || MancheOrdinateur.passeCodeSuivantLexico(cod)) {
			c++;
			int res = func.apply(cod);
			sum += res;
			if (res > max) {
				max = res;
				maxCod = Couleur.entiersVersMot(cod);
			}
		}
		long endTime = System.currentTimeMillis();
		PrintResult("- " + name, c, sum, max, maxCod, endTime - startTime);
	}

	/*
	 * Affiche les résultats
	 */
	private static void PrintResult(String algoName, int count, int sum, int max, String maxCod,
			long duration) {
		Ut.afficherSL(algoName + "\nMoyenne : " + (float) (sum / count) + " propositions | Max : "
				+ max + " propositions (" + maxCod + ") | Durée : " + duration
				+ "ms\n--------------");
	}
}

