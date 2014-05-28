package br.com.vendaslim.web.converter;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.representante.GrupoRepresentante;

@FacesConverter(value="repreGrupoConverter")
public class RepesentanteGrupoConverter implements Converter{

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
		if(obj == null || obj.equals(""))
			return "";
		GrupoRepresentante grupoRepresentante = (GrupoRepresentante) obj;
		return grupoRepresentante.getDescricao();
	}
	
	public Object findById(Collection collection, String descricao){
		for(Object obj : collection){
			GrupoRepresentante grupoRepresentante = (GrupoRepresentante) obj;
			if(grupoRepresentante.getDescricao().equals(descricao))
				return obj;
		}
		return null;
	}

}
