package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.RepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.infra.RepresentanteParcelamentoHibernate;

public class RepresentanteParcelamentoController {
private RepresentanteParcelamentoHibernate representanteParcelamentoHibernate;
	
	public RepresentanteParcelamentoController() {
		representanteParcelamentoHibernate = DAOFactory.criaRepresentanteParcelamentoRepository();
	}
		
	public List<RepresentanteParcelamentoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilia, Integer idRepresentante) throws Exception{
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return  representanteParcelamentoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilia, idRepresentante);
	}
	
	public void save(List<RepresentanteParcelamentoIntegration> lsRepresentanteParcelamentoIntegration){
		representanteParcelamentoHibernate.save(lsRepresentanteParcelamentoIntegration);
	}
}