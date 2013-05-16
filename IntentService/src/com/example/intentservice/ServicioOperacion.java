package com.example.intentservice;

import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.intentservice.MainActivity.ReceptorOperacion;

public class ServicioOperacion extends IntentService {
	
	public ServicioOperacion() {
		super("ServicioOperacionService");
	}

//	@Override
//	public int onStartCommand(Intent i, int flags, int idArranque) {
//
//		double n = i.getExtras().getDouble("numero");
//
//		SystemClock.sleep(5000);
//
//		MainActivity.salida.append(n * n + "\n");
//
//		return START_STICKY;
//
//	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		 double n = intent.getExtras().getDouble("numero");

         SystemClock.sleep(5000);
//
//         MainActivity.salida.append(n*n + "\n");    
    
         Intent i = new Intent();

         i.setAction(ReceptorOperacion.ACTION_RESP);

         i.addCategory(Intent.CATEGORY_DEFAULT);

         i.putExtra("resultado", n*n);

         sendBroadcast(i);
	}

}
