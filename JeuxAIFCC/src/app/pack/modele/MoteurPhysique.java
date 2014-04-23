package app.pack.modele;

import java.util.ArrayList;
import java.util.List;

import android.R.integer;
import android.util.Log;
import app.pack.gen.MainActivity;

/**
 * Class qui gere le moteur physique du jeux
 * @author dark_d0g
 *
 */
public class MoteurPhysique {

	private Grille grille = null;
	
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
		
	/*	ajoutTuile(new Tuile(0, 0, 1));
		ajoutTuile(new Tuile(0, 1, 2));
		ajoutTuile(new Tuile(0, 2, 3));
		ajoutTuile(new Tuile(0, 3, 4));
		
		ajoutTuile(new Tuile(1, 0, 5));
		ajoutTuile(new Tuile(1, 1, 6));
		ajoutTuile(new Tuile(1, 2, 11));
		ajoutTuile(new Tuile(1, 3, 8));
		
		ajoutTuile(new Tuile(2, 0, 9));
		ajoutTuile(new Tuile(2, 1, 10));
		ajoutTuile(new Tuile(2, 2, 11));
		ajoutTuile(new Tuile(2, 3, 12));
		
		ajoutTuile(new Tuile(3, 0, 13));
		ajoutTuile(new Tuile(3, 1, 14));
		ajoutTuile(new Tuile(3, 2, 15));
		ajoutTuile(new Tuile(3, 3, 17));*/
	//	this.ajoutCarreBonus();
	//	this.ajoutCarreBonus();
		
		
		
		
		/*Log.i("test1","Jeux est perdant : "+grille.isGameLost());
		Log.i("test1","****************************************");
		Log.i("test1","Jeux est perdant : "+grille.isGameLost());
		Log.i("test1","****************************************");
		  
		List<Integer> tbDesPosibilite = grille.possibiliteMouvement();
		for (Integer integer : tbDesPosibilite) {
			Log.i("test1","----------> POSIBLE ----> "+integer);
		}*/
		
		
	/*	this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		grille.debog_Tableau();
		this.bas();
		grille.debog_Tableau();
		this.droite();
		grille.debog_Tableau();
		this.gauche();
		grille.debog_Tableau();
		this.haut();
		grille.debog_Tableau();
		
		Log.i("test1","****************************************");
		Log.i("test1","Jeux est perdant : "+grille.isGameLost());
		Log.i("test1","****************************************");*/
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
	public Grille getGrille() {
		return this.grille;
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
