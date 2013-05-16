package com.example.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private EditText entrada;
	private TextView salida;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		entrada = (EditText) findViewById(R.id.EditText01);
		salida = (TextView) findViewById(R.id.TextView01);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.permitNetwork().build());
	}

	public void buscar(View view) {
		try {
			String palabras = entrada.getText().toString();
			String resultado = resultadosGoogle(palabras);
			salida.append(palabras + "--" + resultado + "\n");
		} catch (Exception e) {
			salida.append("Error al conectar\n");
			Log.e("HTTP", e.getMessage(), e);
		}
	}

	String resultadosGoogle(String palabras) throws Exception {
		String pagina = "", devuelve = "";
		URL url = new URL("http://www.google.es/search?hl=es&q=\""
				+ URLEncoder.encode(palabras, "UTF-8") + "\"");
		HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
		conexion.setRequestProperty("User-Agent",
				"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					conexion.getInputStream()));
			String linea = reader.readLine();
			while (linea != null) {
				pagina += linea;
				linea = reader.readLine();
			}
			reader.close();
			int ini = pagina.indexOf("Aproximadamente");
			if (ini != -1) {
				int fin = pagina.indexOf(" ", ini + 16);
				devuelve = pagina.substring(ini + 16, fin);
			} else {
				devuelve = "no encontrado";
			}
		} else {
			salida.append("ERROR: " + conexion.getResponseMessage() + "\n");
		}
		conexion.disconnect();
		return devuelve;
	}
}