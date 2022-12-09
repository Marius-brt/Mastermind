public class Statistics {
	private static int BasicSolve(Code s) {
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

	public static void main(String[] args) {
		Couleur.setTabCouleurs(Couleur.saisirCouleurs());
		Plateau.setNbEssaisMax(1000);
		Ut.afficherSL("Longueur code");
		Code.lgCode = UtMM.saisirEntierPositif();
		Code cod = new Code("a".repeat(Code.lgCode));
		int c = 0, sumB = 0, maxB = 0;
		String maxCodB = "";
		Ut.afficherSL((int) Math.pow(Couleur.nbCouleurs(), Code.lgCode) + " codes à tester...");
		long startTime = System.currentTimeMillis();
		while (c == 0 || MancheOrdinateur.passeCodeSuivantLexico(cod)) {
			c++;
			int res = BasicSolve(cod);
			sumB += res;
			if (res > maxB) {
				maxB = res;
				maxCodB = Couleur.entiersVersMot(cod);
			}
		}
		long endTime = System.currentTimeMillis();
		Ut.clearConsole();
		Ut.afficherSL("[RESULTATS]\n");
		PrintResult("- Algo Basic", c, sumB, maxB, maxCodB, endTime - startTime);
		Ut.afficherSL("[TOTAL] " + c + " codes");
	}

	private static void PrintResult(String algoName, int count, int sum, int max, String maxCod,
			long duration) {
		Ut.afficherSL(algoName + "\nMoyenne : " + (float) (sum / count) + " propositions | Max : "
				+ max + " propositions (" + maxCod + ") | Durée : " + duration
				+ "ms\n--------------");
	}
}
