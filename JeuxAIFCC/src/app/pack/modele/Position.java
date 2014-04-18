package app.pack.modele;
/*
 * Class qui permet de faire facilement la conversion d'un objet a l'affichage des coordonnée
 * @author DaRk-_-D0G
 */
public class Position {
	//Coordonnée TB
	private int posY = 0;
	private int posX = 0;
	
	public Position(int posY, int posX) {
		super();
		this.posX = posX;
		this.posY = posY;
	}
	
	public int getPosY() {
		return posY;
	}
	public int getPosX() {
		return posX;
	}
	
}
