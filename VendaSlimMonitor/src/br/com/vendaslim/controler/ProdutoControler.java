package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.repository.IProdutoRepository;
import br.com.vendaslim.util.DAOFactory;

public class ProdutoControler {
	
	private IProdutoRepository repository;
	public ProdutoControler() {
		this.repository = DAOFactory.criaProdutoRepository();
	}
	
	public List<Produto> buscarTodosPorFilial(Filial filial){
		return repository.buscarTodosPorFilial(filial);
	}
			
	public Produto buscaPorId(Produto produto){
		return this.repository.buscaPorId(produto);
	}
	

	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.repository.buscaMaiorIdPorFilial(filial) + 1;
	}
	
	public void salvar(Produto produto){
		this.repository.save(produto);
	}
	
	public void atualizar(Produto produto){
		this.repository.update(produto);
	}
	
	public void delete(Produto produto){
		this.repository.delete(produto);
	}
	
	public  List<Produto> buscaPorGrupo(GrupoProduto grupoProduto){
		return this.repository.buscaPorGrupo(grupoProduto);
	}
	
	public boolean exclusaoPermitida(Filial filial, Produto produto){
		PedidoControler controller = new PedidoControler();
		return !controller.pedidoUsouProduto(filial, produto);
	}
	
	public List<Produto> buscaPorFiltro(Filial filial, String filtro){
		Integer codigo = 0;
		try {
			codigo = Integer.parseInt(filtro);
		} catch (NumberFormatException e) {}
		
		return this.repository.buscaPorFiltro(filial, filtro, codigo);
	}

}
