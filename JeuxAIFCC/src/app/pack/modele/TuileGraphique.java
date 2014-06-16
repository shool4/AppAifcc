package app.pack.modele;

import java.io.Serializable;

/**
 * Class heritant de tuile pour son affichage
 */
public class TuileGraphique extends Tuile implements Serializable{

    private PositionGraphique posGPasse;
    private PositionGraphique posGActuel;
    //private Tuile tuile;
    //private int tailleFond;
    private int animationApparition = 5;
    /**
     *
     * @param uneTuile
     */
	/*public TuileGraphique(Tuile uneTuile){
		super(uneTuile);


		//this.tailleFond = taille;
		//this.tuile = tuile;
		//this.imgCarre = uneBipmap;
        this.posGActuel = new PositionGraphique(uneTuile.getPostionActuel());
		this.posGPasse = new PositionGraphique(uneTuile.getPostionPasse());

		//determinerCouleur();
		//bitmapCarre();
	}
*/
    /**
     * Constructeur 1er
     * @param unePositionActuel Position
     * @param unePositionPasse Position
     * @param valeur int
     */

    public TuileGraphique(Position unePositionActuel, Position unePositionPasse, int valeur) {
        super(unePositionActuel,unePositionPasse,valeur);

        this.posGActuel = new PositionGraphique(unePositionActuel);
        this.posGPasse = new PositionGraphique(unePositionPasse);
    }

    /**
     * Constructeur 2eme
     * @param uneTuileGraphique TuileGraphique
     */
    public  TuileGraphique(TuileGraphique uneTuileGraphique) {
        super(uneTuileGraphique.getPostionActuel(),uneTuileGraphique.getPostionPasse(),uneTuileGraphique.getValeur());

        this.posGActuel = new PositionGraphique(uneTuileGraphique.getPostionActuel());
        this.posGPasse = new PositionGraphique(uneTuileGraphique.getPostionPasse());
    }
    /**
     * Setter de position actuel
     * @param unePosition Position
     */
    @Override
     public void setPositionActuel(Position unePosition){
        this.positionActuel = unePosition;
        this.posGActuel = new PositionGraphique(unePosition);
    }
    /**
     * Setter de position passe
     * @param	unePosition Position
     */
     public void setPositionPasse(Position unePosition){
        this.positionPasse = unePosition;
        this.posGPasse = new PositionGraphique(unePosition);

    }
   /* public TuileGraphique(int unePositionActuelY, int unePositionActuelX,int unePositionPasseY, int unePositionPasseX, int uneValeur) {
        Position unePositionActuel = new Position(unePositionActuelY, unePositionActuelX);
        Position unePositionPasse = new Position(unePositionPasseY, unePositionPasseX);
        super(unePositionActuel,unePositionPasse,uneValeur);

        this.posGActuel = new PositionGraphique(unePositionActuel);
        this.posGPasse = new PositionGraphique(unePositionPasse);
    }*/
	/*
	public Tuile getTuile() {
		return tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}*/
	/*
	public Bitmap getImgCarre() {
		return imgCarre;
	}

	public void setImgCarre(Bitmap imgCarre) {
		this.imgCarre = imgCarre;
	}*/

	/*public float getTaille() {
		return taille;
	}*/

    /*	public void setTaille(float taille) {
            this.taille = taille;
        }

        public Paint getPaint() {
            return paint;
        }

        public void setPaint(Paint paint) {
            this.paint = paint;
        }*/
/*
	public Tuile getCarre() {
		return tuile;
	}

	public void setCarre(Tuile carre) {
		this.tuile = carre;
	}
*/

    /**
     * Getter de position passe
     * @return PositionGraphique
     */
     public PositionGraphique getPosGPasse() {
        return posGPasse;
    }


    /**
     * Setter de position passe
     * @param posGPasse PositionGraphique
     */
     public void setPosGPasse(PositionGraphique posGPasse) {

        this.posGPasse = posGPasse;
    }

    /**
     * Getter de la position actuel
     * @return PositionGraphique
     */
     public PositionGraphique getPosGActuel() {
        return posGActuel;
    }


	/*public void setPosGActuel(PositionGraphique posGActuel) {
		this.posGActuel = posGActuel;
	}*/

	/*private Paint determinerCouleur(){
		paint = new Paint();
		paint.setColor(Color.BLUE);
		if(tuile != null){
			// Faire un switch case pour déterminer la couleur de la case
			
		}
		return paint;
	}*/
	
	/*public void bitmapCarre(){
		//this.imgCarre = Bitmap.createBitmap(this.tailleFond, this.tailleFond, Config.ARGB_8888);
		Bitmap carre = BitmapFactory.decodeResource(MainActivity.resources, R.drawable.carre);
		carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
		Bitmap copieCarre = carre.copy(Bitmap.Config.ARGB_8888, true);
		
		Canvas c = new Canvas(copieCarre);
        //c.drawRoundRect(new RectF(this.posG.getX1(), this.posG.getY1(), this.posG.getX2(), this.posG.getY2()), 10, 10, this.paint);
        Paint mPaintText = new Paint();
        mPaintText.setColor(Color.WHITE);
        mPaintText.setTextSize(100);
        c.drawText("" + this.tuile.getValeur(), 60, 150, mPaintText);
		
		this.imgCarre = copieCarre;
	}*/

    /**
     * mouvDroite
     * @param valeur int
     */
     public void mouvDroite(float valeur){
        this.posGPasse.setY1(posGPasse.getY1() + valeur);
    }

    /**
     * mouvGauche
     * @param valeur int
     */
     public void mouvGauche(float valeur){
        this.posGPasse.setY1(posGPasse.getY1() - valeur);
    }

    /**
     * mouvBas
     * @param valeur int
     */
     public void mouvBas(float valeur){
        this.posGPasse.setX1(posGPasse.getX1() + valeur);
    }

    /**
     * mouvHaut
     * @param valeur int
     */
     public void mouvHaut(float valeur){
        this.posGPasse.setX1(posGPasse.getX1() - valeur);
    }

    /**
     * getAnimationApparition
     * @return int
     */
     public int getAnimationApparition() {
        return animationApparition;
    }

    /**
     * setAnimationApparition
     * @param animationApparition int
     */
     public void setAnimationApparition(int animationApparition) {
        this.animationApparition = animationApparition;
    }
}
