package app.pack.vue;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.util.TypedValue;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import app.pack.gen.R;
import app.pack.modele.*;

@SuppressLint("WrongCall")
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {
	
	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;
	Paint mPaint;
	Paint mPaintCarre;
	
	public int mouvement = 0;
	
	//Stokage des bitmap
	ArrayList<Bitmap> tbBitmapCarre;
	
	private Bitmap fondTableau;
	private Bitmap imgCarre;
	
	TuileGraphique carreGraphique;
	
	ArrayList<Tuile> grilleTuiles = null;
	
	ArrayList<TuileGraphique> listTuilesG = null;
	/**
	 * Constructeur
	 * 
	 * @param pContext
	 */
	public MoteurGraphique(Context pContext) {
		super(pContext);
		
		mSurfaceHolder = getHolder();
	
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();

		mPaint = new Paint();
		mPaint.setColor(Color.WHITE);
		
		mPaintCarre = new Paint();
		mPaintCarre.setColor(Color.RED);
		
		Bitmap tableau = BitmapFactory.decodeResource(getResources(), R.drawable.fond_grille);
		this.fondTableau = Bitmap.createScaledBitmap(tableau, tableau.getWidth(), tableau.getHeight(), false);
		
	}
	
	//##################
	// GETTERS - SETTERS
	//##################
	
	public ArrayList<Bitmap> getTbBitmapCarre() {
		return tbBitmapCarre;
	}
	public void setTbBitmapCarre(ArrayList<Bitmap> tbBitmapCarre) {
		this.tbBitmapCarre = tbBitmapCarre;
	}
	
	//##################

	public ArrayList<Tuile> getGrilleTuiles() {
		return grilleTuiles;
	}

	public void setGrilleTuiles(ArrayList<Tuile> grilleTuiles) {
		this.grilleTuiles = grilleTuiles;
	}

	public ArrayList<TuileGraphique> getListTuilesG() {
		return listTuilesG;
	}

	public void setListTuilesG(ArrayList<TuileGraphique> listTuilesG) {
		this.listTuilesG = listTuilesG;
	}

	/**
	 * S'affiche au lancement de la surface view
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.keepDrawing = true;
		mThread.start();
		
		this.imgCarre = BitmapFactory.decodeResource(getResources(), R.drawable.carre2);
		this.imgCarre = Bitmap.createScaledBitmap(imgCarre, imgCarre.getWidth(), imgCarre.getHeight(), false);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		//mThread.keepDrawing = true;
		//mThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mThread.keepDrawing = false;
		boolean retry = true;
		while (retry) {
			try {
				mThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}

	}
	
	protected Bitmap imageTuile(int valeurTuile){
		Bitmap carre = BitmapFactory.decodeResource(getResources(), R.drawable.carre);
		carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
		
		Canvas canvasNumero = new Canvas(carre);
		
		Paint pinceauNumero = new Paint();
		pinceauNumero.setColor(Color.WHITE);
		canvasNumero.drawText("2", 10, 10, pinceauNumero);
		
		return carre;
	}

	@Override
	protected void onDraw(Canvas pCanvas) {
		
		// Couleur de fond de la surface view
		pCanvas.drawColor(Color.WHITE);
		
		// Affichage du fond de la grille
		pCanvas.drawBitmap(this.fondTableau, 0, 192, null);
		//Log.v("testo", "X : " + fondTableau.getWidth() + " / Y : " + fondTableau.getHeight());
        //pCanvas.drawBitmap(this.bitmapTableau, (getWidth() - this.bitmapTableau.getWidth()) / 2, 192, mPaint);
		//Log.v("testo", "Mouvement : " + mouvement);
		
		if(this.listTuilesG != null){
			for(TuileGraphique uneTuile : this.listTuilesG){
				if(uneTuile.getTuile().getValeur() != 0){
					//Log.v("testo", "X : " + uneTuile.getPostionActuel().getPosX() + " / Y : " + uneTuile.getPostionActuel().getPosY());
					
					//carreGraphique = new TuileGraphique(uneTuile, this.fondTableau.getWidth());
					
					switch(this.mouvement){
						case 1:
							Log.v("testo", "*********************** GAUCHE *********************");
							Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
							Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
							if(uneTuile.getPosGPasse().getY1() >= uneTuile.getPosGActuel().getY1()){
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								Log.v("testo", "Valeur Y1 départ  : " + uneTuile.getPosGPasse().getY1());
								uneTuile.mouvGauche(10);
								Log.v("testo", "Valeur Y1 après modif : " + uneTuile.getPosGPasse().getY1());
							}else{
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
								//this.mouvement = 0;
							}
						break;
						case 2:
							Log.v("testo", "*********************** DROITE *********************");
							Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
							Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
							if(uneTuile.getPosGPasse().getY1() <= uneTuile.getPosGActuel().getY1()){
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								uneTuile.mouvDroite(10);
								Log.v("testo", "Valeur Y1 : " + uneTuile.getPosGPasse().getY1());
							}else{
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
								//this.mouvement = 0;
							}
						break;
						case 3:
							Log.v("testo", "*********************** HAUT *********************");
							if(uneTuile.getPosGPasse().getX1() >= uneTuile.getPosGActuel().getX1()){
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								uneTuile.mouvHaut(10);
								Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
							}else{
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
								//this.mouvement = 0;
							}
						break;
						case 4:
							Log.v("testo", "*********************** BAS *********************");
							if(uneTuile.getPosGPasse().getX1() <= uneTuile.getPosGActuel().getX1()){
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								uneTuile.mouvBas(10);
								Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
							}else{
								pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
								//this.mouvement = 0;
							}
						break;
						default:
							Log.v("testo", "*********************** AUCUN *********************");
							pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
						break;
					}
					//pCanvas.drawBitmap(carreGraphique.getImgCarre(), carreGraphique.getPosGActuel().getY1(), carreGraphique.getPosGActuel().getX1() + 192, null);
					
					/*if(carreGraphique.getPosGPasse().getX1() != carreGraphique.getPosGActuel().getX1()){
						pCanvas.drawBitmap(carreGraphique.getImgCarre(), carreGraphique.getPosGPasse().getY1(), carreGraphique.getPosGPasse().getX1() + 192, null);
						carreGraphique.avancerTuile(1);
					}*/
					//PositionGraphique posG = new PositionGraphique(uneTuile.getPostionActuel(), this.fondTableau.getWidth());
					
				}
				
				//Log.v("testo", "X : " + posG.getX1() + " Y : " + posG.getY1());
			}
			
		}
		
	}

	private void drawTextDeTest(Canvas canvas, String stringTest, int posX, int posY) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		String text = stringTest;
		canvas.drawText(text, posX, posY, paint);
	}
	
	private class DrawingThread extends Thread {
		boolean keepDrawing = true;
		
		@Override
		public void run() {
			Canvas canvas;
			while (keepDrawing) {
				canvas = null;

				try {
					canvas = mSurfaceHolder.lockCanvas();
					synchronized (mSurfaceHolder) {
						onDraw(canvas);
					}
				} finally {
					if (canvas != null)
						mSurfaceHolder.unlockCanvasAndPost(canvas);
				}

				// Pour dessiner � 50 fps
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	
	
}
