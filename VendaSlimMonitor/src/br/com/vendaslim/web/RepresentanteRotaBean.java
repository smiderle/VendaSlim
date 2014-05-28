package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.RepresentanteRotaDataModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.controler.RepresentanteRotaControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.domain.representante.RepresentanteRota;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean
@ViewScoped
public class RepresentanteRotaBean {
	private MapModel simpleModel;
	private Marker marker;
	List<RepresentanteRota> representantesRota = new ArrayList<RepresentanteRota>();
	List<Representante> representantes ;
	private Representante representante;
	private Date dtRota = new Date();
	//Localização na qual é mostrada no mapa
	private String latitudoLongitudeSelecionado = "-26.218016, -52.673166";
	
	private RepresentanteRota representanteRotaSelecionado;
	
	private RepresentanteRotaDataModel representanteRotaDataModel = new RepresentanteRotaDataModel();
	
	public RepresentanteRotaBean() {
		simpleModel = new DefaultMapModel();
		
		LatLng coord1 = new LatLng(-26.218016, -52.673166);
		LatLng coord2 = new LatLng(-26.14978, -52.401962);
        
        
        //Basic marker  
        simpleModel.addOverlay(new Marker(coord1, "19:00"));
        simpleModel.addOverlay(new Marker(coord2, "20:00"));
          
		
	}
	
	public List<RepresentanteRota> listaRepresentanteRota(){
		if(representante != null){
			RepresentanteRotaControler controler = new RepresentanteRotaControler();
			this.representantesRota = controler.buscaTodosPorRepresentante(representante, dtRota);
			representanteRotaDataModel = new RepresentanteRotaDataModel(this.representantesRota);
			if(this.representantesRota != null && this.representantesRota.size() > 0){
				RepresentanteRota representanteRota = this.representantesRota.get(representantesRota.size()-1);
				novaLatitudeLongitudeSelecionado(representanteRota);
			}
		}
		return this.representantesRota;
	}
	
	private void novaLatitudeLongitudeSelecionado(RepresentanteRota representanteRota){
		Double latitude = representanteRota.getLatitude();
		Double longitude = representanteRota.getLongitude();
		this.latitudoLongitudeSelecionado = latitude+","+longitude; 
	}
	
	public List<Representante> listaRepresentantes(){
		if(this.representantes == null){			
			Filial filial = ContextoUtil.getContextBean().getFilial();
			RepresentanteControler controler = new RepresentanteControler();
			this.representantes = controler.buscaPorFilial(filial);
		}
		return this.representantes;
	}
	
	public void handleRepresentanteChange(){
		atualizaRotas();
	}
	
	private void atualizaRotas(){
		latitudoLongitudeSelecionado = "-26.218016, -52.673166";
		simpleModel= new DefaultMapModel();
		
		if(representante != null && dtRota != null){
			listaRepresentanteRota();
			
			for(RepresentanteRota representanteRota : representantesRota){
				LatLng coordenada = new LatLng(representanteRota.getLatitude(), 
						representanteRota.getLongitude());
		        simpleModel.addOverlay(new Marker(coordenada, representanteRota.getHoraFormatada()));
			}
		}
	}
	
	public void handleDateSelect(){
		atualizaRotas();
	}
	
	public void rowSelectDataTable(){
		novaLatitudeLongitudeSelecionado(representanteRotaSelecionado);
	}
	
	public MapModel getSimpleModel() {  
        return simpleModel;  
    }  
      
    public void onMarkerSelect(OverlaySelectEvent event) {  
        marker = (Marker) event.getOverlay();  
          
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Selected", marker.getTitle()));  
    }  
      
    public Marker getMarker() {  
        return marker;  
    }  
      
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }

	

	public List<RepresentanteRota> getRepresentantesRota() {
		return representantesRota;
	}

	public void setRepresentantesRota(List<RepresentanteRota> representantesRota) {
		this.representantesRota = representantesRota;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public void setSimpleModel(MapModel simpleModel) {
		this.simpleModel = simpleModel;
	}

	public void setMarker(Marker marker) {
		this.marker = marker;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Date getDtRota() {
		return dtRota;
	}

	public void setDtRota(Date dtRota) {
		this.dtRota = dtRota;
	}

	public String getLatitudoLongitudeSelecionado() {
		return latitudoLongitudeSelecionado;
	}

	public void setLatitudoLongitudeSelecionado(String latitudoLongitudeSelecionado) {
		this.latitudoLongitudeSelecionado = latitudoLongitudeSelecionado;
	}

	public RepresentanteRota getRepresentanteRotaSelecionado() {
		return representanteRotaSelecionado;
	}

	public void setRepresentanteRotaSelecionado(
			RepresentanteRota representanteRotaSelecionado) {
		this.representanteRotaSelecionado = representanteRotaSelecionado;
	}

	public RepresentanteRotaDataModel getRepresentanteRotaDataModel() {
		return representanteRotaDataModel;
	}

	public void setRepresentanteRotaDataModel(
			RepresentanteRotaDataModel representanteRotaDataModel) {
		this.representanteRotaDataModel = representanteRotaDataModel;
	}

	
}
