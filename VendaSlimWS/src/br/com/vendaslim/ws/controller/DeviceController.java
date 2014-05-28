package br.com.vendaslim.ws.controller;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.DeviceIntegration;
import br.com.vendaslim.ws.infra.DeviceHibernate;

public class DeviceController {

private DeviceHibernate deviceHibernate;

	public DeviceController() {
		deviceHibernate = DAOFactory.criaDeviceRepository();
	}
	
	
	public void save(DeviceIntegration deviceIntegration){
		deviceHibernate.saveOrUpdate(deviceIntegration);
	}
	
	public DeviceIntegration getDeviceBySerial(String serial){
		return deviceHibernate.buscarPorSerial(serial);
	}
	
	public void saveOrGet(DeviceIntegration deviceIntegration){
		DeviceIntegration device =  getDeviceBySerial(deviceIntegration.getSerial());
		if(device != null){
			deviceIntegration = null;
			deviceIntegration = device;
		} else {
			deviceHibernate.saveOrUpdate(deviceIntegration);
		}
	}
}