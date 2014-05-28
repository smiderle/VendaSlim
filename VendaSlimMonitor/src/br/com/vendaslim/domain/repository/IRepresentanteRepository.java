package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.representante.Representante;

public interface IRepresentanteRepository extends Repository{
	public List<Representante> lista(Empresa empresa);
	public Representante buscaPorNome(String nome, Filial filial);
	public Integer buscaMariorIdPorEmpresa(Empresa empresa);
	public Representante buscaPorId(Representante representante);
	public Representante buscaPorIdOuLogin(Representante representante);
	public void deleteRepresentanteTabPreco(Representante representante);
	public void deleteRepresentanteParcelamento(Representante representante);
	public void deleteRepresentanteFilial(Representante representante);
	public void deleteRepresentante(Representante representante);
	public List<Representante> buscaPorFilial(Filial filial);	
}
