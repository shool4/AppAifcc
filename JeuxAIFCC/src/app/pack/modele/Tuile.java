package app.pack.modele;

/**
 * Class permettant de genener les tuiles
 * @author dark_d0g
 *
 */

public class Tuile {
	
	//Valeur
	private int valeur;
	//Postion
	private Position positionActuel;
	private Position positionPasse;
	
	/**
	 * 1er Constructeur
	 * @param posActuelY
	 * @param posActuelX
	 * @param valeur
	 */
	public Tuile(int posActuelY, int posActuelX, int valeur) {
		super();
		this.positionActuel = new Position(posActuelY, posActuelX);
		this.positionPasse = new Position(posActuelY, posActuelX);
		this.valeur = valeur;
	}
	/**
	 * 2er Constructeur
	 * @param position
	 * @param valeur
	 */
	public Tuile(Position position, int valeur) {
		super();
		this.positionActuel = position;
		this.positionPasse = position;
		this.valeur = valeur;
	}

    /**
     * 3eme Constructeur
     * @param position
     * @param valeur
     */
    public Tuile(Tuile tuile) {
        super();
        this.positionActuel = tuile.getPostionActuel();
        this.positionPasse = tuile.getPostionPasse();
        this.valeur = tuile.getValeur();
    }
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
	 * Setter valeur
	 * @return void
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
	 * @param	Position
	 * @return	void
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
	 * Permet de merged 2 tuiles
	 * @param 	uneTuille
	 * @return 	void
	 */
	public void merge(Tuile uneTuille){
		//Multiplication des valeurs des carres
		this.valeur = uneTuille.getValeur()*2;
		//Mise a jour de la position passe
		this.setPositionPasse(uneTuille.getPostionActuel());
		
	}



	
	
}