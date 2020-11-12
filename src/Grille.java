
/**
 * Classe pour représenter une grille pour le jeu de la bataille navale
 * 
 * @author JulienCayla
 * @author DonatienLevreyGalland
 */
public class Grille {
	//Attribut qui représente la taille en x de la grille
	private int tailleX;
	//Attribut qui représente la taille en y de la grille
	private int tailleY;
	//Attribut de tableau à 2 dimensions de cases qui va représenter la grille
	private Cases[][] tabGrille;
	//Attribut de tableau qui stocke les bateaux présents dans la grille
	private Bateau[] tabB ;
	
	/**
	 * Constructeur de notre classe Joueur pour initialiser tous ces attributs
	 * @param tx Valeur permattant d'attribuer la taille de la grille en x
	 * @param ty Valeur permettant d'attribuer la taille de la grille en y
	 */
	public Grille(int tx, int ty) {
		//Initialisation de la taille en x de la grille à tx
		this.tailleX = tx;
		//Initialisation de la taille en y de la grille à ty
		this.tailleY = ty;
		//Initialisation de la taille du tableau de cases à la taille de la grille en x et en y
		this.tabGrille = new Cases[tailleX][tailleY];
		//Pour i allant de 0 à la taille de la grille en x(non inclus), avec i allant de 1 en 1
		for(int i = 0 ; i < this.tailleX ; i++) {
			//Pour j allant de 0 à la taille de la grille en y(non inclus), avec j allant de 1 en 1 
			for(int j = 0 ; j < this.tailleY ; j++) {
				//Initialisation de toutes les cases à des cases vides
				this.tabGrille[i][j] = new Cases();
			}
		}
	}
	
	/**
	 * Fonction qui récupère la taille en x de la grille
	 *
	 */
	public int getTailleX() {
		return this.tailleX;
	}
	
	/**
	 * Fonction qui récupère la taille en y de la grille
	 *
	 */
	public int getTailleY() {
		return this.tailleY;
	}
	
	/**
	 * Fonction qui initialise la taille en x de la grille à x, puis change la taille du tableau de cases en fonction et recrée un tableau de cases vides
	 * @param x Nouvelle taille en x de la grille
	 */
	public void setTailleX(int x) {
		//Initialisation de la taille en x de la grille à x
		this.tailleX = x;
		//Nouvelle taille du tableau de cases
		this.tabGrille = new Cases[tailleX][tailleY];
		//Pour i allant de 0 à la taille de la grille en x(non inclus), avec i allant de 1 en 1
		for(int i = 0 ; i < this.tailleX ; i++) {
			//Pour j allant de 0 à la taille de la grille en y(non inclus), avec j allant de 1 en 1 
			for(int j = 0 ; j < this.tailleY ; j++) {
				//Initialisation de toutes les cases à des cases vides
				this.tabGrille[i][j] = new Cases();
			}
		}
	}
	
	/**
	 * Fonction qui initialise la taille en y de la grille à y, puis change la taille du tableau de cases en fonction et recrée un tableau de cases vides
	 * @param y Nouvelle taille en y de la grille
	 */
	public void setTailleY(int y) {
		//Initialisation de la taille en y de la grille à y
		this.tailleY = y;
		//Nouvelle taille du tableau de cases
		this.tabGrille = new Cases[tailleX][tailleY];
		//Pour i allant de 0 à la taille de la grille en x(non inclus), avec i allant de 1 en 1
		for(int i = 0 ; i < this.tailleX ; i++) {
			//Pour j allant de 0 à la taille de la grille en y(non inclus), avec j allant de 1 en 1 
			for(int j = 0 ; j < this.tailleY ; j++) {
				//Initialisation de toutes les cases à des cases vides
				this.tabGrille[i][j] = new Cases();
			}
		}
	}
	
	/**
	 * Fonction qui récupère le tableau à 2 dimensions de cases
	 *
	 */
	public Cases[][] getTabGrille(){
		return this.tabGrille;
	}
	
