package app.pack.modele;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Random;

import android.R.integer;
import android.util.Log;

public class Grille {

	private Tuile[][] grille = null;

	// private List<List<Carre>> tableau = null;

	/**
	 * Constructeur 
	 * Dimensionne celons la taille et charge la grille de tuile zeros
	 * 
	 */
	public Grille(int tailleY, int tailleX) {
		super();
		this.grille = new Tuile[tailleY][tailleX];
		this.mettreZero();

	}
	/**
	 * Charge tout le grille de tuile Zeros
	 * 
	 * @return void
	 * 
	 */
	private void mettreZero() {
		for (int i = 0; i < this.getSizeY(); i++) {
			for (int u = 0; u < this.getSizeX(); u++) {
				this.grille[i][u] = new Tuile(i, u, 0);
			}
		}
	}

	public int getSizeX() { // get the x length of the playing field
		return grille[0].length;
	}

	public int getSizeY() { // get the y length of the playing field
		return grille.length;
	}

	/**
	 * Get la taille grille
	 * 
	 * @return int
	 * 
	 */
	public int getSize() {
		return getSizeX() * getSizeY();
	}

	/**
	 * check Game One ou NON
	 * 
	 * @return 	Boolean
	 * 
	 */
	public Boolean isGameLost() {
		if (getNombreTuileVide() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * check si jeux est gagnant --> check ou est un 2048
	 * 
	 * @return
	 * 
	 */
	public Boolean isGameWon() {
		for (int i = 0; i < getSizeY(); i++) {
			for (int u = 0; u < getSizeX(); u++) {
				if (this.grille[i][u].getValeur() == 2048) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Obtenir nombre de d'espace de tuile vide
	 * 
	 * @return
	 * 
	 */
	public int getNombreTuileVide() {
		int sum = 0;
		for (int i = 0; i < getSizeY(); i++) {
			for (int u = 0; u < getSizeX(); u++) {
				if (this.grille[i][u].getValeur() == 0) {
					sum++;
				}
			}
		}
		return sum;
	}

	public int getNombreOccurenceTuileNonVide() {
		return getSize() - getNombreTuileVide();
	}

	/**
	 * Obtenir les tuile ou il n'y pas de valeur assigné
	 * 
	 * @return
	 */
	public ArrayList<Tuile> getFreeTuille() {
		ArrayList<Tuile> freeTuile = new ArrayList<Tuile>();
		for (int i = 0; i < getSizeY(); i++) {
			for (int u = 0; u < getSizeX(); u++) {
				if (this.grille[i][u].getValeur() == 0) {
					freeTuile.add(this.grille[i][u]);
				}
			}
		}
		return freeTuile;
	}

	/**
	 * set valeur d'une tuile en fonction (x,y) sur la grille
	 * 
	 * @param x
	 * @param y
	 * @param value
	 * 
	 */
	public void setValue(int y, int x, int value) {

		this.grille[y][x].setValeur(value);
	}

	/**
	 * ajoute un carre en fonction de ca position
	 * 
	 * @param 	Tuile
	 * @return 	Boolean
	 * 
	 */

	// ************************************************************************************
	// MISE A JOUR
	// ************************************************************************************
	/*
	 * Mise a jour des positino des carrées dans le tableau
	 */

	public void deplacementDroite() {
	}
	/**
	 * Permet le deplacement des tuiles vers la gauche
	 * 
	 * @param 	void
	 * @return 	les tuiles à bouger coter graphique
	 * 
	 */
	public void deplacementGauche() {
		Log.i("test1", "************ GAUCHE **************");
		// Pacour des Y

		ArrayList<Tuile> tuileMerged = new ArrayList<Tuile>();
		// int indiceIncrement = 0;

		for (int i = 0; i < this.getSizeY(); i++) {

			int j = 0;
			// On statue sur un tuile pour faire un parcours des tuiles
			// suivante
			for (j = 0; j < this.getSizeX() - 1; j++) {

				// Pacours sur les tuiles suivante de la tuile ou on statue
				for (int j2 = j + 1; j2 <= this.getSizeX() - 1; j2++) {

					// Si la valeur courante et différente de zeros
					if (this.grille[i][j].getValeur() != 0) {

						// Si la valeur courant est = a la valeur prochaine
						// Si pas egal on avant 1 tuile la valeur prochaine
						// pour comparais
						if (this.grille[i][j].getValeur() == this.grille[i][j2].getValeur()) {
							if (!tuileMerged.contains(this.grille[i][j])) {
								tuileMerged.add(this.grille[i][j]);
							}

							this.grille[i][j].merge(this.grille[i][j2]);
							this.grille[i][j2].setValeur(0);

						}

						break;

					} else {
						// Si la valeur courant et 0 on avance le zeros a la
						// case prochaine
						this.inverseX(i, j, j2);
					}

				}
			}

		}

		debog_ListTuile(tuileMerged, "tuileMerge");

	}

	/**
	 * Change 2 tuile sur un axe Y (i) et un indice X (j et j2)
	 * 
	 * @param 	int i pour l'axe Y, j et j2 pour l'axe x
	 * @return 	void
	 * 
	 */
	public void inverseX(int i, int j, int j2) {
		// Var tuile 0 et position de son prédeseur
		Tuile unTuileTemp = this.grille[i][j];
		Position nouvellePositionFuturZeros = this.grille[i][j2].getPostionActuel();

		// CHangement des position
		this.grille[i][j2].setPositionActuel(unTuileTemp.getPostionActuel());
		unTuileTemp.setPositionActuel(nouvellePositionFuturZeros);

		// Inverse les tuiles
		this.grille[i][j] = this.grille[i][j2];
		this.grille[i][j2] = unTuileTemp;
	}

	// ************************************************************************************
	// AJOUTER-OBTENIR CARRE
	// ************************************************************************************
	/**
	 * AjoutUnCarrer permet ajouter un carre (ce placera automatiquement en fonction de la posXY du carre)
	 * 
	 * @param 	void
	 * @return 	Boolean (True posé, false non posé)
	 * 
	 */
	public Boolean ajoutUneTuileAleatoire() {
		// GENERE un tableau de tuile vide sur la grille (permet de les
		// modifiers en pointant decu)
		ArrayList<Tuile> lesTuileVides = getFreeTuille();
		if (lesTuileVides.size() != 0) {
			int uneValeurCarreAleatoire = Outil.aleatoireDeuxQuatre();
			int unNombreAleatoire = 0;
			if (!(lesTuileVides.size() == 1)) {
				unNombreAleatoire = Outil.aleatoireNombreEntier(0, lesTuileVides.size());
			}

			lesTuileVides.get(unNombreAleatoire).setValeur(uneValeurCarreAleatoire);
			Log.w("test1", "Tuile aléatoirement valeur : " + uneValeurCarreAleatoire);
			return true;
		} else {
			return false;
		}
	}

	public void debog_ListTuile(ArrayList<Tuile> maList, String titre) {
		Log.i("test1", "******************");
		Log.i("test1", "*****" + titre + "*****");
		String chaineAffiche = "";
		for (ListIterator<Tuile> iteratorLigne = maList.listIterator(); iteratorLigne.hasNext();) {
			Tuile tuile = (Tuile) iteratorLigne.next();

			chaineAffiche += ":" + tuile.getValeur();
		}
		Log.i("test1", chaineAffiche);
		Log.i("test1", "******************");
	}

	/*
	 * DEBUG de l'attribut tableau en console
	 * 
	 * @void
	 */
	public void debog_Tableau() {
		Log.i("test1", "******************");
		Log.i("test1", "TABLEAU : ");
		String chaineAffiche = "";
		for (int i = 0; i < getSizeY(); i++) {
			chaineAffiche = "";
			for (int u = 0; u < getSizeX(); u++) {
				chaineAffiche += ":" + this.grille[i][u].getValeur();

			}
			Log.i("test1", chaineAffiche);
		}

		Log.i("test1", "******************");

	}

	/*
	 * DEBUG de l'attribut tableau en console
	 * 
	 * @void
	 */
	public void debog_TableauTuile(Tuile[][] grille) {
		Log.i("test1", "******************");
		Log.i("test1", "TABLEAU : ");
		String chaineAffiche = "";
		for (int i = 0; i < grille.length; i++) {
			chaineAffiche = "";
			for (int u = 0; u < grille.length; u++) {
				chaineAffiche += ":" + grille[i][u].getValeur();

			}
			Log.i("test1", chaineAffiche);
		}

		Log.i("test1", "******************");

	}
	/*
	 * DEBUG de l'attribut tableau en console
	 * 
	 * @void
	 */
	/*
	 * public void debog_TableauAffichePosition() { Log.i("test1",
	 * "******************"); Log.i("test1", "Position ACTUEL : "); String
	 * chaineAffiche; for (ListIterator<List<Carre>> iteratorLigne =
	 * tableau.listIterator(); iteratorLigne.hasNext();) { List<Carre> ligne =
	 * (List<Carre>) iteratorLigne.next(); chaineAffiche = ""; for
	 * (ListIterator<Carre> iteratorCarre = ligne.listIterator();
	 * iteratorCarre.hasNext();) { Carre unCarre = (Carre) iteratorCarre.next();
	 * chaineAffiche += ":X="+unCarre.getPostionActuel().getPosX();
	 * chaineAffiche += "/Y="+unCarre.getPostionActuel().getPosY(); }
	 * Log.i("test1", chaineAffiche); } Log.i("test1", "******************");
	 * Log.i("test1", "Position PASSE : "); chaineAffiche = ""; for
	 * (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator();
	 * iteratorLigne.hasNext();) { List<Carre> ligne = (List<Carre>)
	 * iteratorLigne.next(); chaineAffiche = ""; for (ListIterator<Carre>
	 * iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) { Carre
	 * unCarre = (Carre) iteratorCarre.next(); chaineAffiche +=
	 * ":X="+unCarre.getPostionPasse().getPosX(); chaineAffiche +=
	 * "/Y="+unCarre.getPostionPasse().getPosY(); } Log.i("test1",
	 * chaineAffiche); } Log.i("test1", "******************"); }
	 */

}