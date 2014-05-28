package br.com.slim.venda.device;

import android.content.Context;
import android.telephony.TelephonyManager;

public class DeviceController {
	
	private Context context;
	public DeviceController(Context context) {
		this.context = context;
	}
	
	public Device getDadosDevice(){
		Device device = new Device();
		TelephonyManager tManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		device.setSerial(tManager.getDeviceId());
		device.setVesaoAndroid(android.os.Build.VERSION.RELEASE);
		return device;
	}

}
