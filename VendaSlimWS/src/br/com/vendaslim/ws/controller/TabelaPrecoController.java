package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.TabelaPrecoIntegration;
import br.com.vendaslim.ws.infra.TabelaPrecoHibernate;

public class TabelaPrecoController {
private TabelaPrecoHibernate tabelaPrecoHibernate;
	
	public TabelaPrecoController() {
		tabelaPrecoHibernate = DAOFactory.criaTabelaPrecoRepository();
	}
		
	public List<TabelaPrecoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return tabelaPrecoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
	
	public void geraTabelaPreco(FilialIntegration filial){
		List<TabelaPrecoIntegration> lsTabPreco = new ArrayList<>();
		
		TabelaPrecoIntegration tabelaPreco = new TabelaPrecoIntegration();
		tabelaPreco.setIdTabPreco(1);
		tabelaPreco.setIdEmpresa(filial.getIdEmpresa());
		tabelaPreco.setIdFilial(filial.getIdFilial());		
		tabelaPreco.setAcrescimo(false);
		tabelaPreco.setDescricao("TABELA PADRÃO");
		tabelaPreco.setDtHrAlteracao(new GregorianCalendar());
		tabelaPreco.setInativo(false);
		tabelaPreco.setPercentual(0.0);
		
		TabelaPrecoIntegration tabelaPreco1 = new TabelaPrecoIntegration();
		tabelaPreco1.setIdTabPreco(2);
		tabelaPreco1.setIdEmpresa(filial.getIdEmpresa());
		tabelaPreco1.setIdFilial(filial.getIdFilial());		
		tabelaPreco1.setAcrescimo(false);
		tabelaPreco1.setDescricao("TABELA A VISTA");
		tabelaPreco1.setDtHrAlteracao(new GregorianCalendar());
		tabelaPreco1.setInativo(false);
		tabelaPreco1.setPercentual(10.0);
		
		TabelaPrecoIntegration tabelaPreco2 = new TabelaPrecoIntegration();
		tabelaPreco2.setIdTabPreco(3);
		tabelaPreco2.setIdEmpresa(filial.getIdEmpresa());
		tabelaPreco2.setIdFilial(filial.getIdFilial());		
		tabelaPreco2.setAcrescimo(true);
		tabelaPreco2.setDescricao("TABELA PREÇO 2");
		tabelaPreco2.setDtHrAlteracao(new GregorianCalendar());
		tabelaPreco2.setInativo(false);
		tabelaPreco2.setPercentual(20.0);
		
		lsTabPreco.add(tabelaPreco);
		lsTabPreco.add(tabelaPreco1);
		lsTabPreco.add(tabelaPreco2);
		tabelaPrecoHibernate.save(lsTabPreco);
	}
}