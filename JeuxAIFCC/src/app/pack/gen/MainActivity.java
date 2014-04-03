package app.pack.gen;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import app.pack.modele.Grille;
import app.pack.vue.MoteurGraphique;

public class MainActivity extends Activity {
	// Le moteur graphique du jeu
	public MoteurGraphique moteurGraphique = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		moteurGraphique = new MoteurGraphique(this);
		setContentView(moteurGraphique);
		
		Log.v("test1", "#########################");
		Log.v("test1", "Initialisation");
		new Grille();
	}



}
