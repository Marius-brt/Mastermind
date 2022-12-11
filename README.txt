Infos générales :

- BP = Bien Placé
- MP = Mal Placé

Version de base :

	Pour tester le MasterMind de base :

	1 - Se rendre dans le dossier "MasterMindBase"
	2 - Executer la commande "javac *.java && java MM" pour linux ou "javac *.java; java MM" pour Windows

Version extensions

	Pour tester le MasterMind avec extensions :

	1 - Se rendre dans le dossier "MasterMindExtensions"
	2 - Executer la commande "javac *.java && java MainMasterMind" pour linux ou "javac *.java; java MainMasterMind" pour Windows
	3 - Choisir la version Graphique ou Console (y = oui, n = non)

	Utilisation du MasterMind Graphique :

	Le MasterMind Graphique utilise 3 couleurs (Rouge = r, Bleu = b, Vert = v).
	Le code couleur s'écrit donc avec la première lettre de chaque couleur.

	Utilisation de l'extension Statistiques :

	1 - Se rendre dans le dossier "MasterMindExtensions"
	2 - Executer la commande "javac *.java && java Statistics" pour linux ou "javac *.java; java Statistics" pour Windows
	3 - Rentrer les couleurs et la longueur du code (attention l'ordre des couleurs défini l'ordre lexicographique)
	4 - Attendre la fin des tests

	Explications des fichiers :

	- "UtMM.java", "Plateau.java", "Partie.java", "MancheOrdinateur.java", "MancheHumain.java", "MainMasterMind.java",
	"Couleur.java" et "Code.java" sont les fichiers de l'extension programmation orienté objet.

	- Les fichiers suivants servent à l'extension Graphique :
		- "Fenetre.java" = fichier principale de l'extension Graphique. Il permet de créer la fenêtre. Tout les boutons, textes
		et input sont placés dans ce fichier. La classe hérite de la classe JFrame.
		- "ColorsPanel.java" = permet de faire le rendu des points de couleur et des textes des couleurs BP et MP. La classe hérite de la classe JPanel.
		- "Point.java" = permet de dessiner des ronds de couleur.
		- "GraphText.java" = permet de dessiner des textes. Cela permet d'afficher les BP et MP à des positions X et Y précise.
		- "RegexFilter.java" = permet d'utiliser des regex (expressions régulières) dans les inputs. Cela sert notamment à limiter le nombre de
		charactères pour l'input qui permet de rentrer un code ou bien d'autorisé uniquement des chiffres pour celui pour indiquer les BP et MP.
		
	- "Statistics.java" permet de faire les statistics des différents algorithme. Les différents algorithmes ont été réécrit et alégé afin de ne pas à avoir
	à rentrer manuellement des codes comme dans une manche normale.