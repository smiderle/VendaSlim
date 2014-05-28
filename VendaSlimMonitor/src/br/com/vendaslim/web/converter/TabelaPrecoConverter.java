package br.com.vendaslim.web.converter;

import java.util.Collection;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import org.hibernate.Hibernate;

import br.com.vendaslim.domain.pedido.TabelaPreco;

@FacesConverter(value="tabelaPrecoConverter")
public class TabelaPrecoConverter implements Converter{

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
		if(obj == null || obj.toString().trim().equals(""))
			return "";	
		TabelaPreco tabela = (TabelaPreco) obj;
		return tabela.getDescricao();
	}
	
	
	public Object findById(Collection collection, String descricao){
		for(Object obj : collection){
			TabelaPreco tabelaPreco = (TabelaPreco) obj;
			if(tabelaPreco.getDescricao().equals(descricao))
				return obj;
		}
		return null;
	}
	
	
/*	
	public Object getAsObject(FacesContext arg0, UIComponent component, String value) {
		if(value == null || value.trim().length() == 0){
			return null;
		}			
		FilialControler controler = new FilialControler();
		return controler.buscaPorDescricao(value, null);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if(value == null || value.toString().trim().equals(""))
			return "";
		//return getByReflection(value).toString();
		Filial filial = (Filial) value;
		return filial.getNomeFantasia();
	}
*/
}
