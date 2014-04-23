package app.pack.modele;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import android.R.integer;
import android.util.Log;
/**
 * Class permettant de gerer la grille de tuile
 * @author dark_d0g
 *
 */
public class Grille {

	private Tuile[][] grille = null;

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
	
	/*
	 * 
	 ******************************************************************************************************************************
	 * GETTE - SETTER
	 ******************************************************************************************************************************
	 *
	 */
	/**
	 * Obtenir taille sur les X
	 * @return	Entier taille sur x
	 */
	private int getSizeX() {return grille[0].length;}
	
	/**
	 * Obtenir taille sur les Y
	 * @return	Entier taille sur Y
	 */
	private int getSizeY() {return grille.length;}

	/**
	 * Obtent la taile X*Y
	 * @return int
	 * 
	 */
	public int getSize() {return getSizeX() * getSizeY();}
	
	/**
	 * Getter de Grille
	 * @return	une grille
	 */
	public Tuile[][]  getGrille() {return this.grille;}
	
	/**
	 * Setter de Grille
	 * @param 	Tuile[][] de tuile
	 * @return	Tuile[][] de tuile
	 */
	public void setGrille(Tuile[][] arrayTuile) {this.grille = arrayTuile;}
	
	/**
	 * Obtenir nombre de tuile = 0
	 * @return	int
	 * 
	 */
	public int getNombreTuileVide() {
		int sum = 0;
		for (int i = 0; i < getSizeY(); i++) for (int u = 0; u < getSizeX(); u++) 
			if (this.grille[i][u].getValeur() == 0) sum++;
		
		return sum;
	}

	/**
	 * Obtenir nombre de tuile != 0
	 * @return	int
	 */
	public int getNombreOccurenceTuileNonVide() {return getSize() - getNombreTuileVide();}
	
	/**
	 * set valeur d'une tuile en fonction (x,y) sur la grille
	 * @param y
	 * @param x
	 * @param value
	 * 
	 */
	public void setValue(int y, int x, int value) {this.grille[y][x].setValeur(value);}
	
	/**
	 * ajoute une tuile en fonction de ca position
	 * 
	 * @param 	Tuile
	 * @return 	Boolean
	 * 
	 */
	public Boolean ajoutUneTuile(Tuile uneTuile) {

		int posX = uneTuile.getPostionActuel().getPosX();
		int posY = uneTuile.getPostionActuel().getPosY();

		if (posX <= this.getSizeX() && posY <= this.getSizeY()) {
			if (this.grille[posY][posX].getValeur() == 0) {
				this.grille[posY][posX] = uneTuile;
				return true;
			}
		}
		return false;
	}
	
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES METHODES
	 ******************************************************************************************************************************
	 *
	 */
	/**
	 * Charge la grille de tuile Zeros
	 * 
	 */
	private void mettreZero() {
		for (int i = 0; i < this.getSizeY(); i++) for (int u = 0; u < this.getSizeX(); u++) 
			this.grille[i][u] = new Tuile(i, u, 0);
	}
	
	/**
	 * Ajoute une tuile aleatoirement
	 * 
	 * @return 	Boolean (True pose, false non pose)
	 * 
	 */
	public Boolean ajoutTuileAleatoire() {
		// GENERE un tableau de tuile vide sur la grille (permet de les
		// modifiers en pointant decu)
		ArrayList<Tuile> lesTuileVides = getFreeTuille();
		if (lesTuileVides.size() != 0) {
			int uneValeurCarreAleatoire = Outil.aleatoireDeuxQuatre();
			int unNombreAleatoire = 0;
			if (!(lesTuileVides.size() == 1)) unNombreAleatoire = Outil.aleatoireNombreEntier(0, lesTuileVides.size());
			lesTuileVides.get(unNombreAleatoire).setValeur(uneValeurCarreAleatoire);
			//Log.w("test1", "Tuile aleatoirement valeur : " + uneValeurCarreAleatoire);
			return true;
			
		} else return false;
	}
	
	/**
	 * Check si le jeux est perdant --> Tout bloquer
	 * 
	 * @return 	Boolean (False Jeux non perdant, True Jeux perdant)
	 * 
	 */
	public Boolean isGameLost() {
		//Check si aucun tuile a Zeros pour un mouvement possible (cas check avant l'ajout d'un nouvelle tuile)
		if (getNombreTuileVide() == 0) {
			//Check si des mouvements son possible 
			if(this.chekcIsPossibleDroiteGauche(true)) return false;
			if(this.chekcIsPossiblehHautBas(true)) return false;
			
			//Log.i("test1","---> Posible Aucun");
			return true;

		} return false;
	}
	
