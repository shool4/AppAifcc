package app.pack.modele;

import android.R.integer;
import android.util.Log;


abstract class Carre {
	
	private int valeur;
	//Postion
	private Position positionActuel;
	private Position positionPasse;
	
	public Carre(int posActuelX, int posActuelY, int valeur) {
		super();
		this.positionActuel = new Position(posActuelX, posActuelY);
		this.positionPasse = new Position(posActuelX, posActuelY);
		this.valeur = valeur;
	}


	public int getValeur() {
		return valeur;
	}


	public void upValeur() {
		this.valeur = valeur*2;
		
		//this.valeur = valeur*2;
	}
	
	public void setPositionActuel(Position unePosition){
		this.positionActuel = unePosition;
	}
	public void miseJourPositionPasse() {
		this.positionPasse = this.positionActuel;
	}

	/* GETTER - SETTER */
	//##################
	/*
	 * Getter - Setter de position Actuel
	 * 
	 */
	public Position getPostionActuel() {
		return positionActuel;
	}
	public Position getPostionPasse() {
		return positionPasse;
	}
	/*
	 * Getter - Setter de position Futur
	 * 
	 */
	/*public void setPositionActuel(Position unePosition) {
		this.positionActuel.setPosX(unePosition.getPosX());
		this.positionActuel.setPosY(unePosition.getPosY());
	}*/
	/*public void setPositionPasse(Position unePosition) {
		this.positionFutur.setPosX(unePosition.getPosX());
		this.positionFutur.setPosY(unePosition.getPosY());
	}*/

	
	
}