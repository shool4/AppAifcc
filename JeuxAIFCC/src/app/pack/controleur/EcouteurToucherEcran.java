package app.pack.controleur;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import app.pack.gen.MainActivity;

/**
 * Class Ecouteur de la zone de jeux lors du jeux en marche
 *
 */
public class EcouteurToucherEcran implements OnTouchListener {
    /* la position de départ du doigt */
    private float start_x;
    private float start_y;
    private MainActivity mainActivity = null;
    public Boolean activeMove = false;
    public EcouteurToucherEcran(MainActivity mainActivity) {

        this.mainActivity = mainActivity;

    }

    /**
     * Methode lors de l'appui sur l'ecran
     * @param v View
     * @param event MotionEvent
     * @return boolean
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        // obtenir l'indice de pointeur de l'objet de l'événement
        int pointerIndex = event.getActionIndex();

        // obtenir pointeur ID
        int pointerId = event.getPointerId(pointerIndex);

        // se masqué (non spécifique à un pointeur) l'action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                if(pointerId == 0) {
                    start_x = event.getX();
                    start_y = event.getY();
                    activeMove = true;

                }

            case MotionEvent.ACTION_POINTER_DOWN: {
                // TODO use data
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved
                if(pointerId == 0) {

                    if(start_x+100 <= event.getX() && activeMove) {
                        mainActivity.droite();
                        activeMove = false;
                    }
                    if(start_x-100 >= event.getX() && activeMove ) {
                        mainActivity.gauche();
                        activeMove = false;
                    }
                    if(start_y+100 <= event.getY() && activeMove) {
                        mainActivity.bas();
                        activeMove = false;
                    }
                    if(start_y-100 >= event.getY() && activeMove ) {
                        mainActivity.haut();
                        activeMove = false;
                    }
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                activeMove = false;
            case MotionEvent.ACTION_POINTER_UP:

            case MotionEvent.ACTION_CANCEL: {
                activeMove = false;
                break;
            }
        }
        return true;
    }

}