	/**
	 * Check si jeux est gagnant --> check ou est un 2048
	 * @return	Boolean (True gagner 2048 : False pas encore)
	 * 
	 */
	public Boolean isGameWon(int nombreForWin) {
		for (int i = 0; i < getSizeY(); i++) for (int u = 0; u < getSizeX(); u++) 
			if (this.grille[i][u].getValeur() == nombreForWin) return true;
		
		return false;
	}

	/**
	 * Obtenir les tuile ou il n'y pas de valeur assigne
	 * 
	 * @return	ArrayList<Tuile> Un tableau de tuile avec la valeur 0
	 */
	private ArrayList<Tuile> getFreeTuille() {
		ArrayList<Tuile> freeTuile = new ArrayList<Tuile>();
		for (int i = 0; i < getSizeY(); i++) for (int u = 0; u < getSizeX(); u++) 
			if (this.grille[i][u].getValeur() == 0) freeTuile.add(this.grille[i][u]);
				
		return freeTuile;
	}
	
	/**
	 * Change 2 tuile sur un axe Y (i) et un indice X (j et j2)
	 * 
	 * @param 	int i pour l'axe Y
	 * @param	int j pour l'axe X
	 * @param	int j2 pour l'axe X
	 * 
	 */
	private void inverseX(int i, int j, int j2) {
		// Var tuile 0 et position de son predeseur
		Tuile unTuileTemp = this.grille[i][j];
		Position nouvellePositionFuturZeros = this.grille[i][j2].getPostionActuel();
		// CHangement des position
		this.grille[i][j2].setPositionActuel(unTuileTemp.getPostionActuel());
		unTuileTemp.setPositionActuel(nouvellePositionFuturZeros);
		// Inverse les tuiles
		this.grille[i][j] = this.grille[i][j2];
		this.grille[i][j2] = unTuileTemp;
	}
	
	/**
	 * Change 2 tuile sur un axe X (i) et un indice Y (j et j2)
	 * 
	 * @param 	int i pour l'axe X
	 * @param	int j pour l'axe Y
	 * @param	int j2 pour l'axe Y
	 * 
	 */
	private void inverseY(int i, int j, int j2) {
		Tuile unTuileTemp = this.grille[j][i];
		Position nouvellePositionFuturZeros = this.grille[j2][i].getPostionActuel();
		this.grille[j2][i].setPositionActuel(unTuileTemp.getPostionActuel());
		unTuileTemp.setPositionActuel(nouvellePositionFuturZeros);
		this.grille[j][i] = this.grille[j2][i];
		this.grille[j2][i] = unTuileTemp;
	}
	
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES MOUVEMENTS
	 ******************************************************************************************************************************
	 *
	 */
	/**
	 * Posibiliter de mouvement au joueur pour jouer
	 * 
	 * @return	ArrayList<Integer> List d'entier -> 
	 * 			0 : Aucun, 
	 * 			1 : Droite,Gauche 
	 * 			2 : Haut,Bas 
	 * 
	 */
	public ArrayList<Integer> possibiliteMouvement(){
		
			ArrayList<Integer>  mouvementsPossibles = new ArrayList<Integer>();
		
			if(!this.chekcIsPossibleDroiteGauche(false)) mouvementsPossibles.add(1);
			
			if(!this.chekcIsPossiblehHautBas(false)) mouvementsPossibles.add(2);
				
			if(mouvementsPossibles.isEmpty()) mouvementsPossibles.add(0);
			
			return mouvementsPossibles;
	}
	
	/**
	 * Permet de savoir les deplacements possibles des tuiles vers la gauche ou droite
	 * 
	 * @param 	Boolean pour prendre en compte les Zeros, 
	 * 			Si on prend en compte les zeros, si le mouvements est posible (le deplacement),
	 * 			Si non prise du zeros cela veut dire si le mouvement et posible juste pour merged une ou plusieurs tuiles
	 * 
	 * @return 	Boolean (True possible de bouger a droite ou gauche : false)
	 * 
	 */
	public Boolean chekcIsPossibleDroiteGauche(Boolean priseEnCompteZerosTrue) {
		
		for (int i = 0; i < this.getSizeY(); i++) {
			for (int j = 0; j < this.getSizeX() ; j++) {
				for (int j2 = j + 1; j2 < this.getSizeX() ; j2++) {

					if (this.grille[i][j].getValeur() != 0) {
						if (this.grille[i][j].getValeur() == this.grille[i][j2].getValeur()) return true;
						else if (this.grille[i][j2].getValeur() != 0) break;
					}
					
					if(priseEnCompteZerosTrue && this.grille[i][j].getValeur() == 0) return true;	
				}
			}
		}
		return false;
	}
	
