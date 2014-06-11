package app.pack.modele;

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
 *  ESAY : 128, 4x4, 2 Tuile au depart, ajout 1 tuile par tour, tuile aleatoire
 *  Description : jouer normal
 */
easy(128,4,4,2,1,true),
/**
 * NORMAL : 128, 4x4, 2 Tuile au depart, ajout 2 tuile par tour, 2 ajouter
 * Description : Jouer assez rapidement pour gagner
 */
normal(128,4,4,2,2,false),
/**
 * HARD : 128, 4x4, 10 Tuile au depart, ajout 2 tuile par tour 
 * 
 */
hard(2048,4,4,10,2,true);

private final int nombreForWinGame;
private final int tailleX;
private final int tailleY;
private final int nombreAjoutTuileDepart;
private final int nombreAjoutTuilePartTour;
private final boolean activeAleatoire;

	TypePartie(int unNombreForWin, int unX, int unY, int unNombreAjoutTuileDepart,int unNombreAjoutTuilePartTour, boolean unActiveAleatoire) {
		this.nombreForWinGame = unNombreForWin;
		this.tailleX = unX;
		this.tailleY = unY;
		this.nombreAjoutTuileDepart = unNombreAjoutTuileDepart;
		this.nombreAjoutTuilePartTour = unNombreAjoutTuilePartTour;
        this.activeAleatoire = unActiveAleatoire;
	}

	TypePartie(int unNombreForWin) {
		this(unNombreForWin, 4, 4, 1, 1,true);
	}

	public int getNombreAjoutTuilePartTour() {
		return nombreAjoutTuilePartTour;
	}

	public int getNombreAjoutTuileDepart() {
		return nombreAjoutTuileDepart;
	}

	public int getNombreForWinGame() {
		return nombreForWinGame;
	}

	public int getTailleX() {
		return tailleX;
	}

	public int getTailleY() {
		return tailleY;
	}

    public boolean isActiveAleatoire() {
        return activeAleatoire;
    }
}
