package app.pack.gen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
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
    final Context context = this;
    public Dialog dialog = null;
    public EcouteurToucherEcran ecouteurToucherEcran = null;


    public int taillePlateau = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);




        ecouteurToucherEcran = new EcouteurToucherEcran(this);
        this.moteurGraphique = new MoteurGraphique(this);
        this.moteurGraphique.setOnTouchListener(ecouteurToucherEcran);
        //moteurGraphique.setTbBitmapCarre(moteurPhysique.ConstructionCarre());

        Log.v("test1", "#########################");
        Log.v("test1", "Initialisation");

        // custom dialog
        dialog = new Dialog(context);




        //this.resources = getResources();
    }

    /**
     * Appel lors de la restauration de l'application
     */
    @Override
    protected void onResume() {
        super.onResume();
        //isInFront = true;
        Log.i("test1"," ON RESUME **********************");

        moteurGraphique.setPauseResumeThread(true);

        //moteurGraphique.setTbBitmapCarre(moteurPhysique.ConstructionCarre());

        Log.v("test1", "#########################");
        Log.v("test1", "Initialisation");
    }

    /**
     * Fermeture application
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        moteurGraphique.kill();
        finish();
        System.exit(0);
    }
    /**
     * Appel lors de la mise en pause
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test1"," ON PAUSE **********************");
        moteurGraphique.setPauseResumeThread(false);
        // isInFront = false;
    }

    public void gauche() {
        Log.i("test1", "*********************** GAUCHE *********************");
        //   this.moteurGraphique.setMouvementFini(false);
        if(moteurGraphique.isMouvementFini()) {
            ArrayList<TuileGraphique> arrayTuileGraphique = this.moteurPhysique.gauche();
            this.moteurGraphique.setMouvement(1);
            this.moteurGraphique.setListTuilesG(arrayTuileGraphique);

            this.checkGame();

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

            this.checkGame();
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

            this.checkGame();
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

            this.checkGame();


        }
    }

    public void checkGame() {
        this.isPerdant();
        //RESTE ...
    }
    public void isPerdant(){
        if(this.moteurPhysique.isJeuxPerdant()) {


            dialog.setContentView(R.layout.dialog_perdu);

            dialog.show();







            // return true;
        }
        //return false;
    }




    public void buttonOnClick(View v) {
        Log.i("test1","---------------------------------->ok");
        //CHARGEMENT DE LA VUE GRAPHIQUE
        if(		v.getId() == R.id.button1 ||
                v.getId() == R.id.button2 ||
                v.getId() == R.id.button3
                ) {
            setContentView(moteurGraphique);
        }
        showAlert(this,"blr");
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

            case R.id.btnQuitter:
                this.moteurGraphique.kill();
            case R.id.BtnRetourMenu:
                this.moteurGraphique = new MoteurGraphique(this);
                this.moteurGraphique.setOnTouchListener(ecouteurToucherEcran);

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
