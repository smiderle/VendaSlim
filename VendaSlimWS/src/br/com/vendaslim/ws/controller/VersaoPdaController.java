package br.com.vendaslim.ws.controller;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.VersaoPda;
import br.com.vendaslim.ws.infra.VersaoPdaHibernate;

public class VersaoPdaController {
private VersaoPdaHibernate versaoPdaHibernate;
	
	public VersaoPdaController() {
		versaoPdaHibernate = DAOFactory.criaVersaoPdaRepository();
	}
	
	public VersaoPda buscaPorSerial(String serial){
		return versaoPdaHibernate.buscaPorSerial(serial);
	}
	
	
	public void insert(VersaoPda versaoPda){
		versaoPdaHibernate.save(versaoPda);
	}

}
