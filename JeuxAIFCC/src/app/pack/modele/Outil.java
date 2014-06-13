package app.pack.modele;

import java.util.Random;

/**
 * Class regroupant la Boite a outil
 * 
 */
public class Outil {

    /**
     * Fonction qui genere un nombre aleatoire entre un mini et un max
     *
     * @param min
     * @param max
     * @return int
     */
	public static int aleatoireNombreEntier(int min, int max) {
		Random rand = new Random();
		int n = rand.nextInt(max - 1) + min;
		return n;
	}

    /**
     * Fonction qui genere un nombre aleatoire entre 2 et 4
     * @return int
     */
	public static int aleatoireDeuxQuatre() {
		int[] maList = { 2, 4, 2, 4, 2, 4, 2, 4, 2, 4 };
		int unNombreAleatoire = aleatoireNombreEntier(0, 10);
		return maList[unNombreAleatoire];
	}
	
}
