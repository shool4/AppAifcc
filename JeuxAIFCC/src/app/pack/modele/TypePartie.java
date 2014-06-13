package app.pack.modele;

/**
 * Class enum pour creer des types de parties
 */
public enum TypePartie {

/*
 * 1 / Nombre de point pour gagner
 * 2 / Taille X
 * 3 / Taille Y
 * 4 / Nombre dajoute au depart
 * 5 / Nombre dajoute de tuile part tour
 * 
 */
/**
 *  ESAY : 2048, 4x4, 2 Tuile au depart, ajout 1 tuile par tour, tuile non aleatoire
 *  Description : jouer normal
 */
easyOne(2048,4,4,2,1,false),
/**
  *  ESAY : 2048, 4x4, 2 Tuile au depart, ajout 1 tuile par tour, tuile aleatoire
  *  Description : jouer normal
  */
easyTwo(2048,4,4,2,1,true),
/**
 * NORMAL : 2048, 4x4, 2 Tuile au depart, ajout 2 tuile par tour, tuile non aleatoire
 * Description : Jouer assez rapidement pour gagner
 */
normalOne(2048,4,4,2,2,false),
/**
  * NORMAL : 2048, 4x4, 2 Tuile au depart, ajout 2 tuile par tour, tuile non aleatoire
  * Description : Jouer assez rapidement pour gagner
  */
normalTwo(2048,4,4,2,2,true),
/**
 * HARD : 2048, 4x4, 10 Tuile au depart, ajout 2 tuile par tour
 * 
 */
hardOne(2048,4,4,10,4,false),

 /**
   * HARD : 2048, 4x4, 10 Tuile au depart, ajout 2 tuile par tour
   */
hardTwo(2048,4,4,10,4,true);

private final int nombreForWinGame;
private final int tailleX;
private final int tailleY;
private final int nombreAjoutTuileDepart;
private final int nombreAjoutTuilePartTour;
private final boolean activeAleatoire;

    /**
     * Constructeur
     * @param unNombreForWin int
     * @param unX int
     * @param unY int
     * @param unNombreAjoutTuileDepart int
     * @param unNombreAjoutTuilePartTour boolean
     * @param unActiveAleatoire boolean
     */
	TypePartie(int unNombreForWin, int unX, int unY, int unNombreAjoutTuileDepart,int unNombreAjoutTuilePartTour, boolean unActiveAleatoire) {
		this.nombreForWinGame = unNombreForWin;
		this.tailleX = unX;
		this.tailleY = unY;
		this.nombreAjoutTuileDepart = unNombreAjoutTuileDepart;
		this.nombreAjoutTuilePartTour = unNombreAjoutTuilePartTour;
        this.activeAleatoire = unActiveAleatoire;
	}

    /**
     * TypePartie
     * @param unNombreForWin int
     */
	TypePartie(int unNombreForWin) {
		this(unNombreForWin, 4, 4, 1, 1,true);
	}

    /**
     * getNombreAjoutTuilePartTour
     * @return int
     */
	public int getNombreAjoutTuilePartTour() {
		return nombreAjoutTuilePartTour;
	}

    /**
     * getNombreAjoutTuileDepart
     * @return int
     */
	public int getNombreAjoutTuileDepart() {
		return nombreAjoutTuileDepart;
	}

    /**
     * getNombreForWinGame
     * @return int
     */
	public int getNombreForWinGame() {
		return nombreForWinGame;
	}

    /**
     * getTailleX
     * @return int
     */
	public int getTailleX() {
		return tailleX;
	}

    /**
     * getTailleY
     * @return int
     */
	public int getTailleY() {
		return tailleY;
	}

    /**
     * isActiveAleatoire
     * @return boolean
     */
    public boolean isActiveAleatoire() {
        return activeAleatoire;
    }
}
