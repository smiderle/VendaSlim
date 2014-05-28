package br.com.vendaslim.ws.controller;

import java.util.GregorianCalendar;

import br.com.vendaslim.ws.conexao.DAOFactory;
import br.com.vendaslim.ws.domain.Sincronizacao;
import br.com.vendaslim.ws.infra.SincronizacaoHibernate;

public class SincronizacaoController {
private SincronizacaoHibernate sincronizacaoHibernate ;
	
	public SincronizacaoController() {
		sincronizacaoHibernate = DAOFactory.criaSincronizacaoRepository();
	}
		
	public Sincronizacao buscarUltimaSincronizacao(int idRepresentante, Integer idEmpresa, Integer idFilial, String tabela) throws Exception{
		Sincronizacao sincronizacao = sincronizacaoHibernate.buscarUltimaSincronizacao(idRepresentante, idEmpresa, idFilial);
		
		if(sincronizacao == null)
			throw new Exception("Sincronização não cadastrada para este representante Empresa: " + idEmpresa +" Filial: " +idFilial + "Representante: "+idRepresentante);

		
		
		return sincronizacao;
	}
	
	public void save(Sincronizacao sincronizacao){
		sincronizacaoHibernate.save(sincronizacao);
	}
	
	
	public Sincronizacao geraNova(int idRepresentante, Integer idEmpresa, Integer idFilial, String tabela){
		Sincronizacao sincronizacao = new Sincronizacao();
		sincronizacao.setIdEmpresa(idEmpresa);
		sincronizacao.setIdFilial(idFilial);
		sincronizacao.setIdRepresentante(idRepresentante);
		sincronizacao.setDtHrSincronizacao(new GregorianCalendar());
		sincronizacao.setSincCompleta(false);
		sincronizacao.setTabela(tabela);
		
		save(sincronizacao);
		
		return sincronizacao;
	}
}
