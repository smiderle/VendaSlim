package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.GrupoRepresentanteTabPrecoIntegration;
import br.com.vendaslim.ws.infra.GrupoRepresentanteTabPrecoHibernate;

public class GrupoRepresentanteTabPrecoController {
private GrupoRepresentanteTabPrecoHibernate grupoRepresentanteTabPrecoHibernate;
	
	public GrupoRepresentanteTabPrecoController() {
		grupoRepresentanteTabPrecoHibernate = DAOFactory.criaGrupoRepresentanteTabPrecoRepository();
	}
		
	public List<GrupoRepresentanteTabPrecoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return grupoRepresentanteTabPrecoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
}