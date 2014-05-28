package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.MensagemIntegration;
import br.com.vendaslim.ws.infra.MensagemHibernate;

public class MensagemController {
private MensagemHibernate mensagemHibernate ;
	
	public MensagemController() {
		mensagemHibernate = DAOFactory.criaMensagemRepository();
	}
		
	public List<MensagemIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idRepresentante){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return mensagemHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idRepresentante);
	}
}
