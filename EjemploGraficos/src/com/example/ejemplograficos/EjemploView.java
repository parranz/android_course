package com.example.ejemplograficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.view.View;

public class EjemploView extends View {

	private ShapeDrawable miImagen;
 
	public EjemploView(Context context, AttributeSet attrs) {
		super(context, attrs);
		miImagen = new ShapeDrawable(new OvalShape());
		miImagen.getPaint().setColor(0xff0000ff);
	}

	@Override
	protected void onSizeChanged(int ancho, int alto, int ancho_anterior,
			int alto_anterior) {
		miImagen.setBounds(0, 0, ancho, alto);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		miImagen.draw(canvas);
	}

}
