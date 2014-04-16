package app.pack.modele;
/*
 * Class qui permet de faire facilement la conversion d'un objet a l'affichage des coordonnée
 * @author DaRk-_-D0G
 */
public class Position {
	//Coordonnée TB
	private int posY = 0;
	private int posX = 0;
	
	//Coordonnée graphique
	private float posGrapX = 0;
	private float posGrapY = 0;
	
	public Position(int posY, int posX) {
		super();
		this.posX = posX;
		this.posY = posY;
		this.posGrapX = convertionPosEnPosGrap(posX);
		this.posGrapY = convertionPosEnPosGrap(posY);
	}
	/*
	 * Methode de conversion pour coordonnée posXY en Cordonnée sur l'ecran
	 * a definit TODO
	 */
	private float convertionPosEnPosGrap(int pos) {
		return 1.11f;
	}
	private int convertionPosGrapEnPos(float posGrap) {
		return 1;
	}
	//GETTER _ SETTER
	//####################
	public int getPosY() {
		return posY;
	}
	public int getPosX() {
		return posX;
	}
	public float getposGrapX() {
		return posGrapX;
	}
	public float getposGrapY() {
		return posGrapY;
	}
	//LORS DU SET CONVERTION DIRECT DES POS GRAPHIQUE ECRAN
	public void setPosY(int posY) {
		this.posY = posY;
		this.posGrapY = convertionPosEnPosGrap(posY);
	}

	public void setPosX(int posX) {
		this.posX = posX;
		this.posGrapX = convertionPosEnPosGrap(posX);
	}

	public void setposGrapX(float grapPosX) {
		this.posGrapX = grapPosX;
		this.posX = convertionPosGrapEnPos(grapPosX);
	}

	public void setposGrapY(float grapPosY) {
		this.posGrapY = grapPosY;
		this.posY = convertionPosGrapEnPos(grapPosY);
	}
	
}
