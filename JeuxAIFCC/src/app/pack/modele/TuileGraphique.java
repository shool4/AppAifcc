package app.pack.modele;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import app.pack.gen.MainActivity;
import app.pack.gen.R;

public class TuileGraphique {
	
	private Bitmap imgCarre;
	
	private float taille;
	private Paint paint;
	private PositionGraphique posGPasse;
	private PositionGraphique posGActuel;
	private Tuile tuile;
	private int tailleFond;
	
	public TuileGraphique(Tuile tuile, int taille){
		this.tailleFond = taille;
		this.tuile = tuile;
		this.posGPasse = new PositionGraphique(tuile.getPostionPasse(), taille);
		this.posGActuel = new PositionGraphique(tuile.getPostionActuel(), taille);
		determinerCouleur();
		bitmapCarre();
	}
	
	public Tuile getTuile() {
		return tuile;
	}

	public void setTuile(Tuile tuile) {
		this.tuile = tuile;
	}
	
	public Bitmap getImgCarre() {
		return imgCarre;
	}

	public void setImgCarre(Bitmap imgCarre) {
		this.imgCarre = imgCarre;
	}

	public float getTaille() {
		return taille;
	}

	public void setTaille(float taille) {
		this.taille = taille;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public Tuile getCarre() {
		return tuile;
	}

	public void setCarre(Tuile carre) {
		this.tuile = carre;
	}

	public PositionGraphique getPosGPasse() {
		return posGPasse;
	}

	public void setPosGPasse(PositionGraphique posGPasse) {
		this.posGPasse = posGPasse;
	}

	public PositionGraphique getPosGActuel() {
		return posGActuel;
	}

	public void setPosGActuel(PositionGraphique posGActuel) {
		this.posGActuel = posGActuel;
	}

	private Paint determinerCouleur(){
		paint = new Paint();
		paint.setColor(Color.BLUE);
		if(tuile != null){
			// Faire un switch case pour déterminer la couleur de la case
			
		}
		return paint;
	}
	
	public void bitmapCarre(){
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
	}

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
	
}
