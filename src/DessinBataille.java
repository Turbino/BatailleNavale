  import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Classe pour dessiner l'interface graphique représentant le jeu de la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */

public class DessinBataille extends JPanel{
	//Attribut de la grille utilisée lors de la bataille navale
	private Grille g;
	//Attribut d'un tableau de bateaux qui représente les 5 bateaux utilisés lors de la bataille navale
	private Bateau[] tabB;
	//Attribut représentant le joueur qui va jouer à la bataille navale
	private Joueur j;
	//Attributs de chaînes pour l'interaction avec le joueur
	private String msg1Interface, msg2Interface, msg3Interface, msg4Interface, msg5Interface, msg6Interface, msg7Interface, msg8Interface, msg9Interface, msg10Interface, msg11Interface;
	//Attribut pour savoir à quel étape du jeu l'on est
	//etape = 1 : Choix de la taille en x et en y de la grille
	//etape = 2 : Placements des bateaux
	//etape = 3 : Choix d'une position en x et en y pour attaquer
	//etape = 4 : Jeu fini quand tous les bateaux sont coulés
	private int etape;
	//Attributs de chaînes qui vont stocker les valeurs données pour
	//la taille de la grille en x et en y séparées par une virgule
	//la position de l'attaque en x et en y séparées par une virgule
	private String resDemandeTailleGrille, resDemandePosAttaq;
	//Attributs de tableaux de chaînes qui vont permettre de décomposer les attributs
	//resDemandeTailleGrille et resDemandePosAttaq en enlevant les virgules
	private String[] resNbTailleGrille, resNbPosAttaq;
	//Attribut de tableau de valeurs stockant les longueurs des bateaux
	private int[] longueurBatInterface;
	//Attribut stockant le numéro du bateau sélectionné lors du placement
	private int batSelec;
	//Attribut de tableau de booleen stockant si le placement pour tel bateau a été effectué ou non
	private boolean[] modifFaite;
	//Attribut de tableau de boolean stockant si la position souhaitée lors du placement pour tel
	//bateau est horizontale ou non
	private boolean[] posHorizBat;
	//Attribut définissant le nombre de bateaux par joueur
	private final int nbBateaux = 5;
	//Attribut définissant le nombre de bateaux de taille 1, taille 2, taille 3, taille 4 et taille 5
	private final int[] longueurBat = {1,1,1,1,1};
	
	/**
	 * Constructeur de notre classe DessinBataille pour initialiser tous ces attributs
	 * @param msg1 Message 1 à dessiner à une certaine position pour l'intéractivité
	 * @param msg2 Message 2 à dessiner à une certaine position pour l'intéractivité
	 * @param msg3 Message 3 à dessiner à une certaine position pour l'intéractivité
	 * @param msg4 Message 4 à dessiner à une certaine position pour l'intéractivité
	 * @param msg5 Message 5 à dessiner à une certaine position pour l'intéractivité
	 * @param msg6 Message 6 à dessiner à une certaine position pour l'intéractivité
	 * @param msg7 Message 7 à dessiner à une certaine position pour l'intéractivité
	 * @param msg8 Message 8 à dessiner à une certaine position pour l'intéractivité
	 * @param msg9 Message 9 à dessiner à une certaine position pour l'intéractivité
	 * @param msg10 Message 10 à dessiner à une certaine position pour l'intéractivité
	 * @param msg11 Message 11 à dessiner à une certaine position pour l'intéractivité
	 */
	public DessinBataille(String msg1, String msg2, String msg3, String msg4, String msg5, String msg6, String msg7, String msg8, String msg9, String msg10, String msg11) {
		//Définition d'une grille de base, les valeurs des paramètres seront changés lors de la demande de la taille de la grille
		g = new Grille(10,10);
		//Définition d'un tableau de bateau de taille 6 pour stocker nos 5 bateaux
		this.tabB = new Bateau[6];
		//Nouveau bateau de taille 5 ayant le numéro 5 et comme nom : "Torpilleur"
		this.tabB[5] = new Bateau("Torpilleur", 5 , 5);
		//Nouveau bateau de taille 4 ayant le numéro 4 et comme nom : "Sous-marin"
		this.tabB[4] = new Bateau("Sous-marin", 4 , 4);
		//Nouveau bateau de taille 3 ayant le numéro 3 et comme nom : "Cuiracier"
		this.tabB[3] = new Bateau("Cuiracier", 3, 3);
		//Nouveau bateau de taille 2 ayant le numéro 2 et comme nom : "Porte-avion"
		this.tabB[2] = new Bateau("Porte-avion", 2, 2);
		//Nouveau bateau de taille 1 ayant le numéro 1 et comme nom : "Canonnier"
		this.tabB[1] = new Bateau("Canonnier", 1 ,1);  
		//Définition d'un joueur possédant les 5 précédents bateaux et comme nom "Julien"
		this.j = new Joueur("Julien", this.g, this.tabB[1], this.tabB[2], this.tabB[3], this.tabB[4], this.tabB[5]);
		//Définition des messages d'interactions avec le joueur
		this.msg1Interface = msg1;
		this.msg2Interface = msg2;
		this.msg3Interface = msg3;
		this.msg4Interface = msg4;
		this.msg5Interface = msg5;
		this.msg6Interface = msg6;
		this.msg7Interface = msg7;
		this.msg8Interface = msg8;
		this.msg9Interface = msg9;
		this.msg10Interface = msg10;
		this.msg11Interface = msg11;
		//Initialisation de l'attribut etape à 1 : Choix de la taille en x et en y de la grille
		this.etape = 1;
		//Initialisation de la chaînes pour demander la taille de la grille
		this.resDemandeTailleGrille = "";
		//Initialisation de la chaînes pour demander la position de l'attaque
		this.resDemandePosAttaq = "";
		//Initialisation de l'attribut batSelec à -1 pour dire qu'il n'y a aucun bateau sélectionné
		this.batSelec = -1;
		
		//Auditeur pour les touches du clavier
		KeyListener kl = new KeyListener() {
			public void keyTyped(KeyEvent e) {
			}
			public void keyReleased(KeyEvent e) {
			}
			
			/**
			 * Fonction s'activant quand une touche du clavier est pressée
			 * @param e Evenement représentant quand les touches de claviers sont pressées
			 */
			public void keyPressed(KeyEvent e) {
				//Initialisation d'une variable à la valeur entière représentée par les touches quand elles sont pressées
				int code = e.getKeyCode();
				//Initialisation d'une variable au caractère sur la touche appuyée
				char lettre = e.getKeyChar();
				
				//Appel de la fonction permettant d'entrer les valeurs de la taille de la grille en x et en y
				demandeTailleGrilleEtape1KeyPress(e, code);
				
				//Appel de la fonction permettant de changer le sens du bateau lors du placement
				changerSensBatEtape2KeyPress(e, lettre);
				
				//Appel de la fonction permettant d'entrer les valeurs de la position en x et en y de l'attaque
				demandePosAttaqEtape3KeyPress(e, code);
				
				//Appel de la fonction permettant d'afficher la liste des bateaux triée par impact ou par taille
				trierListeBatEtape3KeyPress(e, lettre);
			}
		};
		addKeyListener(kl);
		
		
		//Auditeur pour les touches de la souris
		addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
				
			}
			public void mouseEntered(MouseEvent e) {
				
			}
			public void mouseExited(MouseEvent e) {
				
			}
			public void mouseReleased(MouseEvent e) {
				
			}
			
