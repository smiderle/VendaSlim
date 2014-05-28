package br.com.vendaslim.ws.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.vendaslim.util.Converter;
import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.FilialIntegration;
import br.com.vendaslim.ws.domain.GrupoProdutoIntegration;
import br.com.vendaslim.ws.infra.GrupoProdutoHibernate;

public class GrupoProdutoController {
private GrupoProdutoHibernate grupoProdutoHibernate;
	
	public GrupoProdutoController() {
		grupoProdutoHibernate = DAOFactory.criaGrupoProdutoRepository();
	}
		
	public List<GrupoProdutoIntegration> buscarPorDataAlteracao(long dtHrAlteracao, Integer idEmpresa, Integer idFilial){
		Calendar alteracao = Converter.longToCalendar(dtHrAlteracao);
		return grupoProdutoHibernate.buscarPorDataAlteracao(alteracao, idEmpresa, idFilial);
	}
	
	
	public void geraGrupoProduto(FilialIntegration filial){
		List<GrupoProdutoIntegration> lsGrupo = new ArrayList<>();
		
		
		GrupoProdutoIntegration grupo0 = new GrupoProdutoIntegration();
		grupo0.setIdGrupo(0);
		grupo0.setIdEmpresa(filial.getIdEmpresa());
		grupo0.setIdFilial(filial.getIdFilial());		
		grupo0.setDescMax(10.0);
		grupo0.setDescricao("SEM GRUPO");
		grupo0.setDtHrAlteracao(new GregorianCalendar());
		
		
		
		GrupoProdutoIntegration grupo1 = new GrupoProdutoIntegration();
		grupo1.setIdGrupo(1);
		grupo1.setIdEmpresa(filial.getIdEmpresa());
		grupo1.setIdFilial(filial.getIdFilial());		
		grupo1.setDescMax(10.0);
		grupo1.setDescricao("BEBIDAS");
		grupo1.setDtHrAlteracao(new GregorianCalendar());
		
		GrupoProdutoIntegration grupo2 = new GrupoProdutoIntegration();
		grupo2.setIdGrupo(2);
		grupo2.setIdEmpresa(filial.getIdEmpresa());
		grupo2.setIdFilial(filial.getIdFilial());		
		grupo2.setDescMax(10.0);
		grupo2.setDescricao("HIGIENE E PERFUMARIA");
		grupo2.setDtHrAlteracao(new GregorianCalendar());
		
		
		GrupoProdutoIntegration grupo3 = new GrupoProdutoIntegration();
		grupo3.setIdGrupo(3);
		grupo3.setIdEmpresa(filial.getIdEmpresa());
		grupo3.setIdFilial(filial.getIdFilial());		
		grupo3.setDescMax(10.0);
		grupo3.setDescricao("LIMPEZA");
		grupo3.setDtHrAlteracao(new GregorianCalendar());
		
		GrupoProdutoIntegration grupo4 = new GrupoProdutoIntegration();
		grupo4.setIdGrupo(4);
		grupo4.setIdEmpresa(filial.getIdEmpresa());
		grupo4.setIdFilial(filial.getIdFilial());		
		grupo4.setDescMax(10.0);
		grupo4.setDescricao("CONGELADOS");
		grupo4.setDtHrAlteracao(new GregorianCalendar());
		
		/*GrupoProdutoIntegration grupo5 = new GrupoProdutoIntegration();
		grupo5.setIdGrupo(5);
		grupo5.setIdEmpresa(filial.getIdEmpresa());
		grupo5.setIdFilial(filial.getIdFilial());		
		grupo5.setDescMax(10.0);
		grupo5.setDescricao("FRIOS E LATICÍNIOS");
		grupo5.setDtHrAlteracao(new GregorianCalendar());
		
		GrupoProdutoIntegration grupo6 = new GrupoProdutoIntegration();
		grupo6.setIdGrupo(6);
		grupo6.setIdEmpresa(filial.getIdEmpresa());
		grupo6.setIdFilial(filial.getIdFilial());		
		grupo6.setDescMax(10.0);
		grupo6.setDescricao("HORTIFRUTI E OVOS");
		grupo6.setDtHrAlteracao(new GregorianCalendar());
		
		GrupoProdutoIntegration grupo7 = new GrupoProdutoIntegration();
		grupo7.setIdGrupo(7);
		grupo7.setIdEmpresa(filial.getIdEmpresa());
		grupo7.setIdFilial(filial.getIdFilial());		
		grupo7.setDescMax(10.0);
		grupo7.setDescricao("PÃES E BOLOS");
		grupo7.setDtHrAlteracao(new GregorianCalendar());
		
		
		GrupoProdutoIntegration grupo8 = new GrupoProdutoIntegration();
		grupo8.setIdGrupo(8);
		grupo8.setIdEmpresa(filial.getIdEmpresa());
		grupo8.setIdFilial(filial.getIdFilial());		
		grupo8.setDescMax(10.0);
		grupo8.setDescricao("DIET LIGHT E INTEGRAIS");
		grupo8.setDtHrAlteracao(new GregorianCalendar());*/
		lsGrupo.add(grupo0);
		lsGrupo.add(grupo1);
		lsGrupo.add(grupo2);
		lsGrupo.add(grupo3);
		lsGrupo.add(grupo4);
		/*lsGrupo.add(grupo5);
		lsGrupo.add(grupo6);
		lsGrupo.add(grupo7);
		lsGrupo.add(grupo8);*/
		grupoProdutoHibernate.save(lsGrupo);
	}
	
	
}
