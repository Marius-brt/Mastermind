import java.util.*;
import java.lang.*;

public class MasterMindBase {

	// .........................................................................
	// OUTILS DE BASE
	// .........................................................................

	// fonctions classiques sur les tableaux

	/**
	 * pré-requis : nb >= 0 résultat : un tableau de nb entiers égaux à val
	 */

	public static int[] initTab(int nb, int val) {
		int[] t = new int[nb];
		for (int i = 0; i < nb; i++)
			t[i] = val;
		return t;
	}

	// ______________________________________________

	/**
	 * pré-requis : aucun résultat : une copie de tab
	 */
	public static int[] copieTab(int[] tab) {
		int[] c = new int[tab.length];
		for (int i = 0; i < tab.length; i++)
			c[i] = tab[i];
		return c;
	}

	// ______________________________________________

	/**
	 * pré-requis : aucun résultat : la liste des éléments de t entre parenthèses et séparés par des
	 * virgules
	 */
	public static String listElem(char[] t) {
		if (t.length == 0)
			return "";
		String r = "";
		for (char c : t)
			r += c + ",";
		return r.substring(0, r.length() - 1);
	}

	// ______________________________________________

	/**
	 * pré-requis : aucun résultat : le plus grand indice d'une case de t contenant c s'il existe,
	 * -1 sinon
	 */
	public static int plusGrandIndice(char[] t, char c) {
		for (int x = t.length - 1; x > 0; x--)
			if (t[x] == c)
				return x;
		return -1;
	}
	// ______________________________________________

	/**
	 * pré-requis : aucun résultat : vrai ssi c est un élément de t stratégie : utilise la fonction
	 * plusGrandIndice
	 */
	public static boolean estPresent(char[] t, char c) {
		for (char e : t)

			if (e == c)
				return true;
		return false;
	}

	// ______________________________________________

	/**
	 * pré-requis : aucun action : affiche un doublon et 2 de ses indices dans t s'il en existe
	 * résultat : vrai ssi les éléments de t sont différents stratégie : utilise la fonction
	 * plusGrandIndice
	 */
	public static boolean elemDiff(char[] t) {
		if (t.length < 2)
			return true;
		for (int x = 0; x < t.length; x++)
			for (int y = 0; y < t.length; y++)
				if (x != y && t[x] == t[y]) {
					Ut.afficherSL("Doublon pour la lettre " + t[x] + " entre l'index " + x
							+ " et l'index " + y);
					return false;
				}
		return true;
	}

	// ______________________________________________

	/**
	 * pré-requis : t1.length = t2.length résultat : vrai ssi t1 et t2 contiennent la même suite
	 * d'entiers
	 */
	public static boolean sontEgaux(int[] t1, int[] t2) {
		for (int i = 0; i < t1.length; i++)
			if (t1[i] != t2[i])
				return false;
		return true;
	}

	// ______________________________________________

	// Dans toutes les fonctions suivantes, on a comme pré-requis implicites sur les paramètres
	// lgCode, nbCouleurs et tabCouleurs :
	// lgCode > 0, nbCouleurs > 0, tabCouleurs.length > 0 et les éléments de tabCouleurs sont
	// différents

	// fonctions sur les codes pour la manche Humain

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

	// ____________________________________________________________

	/**
	 * pré-requis : aucun action : si codMot n'est pas correct, affiche pourquoi résultat : vrai ssi
	 * codMot est correct, c'est-à-dire de longueur lgCode et ne contenant que des éléments de
	 * tabCouleurs
	 */
	public static boolean codeCorrect(String codMot, int lgCode, char[] tabCouleurs) {
		if (codMot.length() != lgCode) {
			Ut.afficherSL("La longueur du code est incorrecte.");
			return false;
		}
		for (int i = 0; i < codMot.length(); i++) {
			boolean exist = false;
			for (int x = 0; x < tabCouleurs.length; x++)
				if (codMot.charAt(i) == tabCouleurs[x])
					exist = true;
			if (!exist) {
				Ut.afficherSL("La couleur n'existe pas.");
				return false;
			}
		}
		return true;
	}

