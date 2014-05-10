package app.pack.vue;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import app.pack.modele.ClasseurImages;
import app.pack.modele.TuileGraphique;

@SuppressLint("WrongCall")
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {
	
	private SurfaceHolder mSurfaceHolder;
	private DrawingThread mThread;
	private  int mouvement = 0;
	private ArrayList<TuileGraphique> listTuilesG = null;

    //private int apparationGif = 1;
    private boolean mouvementFini = false;
	/* 
	 * MODIF YANNICK
	 */
	public ClasseurImages classeurImages = null;
	/*
	 * END MODIF YANNICK
	 */

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
		/* 
		 * MODIF YANNICK
		 */
		this.classeurImages = new ClasseurImages(pContext);
		/*
		 * END MODIF YANNICK
		 */
	
	}
	
	//##################
	// GETTERS - SETTERS
	//##################
	/*
	 * MODIF YANNICK
	 */

	public int getMouvement() {
		return mouvement;
	}

	public void setMouvement(int mouvement) {
        this.mouvementFini = false;
        //TODO pour indiquer fini
		this.mouvement = mouvement;
	}
	/*
	 * END MODIF YANNICK
	 */
	
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
		
		// Affichage du fond de la grille
		
		pCanvas.drawBitmap(this.classeurImages.getFondImage(), 0, 192, null);
		//Log.i("test","ok");
		
		//Log.v("testo", "X : " + fondTableau.getWidth() + " / Y : " + fondTableau.getHeight());
        //pCanvas.drawBitmap(this.bitmapTableau, (getWidth() - this.bitmapTableau.getWidth()) / 2, 192, mPaint);
		//Log.v("testo", "Mouvement : " + mouvement);
		int fini = 0;
		if(this.listTuilesG != null){
			for(TuileGraphique uneTuile : this.listTuilesG){
				/*
				 *  MODIF YANNICK
				 */
				int valeurTuile = uneTuile.getValeur();
				/*
				 * END MODIF YANNICK ok
				 */
				if(valeurTuile != 0){
					//Log.v("testo", "X : " + uneTuile.getPostionActuel().getPosX() + " / Y : " + uneTuile.getPostionActuel().getPosY());
					
					//carreGraphique = new TuileGraphique(uneTuile, this.fondTableau.getWidth());
					/*
					 * MODIF YANNICK
					 */
                    Bitmap imageTuile = classeurImages.getTuileImage(valeurTuile);

                    if( uneTuile.isAleatoire()) {
                        //uneTuile.setAleatoire(false);
                        //valeurTuile = 0;
                      //  Log.i("test1","aléa"+uneTuile.getValeur());
                     //   if(mouvementFini) {
                            //uneTuile.setAleatoire(false);
                       //     imageTuile = classeurImages.getTuileImage(valeurTuile);
                           /*
                           imageTuile = classeurImages.getApparition(apparationGif);
                           this.apparationGif++;
                            if(apparationGif >= 10) {
                               uneTuile.setAleatoire(false);
                                apparationGif = 1;
                            }*/
                    }
                    //Log.i("test1"," fini ?---> "+this.mouvementFini);

					//Bitmap
					/*
					 * END MODIF YANNICK
					 */
                   // if(!uneTuile.isAleatoire()) {
                        switch(this.mouvement){
                            case 1:
                                Log.v("testo", "*********************** GAUCHE 1*********************");
                                Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
                                Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
                                if(uneTuile.getPosGPasse().getY1() >= uneTuile.getPosGActuel().getY1()){
                                    //classeurImages.getTuileImage(uneTuile.getValeur())
								/*
								 * MODIF YANNICK
								 */
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
                                    //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								/*
								 * END MODIF YANNICK
								 */

                                    Log.v("testo", "Valeur Y1 départ  : " + uneTuile.getPosGPasse().getY1());
                                    uneTuile.mouvGauche(100);
                                    Log.v("testo", "Valeur Y1 après modif : " + uneTuile.getPosGPasse().getY1());
                                }else{
                                    //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    fini++;
                                    //classeurImages
                                    //this.mouvement = 0;
                                }
                                break;
                            case 2:
                                Log.v("testo", "*********************** DROITE *********************");
                                Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
                                Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
                                if(uneTuile.getPosGPasse().getY1() <= uneTuile.getPosGActuel().getY1()){

                                    ////classeurImages.getTuileImage(uneTuile.getValeur())
                                    //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);


                                    uneTuile.mouvDroite(100);
                                    Log.v("testo", "Valeur Y1 : " + uneTuile.getPosGPasse().getY1());
                                }else{
                                    ////classeurImages.getTuileImage(uneTuile.getValeur())
                                    //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    fini++;
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    //this.mouvement = 0;
                                }
                                break;
                            case 3:
                                Log.v("testo", "*********************** HAUT *********************");
                                if(uneTuile.getPosGPasse().getX1() >= uneTuile.getPosGActuel().getX1()){



                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);

                                    uneTuile.mouvHaut(100);
                                    Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
                                }else{
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    //this.mouvement = 0;
                                    fini++;
                                }
                                break;
                            case 4:
                                Log.v("testo", "*********************** BAS *********************");
                                if(uneTuile.getPosGPasse().getX1() <= uneTuile.getPosGActuel().getX1()){
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
                                    uneTuile.mouvBas(100);
                                    Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
                                }else{
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                    //this.mouvement = 0;
                                    fini++;
                                }
                                break;
                            default:
                                Log.v("testo", "*********************** AUCUN *********************");
                                pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                fini++;
                                break;
                        }
                   // } else {
                    //    fini++;
                   // }

					//pCanvas.drawBitmap(carreGraphique.getImgCarre(), carreGraphique.getPosGActuel().getY1(), carreGraphique.getPosGActuel().getX1() + 192, null);
					
					/*if(carreGraphique.getPosGPasse().getX1() != carreGraphique.getPosGActuel().getX1()){
						pCanvas.drawBitmap(carreGraphique.getImgCarre(), carreGraphique.getPosGPasse().getY1(), carreGraphique.getPosGPasse().getX1() + 192, null);
						carreGraphique.avancerTuile(1);
					}*/
					//PositionGraphique posG = new PositionGraphique(uneTuile.getPostionActuel(), this.fondTableau.getWidth());
					
				}
				
				//Log.v("testo", "X : " + posG.getX1() + " Y : " + posG.getY1());
			}
			if(fini == listTuilesG.size()) {
                mouvementFini = true;

            } else {
                mouvementFini = false;
            }
		}
		
	}

/*	private void drawTextDeTest(Canvas canvas, String stringTest, int posX, int posY) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		String text = stringTest;
		canvas.drawText(text, posX, posY, paint);
	}*/
	
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
					Thread.sleep(10);
				} catch (InterruptedException e) {
				}
			}
		}
	}

    public void setMouvementFini(boolean mouvementFini) {
        this.mouvementFini = mouvementFini;
    }
}
