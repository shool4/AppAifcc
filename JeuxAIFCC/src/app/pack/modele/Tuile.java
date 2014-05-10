package app.pack.modele;

/**
 * Class permettant de genener les tuiles
 * @author dark_d0g
 *
 */

public class Tuile {


    private boolean aleatoire;
	//Valeur
	private int valeur;
	//Postion
	protected Position positionActuel;
	protected Position positionPasse;



	/**
	 * 2er Constructeur
	 * @param position
	 * @param valeur
	 */
	public Tuile(Position unePositionActuel, Position unePositionPasse, int valeur) {
		this.positionActuel = unePositionActuel;
		this.positionPasse = unePositionPasse;
		this.valeur = valeur;
        this.aleatoire = false;
	}

    /**
     * Constructeur
     * @param uneTuile
     */
   /* public Tuile(Tuile uneTuile) {
        this(uneTuile.getPostionActuel(),uneTuile.getPostionPasse(),uneTuile.getValeur());
    }*/


   /* public Tuile(int unePositionActuelY, int unePositionActuelX,int unePositionPasseY, int unePositionPasseX, int uneValeur) {

        this(
                new Position(unePositionActuelY, unePositionActuelX),
                new Position(unePositionPasseY, unePositionPasseX),
                uneValeur
        );
    }*/
	/*
	 * 
	 ******************************************************************************************************************************
	 * GETTE - SETTER
	 ******************************************************************************************************************************
	 *
	 */
	/**
	 * Getter valeur
	 * @return int
	 */
	public int getValeur() {return valeur;}

    /**
     * Setteur de valeur
     * @param sValeur
     */
	public void setValeur(int sValeur) {this.valeur = sValeur;}
	/**
	 * Getter de position actuel
	 * @return	Position
	 */
	public Position getPostionActuel() {return positionActuel;}
	/**
	 * Getter de position Passe
	 * @return	Position
	 */
	public Position getPostionPasse() {return positionPasse;}

    /**
     * Setter de position actuel
     * @param unePosition
     */
	public void setPositionActuel(Position unePosition){this.positionActuel = unePosition;}
	/**
	 * Setter de position passe
	 * @param	Position
	 * @return	Position
	 */
	public void setPositionPasse(Position unePosition){this.positionPasse = unePosition;}
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES METHODES
	 ******************************************************************************************************************************
	 *
	 */

    /**
     * merger les tuiles
     * @param uneTuille
     */
	public void merge(Tuile uneTuille){

		//Multiplication des valeurs des carres
		this.valeur = uneTuille.getValeur()*2;
		//Mise a jour de la position passe
		this.setPositionPasse(uneTuille.getPostionActuel());

	}




    public boolean isAleatoire() {
        return aleatoire;
    }

    public void setAleatoire(boolean aleatoire) {
        this.aleatoire = aleatoire;
    }

}