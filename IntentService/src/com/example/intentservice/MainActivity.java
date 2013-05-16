package com.example.intentservice;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText entrada;

	public static TextView salida;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);

		entrada = (EditText) findViewById(R.id.entrada);

		salida = (TextView) findViewById(R.id.salida);

	}

	public void calcularOperacion(View view) {

		double n = Double.parseDouble(entrada.getText().toString());

		salida.append(n + "^2 = ");

		Intent i = new Intent(this, ServicioOperacion.class);

		i.putExtra("numero", n);

		startService(i);

	}

	public class ReceptorOperacion extends BroadcastReceiver {

		public static final String ACTION_RESP = "com.example.intentservice.intent.action.RESPUESTA_OPERACION";

		@Override
		public void onReceive(Context context, Intent intent) {

			Double res = intent.getDoubleExtra("resultado", 0.0);

			salida.append(" " + res);
			IntentFilter filtro = new IntentFilter(
					ReceptorOperacion.ACTION_RESP);

			filtro.addCategory(Intent.CATEGORY_DEFAULT);

			registerReceiver(new ReceptorOperacion(), filtro);
		}

	}

}
