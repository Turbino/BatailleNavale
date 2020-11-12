import java.text.DecimalFormat;


/**
 * Classe pour représenter un bateau dans le jeu de la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Bateau {
	//Attribut de chaîne qui représente le nom du bateau
	private String nomBateau;
	//Attribut de valeur qui représente la taille du bateau
	private int tailleBateau;
	//Attribut de valeur qui représente la vie du bateau
	private int vie;
	//Attribut de valeur qui représente le numéro du bateau
	private int numBateau;
	//Attribut de valeur qui représente la vie du bateau en pourcentage
	public double impacts;
	
	/**
	 * Constructeur de notre classe Bateau pour initialiser tous ces attributs
	 * @param n Chaîne permettant d'attribuer un nom au bateau
	 * @param t Valeur qui va attribuer une taille au bateau
	 * @param num Valeur qui va représenter le bateau par un numéro
	 */
	public Bateau(String n, int t, int num) {
		//Initialisation du nom du bateau à n
		this.nomBateau = n;
		//Initialisation de la taille du bateau à t
		this.tailleBateau = t;
		//Initialisation de la vie du bateau à t
		this.vie = t;
		//Initialisation du numéro du bateau à num
		this.numBateau = num;
		//Initialisation de la vie en pourcentage du bateau à l'appel de la méthode getViee (100% au début)
		this.impacts = this.getViee();
	}
	
	/**
	 * Fonction qui retourne le nom du bateau
	 * @param num Numéro du bateau auquel on veut retourner son nom
	 * @param tabB Tableau stockant les bateaux crées
	 */
	public static String getNomBateau(int num, Bateau[] tabB ) {
		return tabB[num].nomBateau;
	}
	
	/**
	 * Fonction qui retourne la vie en pourcentage du bateau
	 * @param num Numéro du bateau auquel on veut retourner sa vie en pourcentage
	 * @param tabB Tableau stockant les bateaux crées
	 * @param j Joueur auquel appartient les bateaux
	 * @exception ArithmeticException qui empêche la division par 0
	 */
	public static String getViePourcentage(int num , Bateau[] tabB, Joueur j ) {
		//Initialisation d'une valeur à la la vie du bateau
		double a = tabB[num].vie ;
		//Initialisation d'une valeur à la taille du bateau
		double b = tabB[num].tailleBateau ;
		//Initialisation du résultat final à 0
		double resultatFinal = 0;
		try {
			//Initialisation d'un résultat intermédiaire à la vie du bateau/la taille du bateau
			double resultat = a/b;
			//Resultat final = resultat intermédiaire * 100
			resultatFinal = resultat*100;
		//Exception arithmétique si la division se fait par 0
		} catch(ArithmeticException e) {
			//Message de la console
			System.out.println("Division par 0 interdite");
		}
		//Renvoie la valeur du résultat sous une forme décimale au centième
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(resultatFinal) + " %";
	}
	
	/**
	 * Fonction qui retourne la taille du bateau
	 * 
	 */
	public int getTaille() {
		return this.tailleBateau;
	}
	
	/**
	 * Fonction qui retourne la vie en pourcentage du bateau
	 * @exception ArithmeticException sert à éviter une division par 0
	 */
	public int getViee() {
		//Initialisation d'une valeur à la vie du bateau
		int a = this.vie ;
		//Initialisation d'une valeur à la taille du tableau
		int b = this.tailleBateau ;
		//Initialisation du résultat à 0
		int resultat=0;
		try {
			//Résultat = 100*(la vie du bateau/la taille du bateau) 
			resultat = (100*a/b);
			//Exception arithmétique si la division se fait par 0
		} catch(ArithmeticException e) {
			//Message de la console
			System.out.println("Division par 0 interdite");
		};
        return resultat;
	}
	
	/**
	 * Fonction qui fait subir une attaque à un bateau
	 * @param num Numéro du bateau auquel on veut faire subir une attaque
	 * @param tabB
	 */
	public static void subirAttaque(int num, Bateau[] tabB) {
		//Vie du bateau baisse de 1
		tabB[num].vie -= 1;
	}
	
	/**
	 * Fonction qui retourne le nom du bateau
	 * 
	 */
	public String afficherNom() {
		return this.nomBateau;
	}
	
	/**
	 * Fonction qui retourne la vie en pourcentage du bateau
	 * @param num Numéro du bateau qu'on veut couler
	 * @param tabB Tableau stockant les bateaux du joueur
	 * @param j Joueur à qui appartient le bateau que l'on veut couler
	 */
	public static void coule(int num, Bateau[] tabB, Joueur j) {
		if (tabB[num].vie == 0) {
			System.out.println(tabB[num].nomBateau  + " a été coulé");
			j.bateauCoule();
			System.out.println("Nombre de bateaux restants : "  +j.getNbBateaux());
		}
	}
	
	/**
	 * Fonction qui retourne la vie du bateau
	 * 
	 */
	public int getVie() {
		return this.vie;
	}
	
	/**
	 * Fonction qui retourne le numéro du bateau
	 * 
	 */
	public int getNumBateau() {
		return this.numBateau;
	}
	
	/**
	 * Fonction qui reinitialise la vie du bateau par le paramètre vie
	 * @param vie Vie que l'on veut que l'on bateau ait
	 */
	public void setVie(int vie) {
		this.vie = vie;
	}
}