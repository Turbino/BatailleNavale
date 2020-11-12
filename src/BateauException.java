
/**
 * Classe pour repr�senter l'exception pour les bateaux lors de l'accessibilit�
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class BateauException extends Exception {
	/**
	 * Constructeur de notre classe BateauException
	 * @param m Cha�ne renvoy�e lors de l'exception
	 */
	public BateauException(String m) {
		//Renvoi d'une exception par la cha�ne m 
		super(m);
	}

}
