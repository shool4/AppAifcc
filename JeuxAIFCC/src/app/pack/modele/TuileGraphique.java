package app.pack.modele;

/**
 * Class heritant de tuile pour son affichage
 */
public class TuileGraphique extends Tuile {

    private PositionGraphique posGPasse;
    private PositionGraphique posGActuel;
    //private Tuile tuile;
    //private int tailleFond;
    private int animationApparition = 0;
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
     *
     * @param unePositionActuel
     * @param unePositionPasse
     * @param valeur
     */

    public TuileGraphique(Position unePositionActuel, Position unePositionPasse, int valeur) {
        super(unePositionActuel,unePositionPasse,valeur);

        this.posGActuel = new PositionGraphique(unePositionActuel);
        this.posGPasse = new PositionGraphique(unePositionPasse);
    }

    public  TuileGraphique(TuileGraphique uneTuileGraphique) {
        super(uneTuileGraphique.getPostionActuel(),uneTuileGraphique.getPostionPasse(),uneTuileGraphique.getValeur());

        this.posGActuel = new PositionGraphique(uneTuileGraphique.getPostionActuel());
        this.posGPasse = new PositionGraphique(uneTuileGraphique.getPostionPasse());
    }
    /**
     * Setter de position actuel
     * @param unePosition
     */
    @Override
    public void setPositionActuel(Position unePosition){
        this.positionActuel = unePosition;
        this.posGActuel = new PositionGraphique(unePosition);
    }
    /**
     * Setter de position passe
     * @param	Position
     * @return	Position
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
     * @param PositionGraphique
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

    public void mouvDroite(int valeur){
        this.posGPasse.setY1(posGPasse.getY1() + valeur);
    }
    public void mouvGauche(int valeur){
        this.posGPasse.setY1(posGPasse.getY1() - valeur);
    }
    public void mouvBas(int valeur){
        this.posGPasse.setX1(posGPasse.getX1() + valeur);
    }
    public void mouvHaut(int valeur){
        this.posGPasse.setX1(posGPasse.getX1() - valeur);
    }

    public int getAnimationApparition() {
        return animationApparition;
    }

    public void setAnimationApparition(int animationApparition) {
        this.animationApparition = animationApparition;
    }
}
