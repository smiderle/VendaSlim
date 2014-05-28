package br.com.slim.venda.geolocalizacao;



import java.util.List;
import java.util.Locale;

import org.holoeverywhere.widget.Toast;

import android.content.ContentResolver;
import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import br.com.slim.venda.representante.RepresentanteRotaController;


/*Fonte http://stackoverflow.com/questions/9764169/how-to-disable-android-locationlistener-when-application-is-closed*/
public class MyLocationManager {

	private int tempo = 60 * 60 * 1000;
    private Context context;
    private double myLat = 0.0;
    private double myLon = 0.0;
    private LocationManager locationManager = null;
    private Location location = null;
    private Criteria criteria;
    private String locationName = null;

    public MyLocationManager(Context ctx) {
        this.context = ctx;
    }

    private String setCriteria() {
        this.criteria = new Criteria();
        this.criteria.setAccuracy(Criteria.ACCURACY_FINE);
        this.criteria.setAltitudeRequired(false);
        this.criteria.setBearingRequired(false);
        this.criteria.setCostAllowed(true);
        this.criteria.setPowerRequirement(Criteria.POWER_LOW);
        return locationManager.getBestProvider(criteria, true);
    }

    public double getMyLatitude() {
        return this.myLat;
    }

    public double getMyLongitude() {
        return this.myLon;
    }

    public String getLocation() {
        return this.locationName;
    }

    public void onLocationUpdate() {
    	
    	if(gpsStatusOn()){
	        locationManager = (LocationManager) context
	                .getSystemService(Context.LOCATION_SERVICE);
	        String provider = setCriteria();
	
	        location = locationManager.getLastKnownLocation(provider);

	        updateWithNewLocation(location);
	        locationManager.requestLocationUpdates(provider, tempo, 0,
	                new MyLocationListener());
    	}

    }

    private void updateWithNewLocation(Location location) {
        if (location != null) {
            this.myLat = location.getLatitude();
            this.myLon = location.getLongitude();
            getLocationName(this.myLat, this.myLon);
        }/* else {
            Toast.makeText(this.context, "Localização indefinida",
                    Toast.LENGTH_SHORT).show();
        }*/
    }
    
    private Boolean gpsStatusOn() {
		ContentResolver contentResolver = context.getContentResolver();
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}

    private void getLocationName(double lat, double lon) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> adresses = geocoder.getFromLocation(lat, lon, 1);
            StringBuilder sb = new StringBuilder();
            if (adresses.size() > 0) {
                Address address = adresses.get(0);
                for (int i = 0; i < address.getMaxAddressLineIndex(); i++)
                    sb.append(address.getAddressLine(i)).append("\n");
                sb.append(address.getCountryName()).append("\n");
            }
            this.locationName = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class MyLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(Location newLocation) {
            myLat = newLocation.getLatitude();
            myLon = newLocation.getLongitude();
            getLocationName(myLat, myLon);
            Toast.makeText(context,
                    "Lat: " + myLat + "\nLon: " + myLon +"\nAltitude "+ newLocation.getAltitude(),
                    Toast.LENGTH_SHORT).show();
            saveLocation(myLat, myLon);
        }

        @Override
        public void onProviderDisabled(String arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String arg0) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
            // TODO Auto-generated method stub
        }

    }
    
    private void saveLocation(double lat, double lon){
    	RepresentanteRotaController controller = new RepresentanteRotaController(context);
    	controller.saveLocation(lat, lon);
    }

}
