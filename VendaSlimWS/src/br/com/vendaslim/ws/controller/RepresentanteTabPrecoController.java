package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.RepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.infra.RepresentanteTabPrecoHibernate;

public class RepresentanteTabPrecoController {
private RepresentanteTabPrecoHibernate representanteTabPrecoHibernate;
	
	public RepresentanteTabPrecoController() {
		representanteTabPrecoHibernate = DAOFactory.criaRepresentanteTabPrecoRepository();
	}
		
	public List<RepresentanteTabPrecoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilia, Integer idRepresentante) throws Exception{
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return  representanteTabPrecoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilia, idRepresentante);
	}
	
	public void save(List<RepresentanteTabPrecoIntegration> lsRepresentanteTabPrecoIntegration){
		representanteTabPrecoHibernate.save(lsRepresentanteTabPrecoIntegration);
	}
}