package app.pack.controleur;

import java.util.ArrayList;

import android.R.integer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import app.pack.modele.GestionMouvement;
import app.pack.modele.MoteurPhysique;

public class EcouteurToucherEcran implements OnTouchListener {
	
	private MoteurPhysique moteurPhysiqueEcouteur = null;
	private ArrayList<Float> pointDuTracerX = null;
	private ArrayList<Float> pointDuTracerY = null;
	private GestionMouvement gestionMouvement = null;
	
	public EcouteurToucherEcran(MoteurPhysique moteurPhysique) {
		// TODO Auto-generated constructor stub
		moteurPhysiqueEcouteur = moteurPhysique;
		pointDuTracerX = new ArrayList<Float>();
		pointDuTracerY = new ArrayList<Float>();
		gestionMouvement = new GestionMouvement();
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
	
		// obtenir l'indice de pointeur de l'objet de l'événement
		int pointerIndex = event.getActionIndex();

		// obtenir pointeur ID
		int pointerId = event.getPointerId(pointerIndex);

		// se masqué (non spécifique à un pointeur) l'action
		int maskedAction = event.getActionMasked();

		float posX = event.getX();
		float posY = event.getY();
		
		
		
		switch (maskedAction) {

			case MotionEvent.ACTION_DOWN:
				if(pointerId == 0) {
					//Log.i("test1",""+posX+"//"+posY);
					// On enregistre la position du début du glissé de doigt
					gestionMouvement.setDimensionView(v.getWidth(), v.getHeight());
					gestionMouvement.setPositionDepart(event.getX(), event.getY());
					//Log.i("test1",""+posX+"//"+posY);
						/*pointDuTracerX.add(posX);
						pointDuTracerY.add(posY);
						
						posYTemp = posY+100.00f;
						posYTemp = posX+100.00f;*/
					

				
					Log.i("test1","DOIGT ====>DOWN");
				}
				
			case MotionEvent.ACTION_POINTER_DOWN: {
				// TODO use data
				break;
			}
			case MotionEvent.ACTION_MOVE: { // a pointer was moved
				if(pointerId == 0) {
					gestionMouvement.addPositionBouger(event.getX(), event.getY());
					/*if(posX >posXTemp || posY > posYTemp) {
						Log.i("test1",""+posX+"//"+posY);
						pointDuTracerX.add(posX);
						pointDuTracerY.add(posY);
						
						posYTemp = posY+50.00f;
						posYTemp = posX+50.00f;
					}*/
				}
				//Log.i("test1","DOIGT ====>MOVE");
				// TODO use data
				break;
			}
			case MotionEvent.ACTION_UP:
				Log.i("test1","DOIGT ====>UP");
				if(pointerId == 0) {
					float laps = event.getEventTime() - event.getDownTime();
					gestionMouvement.setPositionFin(event.getX(), event.getY(), laps);
					
					int add = gestionMouvement.indexSlideHorizontal(500);
					
					boolean okHonrizontal = gestionMouvement.valideMouvementHonrizontal(0.2f);
					boolean okVertical = gestionMouvement.valideMouvementVertical(0.2f);
					
					gestionMouvement.reinitialiserEtat();
					
					if(add != 0) {
						if (okHonrizontal) moteurPhysiqueEcouteur.gauche();
						else if (okVertical) moteurPhysiqueEcouteur.haut();
					}

					
					
					
					/*pointDuTracerX.add(posX);
					pointDuTracerY.add(posY);
					
					float PrecedantTotalX = 0, PrecedantTotalY = 0;
					
					int avancementX = 0,avancementY = 0;
					
					for (float unPointX : pointDuTracerX) {
						PrecedantTotalX += unPointX;
						
						
					}
					PrecedantTotalX = PrecedantTotalX / pointDuTracerX.size();
					avancementX = (int) ((PrecedantTotalX - pointDuTracerX.get(0) )/pointDuTracerX.get(0)*100);
					
					for (float unPointY : pointDuTracerY) {
						PrecedantTotalY += unPointY;
						
					}
					PrecedantTotalY = PrecedantTotalY / pointDuTracerY.size();
					avancementY = (int) ((PrecedantTotalY - pointDuTracerY.get(0) )/pointDuTracerY.get(0)*100);
					
					Log.i("test1","TOTAL = >"+avancementX+" : "+avancementY);
					if(avancementX > avancementY) {
						//X
						if(avancementX > 0) {
							moteurPhysiqueEcouteur.gauche();
							Log.i("test1","doit = >Gauche");
						}
					} else {
						//Y
					}*/
					
					
				}
				

				
			case MotionEvent.ACTION_POINTER_UP:
				
			case MotionEvent.ACTION_CANCEL: {
				// TODO use data
				break;
			}
		}
	
		
		return true;
	    
		
		
	
	}
	
	  
}