	/**
	 * Fonction qui dit si l'emplacement donné par les paramètres est accessible
	 * @param x Position en x de la grille où l'on veut savoir si c'est accessible
	 * @param y Position en y de la grille où l'on veut savoir si c'est accessible
	 * @exception BateauException sert à éviter le fait où l'on place un bateau en dehors de la grille
	 */
	public boolean etreAccessible(int x, int y) {
		//Initialisation d'un boolean accessible à true
		boolean accessible = true;
		try {
			//Si x et y sont supérieurs à 0 et que x est inférieur à la taille de la grille en x et que y est inférieur à la taille de la grille en y
			if(x < 0 || x >= this.tailleX || y < 0 || y >= this.tailleY) {
				//La position n'est pas accessible, booleen = false
				accessible = false;
				//Exception de BateauException qui renvoie le message suivant
				throw new BateauException ("coordonnées en x inférieures à 0 ou supérieures à " + this.tailleX + " ou en y supérieures à 0 ou inférieures à "+ this.tailleY);
			}
		//Exception de BateauException qui renvoie le message précédent
		}catch (BateauException e) {
			//Message de la console
			System.out.println(e);
		}
		return accessible;
	}
	
	/**
	 * Fonction qui dit si l'on peut ajouter un bateau avec les paramètres donnés
	 * @param xdep Position en x du début du bateau
	 * @param ydep Position en y du début du bateau
	 * @param xfin Position en x de la fin du bateau
	 * @param yfin Position en y de la fin du bateau
	 * @param num Numéro du bateau que l'on souhaite ajouter
	 */
	public boolean ajouterBateau(int xdep, int ydep, int xfin, int yfin, int num){
		//Initialisation d'un boolean ajoutReussi à true
		boolean ajoutReussi = true;
		//Si la place est disponible
		if (this.placeDispo(xdep, ydep, xfin, yfin) == true){
			//Si le bateau que l'on souhaite ajouter à la bonne taille
			if (this.bonneTaille(xdep, ydep, xfin, yfin, num)) {
				//Initialisation de 2 variables yc et xc
				int yc, xc;
				//Information comme quoi la case de départ du bateau est maintenant occupée par lui
				this.tabGrille[xdep][ydep].occupeBateauCase(num);
				//Information comme quoi la case de fin du bateau est maintenant occupée par lui
				this.tabGrille[xfin][yfin].occupeBateauCase(num);
				//Si la position en x de départ et de fin sont égales(Bateau vertical)
				if (xdep == xfin) {
					//Si la position en y de départ est supérieure à celle de fin
					if (ydep > yfin) {
						//Stockage de la position en y de depart dans yc
						yc = ydep;
						//Tant que yc et la position en y de fin sont différentes
						while (yc != yfin) {
							//La case en position en x de départ et en position en y de yc est occupée par lui
							this.tabGrille[xdep][yc].occupeBateauCase(num);
							//Décrémentation de yc
							yc -= 1;
						}
					//Sinon
					} else {
						//Stockage de la position en y de fin dans yc
						yc = yfin;
						//Tant que yc et la position en y de départ sont différentes
						while (yc != ydep) {
							//La case en position en x de départ et en position en y de yc est occupée par lui
							this.tabGrille[xdep][yc].occupeBateauCase(num);
							//Décrémentation de yc
							yc -= 1;
						}
					}
				}
				//Si la position en y de départ et de fin sont égales(Bateau horizontal)
				if (ydep == yfin) {
					//Si la position en x de départ est supérieure à celle de fin
					if (xdep > xfin) {
						//Stockage de la position en x de depart dans xc
						xc = xdep;
						//Tant que xc et la position en x de fin sont différentes
						while (xc != xfin) {
							//La case en position en x de xc et en position en y de départ est occupée par lui
							this.tabGrille[xc][ydep].occupeBateauCase(num);
							//Décrémentation de xc
							xc -= 1;
						}
					//Sinon
					} else {
						//Stockage de la position en x de fin dans xc
						xc = xfin;
						//Tant que xc et la position en x de départ sont différentes
						while (xc != xdep) {
							//La case en position en x de xc et en position en y de départ est occupée par lui
							this.tabGrille[xc][ydep].occupeBateauCase(num);
							//Décrémentation de xc
							xc-=1;
						}
					}
				}
			//Sinon
			} else {
				//Message de la console pour le refus de l'éxécution de la méthode
				System.out.println("le bateau " + num + " n'a pas pu etre posé : la taille du bateau doit etre de " + num);
				//Booleen = false
				ajoutReussi = false;
			}
		}else {
			//Message de la console pour le refus de l'éxécution de la méthode
			System.out.println("le bateau " + num + " n'a pas pu etre posé : un autre bateau occupe déja l'espace");
			//Booleen = false
			ajoutReussi = false;
		}
		return ajoutReussi;
	}
	
