package app.pack.modele;

import java.util.Random;

/**
 * Boite à outil
 * 
 */
public class Outil {

	/**
	 * Fonction qui genere un nombre aléatoire entre un mini et un max
	 * 
	 */
	static int aleatoireNombreEntier(int min, int max) {
		Random rand = new Random();
		int n = rand.nextInt(max - 1) + min;
		return n;
	}

	/**
	 * Fonction qui genere un nombre aléatoire entre 2 et 4
	 * 
	 */
	static int aleatoireDeuxQuatre() {
		int[] maList = { 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 };
		int unNombreAleatoire = aleatoireNombreEntier(0, 10);
		return maList[unNombreAleatoire];
	}
}
