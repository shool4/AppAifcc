package app.pack.modele;

import app.pack.gen.MainActivity;

public class PositionGraphique {
	
	private float x1;
	private float y1;
	private float x2;
	private float y2;
	private Position pos;
	//private int tailleFond;
	
	public PositionGraphique(Position position) {
		this.pos = position;
		//this.tailleFond = taille;
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


	public void conversionPosition(){
		//1080
		float ecart = MainActivity.tailleGrille * (1f/45f);
		//float ecart = tailleFond * (1f/45f);
		
		//float ecart = tailleFond * 0.022222222222222222f;
		float tailleCarre = (MainActivity.tailleGrille - (5 * ecart)) / 4;
		//float tailleCarre = (tailleFond - (5 * ecart)) / 4;
		
		this.x1 = ((this.pos.getPosY() + 1) * ecart) + (this.pos.getPosY() * tailleCarre);
		this.y1 = ((this.pos.getPosX() + 1) * ecart) + (this.pos.getPosX() * tailleCarre);
		this.x2 = (this.pos.getPosY() + 1) * (ecart + tailleCarre);
		this.y2 = (this.pos.getPosX() + 1) * (ecart + tailleCarre);
	}
	
}
