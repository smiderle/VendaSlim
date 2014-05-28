package br.com.vendaslim.ws.controller;

import java.util.ArrayList;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.RepresentanteRotaIntegration;
import br.com.vendaslim.ws.infra.RepresentanteRotaHibernate;

public class RepresentanteRotaController {

private RepresentanteRotaHibernate representanteRotaHibernate;
	
	public RepresentanteRotaController() {
		representanteRotaHibernate = DAOFactory.criaRepresentanteRotaRepository();
	}
	
	public void save(ArrayList<RepresentanteRotaIntegration> lsRepresentanteRota){
		representanteRotaHibernate.save(lsRepresentanteRota);
	}
}