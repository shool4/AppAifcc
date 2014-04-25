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
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import app.pack.gen.R;
import app.pack.modele.CarreGraphique;
import app.pack.modele.PositionGraphique;
import app.pack.modele.Tuile;

@SuppressLint("WrongCall")
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {
	
	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;
	Paint mPaint;
	Paint mPaintCarre;
	
	//Stokage des bitmap
	ArrayList<Bitmap> tbBitmapCarre;
	
	private Bitmap fondTableau;
	private Bitmap imgCarre;
	
	CarreGraphique carreGraphique;
	
	ArrayList<Tuile> grilleTuiles = null;
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

	/**
	 * S'affiche au lancement de la surface view
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.keepDrawing = true;
		mThread.start();
		/*
		// Couleur du fond de la grille
		Paint mPaintCarre = new Paint();
		mPaintCarre.setARGB(255, 187, 173, 160);
		// Couleur des tuiles de fond
		Paint mPaintC = new Paint();
		mPaintC.setARGB(255, 204, 192, 179);
		// Détermine la taille du fond en fonction de l'écran
		int tailleFond = (int) (getWidth() * 0.9f);
		
		// Création de l'image de fond
		this.fondTableau = Bitmap.createBitmap(tailleFond, tailleFond, Config.ARGB_8888);
		Canvas c = new Canvas(this.fondTableau);
		c.drawRoundRect(new RectF(0, 0, tailleFond, tailleFond), 10, 10, mPaintCarre);
		// Affichage des tuiles de fond
		int i ,j;
		for(i=0; i<4; i++){
			for(j=0; j<4; j++){
				PositionGraphique posG = new PositionGraphique(i, j, tailleFond);
				c.drawRoundRect(new RectF(posG.getX1(), posG.getY1(), posG.getX2(), posG.getY2()), 10, 10, mPaintC);
			}
		}*/
		
		this.fondTableau = BitmapFactory.decodeResource(getResources(), R.drawable.fond_grille);
		this.fondTableau = Bitmap.createScaledBitmap(fondTableau, fondTableau.getWidth(), fondTableau.getHeight(), false);
		
		this.imgCarre = BitmapFactory.decodeResource(getResources(), R.drawable.carre);
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

	@Override
	protected void onDraw(Canvas pCanvas) {
		
		// Couleur de fond de la surface view
		pCanvas.drawColor(Color.WHITE);
		
		// Affichage de la grille
		//pCanvas.drawBitmap(this.fondTableau, 54, 192, mPaint);
		Bitmap Grille = BitmapFactory.decodeResource(getResources(), R.drawable.fond_grille);
		Grille = Bitmap.createScaledBitmap(fondTableau, fondTableau.getWidth(), fondTableau.getHeight(), false);
		pCanvas.drawBitmap(Grille, 0, 192, null);
        //pCanvas.drawBitmap(this.bitmapTableau, (getWidth() - this.bitmapTableau.getWidth()) / 2, 192, mPaint);
		
		
		if(this.grilleTuiles != null){
			for(Tuile uneTuile : grilleTuiles){
				if(uneTuile.getValeur() != 0){
					
					PositionGraphique posG = new PositionGraphique(uneTuile.getPostionActuel(), 1027);
					Log.v("testo", "X : " + uneTuile.getPostionActuel().getPosX() + " / Y : " + uneTuile.getPostionActuel().getPosY());
					if(uneTuile.getValeur() == 2){
						Bitmap carre = BitmapFactory.decodeResource(getResources(), R.drawable.c2);
						carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
						pCanvas.drawBitmap(carre, posG.getY1()+25, posG.getX1()+220, null);
					}else if(uneTuile.getValeur() == 4){
						Bitmap carre = BitmapFactory.decodeResource(getResources(), R.drawable.c4);
						carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
						pCanvas.drawBitmap(carre, posG.getY1()+25, posG.getX1()+220, null);
					}else if(uneTuile.getValeur() == 8){
						Bitmap carre = BitmapFactory.decodeResource(getResources(), R.drawable.c8);
						carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
						pCanvas.drawBitmap(carre, posG.getY1()+25, posG.getX1()+220, null);
					}else if(uneTuile.getValeur() == 16){
						Bitmap carre = BitmapFactory.decodeResource(getResources(), R.drawable.c16);
						carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
						pCanvas.drawBitmap(carre, posG.getY1()+25, posG.getX1()+220, null);
					}else{
						pCanvas.drawBitmap(this.imgCarre, posG.getY1()+25, posG.getX1()+220, null);
					}
					
					//pCanvas.drawRoundRect(new RectF(posG.getX1(), posG.getY1(), posG.getX2(), posG.getY2()), 10, 10, mPaintCarre);
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
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
			}
		}
	}
	
	
	
}