			/**
			 * Fonction s'activant quand une touche de la souris est pressée
			 * @param e Evenement représentant quand les touches de la souris sont pressées
			 */
			public void mousePressed(MouseEvent e) {
				
				//Appel de la fonction permettant de réinitialiser la plupart des attributs pour recommencer la partie
				recommencementPartie(e);
				
				//Appel de la fonction permettant de réinitialiser ou valider le placement des bateaux
				reinitOuValidPlacementBat(e);
				
				//Appel de la fonction permettant de seléctionner les bateaux dans l'interface
				selectionBatInterface(e);
				
				//Appel de la fonction permettant de poser les bateaux sur la grille
				poseBatInterface(e);
				
			}
		});
	}
	
	/**
	 * Fonction dessinant l'interface graphique et s'actualisant lors d'un repaint()
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Initialisation d'une variable à la taille en x de l'interface graphique
		int w = getWidth();
		//Initialisation d'une variable à la taille en y de l'interface graphique - 200
		//200 est le nombre de pixels utilisées pour les messages d'interactivité avec le joueur
		int h = getHeight()-200;
		
		//Appel de la fonction permettant d'actualiser les messages d'interactivité avec le joueur
		//et la grille selon l'état des bateaux
		this.initDessin(g, w, h);
		
		//Appel de la fonction permettant d'afficher une case pour recommencer la partie
		this.recommencementPartieDessin(g, w, h);
		
		//Appel de la fonction permettant de faire les changements sur l'interface graphique lors
		//de la reinitialisation ou la validation du placement des bateaux
		this.reinitOuValidPoseBatDessin(g, w, h);
		
		//Appel de la fonction permettant de faire les changements sur l'interface graphique lors
		//de la sélection des bateaux
		this.selectionBatDessin(g, w, h);
		
		//Appel de la fonction permettant de faire les changements sur l'interface graphique lors
		//de la pose des bateaux
		this.poseBatDessin(g, w, h);
		
	}
	
	/**
	 * Fonction mettant à jouer le premier message d'interactivité avec le joueur
	 * @param msg1 Nouveau premier message d'interactivité avec le joueur
	 */
	public void setMsg1Interface(String msg1) {
		//Définition de l'attribut au paramètre msg1
		this.msg1Interface = msg1;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le deuxième message d'interactivité avec le joueur
	 * @param msg2 Nouveau deuxième message d'interactivité avec le joueur
	 */
	public void setMsg2Interface(String msg2) {
		//Définition de l'attribut au paramètre msg2
		this.msg2Interface = msg2;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le troisième message d'interactivité avec le joueur
	 * @param msg3 Nouveau trosième message d'interactivité avec le joueur
	 */
	public void setMsg3Interface(String msg3) {
		//Définition de l'attribut au paramètre msg3
		this.msg3Interface = msg3;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le quatrième message d'interactivité avec le joueur
	 * @param msg4 Nouveau quatrième message d'interactivité avec le joueur
	 */
	public void setMsg4Interface(String msg4) {
		//Définition de l'attribut au paramètre msg4
		this.msg4Interface = msg4;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le cinquième message d'interactivité avec le joueur
	 * @param msg5 Nouveau cinquième message d'interactivité avec le joueur
	 */
	public void setMsg5Interface(String msg5) {
		//Définition de l'attribut au paramètre msg5
		this.msg5Interface = msg5;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le sixième message d'interactivité avec le joueur
	 * @param msg6 Nouveau sixième message d'interactivité avec le joueur
	 */
	public void setMsg6Interface(String msg6) {
		//Définition de l'attribut au paramètre msg6
		this.msg6Interface = msg6;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le septième message d'interactivité avec le joueur
	 * @param msg7 Nouveau septième message d'interactivité avec le joueur
	 */
	public void setMsg7Interface(String msg7) {
		//Définition de l'attribut au paramètre msg7
		this.msg7Interface = msg7;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le huitième message d'interactivité avec le joueur
	 * @param msg8 Nouveau huitième message d'interactivité avec le joueur
	 */
	public void setMsg8Interface(String msg8) {
		//Définition de l'attribut au paramètre msg8
		this.msg8Interface = msg8;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le neuvième message d'interactivité avec le joueur
	 * @param msg9 Nouveau neuvième message d'interactivité avec le joueur
	 */
	public void setMsg9Interface(String msg9) {
		//Définition de l'attribut au paramètre msg9
		this.msg9Interface = msg9;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le dixième message d'interactivité avec le joueur
	 * @param msg10 Nouveau dixième message d'interactivité avec le joueur
	 */
	public void setMsg10Interface(String msg10) {
		//Définition de l'attribut au paramètre msg10
		this.msg10Interface = msg10;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction mettant à jouer le onzième message d'interactivité avec le joueur
	 * @param msg11 Nouveau onzième message d'interactivité avec le joueur
	 */
	public void setMsg11Interface(String msg11) {
		//Définition de l'attribut au paramètre msg11
		this.msg11Interface = msg11;
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction qui récupère la taille en x de la grille
	 */
	public int getTailleXGrille() {
		//Si l'attribut etape = 1
		if(this.etape == 1) {
			//retourne 0
			return 0;
		//Sinon
		} else {
			//retourne taille de x de la grille
			return this.g.getTailleX();
		}
	}
	
	/**
	 * Fonction qui récupère la taille en y de la grille
	 */
	public int getTailleYGrille() {
		//Si l'attribut etape = 1
		if(this.etape == 1) {
			//retourne 0
			return 0;
		//Sinon
		} else {
			//retourne taille de y de la grille
			return this.g.getTailleY();
		}
	}
	
	/**
	 * Fonction qui change le bateau sélectionné par le paramètre batSelec
	 * @param batSelec Nouveau bateau selectionné
	 */
	public void setBatSelec(int batSelec) {
		//Si le bateau sélectionné est le même que celui qu'on veut sélectionner
		if(this.batSelec == batSelec) {
			//On déselectionne le bateau
			this.batSelec = -1;
			//N'affiche rien comme troisième message
			this.setMsg3Interface("");
		//Sinon
		} else {
			//On change le bateau sélectionner par le paramètre batSelec
			this.batSelec = batSelec;
			//On actualise le troisième message
			this.setMsg3Interface("Position : Horizontale");
			//On lui attribue une position horizontale
			posHorizBat[batSelec] = true;
		}
		//Actualisation de l'interface graphique
		repaint();
	}
	
	/**
	 * Fonction qui permet la demande de la taille de la grille en x et en y
	 * @param e Evenement représentant quand les touches de claviers sont pressées
	 * @param code Valeur entière représentée par les touches quand elles sont pressées
	 */
	public void demandeTailleGrilleEtape1KeyPress(KeyEvent e, int code) {
		//Si la touche pressée est NUMPAD0 et que l'étape = 1
		if(code == e.VK_NUMPAD0 && etape == 1) {
			//Ajoute 0 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "0";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD1 et que l'étape = 1
		if(code == e.VK_NUMPAD1 && etape == 1) {
			//Ajoute 1 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "1";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD2 et que l'étape = 1
		if(code == e.VK_NUMPAD2 && etape == 1) {
			//Ajoute 2 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "2";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD3 et que l'étape = 1
		if(code == e.VK_NUMPAD3 && etape == 1) {
			//Ajoute 3 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "3";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD4 et que l'étape = 1
		if(code == e.VK_NUMPAD4 && etape == 1) {
			//Ajoute 4 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "4";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD5 et que l'étape = 1
		if(code == e.VK_NUMPAD5 && etape == 1) {
			//Ajoute 5 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "5";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD6 et que l'étape = 1
		if(code == e.VK_NUMPAD6 && etape == 1) {
			//Ajoute 6 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "6";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD7 et que l'étape = 1
		if(code == e.VK_NUMPAD7 && etape == 1) {
			//Ajoute 7 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "7";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD8 et que l'étape = 1
		if(code == e.VK_NUMPAD8 && etape == 1) {
			//Ajoute 8 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "8";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est NUMPAD9 et que l'étape = 1
		if(code == e.VK_NUMPAD9 && etape == 1) {
			//Ajoute 9 à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += "9";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est , et que l'étape = 1
		if(code == 44 && etape == 1) {
			//Ajoute , à la chaîne resDemandeTailleGrille
			resDemandeTailleGrille += ",";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est BACKSPACE et que l'étape = 1
		if(code == 8 && etape == 1) {
			//Reinitialisation de la chaîne resDemandeTailleGrille
			resDemandeTailleGrille = "";
			//On actualise le troisième message
			setMsg3Interface(resDemandeTailleGrille);
		}
		//Si la touche pressée est ENTER et que l'étape = 1
		if(code == e.VK_ENTER && etape == 1) {
			//Stockage des valeurs de resDemandeTailleGrille dans un tableau resNbTailleGrille en ne prenant pas compte des virgules
			resNbTailleGrille = resDemandeTailleGrille.split(",");
			//S'il n'y a bien que 2 valeurs rentrées et que les 2 valeurs soit supérieures à 6
			if(resNbTailleGrille.length == 2 && Integer.parseInt(resNbTailleGrille[0]) > 6 && Integer.parseInt(resNbTailleGrille[1]) > 6) {
				//Etape passe à 2 : Placements des bateaux 
				etape++;
				//Actualisation du premier message
				setMsg1Interface("Placements des bateaux");
				//Actualisation du deuxième message
				setMsg2Interface("Cliquer sur un bateau puis sur une case de la grille pour le positionner, appuyer sur 'r' pour le changer de sens");
				//Reinitialisation du troisième message
				setMsg3Interface("");
				//Définition de la taille x de la grille à la première valeur rentrée
				g.setTailleX(Integer.parseInt(resNbTailleGrille[0]));
				//Définition de la taille x de la grille à la deuxième valeur rentrée
				g.setTailleY(Integer.parseInt(resNbTailleGrille[1]));
				//Définition de la taille du tableau modifFaite au nombre de bateaux
				modifFaite = new boolean[nbBateaux];
				//Pour i de 0 à la taille du tableau modifFaite(non incluse), i augmentant de 1 en 1
				for(int i = 0 ; i < modifFaite.length ; i++) {
					//Modification n'est pas faite pour tous les bateaux
					modifFaite[i] = false;
				}
				//Actualisation de l'interface graphique
				repaint();
			} else {
				//On actualise le premier message pour annoncer l'erreur
				setMsg1Interface("Réessayez, il faut entrer des valeurs supérieures à 6 : Taille minimum de la grille : 7,7");
				//On actualise le deuxième message pour annoncer l'erreur
				setMsg2Interface("Avec la forme tailleX,tailleY séparé d'une virgule");
				//Reinitialisation de la chaîne resDemandeTailleGrille
				resDemandeTailleGrille = "";
				//On actualise le troisième message
				setMsg3Interface("");
			}
		}
	}
	
	/**
	 * Fonction qui permet de changer le sens d'un bateau lors de son placement
	 * @param e Evenement représentant quand les touches de claviers sont pressées
	 * @param lettre Caractère représenté par les touches quand elles sont pressées
	 */
	public void changerSensBatEtape2KeyPress(KeyEvent e, char lettre) {
		//Si etape = 2 et que la touche pressée est 'r'
		if(etape == 2 && lettre == 'r') {
			//Si la position du bateau est horizontale
			if(posHorizBat[batSelec] == true) {
				//Position du bateau devient verticale
				posHorizBat[batSelec] = false;
			//Sinon
			} else {
				//Position du bateau devient horizontale
				posHorizBat[batSelec] = true;
			}
			//Si la position du bateau est horizontale
			if(posHorizBat[batSelec] == true) {
				//Actualisation du troisième message
				setMsg3Interface("Position : Horizontale");
			//Sinon
			} else {
				//Actualisation du troisième message
				setMsg3Interface("Position : Verticale");
			}
		}
	}
	
	/**
	 * Fonction qui permet de trier les bateaux par impact ou par taille
	 * @param e Evenement représentant quand les touches de claviers sont pressées
	 * @param lettre Caractère représenté par les touches quand elles sont pressées
	 */
	public void trierListeBatEtape3KeyPress(KeyEvent e, char lettre) {
		//Si etape = 3 et que la touche pressée est 'r'
		if(etape == 3 && lettre == 'r') {
			//Trier la liste des bateaux par impact
			j.trier(1);
			//Actualisation de certains messages d'interactivité
			setMsg7Interface(j.afficherParImpact()[0]);
			setMsg8Interface(j.afficherParImpact()[1]);
			setMsg9Interface(j.afficherParImpact()[2]);
			setMsg10Interface(j.afficherParImpact()[3]);
			setMsg11Interface(j.afficherParImpact()[4]);
		}
		//Si etape = 3 et que la touche pressée est 't'
		if(etape == 3 && lettre == 't') {
			//Trier la liste des bateaux par taille
			j.trier(2);
			//Actualisation de certains messages d'interactivité
			setMsg7Interface(j.afficherParTaille()[0]);
			setMsg8Interface(j.afficherParTaille()[1]);
			setMsg9Interface(j.afficherParTaille()[2]);
			setMsg10Interface(j.afficherParTaille()[3]);
			setMsg11Interface(j.afficherParTaille()[4]);
		}
	}
	
	/**
	 * Fonction qui permet la demande de la position en x et en y de l'attaque
	 * @param e Evenement représentant quand les touches de claviers sont pressées
	 * @param code Valeur entière représentée par les touches quand elles sont pressées
	 */
	public void demandePosAttaqEtape3KeyPress(KeyEvent e, int code) {
		//Si la touche pressée est NUMPAD0 et que l'étape = 3
		if(code == e.VK_NUMPAD0 && etape == 3) {
			//Ajoute 0 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "0";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD1 et que l'étape = 3
		if(code == e.VK_NUMPAD1 && etape == 3) {
			//Ajoute 1 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "1";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD2 et que l'étape = 3
		if(code == e.VK_NUMPAD2 && etape == 3) {
			//Ajoute 2 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "2";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD3 et que l'étape = 3
		if(code == e.VK_NUMPAD3 && etape == 3) {
			//Ajoute 3 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "3";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD4 et que l'étape = 3
		if(code == e.VK_NUMPAD4 && etape == 3) {
			//Ajoute 4 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "4";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD5 et que l'étape = 3
		if(code == e.VK_NUMPAD5 && etape == 3) {
			//Ajoute 5 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "5";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD6 et que l'étape = 3
		if(code == e.VK_NUMPAD6 && etape == 3) {
			//Ajoute 6 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "6";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD7 et que l'étape = 3
		if(code == e.VK_NUMPAD7 && etape == 3) {
			//Ajoute 7 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "7";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD8 et que l'étape = 3
		if(code == e.VK_NUMPAD8 && etape == 3) {
			//Ajoute 8 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "8";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est NUMPAD9 et que l'étape = 3
		if(code == e.VK_NUMPAD9 && etape == 3) {
			//Ajoute 9 à la chaîne resDemandePosAttaq
			resDemandePosAttaq += "9";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est , et que l'étape = 3
		if(code == 44 && etape == 3) {
			//Ajoute , à la chaîne resDemandePosAttaq
			resDemandePosAttaq += ",";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est BACKSPACE et que l'étape = 3
		if(code == 8 && etape == 3) {
			//Reinitialisation de la chaîne resDemandePosAttaq
			resDemandePosAttaq = "";
			//On actualise le quatrième message
			setMsg4Interface(resDemandePosAttaq);
		}
		//Si la touche pressée est ENTER et que l'étape = 3
		if(code == e.VK_ENTER && etape == 3) {
			//Stockage des valeurs de resDemandePosAttaq dans un tableau resNbPosAttaq en ne prenant pas compte des virgules
			resNbPosAttaq = resDemandePosAttaq.split(",");
			//S'il n'y a bien que 2 valeurs rentrées et que les 2 valeurs soit supérieures à 0 et que la première valeur soit
			//inférieure à la taille en x de la grille et que la deuxième valeur soit inférieure à la taille en y de la grille
			if(resNbPosAttaq.length == 2 && Integer.parseInt(resNbPosAttaq[0]) >= 0 && Integer.parseInt(resNbPosAttaq[1]) >= 0 && Integer.parseInt(resNbPosAttaq[0]) < g.getTailleX() && Integer.parseInt(resNbPosAttaq[1]) < g.getTailleY()) {
				//Actualisation du premier message pour renouveler une attaque
				setMsg1Interface("Nouvelle attaque ! Case rouge : Case avec bateau touché  |  Case verte : Case touchée sans bateau");
				//Actualisation du deuxième message pour renouveler une attaque
				setMsg2Interface("Entrez la position en x et en y où vous voulez attaquer");
				//Actualisation du troisième message pour renouveler une attaque
				setMsg3Interface("Sous la forme positionX,positionY séparée d'une virgule");
				//Actualisation du quatrième message pour renouveler une attaque
				setMsg4Interface("");
				//Actualisation du cinquième message pour annoncer un éventuel tri possible
				setMsg5Interface("Options : Appuyez sur r pour trier les bateaux par impact");
				//Actualisation du sixième message pour annoncer un éventuel tri possible
				setMsg6Interface("       ou Appuyez sur t pour trier les bateaux par taille");
				//Reinitialisation des autres messages
				setMsg7Interface("");
				setMsg8Interface("");
				setMsg9Interface("");
				setMsg10Interface("");
				setMsg11Interface("");
				//Le joueur attaque aux coordonnées rentrées
				j.attaquer(Integer.parseInt(resNbPosAttaq[0]), Integer.parseInt(resNbPosAttaq[1]), g, j, tabB);
				//Reinitialisation de la chaîne resDemandePosAttaq
				resDemandePosAttaq = "";
				//Si tous les bateaux ont 0 de vie
				if(tabB[1].getViee() == 0 && tabB[2].getViee() == 0 && tabB[3].getViee() == 0 && tabB[4].getViee() == 0 && tabB[5].getViee() == 0) {
					//Initialisation de etape à 4 : Jeu fini
					etape++;
					//Actualisation du premier message pour annoncer la victoire
					setMsg1Interface("Vous avez gagné ! Partie finie");
					//Reinitialisation du deuxième message
					setMsg2Interface("");
					//Reinitialisation du troisième message
					setMsg3Interface("");
					//Reinitialisation du quatrième message
					setMsg4Interface("");
					//Reinitialisation du cinquième message
					setMsg5Interface("");
					//Reinitialisation du sixième message
					setMsg6Interface("");
					//Reinitialisation du septième message
					setMsg7Interface("");
				}
				//Actualisation de l'interface graphique
				repaint();
			//Sinon
			} else {
				//On actualise le premier message pour annoncer l'erreur
				setMsg1Interface("Réesayez, il faut entrer des valeurs entre 0 et " + g.getTailleX() + " en x et");
				//On actualise le deuxième message pour annoncer l'erreur
				setMsg2Interface("entre 0 et " + g.getTailleY() + " en y. Entrez la position en x et en y où vous voulez attaquer");
				//On actualise le troisième message pour annoncer l'erreur
				setMsg3Interface("Sous la forme positionX,positionY séparée d'une virgule");
				//On reinitialise le quatrième message
				setMsg4Interface("");
				//Reinitialisation de la chaîne resDemandePosAttaq
				resDemandePosAttaq = "";
			}
		}
	}
	
	/**
	 * Fonction qui permet le recommencement de la partie
	 * @param e Evenement représentant quand les touches de la souris sont pressées
	 */
	public void recommencementPartie(MouseEvent e) {
		//Si la souris est pressée dans l'intervalle en x entre la taille de l'interface graphique - 100 et la taille de l'interface graphique
		//et dans l'intervalle en y entre 0 et 50
		if(e.getX() > getWidth()-100 && e.getX() < getWidth() && e.getY() > 0 && e.getY() < 50) {
			//Reinitialisation de tous les attributs
			etape = 1;
			batSelec = -1;
			setMsg1Interface("Choisissez la taille en x et la taille en y de la grille.");
			setMsg2Interface("Avec la forme tailleX,tailleY séparé d'une virgule");
			setMsg3Interface("");
			setMsg4Interface("");
			setMsg5Interface("");
			setMsg6Interface("");
			setMsg7Interface("");
			setMsg8Interface("");
			setMsg9Interface("");
			setMsg10Interface("");
			setMsg11Interface("");
			resDemandeTailleGrille = "";
			resDemandePosAttaq = "";
			//Remet le nombre de bateaux à 5 s'il y en avait de couler dans la partie précédente
			j.setNbBateaux();
			//Pour i allant de 1 au nombre de bateaux compris, avec i allant de 1 en 1
			for(int i = 1 ; i <= nbBateaux ; i++) {
				//On remet la vie de départ aux bateaux
				tabB[i].setVie(tabB[i].getNumBateau());
			}
		}
	}
	
	/**
	 * Fonction qui permet la reinitialisation ou la validation du placement des bateaux
	 * @param e Evenement représentant quand les touches de la souris sont pressées
	 */
	public void reinitOuValidPlacementBat(MouseEvent e) {
		//Si etape = 2 : Placement des bateaux
		if(etape == 2) {
			//Si la souris est pressée dans l'intervalle en x entre 7/10 de la taille de la grille en x et 8/10 de la taille de la grille en x
			//et dans l'intervalle en y entre 160 et 190
			if(e.getX() > (int)((0.7)*this.g.getTailleX())*(int)(getWidth()/((this.g.getTailleX())+(0.8)*this.g.getTailleX())) && e.getX() < (int)((0.8)*this.g.getTailleX())*(int)(getWidth()/((this.g.getTailleX())+(0.8)*this.g.getTailleX())) && e.getY() > 160 && e.getY() < 190) {
				//Pour i de 0 au nombre de bateaux(non inclus), avec i allant de 1 en 1
				for(int i = 0 ; i < nbBateaux ; i++) {
					//Placement des bateaux non effectués
					modifFaite[i] = false;
					//Position des bateaux : horizontale
					posHorizBat[i] = true;
					//Pour k de 0 à la taille de la grille en x(non incluse), avec k allant de 1 en 1
					for(int k = 0 ; k < g.getTailleX() ; k++) {
						//Pour j de 0 à la taille de la grille en y(non incluse), avec j allant de 1 en 1
						for(int j = 0 ; j < g.getTailleY() ; j++) {
							//Reinitialisation de la grille vide
							g.getTabGrille()[k][j].rendreVideCase();
						}
					}
					//Actualisation de l'interface graphique
					repaint();
				}
			}
			//Si la souris est pressée dans l'intervalle en x entre 9/10 de la taille de la grille en x et 10/10 de la taille de la grille en x
			//et dans l'intervalle en y entre 160 et 190
			if(e.getX() > (int)((0.9)*this.g.getTailleX())*(int)(getWidth()/((this.g.getTailleX())+(0.8)*this.g.getTailleX())) && e.getX() < (this.g.getTailleX())*(int)(getWidth()/((this.g.getTailleX())+(0.8)*this.g.getTailleX())) && e.getY() > 160 && e.getY() < 190) {
				//Initialisation à true d'un booleen pour savoir si toutes les placements sont effectués
				boolean ttesModifsFaites = true;
				//Pour i allant de 0 à la taille du tableau modifFaite(nombre de bateaux)(non inclus), avec i allant de 1 en 1
				for(int i = 0 ; i < modifFaite.length ; i++) {
					//Si le placement est fait pour le bateau du numéro i
					if(modifFaite[i] == true) {
						//Garder le booleen true
					//Sinon
					} else {
						//Mettre le booleen false
						ttesModifsFaites = false;
					}
				}
				//Si tous les placements sont effectués
				if(ttesModifsFaites == true) {
					//Initialisation de etape à 3
					etape++;
					//Actualisation du premier message
					setMsg1Interface("Le partie commence !");
					//Actualisation du deuxième message
					setMsg2Interface("Entrez la position en x et en y où vous voulez attaquer");
					//Actualisation du troisième message
					setMsg3Interface("Sous la forme positionX,positionY séparée d'une virgule");
					//Actualisation de l'interface graphique
					repaint();
				}
			}
		}
	}
	
	/**
	 * Fonction qui permet la selection des bateaux dans l'interface pour le placement
	 * @param e Evenement représentant quand les touches de la souris sont pressées
	 */
	public void selectionBatInterface(MouseEvent e) {
		//Si etape = 2 : Placement des bateaux
		if(etape == 2) {
			//Pour i de 0 au nombre de bateaux(non inclus), avec i allant de 1 en 1
			for(int i = 0 ; i < nbBateaux ; i++) {
				//Pour j de 0 à la taille du tableau longueurBat(nombre de bateaux)(non inclus), avec i allant de 1 en 1
				for(int j = 0 ; j < longueurBat.length ; j++) {
					//Si le placement est effectué pour le bateau de numéro i
					if(modifFaite[i] == true) {
						//Impossible de le sélectionner
					//Sinon
					} else {
						//Si la souris est pressée dans l'intervalle en x entre la taille de la grille en x + 100 et la taille de la grille en x + 100 + taille du bateau(en pixels)
						//
						if(e.getX() > ((10)*getWidth())/18+100 && e.getX() < (((10)*getWidth())/18+100)+((getWidth()/(g.getTailleX()+(int)((0.8)*g.getTailleX())))*(longueurBatInterface[i]))-1 && e.getY() > 200+(i*((getHeight()-200)/g.getTailleY())+1) && e.getY() < 200+((i+1)*((getHeight()-200)/g.getTailleY())+1)) {
							//Change le bateau sélectionné
							setBatSelec(i);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Fonction qui permet la pose des bateaux pour le placement
	 * @param e Evenement représentant quand les touches de la souris sont pressées
	 */
	public void poseBatInterface(MouseEvent e) {
		//Si etape = 2 et aucun bateau selectionné
		if(etape == 2 && batSelec != -1) {
			//Pour i de 0 à la taille en x de la grille(non incluse), avec i allant de 1 en 1
			for(int i = 0 ; i < g.getTailleX() ; i++) {
				//Pour j de 0 à la taille en y de la grille(non incluse), avec j allant de 1 en 1
				for(int j = 0 ; j < g.getTailleY() ; j++) {
					//Si la souris est pressée dans l'intervalle en x entre la taille de la grille en x + 100 et la taille de la grille en x + 100 + taille du bateau(en pixels)
					//et dans l'intervalle en y entre 200 + (numero du bateau-1) * taille d'une case en y et 200 + numero du bateau * taille d'une case en y
					if(e.getX() > i*(getWidth()/(g.getTailleX()+(int)((0.8)*g.getTailleX())))+1 && e.getX() < ((i+1)*(getWidth()/(g.getTailleX()+(int)((0.8)*g.getTailleX())))+1) && e.getY() > 200+(j*((getHeight()-200)/g.getTailleY())+1) && e.getY() < 200+((j+1)*((getHeight()-200)/g.getTailleY())+1)){
						//Si la position du bateau selectionné est horizontale et que c'est possible de l'ajouter à l'endroit demandé en horizontale
						if(posHorizBat[batSelec] == true && g.ajouterBateau(i, j, i+longueurBatInterface[batSelec]-1, j, longueurBatInterface[batSelec]) == true) {
							//Ajout du bateau en horizontale
							g.ajouterBateau(i, j, i+longueurBatInterface[batSelec]-1, j, longueurBatInterface[batSelec]);
							//Placement du bateau effectué
							modifFaite[batSelec] = true;
							//Aucun bateau n'est sélectionné ensuite
							batSelec = -1;
							//Actualisation de l'interface graphique
							repaint();
						//Sinon si la position du bateau selectionné est verticale et que c'est possible de l'ajouter à l'endroit demandé en verticale
						} else if(posHorizBat[batSelec] == false && g.ajouterBateau(i, j, i, j+longueurBatInterface[batSelec]-1, longueurBatInterface[batSelec])== true) {
							//Ajout du bateau en verticale
							g.ajouterBateau(i, j, i, j+longueurBatInterface[batSelec]-1, longueurBatInterface[batSelec]);
							//Placement du bateau effectué
							modifFaite[batSelec] = true;
							//Aucun bateau n'est pas sélectionné ensuite
							batSelec = -1;
							//Actualisation de l'interface graphique
							repaint();
						}
					}
				}
			}
		}
	}
	
	/**
	 * Fonction qui permet l'initialisation du dessin principal, la grille, les messages interactifs
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 * @param w Taille en x de l'interface graphique
	 * @param h Taille en y de l'interface graphique - 200
	 */
	public void initDessin(Graphics g, int w, int h) {
		//Fond de l'interface graphique en couleur blanche
		this.setBackground(Color.white);
		//Initialisation d'une police d'ecriture "TimesRoman" en gras de taille (taille en x de l'interface graphique / 60)
		Font t = new Font("TimesRoman", Font.BOLD, w/60);
		//Couleur d'écriture noire
		g.setColor(Color.black);
		//Définition de la police à la police t
		g.setFont(t);
		//Ecriture du premier message en x = 30 et y = 40 
		g.drawString(this.msg1Interface, 30, 40);
		//Ecriture du deuxième message en x = 30 et y = 90 
		g.drawString(this.msg2Interface, 30, 90);
		//Ecriture du troisième message en x = 30 et y = 140 
		g.drawString(this.msg3Interface, 30, 140);
		//Ecriture du quatrième message en x = 30 et y = 190
		g.drawString(this.msg4Interface, 30, 190);
		//Ecriture du cinquième message en x = (int)(0.6*w) et y = 200+(int)(0.1*h) 
		g.drawString(this.msg5Interface, (int)(0.6*w), 200+(int)(0.1*h));
		//Ecriture du sixième message en x = (int)(0.6*w) et y = 200+(int)(0.2*h) 
		g.drawString(this.msg6Interface, (int)(0.6*w), 200+(int)(0.2*h));
		//Ecriture du septième message en x = (int)(0.6*w) et y = 200+(int)(0.3*h) 
		g.drawString(this.msg7Interface, (int)(0.6*w), 200+(int)(0.3*h));
		//Ecriture du huitième message en x = (int)(0.6*w) et y = 200+(int)(0.4*h) 
		g.drawString(this.msg8Interface, (int)(0.6*w), 200+(int)(0.4*h));
		//Ecriture du neuvième message en x = (int)(0.6*w) et y = 200+(int)(0.5*h) 
		g.drawString(this.msg9Interface, (int)(0.6*w), 200+(int)(0.5*h));
		//Ecriture du dixième message en x = (int)(0.6*w) et y = 200+(int)(0.6*h) 
		g.drawString(this.msg10Interface, (int)(0.6*w), 200+(int)(0.6*h));
		//Ecriture du onzième message en x = (int)(0.6*w) et y = 200+(int)(0.7*h) 
		g.drawString(this.msg11Interface, (int)(0.6*w), 200+(int)(0.7*h));
		//Si etape est différent de 1
		if(etape != 1) {
			//Pour i allant de 0 à la taille en x de la grille(non incluse), avec i allant de 1 en 1
			for(int i = 0 ; i < this.g.getTailleX() ; i++) {
				//Pour j allant de 0 à la taille en y de la grille(non incluse), avec j allant de 1 en 1
				for(int j = 0 ; j < this.g.getTailleY() ; j++) {
					//Si la case est libre
					if(this.g.getTabGrille()[i][j].occupation == 8) {
						//Couleur bleue
						g.setColor(Color.blue);
					//Sinon si la case est libre mais a été attaquée
					} else if(this.g.getTabGrille()[i][j].occupation == 9) {
						//Couleur verte
						g.setColor(Color.green);
					//Sinon si la case n'est pas libre et a été attaquée
					} else if(this.g.getTabGrille()[i][j].occupation == 7) {
						//Couleur rouge
						g.setColor(Color.red);
					//Sinon (il y a un bateau non attaqué)
					} else {
						//Couleur noire
						g.setColor(Color.black);
					}
					//Dessin d'une case
					g.fillRect(i*(int)(w/((this.g.getTailleX())+(0.8)*this.g.getTailleX()))+1, 200+(j*(h/this.g.getTailleY())+1), (int)(w/((this.g.getTailleX())+(0.8)*this.g.getTailleX()))-1, (h/this.g.getTailleY())-1);
				}
			}
			//Ecriture dans la console de la grille avec ces valeurs
			System.out.println(this.g);
		}
	}
	
	/**
	 * Fonction qui permet d'effectuer le dessin des touches pour reinitialiser ou valider le placement
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 * @param w Taille en x de l'interface graphique
	 * @param h Taille en y de l'interface graphique - 200
	 */
	public void recommencementPartieDessin(Graphics g, int w, int h) {
		//Couleur rouge
		g.setColor(Color.red);
		//Dessin d'une case pour recommencer une partie
		g.fillRect(w-100, 0, 100, 50);
		//Initialisation d'une police d'ecriture "TimesRoman" en gras de taille (taille en x de l'interface graphique / 60)
		Font t = new Font("TimesRoman", Font.BOLD, w/130);
		//Couleur d'écriture noire
		g.setColor(Color.black);
		//Définition de la police à la police t
		g.setFont(t);
		//Ecriture du message dans le bouton pour recommencer
		g.drawString("Recommencer", w-95, 30);
	}
	
	/**
	 * Fonction qui permet d'effectuer le dessin des touches pour reinitialiser ou valider le placement
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 * @param w Taille en x de l'interface graphique
	 * @param h Taille en y de l'interface graphique - 200
	 */
	public void reinitOuValidPoseBatDessin(Graphics g, int w, int h) {
		//Si etape = 2 : Placement des bateaux
		if(etape == 2) {
			//Couleur rouge
			g.setColor(Color.red);
			//Dessin d'une case pour reinitialiser le placement des bateaux
			g.fillRect((int)((0.7)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX()))), 160, w/18, 30);
			//Couleur verte
			g.setColor(Color.green);
			//Dessin d'une case pour valider le placement des bateaux
			g.fillRect((int)((0.9)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX()))), 160, w/18, 30);
			//Initialisation d'une police d'ecriture "TimesRoman" en gras de taille (taille en x de l'interface graphique / 60)
			Font t = new Font("TimesRoman", Font.BOLD, w/130);
			//Couleur d'écriture noire
			g.setColor(Color.black);
			//Définition de la police à la police t
			g.setFont(t);
			//Ecriture du message dans le bouton pour recommencer le placement
			g.drawString("Recommencer", (int)((0.7)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX())))+5, 170);
			g.drawString("Placement", (int)((0.7)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX())))+5, 185);
			//Ecriture du message dans le bouton pour valider le placement
			g.drawString("Valider", (int)((0.9)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX())))+5, 170);
			g.drawString("Placement", (int)((0.9)*(this.g.getTailleX()))*(w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX())))+5, 185);
		}
	}
	
	/**
	 * Fonction qui permet d'effectuer le dessin de la selection des bateaux de l'interface
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 * @param w Taille en x de l'interface graphique
	 * @param h Taille en y de l'interface graphique - 200
	 */
	public void selectionBatDessin(Graphics g, int w, int h) {
		//Si etape = 2 et qu'il n'y a aucun bateau sélectionné 
		if(this.etape == 2 && this.batSelec == -1) {
			//Initialisation d'un compteur à 0
			int compteur = 0;
			//Initialisation de la taille du tableau longueurBatInterface au nombre de bateaux(5)
			this.longueurBatInterface = new int[nbBateaux];
			//Initialisation de la taille du tableau posHorizBat au nombre de bateaux(5)
			this.posHorizBat = new boolean[nbBateaux];
			//Pour i allant de 0 à la taille du tableau longueurBat(non incluse), avec i allant de 1 en 1
			for(int i = 0 ; i < this.longueurBat.length ; i++) {
				//Pour j allant de 0 à longueur du bateau de numéro i(non incluse), avec j allant de 1 en 1
				for(int j = 0 ; j < this.longueurBat[i] ; j++) {
					//Recupere la taille de chaque bateau, ici 1,2,3,4,5
					this.longueurBatInterface[compteur] = i+1;
					//Incrémentation du compteur
					compteur++;
				}
			}
		}
	}
	
	/**
	 * Fonction qui permet d'effectuer le dessin de la pose des bateaux
	 * @param g Graphiques permettant de changer l'interface graphique et de dessiner
	 * @param w Taille en x de l'interface graphique
	 * @param h Taille en y de l'interface graphique - 200
	 */
	public void poseBatDessin(Graphics g, int w, int h) {
		//Si etape = 2 : Placement des bateaux
		if(this.etape == 2) {
			//Initialisation d'un compteur à 0
			int compteur = 0;
			//Initialisation de la taille du tableau longueurBatInterface au nombre de bateaux(5)
			this.longueurBatInterface = new int[this.nbBateaux];
			//Pour i allant de 0 à la taille du tableau longueurBat(non incluse), avec i allant de 1 en 1
			for(int i = 0 ; i < this.longueurBat.length ; i++) {
				//Pour j allant de 0 à longueur du bateau de numéro i(non incluse), avec j allant de 1 en 1
				for(int j = 0 ; j < this.longueurBat[i] ; j++) {
					//Recupere la taille de chaque bateau, ici 1,2,3,4,5
					this.longueurBatInterface[compteur] = i+1;
					//Si le numéro du bateau sélectionné = compteur
					if(this.batSelec == compteur) {
						//Couleur rouge pour dire que le bateau est sélectionné
						g.setColor(Color.red);
					//Sinon si le placement de ce bateau a déja été fait
					} else if(this.modifFaite[compteur] == true){
						//Couleur blanche pour effacer ce bateau
						g.setColor(Color.white);
					//Sinon
					} else {
						//Couleur noire pour dire que le bateau n'est pas sélectionné ni placé
						g.setColor(Color.black);
					}
					//Dessin des bateaux de l'interface avec la couleur correspondante
					g.fillRect(((10)*w)/18+100, 200+(compteur*(h/this.g.getTailleY()))+1, ((w/(this.g.getTailleX()+(int)((0.8)*this.g.getTailleX())))*(i+1))-1, (h/this.g.getTailleY())-1);
					//Incrémentation du compteur
					compteur++;
				}
			}
			//Ecriture dans la console de la grille avec ces valeurs
			System.out.println(this.g);
		}
	}
}
