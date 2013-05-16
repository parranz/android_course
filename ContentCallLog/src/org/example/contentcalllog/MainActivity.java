package org.example.contentcalllog;

import java.util.Date;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog.Calls;
import android.text.format.DateFormat;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ContentValues valores = new ContentValues();
		valores.put(Calls.DATE, new Date().getTime());
		valores.put(Calls.NUMBER, "555555555");
		valores.put(Calls.DURATION, "55");
		valores.put(Calls.TYPE, Calls.INCOMING_TYPE);
		Uri nuevoElemento = getContentResolver().insert(Calls.CONTENT_URI,
				valores);

		ContentValues valores2 = new ContentValues();
		valores2.put(Calls.NUMBER, "444444444");
		getContentResolver().update(Calls.CONTENT_URI, valores2,
				"number='555555555'",null);
		
		String[] TIPO_LLAMADA = { "", "entrante", "saliente", "perdida" };
		TextView salida = (TextView) findViewById(R.id.salida);
		Uri llamadas = Uri.parse("content://call_log/calls");
		Cursor c = managedQuery(llamadas, null, null, null, null);
		while (c.moveToNext()) {
			salida.append("\n"
					+ DateFormat.format("dd/MM/yy k:mm (",
							c.getLong(c.getColumnIndex(Calls.DATE)))
					+ c.getString(c.getColumnIndex(Calls.DURATION))
					+ ") "
					+ c.getString(c.getColumnIndex(Calls.NUMBER))
					+ ", "
					+ TIPO_LLAMADA[Integer.parseInt(c.getString(c
							.getColumnIndex(Calls.TYPE)))]);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
