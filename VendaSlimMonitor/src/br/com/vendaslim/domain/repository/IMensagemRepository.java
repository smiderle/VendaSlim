package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Mensagem;
import br.com.vendaslim.domain.representante.Representante;

public interface IMensagemRepository  extends Repository{
	public List<Mensagem> listaTodas(Empresa empresa);
	public List<Mensagem> listaTodasPorRepresentante(Empresa empresa, Representante representante);
	public Integer buscaMariorId(Empresa empresa);

}
