package com.example.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class ServicioMusica extends Service {

	MediaPlayer reproductor;
	private NotificationManager nm;
	private static final int ID_NOTIFICACION_CREAR = 1;

	@Override
	public void onCreate() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Toast.makeText(this, "Servicio creado", Toast.LENGTH_SHORT).show();
		reproductor = MediaPlayer.create(this, R.raw.audio);
	}

	@Override
	public int onStartCommand(Intent intenc, int flags, int idArranque) {
		Notification notificacion = new Notification(R.drawable.ic_launcher,
				"Creando Servicio de Música", System.currentTimeMillis());
		
		PendingIntent intencionPendiente = PendingIntent.getActivity(
		          this, 0, new Intent(this, MainActivity.class), 0);
		notificacion.setLatestEventInfo(this, "Reproduciendo música",
		       "información adicional", intencionPendiente);
		 nm.notify(ID_NOTIFICACION_CREAR, notificacion);   
		Toast.makeText(this, "Servicio arrancado " + idArranque,
				Toast.LENGTH_SHORT).show();
		reproductor.start();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Toast.makeText(this, "Servicio detenido", Toast.LENGTH_SHORT).show();
		reproductor.stop();
		nm.cancel(ID_NOTIFICACION_CREAR);
	}

	@Override
	public IBinder onBind(Intent intencion) {
		return null;
	}
}