package app.pack.gen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import app.pack.controleur.EcouteurToucherEcran;
import app.pack.modele.MoteurPhysique;
import app.pack.modele.TuileGraphique;
import app.pack.modele.TypePartie;
import app.pack.vue.MoteurGraphique;

public class MainActivity extends Activity{
	// Le moteur graphique du jeu
	public MoteurGraphique moteurGraphique = null;
	public MoteurPhysique moteurPhysique = null;

	
	public int taillePlateau = 4;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.menu);	
	
		
		EcouteurToucherEcran ecouteurToucherEcran = new EcouteurToucherEcran(this);
		this.moteurGraphique = new MoteurGraphique(this);
		this.moteurGraphique.setOnTouchListener(ecouteurToucherEcran);
		
		//moteurGraphique.setTbBitmapCarre(moteurPhysique.ConstructionCarre());
		
		Log.v("test1", "#########################");
		Log.v("test1", "Initialisation");


		
		//this.resources = getResources();
	}

	public void gauche() {
		Log.i("test1", "*********************** GAUCHE *********************");
     //   this.moteurGraphique.setMouvementFini(false);
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.gauche();
            this.moteurGraphique.setMouvement(1);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
        }

	}

	public void droite() {
		Log.i("test1", "*********************** DROITE *********************");
     //   this.moteurGraphique.setMouvementFini(false);
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.droite();
            //this.moteurGraphique.mouvement = 2;
            this.moteurGraphique.setMouvement(2);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
        }
	}
	public void haut() {
		Log.i("test1", "*********************** HAUT ***********************");
      //  this.moteurGraphique.setMouvementFini(false);
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.haut();
            //this.moteurGraphique.mouvement = 3;

            this.moteurGraphique.setMouvement(3);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
        }
	}
	public void bas() {
		Log.i("test1", "*********************** BAS ************************");
       // this.moteurGraphique.setMouvementFini(false);
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.bas();
            //this.moteurGraphique.mouvement = 4;
            this.moteurGraphique.setMouvement(4);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
        }
	}
	
	@SuppressWarnings("deprecation")
	public void ButtonOnClick(View v) {
		
		//CHARGEMENT DE LA VUE GRAPHIQUE
		if(		v.getId() == R.id.button1 ||
				v.getId() == R.id.button2 ||	
				v.getId() == R.id.button3
		) {
			setContentView(moteurGraphique);
		}
		
	    switch (v.getId()) {
	      case R.id.button1:
			moteurPhysique = new MoteurPhysique(TypePartie.easy);
			this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
	        break;
	      case R.id.button2:
			moteurPhysique = new MoteurPhysique(TypePartie.normal);
			this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
	        break;
	      case R.id.button3:
			moteurPhysique = new MoteurPhysique(TypePartie.normal);
			this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
	        break;
	      case R.id.button4:
	    	  AlertDialog alertDialog = new AlertDialog.Builder(
	    		        MainActivity.this).create();
	    		 
	    		// Le titre
	    		alertDialog.setTitle("LES SUPER CREATEUR");
	    		 
	    		// Le message
	    		alertDialog.setMessage("Aurélien blaise & Yannick Stephan");
	    		 
	    		// L'icône
	    		alertDialog.setIcon(android.R.drawable.btn_star);
	    		 
	    		// Ajout du bouton "OK"
	    		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
	    		    public void onClick(DialogInterface dialog, int which) {
	    		        // Le code à exécuter après le clique sur le bouton
	    		        Toast.makeText(getApplicationContext(), "Bravos ta cliqué sur ok ;)",
	    		                Toast.LENGTH_SHORT).show();
	    		    }
	    		});
	    		 
	    		// Affichage
	    		alertDialog.show();
				
	        break;
	      }

	}
	
	/*public ArrayList<TuileGraphique> conversionTuile(){
		ArrayList<TuileGraphique> listTuilesG = new ArrayList<TuileGraphique>();

		for(Tuile uneTuile : this.moteurPhysique.getGrille()){
            boolean isAleatoire;
            if(uneTuile.getValeur() != 0 ) {
                //Log.i("test1"," "+uneTuile.getValeur()+" --> "+uneTuile.isAleatoire());
                isAleatoire = uneTuile.isAleatoire();
                TuileGraphique tuileG = new TuileGraphique(uneTuile);
                // Log.i("test1"," "+tuileG.getValeur()+" --> "+tuileG.isAleatoire());
                tuileG.setAleatoire(isAleatoire);
                listTuilesG.add(tuileG);
                   // if(uneTuile.isAleatoire()) {
                   //     uneTuile.setPositionPasse(uneTuile.getPostionActuel());
                   // }

                //tuileG.setPosGPasse(tuileG.getPosGActuel());
            }
        }
		
		return listTuilesG;
	}*/
}
