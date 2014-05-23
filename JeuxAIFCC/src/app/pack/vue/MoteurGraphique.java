package app.pack.vue;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

import app.pack.gen.MainActivity;
import app.pack.modele.ClasseurImages;
import app.pack.modele.TuileGraphique;


/**
 * CLasse permettant l'affichage du jeux
 */
public class MoteurGraphique extends SurfaceView implements SurfaceHolder.Callback {

	private SurfaceHolder mSurfaceHolder;

	private DrawingThread mThread;
	private  int mouvement = 0;
	private ArrayList<TuileGraphique> listTuilesG = null;
    private Context pContext;

    private boolean autoMarche = true;
    private boolean mouvementFini = false;
    private boolean animationFini = false;
	/*
	 * MODIF YANNICK
	 */
	public ClasseurImages classeurImages = null;
	/*
	 * END MODIF YANNICK
	 */

    public int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

	/**
	 * Constructeur
	 *
	 * @param pContext
	 */
	public MoteurGraphique(Context context) {
		super(context);
	}

    /* MODIF AUREL
    /**
     * Constructeur pour la récupération d'une surface view dans un fichier XML
     *
     * @param context
     * @param attrs
     */
    public MoteurGraphique(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.pContext = context;
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        mThread = new DrawingThread();

        Log.v("tuileM", "Constructeur MG : " + getWidth());
        //this.classeurImages = new ClasseurImages(context);

        this.score = 0;
    }

    public MoteurGraphique(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec,heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int size = width > height ? height : width;
        setMeasuredDimension(size, size);
        Log.v("tuileM", "onMeasure : " + width);
    }
    /*
    * FIN MODIF AUREL
    */


	//##################
	// GETTERS - SETTERS
	//##################
	/*
	 * MODIF YANNICK
	 */

	/*public int getMouvement() {
		return mouvement;
	}*/

	public void setMouvement(int mouvement) {
        this.mouvementFini = false;
        //TODO pour indiquer fini
		this.mouvement = mouvement;
	}
	/*
	 * END MODIF YANNICK
	 */
	
	/*public ArrayList<TuileGraphique> getListTuilesG() {
		return listTuilesG;
	}*/

	public void setListTuilesG(ArrayList<TuileGraphique> listTuilesG) {
		this.listTuilesG = listTuilesG;
	}

