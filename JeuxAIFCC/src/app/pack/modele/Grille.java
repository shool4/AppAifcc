package app.pack.modele;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import android.R.integer;
import android.util.Log;

public class Grille {
	

 
	private List<List<Carre>> tableau = null;
	
	
	private final int TAILLE_MAX = 4;
	/*
	 * CONSTRUCTEUR
	 * 
	 */
	public Grille() {
		super();
	
		tableau = new LinkedList<List<Carre>>();
				
		new LinkedList<Carre>();
		
		//INITIALISATION du tableau
		for (int i = 0; i < TAILLE_MAX; i++) {
			//Creation de la ligne
			List<Carre> ligneTemporaire = new LinkedList<Carre>();
		
			//ajout de null en fonction taille definit
			for (int j = 0; j < TAILLE_MAX; j++) {
				ligneTemporaire.add(new CarreNumero(i, j, 0));
			}
			tableau.add(ligneTemporaire);
		}
		

		
		
		//*****************************************
		// TEST
		Log.i("test1", "INIT GRILLE");
		Log.i("test1", "Taille grille : "+tableau.size());
		debog_AttrTableau();
		Log.i("test1", "******************");
		Log.i("test1", "Test carre");
		
	
		this.ajoutUnCarre(new CarreNumero(0, 0, 2));

		this.ajoutUnCarre(new CarreNumero(1, 0, 2));
		
		this.ajoutUnCarre(new CarreNumero(2, 0, 2));
		
		this.ajoutUnCarre(new CarreNumero(3, 0, 16));
		/*
		CarreBonus carreDepart2 = new CarreBonus(3,0,0);
		this.ajoutUnCarre(carreDepart2);*/


		this.ajoutUnCarre(new CarreNumero(0, 1, 2));
	
		this.ajoutUnCarre(new CarreNumero(1, 1, 2));

		this.ajoutUnCarre(new CarreNumero(2, 1, 4));
		
		this.ajoutUnCarre(new CarreNumero(3, 1, 16));
		
		
		this.ajoutUnCarre(new CarreNumero(0, 2, 2));
		
		this.ajoutUnCarre(new CarreNumero(1, 2, 2));
	
		this.ajoutUnCarre(new CarreNumero(2, 2, 16));

		this.ajoutUnCarre(new CarreNumero(3, 2, 2));
		
		
		this.ajoutUnCarre(new CarreNumero(0, 3, 2));
		
		this.ajoutUnCarre(new CarreNumero(1, 3, 2));
	
		this.ajoutUnCarre(new CarreNumero(2, 3, 4));
		
		this.ajoutUnCarre( new CarreNumero(3, 3, 16));
		
		debog_AttrTableau();
		this.deplaceHautBas(false);
		debog_AttrTableau();
		this.deplaceHautBas(true);
		debog_AttrTableau();
		this.deplaceDroitGauche(false);
		debog_AttrTableau();
		this.deplaceDroitGauche(true);
		debog_AttrTableau();
			
	}
	
	//************************************************************************************
	//	METHODE
	//************************************************************************************