	/**
	 * Permet de savoir les deplacement posible des tuiles vers la Haut ou Bas
	 * 
	 * @param 	Boolean pour prendre en compte les Zeros 
	 * 			Si on prend en compte les zeros, si le mouvements est posible (le deplacement)
	 * 			Si non prise du zeros cela veut dire si le mouvement et posible juste pour merged une ou plusieurs tuiles
	 * 
	 * @return 	Boolean (True possible de bouger a Haut ou Bas : false)
	 * 
	 */
	public Boolean chekcIsPossiblehHautBas(Boolean priseEnCompteZerosTrue) {

		for (int i = 0; i < this.getSizeX(); i++) {
			for (int j = 0; j < this.getSizeY(); j++) {
				for (int j2 = j + 1; j2 < this.getSizeY(); j2++) {

					if (this.grille[j][i].getValeur() != 0) {
						if (this.grille[j][i].getValeur() == this.grille[j2][i].getValeur()) return true;
						else if (this.grille[j2][i].getValeur() != 0) break;
					}
					
					if (priseEnCompteZerosTrue && this.grille[i][j].getValeur() == 0) return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Permet le deplacement des tuiles vers la Droite
	 * 
	 * @return 	ArrayList<Tuile> les tuiles bougaient
	 * 
	 */
	public ArrayList<Tuile> deplacementDroite() {
		//Log.i("test1", "************ DROITE **************");
		// Pacour des Y

		ArrayList<Tuile> tuileMerged = new ArrayList<Tuile>();
		// int indiceIncrement = 0;

		for (int i = 0; i < this.getSizeY(); i++) {
			
			// On statue sur un tuile pour faire un parcours des tuiles
			// suivante
		
			for (int j = this.getSizeX()-1; j > 0  ; j--) {

				// Pacours sur les tuiles suivante de la tuile ou on statue
				// -1 						0				--
				for (int j2 = j - 1; j2 >= 0 ; j2--) {

					// Si la valeur courante et differente de zeros
					if (this.grille[i][j].getValeur() != 0) {

						// Si la valeur courant est = a la valeur prochaine
						// Si pas egal on avant 1 tuile la valeur prochaine
						// pour comparais
						if (this.grille[i][j].getValeur() == this.grille[i][j2].getValeur()) {
							if (!tuileMerged.contains(this.grille[i][j])) tuileMerged.add(this.grille[i][j]);
							this.grille[i][j].merge(this.grille[i][j2]);
							this.grille[i][j2].setValeur(0);
							break;
							
						} else if (this.grille[i][j2].getValeur() != 0) break;
						
			
					} else {
						// Si la valeur courant et 0 on avance le zeros a la
						// case prochaine
						this.inverseX(i, j2, j);
						if (this.grille[i][j].getValeur() != 0) tuileMerged.add(this.grille[i][j]);
						
					}
				}
			}
		}
	//	debog_ListTuile(tuileMerged, "---> Tuile Merged <---");
		return tuileMerged;
	}
	
	/**
	 * Permet le deplacement des tuiles vers la gauche
	 * 
	 * @return 	ArrayList<Tuile> les tuiles bougaient
	 * 
	 */
	public ArrayList<Tuile> deplacementGauche() {
	//	Log.i("test1", "************ GAUCHE **************");
		ArrayList<Tuile> tuileMerged = new ArrayList<Tuile>();

		for (int i = 0; i < this.getSizeY(); i++) {
			for (int j = 0; j < this.getSizeX() ; j++) {
				for (int j2 = j + 1; j2 < this.getSizeX() ; j2++) {

					if (this.grille[i][j].getValeur() != 0) {

						if (this.grille[i][j].getValeur() == this.grille[i][j2].getValeur()) {
							if (!tuileMerged.contains(this.grille[i][j])) tuileMerged.add(this.grille[i][j]);
							this.grille[i][j].merge(this.grille[i][j2]);
							this.grille[i][j2].setValeur(0);
							break;
							
						} else if (this.grille[i][j2].getValeur() != 0) break;
			
					} else {
						this.inverseX(i, j, j2);
						if (this.grille[i][j].getValeur() != 0) tuileMerged.add(this.grille[i][j]);
					}
				}
			}
		}
	//	debog_ListTuile(tuileMerged, "---> Tuile Merged <---");
		return tuileMerged;
	}
	
	/**
	 * Permet le deplacement des tuiles vers le Haut
	 * 
	 * @return 	ArrayList<Tuile> les tuiles bougaient
	 * 
	 */
	public ArrayList<Tuile> deplacementHaut() {
		//Log.i("test1", "************ HAUT **************");
	
		ArrayList<Tuile> tuileMerged = new ArrayList<Tuile>();

		for (int i = 0; i < this.getSizeX(); i++) {
			for (int j = 0; j < this.getSizeY() ; j++) {
				for (int j2 = j + 1; j2 < this.getSizeY() ; j2++) {

					if (this.grille[j][i].getValeur() != 0) {

						if (this.grille[j][i].getValeur() == this.grille[j2][i].getValeur()) {
							if (!tuileMerged.contains(this.grille[j][i])) tuileMerged.add(this.grille[j][i]);
							this.grille[j][i].merge(this.grille[j2][i]);
							this.grille[j2][i].setValeur(0);
							break;
							
						} else if (this.grille[j2][i].getValeur() != 0) break;
					
					} else {
						this.inverseY(i, j, j2);
						if (this.grille[j][i].getValeur() != 0) tuileMerged.add(this.grille[j][i]);	
					}
				}
			}
		}
	//	debog_ListTuile(tuileMerged, "---> Tuile Merged <---");
		return tuileMerged;
	}
	
	/**
	 * Permet le deplacement des tuiles vers le Bas
	 * 
	 * @return 	ArrayList<Tuile> les tuiles bougaient
	 * 
	 */
	public ArrayList<Tuile> deplacementBas() {
		//Log.i("test1", "*********************** BAS ************************");

		ArrayList<Tuile> tuileMerged = new ArrayList<Tuile>();

		for (int i = 0; i < this.getSizeY(); i++) {
			for (int j = this.getSizeX()-1; j > 0  ; j--) {	
				for (int j2 = j - 1; j2 >= 0 ; j2--) {

					if (this.grille[j][i].getValeur() != 0) {

						if (this.grille[j][i].getValeur() == this.grille[j2][i].getValeur()) {
							if (!tuileMerged.contains(this.grille[j][i])) tuileMerged.add(this.grille[j][i]);
							this.grille[j][i].merge(this.grille[j2][i]);
							this.grille[j2][i].setValeur(0);
							break;
							
						} else if (this.grille[j2][i].getValeur() != 0) break;
						
					} else {
						this.inverseY(i, j2, j);
						if (this.grille[j][i].getValeur() != 0) tuileMerged.add(this.grille[j][i]);
					}
				}
			}
		}
		//debog_ListTuile(tuileMerged, "---> Tuile Merged <---");
		return tuileMerged;
	}
	
	/*
	 * 
	 ******************************************************************************************************************************
	 * LES METHODES DE DEBUG
	 ******************************************************************************************************************************
	 *
	 */
	/**
	 * DEBUG d'une list de carre
	 * @param List<Tuile> une liste de carre
	 * @parem String titre a afficher dans la console
	 * 
	 */
	private void debog_ListTuile(List<Tuile> maList, String titre) {
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

	/**
	 * DEBUG de le grille
	 * @void
	 * 
	 */
	public void debog_Tableau() {
		Log.i("test1", "****************************************************");
		String chaineAffiche = "";
		for (int i = 0; i < getSizeY(); i++) {
			chaineAffiche = "";
			for (int u = 0; u < getSizeX(); u++) {
				int valeurRetour = this.grille[i][u].getValeur();
				
				if(valeurRetour > 9) {
					chaineAffiche += "  " + valeurRetour;
				} else if(valeurRetour > 99) {
					chaineAffiche += " " + valeurRetour;
				} else {
					chaineAffiche += "   " + valeurRetour;
				}
				
			}
			Log.i("test1", chaineAffiche);
		}


	}

	/**
	 * DEBUG un tableau de tuile
	 * 
	 */
	private void debog_TableauTuile(Tuile[][] grille) {
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
	
}
