
/**
 * Classe pour repr�senter la valeur d'une case d'une grille
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Cases {
	//Attribut d'entier pour donner une valeur enti�re � chaque case pour savoir ce qui l'occupe
	public int occupation;
	
	/**
	 * Constructeur vide de notre classe Cases pour initialiser l'attribut occupation
	 *
	 */
	public Cases() {
		//Attribut occupation initialis� � 8 : Case ne comprenant pas de bateaux et n'est pas touch�e
		this.occupation = 8;
	}
	
	/**
	 * Fonction permettant d'attribuer une case � un bateau lors du placement
	 * 
	 */
	public void occupeBateauCase(int num) {
		//Attribut occupation initialis� au numero du bateau : Case comprenant un bateau de ce num�ro et n'est pas touch�e
		this.occupation = num;
	}
	
	/**
	 * Fonction permettant de dire quand la case est touch�e et ne comporte pas de bateaux
	 * 
	 */
	public void toucherCase() {
		//Attribut occupation initialis� � 9 : Case ne comprenant pas de bateaux et est touch�e
		this.occupation = 9 ;
	}
	
	/**
	 * Fonction permettant de rendre la case vide comme au d�part
	 * 
	 */
	public void rendreVideCase() {
		//Attribut occupation r�initialis� � 8 : Case ne comprenant pas de bateaux et n'est pas touch�e
		this.occupation = 8;
	}
	
	/**
	 * Fonction permettant de dire quand la case est touch�e et comporte un bateau
	 * 
	 */
	public void toucherBateau() {
		//Attribut occupation initialis� � 7 : Case comprenant un bateau et est touch�e
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
