package app.pack.gen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.pack.controleur.EcouteurToucherEcran;
import app.pack.modele.MoteurPhysique;
import app.pack.modele.TuileGraphique;
import app.pack.modele.TypePartie;
import app.pack.vue.MoteurGraphique;

/**
 * ACTIVITE PRINCIPAL
 *
 *
 */
public class MainActivity extends Activity{


    public MoteurGraphique moteurGraphique = null;
    public MoteurPhysique moteurPhysique = null;
    final Context context = this;
    public Dialog dialog = null;
    public EcouteurToucherEcran ecouteurToucherEcran = null;
    public int taillePlateau = 4;

    // Stock la taille de la grille et des tuiles en fonction de l'ecran
    public static int tailleGrille;
    public static int tailleTuile;
    /**
     * Initialisation de l'application
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        // Calcul de la taille de la grille en fonction de l'écran
        this.tailleGrille = dpToPx(39);
        // Calcul la taille des carrés en fonction de la grille
        this.tailleTuile = (int) ((tailleGrille - (5 * (tailleGrille * (1f/45f)))) / 4f);

        // custom dialog
        dialog = new Dialog(context);
    }

    /**
     * Restauration de l'application
     *
     */
    @Override
    protected void onResume() {
        super.onResume();
        if(moteurGraphique != null) {
            moteurGraphique.setPauseResumeThread(true);
        }


        Log.i("test1","*** Methode onResume ***");

    }

    /**
     * Fermeture l'application
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        moteurGraphique.kill();
        finish();
        System.exit(0);

        Log.i("test1","*** Methode onDestroy ***");
    }
    /**
     * Mise en pause de l'application
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        moteurGraphique.setPauseResumeThread(false);

        Log.i("test1","*** Methode onPause ***");
    }

//##########################################################################################################################################################
// Methode de mouvement
//##########################################################################################################################################################

    /**
     * Mouvement Gauche de l'activité
     */
    public void gauche() {
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.gauche();
            this.moteurGraphique.setMouvement(1);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            this.checkGame();

        }
        Log.i("test1", "*** Gauche ***");
    }

    /**
     * Mouvement Droite de l'activité
     */
    public void droite() {
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.droite();
            this.moteurGraphique.setMouvement(2);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            this.checkGame();

        }
        Log.i("test1", "*** Droite ***");
    }
    /**
     * Mouvement Haut de l'activité
     */
    public void haut() {
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.haut();
            this.moteurGraphique.setMouvement(3);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            this.checkGame();
        }
        Log.i("test1", "*** Haut ***");
    }
    /**
     * Mouvement Bas de l'activité
     */
    public void bas() {
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.bas();
            this.moteurGraphique.setMouvement(4);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            this.checkGame();
        }
        Log.i("test1", "*** Bas ***");
    }

    /**
     * Permet de checker le jeux en cours si perdant ou gagnant et methode a appliquer en fonction
     */
    public void checkGame() {
        this.isPerdant();
        //RESTE ...
    }

    /**
     * Cheche si le jeux et perdant
     */
    public void isPerdant(){
        if(this.moteurPhysique.isJeuxPerdant()) {
            dialog.setContentView(R.layout.dialog_perdu);
            dialog.show();
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        px = displayMetrics.widthPixels - px;
        return px;
    }

    /**
     * Methode appeller sur l'appui d'un bouton
     * @param v
     */
    public void buttonOnClick(View v) {
        Log.i("test1","---------------------------------->ok");
        //CHARGEMENT DE LA VUE GRAPHIQUE
        /**
         *
         * PARTIE
         *
         */
        if(		v.getId() == R.id.button_classique ||
                v.getId() == R.id.button_middlex2 ||
                v.getId() == R.id.button_hard
                ) {

			setContentView(R.layout.activity_main);
            EcouteurToucherEcran ecouteurToucherEcran = new EcouteurToucherEcran(this);


            this.moteurGraphique = (MoteurGraphique)findViewById(R.id.surfaceViewGrille);
            this.moteurGraphique.setOnTouchListener(ecouteurToucherEcran);

            //CHANGER LE FOND
            LinearLayout l ;
            l = (LinearLayout)findViewById(R.id.principal);
            //****
            switch (v.getId()) {

                case R.id.button_classique:
                    moteurPhysique = new MoteurPhysique(TypePartie.easy);

                    l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_easy));

                    break;


                case R.id.button_middlex2:
                    moteurPhysique = new MoteurPhysique(TypePartie.normal);
                    break;
                case R.id.button_hard:
                    moteurPhysique = new MoteurPhysique(TypePartie.hard);
                    break;
            }
            this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());

            /**
             *
             * MENU
             *
             */
        } else {

            switch (v.getId()) {

                case R.id.button_classique:
                    moteurPhysique = new MoteurPhysique(TypePartie.easy);
                    this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
                    break;



                case R.id.button_middlex2:
                    moteurPhysique = new MoteurPhysique(TypePartie.normal);
                    this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
                    break;
                case R.id.button_hard:
                    moteurPhysique = new MoteurPhysique(TypePartie.hard);
                    this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
                    break;

                case R.id.ok:
                    dialog.dismiss();

                    break;
                case R.id.btn_jouer:
                    setContentView(R.layout.menu_jouer_partie);
                    break;
           /* case R.id.button3:
                moteurPhysique = new MoteurPhysique(TypePartie.normal);
                this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());
                break;*/
                case R.id.button3:
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

                case R.id.btnQuitter:
                    this.moteurGraphique.kill();
                case R.id.BtnRetourMenu:
                    this.moteurGraphique = new MoteurGraphique(this);
                    this.moteurGraphique.setOnTouchListener(ecouteurToucherEcran);

                    break;
            }
        }




    }

    /**
     * Creer un boite de message (a voir si pas a mettre dans outil)
     * @param activity
     * @param message
     */
    public static void showAlert(Activity activity, String message) {
        TextView title = new TextView(activity);
        title.setText("Title");

        title.setPadding(10, 10, 10, 10);
        title.setGravity(Gravity.CENTER);
        title.setTextColor(Color.WHITE);
        title.setTextSize(20);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        // builder.setTitle("Title");
        builder.setCustomTitle(title);
        // builder.setIcon(R.drawable.alert_36);

        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();

            }

        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
