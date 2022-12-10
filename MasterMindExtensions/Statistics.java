import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Statistics {
	public static int BasicSolver(Code s) {
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

	public static int CFCsolver(Code s) {
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

		Code cod = new Code(), cod2 = new Code();
		int c = 0, sumB = 0, maxB = 0;
		String maxCodB = "", maxCodCFC = "";
		int sumCFC = 0, maxCFC = 0;

		int codSum = (int) Math.pow(Couleur.nbCouleurs(), Code.lgCode);
		Ut.afficherSL(codSum + " codes à tester...");
		long startTime = System.currentTimeMillis();
		while (c == 0 || MancheOrdinateur.passeCodeSuivantLexico(cod)) {
			c++;
			int res = BasicSolver(cod);
			sumB += res;
			if (res > maxB) {
				maxB = res;
				maxCodB = Couleur.entiersVersMot(cod);
			}
		}
		long endTime = System.currentTimeMillis();
		long startTime2 = System.currentTimeMillis();
		c = 0;
		while (c == 0 || MancheOrdinateur.passeCodeSuivantLexico(cod2)) {
			c++;
			int res = BasicSolver(cod2);
			sumCFC += res;
			if (res > maxCFC) {
				maxCFC = res;
				maxCodCFC = Couleur.entiersVersMot(cod2);
			}
		}
		long endTime2 = System.currentTimeMillis();
		Ut.clearConsole();
		Ut.afficherSL("[RESULTATS]\n");
		// runTest(BasicSolver, "Basic algo");
		PrintResult("- Algo Basic", c, sumB, maxB, maxCodB, endTime - startTime);
		PrintResult("- Algo CFC", c, sumCFC, maxCFC, maxCodCFC, endTime2 - startTime2);
		Ut.afficherSL("[TOTAL] " + codSum + " codes");

	}

	/*
	 * private <T extends Callable> void runTest(Class<T> test, String name) { ExecutorService
	 * executorService = Executors.newSingleThreadExecutor(); Future<Integer> future =
	 * executorService.submit(new test()); try { Code cod = new Code(); int c = 0, sum = 0, max = 0;
	 * String maxCod = ""; long startTime = System.currentTimeMillis(); while (c == 0 ||
	 * MancheOrdinateur.passeCodeSuivantLexico(cod)) { c++; int res = future.get(); sum += res; if
	 * (res > max) { max = res; maxCod = Couleur.entiersVersMot(cod); } } long endTime =
	 * System.currentTimeMillis(); PrintResult("- " + name, c, sum, max, maxCod, endTime -
	 * startTime);
	 * 
	 * } catch (Exception e) { Ut.afficherSL("Erreur avec l'algorithme " + name); } }
	 */

	private static void PrintResult(String algoName, int count, int sum, int max, String maxCod,
			long duration) {
		Ut.afficherSL(algoName + "\nMoyenne : " + (float) (sum / count) + " propositions | Max : "
				+ max + " propositions (" + maxCod + ") | Durée : " + duration
				+ "ms\n--------------");
	}
}

