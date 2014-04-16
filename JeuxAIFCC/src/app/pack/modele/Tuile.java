package app.pack.modele;



public class Tuile {
	
	//Valeur
	private int valeur;
	//Postion
	private Position positionActuel;
	private Position positionPasse;
	
	public Tuile(int posActuelY, int posActuelX, int valeur) {
		super();
		this.positionActuel = new Position(posActuelY, posActuelX);
		this.positionPasse = new Position(posActuelY, posActuelX);
		this.valeur = valeur;
	}
	public Tuile(Position position, int valeur) {
		super();
		this.positionActuel = position;
		this.positionPasse = position;
		this.valeur = valeur;
	}

	public int getValeur() {
		return valeur;
	}
	public void setValeur(int sValeur) {
		this.valeur = sValeur;
	}
	public void merge(Tuile uneTuille){
		//Multiplication des valeurs des carres
		this.valeur = uneTuille.getValeur()*2;
		//Mise a jour de la position passé
		this.setPositionPasse(uneTuille.getPostionActuel());
		
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
	public void setPositionActuel(Position unePosition){
		this.positionActuel = unePosition;
	}
	public void setPositionPasse(Position unePosition){
		this.positionPasse = unePosition;
	}
	
	
}