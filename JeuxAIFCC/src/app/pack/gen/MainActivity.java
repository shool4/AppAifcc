package app.pack.gen;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
   // public SurfaceView sfvTrack = null;

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
        //moteurGraphique.setAlpha(PixelFormat.TRANSPARENT);

        //SurfaceHolder sfvTrack = (SurfaceHolder)findViewById(R.id.surfaceViewGrille);
        //sfvTrack.setFormat(PixelFormat.TRANSPARENT);
        //sfvTrack.setZOrderOnTop(true);
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

        View view_instance;

        for (int i = 0; i != 2; i++) {

            if(i == 1){
                view_instance = findViewById(R.id.menu_dans_jeux_perdu);
            } else {
                view_instance = findViewById(R.id.menu_dans_jeux_reprise);
            }
            ViewGroup.LayoutParams params=view_instance.getLayoutParams();
            params.width=this.tailleGrille;
            params.height = this.tailleGrille;
            view_instance.setLayoutParams(params);

        }


        Log.i("test1","*** Methode onResume ***");
        super.onResume();

            if(!initialise) {
                initialise = true;
                if(this.moteurGraphique.loadImageOk == false) {


                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            loadImage();
                            // Gets linearlayout




                            // Changes the height and width to the specified *pixels*

                            // Opération consommatrice en temps exécuté par le nouveau thread
                            //appel de updateIHM par le nouveau thread

                        }
                    }).start();
                }
            }

                    chargeSauvegarde();
                    // Gets linearlayout




                    // Changes the height and width to the specified *pixels*

                    // Opération consommatrice en temps exécuté par le nouveau thread
                    //appel de updateIHM par le nouveau thread



            if(moteurPhysique != null && moteurGraphique != null ) {
                this.ecouteurToucherEcran.setActiveEcouteMouvement(false);
                Log.i("test1","moteurGraphique différent de null**");

                LinearLayout menuPerdu = (LinearLayout)findViewById(R.id.menu_dans_jeux_perdu);
                moteurGraphique.setPauseResumeThread(true);

                if(menuPerdu.getVisibility() != View.VISIBLE) {
                    LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.VISIBLE);
                }


            }






    }

    public void chargeSauvegarde(){
        File file1 = new File(Environment.getExternalStorageDirectory()+"/saveSquare.xml");
        if(file1.exists()){
            LinearLayout linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
            linearLayout.setVisibility(View.INVISIBLE);
            this.ecouteurToucherEcran = new EcouteurToucherEcran(this);
            this.moteurGraphique.setOnTouchListener(this.ecouteurToucherEcran);
            //CHANGER LE FOND
            RelativeLayout l ;
            l = (RelativeLayout)findViewById(R.id.principal);

            ObjectInputStream ois = null;
            try
            {
                Log.v("tuileMerge", "Deserialisation de : " + file1.getAbsolutePath());
                FileInputStream fos = new FileInputStream(file1);
                try{
                    ois = new ObjectInputStream(fos);
                    Object MP = ois.readObject();
                    this.moteurPhysique = (MoteurPhysique) MP;
                    l.setBackground(getResources().getDrawable(R.drawable.fond_jeux_easy));
                    TextView textScore = (TextView)findViewById(R.id.txt_valeur_score);
                    textScore.setText("" + this.moteurPhysique.calculScore());
                }catch(Exception ex){
                    Log.v("tuileMerge", "Erreur lecture MP : " + ex.getMessage());
                    ex.printStackTrace();
                }
            }
            catch (IOException ex)
            {
                ex.printStackTrace();
            }

            this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());

            linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
            linearLayout.setVisibility(View.INVISIBLE);

            linearLayout = (LinearLayout)findViewById(R.id.grille_en_jeux_layout);
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
        sauvegardePartie();
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
        if(moteurGraphique.isMouvementFini() ) {

            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.gauche();
            this.moteurGraphique.setMouvement(1);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            TextView textScore = (TextView)findViewById(R.id.txt_valeur_score);
            textScore.setText("" + this.moteurPhysique.calculScore());
        }

        Log.i("test1", "*** Gauche ***");
    }

    /**
     * Mouvement Droite de l'activité
     */
    public void droite() {
        if(moteurGraphique.isMouvementFini() ) {

            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.droite();
            this.moteurGraphique.setMouvement(2);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            TextView textScore = (TextView)findViewById(R.id.txt_valeur_score);
            textScore.setText("" + this.moteurPhysique.calculScore());
        }
        Log.i("test1", "*** Droite ***");
    }
    /**
     * Mouvement Haut de l'activité
     */
    public void haut() {
        if(moteurGraphique.isMouvementFini() ) {

            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.haut();
            this.moteurGraphique.setMouvement(3);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            TextView textScore = (TextView)findViewById(R.id.txt_valeur_score);
            textScore.setText("" + this.moteurPhysique.calculScore());
        }
        Log.i("test1", "*** Haut ***");
    }
    /**
     * setMouvement
     * @param mouvement int
     */
    public void actualiseGraphique() {
        this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuile());
        // this.mouvement = mouvement;
    }
    /**
     * Mouvement Bas de l'activité
     */
    public void bas() {
        if(moteurGraphique.isMouvementFini() ) {

            this.checkGame();
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.bas();
            this.moteurGraphique.setMouvement(4);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);
            TextView textScore = (TextView)findViewById(R.id.txt_valeur_score);
            textScore.setText("" + this.moteurPhysique.calculScore());
        }
        Log.i("test1", "*** Bas ***");
    }

    public void supprimePartie(){


            File file1 = new File(Environment.getExternalStorageDirectory()+"/saveSquare.xml");
            file1.delete();

    }
    /**
     * Sauvegarde de la partie quand l'utilisateur quitte l'application.
     * Serialisation d'un objet
     * Enregistrement de "moteurPhysique"
     */
    public void sauvegardePartie(){
        if(this.moteurPhysique != null) {
            ObjectOutputStream oos = null;
            try
            {
                File file1 = new File(Environment.getExternalStorageDirectory()+"/saveSquare.xml");
                file1.getParentFile().createNewFile();
                Log.v("tuileMerge", "Sauvegarde ici : " + file1.getAbsolutePath());
                FileOutputStream fos = new FileOutputStream(file1);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(this.moteurPhysique);
            }
            catch (FileNotFoundException ex)
            {
                Log.v("serialisation", "File not found : " + ex.getMessage());
                ex.printStackTrace();
            } catch (IOException ex)
            {
                Log.v("serialisation", "IO exception : " + ex.getMessage());
                ex.printStackTrace();
            }
        }

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
            LinearLayout layoutPerdu;
            this.ecouteurToucherEcran.setActiveEcouteMouvement(false);
            layoutPerdu = (LinearLayout) findViewById(R.id.menu_dans_jeux_perdu);
            //RelativeLayout layoutJeux = (RelativeLayout) findViewById(R.id.layout_jeux);

            layoutPerdu.setVisibility(View.VISIBLE);


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

            linearLayout = (LinearLayout)findViewById(R.id.grille_en_jeux_layout);
            linearLayout.setVisibility(View.VISIBLE);


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

                case R.id.btn_menu_reprendre_recommencer :
                    this.ecouteurToucherEcran.setActiveEcouteMouvement(true);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.INVISIBLE);

                    // this.ecouteurToucherEcran = new EcouteurToucherEcran(this);
                    //  this.moteurGraphique.setOnTouchListener(this.ecouteurToucherEcran);
                    moteurPhysique = new MoteurPhysique(this.moteurPhysique.uneTypeDePartie);
                    this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());

                    break;


                case R.id.btn_menu_perdu_rejouer:
                    this.ecouteurToucherEcran.setActiveEcouteMouvement(true);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_perdu);
                    linearLayout.setVisibility(View.INVISIBLE);

                   // this.ecouteurToucherEcran = new EcouteurToucherEcran(this);
                  //  this.moteurGraphique.setOnTouchListener(this.ecouteurToucherEcran);
                    moteurPhysique = new MoteurPhysique(this.moteurPhysique.uneTypeDePartie);
                    this.moteurGraphique.setListTuilesG(this.moteurPhysique.getGrilleGraphiqueDeTuileNonVide());

                    break;
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
                case R.id.btn_menu_type_jeux_retour :
                    linearLayout = (LinearLayout)findViewById(R.id.menu_type_jeux);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case  R.id.btn_menu_perdu_retour_menu:
                    moteurGraphique.setPauseResumeThread(false);
                    moteurPhysique = null;

                    linearLayout = (LinearLayout)findViewById(R.id.grille_en_jeux_layout);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_perdu);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.VISIBLE);
                    supprimePartie();
                break;

                case R.id.btn_petit_menu :
                    this.ecouteurToucherEcran.setActiveEcouteMouvement(false);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.VISIBLE);
                    break;
                case R.id.btn_menu_reprendre_aller_menu:


                    moteurGraphique.setPauseResumeThread(false);

                    moteurPhysique = null;

                    linearLayout = (LinearLayout)findViewById(R.id.grille_en_jeux_layout);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.INVISIBLE);

                    linearLayout = (LinearLayout)findViewById(R.id.menu_acceuil);
                    linearLayout.setVisibility(View.VISIBLE);
                    supprimePartie();



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

                case R.id.btn_menu_reprendre_reprendre:
                    this.ecouteurToucherEcran.setActiveEcouteMouvement(true);
                    linearLayout = (LinearLayout)findViewById(R.id.menu_dans_jeux_reprise);
                    linearLayout.setVisibility(View.INVISIBLE);
                    break;
                case R.id.btn_menu_aide_retour:
               



                    relativeLayout = (RelativeLayout)findViewById(R.id.menu_aide);
                    relativeLayout.setVisibility(View.INVISIBLE);

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
