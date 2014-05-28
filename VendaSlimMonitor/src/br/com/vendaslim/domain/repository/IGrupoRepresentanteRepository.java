package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.representante.GrupoRepresentante;
import br.com.vendaslim.domain.representante.GrupoRepresentanteTabPreco;

public interface IGrupoRepresentanteRepository extends Repository{
	public List<GrupoRepresentante> list(Empresa empresa);
	public List<GrupoRepresentante> list(Filial filial);
	public GrupoRepresentante buscaPorID(Filial filial, Integer idGrupo);
	public void deleteGrupoRepresentanteTabPreco(GrupoRepresentante grupoRepresentante);
	public void deleteGrupoRepresentanteParcelamento(GrupoRepresentante grupoRepresentante);	
	public Integer buscaMaiorIdPorFilial(Filial filial);
}
