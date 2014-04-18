package app.pack.modele;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

import android.util.Log;
import app.pack.modele.CarreBonus.Type_Bonus;

public class Grille {
	
	private List<List<Carre>> tableau = null;
	
	public List<List<Carre>> getTableau() {
		return tableau;
	}

	public void setTableau(List<List<Carre>> tableau) {
		this.tableau = tableau;
	}
	
	private int tailleGrille;
	
	private float tailleFondGrille;
	
	private float hauteurEcran;
	private float largeurEcran;
	
	/*
	 * CONSTRUCTEUR
	 * 
	 */
	public Grille(int uneTailleGrille) {
		super();
		this.tailleGrille = uneTailleGrille;
		tableau = new LinkedList<List<Carre>>();
				
		new LinkedList<Carre>();
		
		//INITIALISATION du tableau
		for (int i = 0; i < this.tailleGrille; i++) {
			//Creation de la ligne
			List<Carre> ligneTemporaire = new LinkedList<Carre>();
		
			//ajout de null en fonction taille definit
			for (int j = 0; j < this.tailleGrille; j++) {
				ligneTemporaire.add(new CarreNumero(i, j, 0));
			}
			tableau.add(ligneTemporaire);
		}
		

		this.ajoutUnCarreAleatoire();
		
		//*****************************************
		// TEST
		Log.i("test1", "INIT GRILLE");
		Log.i("test1", "Taille grille : "+tableau.size());
		debog_Tableau();
		Log.i("test1", "******************");
		Log.i("test1", "Test carre");
		
	}
	
	public float getHauteurEcran() {
		return hauteurEcran;
	}

	public void setHauteurEcran(float hauteurEcran) {
		this.hauteurEcran = hauteurEcran;
	}

	public float getLargeurEcran() {
		return largeurEcran;
	}

	public void setLargeurEcran(float largeurEcran) {
		this.largeurEcran = largeurEcran;
	}
	
	public float getTailleFondGrille() {
		return tailleFondGrille;
	}

	public void setTailleFondGrille(float tailleFondGrille) {
		this.tailleFondGrille = tailleFondGrille;
	}