    /**
     * Au démarage de l'application
     * @param holder
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {



        mThread.setActiveDessineThread(true);
        try {
            mThread.start();
        this.classeurImages = new ClasseurImages(this.pContext);
        } catch (IllegalThreadStateException e) {
            //mThread = new DrawingThread();
            //mThread.start();
            //mThread.run();
            // listTuilesG.add(new TuileGraphique(new Position(1,1),new Position(1,1),2));
        }








    }

    /**
     * Changement de la d'orientation de la surface
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {

        //mThread.keepDrawing = true;
        //mThread.start();
    }

    /**
     * Lors de la fermeture de l'application
     * @param holder
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("test1","entre destroy$$$$$$$$$$$$");
        // TODO Auto-generated method stub
        mThread.setActiveDessineThread(false);


    }

    /**
     * KILL
     */
    public void kill() {
        boolean retry = true;
        while (retry) {
            try {
                mThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }
    /**
     * Methode de dessins
     * @param pCanvas
     */
    @Override
    protected void onDraw(Canvas pCanvas) {

        // Couleur de fond de la surface view
        pCanvas.drawColor(Color.WHITE);

        // Affichage du fond de la grille

        //pCanvas.drawBitmap(this.classeurImages.getFondImage(), 0, 192, null);
        //Log.i("test","ok");

        if (this.classeurImages != null) {
            Bitmap fondGrille = this.classeurImages.getFondImage();
            fondGrille = Bitmap.createScaledBitmap(fondGrille, this.getWidth(), this.getWidth(), false);
            pCanvas.drawBitmap(fondGrille, 0, 0, null);

            //Log.v("testo", "X : " + fondTableau.getWidth() + " / Y : " + fondTableau.getHeight());
            //pCanvas.drawBitmap(this.bitmapTableau, (getWidth() - this.bitmapTableau.getWidth()) / 2, 192, mPaint);
            //Log.v("testo", "Mouvement : " + mouvement);
            int fini = 0;
            if (this.listTuilesG != null) {
                for (TuileGraphique uneTuile : this.listTuilesG) {
				/*
				 *  MODIF YANNICK
				 */
                    int valeurTuile = uneTuile.getValeur();
                    //int valeurTuilePasse = uneTuile.getValeurPrecendant();
                    //int indiceTuileApparision = uneTuile.getAnimationApparition();
				/*
				 * END MODIF YANNICK ok
				 */
                    if (valeurTuile != 0) {
                        //Log.v("testo", "X : " + uneTuile.getPostionActuel().getPosX() + " / Y : " + uneTuile.getPostionActuel().getPosY());

                        //carreGraphique = new TuileGraphique(uneTuile, this.fondTableau.getWidth());
					/*
					 * MODIF YANNICK
					 */
                        Bitmap imageTuile;

                        if (uneTuile.isAleatoire()) {

                            if (mouvementFini && uneTuile.getAnimationApparition() < 10) {


                                uneTuile.setAnimationApparition(uneTuile.getAnimationApparition() + 1);


                            }

                            imageTuile = classeurImages.getApparition(uneTuile.getAnimationApparition(), uneTuile.getValeur());

                            if (uneTuile.getAnimationApparition() >= 9) {

                                uneTuile.setAleatoire(false);
                                uneTuile.setAnimationApparition(0);
                                // apparationGif = 0;

                            }


                        } else {
                            //TODO IF MODIF
                            imageTuile = classeurImages.getTuileImage(valeurTuile);

                        }

                        if (!uneTuile.isAleatoire() || (uneTuile.getAnimationApparition() != 0 && mouvementFini)) {
                            switch (this.mouvement) {
                                case 1:
                                    Log.v("testo", "*********************** GAUCHE 1*********************");
                                    //Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
                                    //  Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
                                    if (uneTuile.getPosGPasse().getY1() >= uneTuile.getPosGActuel().getY1() && !(uneTuile.getPosGPasse().getY1() == uneTuile.getPosGActuel().getY1())) {
                                        //classeurImages.getTuileImage(uneTuile.getValeur())
								/*
								 * MODIF YANNICK
								 */
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1(), null);
                                        //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
								/*
								 * END MODIF YANNICK
								 */

                                        // Log.v("testo", "Valeur Y1 départ  : " + uneTuile.getPosGPasse().getY1());
                                        uneTuile.mouvGauche(100);
                                        // Log.v("testo", "Valeur Y1 après modif : " + uneTuile.getPosGPasse().getY1());
                                    } else {
                                        //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1(), null);
                                        fini++;


                                        uneTuile.setPosGPasse(uneTuile.getPosGActuel());


                                        //classeurImages
                                        //this.mouvement = 0;
                                    }
                                    break;
                                case 2:
                                    Log.v("testo", "*********************** DROITE *********************");
                                    //Log.v("testo", "Valeur Y1 PASSE : " + uneTuile.getPosGPasse().getY1());
                                    //Log.v("testo", "Valeur Y1 ACTUEL : " + uneTuile.getPosGActuel().getY1());
                                    if (uneTuile.getPosGPasse().getY1() <= uneTuile.getPosGActuel().getY1() && !(uneTuile.getPosGPasse().getY1() == uneTuile.getPosGActuel().getY1())) {

                                        ////classeurImages.getTuileImage(uneTuile.getValeur())
                                        //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1() + 192, null);
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1(), null);


                                        uneTuile.mouvDroite(100);
                                        // Log.v("testo", "Valeur Y1 : " + uneTuile.getPosGPasse().getY1());
                                    } else {
                                        ////classeurImages.getTuileImage(uneTuile.getValeur())
                                        //pCanvas.drawBitmap(uneTuile.getImgCarre(), uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1() + 192, null);

                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1(), null);
                                        //this.mouvement = 0;
                                        fini++;
                                        uneTuile.setPosGPasse(uneTuile.getPosGActuel());


                                    }
                                    break;
                                case 3:
                                    Log.v("testo", "*********************** HAUT *********************");
                                    if (uneTuile.getPosGPasse().getX1() >= uneTuile.getPosGActuel().getX1() && !(uneTuile.getPosGPasse().getX1() == uneTuile.getPosGActuel().getX1())) {


                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1(), null);

                                        uneTuile.mouvHaut(100);
                                        // Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
                                    } else {
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1(), null);
                                        //this.mouvement = 0;
                                        fini++;
                                        uneTuile.setPosGPasse(uneTuile.getPosGActuel());


                                    }
                                    break;
                                case 4:
                                    Log.v("testo", "*********************** BAS *********************");
                                    if (uneTuile.getPosGPasse().getX1() <= uneTuile.getPosGActuel().getX1() && !(uneTuile.getPosGPasse().getX1() == uneTuile.getPosGActuel().getX1())) {
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGPasse().getY1(), uneTuile.getPosGPasse().getX1(), null);
                                        uneTuile.mouvBas(100);
                                        // Log.v("testo", "Valeur X1 : " + uneTuile.getPosGPasse().getX1());
                                    } else {
                                        pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1(), null);
                                        //this.mouvement = 0;
                                        fini++;
                                        uneTuile.setPosGPasse(uneTuile.getPosGActuel());

                                    }
                                    break;
                                default:
                                    Log.v("testo", "*********************** AUCUN *********************");
                                    pCanvas.drawBitmap(imageTuile, uneTuile.getPosGActuel().getY1(), uneTuile.getPosGActuel().getX1(), null);
                                    fini++;
                                    uneTuile.setPosGPasse(uneTuile.getPosGActuel());


                                    break;
                            }
                        } else {
                            fini++;
                        }


                    }

                    //Log.v("testo", "X : " + posG.getX1() + " Y : " + posG.getY1());
                }
                if (fini == listTuilesG.size()) {
                    mouvementFini = true;

                    for (int i = 0; i < listTuilesG.size(); i++) {

                        if (listTuilesG.get(i).isPrecedant()) {
                            listTuilesG.get(i).setPrecendant(false);
                            listTuilesG.remove(i);
                        }

                    }

                } else {


                    mouvementFini = false;
                }
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

    /**
     * Class THREAD permettant de faire un boucle infini
     */
    private class DrawingThread extends Thread {
        public boolean autoDessine = true;

        //  public int modeThread = 0;
        //  private Object mPauseLock;


        /**
         * Methode qui lance la boucle infini
         */
        @Override
        public void run() {


            Canvas canvas;
            while (autoMarche) {
                while (autoDessine) {
                    canvas = null;

                    try {
                        canvas = mSurfaceHolder.lockCanvas(null);
                        synchronized (mSurfaceHolder) {
                            onDraw(canvas);
                            try {

                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (canvas != null)
                            mSurfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }
        private void setActiveDessineThread(boolean run) {

            this.autoDessine = run;



        }

    }
/*
        public void onPause() {
            synchronized (mPauseLock) {
                keepDrawing = false;
                modeThread = 1;
            }
        }


        public void onResume() {
            synchronized (mPauseLock) {
                modeThread = 3;
                keepDrawing = true;
                mPauseLock.notifyAll();
            }
        }*/

    /**
     * Activer ou desactiver le dessin dans le thread
     * @param boolean
     */
    public void setPauseResumeThread(boolean activeThread) {
        mThread.setActiveDessineThread(activeThread);
    }


    /**
     * Si le mouvement des tuiles et fini
     * @return boolean
     */
    public boolean isMouvementFini() {
        return mouvementFini;
    }

    /*public void setMouvementFini(boolean mouvementFini) {
        this.mouvementFini = mouvementFini;
    }*/
}
