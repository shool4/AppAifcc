package app.pack.modele;

public class PositionGraphique {
	
	private float x1;
	private float y1;

	private Position pos;
	//private int tailleFond;

    /**
     * Constructeur
     * @param position
     */
	public PositionGraphique(Position position) {
		this.pos = position;
		//this.tailleFond = taille;
		conversionPosition();
	}

    /**
     * Getter X
     * @return int
     */
	public float getX1() {
		return x1;
	}
    /**
     * Sette X
     */
	public void setX1(float x1) {

        this.x1 = x1;
	}
    /**
     * Getter Y
     * @return int
     */
	public float getY1() {
		return y1;
	}
    /**
     * Setter Y
     */
	public void setY1(float y1) {

        this.y1 = y1;
	}

    /**
     * Convertion de positon
     */
	public void conversionPosition(){
		//1080
		float ecart = 1080 * (1f/45f);
		//float ecart = tailleFond * (1f/45f);
		
		//float ecart = tailleFond * 0.022222222222222222f;
		float tailleCarre = (1080 - (5 * ecart)) / 4;
		//float tailleCarre = (tailleFond - (5 * ecart)) / 4;
		
		this.x1 = ((this.pos.getPosY() + 1) * ecart) + (this.pos.getPosY() * tailleCarre);
		this.y1 = ((this.pos.getPosX() + 1) * ecart) + (this.pos.getPosX() * tailleCarre);

	}
	
}
