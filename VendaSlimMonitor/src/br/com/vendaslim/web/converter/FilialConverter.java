package br.com.vendaslim.web.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.domain.cadastro.Filial;

@FacesConverter(value="filialConverter")
public class FilialConverter implements Converter{

	// Fonte http://www.guj.com.br/java/257236-jsf-20---como-pegar-objeto-inteiro-no-selectonemenu
		@Override
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
	}
