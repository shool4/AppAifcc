package app.pack.modele;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import app.pack.gen.MainActivity;
import app.pack.gen.R;

public class ClasseurImages {
	private Bitmap[] classeurImageTuile = null;

	/**
	 * 
	 * @param context
	 */
	public ClasseurImages(Context context) {
		// Init du tableau de Bitmap
		/*Bitmap carre = BitmapFactory.decodeResource(context.getResources(), R.drawable.c2);
		carre = Bitmap.createScaledBitmap(carre, carre.getWidth(), carre.getHeight(), false);
		
		classeurImageTuile = new Bitmap[] { 
				carre,
				carre,
				carre,
				carre,
				carre
				
		};*/

		classeurImageTuile = new Bitmap[] {
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c2),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c4),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c8),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c16),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c32),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c64),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c128),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c256),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c512),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c1024),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.c2048),
				BitmapFactory.decodeResource(context.getResources(), R.drawable.fond_grille),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini1),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini2),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini3),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini4),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini5),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini6),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini7),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini8),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini9),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini10),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini1),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini2),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini3),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini4),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini5),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini6),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini7),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini8),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini9),
                BitmapFactory.decodeResource(context.getResources(), R.drawable.c4ini10)

				};

        Log.v("tuileM", "Taille grille : " + MainActivity.tailleGrille + " / Taille tuile : " + MainActivity.tailleTuile);
        for(int i = 0; i < classeurImageTuile.length; i++){
            if(i != 11){
                classeurImageTuile[i] = Bitmap.createScaledBitmap(classeurImageTuile[i], MainActivity.tailleTuile, MainActivity.tailleTuile, false );
            }
        }

		//this.imgCarre = Bitmap.createBitmap(this.tailleFond, this.tailleFond, Config.ARGB_8888);
	}

	/**
	 * 
	 * @param
	 * @return
	 */
    public Bitmap getApparition(int debApp, int valeurTuile) {
        if(valeurTuile == 2) {
            switch (debApp) {
                case 1:
                    return classeurImageTuile[12];

                case 2:
                    return classeurImageTuile[13];

                case 3:
                    return classeurImageTuile[14];

                case 4:
                    return classeurImageTuile[15];

                case 5:
                    return classeurImageTuile[16];

                case 6:
                    return classeurImageTuile[17];

                case 7:
                    return classeurImageTuile[18];

                case 8:
                    return classeurImageTuile[19];

                case 9:
                    return classeurImageTuile[20];

                case 10:
                    return classeurImageTuile[21];

            }
        } else if (valeurTuile == 4 ) {
            switch (debApp) {
                case 1:
                    return classeurImageTuile[22];

                case 2:
                    return classeurImageTuile[23];

                case 3:
                    return classeurImageTuile[24];

                case 4:
                    return classeurImageTuile[25];

                case 5:
                    return classeurImageTuile[26];

                case 6:
                    return classeurImageTuile[27];

                case 7:
                    return classeurImageTuile[28];

                case 8:
                    return classeurImageTuile[29];

                case 9:
                    return classeurImageTuile[30];

                case 10:
                    return classeurImageTuile[31];

            }
        }
        return null;

    }

	public Bitmap getFondImage() {
		return classeurImageTuile[11];
	}

	public Bitmap getTuileImage(int valeurTuile) {
		switch (valeurTuile) {
			case 2:
                //classeurImageTuile[0] = Bitmap.createScaledBitmap(classeurImageTuile[0], MainActivity.tailleTuile, MainActivity.tailleTuile, false );
				return classeurImageTuile[0];
			
			case 4:
				return classeurImageTuile[1];
			
			case 8:
				return classeurImageTuile[2];
				
			case 16:
				return classeurImageTuile[3];
				
			case 32:
				return classeurImageTuile[4];
				
			case 64:
				return classeurImageTuile[5];
				
			case 128:
				return classeurImageTuile[6];
				
			case 256:
				return classeurImageTuile[7];
				
			case 512:
				return classeurImageTuile[8];
				
			case 1024:
				return classeurImageTuile[9];
				
			case 2048:
				return classeurImageTuile[10];


				
		}
		return null;
		
	}
}
