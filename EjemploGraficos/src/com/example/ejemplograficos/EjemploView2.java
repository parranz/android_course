package com.example.ejemplograficos;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.View;

public class EjemploView2 extends View {

	private ShapeDrawable miImagen;

	public EjemploView2(Context context) {
		super(context);
		miImagen  = new ShapeDrawable(new OvalShape());
		miImagen.getPaint().setColor(0xff0000ff);
		miImagen.setBounds(10, 10, 310, 60);
//		Resources res = context.getResources();
//		miImagen = res.getDrawable(R.drawable.mi_imagen);
//		miImagen.setBounds(30, 30, 200, 200);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		miImagen.draw(canvas);
//		Path trazo = new Path();
//		trazo.moveTo(50, 100);
//		trazo.cubicTo(60, 70, 150, 65, 200, 110);
//		trazo.lineTo(300, 200);
//		// trazo.addCircle(150, 150, 100, Direction.CW);
//		canvas.drawColor(Color.WHITE);
//		Paint pincel = new Paint();
//		pincel.setColor(Color.BLUE);
//		pincel.setStrokeWidth(8);
//		pincel.setStyle(Style.STROKE);
//		canvas.drawPath(trazo, pincel);
//		pincel.setStrokeWidth(1);
//		pincel.setStyle(Style.FILL);
//		pincel.setTextSize(20);
//		pincel.setTypeface(Typeface.SANS_SERIF);
//		canvas.drawTextOnPath(
//				"Desarrollo de aplicaciones para móviles con Android",
//				trazo, 10, 40, pincel);
		/*
		 * Paint pincel = new Paint();
		 * 
		 * // pincel.setColor(Color.argb(127,255,0,0));
		 * pincel.setColor(getResources().getColor(R.color.color_circulo));
		 * 
		 * pincel.setStrokeWidth(8);
		 * 
		 * pincel.setStyle(Style.FILL);
		 * 
		 * canvas.drawCircle(150, 150, 100, pincel);
		 * 
		 * pincel = new Paint();
		 * 
		 * pincel.setColor(Color.CYAN);
		 * 
		 * pincel.setStrokeWidth(8);
		 * 
		 * pincel.setStyle(Style.FILL);
		 * 
		 * canvas.drawLine(0L, 50L, 50, 100, pincel);
		 */
	}

}
