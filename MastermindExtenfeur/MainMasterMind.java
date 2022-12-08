public class MainMasterMind {
	public static void main(String[] args) {
		Ut.clearConsole();
		Couleur.setTabCouleurs(new char[] {'a', 'b', 'c'});
		Code.lgCode = 3;
		/*
		 * MancheHumain hm = new MancheHumain(new Plateau(4)); hm.Joue();
		 */

		Fenetre.createFenetre(300, 400, "MasterMind");

		MancheOrdinateur mo = new MancheOrdinateur(new Plateau(2));
		mo.Joue();
		Fenetre.getInstance().dispose();
	}
}
