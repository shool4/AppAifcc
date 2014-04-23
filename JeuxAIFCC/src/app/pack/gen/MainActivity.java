package app.pack.gen;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import app.pack.controleur.EcouteurToucherEcran;
import app.pack.modele.MoteurPhysique;
import app.pack.modele.Tuile;
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
		
		moteurGraphique = new MoteurGraphique(this);
		EcouteurToucherEcran ecouteurToucherEcran = new EcouteurToucherEcran(this);
		moteurGraphique.setOnTouchListener(ecouteurToucherEcran);
		

	}

	public void gauche() {
		Log.i("test1", "*********************** GAUCHE *********************");
	
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.gauche();
	
		
		
	}
	public void droite() {
		Log.i("test1", "*********************** DROITE *********************");
		
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.droite();
	}    
	public void haut() {
		Log.i("test1", "*********************** HAUT ***********************");
	
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.haut();
		
	}  
	public void bas() {
		Log.i("test1", "*********************** BAS ************************");
		
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.bas();
		

	} 
	

	public void ButtonOnClick(View v) {
		
		//CHARGEMENT DE LA VUE GRAPHIQUE
		setContentView(moteurGraphique);
	
	    switch (v.getId()) {
	      case R.id.button1:
			moteurPhysique = new MoteurPhysique(TypePartie.easy);

	        break;
	      case R.id.button2:
			moteurPhysique = new MoteurPhysique(TypePartie.normal);
				
	        break;
	      }

	}

}
