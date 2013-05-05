package com.example.asteroides;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Asteroides extends Activity {

	private Button bSalir;
	public static AlmacenPuntuaciones almacen;

	private static final int ALMACENAMIENTO_ARRAY = 0;

	private static final int ALMACENAMIENTO_PREFERENCIAS = 1;

	private static final int ALMACENAMIENTO_FICHERO_INTERNO = 2;
	
	private static final int ALMACENAMIENTO_FICHERO_EXTERNO = 3;
	
	private static final int ALMACENAMIENTO_FICHERO_XML = 4;
	
	private static final int ALMACENAMIENTO_BDD = 5; 
	
	private static final int ALMACENAMIENTO_NUBE = 6; 

	// private MediaPlayer mp;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		startService(new Intent(this, ServicioMusica.class));

		setContentView(R.layout.main);

		bSalir = (Button) findViewById(R.id.Button04);

		bSalir.setOnClickListener(new OnClickListener() {

			public void onClick(View view) {

				lanzarPuntuaciones(view);

			}

		});

//		SharedPreferences preferences = getSharedPreferences("com.example.asteroides_preferencess",MODE_PRIVATE);
		SharedPreferences preferences = getPreferences(MODE_PRIVATE);
		int almacenamiento = preferences.getInt("almacenamiento", -1);  ;

		if (almacenamiento == ALMACENAMIENTO_ARRAY) {
			almacen = new AlmacenPuntuacionesArray();
		} else if (almacenamiento == ALMACENAMIENTO_PREFERENCIAS) {
			almacen = new AlmacenPuntuacionesPreferencias(this);
		} else if (almacenamiento == ALMACENAMIENTO_FICHERO_INTERNO) {
			almacen = new AlmacenPuntuacionesFicheroInterno(this);
		} else if (almacenamiento == ALMACENAMIENTO_FICHERO_EXTERNO) {
			almacen = new AlmacenPuntuacionesFicheroExterno(this);
		} else if (almacenamiento == ALMACENAMIENTO_FICHERO_XML) {
				almacen = new AlmacenPuntuacionesXML_SAX(this);
		} else if (almacenamiento == ALMACENAMIENTO_BDD) {
			almacen = new AlmacenPuntuacionesSQLite(this);
		} else if (almacenamiento == ALMACENAMIENTO_NUBE) {
			almacen = new AlmacenPuntuacionesSocket();
		} else {
			Toast.makeText(this, "No se ha encontrado la propiedad de tipo de almacenamiento, se coge bdd por defecto", Toast.LENGTH_SHORT).show();
			almacen = new AlmacenPuntuacionesSQLite(this);
		}
		
		Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

		// mp = MediaPlayer.create(this, R.raw.audio);
		// mp.start();
	}

	public void lanzarPuntuaciones(View view) {
		Intent i = new Intent(this, Puntuaciones.class);
		startActivity(i);
	}

	public void lanzarAcercaDe(View view) {
		Intent i = new Intent(this, AcercaDe.class);
		startActivity(i);
	}

	public void lanzarPreferencias(View view) {
		Intent i = new Intent(this, Preferencias.class);
		startActivity(i);
	}

	public void lanzarJuego(View view) {
		Intent i = new Intent(this, Juego.class);
		startActivityForResult(i, 1234);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == 1234 & resultCode == RESULT_OK & data != null) {
			int puntuacion = data.getExtras().getInt("puntuacion");
			String nombre = "Yo";
			// Mejor leerlo desde un Dialog o una nueva actividad
			// //AlertDialog.Builder
			almacen.guardarPuntuacion(puntuacion, nombre,
					System.currentTimeMillis());
			lanzarPuntuaciones(null);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
		/** true -> el menú ya está visible */
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.acercaDe:
			lanzarAcercaDe(null);
			break;
		case R.id.config:
			lanzarPreferencias(null);
			break;
		}
		return true;
		/** true -> consumimos el item, no se propaga */
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onPause() {
		Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
		super.onPause();
		// mp.pause();
	}

	@Override
	protected void onStop() {
		Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
		super.onStop();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		// mp.start();
		Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
	}

	@Override
	protected void onDestroy() {
		Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
		super.onDestroy();
		stopService(new Intent(this, ServicioMusica.class));
		// mp.stop();
	}

	// @Override
	// protected void onRestoreInstanceState(Bundle savedInstanceState) {
	// super.onRestoreInstanceState(savedInstanceState);
	// if (savedInstanceState!=null && mp!=null) {
	// int pos = savedInstanceState.getInt("pos");
	// mp.seekTo(pos);
	// }
	// }
	//
	// @Override
	// protected void onSaveInstanceState(Bundle outState) {
	// super.onSaveInstanceState(outState);
	// if (outState!=null && mp != null) {
	// int pos = mp.getCurrentPosition();
	// outState.putInt("pos", pos);
	// }
	// }

}
