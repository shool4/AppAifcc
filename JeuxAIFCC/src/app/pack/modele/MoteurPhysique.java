package app.pack.modele;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import app.pack.gen.MainActivity;

//DECLARER EN INTEGER AU CAS SI OBJECT DANS TB
public class MoteurPhysique {

	private Grille grille = null;
	private int tailleMax = 4;
	private MainActivity pView = null;
	
	private List<CarreGraphique> listCarreGraphique;
	
	ArrayList<Bitmap> tbBitmapCarre;

	public MoteurPhysique(MainActivity pView) {
		
		grille = new Grille(4,4);
		grille.debog_Tableau();
		
		this.pView = pView;
	
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		
		/*ajoutTuile(new Tuile(0, 0, 0));
		ajoutTuile(new Tuile(0, 1, 0));
		ajoutTuile(new Tuile(0, 2, 2));
		/*ajoutTuile(new Tuile(0, 0, 2));
		ajoutTuile(new Tuile(0, 1, 2));
		ajoutTuile(new Tuile(0, 2, 0));
		ajoutTuile(new Tuile(0, 3, 2));
		
		ajoutTuile(new Tuile(1, 0, 0));
		ajoutTuile(new Tuile(1, 1, 2));
		ajoutTuile(new Tuile(1, 2, 0));
		ajoutTuile(new Tuile(1, 3, 2));
		
		ajoutTuile(new Tuile(2, 0, 0));
		ajoutTuile(new Tuile(2, 1, 2));
		ajoutTuile(new Tuile(2, 2, 2));
		ajoutTuile(new Tuile(2, 3, 2));
		
		ajoutTuile(new Tuile(3, 0, 0));
		ajoutTuile(new Tuile(3, 1, 2));
		ajoutTuile(new Tuile(3, 2, 2));
		ajoutTuile(new Tuile(3, 3, 2));*/
	//	this.ajoutCarreBonus();
	//	this.ajoutCarreBonus();
		grille.debog_Tableau();
		
		
		
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
	
	public void ajoutTuileAleatoire() {
		if(!this.grille.ajoutTuileAleatoire()) {
			Log.i("test1","---> Grille plien ajout imposible <---");
		}
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
	public void droite(){
		this.grille.deplacementDroite();
	}
	public void gauche(){
		this.grille.deplacementGauche();
	}
	public void haut(){
		this.grille.deplacementHaut();
	}
	public void bas(){
		this.grille.deplacementBas();
	}

	public void setGrille(Grille grille) {
		this.grille = grille;
	}
	
}
