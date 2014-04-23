package app.pack.gen;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import app.pack.controleur.EcouteurToucherEcran;
import app.pack.modele.MoteurPhysique;
import app.pack.modele.Tuile;
import app.pack.vue.MoteurGraphique;

public class MainActivity extends Activity {
	// Le moteur graphique du jeu
	public MoteurGraphique moteurGraphique = null;
	public MoteurPhysique moteurPhysique = null;
	
	public int taillePlateau = 4;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		
		//setContentView(R.layout.activity_main);
		moteurGraphique = new MoteurGraphique(this);
		setContentView(moteurGraphique);
		moteurPhysique = new MoteurPhysique();
		
		EcouteurToucherEcran ecouteurToucherEcran = new EcouteurToucherEcran(this);
		moteurGraphique.setOnTouchListener(ecouteurToucherEcran);
		
		
		
		moteurGraphique.setGrille(moteurPhysique.getGrille());
		
		Log.v("test1", "#########################");
		Log.v("test1", "Initialisation");

	}

	public void gauche() {
		Log.i("test1", "************ GAUCHE **************");
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.gauche();
	
		
		
	}
	public void droite() {
		Log.i("test1", "************ DROITE **************");
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.droite();
	}    
	public void haut() {
		Log.i("test1", "************ HAUT **************");
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.haut();
		
	}  
	public void bas() {
		Log.i("test1", "************ BAS **************");
		ArrayList<Tuile> arrayTuile = this.moteurPhysique.bas();
		

	} 

}
