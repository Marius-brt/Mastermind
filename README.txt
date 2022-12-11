Infos générales :

- BP = Bien Placé
- MP = Mal Placé

Version de base :

	Pour tester le MasterMind de base :

	1 - Se rendre dans le dossier "MasterMindBase"
	2 - Executer la commande "javac *.java && java MM" pour linux ou "javac *.java; java MM" pour Windows

Version extensions

	Tout les fichiers d'extension ont été mis dans le même dossier afin de ne pas à avoir de problème avec les packages.

	Pour tester le MasterMind avec extensions :

	1 - Se rendre dans le dossier "MasterMindExtensions"
	2 - Executer la commande "javac *.java && java MainMasterMind" pour linux ou "javac *.java; java MainMasterMind" pour Windows
	3 - Choisir la version Graphique ou Console (y = oui, n = non)

	Extension Graphique :

	Pour tester l'extension Graphique il suffit de choisir la version Graphique en lanceant le MasterMind comme écrit ci-dessus.
	Le MasterMind Graphique utilise 3 couleurs (Rouge = r, Bleu = b, Vert = v).
	Le code couleur s'écrit donc avec la première lettre de chaque couleur.

	Extension affichage du plateau :

	Si vous lancer le MasterMind en version console, le plateau sera afficher sous forme de tableau.
	Le nombre de colomne dépend de la longueur du code et le nombre de ligne du nombre d'essais max.
	A côté de chaque ligne sera indiqué le nombre de BP et de MP pour ce coups.

	Extension erreurs de réponse :

	Si le joueur fait une erreur ou tente de tricher lorsqu'il est codeur, l'ordinateur le détectera.
	Il demandera donc le code que le joueur avait. Si l'ordinateur avait proposer le bon code, l'ordinateur
	dit au joueur qu'il avait proposer le bon code au coups X. Si le code était impossible à trouver car le nombre
	de BP ou de MP était faux à un coups, l'ordinateur dit au joueur à quelle coups les données était fausses.

	Utilisation de l'extension Statistiques :

	1 - Se rendre dans le dossier "MasterMindExtensions"
	2 - Executer la commande "javac *.java && java Statistics" pour linux ou "javac *.java; java Statistics" pour Windows
	3 - Rentrer les couleurs et la longueur du code (attention l'ordre des couleurs défini l'ordre lexicographique)
	4 - Attendre la fin des tests

	Algorithme CFC :

	L'algorithme CFC est présent dans le fichier "Statistics.java". On peut le tester en utilisant l'extension Statistiques expliqué ci-dessus.
	L'algo CFC utilise environ 2 fois plus de coups que l'algo lexicographique. Celui-ci fonctionne comme ceci :

	(Phase C)
	1 - Dans un premier temps on remplis uniquement le code de la première lettre de tabCouleurs puis on regarde le nombre de BP.
	2 - On ajoute ensuite la lettre suivante X fois (X = lgCode - BP du code précédent). On répète l'opération 2 jusqu'à que la somme
	de BP et MP du nouveau code soit égale à lgCode. Le code obtenu possède toutes les couleurs du code à trouver mais dans le mauvais ordre.
	On appelera ce code A.
	(Phase FC)
	3 - On crée un code avec uniquement la lettre la plus présente dans le code obtenu dans l'étape précédente (on l'appelera code B). On prend ensuite une autre
	couleur du code A et on la place à la première position du code B. Ensuite on vérifie :
		- Si le nombre de BP est supérieur à ceux du code B alors la couleur est à la bonne position.
		- Si le nombre de BP est inférieur à ceux du code B alors c'est une couleur de fond à la place cette couleur
	On répète cette étape jusqu'à que toutes les couleurs est été placées

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