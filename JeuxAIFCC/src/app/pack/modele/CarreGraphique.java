package app.pack.modele;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class CarreGraphique {
	
	private Bitmap imgCarre;
	private float taille;
	private Paint paint;
	private PositionGraphique posG;
	private Tuile tuile;
	private int tailleFond;
	
	public CarreGraphique(Tuile carre, int taille){
		this.tailleFond = taille;
		this.tuile = carre;
		this.posG = new PositionGraphique(carre.getPostionActuel(), taille);
		determinerCouleur();
		bitmapCarre();
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

	public PositionGraphique getPosG() {
		return posG;
	}

	public void setPosG(PositionGraphique posG) {
		this.posG = posG;
	}

	private Paint determinerCouleur(){
		paint = new Paint();
		paint.setColor(Color.BLACK);
		if(tuile != null){
			// Faire un switch case pour déterminer la couleur de la case
			
		}
		return paint;
	}
	
	public Bitmap bitmapCarre(){
		this.imgCarre = Bitmap.createBitmap(this.tailleFond, this.tailleFond, Config.ARGB_8888);
		
		Canvas c = new Canvas(this.imgCarre);
        c.drawRoundRect(new RectF(this.posG.getX1(), this.posG.getY1(), this.posG.getX2(), this.posG.getY2()), 10, 10, this.paint);
        Paint mPaintText = new Paint();
        mPaintText.setTextSize(60);
        c.drawText("2", 90, 90, mPaintText);
		
		return this.imgCarre;
	}
	
}
