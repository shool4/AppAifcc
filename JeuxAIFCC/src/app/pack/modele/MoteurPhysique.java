package app.pack.modele;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Class qui gere le moteur physique du jeux
 * @author dark_d0g
 *
 */
public class MoteurPhysique {

	private Grille grille = null;
	
	private ArrayList<Bitmap> tbBitmapCarre;
	
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
			this.grille.ajoutTuileAleatoire();
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
	public  ArrayList<Tuile> getGrille() {
		return this.grille.getGrille();
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
		if(!this.grille.ajoutTuileAleatoire()) {Log.i("test1","---> Grille plien ajout imposible <---");}
	}
	
	/**
	 * Ajout d'une tuile bonus
	 * 
	 */
	public void ajoutTuileBonus() {}
	
	/**
	 * Ajout d'un tuile
	 * @param uneTuile
	 * 
	 */
	public void ajoutTuile(Tuile uneTuile) {this.grille.ajoutUneTuile(uneTuile);}
	
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
	public ArrayList<Tuile> droite() {
		ArrayList<Tuile> arrayTuile = null;
		if (this.grille.getNombreTuileVide() > 0 || this.grille.chekcIsPossibleDroiteGauche(false)) {
			arrayTuile = this.grille.deplacementDroite();
			if (arrayTuile.size() > 0) {

				//Nombre de tuile ajout par tour
				for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
					this.grille.ajoutTuileAleatoire();
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
		return arrayTuile;

	}
	
	/**
	 * Deplacemencement a Gauche
	 * 
	 */
	public ArrayList<Tuile>  gauche(){
		ArrayList<Tuile> arrayTuile = null;
		if(this.grille.getNombreTuileVide() > 0 || this.grille.chekcIsPossibleDroiteGauche(false)) {
			arrayTuile =  this.grille.deplacementGauche();
			if(arrayTuile.size() > 0) {
				
				//Nombre de tuile ajout par tour
				for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
					this.grille.ajoutTuileAleatoire();
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
		return arrayTuile;
		
		
	}
	
	/**
	 * Deplacemencement a Haut
	 * 
	 */
	public ArrayList<Tuile> haut() {
		ArrayList<Tuile> arrayTuile = null;
		if (this.grille.getNombreTuileVide() > 0 || this.grille.chekcIsPossiblehHautBas(false)) {
			arrayTuile = this.grille.deplacementHaut();
			if (arrayTuile.size() > 0) {

				//Nombre de tuile ajout par tour
				for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
					this.grille.ajoutTuileAleatoire();
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
		return arrayTuile;

	}
	
	/**
	 * Deplacemencement a Bas
	 * 
	 */
	public ArrayList<Tuile> bas() {
		ArrayList<Tuile> arrayTuile = null;
		if (this.grille.getNombreTuileVide() > 0 || this.grille.chekcIsPossiblehHautBas(false)) {
			arrayTuile = this.grille.deplacementBas();
			if (arrayTuile.size() > 0) {

				//Nombre de tuile ajout par tour
				for (int i = 1; i <= this.nombreDeTuilePartTour; i++) {
					this.grille.ajoutTuileAleatoire();
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
		return arrayTuile;
		
	}
	

	

}
