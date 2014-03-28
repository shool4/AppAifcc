package app.pack.modele;

import java.util.ArrayList;
//DECLARER EN INTEGER AU CAS SI OBJECT DANS TB
public class MoteurPhysique {
	private ArrayList<ArrayList<Integer>> tableau = null;
	private ArrayList<Integer> ligne = null;
	private int tailleMax = 4;

	public MoteurPhysique() {
		tableau = new ArrayList<ArrayList<Integer>>();
		ligne = new ArrayList<Integer>();

		for (int colonne = 0; colonne < tailleMax; colonne++) {
			for (int ligne = 0; ligne < tailleMax; ligne++) {
				//ligne.add(Integer.0);
			}
			tableau.add(ligne);
		}

		// ligne = new ArrayList<Integer>();
	}

	public void setTailleMax(int tailleMax) {
		this.tailleMax = tailleMax;
	}

	public int getTailleMax() {
		return this.tailleMax;
	}

	/*
	 * Changer la position d'un case en tel position
	 */
	public void changeCaseTo(int posX, int posY, int posXvers, int posYvers) {
		// tableauChiffre.
	}
}
