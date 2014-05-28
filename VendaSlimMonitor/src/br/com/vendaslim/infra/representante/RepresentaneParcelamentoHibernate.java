package br.com.vendaslim.infra.representante;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.repository.IRepresentanteParcelamento;
import br.com.vendaslim.domain.repository.IRepresentanteTabelaPreco;
import br.com.vendaslim.domain.representante.GrupoRepresentanteParcelamento;
import br.com.vendaslim.domain.representante.RepresentanteParcelamento;
import br.com.vendaslim.infra.RepositoryHibernate;

public class RepresentaneParcelamentoHibernate extends RepositoryHibernate implements IRepresentanteParcelamento{

	@Override
	public boolean representanteVinculadoParcela(Filial filial, Integer idParcela) {
		String sql = "SELECT * FROM REPPARCELA " +								
				"WHERE IDEMPRESA = :idEmpresa " +
				"AND IDFILIAL = :idFilial " +
				"AND IDPARCELAMENTO = :idParcela ";				
				
		
		@SuppressWarnings("unchecked")
		List<RepresentanteParcelamento> ls =  this.session.createSQLQuery(sql).addEntity(RepresentanteParcelamento.class)
					.setParameter("idEmpresa", filial.getEmpresa().getIdEmpresa())
					.setParameter("idFilial", filial.getIdFilial())
					.setParameter("idParcela", idParcela)
					.setMaxResults(1)
					.list();
		
		return ls != null && ls.size() > 0;
	}

	@Override
	public boolean grupoRepresentanteVinculadoParcela(Filial filial,
			Integer idParcela) {
		String sql = "SELECT * FROM GRUPREPPARCELA " +								
				"WHERE IDEMPRESA = :idEmpresa " +
				"AND IDFILIAL = :idFilial " +
				"AND IDPARCELAMENTO = :idParcela ";				
		
		@SuppressWarnings("unchecked")
		List<GrupoRepresentanteParcelamento> ls =  this.session.createSQLQuery(sql).addEntity(GrupoRepresentanteParcelamento.class)
					.setParameter("idEmpresa", filial.getEmpresa().getIdEmpresa())
					.setParameter("idFilial", filial.getIdFilial())
					.setParameter("idParcela", idParcela)
					.setMaxResults(1)
					.list();
		
		return ls != null && ls.size() > 0;
	}

}
