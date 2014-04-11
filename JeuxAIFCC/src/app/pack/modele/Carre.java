package app.pack.modele;



abstract class Carre {
	
	//Valeur
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
	public Carre(Position position, int valeur) {
		super();
		this.positionActuel = position;
		this.positionPasse = position;
		this.valeur = valeur;
	}

	public int getValeur() {
		return valeur;
	}

	public void merge(Carre unCarre){
		//Multiplication des valeurs des carres
		this.valeur = unCarre.getValeur()*2;
		//Mise a jour de la position passé
		this.setPositionPasse(unCarre.getPostionActuel());
		
	}
	/*public void upValeur() {
		this.valeur = valeur*2;
		setPositionPasse(this.positionActuel);
		//this.valeur = valeur*2;
	}*/
	
	public void setPositionActuel(Position unePosition){
		this.positionActuel = unePosition;
	}
	public void setPositionPasse(Position unePosition){
		this.positionPasse = unePosition;
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