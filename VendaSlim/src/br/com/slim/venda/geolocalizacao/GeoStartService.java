package br.com.slim.venda.geolocalizacao;

import java.util.Calendar;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class GeoStartService extends BroadcastReceiver{	

	@Override
	public void onReceive(Context context, Intent intent) {
		Calendar cal = Calendar.getInstance();
		//cal.add(Calendar.SECOND, 1);
		cal.add(Calendar.SECOND,15);
		
		//1 Hora
		int repetir = 60 *60 * 1000;

		Intent it = new Intent(context, GeoService.class);
	
		PendingIntent pintent = PendingIntent.getService(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
				repetir, pintent);
	
		
		
		
		
		
		
		
		
		/*
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.SECOND, 1);		
		cal.set(Calendar.MINUTE,0);
		cal.set(Calendar.SECOND,0);
		
		//1 Hora
		int repetir = 60 *60 * 1000;

		Intent it = new Intent(context, GeoService.class);
	
		PendingIntent pintent = PendingIntent.getService(context, 0, it, PendingIntent.FLAG_UPDATE_CURRENT);
		
		AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
		
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
				repetir, pintent);
	*/}
}
