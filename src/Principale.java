import java.util.*;
import java.awt.*;
import javax.swing.*;


/**
 * Classe principale pour lancer le programme entier permettant de jouer � la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Principale {
	
	/**
	 * Fonction principale
	 * @param args
	 */
	public static void main(String[] args) {
		//Message dans la console : R�gles
		System.out.println("R�gles : " + "\n" + "Il y'a 5 bateaux, le but est de tous les couler. Il y'a :" + "\n" + "- un bateau de taille 1" + "\n" + "- un bateau de taille 2" + "\n" + "- un bateau de taille 3" + "\n" + "- un bateau de taille 4" + "\n" + "- un bateau de taille 5" + "\n" + "\n" + "La partie commence");
		
		//Initialisation d'une nouvelle fen�tre avec comme nom "Bataille Navale"
		JFrame f = new JFrame("Bataille Navale");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Initialisation du dessin permettant de repr�senter le jeu dans l'interface graphique
		DessinBataille d = new DessinBataille("Choisissez la taille en x et la taille en y de la grille.", "Avec la forme tailleX,tailleY s�par� d'une virgule", "", "", "", "", "", "", "", "", "");
		//Taille de d�part de la fen�tre � 2000 pixels en x et 1000 pixels en y
		f.setPreferredSize(new Dimension(2000, 1000));
		//Ajout du dessin dans la fen�tre
		f.setContentPane(d);
		f.pack();
		//Fonction permettant l'utilisation des auditeurs pour les touches de la souris
		d.requestFocusInWindow();
		//Fen�tre visible
		f.setVisible(true);
	}
}