	//************************************************************************************
	//	MISE A JOUR
	//************************************************************************************
	/*
	 * Mise a jour des positino des carrées dans le tableau
	 * 
	 */
	private void miseJourCarreDansTb(){
		int x,y = 0;
		
		//Parcours sur l'axe des Y
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			x = 0;
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			
			//Parcours de chaque carre sur l'axe X
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				//Un carre a X est Y du tableau
				Carre unCarre = iteratorCarre.next();
				//La position actuel du carré est mis en position passe
				unCarre.setPositionPasse(unCarre.getPostionActuel());
				//La position actuel (maintenant) est mis a jour
				unCarre.setPositionActuel(new Position(x,y));
				x +=1;			
			}
			y +=1;
		}
	}
	
	//************************************************************************************
	//	AJOUTER-OBTENIR CARRE
	//************************************************************************************
	/*
	 * AjoutUnCarrer permet ajouter un carre (ce placera automatiquement en fonction de la posXY du carre)
	 * @param 	unCarre 		(qui hérite de la class abstrait carre)
	 * @return 	Boolean 		(True posé, false non posé)
	 * 
	 */
	public void ajoutUnCarreAleatoire() {
		int uneValeurCarreAleatoire = aleatoireDeuxQuatre();
		Position unePositionAleatoire =  null;
		int uneValeurCarreAleatoireX = 0;
		int uneValeurCarreAleatoireY = 0;
		boolean pasValide = false;
		while(!pasValide) {
			uneValeurCarreAleatoireX = aleatoireNombreEntier(0,this.tailleGrille);
			uneValeurCarreAleatoireY = aleatoireNombreEntier(0,this.tailleGrille);
			unePositionAleatoire = new Position(uneValeurCarreAleatoireX,uneValeurCarreAleatoireY);
			pasValide = ajoutUnCarre(new CarreNumero(unePositionAleatoire, uneValeurCarreAleatoire));
		}
		Log.w("test1", "Carre Ajout aléatoirement : " + uneValeurCarreAleatoire+ " x= "+uneValeurCarreAleatoireX+ " Y= "+uneValeurCarreAleatoireY);
	}
	/*
	 * AjoutUnCarrer permet ajouter un carre (ce placera automatiquement en fonction de la posXY du carre)
	 * @param 	unCarre 		(qui hérite de la class abstrait carre)
	 * @return 	Boolean 		(True posé, false non posé)
	 * 
	 */
	public void ajoutUnCarreAleatoireBonus() {
		//int uneValeurCarreAleatoire = aleatoireDeuxQuatre();
		Position unePositionAleatoire =  null;
		int uneValeurCarreAleatoireX = 0;
		int uneValeurCarreAleatoireY = 0;
		boolean pasValide = false;
		while(!pasValide) {
			uneValeurCarreAleatoireX = aleatoireNombreEntier(0,this.tailleGrille);
			uneValeurCarreAleatoireY = aleatoireNombreEntier(0,this.tailleGrille);
			unePositionAleatoire = new Position(uneValeurCarreAleatoireX,uneValeurCarreAleatoireY);
			pasValide = ajoutUnCarre(new CarreBonus(unePositionAleatoire, 4,Type_Bonus.Bonus_Delete));
		}
		Log.w("test1", "Carre Ajout aléatoirement : " + 4+ "** x= "+uneValeurCarreAleatoireX+ " Y= "+uneValeurCarreAleatoireY);
	}
	/*
	 * AjoutUnCarrer permet ajouter un carre (ce placera automatiquement en fonction de la posXY du carre)
	 * @param 	unCarre 		(qui hérite de la class abstrait carre)
	 * @return 	Boolean 		(True posé, false non posé)
	 * 
	 */
	private Boolean ajoutUnCarre(Carre unCarre) {
		int posX = unCarre.getPostionActuel().getPosX();
		int posY = unCarre.getPostionActuel().getPosY();
		
		if (posX <= this.tailleGrille && posY <= this.tailleGrille) {
			List<Carre> ligne = this.getLigne(posY);

			if (ligne.get(posX).getValeur() == 0) {
				ligne.set(posX, unCarre);
				return true;
			} 
		}
		return false;
	}
	/*
	 * getLigne permet obtenir la ligne sur le tableau
	 * @param 	Le numero de la ligne
	 * @return 	une List de carre (la ligne) exemple : [Carre,null,null,CarreBonus]
	 * 
	 */
	private List<Carre> getLigne(int numeroLigne) {
		if(numeroLigne <= this.tailleGrille) {
			return tableau.get(numeroLigne);
		} else {
			Log.w("test1", "Le numero de ligne trop grand au tableau " + numeroLigne);
			return null;
		}
	}	
	//************************************************************************************
	//	MOUVEMENT
	//************************************************************************************

	/*
	 * Methode qui deplace en Haut et Bas
	 * @param 	Haut -> False, Bas -> True
	 * @return 	Void
	 * @author 	DaRk-_-D0G
	 * 
	 */
	public void deplaceHautBas(Boolean hautFalseBasTrue) {
		// Ligne vertical pour le traitement
		//TODO BAS BON LE TRUC
		int indiceObtention = 0;
		
		//Indice qui permet de savoir si ajoute ou compilé deux est fait alors on passe à un ajout aprés(evite de compilé tout les carrés ensemble si meme valeur)
		int indiceDejaCompile = 0;

		//Parcours des X
		for (int i = 0; i < this.tailleGrille; i++) {
			List<Carre> listTemporaire = new LinkedList<Carre>();
			
			//Parcours des Y
			for (int j = 0; j < this.tailleGrille; j++) {
				//Extrait la ligne a indice J et on prend le carrée à l'indice obtention
				List<Carre> ligne = tableau.get(j);
				Carre unCarre = ligne.get(indiceObtention);
				
				//Si carre différent de 0 est une instance de CarreNumero
				if (unCarre.getValeur() != 0 && unCarre instanceof CarreNumero )  {
					
					//	Si tb temp est pas vide et que l'indexAJout a pas augmenté
					if (listTemporaire.isEmpty() || listTemporaire.size() != indiceDejaCompile + 1) {
						listTemporaire.add(unCarre);

					// si carre sur meme valeur ajout
					} else if (unCarre.getValeur() == listTemporaire.get(indiceDejaCompile).getValeur()) {
						listTemporaire.get(indiceDejaCompile).merge(unCarre);
						indiceDejaCompile++;	//On augmente l'indexajout pour evité qui s'ajoute en cumullé

					// Sinon ajout au tb temp
					} else {
						listTemporaire.add(unCarre);
						indiceDejaCompile++;
					}
				
				//Cas si Carre BONUS a voir
				} else if (unCarre instanceof CarreBonus) {
					
				}
			}

			//Complete de le TB pour ajout des Carres 0, Si Haut ou Bas
			while(listTemporaire.size() != this.tailleGrille ) {
				if(hautFalseBasTrue) {
					listTemporaire.add(0, new CarreNumero(0, 0, 0));
				} else {
					listTemporaire.add(new CarreNumero(0, 0, 0));
				}
					
			}
			
			//AJout au Tableau
			int indice = 0;
			for (Carre carre : listTemporaire) {
				List<Carre> maListCarres = tableau.get(indice);
				maListCarres.set(indiceObtention, carre);
				indice++;
			}
			
			//Index ajout remis a 0 est IndiceObtention passe au X suivant
			indiceDejaCompile = 0;
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
	//VOIR FAIRE GETVALEUR WHILE{NEXT si trouve meme valeur alors compil} si new val alors indix et re next TODO
	public void deplaceDroitGauche(Boolean droitFalseGaucheTrue) {
		
		//Pacour des Y
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			//Ligne du tableau
			List<Carre> uneligneDuTableau = (List<Carre>) iteratorLigne.next();
			//nouvelle ligne
			List<Carre> listTemporaire = new LinkedList<Carre>();
			//Index carre ajouter
			int indiceDejaCompile = 0;
		
			//Parcours des X
			for (Carre unCarre : uneligneDuTableau) {
				if(unCarre.getValeur() != 0 && unCarre instanceof CarreNumero) {
					
					//Si nouvelle ligne ajout ou si taille nouvelle ligne et pas taille index
					if(listTemporaire.isEmpty() || listTemporaire.size() != indiceDejaCompile+1) { 
						
							listTemporaire.add(unCarre);
						
						
						
					//si carre sur meme valeur ajout
					} else if (unCarre.getValeur() == listTemporaire.get(indiceDejaCompile).getValeur()) {
						
						//On obtien le carre et on le merge avec le carre
						listTemporaire.get(indiceDejaCompile).merge(unCarre);
						indiceDejaCompile++;
							
					//sinon on ajoute
					} else {
						if(droitFalseGaucheTrue) {
							listTemporaire.add(indiceDejaCompile,unCarre);
						} else {
							listTemporaire.add(unCarre);
						}
							
						
						indiceDejaCompile++;	
							
					}
					
				//Cas carre bonnus
				} else if (unCarre instanceof CarreBonus) {
					
				}
			}
			//Clear de la ligne du tableau
			uneligneDuTableau.clear();
			
			while(listTemporaire.size() != this.tailleGrille) {
				int indiceDeLajout = 0;
				if(droitFalseGaucheTrue) {
					indiceDeLajout = listTemporaire.size();
				}
				listTemporaire.add(indiceDeLajout, new CarreNumero(0, 0, 0));
			}
			uneligneDuTableau.addAll(listTemporaire);
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
	//	FONCTION = STATIC
	//************************************************************************************	
	/*
	 * Fonction qui genere un nombre aléatoire entre un mini et un max
	 * 
	 */
	static int aleatoireNombreEntier(int min, int max) {
		Random rand = new Random();
		int n = rand.nextInt(max - 1) + min;
		return n;
	}
	/*
	 * Fonction qui genere un nombre aléatoire entre 2 et 4
	 * 
	 */
	static int aleatoireDeuxQuatre() {
		int[] maList = { 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 };
		int unNombreAleatoire = aleatoireNombreEntier(0, 10);
		return maList[unNombreAleatoire];
	}
	//************************************************************************************
	//	METHODE DE DEBUG
	//************************************************************************************
	
	/*
	 * DEBUG d'une list de carre
	 * @param une liste de carre, String du titre à afficher dans la console
	 * 
	 */
	public void debog_ListCarre(List<Carre> maList,String titre) {
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
	 * 
	 */
	public void debog_Tableau() {
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
	/*
	 * DEBUG de l'attribut tableau en console
	 * @void
	 * 
	 */
	public void debog_TableauAffichePosition() {
		Log.i("test1", "******************");
		Log.i("test1", "Position ACTUEL : ");
		String chaineAffiche;
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			chaineAffiche = "";
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				Carre unCarre = (Carre) iteratorCarre.next();
				chaineAffiche += ":X="+unCarre.getPostionActuel().getPosX();
				chaineAffiche += "/Y="+unCarre.getPostionActuel().getPosY();
			}
			Log.i("test1", chaineAffiche);	
		}
		Log.i("test1", "******************");	
		Log.i("test1", "Position PASSE : ");
		chaineAffiche = "";
		for (ListIterator<List<Carre>> iteratorLigne = tableau.listIterator(); iteratorLigne.hasNext();) {
			List<Carre> ligne = (List<Carre>) iteratorLigne.next();
			chaineAffiche = "";
			for (ListIterator<Carre> iteratorCarre = ligne.listIterator(); iteratorCarre.hasNext();) {
				Carre unCarre = (Carre) iteratorCarre.next();
				chaineAffiche += ":X="+unCarre.getPostionPasse().getPosX();
				chaineAffiche += "/Y="+unCarre.getPostionPasse().getPosY();
			}
			Log.i("test1", chaineAffiche);	
		}
		Log.i("test1", "******************");	
	}
	
	

	
}
