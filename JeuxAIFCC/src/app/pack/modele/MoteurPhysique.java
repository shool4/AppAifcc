package app.pack.modele;

import android.util.Log;

import java.util.ArrayList;

/**
 * Class qui gere le moteur physique du jeux
 * @author dark_d0g
 *
 */
public class MoteurPhysique {

	private Grille grille = null;
	
	//private ArrayList<Bitmap> tbBitmapCarre;
	
	private int nombreForWin = 2048;
	private int nombreDeTuilePartTour = 1;
	
/**
 * Construcuteur du moteur physique
 * @param pView
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
		
		//LE NOMBRE DAJOUT DE DEPART
		for (int i = 1; i <= uneTypeDePartie.getNombreAjoutTuileDepart(); i++) {
			this.grille.ajoutTuileAleatoire().setAleatoire(false);

		}
		
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

        if(this.grille.ajoutTuileAleatoire() == null) {
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
	 * 
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
				Log.i("test1", "!!!! !!!! PERDU !!!! !!!!");
		}
		if(this.grille.isGameWon(this.nombreForWin)) {
			Log.i("test1", "!!!! !!!! YOU WIN !!!! !!!!");
		}
		

		this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

	}
	
	/**
	 * Deplacemencement a Gauche
	 * 
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
			Log.i("test1", "!!!! !!!! PERDU !!!! !!!!");
		}
		if(this.grille.isGameWon(this.nombreForWin)) {
			Log.i("test1", "!!!! !!!! YOU WIN !!!! !!!!");
		}
	


		
		this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();
		
		
	}
	
	/**
	 * Deplacemencement a Haut
	 * 
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
			Log.i("test1", "!!!! !!!! PERDU !!!! !!!!");
		}
		if(this.grille.isGameWon(this.nombreForWin)) {
			Log.i("test1", "!!!! !!!! YOU WIN !!!! !!!!");
		}
	

		this.grille.debog_Tableau();
        return this.grille.getTuileNonVide();

	}
	
	/**
	 * Deplacemencement a Bas
	 * 
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
		if (this.grille.isGameLost()) {

			Log.i("test1", "!!!! !!!! PERDU !!!! !!!!");
		}
		if(this.grille.isGameWon(this.nombreForWin)) {
			Log.i("test1", "!!!! !!!! YOU WIN !!!! !!!!");
		}

		this.grille.debog_Tableau();
		return this.grille.getTuileNonVide();
		
	}
	

	

}
