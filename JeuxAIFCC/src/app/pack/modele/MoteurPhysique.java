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
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
		this.ajoutCarreNumero();
	//	this.ajoutCarreBonus();
	//	this.ajoutCarreBonus();
		grille.debog_Tableau();
		
		this.gauche();
		grille.debog_Tableau();
		this.gauche();
		grille.debog_Tableau();
		this.gauche();
		grille.debog_Tableau();
	
	}
	public void ajoutCarreNumero() {
		this.grille.ajoutUnCarreAleatoire();
	}
	public void ajoutCarreBonus() {
		//this.grille.ajoutUnCarreAleatoireBonus();
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
