package app.pack.modele;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
                BitmapFactory.decodeResource(context.getResources(), R.drawable.cini9)

				};
		
		
		//this.imgCarre = Bitmap.createBitmap(this.tailleFond, this.tailleFond, Config.ARGB_8888);
		
		
	
	}

	/**
	 * 
	 * @param indiceImage
	 * @return
	 */
    public Bitmap getApparition(int debApp) {
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




        }
        return null;

    }

	public Bitmap getFondImage() {
		return classeurImageTuile[11];
	}

	public Bitmap getTuileImage(int valeurTuile) {
		switch (valeurTuile) {
			case 2:
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
