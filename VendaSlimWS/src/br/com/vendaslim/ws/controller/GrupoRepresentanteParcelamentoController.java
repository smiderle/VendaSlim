package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.GrupoRepresentanteParcelamentoIntegration;
import br.com.vendaslim.ws.infra.GrupoRepresentanteParcelamentoHibernate;

public class GrupoRepresentanteParcelamentoController {
private GrupoRepresentanteParcelamentoHibernate grupoRepresentanteParcelamentoHibernate;
	
	public GrupoRepresentanteParcelamentoController() {
		grupoRepresentanteParcelamentoHibernate = DAOFactory.criaGrupoRepresentanteParcelamentoRepository();
	}
		
	public List<GrupoRepresentanteParcelamentoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return grupoRepresentanteParcelamentoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
}