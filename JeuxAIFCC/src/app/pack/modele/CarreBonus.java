package app.pack.modele;

public class CarreBonus extends Carre {
	Type_Bonus typeDeBonus;
	
	public enum Type_Bonus {
		Bonus_Joker,
		Bonus_Demon,
		Bonus_Delete
	}
	public CarreBonus(int posActuelX, int posActuelY, int valeur, Type_Bonus typeDeBonus) {
		super(posActuelX, posActuelY,valeur);
		// TODO Auto-generated constructor stub
		this.typeDeBonus = typeDeBonus;
	}
	
	public CarreBonus(Position position,int valeur, Type_Bonus typeDeBonus) {
		super(position, valeur);
		// TODO Auto-generated constructor stub
		this.typeDeBonus = typeDeBonus;
	}

	public void mergeForBonus (Carre unCarre) {
		switch (this.typeDeBonus) {
		case Bonus_Joker:
			unCarre.merge(unCarre);
			
			break;
		case Bonus_Demon:
			
			break;
		case Bonus_Delete:
				
			break;

		default:
			break;
		}
	}

}
