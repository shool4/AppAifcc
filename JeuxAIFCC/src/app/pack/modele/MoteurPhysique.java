package app.pack.modele;

import android.os.Environment;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class qui gere le moteur physique du jeux
 * @author dark_d0g
 *
 */
public class MoteurPhysique implements Serializable{

    private static final long serialVersionUID = 16062014;

    private Grille grille = null;
    //private ArrayList<Bitmap> tbBitmapCarre;
    private boolean activeAleatoire;
    private int nombreForWin = 2048;
    private int nombreDeTuilePartTour = 1;
    private boolean jeuxPerdant = false;
    public int score;

    /**
     * Construcuteur du moteur physique
     * @param uneTypeDePartie TypePartie
     */
    public MoteurPhysique(TypePartie uneTypeDePartie) {
        Log.i("test1", "*********************** START **********************");
        Log.v("test1", "PARTI EN MODE : "+uneTypeDePartie.name());
        Log.i("test1", "****************************************************");

        //GENERE LA TAILLE DE LA GRILLE
        grille = new Grille(uneTypeDePartie.getTailleY(),uneTypeDePartie.getTailleX());

        //LE NOMBRE POUR GAGNER LA PARTIE
        this.nombreForWin = uneTypeDePartie.getNombreForWinGame();
        //LE NOMBRE DE TUILE PART TOUR
        this.nombreDeTuilePartTour = uneTypeDePartie.getNombreAjoutTuilePartTour();
        this.activeAleatoire = uneTypeDePartie.isActiveAleatoire();
        //LE NOMBRE DAJOUT DE DEPART
        for (int i = 1; i <= uneTypeDePartie.getNombreAjoutTuileDepart(); i++) {

                this.grille.ajoutTuileAleatoire(this.activeAleatoire).setAleatoire(false);

        }
        //INITIALISATION DU SCORE A 0
        this.score = 0;

        grille.debog_Tableau();

    }
	
	
	/*
	 * 
	 ******************************************************************************************************************************
	 * GETTE - SETTER
	 ******************************************************************************************************************************
	 *
	 */
    /**
     * Getter de la grille
     * @return 	Grille
     */
    public  ArrayList<TuileGraphique> getGrilleGraphiqueDeTuileNonVide() {
        return this.grille.getTuileNonVide();
    }

    public  ArrayList<TuileGraphique> getGrilleGraphiqueDeTuile() {
        return this.grille.getTuile();
    }
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES METHODES
	 ******************************************************************************************************************************
	 *
	 */

    /**
     * Ajout un tuile aleatoiremenet
     *
     */
    public void ajoutTuileAleatoire() {

        if(this.grille.ajoutTuileAleatoire(this.activeAleatoire) == null) {
            Log.i("test1","---> MOUVEMENT IMPOSSIBLE GRILLE COMPLET <---");
        }
    }

    /**
     * Ajout d'une tuile bonus
     * public void ajoutTuileBonus() {}
     */


    /**
     * Ajout d'un tuile
     * @param uneTuile
     * 	public void ajoutTuile(TuileGraphique uneTuile) {this.grille.ajoutUneTuile(uneTuile);}
     */

	
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES MOUVEMENTS
	 ******************************************************************************************************************************
	 *
	 */

    /**
     * Deplacemencement a droite
     * @return ArrayList<TuileGraphique>
     */
    public ArrayList<TuileGraphique> droite() {
        //ArrayList<Tuile> arrayTuile = null;
        if (this.grille.chekcIsPossibleDroiteGauche(true)) {
            //  Log.i("test1"," --> "+this.grille.chekcIsPossibleDroiteGauche(true));
            this.grille.deplacementDroite();
            if (this.grille.getNombreTuileVide() > 0) {

                //Nombre de tuile ajout par tour
                for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
                    this.ajoutTuileAleatoire();
                }


            } else {
                Log.i("test1", "---> Pas deplacement pas possible");
            }
        }
        if (this.grille.isGameLost()) {
            jeuxPerdant = true;
        }
        if(this.grille.isGameWon(this.nombreForWin)) {

        }

        this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

    }

    /**
     * Deplacemencement a Gauche
     * @return ArrayList<TuileGraphique>
     */
    public ArrayList<TuileGraphique>  gauche(){
        //ArrayList<Tuile> arrayTuile = null;
        if(this.grille.chekcIsPossibleDroiteGauche(false)) {
            // Log.i("test1"," --> "+this.grille.chekcIsPossibleDroiteGauche(false));
            this.grille.deplacementGauche();
            if(this.grille.getNombreTuileVide() > 0) {

                //Nombre de tuile ajout par tour
                for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
                    this.ajoutTuileAleatoire();
                }

            }else {
                Log.i("test1", "---> Pas deplacement pas possible");
            }
        }
        if (this.grille.isGameLost()) {
            jeuxPerdant = true;
        }
        if(this.grille.isGameWon(this.nombreForWin)) {

        }

        this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

    }

    /**
     * Deplacemencement a Haut
     * @return ArrayList<TuileGraphique>
     */
    public ArrayList<TuileGraphique> haut() {
        //ArrayList<Tuile> arrayTuile = null;
        if (this.grille.chekcIsPossiblehHautBas(true)) {
            // Log.i("test1"," --> "+this.grille.chekcIsPossiblehHautBas(true));
            this.grille.deplacementHaut();
            if (this.grille.getNombreTuileVide() > 0) {

                //Nombre de tuile ajout par tour
                for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
                    this.ajoutTuileAleatoire();
                }

            } else {
                Log.i("test1", "---> Pas deplacement pas possible");
            }
        }
        if (this.grille.isGameLost()) {
            jeuxPerdant = true;
        }
        if(this.grille.isGameWon(this.nombreForWin)) {

        }


        this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

    }

    /**
     * Deplacemencement a Bas
     * @return ArrayList<TuileGraphique>
     */
    public ArrayList<TuileGraphique> bas() {
        //ArrayList<Tuile> arrayTuile = null;
        if (this.grille.chekcIsPossiblehHautBas(false)) {
            //Log.i("test1"," --> "+this.grille.chekcIsPossiblehHautBas(false));
            this.grille.deplacementBas();
            if (this.grille.getNombreTuileVide() > 0) {

                //Nombre de tuile ajout par tour
                for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
                    this.ajoutTuileAleatoire();
                }

            } else {
                Log.i("test1", "---> Pas deplacement pas possible");
            }
        }
        jeuxPerdant = this.grille.isGameLost();

        if(this.grille.isGameWon(this.nombreForWin)) {

        }

        this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

    }

    public int calculScore(){
        if(this.grille.getTuileMerge().size() != 0){
            for (TuileGraphique uneTuile : this.grille.getTuileMerge()) {
                this.score += uneTuile.getValeur()*2;
            }
        }
        return this.score;
    }

    public boolean isJeuxPerdant() {
        return jeuxPerdant;
    }
}
