package app.pack.gen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;

import app.pack.controleur.EcouteurToucherEcran;
import app.pack.modele.MoteurPhysique;
import app.pack.modele.TuileGraphique;
import app.pack.modele.TypePartie;
import app.pack.vue.MoteurGraphique;

/**
 * Classe de l'activite
 */
public class MainActivity extends Activity{
    boolean initialise = false;
    public MoteurGraphique moteurGraphique = null;
    public MoteurPhysique moteurPhysique = null;
    public EcouteurToucherEcran ecouteurToucherEcran = null;


    // Stock la taille de la grille et des tuiles en fonction de l'ecran
    public static int tailleGrille;
    public static int tailleTuile;
    /**
     * Initialisation de l'application
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("test1","*** Methode onCreate ***");
        setContentView(R.layout.activity_main);
        this.moteurGraphique = (MoteurGraphique)findViewById(R.id.surfaceViewGrille);
        // Calcul de la taille de la grille en fonction de l'écran
        this.tailleGrille = dpToPx(39);
        // Calcul la taille des carrés en fonction de la grille
        this.tailleTuile = (int) ((tailleGrille - (5 * (tailleGrille * (1f/45f)))) / 4f);

        // custom dialog

    }

    /**
     * Restauration de l'application
     *
     */
    @Override
    protected void onResume() {
        Log.i("test1","*** Methode onResume ***");
        super.onResume();

            if(!initialise) {
                initialise = true;
                if(this.moteurGraphique.loadImageOk == false) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            loadImage();
                            // Opération consommatrice en temps exécuté par le nouveau thread
                            //appel de updateIHM par le nouveau thread

                        }
                    }).start();
                }
            }

            if(moteurPhysique != null && moteurGraphique != null ) {
                Log.i("test1","moteurGraphique différent de null**");

                moteurGraphique.setPauseResumeThread(true);
                LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                linearLayout.setVisibility(View.VISIBLE);
            }






    }

    /**
     * Fermeture l'application
     *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("test1","*** Methode onDestroy ***");
        //METHODE DE SAVE AVANT
        finish();
        System.exit(0);


    }
    /**
     * Mise en pause de l'application
     *
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test1","*** Methode onPause ***");
        if(moteurGraphique != null) {
            moteurGraphique.setPauseResumeThread(false);
        }



    }

//##########################################################################################################################################################
// Methode de mouvement
//##########################################################################################################################################################

    /**
     * Mouvement Gauche de l'activité
     */
    public void gauche() {
        if(moteurGraphique.isMouvementFini()) {
            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.gauche();
            this.moteurGraphique.setMouvement(1);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);


        }
        Log.i("test1", "*** Gauche ***");
    }

    /**
     * Mouvement Droite de l'activité
     */
    public void droite() {
        if(moteurGraphique.isMouvementFini()) {
            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.droite();
            this.moteurGraphique.setMouvement(2);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);


        }
        Log.i("test1", "*** Droite ***");
    }
    /**
     * Mouvement Haut de l'activité
     */
    public void haut() {
        if(moteurGraphique.isMouvementFini()) {
            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.haut();
            this.moteurGraphique.setMouvement(3);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);

        }
        Log.i("test1", "*** Haut ***");
    }
    /**
     * Mouvement Bas de l'activité
     */
    public void bas() {
        if(moteurGraphique.isMouvementFini()) {
            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.bas();
            this.moteurGraphique.setMouvement(4);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);

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
            LinearLayout layoutPerdu = (LinearLayout) findViewById(R.id.menu_dans_jeux);
            //RelativeLayout layoutJeux = (RelativeLayout) findViewById(R.id.layout_jeux);

            layoutPerdu.setVisibility(View.VISIBLE);

            // layoutJeux.setVisibility(View.VISIBLE);
            //l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_orange));
            // dialog.setContentView(R.layout.dialog_perdu);
            // dialog.show();
        }
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        px = displayMetrics.widthPixels - px;
        return px;
    }
public void loadImage() {
    this.moteurGraphique.load();
}
    /**
     * Methode appeller sur l'appui d'un bouton
     * @param v View
     */
    public void buttonOnClick(View v) {
        try {


            Thread.sleep(100);

        } catch (InterruptedException e){

        }
        Log.i("test1","---------------------------------->ok");
        //CHARGEMENT DE LA VUE GRAPHIQUE
        /**
         *
         * PARTIE
         *
         */
        if(		v.getId() == R.id.button_classique ||
                v.getId() == R.id.button_middlex2 ||
                v.getId() == R.id.button_lvl3
                ) {

            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
            linearLayout.setVisibility(View.INVISIBLE);


            this.ecouteurToucherEcran = new EcouteurToucherEcran(this);

            this.moteurGraphique.setOnTouchListener(this.ecouteurToucherEcran);

            //CHANGER LE FOND
            RelativeLayout l ;
            l = (RelativeLayout)findViewById(R.id.principal);
            switch (v.getId()) {

                case R.id.button_classique:
                    moteurPhysique = new MoteurPhysique(TypePartie.easyOne);

                    l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_easy));

                    break;


                case R.id.button_middlex2:
                    moteurPhysique = new MoteurPhysique(TypePartie.easyTwo);
                    l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_orange));
                    break;
                case R.id.button_lvl3:
                    moteurPhysique = new MoteurPhysique(TypePartie.normalOne);
                    l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_bleu));
                    break;
            }
            this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());

            linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
            linearLayout.setVisibility(View.INVISIBLE);

            RelativeLayout relativeLayout = (RelativeLayout)findViewById(R.id.menu_en_partie);
            relativeLayout.setVisibility(View.VISIBLE);


            /**
             *
             * MENU
             *
             */
        } else {
            LinearLayout linearLayout;
            RelativeLayout relativeLayout;
            //Load les images apres le chargement du context car dans le oncreate le context nest pas fini cela
            //creer une errreur peu etre trouve la methode qui dit context charger

            switch (v.getId()) {



                case R.id.btn_aide:

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.INVISIBLE);
                    relativeLayout = (RelativeLayout)findViewById(R.id.menu_aide);
                    relativeLayout.setVisibility(View.VISIBLE);

                    break;
                case R.id.btn_quitter:
                    //METHODE DE SAVE AVANT
                    finish();
                    System.exit(0);

                    break;
                case R.id.btn_dans_jeux_retour_menu:


                    moteurGraphique.setPauseResumeThread(false);

                    moteurPhysique = null;

                    relativeLayout = (RelativeLayout)findViewById(R.id.menu_en_partie);
                    relativeLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.VISIBLE);




                    break;
                case R.id.btn_jouer:
                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.INVISIBLE);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
                    linearLayout.setVisibility(View.VISIBLE);


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

                case R.id.btn_dans_jeux_retour_jeux:
                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.btn_retour:
                    linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
                    linearLayout.setVisibility(View.INVISIBLE);



                    relativeLayout = (RelativeLayout)findViewById(R.id.menu_aide);
                    relativeLayout.setVisibility(View.INVISIBLE);

                    relativeLayout = (RelativeLayout)findViewById(R.id.menu_en_partie);
                    relativeLayout.setVisibility(View.INVISIBLE);


                    linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
                    linearLayout.setVisibility(View.INVISIBLE);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
            }
        }




    }

    /**
     * Creer un boite de message (a voir si pas a mettre dans outil)
     * @param activity
     * @param message

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
    } */
}
