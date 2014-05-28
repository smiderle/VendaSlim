package br.com.vendaslim.infra.representante;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.repository.IRepresentanteTabelaPreco;
import br.com.vendaslim.domain.representante.GrupoRepresentanteTabPreco;
import br.com.vendaslim.domain.representante.RepresentanteTabPreco;
import br.com.vendaslim.infra.RepositoryHibernate;

public class RepresentaneTabPrecoHibernate extends RepositoryHibernate implements IRepresentanteTabelaPreco{

	@Override
	public boolean representanteVinculadotabela(Filial filial, Integer idTabela) {
		String sql = "SELECT * FROM REPTABPRECO " +								
				"WHERE IDEMPRESA = :idEmpresa " +
				"AND IDFILIAL = :idFilial " +
				"AND IDTABELA = :idTabPreco ";				
				
		
		@SuppressWarnings("unchecked")
		List<RepresentanteTabPreco> ls =  this.session.createSQLQuery(sql).addEntity(RepresentanteTabPreco.class)
					.setParameter("idEmpresa", filial.getEmpresa().getIdEmpresa())
					.setParameter("idFilial", filial.getIdFilial())
					.setParameter("idTabPreco", idTabela)
					.setMaxResults(1)
					.list();
		
		
		return ls != null && ls.size() > 0;
		
	}

	@Override
	public boolean grupoRepresentanteVinculadotabela(Filial filial,
			Integer idTabela) {
		String sql = "SELECT * FROM GRUPREPTABPRECO " +								
				"WHERE IDEMPRESA = :idEmpresa " +
				"AND IDFILIAL = :idFilial " +
				"AND IDTABELA = :idTabPreco ";				
				
		
		@SuppressWarnings("unchecked")
		List<RepresentanteTabPreco> ls =  this.session.createSQLQuery(sql).addEntity(GrupoRepresentanteTabPreco.class)
					.setParameter("idEmpresa", filial.getEmpresa().getIdEmpresa())
					.setParameter("idFilial", filial.getIdFilial())
					.setParameter("idTabPreco", idTabela)
					.setMaxResults(1)
					.list();
		
		
		return ls != null && ls.size() > 0;
	}

}
