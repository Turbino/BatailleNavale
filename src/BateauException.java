
/**
 * Classe pour représenter l'exception pour les bateaux lors de l'accessibilité
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class BateauException extends Exception {
	/**
	 * Constructeur de notre classe BateauException
	 * @param m Chaîne renvoyée lors de l'exception
	 */
	public BateauException(String m) {
		//Renvoi d'une exception par la chaîne m 
		super(m);
	}

}
