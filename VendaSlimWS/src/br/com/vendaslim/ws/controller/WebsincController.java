package br.com.vendaslim.ws.controller;

import java.util.List;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.Websinc;
import br.com.vendaslim.ws.infra.WebsincHibernate;

public class WebsincController {
	

private WebsincHibernate websincHibernate;
	
	public WebsincController() {
		websincHibernate = DAOFactory.criaWebsincRepository();
	}
	
	public List<Websinc> buscaPorRepresentante(Integer idEmpresa, Integer idFilial, Integer idRepresentante){
		return websincHibernate.buscaPorRepresentante(idEmpresa, idFilial, idRepresentante);
	}
	
	public void deleteWebsinc(String sequencias){
		websincHibernate.deleteData(sequencias);
	}
}
