package app.pack.vue;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Type;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import app.pack.gen.R;
import app.pack.modele.Carre;

@SuppressLint("WrongCall")
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {
	
	SurfaceHolder mSurfaceHolder;
	DrawingThread mThread;
	Paint mPaint; 
	//TABLEAU DE VALEUR DES POINTEURS VERS LES IMAGES
	
	
	public void yannick(){
		int blabla;
	}
	
	public MoteurGraphique(Context pContext) {
		super(pContext);
		mSurfaceHolder = getHolder();
		mSurfaceHolder.addCallback(this);
		mThread = new DrawingThread();
		
		mPaint = new Paint();
		mPaint.setColor(Color.BLACK);
		
		//VALEUR DE TEST TODO
		testCarre.posY= 50;
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		mThread.keepDrawing = true;
		mThread.start();

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
	Carre testCarre = new Carre(1, 0,0 );
	Carre testCarre5 = new Carre(1, 0,60 );
	int i2 = 1;
	//******
	@Override
	protected void onDraw(Canvas pCanvas) {
		
		//DISINE LE FOND
		pCanvas.drawColor(Color.YELLOW);
		//DESINE LE CADRIYAGE DE TEST
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.carre);
		bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()*2, bitmap.getHeight()*2, false);
		pCanvas.drawBitmap(bitmap, 50, 500, null);
		
		
		//ZONE DE TEST DU DEPLACEMENT DU CARRER 
		// LES POS X ET POS Y Doivent etre applique sur l'objet et non en dur ici <!> TODO

		//Carre testCarre2 = new Carre(Type.T2, i, i);
		//pCanvas.drawRect(testCarre2.getRectangle(), mPaint);
		//i++;
		
		//COMMENT IL FAUT FAIRE TODO
		pCanvas.drawRect(testCarre.getRectangle(), mPaint);

		pCanvas.drawRect(testCarre5.getRectangle(), mPaint);
		
		if(testCarre.posX >= 20) {
			testCarre.posX  = 0;
			testCarre.posY = 5; 
			testCarre5.posX =testCarre.posY+1;

			
		} else {
			testCarre.posX =testCarre.posX+1;

			testCarre5.posX =testCarre.posX+1;
		}
		

		drawTextDeTest(pCanvas, "Valeur X pos carre :"+String.valueOf(testCarre.posX),100,30);
		drawTextDeTest(pCanvas, "Valeur i :"+String.valueOf(i),200,100);
	}

	/*private void drawText(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		String text = "blabla";
		canvas.drawText(text, 100, 30, paint);
	}*/
	
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
