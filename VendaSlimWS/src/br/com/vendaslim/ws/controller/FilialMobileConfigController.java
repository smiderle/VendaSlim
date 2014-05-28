package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialMobileConfigIntegration;
import br.com.vendaslim.ws.infra.FilialMobileConfigHibernate;

public class FilialMobileConfigController {
private FilialMobileConfigHibernate filialMobileConfigHibernate;
	
	public FilialMobileConfigController() {
		filialMobileConfigHibernate = DAOFactory.criaFilialMobileCOnfigRepository();
	}
		
	public List<FilialMobileConfigIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return filialMobileConfigHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
}
