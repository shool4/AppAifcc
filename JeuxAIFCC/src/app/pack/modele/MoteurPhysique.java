package app.pack.modele;

import java.util.List;

import android.util.Log;
import app.pack.gen.MainActivity;

/**
 * Class qui gere le moteur physique du jeux
 * @author dark_d0g
 *
 */
public class MoteurPhysique {

	private Grille grille = null;
	//private int tailleMax = 4;
	private MainActivity pView = null;
/**
 * Construcuteur du moteur physique
 * @param pView
 */
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
	public void droite(){
		this.grille.deplacementDroite();
	}
	
	/**
	 * Deplacemencement a Gauche
	 * 
	 */
	public void gauche(){
		this.grille.deplacementGauche();
		grille.debog_Tableau();
	}
	
	/**
	 * Deplacemencement a Haut
	 * 
	 */
	public void haut(){
		this.grille.deplacementHaut();
	}
	
	/**
	 * Deplacemencement a Bas
	 * 
	 */
	public void bas(){
		this.grille.deplacementBas();
	}

}
