package com.example.asteroides;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ReceptorSMS extends BroadcastReceiver {

    @Override

    public void onReceive(Context context, Intent intent) {

          Log.d("ReceptorAnuncio","intent="+ intent);

          // Creamos Notificación

          NotificationManager nm = (NotificationManager) context.getSystemService( Context.NOTIFICATION_SERVICE);

          Notification notificacion = new Notification( R.drawable.ic_launcher,"SMS Recibido", System.currentTimeMillis());

          Intent i = new Intent(context, AcercaDe.class);
          i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
          
          PendingIntent intencionPendiente = PendingIntent.getActivity( context, 0, i, 0);

          notificacion.setLatestEventInfo(context, "SMS Recibido", "", intencionPendiente);

          nm.notify(452345, notificacion);

    }

}