package br.com.vendaslim.domain.repository;

import java.util.List;

import br.com.vendaslim.domain.Repository;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;

public interface IClienteRepository extends Repository{
	public List<Cliente> lista(Filial filial);
	public Cliente buscaPorNome(String nome, Filial filial);
	public Integer buscaMariorIdPorFilial(Filial filial);
	public Cliente buscaPorId(Cliente cliente);
	public List<Cliente> listaPorFiltro(Filial filial, String filtro, Integer codigo);
	public boolean clienteUsouTabelaPreco(Filial filial, TabelaPreco tabelaPreco);
	public boolean clienteUsouParcelamento(Filial filial, Parcelamento parcelamento);
}