	// ____________________________________________________________

	/**
	 * pré-requis : les caractères de codMot sont des éléments de tabCouleurs résultat : le code
	 * codMot sous forme de tableau d'entiers en remplaçant chaque couleur par son indice dans
	 * tabCouleurs
	 */
	public static int[] motVersEntiers(String codMot, char[] tabCouleurs) {
		int[] res = new int[codMot.length()];
		for (int x = 0; x < codMot.length(); x++)
			for (int y = 0; y < tabCouleurs.length; y++)
				if (codMot.charAt(x) == tabCouleurs[y])
					res[x] = y;
		return res;
	}

	// ____________________________________________________________

	/**
	 * pré-requis : aucun action : demande au joueur humain de saisir la (nbCoups + 1)ème
	 * proposition de code sous forme de mot, avec re-saisie éventuelle jusqu'à ce qu'elle soit
	 * correcte (le paramètre nbCoups ne sert que pour l'affichage) résultat : le code saisi sous
	 * forme de tableau d'entiers
	 */
	public static int[] propositionCodeHumain(int nbCoups, int lgCode, char[] tabCouleurs) {
		nbCoups++;
		Ut.afficherSL(
				((Integer) nbCoups).toString() + (nbCoups == 1 ? "ère" : "ème") + " proposition");
		Ut.afficher("Veuillez saisir un code : ");
		String c = Ut.saisirChaine();
		while (!codeCorrect(c, lgCode, tabCouleurs)) {
			Ut.afficherSL("Code incorrecte !");
			Ut.afficher("Veuillez saisir un code : ");
			c = Ut.saisirChaine();
		}
		return motVersEntiers(c, tabCouleurs);
	}

	// ____________________________________________________________

	/**
	 * pré-requis : cod1.length = cod2.length résultat : le nombre d'éléments communs de cod1 et
	 * cod2 se trouvant au même indice Par exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la
	 * fonction retourne 1 (le "0" à l'indice 3)
	 */
	public static int nbBienPlaces(int[] cod1, int[] cod2) {
		int c = 0;
		for (int x = 0; x < cod1.length; x++)
			if (cod1[x] == cod2[x])
				c++;
		return c;
	}

	// ____________________________________________________________

	/**
	 * pré-requis : les éléments de cod sont des entiers de 0 à nbCouleurs-1 résultat : un tableau
	 * de longueur nbCouleurs contenant à chaque indice i le nombre d'occurrences de i dans cod Par
	 * exemple, si cod = (1,0,2,0) et nbCouleurs = 6 la fonction retourne (2,1,1,0,0,0)
	 */
	public static int[] tabFrequence(int[] cod, int nbCouleurs) {
		int[] r = new int[nbCouleurs];
		for (int x = 0; x < cod.length; x++)
			r[cod[x]]++;
		return r;
	}

	// ____________________________________________________________

