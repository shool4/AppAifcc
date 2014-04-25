package app.pack.modele;

import java.util.ArrayList;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.R.integer;

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
	
	public Bitmap constructionFondGrille(int largeurEcran, int hauteurEcran){
		Bitmap fond;
		Paint mPaintCarre = new Paint();
		mPaintCarre.setARGB(255, 187, 173, 160);
		Paint mPaintC = new Paint();
		mPaintC.setARGB(255, 204, 192, 179);
		
		int tailleFond = (int) (largeurEcran * 0.9f);
		Log.v("testo", "Taille fond : " + tailleFond);
		
		fond = Bitmap.createBitmap(tailleFond, tailleFond, Config.ARGB_8888);
		Canvas c = new Canvas(fond);
		c.drawRoundRect(new RectF(0, 0, tailleFond, tailleFond), 10, 10, mPaintCarre);
		
		int i ,j;
		for(i=1; i<=4; i++){
			for(j=1; j<=4; j++){
				PositionGraphique posG = new PositionGraphique(i, j, tailleFond);
				c.drawRoundRect(new RectF(posG.getX1(), posG.getY1(), posG.getX2(), posG.getY2()), 10, 10, mPaintC);
			}
		}
		
		return fond;
	}
	
	public ArrayList<Bitmap> ConstructionCarre(){
		tbBitmapCarre = new ArrayList<Bitmap>();
		int i = 2;
		
		Paint mPaintCarre = new Paint();
		mPaintCarre.setColor(Color.BLUE);
		
		while(i <= 16){
			Bitmap carre = Bitmap.createBitmap(200, 200, Config.ARGB_8888);
			Canvas c = new Canvas(carre);
	        c.drawRoundRect(new RectF(0, 0, 200, 200), 10, 10, mPaintCarre);
	        Paint mPaintText = new Paint();
	        mPaintText.setTextSize(60);
	        c.drawText("" + i, 90, 90, mPaintText);
	        
	        tbBitmapCarre.add(carre);
			
			i = i*2;
		}
		
		return tbBitmapCarre;
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
	public  ArrayList<Tuile>  getGrille() {
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
