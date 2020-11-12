import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe pour représenter un joueur pour le jeu de la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Joueur {

	//Attribut de chaîne qui représente le nom du joueur
	private String nomJoueur;
	//Attribut d'une grille représentant dans quelle grille va jouer le joueur
	private Grille grilleAtt;
	//Attribut d'un entier représentant le nombre de bateaux pour un joueur
	private int nbBateaux = 5;
	//Attribut d'une liste de bateaux pour les trier
	private ArrayList<Bateau> lBat;
	//Attribut d'un tableau de bateaux pour les répertorier
	private Bateau[] tabB;

	/**
	 * Constructeur de notre classe Joueur pour initialiser tous ces attributs
	 * @param n Chaîne permettant d'attribuer un nom au joueur
	 * @param att Grille dans laquelle le joueur va jouer
	 * @param a Premier bateau du joueur
	 * @param b Deuxième bateau du joueur
	 * @param c Troisième bateau du joueur
	 * @param d Quatrième bateau du joueur
	 * @param e Cinquième bateau du joueur
	 */
	public Joueur(String n, Grille att, Bateau a, Bateau b, Bateau c, Bateau d, Bateau e) {
		//Initialisation de la taille du tableau de bateaux tabb à 6 pour pouvoir stocker les 5 bateaux
		Bateau[] tabb = new Bateau[6];
		//Attribution des bateaux du joueur dans le tableau
		tabb[5] = e;
		tabb[4] = d;
		tabb[3] = c;
		tabb[2] = b;
		tabb[1] = a;
		//Attribution du nom du joueur à n
		this.nomJoueur = n;
		//Attribution de la grille du joueur à att
		this.grilleAtt = att;
		//Initialisation du nombre de bateaux à 0
		this.nbBateaux = 0 ;
		//Nouvelle liste de bateau
		this.lBat = new ArrayList<Bateau>();
		//Attribution des bateaux dans la liste
		this.lBat.add(a);
		this.lBat.add(b);
		this.lBat.add(c);
		this.lBat.add(d);
		this.lBat.add(e);
		
	}
	
	/**
	 * Fonction qui effectuer l'attaque d'un joueur sur la grille
	 * @param x Position de l'attaque en x
	 * @param y Position de l'attaque en y
	 * @param t Grille où le joueur joue
	 * @param j Joueur qui attaque
	 * @param tabB Tableau stockant les bateaux appartenant au joueur
	 */
	public void attaquer(int x, int y, Grille t, Joueur j, Bateau[] tabB){
		//Si la position où l'attaque est demandée est accesible
		if (t.etreAccessible(x, y)) {
			//Si la case ne contient pas de bateaux
			if ((t.getTabGrille()[x][y].getOccupation() == 8) || (t.getTabGrille()[x][y].getOccupation() == 9)) {
				//La case se voit attribuer une valeur pour dire qu'elle est touchée mais qu'elle ne contient aucun bateau
				t.getTabGrille()[x][y].toucherCase();
				//Message dans la console
				System.out.println("Nous n'avons rien trouvé ici");
			} 
			//Si la case contient un bateau et qu'elle a déja était touchée
			if((t.getTabGrille()[x][y].getOccupation() == 7)) {
				//Message dans la console
				System.out.println("Bateau déja touché");
			}
			//Si la case contient le bateau de numéro 5
			if (t.getTabGrille()[x][y].getOccupation() == 5) { 
				//La case se voit attribuer une valeur pour dire qu'elle est touchée et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la méthode subirAttaque pour perdre des points de vie au bateau touché
				Bateau.subirAttaque(5, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a été touché");
				//Appel de la méthode coule pour vérifier si le bateau est entièrement et donc coule ou non
				Bateau.coule(5, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(5, tabB, j));
			}
			//Si la case contient le bateau de numéro 4
			if (t.getTabGrille()[x][y].getOccupation() == 4) {
				//La case se voit attribuer une valeur pour dire qu'elle est touchée et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la méthode subirAttaque pour perdre des points de vie au bateau touché
				Bateau.subirAttaque(4, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(4, tabB) + " a été touché");
				//Appel de la méthode coule pour vérifier si le bateau est entièrement et donc coule ou non
				Bateau.coule(4, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(4, tabB, j));
			}
			//Si la case contient le bateau de numéro 3
			if (t.getTabGrille()[x][y].getOccupation() == 3) {
				//La case se voit attribuer une valeur pour dire qu'elle est touchée et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la méthode subirAttaque pour perdre des points de vie au bateau touché
				Bateau.subirAttaque(3, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(3, tabB) + " a été touché");
				//Appel de la méthode coule pour vérifier si le bateau est entièrement et donc coule ou non
				Bateau.coule(3, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(3, tabB, j));
			}
			//Si la case contient le bateau de numéro 2
			if (t.getTabGrille()[x][y].getOccupation() == 2) {
				//La case se voit attribuer une valeur pour dire qu'elle est touchée et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la méthode subirAttaque pour perdre des points de vie au bateau touché
				Bateau.subirAttaque(2, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a été touché");
				//Appel de la méthode coule pour vérifier si le bateau est entièrement et donc coule ou non
				Bateau.coule(2, tabB, j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(2, tabB, j));
			}
			//Si la case contient le bateau de numéro 1
			if (t.getTabGrille()[x][y].getOccupation() == 1) {
				//La case se voit attribuer une valeur pour dire qu'elle est touchée et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la méthode subirAttaque pour perdre des points de vie au bateau touché
				Bateau.subirAttaque(1, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a été touché");
				//Appel de la méthode coule pour vérifier si le bateau est entièrement et donc coule ou non
				Bateau.coule(1, tabB, j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(1, tabB,  j));
			}
		}
	}
	
	/**
	 * Fonction qui réinitialise le nombre de bateaux à 5 pour recommencer la partie
	 *
	 */
	public void setNbBateaux() {
		this.nbBateaux = 5;
	}
	
	/**
	 * Fonction qui récupère le nombre de bateaux encore non coulés appartenant au joueur
	 *
	 */
	public int getNbBateaux() {
		return this.nbBateaux;
	}

	/**
	 * Fonction qui récupère la grille où le joueur joue
	 *
	 */
	public Grille getGrilleAtt() {
		return grilleAtt;
	}

	/**
	 * Fonction qui récupère le nom du joueur
	 *
	 */
	public String getNomJoueur() {
		return nomJoueur;
	}
	
	/**
	 * Fonction qui coule un bateau du joueur
	 *
	 */
	public void bateauCoule() {
		//Le nombre de bateaux diminue de 1
		nbBateaux -=1;
	}
	
	/**
	 * Fonction qui récupère les chaînes qui représentent la liste de bateaux triée par impact
	 *
	 */
	public String[] afficherParImpact() {
		//Initialisation d'un tableau de chaînes à une taille de 5
		String[] tabTrieParImpact = new String[5];
		//Pour i allant de 0 à 5(non inclus), avec i allant de 1 en 1
		for (int i = 0 ; i < 5 ; i++){
			//Stockage des chaînes dans le tableau
			tabTrieParImpact[i] = this.lBat.get(i).afficherNom() + " à " + this.lBat.get(i).getViee() +"% de vie";
		}
		return tabTrieParImpact;
	}
	
	/**
	 * Fonction qui récupère les chaînes qui représentent la liste de bateaux triée par taille
	 *
	 */
	public String[] afficherParTaille() {
		//Initialisation d'un tableau de chaînes à une taille de 5
		String[] tabTrieParTaille = new String[5];
		//Pour i allant de 0 à 5(non inclus), avec i allant de 1 en 1
		for (int i =0; i <5; i++){
			//Stockage des chaînes dans le tableau
			tabTrieParTaille[i] = this.lBat.get(i).afficherNom() + " à une taille de " + this.lBat.get(i).getTaille();
		}
		return tabTrieParTaille;
	}
	
	/**
	 * Fonction qui effectue le tri de la liste de bateaux par taille
	 *
	 */
	public  void trierParTaille() {
		Collections.sort(lBat, (bateau1, bateau2) -> (true ? 1 : -1) * (bateau1.getTaille() - bateau2.getTaille()) );		
	}
	
	/**
	 * Fonction qui effectue le tri de la liste de bateaux par impact
	 *
	 */
	public void trierParImpact() {
		Collections.sort(lBat, (bateau1, bateau2) -> (true ? 1 : -1) * (bateau1.getViee() - bateau2.getViee()) ); 
	}
	
	/**
	 * Fonction qui trie la liste de bateaux selon le type de tri demandé
	 * 
	 */
	public void trier(int trie) {
		//Si trie = 1
		if (trie == 1) {
			//On trie la liste de bateaux par impact
			this.trierParImpact();
		//Sinon si trie = 2
		} else if (trie == 2) {
			//On trie la liste de bateaux par taille
			this.trierParTaille();
		} 
	}
	
	/**
	 * Fonction qui retourne le tableau stockant les bateaux du joueur
	 * 
	 */
	public Bateau[] getTabB() {
		return tabB;
	}
	

}

