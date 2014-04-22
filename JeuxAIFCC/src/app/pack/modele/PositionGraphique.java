package app.pack.modele;

public class PositionGraphique {
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private Position pos;
	private int tailleFond;
	
	public PositionGraphique(Position position, int taille) {
		this.pos = position;
		this.tailleFond = taille;
		conversionPosition();
	}
	
	public PositionGraphique(int X, int Y, int taille) {
		this.pos = new Position(X, Y);
		this.tailleFond = taille;
		conversionPosition();
	}
	
	public float getX1() {
		return x1;
	}

	public void setX1(float x1) {
		this.x1 = x1;
	}

	public float getY1() {
		return y1;
	}

	public void setY1(float y1) {
		this.y1 = y1;
	}

	public float getX2() {
		return x2;
	}

	public void setX2(float x2) {
		this.x2 = x2;
	}

	public float getY2() {
		return y2;
	}

	public void setY2(float y2) {
		this.y2 = y2;
	}

	public void conversionPosition(){
		float ecart = tailleFond * 0.02f;
		float tailleCarre = (tailleFond - (5 * ecart)) / 4;
		
		this.x1 = (this.pos.getPosY() * ecart) + ((this.pos.getPosY() - 1) * tailleCarre);
		this.y1 = (this.pos.getPosX() * ecart) + ((this.pos.getPosX() - 1) * tailleCarre);
		this.x2 = this.pos.getPosY() * (ecart + tailleCarre);
		this.y2 = this.pos.getPosX() * (ecart + tailleCarre);
	}
	
}
