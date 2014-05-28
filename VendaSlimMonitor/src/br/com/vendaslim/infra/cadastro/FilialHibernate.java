package br.com.vendaslim.infra.cadastro;

import java.util.List;

import org.hibernate.criterion.Restrictions;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.FilialPK;
import br.com.vendaslim.domain.repository.IFilialRepository;
import br.com.vendaslim.infra.RepositoryHibernate;

public class FilialHibernate extends RepositoryHibernate implements IFilialRepository{

	
	@SuppressWarnings("unchecked")
	@Override
	public List<Filial> list(Empresa empresa) {
		return this.session.createCriteria(Filial.class)
				.add(Restrictions.eq("empresa", empresa)).list();
	}

	
	@Override
	public Filial findById(FilialPK filialId) {
		return 	(Filial) this.session.createCriteria(Filial.class)
				.add(Restrictions.eq("filialPK", filialId)).uniqueResult();
	}
	
	@Override
	public Filial buscaPorNome(String nomeFantasia, Empresa empresa){
		List<Filial> lsFilial= this.session.createCriteria(Filial.class)
						.add(Restrictions.and(
							Restrictions.eq("empresa", empresa),
							Restrictions.eq("nomeFantasia", nomeFantasia))).list();
		
		if(lsFilial.size() > 0){
			return lsFilial.get(0);
		}
		return null;
	}

}
