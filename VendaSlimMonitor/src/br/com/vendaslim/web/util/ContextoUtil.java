package br.com.vendaslim.web.util;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.vendaslim.web.ContextoBean;

public class ContextoUtil {
	
	public static ContextoBean getContextBean(){
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ContextoBean contextBean = (ContextoBean) session.getAttribute("contextoBean");
		return contextBean;
	}

}
