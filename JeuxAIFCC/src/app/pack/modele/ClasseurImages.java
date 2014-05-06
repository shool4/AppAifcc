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
				BitmapFactory.decodeResource(context.getResources(), R.drawable.fond_grille)
				};
		
		
		//this.imgCarre = Bitmap.createBitmap(this.tailleFond, this.tailleFond, Config.ARGB_8888);
		
		
	
	}

	/**
	 * 
	 * @param indiceImage
	 * @return
	 */

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
