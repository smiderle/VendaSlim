package br.com.vendaslim.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
//@FacesConverter(forClass=String.class)
public class StringConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if(value != null && value.trim().equals(""))
			value = null;
		return value;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object obj) {		
		return (String) obj;
	}

}