	/**
	 * Fonction permettant d'afficher la grille avec les valeurs de ses cases dans la console pour une meilleure visualisation du déroulement du programme
	 * 
	 */
	public String toString() {
		//Initialisation d'une chaîne temporaire
		String tmp = "";
		//Initialisation d'une chaîne pour faire le repère du haut
		String repereHaut = "";
		//Pour i allant de 0 à la taille en y de la grille(non incluse), avec i allant de 1 en 1
		for(int i = 0 ; i < this.tailleY ; i++) {
			//Si i est supérieur ou égal à 10
			if(i >= 10) {
				//Ajout d'un retour à la ligne et du nombre i et d'un | pour espacer les valeurs dans la variable temporaire
				tmp += "\n" + i + "| ";
			//Sinon (différence : un espace en plus)
			} else {
				//Ajout d'un retour à la ligne et du nombre i et d'un | pour espacer les valeurs dans la variable temporaire
				tmp += "\n" + i + " | ";
			}
			//Reinitialisation de la chaîne pour faire le repère du haut
			repereHaut = "";
			//Pour j allant de 0 à la taille en x de la grille(non incluse), avec j allant de 1 en 1
			for(int j = 0 ; j < this.tailleX ; j++) {
				//Si j est supérieur ou égal à 10
				if(j >= 10) {
					//Ajout du nombre i et d'un | pour espacer les valeurs dans le repère du haut
					repereHaut += j + "| ";
				//Sinon (différence : un espace en plus)
				} else {
					//Ajout du nombre i et d'un | pour espacer les valeurs dans le repère du haut
					repereHaut += j + " | ";
				}
				//Ajout de la valeur de la case en position j en x et i en y dans la variable temporaire
				tmp += this.tabGrille[j][i].occupation + " | ";
			}
		}
		//Initialisation du résultat qui est le repère du haut + la variable temporaire
		String res = "\n" + "  | " + repereHaut + tmp;
		return res;
	}
		
