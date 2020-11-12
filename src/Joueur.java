import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe pour repr�senter un joueur pour le jeu de la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Joueur {

	//Attribut de cha�ne qui repr�sente le nom du joueur
	private String nomJoueur;
	//Attribut d'une grille repr�sentant dans quelle grille va jouer le joueur
	private Grille grilleAtt;
	//Attribut d'un entier repr�sentant le nombre de bateaux pour un joueur
	private int nbBateaux = 5;
	//Attribut d'une liste de bateaux pour les trier
	private ArrayList<Bateau> lBat;
	//Attribut d'un tableau de bateaux pour les r�pertorier
	private Bateau[] tabB;

	/**
	 * Constructeur de notre classe Joueur pour initialiser tous ces attributs
	 * @param n Cha�ne permettant d'attribuer un nom au joueur
	 * @param att Grille dans laquelle le joueur va jouer
	 * @param a Premier bateau du joueur
	 * @param b Deuxi�me bateau du joueur
	 * @param c Troisi�me bateau du joueur
	 * @param d Quatri�me bateau du joueur
	 * @param e Cinqui�me bateau du joueur
	 */
	public Joueur(String n, Grille att, Bateau a, Bateau b, Bateau c, Bateau d, Bateau e) {
		//Initialisation de la taille du tableau de bateaux tabb � 6 pour pouvoir stocker les 5 bateaux
		Bateau[] tabb = new Bateau[6];
		//Attribution des bateaux du joueur dans le tableau
		tabb[5] = e;
		tabb[4] = d;
		tabb[3] = c;
		tabb[2] = b;
		tabb[1] = a;
		//Attribution du nom du joueur � n
		this.nomJoueur = n;
		//Attribution de la grille du joueur � att
		this.grilleAtt = att;
		//Initialisation du nombre de bateaux � 0
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
	 * @param t Grille o� le joueur joue
	 * @param j Joueur qui attaque
	 * @param tabB Tableau stockant les bateaux appartenant au joueur
	 */
	public void attaquer(int x, int y, Grille t, Joueur j, Bateau[] tabB){
		//Si la position o� l'attaque est demand�e est accesible
		if (t.etreAccessible(x, y)) {
			//Si la case ne contient pas de bateaux
			if ((t.getTabGrille()[x][y].getOccupation() == 8) || (t.getTabGrille()[x][y].getOccupation() == 9)) {
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e mais qu'elle ne contient aucun bateau
				t.getTabGrille()[x][y].toucherCase();
				//Message dans la console
				System.out.println("Nous n'avons rien trouv� ici");
			} 
			//Si la case contient un bateau et qu'elle a d�ja �tait touch�e
			if((t.getTabGrille()[x][y].getOccupation() == 7)) {
				//Message dans la console
				System.out.println("Bateau d�ja touch�");
			}
			//Si la case contient le bateau de num�ro 5
			if (t.getTabGrille()[x][y].getOccupation() == 5) { 
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la m�thode subirAttaque pour perdre des points de vie au bateau touch�
				Bateau.subirAttaque(5, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a �t� touch�");
				//Appel de la m�thode coule pour v�rifier si le bateau est enti�rement et donc coule ou non
				Bateau.coule(5, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(5, tabB, j));
			}
			//Si la case contient le bateau de num�ro 4
			if (t.getTabGrille()[x][y].getOccupation() == 4) {
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la m�thode subirAttaque pour perdre des points de vie au bateau touch�
				Bateau.subirAttaque(4, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(4, tabB) + " a �t� touch�");
				//Appel de la m�thode coule pour v�rifier si le bateau est enti�rement et donc coule ou non
				Bateau.coule(4, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(4, tabB, j));
			}
			//Si la case contient le bateau de num�ro 3
			if (t.getTabGrille()[x][y].getOccupation() == 3) {
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la m�thode subirAttaque pour perdre des points de vie au bateau touch�
				Bateau.subirAttaque(3, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(3, tabB) + " a �t� touch�");
				//Appel de la m�thode coule pour v�rifier si le bateau est enti�rement et donc coule ou non
				Bateau.coule(3, tabB,j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(3, tabB, j));
			}
			//Si la case contient le bateau de num�ro 2
			if (t.getTabGrille()[x][y].getOccupation() == 2) {
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la m�thode subirAttaque pour perdre des points de vie au bateau touch�
				Bateau.subirAttaque(2, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a �t� touch�");
				//Appel de la m�thode coule pour v�rifier si le bateau est enti�rement et donc coule ou non
				Bateau.coule(2, tabB, j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(2, tabB, j));
			}
			//Si la case contient le bateau de num�ro 1
			if (t.getTabGrille()[x][y].getOccupation() == 1) {
				//La case se voit attribuer une valeur pour dire qu'elle est touch�e et qu'elle contient un bateau
				t.getTabGrille()[x][y].toucherBateau();
				//Appel de la m�thode subirAttaque pour perdre des points de vie au bateau touch�
				Bateau.subirAttaque(1, tabB);
				//Message dans la console
				System.out.println("le " + Bateau.getNomBateau(5, tabB) + " a �t� touch�");
				//Appel de la m�thode coule pour v�rifier si le bateau est enti�rement et donc coule ou non
				Bateau.coule(1, tabB, j);
				//Message dans la console
				System.out.println("vie :" + Bateau.getViePourcentage(1, tabB,  j));
			}
		}
	}
	
	/**
	 * Fonction qui r�initialise le nombre de bateaux � 5 pour recommencer la partie
	 *
	 */
	public void setNbBateaux() {
		this.nbBateaux = 5;
	}
	
	/**
	 * Fonction qui r�cup�re le nombre de bateaux encore non coul�s appartenant au joueur
	 *
	 */
	public int getNbBateaux() {
		return this.nbBateaux;
	}

	/**
	 * Fonction qui r�cup�re la grille o� le joueur joue
	 *
	 */
	public Grille getGrilleAtt() {
		return grilleAtt;
	}

	/**
	 * Fonction qui r�cup�re le nom du joueur
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
	 * Fonction qui r�cup�re les cha�nes qui repr�sentent la liste de bateaux tri�e par impact
	 *
	 */
	public String[] afficherParImpact() {
		//Initialisation d'un tableau de cha�nes � une taille de 5
		String[] tabTrieParImpact = new String[5];
		//Pour i allant de 0 � 5(non inclus), avec i allant de 1 en 1
		for (int i = 0 ; i < 5 ; i++){
			//Stockage des cha�nes dans le tableau
			tabTrieParImpact[i] = this.lBat.get(i).afficherNom() + " � " + this.lBat.get(i).getViee() +"% de vie";
		}
		return tabTrieParImpact;
	}
	
	/**
	 * Fonction qui r�cup�re les cha�nes qui repr�sentent la liste de bateaux tri�e par taille
	 *
	 */
	public String[] afficherParTaille() {
		//Initialisation d'un tableau de cha�nes � une taille de 5
		String[] tabTrieParTaille = new String[5];
		//Pour i allant de 0 � 5(non inclus), avec i allant de 1 en 1
		for (int i =0; i <5; i++){
			//Stockage des cha�nes dans le tableau
			tabTrieParTaille[i] = this.lBat.get(i).afficherNom() + " � une taille de " + this.lBat.get(i).getTaille();
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
	 * Fonction qui trie la liste de bateaux selon le type de tri demand�
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

