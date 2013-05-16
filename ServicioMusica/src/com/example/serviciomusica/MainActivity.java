package com.example.serviciomusica;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private NotificationManager nm;
	private static final int ID_NOTIFICACION_SOCORRO = 3647382;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		
		setContentView(R.layout.activity_main);

		Button arrancar = (Button) findViewById(R.id.boton_arrancar);
		arrancar.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				startService(new Intent(MainActivity.this, ServicioMusica.class));
			}
		});
		Button detener = (Button) findViewById(R.id.boton_detener);
		detener.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				stopService(new Intent(MainActivity.this,
						ServicioMusica.class));
			}
		});
		
		Button socorro = (Button) findViewById(R.id.boton_socorro);
		socorro.setOnClickListener(new OnClickListener() {
			public void onClick(View view) {
				creaNotificacionSocorro();
			}
		});
	}
	
	public void creaNotificacionSocorro() {
		
		Notification notificacion = new Notification(R.drawable.ic_launcher,
				"¡SOCORRO!", System.currentTimeMillis());
		
		notificacion.sound = Uri.parse("android.resource://com.example/serviciomusica/"+R.raw.audio);
		
		long[] vibrate = {0,100, 100,100, 100,100, 100,200, 100,200, 100,200, 100,100, 100,100, 100,100};
		notificacion.vibrate = vibrate;
		
		PendingIntent intencionPendiente = PendingIntent.getActivity(
		          this, 0, new Intent(this, MainActivity.class), 0);
		notificacion.setLatestEventInfo(this, "Notificacion de Socorro!",
		       "Help me!!", intencionPendiente);
		
		nm.notify(ID_NOTIFICACION_SOCORRO, notificacion);
	}
}
