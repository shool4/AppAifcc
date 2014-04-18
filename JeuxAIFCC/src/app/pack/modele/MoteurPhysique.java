package app.pack.modele;

import app.pack.gen.MainActivity;

//DECLARER EN INTEGER AU CAS SI OBJECT DANS TB
public class MoteurPhysique {

	private Grille grille = null;
	private int tailleMax = 4;
	private MainActivity pView = null;

	public MoteurPhysique(MainActivity pView) {
		
		grille = new Grille(4,4);
		grille.debog_Tableau();
		
		this.pView = pView;
	
		
	/*	this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();
		this.ajoutTuileAleatoire();*/
		
	/*	ajoutTuile(new Tuile(0, 0, 2));
		ajoutTuile(new Tuile(0, 1, 0));
		ajoutTuile(new Tuile(0, 2, 2));
		ajoutTuile(new Tuile(0, 3, 2));*/
		
		ajoutTuile(new Tuile(0, 0, 2));
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
		ajoutTuile(new Tuile(3, 3, 2));
	//	this.ajoutCarreBonus();
	//	this.ajoutCarreBonus();
		grille.debog_Tableau();
		
		this.droite();
		grille.debog_Tableau();
		this.droite();
		grille.debog_Tableau();
		this.droite();
		grille.debog_Tableau();
	
	}
	public void ajoutTuileAleatoire() {
		this.grille.ajoutUneTuileAleatoire();
	}
	public void ajoutTuileBonus() {
		//this.grille.ajoutUnCarreAleatoireBonus();
	}
	public void ajoutTuile(Tuile uneTuile) {
		this.grille.ajoutUneTuile(uneTuile);
	}
	public void droite(){
		this.grille.deplacementDroite();
	}
	public void gauche(){
		this.grille.deplacementGauche();
	}
	public void haut(){
		//this.grille.deplaceHautBas(false);
	}
	public void bas(){
		//this.grille.deplaceHautBas(true);
	}
	public Grille getGrille() {
		return this.grille;
	}
}