	/*
	 * mise à jours des positions carre
	 */
	private void miseJourCarreDansTb(){
		int x,y = 0;
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			//Ligne du tableau
			x = 0;
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				//Ligne du tableau
				Position unePosition = new Position(x,y);
				iteratorCarre.next().setPositionActuel(unePosition);
				x +=1;
			}
			y +=1;
		}
	}
	
	/*
	 * AjoutUnCarrer permet ajouter un carre (ce placera automatiquement en fonction de la posXY du carre)
	 * @param 	unCarre 		(qui hérite de la class abstrait carre)
	 * @return 	Boolean 		(True posé, false non posé)
	 */
	private Boolean ajoutUnCarre(Carre unCarre) {

		int posX = unCarre.getPostionActuel().getPosX();
		int posY = unCarre.getPostionActuel().getPosY();
		if (posX <= TAILLE_MAX && posY <= TAILLE_MAX) {
			List<Carre> ligne = this.getLigne(posY);

			// Verification si pas deja présent
			if (ligne.get(posX).getValeur() == 0) {
				ligne.set(posX, unCarre);
				//ligne.add(posX, unCarre);
				//ligne.
				return true;
			} 
		}
		Log.w("test1", "Carrer deja présent OU Trop Grand en x=" + posX + " y="+ posY);
		return false;
	}
	
	/*
	 * getLigne permet obtenir la ligne sur le tableau
	 * @param 	Le numero de la ligne
	 * @return 	une List de carre (la ligne) exemple : [Carre,null,null,CarreBonus]
	 */
	private List<Carre> getLigne(int numeroLigne) {
		if(numeroLigne <= TAILLE_MAX) {
			return tableau.get(numeroLigne);
		} else {
			Log.w("test1", "Le numero de ligne trop grand au tableau " + numeroLigne);
			return null;
		}
	}
	/*
	 * Methode qui deplace en Haut et Bas
	 * @param 	Haut -> False, Bas -> True
	 * @return 	Void
	 * @author 	DaRk-_-D0G
	 * 
	 */
	private void deplaceHautBas(Boolean hautFalseBasTrue) {
		// Ligne vertical pour le traitement
		//TODO BAS BON LE TRUC
		int indiceObtention = 0;
		int indexAjout = 0;

		//Parcours des X
		for (int i = 0; i < TAILLE_MAX; i++) {
			List<Carre> nouvelleList = new LinkedList<Carre>();
			
			//Parcours des Y
			for (int j = 0; j < TAILLE_MAX; j++) {
				//Extrait la ligne a indice J et on prend le carrée à l'indice obtention
				List<Carre> ligne = tableau.get(j);
				Carre carre = ligne.get(indiceObtention);
				
				//Si carre différent de 0 est une instance de CarreNumero
				if (carre.getValeur() != 0 && carre instanceof CarreNumero) {
					
					//	Si tb temp est pas vide et que l'indexAJout a pas augmenté
					if (nouvelleList.isEmpty() || nouvelleList.size() != indexAjout + 1) {
						nouvelleList.add(carre);

					// si carre sur meme valeur ajout
					} else if (carre.getValeur() == nouvelleList.get(indexAjout).getValeur()) {
						nouvelleList.get(indexAjout).upValeur();
						indexAjout++;	//On augmente l'indexajout pour evité qui s'ajoute en cumullé

					// Sinon ajout au tb temp
					} else {
						nouvelleList.add(carre);
						indexAjout++;
					}
				
				//Cas si Carre BONUS a voir
				} else if (carre instanceof CarreBonus) {

				}
			}

			//Complete de le TB pour ajout des Carres 0, Si Haut ou Bas
			while(nouvelleList.size() != TAILLE_MAX ) {
				if(hautFalseBasTrue) {
					nouvelleList.add(0, new CarreNumero(0, 0, 0));
				} else {
					nouvelleList.add(new CarreNumero(0, 0, 0));
				}
					
			}
			
			//AJout au Tableau
			int indice = 0;
			for (Carre carre : nouvelleList) {
				List<Carre> maListCarres = tableau.get(indice);
				maListCarres.set(indiceObtention, carre);
				indice++;
			}
			
			//Index ajout remis a 0 est IndiceObtention passe au X suivant
			indexAjout = 0;
			indiceObtention++;

		}
		//Mise à jour des pos des carre dans tableau
		this.miseJourCarreDansTb();	
		
		if(hautFalseBasTrue) {
			Log.i("test1", "Execution deplacement à BAS *");
			
		} else {
			Log.i("test1", "Execution deplacement à HAUT *");
		}
	}
	
	/*
	 * Methode qui deplace a droit et gauche les carrés du tableau
	 * @param 	Droit -> false, Gauche -> True
	 * @return 	void
	 * @author 	DaRk-_-D0G
	 * 
	 */
	private void deplaceDroitGauche(Boolean droitFalseGaucheTrue) {
		
		//Pacour des Y
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			//Ligne du tableau
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			//nouvelle ligne
			List<Carre> nouvelleList = new LinkedList<Carre>();
			//Index carre ajouter
			int indexAjout = 0;
		
			//Parcours des X
			for (Carre carre : ligne) {
				if(carre.getValeur() != 0 && carre instanceof CarreNumero) {
					
					//Si nouvelle ligne ajout ou si taille nouvelle ligne et pas taille index
					if(nouvelleList.isEmpty() || nouvelleList.size() != indexAjout+1) { 
						nouvelleList.add(carre);
						
					//si carre sur meme valeur ajout
					} else if (carre.getValeur() == nouvelleList.get(indexAjout).getValeur()) {
							nouvelleList.get(indexAjout).upValeur();
							indexAjout++;
							
					//sinon on ajoute
					} else {
							nouvelleList.add(carre);
							indexAjout++;	
							
					}
					
				//Cas carre bonnus
				} else if (carre instanceof CarreBonus) {
					
				}
			}
			//Clear de la ligne du tableau
			ligne.clear();
			
			while(nouvelleList.size() != TAILLE_MAX) {
				int indiceDeLajout = 0;
				if(droitFalseGaucheTrue) {
					indiceDeLajout = nouvelleList.size();
				}
				nouvelleList.add(indiceDeLajout, new CarreNumero(0, 0, 0));
			}
			ligne.addAll(nouvelleList);
		}
		
		//MISE A JOUR DES POSITION CARRE;
		this.miseJourCarreDansTb();
		
		if(droitFalseGaucheTrue) {
			Log.i("test1", "Execution deplacement à GAUCHE *");
			
		} else {
			Log.i("test1", "Execution deplacement à DROITE *");
		}
		
	}	
	

	//************************************************************************************
	//	METHODE DE DEBUG
	//************************************************************************************
	
	/*
	 * DEBUG d'une list de carre
	 * @param une liste de carre, String du titre à afficher dans la console
	 */
	public void debog_UnTableauCarre(List<Carre> maList,String titre) {
		Log.i("test1", "******************");
		Log.i("test1", "*****"+titre+"*****");
		String chaineAffiche = "";
		for (ListIterator<Carre> iteratorLigne = maList.listIterator(); iteratorLigne.hasNext();) {
			Carre carre = (Carre) iteratorLigne.next();
			
				chaineAffiche += ":"+carre.getValeur();
		}
		Log.i("test1", chaineAffiche);
		Log.i("test1", "******************");
	}
	
	/*
	 * DEBUG de l'attribut tableau en console
	 * @void
	 */
	public void debog_AttrTableau() {
		Log.i("test1", "******************");
		Log.i("test1", "TABLEAU : ");
		String chaineAffiche;
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			chaineAffiche = "";
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				Carre unCarre = (Carre) iteratorCarre.next();
				chaineAffiche += ":"+unCarre.getValeur();
			}
			Log.i("test1", chaineAffiche);	
		}
		Log.i("test1", "******************");	
	}

	

	
}
