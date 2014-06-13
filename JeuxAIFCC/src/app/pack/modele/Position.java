package app.pack.modele;
/**
 * Class qui permet de faire facilement la conversion d'un objet a l'affichage des coordonnee
 * @author DaRk-_-D0G
 */
public class Position {
	//Coordonnee TB
	private int posY = 0;
	private int posX = 0;

    /**
     * Consctructeur
     * @param posY
     * @param posX
     */
	public Position(int posY, int posX) {

		this.posX = posX;
		this.posY = posY;
	}

    /**
     * Getter PosY
     * @return int
     */
	public int getPosY() {
		return posY;
	}
	/**
	 * Get posX
	 * @return int
	 */
	public int getPosX() {
		return posX;
	}

	/**
	 * Setter posY
	 */
	public void setPosY(int posY) {
		this.posY = posY;
	}
	/**
	 * Getter posX
	 */
	public void setPosX(int posX) {
		this.posX = posX;
	}

}
