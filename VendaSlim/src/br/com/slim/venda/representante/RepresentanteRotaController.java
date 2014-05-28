package br.com.slim.venda.representante;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.holoeverywhere.app.Activity;
import org.holoeverywhere.widget.Toast;

import android.content.ContentResolver;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import br.com.slim.venda.integration.Integration;

public class RepresentanteRotaController {
		
	private Context context;
	public RepresentanteRotaController(Context context) {
		this.context = context;
	}
	
	public void saveLocation(double lat, double lon){
		RepresentanteRotaVO representanteRotaVO = new RepresentanteRotaVO();
		representanteRotaVO.setIdEmpresa(1);
		representanteRotaVO.setIdRepresentante(1);
		representanteRotaVO.setDtHrRotaLong(new Date().getTime());
		representanteRotaVO.setLatitude(lat);
		representanteRotaVO.setLongitude(lon);
		insertOrUpdate(representanteRotaVO);
	}
	
	public void insertOrUpdate(ArrayList<RepresentanteRotaVO> lsRepresentanteRotaVO){
		for (RepresentanteRotaVO representanteRotaVO : lsRepresentanteRotaVO) {
			representanteRotaVO.setSincronizado(1);//TODO ALTERAR
			insertOrUpdate(representanteRotaVO);
		}		
	}
	
	public void insertOrUpdate(RepresentanteRotaVO representanteRotaVO){
		RepresentanteRotaDAO representanteRotaDAO = new RepresentanteRotaDAO(context);
		representanteRotaDAO.insertOrUpdate(representanteRotaVO);
		representanteRotaDAO.close();
	}
	
	
	public ArrayList<RepresentanteRotaVO> getAllNaoSincronizado(){
		RepresentanteRotaDAO dao = new RepresentanteRotaDAO(context);
		ArrayList<RepresentanteRotaVO> lsRepresentanteRotaVO =  dao.getAllNaoSincronizado();
		dao.close();
		return lsRepresentanteRotaVO;
	}
	
	

	public void getGeoLocation(){
		LocationManager lm = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		if(lm != null){
			Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			
			if(location != null){
				double longitude = location.getLongitude();
				double latitude = location.getLatitude();

				RepresentanteRotaVO representanteRotaVO = new RepresentanteRotaVO();
				representanteRotaVO.setIdEmpresa(1);
				representanteRotaVO.setIdRepresentante(1);
				representanteRotaVO.setDtHrRotaLong(new Date().getTime());
				representanteRotaVO.setLatitude(latitude);
				representanteRotaVO.setLongitude(longitude);
				Toast.makeText(context, representanteRotaVO.getDtHrRotaLong()+" " + representanteRotaVO.getLatitude()+" "+ representanteRotaVO.getLongitude(), Toast.LENGTH_SHORT).show();
				insertOrUpdate(representanteRotaVO);
			}			
		}
	}
	
	/*----------Method to Check GPS is enable or disable ------------- */
	private Boolean displayGpsStatus() {
		ContentResolver contentResolver = context.getContentResolver();
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (gpsStatus) {
			return true;

		} else {
			return false;
		}
	}
	
	
	
	public void enviarLocalizacao(){
		Integration it = new Integration(context);
		it.enviarLocalizacao();
	}
	
	
	
	

}
