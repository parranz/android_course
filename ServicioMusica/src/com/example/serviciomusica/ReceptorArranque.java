package com.example.serviciomusica;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceptorArranque extends BroadcastReceiver {

	@Override
	public void onReceive(Context arg0, Intent arg1) {
		arg0.startService(new Intent(arg0, ServicioMusica.class));
	}

}
