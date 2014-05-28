package br.com.vendaslim.ws.controller;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.infra.FilialHibernate;

public class FilialController {
private FilialHibernate filialHibernate;
	
	public FilialController() {
		filialHibernate = DAOFactory.criaFilialRepository();
	}
		
	public List<FilialIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return filialHibernate.buscarPorDataAlteracao(alteracao, idEmpresa);
	}
	
	
	public FilialIntegration geraFilial(Integer idEmpresa){
		FilialIntegration filial = new FilialIntegration();
		filial.setIdEmpresa(idEmpresa);
		filial.setIdFilial(1);
		filial.setNomeFantasia("Empresa Desmostração 1");
		filial.setNumero("10");
		filial.setBairro("CENTRO");
		filial.setCep("85502070");
		filial.setDtHrAlteracao(new GregorianCalendar());
		filial.setFax("32566532");
		filial.setFone("35224845");
		filial.setRazaoSocial("Razao Social Empresa 1");
		filial.setRua("Rua Principal");
		filial.setWebsite("www.demonstracao.com.br");
		filialHibernate.save(filial);
		return filial;
	}
}
