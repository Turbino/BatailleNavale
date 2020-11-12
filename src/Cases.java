
/**
 * Classe pour représenter la valeur d'une case d'une grille
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Cases {
	//Attribut d'entier pour donner une valeur entière à chaque case pour savoir ce qui l'occupe
	public int occupation;
	
	/**
	 * Constructeur vide de notre classe Cases pour initialiser l'attribut occupation
	 *
	 */
	public Cases() {
		//Attribut occupation initialisé à 8 : Case ne comprenant pas de bateaux et n'est pas touchée
		this.occupation = 8;
	}
	
	/**
	 * Fonction permettant d'attribuer une case à un bateau lors du placement
	 * 
	 */
	public void occupeBateauCase(int num) {
		//Attribut occupation initialisé au numero du bateau : Case comprenant un bateau de ce numéro et n'est pas touchée
		this.occupation = num;
	}
	
	/**
	 * Fonction permettant de dire quand la case est touchée et ne comporte pas de bateaux
	 * 
	 */
	public void toucherCase() {
		//Attribut occupation initialisé à 9 : Case ne comprenant pas de bateaux et est touchée
		this.occupation = 9 ;
	}
	
	/**
	 * Fonction permettant de rendre la case vide comme au départ
	 * 
	 */
	public void rendreVideCase() {
		//Attribut occupation réinitialisé à 8 : Case ne comprenant pas de bateaux et n'est pas touchée
		this.occupation = 8;
	}
	
	/**
	 * Fonction permettant de dire quand la case est touchée et comporte un bateau
	 * 
	 */
	public void toucherBateau() {
		//Attribut occupation initialisé à 7 : Case comprenant un bateau et est touchée
		this.occupation = 7;
	}
	
	/**
	 * Fonction permettant de retourner la valeur de l'attribut occupation
	 * 
	 */
	public int getOccupation() {
		return this.occupation;
	}
}
