package app.pack.modele;

import java.util.Vector;

import android.util.Log;

public class GestionMouvement {
	/* la position de départ du doigt */
	private float start_x;
	private float start_y;
	/* la position final du doigt */
	private float end_x;
	private float end_y;
	/* la dimension de l'écran sur lequel a été réalisé le déplacement */
	private float view_dx;
	private float view_dy;
	/* L'ensemble des points intermédiaires */
	private Vector<Float> move_x;
	private Vector<Float> move_y;
	/* La durée du déplacement */
	private float duration;
	/* L'état actuel du mouvement du doigt */
	private float state;
	/* Les différents états possible du déplacement */
	public static final int STATE_VOID = -1;
	public static final int STATE_START = 0;
	public static final int STATE_MOVE = 1;
	public static final int STATE_END = 2;
	public static final int STATE_CANCEL = 3;

	public GestionMouvement() {
		move_x = new Vector<Float>();
		move_y = new Vector<Float>();
		reinitialiserEtat();
	}

	public void reinitialiserEtat() {
		state = STATE_VOID;
		start_x = -1.0f;
		start_y = -1.0f;
		end_x = -1.0f;
		end_y = -1.0f;
		duration = -1.0f;
		view_dx = -1.0f;
		view_dy = -1.0f;
		move_x.clear();
		move_y.clear();
	}

	public void setDimensionView(float dx, float dy) {
		view_dx = dx;
		view_dy = dy;
		Log.i("test1","OK setDimensionView");
	}

	public void setPositionDepart(float x, float y) {
		if (state == STATE_VOID) {
			start_x = x;
			start_y = y;
			duration = 0;
		}
		Log.i("test1","OK setPositionDepart");
		state = STATE_START;
	}

	public void addPositionBouger(float x, float y) {
		if ((state == STATE_START) || (state == STATE_MOVE)) {
			move_x.add(x);
			move_y.add(y);
			state = STATE_MOVE;
		}
	}

	public void setPositionFin(float x, float y, float laps) {
		if (state != STATE_END) {
			end_x = x;
			end_y = y;
			duration = laps;
		}
		state = STATE_END;
	}

	/*
	 * Un mouvement sera considéré comme un glissé vers la gauche ou la droite
	 * Si la longueur horizontale entre le point de départ et de fin est
	 * supérieure à 80% de celle de l'écran et à moins de 20 % de la hauteur de
	 * l'écran. De plus la durée du déplacement devra être inférieure à la durée
	 * maxDuration en ms. La valeur retourné sera de -1 pour un déplacement
	 * valide de gauche à droite, +1 pour un déplacement valide de droite à
	 * gauche et 0 pour les autres cas.
	 */
	public int indexSlideHorizontal(int maxDuration) {
		int res = 0;
		float dx = end_x - start_x;
		float dy = end_y - start_y;
		float dxa = Math.abs(dx);
		float dya = Math.abs(dy);
		boolean okx = (dxa > 0.8 * view_dx);
		boolean oky = (dya < 0.2 * view_dy);
		boolean okTime = (duration < (int) maxDuration);
		boolean valide = okx && oky && okTime;
		if (valide) {
			res = (int) Math.signum(dx) * -1;
		}
		return res;
	}
	/*
	 * Un mouvement sera considéré comme un glissé vers la gauche ou la droite
	 * Si la longueur horizontale entre le point de départ et de fin est
	 * supérieure à 80% de celle de l'écran et à moins de 20 % de la hauteur de
	 * l'écran. De plus la durée du déplacement devra être inférieure à la durée
	 * maxDuration en ms. La valeur retourné sera de -1 pour un déplacement
	 * valide de gauche à droite, +1 pour un déplacement valide de droite à
	 * gauche et 0 pour les autres cas.
	 */
	public int indexSlideVertical(int maxDuration) {
		int res = 0;
		float dx = end_x - start_x;
		float dy = end_y - start_y;
		float dxa = Math.abs(dx);
		float dya = Math.abs(dy);
		
		boolean okx = (dxa > 0.2 * view_dx);
		boolean oky = (dya < 0.8 * view_dy);
		
		
		boolean okTime = (duration < (int) maxDuration);
		
		boolean valide = okx && oky && okTime;
		
		if (valide) {
			res = (int) Math.signum(dy) * -1;
		}
		return res;
	}
	/*
	 * Cette méthode permet également de vérifier que l'ensemble des points
	 * intermédiaires du mouvement du doigt sont également globalement sur la
	 * même ligne entre la position de début et de fin du mouvement
	 */
	public boolean valideMouvementHonrizontal(float pourcentage) {
		float dx = end_x - start_x;
		float dy = end_y - start_y;
		if (dy == 0.0)
			return false;
		float delta = dy / dx;
		float dy_max = Math.abs(dx * pourcentage);
		int nbr = move_x.size();
		int i;
		for (i = 0; i < nbr; i++) {
			float vx = move_x.get(i);
			float vy = move_y.get(i);
			float evy = start_y + (vx - start_x) * delta;
			float ey = Math.abs(vy - evy);
			Log.v("smb116rssview", "ey = " + ey);
			if (ey > dy_max)
				return false;
		}
		return true;
	}
	/*
	 * Cette méthode permet également de vérifier que l'ensemble des points
	 * intermédiaires du mouvement du doigt sont également globalement sur la
	 * même ligne entre la position de début et de fin du mouvement
	 */
	public boolean valideMouvementVertical(float pourcentage) {
		float dx = end_x - start_x;
		float dy = end_y - start_y;
		
		if (dx == 0.0)
			return false;
		
		float delta = dx / dy;
		
		
		float dx_max = Math.abs(dy * pourcentage);
		
		
		int nbr = move_y.size();
		
		int i;
		for (i = 0; i < nbr; i++) {
			float vx = move_x.get(i);
			float vy = move_y.get(i);
			
			
			float evx = start_x + (vy - start_y) * delta;
			
			float ex = Math.abs(vx - evx);
			
			Log.v("smb116rssview", "ex = " + ex);
			
			if (ex > dx_max)
				return false;
		}
		return true;
	}
}
