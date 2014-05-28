package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.GrupoRepresentanteIntegration;
import br.com.vendaslim.ws.infra.GrupoRepresentanteHibernate;

public class GrupoRepresentanteController {
private GrupoRepresentanteHibernate grupoRepresentanteHibernate;
	
	public GrupoRepresentanteController() {
		grupoRepresentanteHibernate = DAOFactory.criaGrupoRepresentanteRepository();
	}
		
	public List<GrupoRepresentanteIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return grupoRepresentanteHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
}