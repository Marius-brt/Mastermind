import java.util.function.Function;

public class Statistics {
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

	private static int CFCsolver(Code s) {
		MancheOrdinateur mo = new MancheOrdinateur();
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


		while (mo.p.getRep()[0] != Code.lgCode) {
			mo.p.addCoups();
			mo.p.addCod(new Code(UtMM.copieTab(mo.p.getLastCod().cod)));
			mo.passeCodeSuivantLexicoCompat(mo.p.getCod());
			mo.p.addRep(UtMM.nbBienMalPlaces(s, mo.p.getCod()));
		}
		return mo.p.nbCoups() + 1;
	}

	public static void main(String[] args) {
		Couleur.setTabCouleurs(new char[] {'a', 'b', 'c'});
		Plateau.setNbEssaisMax(1000);
		Code.lgCode = 3;

		Couleur.setTabCouleurs(Couleur.saisirCouleurs());
		Plateau.setNbEssaisMax(1000);
		Ut.afficherSL("Longueur code");
		Code.lgCode = UtMM.saisirEntierPositif();

		int codSum = (int) Math.pow(Couleur.nbCouleurs(), Code.lgCode);
		Ut.afficherSL(codSum + " codes à tester...");
		Ut.clearConsole();
		Ut.afficherSL("[RESULTATS]\n");
		Test("Algo Basic", Statistics::BasicSolver);
		Test("Algo CFC", Statistics::CFCsolver);
		Ut.afficherSL("[TOTAL] " + codSum + " codes");


	}

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

	private static void PrintResult(String algoName, int count, int sum, int max, String maxCod,
			long duration) {
		Ut.afficherSL(algoName + "\nMoyenne : " + (float) (sum / count) + " propositions | Max : "
				+ max + " propositions (" + maxCod + ") | Durée : " + duration
				+ "ms\n--------------");
	}
}

