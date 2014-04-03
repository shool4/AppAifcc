package app.pack.modele;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.util.Log;

public class Grille {
	

 
	private List<List<Carre>> tableau = null;
	
	
	private final int TAILLE_MAX = 4;

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
		Log.i("test1", "Grille OK");
		Log.i("test1", "Taille grille : "+tableau.size());
		debogTableau(0);
		Log.i("test1", "******************");
		Log.i("test1", "Test carre");
		
		CarreNumero carreDepart = new CarreNumero(0, 0, 2);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(1, 0, 2);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(2, 0, 4);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(3, 0, 4);
		this.ajoutUnCarre(carreDepart);
		/*
		CarreBonus carreDepart2 = new CarreBonus(3,0,0);
		this.ajoutUnCarre(carreDepart2);*/

		carreDepart = new CarreNumero(0, 1, 8);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(1, 1, 8);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(2, 1, 8);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(3, 1, 8);
		this.ajoutUnCarre(carreDepart);
		
		carreDepart = new CarreNumero(0, 2, 2);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(1, 2, 4);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(2, 2, 4);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(3, 2, 2);
		this.ajoutUnCarre(carreDepart);
		
		carreDepart = new CarreNumero(0, 3, 2);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(1, 3, 4);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(2, 3, 2);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(3, 3, 4);
		this.ajoutUnCarre(carreDepart);
		
		debogTableau(0);
		this.deplace(false);
		debogTableau(0);
		this.deplace(false);
		debogTableau(0);
		
		carreDepart = new CarreNumero(1, 1, 32);
		this.ajoutUnCarre(carreDepart);
		this.deplace(true);
		debogTableau(0);
		
		carreDepart = new CarreNumero(2, 0, 8);
		this.ajoutUnCarre(carreDepart);
		carreDepart = new CarreNumero(3, 1, 64);
		this.ajoutUnCarre(carreDepart);
		this.deplace(false);
		debogTableau(0);
		//*****************************************
		
		
		
	}
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
	 * Methode qui deplace a droit les carres
	 */
	private void deplace(Boolean droitFalseGaucheTrue) {
		
		
		//Parcour chaque ligne
	
	
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			//Ligne du tableau
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			//nouvelle ligne
			List<Carre> nouvelleList = new LinkedList<Carre>();
		
			//Index carre ajouter
			int indexAjout = 0;
		
			
		
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
			ligne.clear();
			
			while(nouvelleList.size() != TAILLE_MAX) {
				int indiceDeLajout = 0;
				if(droitFalseGaucheTrue) {
		
					indiceDeLajout = nouvelleList.size();
				} else {
					
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
	
	private void deplaceHaut() {
		
	}
	private void deplaceBas() {
		
	}
	
	//METHODE DE DEBUG 
	//*****************
	public void debogTableau(int ajoutTaille){
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
		/*Log.i("test1", "******************");
		Log.i("test1", "POSITION CARRE : ");
		String chaineAffiche2;
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			chaineAffiche2 = "";
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				Carre unCarre = (Carre) iteratorCarre.next();
				chaineAffiche2 += "("+unCarre.getValeur()+")";
				chaineAffiche2 += " X= "+unCarre.getPostionActuel().getPosX();
				chaineAffiche2 += " Y= "+unCarre.getPostionActuel().getPosY();
				chaineAffiche2 += " LastX= "+unCarre.getPostionPasse().getPosX();
				chaineAffiche2 += " LasrY= "+unCarre.getPostionPasse().getPosY();
				
			}
			Log.i("test1", chaineAffiche2);
			
		}
		Log.i("test1", "******************");*/
		
	
	}
	
	
	
}
