package com.example.asteroides;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class Juego extends Activity {

	private VistaJuego vistaJuego;

	SensorManager mSensorManager;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.juego);
		vistaJuego = (VistaJuego) findViewById(R.id.VistaJuego);
		 mSensorManager = (SensorManager) this
					.getSystemService(Context.SENSOR_SERVICE);
		 vistaJuego.setPadre(this);
	}

	@Override
	protected void onStop() {
		super.onStop();
		mSensorManager.unregisterListener(vistaJuego);
	}

	@Override
	protected void onPause() {
		super.onPause();
		vistaJuego.getThread().pausar();
		mSensorManager.unregisterListener(vistaJuego);
	}

	@Override
	protected void onResume() {
		super.onResume();
		vistaJuego.getThread().reanudar();
		List<Sensor> listSensors = mSensorManager
				.getSensorList(Sensor.TYPE_ORIENTATION);
		if (!listSensors.isEmpty()) {
			Sensor orientationSensor = listSensors.get(0);
			mSensorManager.registerListener(vistaJuego, orientationSensor,
					SensorManager.SENSOR_DELAY_GAME);
		}
	}

	@Override
	protected void onDestroy() {
		vistaJuego.getThread().detener();
		mSensorManager.unregisterListener(vistaJuego);
		super.onDestroy();
	}
}