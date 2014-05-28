package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.FilialPK;

public interface IFilialRepository extends Repository{
	public List<Filial> list(Empresa empresa);	
	public Filial findById(FilialPK filialId);
	public Filial buscaPorNome(String nomeFantasia, Empresa empresa);
}