	/**
	 * pré-requis : les éléments de cod1 et cod2 sont des entiers de 0 à nbCouleurs-1 résultat : le
	 * nombre d'éléments communs de cod1 et cod2, indépendamment de leur position Par exemple, si
	 * cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne 3 (2 "0" et 1 "1")
	 */
	public static int nbCommuns(int[] cod1, int[] cod2, int nbCouleurs) {
		int[] f1 = tabFrequence(cod1, nbCouleurs);
		int[] f2 = tabFrequence(cod2, nbCouleurs);
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

	// ____________________________________________________________

	/**
	 * pré-requis : cod1.length = cod2.length et les éléments de cod1 et cod2 sont des entiers de 0
	 * à nbCouleurs-1 résultat : un tableau de 2 entiers contenant à l'indice 0 (resp. 1) le nombre
	 * d'éléments communs de cod1 et cod2 se trouvant (resp. ne se trouvant pas) au même indice Par
	 * exemple, si cod1 = (1,0,2,0) et cod2 = (0,1,0,0) la fonction retourne (1,2) : 1 bien placé
	 * (le "0" à l'indice 3) et 2 mal placés (1 "0" et 1 "1")
	 */
	public static int[] nbBienMalPlaces(int[] cod1, int[] cod2, int nbCouleurs) {
		int bp = nbBienPlaces(cod1, cod2);
		int mp = nbCommuns(cod1, cod2, nbCouleurs) - bp;
		return new int[] {bp, mp < 0 ? 0 : mp};
	}


	// ____________________________________________________________

	// .........................................................................
	// MANCHEHUMAIN
	// .........................................................................

	/**
	 * pré-requis : numMache >= 1 action : effectue la (numManche)ème manche où l'ordinateur est le
	 * codeur et l'humain le décodeur (le paramètre numManche ne sert que pour l'affichage) résultat
	 * : - un nombre supérieur à nbEssaisMax, calculé à partir du dernier essai du joueur humain
	 * (cf. sujet), s'il n'a toujours pas trouvé au bout du nombre maximum d'essais - sinon le
	 * nombre de codes proposés par le joueur humain
	 */
	public static int mancheHumain(int lgCode, char[] tabCouleurs, int numManche, int nbEssaisMax) {
		int[] cod = codeAleat(lgCode, tabCouleurs.length);
		int nbCoups = 0;
		int[] prop = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
		while (nbCoups < nbEssaisMax && !Arrays.equals(cod, prop)) {
			int[] res = nbBienMalPlaces(cod, prop, tabCouleurs.length);
			System.out.println(
					"Résultat : " + res[0] + " bien placé(s) et " + res[1] + " mal placé(s)");
			nbCoups++;
			prop = propositionCodeHumain(nbCoups, lgCode, tabCouleurs);
		}
		if (nbCoups == nbEssaisMax) {
			int[] res = nbBienMalPlaces(cod, prop, tabCouleurs.length);
			Ut.afficherSL("Fin manche. Vous n'avez pas trouvé. Le code était "
					+ entiersVersMot(cod, tabCouleurs));
			return nbEssaisMax + res[1] + (2 * (lgCode - (res[0] + res[1])));
		}
		Ut.afficherSL("Fin manche. Vous avez trouvé !");
		return nbCoups + 1;
	}

	// ____________________________________________________________

	// ...................................................................
	// FONCTIONS COMPLÉMENTAIRES SUR LES CODES POUR LA MANCHE ORDINATEUR
	// ...................................................................

	/**
	 * pré-requis : les éléments de cod sont des entiers de 0 à tabCouleurs.length-1 résultat : le
	 * code cod sous forme de mot d'après le tableau tabCouleurs
	 */
	public static String entiersVersMot(int[] cod, char[] tabCouleurs) {
		String s = "";
		for (int i = 0; i < cod.length; i++)
			s += tabCouleurs[cod[i]];
		return s;
	}

	// ___________________________________________________________________

	/**
	 * pré-requis : rep.length = 2 action : si rep n'est pas correcte, affiche pourquoi, sachant que
	 * rep[0] et rep[1] sont les nombres de bien et mal placés resp. résultat : vrai ssi rep est
	 * correct, c'est-à-dire rep[0] et rep[1] sont >= 0 et leur somme est <= lgCode
	 */
	public static boolean repCorrecte(int[] rep, int lgCode) {
		if (rep[0] + rep[1] != lgCode)
			return false;
		if (rep[0] < 0 || rep[1] < 0)
			return false;
		return true;
	}

	// ___________________________________________________________________

	/**
	 * pré-requis : aucun action : demande au joueur humain de saisir les nombres de bien et mal
	 * placés, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte résultat : les réponses du
	 * joueur humain dans un tableau à 2 entiers
	 */
	public static int[] reponseHumain(int lgCode) {
		Ut.afficherSL("Nombres de bien placé");
		int bp = saisirEntierPos();
		Ut.afficherSL("Nombres de mal placé");
		int mp = saisirEntierPos();
		return new int[] {bp, mp};
	}

	// ___________________________________________________________________

	/**
	 * CHANGE : action si le code suivant n'existe pas
	 *************************************************
	 * pré-requis : les éléments de cod1 sont des entiers de 0 à nbCouleurs-1 action/résultat : met
	 * dans cod1 le code qui le suit selon l'ordre lexicographique (dans l'ensemble des codes à
	 * valeurs de 0 à nbCouleurs-1) et retourne vrai si ce code existe, sinon met dans cod1 le code
	 * ne contenant que des "0" et retourne faux
	 */
	public static boolean passeCodeSuivantLexico(int[] cod1, int nbCouleurs) {
		int i = cod1.length - 1;
		while (i >= 0 && cod1[i] == nbCouleurs - 1) {
			i--;
		}
		if (i < 0) {
			for (int j = 0; j < cod1.length; j++) {
				cod1[j] = 0;
			}
			return false;
		}
		cod1[i]++;
		for (int j = i + 1; j < cod1.length; j++) {
			cod1[j] = 0;
		}
		return true;
	}

	// ___________________________________________________________________

	/**
	 * CHANGE : ajout du paramètre cod1 et modification des spécifications
	 ********************************************************************* 
	 * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0
	 * <= nbCoups < cod.length, nbCoups < rep.length et les éléments de cod1 et de cod sont des
	 * entiers de 0 à nbCouleurs-1 résultat : vrai ssi cod1 est compatible avec les nbCoups
	 * premières lignes de cod et de rep, c'est-à-dire que si cod1 était le code secret, les
	 * réponses aux nbCoups premières propositions de cod seraient les nbCoups premières réponses de
	 * rep resp.
	 */
	public static boolean estCompat(int[] cod1, int[][] cod, int[][] rep, int nbCoups,
			int nbCouleurs) {
		/*
		 * for (int i = 0; i < nbCoups; i++) { int[] res = nbBienMalPlaces(cod1, cod[i],
		 * nbCouleurs); if (res[0] != rep[i][0] || res[1] != rep[i][1]) { return false; } } return
		 * true;
		 */
		return nbCommuns(cod1, cod[nbCoups], nbCouleurs) == cod1.length;
	}

	// ___________________________________________________________________

	/**
	 * CHANGE : renommage de passePropSuivante en passeCodeSuivantLexicoCompat, ajout du paramètre
	 * cod1 et modification des spécifications
	 ************************************************************************** 
	 * pré-requis : cod est une matrice à cod1.length colonnes, rep est une matrice à 2 colonnes, 0
	 * <= nbCoups < cod.length, nbCoups < rep.length et les éléments de cod1 et de cod sont des
	 * entiers de 0 à nbCouleurs-1 action/résultat : met dans cod1 le plus petit code (selon l'ordre
	 * lexicographique (dans l'ensemble des codes à valeurs de 0 à nbCouleurs-1) qui est à la fois
	 * plus grand que cod1 selon cet ordre et compatible avec les nbCoups premières lignes de cod et
	 * rep si ce code existe, sinon met dans cod1 le code ne contenant que des "0" et retourne faux
	 * S: (a,b,b) P1: (a,a,a) 1 BP 0 MP P2: ()
	 */
	public static boolean passeCodeSuivantLexicoCompat(int[] cod1, int[][] cod, int[][] rep,
			int nbCoups, int nbCouleurs) {
		/*
		 * return passeCodeSuivantLexico(cod1, nbCouleurs) && estCompat(cod1, cod, rep, nbCoups,
		 * nbCouleurs);
		 */
		if (passeCodeSuivantLexico(cod1, nbCouleurs)) {
			while (!estCompat(cod1, cod, rep, nbCoups, nbCouleurs))
				if (!passeCodeSuivantLexico(cod1, nbCouleurs))
					return false;
		} else {
			return false;
		}
		return true;
	}

	// ___________________________________________________________________

	// manche Ordinateur

	/**
	 * pré-requis : numManche >= 2 action : effectue la (numManche)ème manche où l'humain est le
	 * codeur et l'ordinateur le décodeur (le paramètre numManche ne sert que pour l'affichage)
	 * résultat : - 0 si le programme détecte une erreur dans les réponses du joueur humain - un
	 * nombre supérieur à nbEssaisMax, calculé à partir du dernier essai de l'ordinateur (cf.
	 * sujet), s'il n'a toujours pas trouvé au bout du nombre maximum d'essais - sinon le nombre de
	 * codes proposés par l'ordinateur
	 */
	public static int mancheOrdinateur(int lgCode, char[] tabCouleurs, int numManche,
			int nbEssaisMax) {
		if (nbEssaisMax == 0)
			return 0;
		int nbCoups = 0;
		int[][] cod = new int[nbEssaisMax][];
		int[][] rep = new int[nbEssaisMax][];
		cod[0] = initTab(lgCode, 0);
		Ut.afficherSL("L'ordi propose : " + entiersVersMot(cod[0], tabCouleurs));
		rep[0] = reponseHumain(lgCode);
		int c = 0;
		while (rep[nbCoups][0] + rep[nbCoups][1] != lgCode && nbCoups + 1 < nbEssaisMax) {
			c = 0;
			for (int i = 0; i < lgCode - (rep[nbCoups][0] + rep[nbCoups][1]); i++)
				c += Math.pow(tabCouleurs.length, i);
			nbCoups++;
			cod[nbCoups] = copieTab(cod[nbCoups - 1]);
			for (int i = 0; i < c; i++) {
				if (!passeCodeSuivantLexico(cod[nbCoups], tabCouleurs.length))
					return 0;
			}
			Ut.afficherSL("L'ordi propose : " + entiersVersMot(cod[nbCoups], tabCouleurs));
			rep[nbCoups] = reponseHumain(lgCode);
		}
		if (nbCoups >= nbEssaisMax - 1)
			return nbEssaisMax + rep[nbCoups][1]
					+ 2 * (lgCode - (rep[nbCoups][0] + rep[nbCoups][1]));
		nbCoups++;
		int y = nbCoups;
		cod[y] = copieTab(cod[y - 1]);
		rep[y] = copieTab(rep[y - 1]);
		printCod(cod[y]);
		while (nbCoups + 1 <= nbEssaisMax && rep[y][0] != lgCode) {
			if (!passeCodeSuivantLexicoCompat(cod[y], cod, rep, y, tabCouleurs.length)) {
				return 0;
			}
			printCod(cod[y]);
			Ut.afficherSL("L'ordi propose : " + entiersVersMot(cod[y], tabCouleurs));
			rep[y] = reponseHumain(lgCode);
			nbCoups++;
		}
		Ut.afficherSL("Coups " + nbCoups + " max " + nbEssaisMax);
		if (nbCoups < nbEssaisMax) {
			Ut.afficherSL("L'ordi a trouvé");
			Ut.afficherSL(entiersVersMot(cod[nbCoups], tabCouleurs));
			return nbCoups;
		}
		return nbEssaisMax + rep[nbCoups - 1][1]
				+ 2 * (lgCode - (rep[nbCoups - 1][0] + rep[nbCoups - 1][1]));
	}

	// ___________________________________________________________________

	// .........................................................................
	// FONCTIONS DE SAISIE POUR LE PROGRAMME PRINCIPAL
	// .........................................................................


	/**
	 * pré-requis : aucun action : demande au joueur humain de saisir un entier strictement positif,
	 * avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte résultat : l'entier strictement
	 * positif saisi
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

	// ___________________________________________________________________

	/**
	 * pré-requis : aucun action : demande au joueur humain de saisir un entier pair strictement
	 * positif, avec re-saisie éventuelle jusqu'à ce qu'elle soit correcte résultat : l'entier pair
	 * strictement positif saisi
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

	// ___________________________________________________________________

	/**
	 * pré-requis : aucun action : demande au joueur humain de saisir le nombre de couleurs
	 * (stricement positif), puis les noms de couleurs aux initiales différentes, avec re-saisie
	 * éventuelle jusqu'à ce qu'elle soit correcte résultat : le tableau des initiales des noms de
	 * couleurs saisis
	 */
	public static char[] saisirCouleurs() {
		Ut.afficherSL("Renseignez le nombre de couleurs");
		int n = saisirEntierPositif();
		char[] cs = new char[n];
		for (int x = 0; x < n; x++) {
			Ut.afficher("Saisir une couleur : ");
			char c = Ut.saisirCaractere();
			if (estPresent(cs, c)) {
				Ut.afficherSL("Couleur déjà renseigné !");
				x--;
			} else {
				cs[x] = c;
			}
		}
		return cs;
	}

	// ___________________________________________________________________

	// .........................................................................
	// PROGRAMME PRINCIPAL
	// .........................................................................


	public static void printCod(int[] cod) {
		for (int i : cod)
			Ut.afficher(i + ",");
		Ut.afficher("\n");
	}

	public static boolean next(int[] cod, int[] newCode) {
		if (passeCodeSuivantLexico(newCode, 3)) {
			while (nbCommuns(cod, newCode, 3) != cod.length)
				if (!passeCodeSuivantLexico(newCode, 3))
					return false;
		} else {
			return false;
		}
		return true;
	}

	/**
	 * CHANGE : ajout de : le nombre d'essais maximum doit être strictement positif
	 ******************************************************************************
	 * action : demande à l'utilisateur de saisir les paramètres de la partie (lgCode, tabCouleurs,
	 * nbManches, nbEssaisMax), effectue la partie et affiche le résultat (identité du gagnant ou
	 * match nul). La longueur d'un code, le nombre de couleurs et le nombre d'essais maximum
	 * doivent être strictement positifs. Le nombre de manches doit être un nombre pair strictement
	 * positif. Les initiales des noms de couleurs doivent être différentes. Toute donnée incorrecte
	 * doit être re-saisie jusqu'à ce qu'elle soit correcte.
	 */
	public static void main(String[] args) {
		/*
		 * Ut.afficherSL("Essais max"); int nbEssaisMax = saisirEntierPositif();
		 * Ut.afficherSL("Longueur code"); int lgCode = saisirEntierPositif();
		 * Ut.afficherSL("Nombre de manches"); int manches = saisirEntierPairPositif(); char[]
		 * tabCouleurs = saisirCouleurs(); int ordi = 0, humain = 0;
		 * Ut.afficherSL("--------------"); for (int i = 1; i < manches + 1; i++) { if (i % 2 == 0)
		 * humain += mancheOrdinateur(lgCode, tabCouleurs, i, nbEssaisMax); else ordi +=
		 * mancheHumain(lgCode, tabCouleurs, 0, nbEssaisMax); Ut.afficherSL("--------------"); }
		 * Ut.afficherSL("Humain : " + humain + " Ordi : " + ordi);
		 */
		Ut.afficherSL(mancheOrdinateur(3, new char[] {'a', 'b', 'c'}, 2, 8));

		/*
		 * int nbCouleurs = 3; int n = 1; int[] s = new int[] {1, 0, 2}; int[] cod = new int[] {0,
		 * 0, 0}; int[] res = nbBienMalPlaces(s, cod, nbCouleurs); printCod(cod); int c = 0; while
		 * (res[0] + res[1] != s.length) { c = 0; for (int i = 0; i < s.length - (res[0] + res[1]);
		 * i++) c += Math.pow(nbCouleurs, i); for (int i = 0; i < c; i++) { if
		 * (!passeCodeSuivantLexico(cod, nbCouleurs)) { Ut.afficherSL("Impossible"); return; } } res
		 * = nbBienMalPlaces(s, cod, nbCouleurs); printCod(cod); n++; }
		 * 
		 * int[] newCode = copieTab(cod); if (!next(cod, newCode)) { Ut.afficherSL("Impossible");
		 * return; } res = nbBienMalPlaces(s, newCode, nbCouleurs); printCod(newCode); n++; while
		 * (res[0] != s.length && next(cod, newCode)) { res = nbBienMalPlaces(s, newCode,
		 * nbCouleurs); printCod(newCode); n++; } Ut.afficherSL(n);
		 */



	} // fin main

	// ___________________________________________________________________

} // fin MasterMindBase