	/**
	 * Fonction qui dit si la place est disponible pour placer un bateau
	 * @param xdep Position en x du début du bateau
	 * @param ydep Position en y du début du bateau
	 * @param xfin Position en x de la fin du bateau
	 * @param yfin Position en y de la fin du bateau
	 */
	public boolean placeDispo(int xdep, int ydep, int xfin, int yfin){
		//Initialisation de 2 variables yc et xc
		int yc, xc;
		//Initialisation d'un booleen dispo = true
		boolean dispo = true;
		//Si la case de départ et de fin du bateau ne sont vides 
		if ((this.tabGrille[xdep][ydep].occupation != 8)||(this.tabGrille[xfin][yfin].occupation != 8)) {
			//Booleen = false
			dispo = false;
		}
		//Si la position en x de départ et de fin sont égales(Bateau vertical)
		if (xdep == xfin) {
			//Si la position en y de départ est supérieure à celle de fin
			if (ydep > yfin) {
				//Stockage de la position en y de depart dans yc
				yc = ydep;
				//Tant que yc et la position en y de fin sont différentes
				while (yc != yfin) {	
					//Si la case de position en x de départ et en y de yc est vide
					if (this.tabGrille[xdep][yc].occupation == 8) {
						//Décrementation de yc
						yc -= 1;
					} else {
						//Booleen = false
						dispo = false;
						//Décrementation de yc
						yc -= 1;
					}
				}
			} else {
				//Stockage de la position en y de fin dans yc
				yc = yfin;
				//Tant que yc et la position en y de départ sont différentes
				while (yc != ydep) {
					//Si la case de position en x de départ et en y de yc est vide
					if (this.tabGrille[xdep][yc].occupation == 8) {
						//Décrementation de yc
						yc -= 1;
					} else {
						//Booleen = false
						dispo = false;
						//Décrementation de yc
						yc -= 1;
					}
				}
			}
		}
		//Si la position en y de départ et de fin sont égales(Bateau horizontal)
		if (ydep == yfin) {
			//Si la position en x de départ est supérieure à celle de fin
			if (xdep > xfin) {
				//Stockage de la position en x de depart dans xc
				xc = xdep;
				//Tant que xc et la position en x de fin sont différentes
				while (xc != xfin) {
					//Si la case de position en x de xc et en y de départ est vide
					if(this.tabGrille[xc][ydep].occupation == 8) {
						//Décrementation de xc
						xc -= 1;
					} else {
						//Booleen = false
						dispo = false;
						//Décrementation de xc
						xc -= 1;
					}
				}
			} else {
				//Stockage de la position en x de fin dans xc
				xc = xfin;
				//Tant que xc et la position en x de départ sont différentes
				while (xc != xdep) {
					//Si la case de position en x de xc et en y de départ est vide
					if(this.tabGrille[xc][ydep].occupation == 8) {
						//Décrementation de xc
						xc -= 1;
					} else {
						//Booleen = false
						dispo = false;
						//Décrementation de xc
						xc -= 1;
					}
				}
			}
		}
		return dispo;
	}
	
	/**
	 * Fonction qui dit si le bateau que l'on souhaite placer est de bonne taille
	 * @param xdep Position en x du début du bateau
	 * @param ydep Position en y du début du bateau
	 * @param xfin Position en x de la fin du bateau
	 * @param yfin Position en y de la fin du bateau
	 * @param taille Taille que le bateau doit avoir
	 */
	public boolean bonneTaille(int xdep, int ydep, int xfin, int yfin, int taille) {
		//Initialisation d'un booleen res = false
		boolean res = false;
		//Initialisation d'une valeur tailleTest
		int tailleTest;
		//Si la position en x de départ et de fin sont égales(Bateau vertical)
		if (xdep == xfin) {
			//Si la position en y de départ est supérieure à celle de fin
			if (ydep > yfin) {
				//Initialisation de tailleTest à ydep-yfin+1
				tailleTest = ydep-yfin + 1;
			//Sinon
			} else {
				//Initialisation de tailleTest à yfin-ydep+1
				tailleTest = yfin-ydep +1; 
			}	
		//Sinon
		} else {
			//Si la position en x de départ est supérieure à celle de fin
			if (xdep > xfin ) {
				//Initialisation de tailleTest à xdep-xfin+1
				tailleTest = xdep -xfin +1;
			//Sinon
			} else {
				//Initialisation de tailleTest à yfin-ydep+1
				tailleTest = xfin-xdep +1;
			}
		}
		//Si tailleTest = taille
		if (tailleTest == taille) {
			//Boolean = true
			res = true;
		}
		return res ;
	}
	
	/**
	 * Fonction qui récupère le tableau stockant les bateaux
	 *
	 */
	public Bateau[] getTabB() {
		return tabB;
	}

	/**
	 * Fonction qui reinitialise le tableau stockant les bateaux
	 * @param tabB Tableau stockant les bateaux
	 */
	public void setTabB(Bateau[] tabB) {
		this.tabB = tabB;
	}
}	