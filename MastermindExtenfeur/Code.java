public class Code {
	public int[] cod;
	public static int lgCode;

	public Code() {
		this.cod = UtMM.initTab(lgCode, 0);
	}

	public Code(int[] cod) {
		this.cod = UtMM.copieTab(cod);
	}

	public Code(String cod) {
		this.cod = Couleur.motVersEntiers(cod);
	}
}
