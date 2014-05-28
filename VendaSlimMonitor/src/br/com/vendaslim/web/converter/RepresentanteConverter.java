package br.com.vendaslim.web.converter;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.domain.representante.Representante;
@FacesConverter(value="representanteConverter")
public class RepresentanteConverter implements Converter {
	
/*

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		if(value == null || value.trim().length() == 0)
			return null;
		RepresentanteControler controler = new RepresentanteControler();
		return controler.buscaPorNome(value, null);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj == null)
			return "";
		Representante representante = (Representante) obj;
		return representante.getNome();
	}
		*/
	

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		if(value == null || value.trim().length() == 0)
			return null;
		try{
			Collection items = (Collection) component.getAttributes().get("items");
			return findById(items, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException("Não foi possível aplicar conversão de item com valor ["+value+"] no componente ["+component.getId()+"]", e);  
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj == null || obj.toString().trim().equals(""))
			return "";
		Representante representante = (Representante) obj;
		return representante.getNome();
	}
	
	public Object findById(Collection collection, String nomeRepresentante){
		for(Object obj : collection){
			Representante representante = (Representante) obj;
			if(representante.getNome().equals(nomeRepresentante))
				return obj;
		}
		return null;
	}
}
