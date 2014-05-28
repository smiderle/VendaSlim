package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.RepresentanteFilialIntegration;
import br.com.vendaslim.ws.infra.RepresentanteFilialHibernate;

public class RepresentanteFilialController {
private RepresentanteFilialHibernate representanteFilialHibernate;
	
	public RepresentanteFilialController() {
		representanteFilialHibernate = DAOFactory.criaRepresentanteFilialRepository();
	}
		
	public List<RepresentanteFilialIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa) throws Exception{
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return  representanteFilialHibernate.buscarPorDataAlteracao(alteracao, idEmpresa);
	}
	
	public void save(RepresentanteFilialIntegration representanteFilialIntegration){
		representanteFilialHibernate.save(representanteFilialIntegration);
	}
}