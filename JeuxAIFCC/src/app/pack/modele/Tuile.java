package app.pack.modele;

import java.io.Serializable;

/**
 * Class permettant de genener les tuiles
 * @author dark_d0g
 *
 */

public class Tuile implements Serializable {

    private boolean merged;
    //private boolean merge;
    private boolean precendant;

    private boolean aleatoire;
    //Valeur
    private int valeur;


    //Postion
    protected Position positionActuel;
    protected Position positionPasse;



    /**
     * 2er Constructeur
     * @param unePositionActuel Position
     * @param unePositionPasse Position
     * @param valeur int
     */
    public Tuile(Position unePositionActuel, Position unePositionPasse, int valeur) {
        this.positionActuel = unePositionActuel;
        this.positionPasse = unePositionPasse;
        this.valeur = valeur;
        this.aleatoire = false;
        this.precendant = false;
        this.merged = false;
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
     * @param sValeur int
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
     * @param unePosition Position
     */
    public void setPositionActuel(Position unePosition){this.positionActuel = unePosition;}
    /**
     * Setter de position passe
     * @return	unePosition Position
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
     * Si c'est un tuile precedant
     * @return boolean
     */
    public boolean isPrecedant() {
        return precendant;
    }

    /**
     * Setter precedant
     */
    public void setPrecendant(boolean precendant) {
        this.precendant = precendant;
    }
    /**
     * Si c'est une tuile aleatoire
     * @return boolean
     */
    public boolean isAleatoire() {
        return aleatoire;
    }

    /**
     * Setter dire que c'est une tuile aleatoire
     * @param  aleatoire boolean
     */
    public void setAleatoire(boolean aleatoire) {
        this.aleatoire = aleatoire;
    }

    /**
     * si c'est une tuile merge
     * @return boolean
     */
    public boolean isMerged() {
        return merged;
    }

    /**
     * Setter de tuile est merge
     * @param merged boolean
     */
    public void setMerged(boolean merged) {
        this.merged = merged;
    }
}