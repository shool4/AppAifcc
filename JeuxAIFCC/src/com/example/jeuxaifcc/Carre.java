package com.example.jeuxaifcc;

import android.graphics.RectF;

public class Carre {
	private float SIZE = 150;
	public int posX = 0;
	public int posY = 0;
	private Type mType = null;
	private RectF mRectangle = null;
	
	public Carre(Type pType, int pX, int pY) {
		this.mType = pType;
		this.mRectangle = new RectF(pX * SIZE, pY * SIZE, (pX + 1) * SIZE,(pY + 1) * SIZE);
		this.posY = pY;
		this.posX = pX;
	}

	enum Type {
		T2, T4, T8
	};

	

	

	public Type getType() {
		return mType;
	}

	public RectF getRectangle() {
		mRectangle.set(posX * SIZE, posY * SIZE, (posX + 1) * SIZE,(posY + 1) * SIZE);
		return mRectangle;
	}
	
	
}