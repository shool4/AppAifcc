package app.pack.vue;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import app.pack.modele.CarreGraphique;
import app.pack.modele.Grille;
import app.pack.modele.MoteurPhysique;

@SuppressLint("WrongCall")
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {
	
	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;
	Paint mPaint;
	Paint mPaintCarre;
	
	//Stokage des bitmap
	ArrayList<Bitmap> tbBitmapCarre;
	public ArrayList<Bitmap> getTbBitmapCarre() {
		return tbBitmapCarre;
	}
	public void setTbBitmapCarre(ArrayList<Bitmap> tbBitmapCarre) {
		this.tbBitmapCarre = tbBitmapCarre;
	}

	Bitmap bitmapTableau;
	
	Grille grille = null;
	
	CarreGraphique carreGraphique;
	
	MoteurPhysique mPhysique;
	
	public MoteurPhysique getmPhysique() {
		return mPhysique;
	}
	public void setmPhysique(MoteurPhysique mPhysique) {
		this.mPhysique = mPhysique;
	}

	private RectF recFond;
	public RectF getRecFond() {
		return recFond;
	}
	public void setRecFond(RectF recFond) {
		this.recFond = recFond;
	}

	public MoteurGraphique(Context pContext) {
		super(pContext);
		
		
		
		
		mSurfaceHolder = getHolder();
	
		//ecouteurEcran = new EcouteurToucherEcran(mSurfaceHolder, null);
		
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();

		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		
		mPaintCarre = new Paint();
		mPaintCarre.setColor(Color.RED);
		
		//Log.v("testo", "MG / taille : " + this.largeurEcran);
		
	}
	
	public void setGrille(Grille uneGrille) {
		this.grille = uneGrille;
	}
	
	public Grille getGrille() {
		return grille;
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		mThread.keepDrawing = true;
		mThread.start();
		
		this.bitmapTableau = mPhysique.constructionFondGrille(getWidth(), getHeight());
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

	//VALEUR DE TESTE TODO
	int i = 1;
	//PARCOURS DE COLLECTION SUREMENT

	int i2 = 1;
	//******
	@Override
	protected void onDraw(Canvas pCanvas) {
		
		//DESSINE LE FOND
		pCanvas.drawColor(Color.WHITE);
		
		//AFFICHAGE DE LA GRILLE
		pCanvas.drawBitmap(this.bitmapTableau, 54, 192, mPaint);
        //pCanvas.drawBitmap(this.bitmapTableau, (getWidth() - this.bitmapTableau.getWidth()) / 2, 192, mPaint);
		
		Bitmap grilleTemp = Bitmap.createBitmap(this.bitmapTableau);
		Canvas c = new Canvas(grilleTemp);
		
		pCanvas.drawBitmap(grilleTemp, 54, 192, mPaint);
		
		drawTextDeTest(pCanvas, "Height recFond : "+ getHeight(),50,50);
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
