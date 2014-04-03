package app.pack.modele;

import android.R.integer;
import android.util.Log;


abstract class Carre {
	
	private int valeur;
	//Postion
	private Position positionActuel;
	private Position positionFutur;
	
	public Carre(int posActuelX, int posActuelY, int valeur) {
		super();
		this.positionActuel = new Position(posActuelX, posActuelY);
		this.positionFutur = new Position(posActuelX, posActuelY);
		this.valeur = valeur;
	}


	public int getValeur() {
		return valeur;
	}


	public void upValeur() {
		this.valeur = valeur*2;
		
		//this.valeur = valeur*2;
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
	public Position getPostionFutur() {
		return positionFutur;
	}
	/*
	 * Getter - Setter de position Futur
	 * 
	 */
	public void setPositionActuel(Position unePosition) {
		this.positionActuel.setPosX(unePosition.getPosX());
		this.positionActuel.setPosY(unePosition.getPosY());
	}
	public void setPositionFutur(Position unePosition) {
		this.positionFutur.setPosX(unePosition.getPosX());
		this.positionFutur.setPosY(unePosition.getPosY());
	}

	
	
}