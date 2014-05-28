package br.com.slim.venda.geolocalizacao;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import br.com.slim.venda.representante.RepresentanteRotaController;

public class GeoService  extends Service{

	@Override
	public IBinder onBind(Intent intent) {
	
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		Log.i("SERVICE", "Service Created");
		Toast.makeText(getApplicationContext(), "Service Created", 1).show();
		super.onCreate();		
	}

	@Override
	public void onDestroy() {
		Log.i("SERVICE", "Service Destroy");
		Toast.makeText(getApplicationContext(), "Service Destroy", 1).show();
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		RepresentanteRotaController controller = new RepresentanteRotaController(getApplicationContext());
		controller.enviarLocalizacao();
		stopSelf();
		return super.onStartCommand(intent, flags, startId);		
	}
}
