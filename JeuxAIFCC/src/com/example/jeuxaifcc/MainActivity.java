package com.example.jeuxaifcc;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	// Le moteur graphique du jeu
	public Affichage moteurGraphique = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		moteurGraphique = new Affichage(this);
		setContentView(moteurGraphique);
	
	}



}
