package br.com.vendaslim.web.converter;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.vendaslim.domain.pedido.Parcelamento;
@FacesConverter(value="parcelamentoConverter")
public class ParcelamentoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		if(value == null || value.trim().length() == 0){
			return null;
		}			
		try {
			Collection items = (Collection) component.getAttributes().get("items");
			return findById(items, value);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException("Não foi possível aplicar conversão de item com valor ["+value+"] no componente ["+component.getId()+"]", e);  
		}
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {
		if(obj == null)
			return "";
		Parcelamento parcelamento = (Parcelamento) obj;
		return parcelamento.getDescricao();
	}
	
	
	public Object findById(Collection collection, String descricao){
		for(Object obj : collection){
			Parcelamento parcelamento = (Parcelamento) obj;
			if(parcelamento.getDescricao().equals(descricao))
				return obj;
		}
		return null;
	}
	
	
}
