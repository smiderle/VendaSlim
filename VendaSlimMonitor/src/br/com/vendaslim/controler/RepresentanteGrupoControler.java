package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.repository.IGrupoRepresentanteRepository;
import br.com.vendaslim.domain.representante.GrupoRepresentante;
import br.com.vendaslim.util.DAOFactory;

public class RepresentanteGrupoControler {
	private IGrupoRepresentanteRepository grupoRepresentanteDAO;
	
	public RepresentanteGrupoControler() {
		this.grupoRepresentanteDAO = DAOFactory.criaUsuarioGrupoDAO();
	}
	
	
	public List<GrupoRepresentante> list(Empresa empresa){
		return this.grupoRepresentanteDAO.list(empresa);
	}
	
	public List<GrupoRepresentante> list(Filial filial){
		return this.grupoRepresentanteDAO.list(filial);
	}
	
	public void salvar(GrupoRepresentante grupoRepresentante){
		grupoRepresentante.convertGruposProdutos();
		if(grupoRepresentante.getIdGrupo() == null || grupoRepresentante.getIdGrupo() == 0){
			Integer idGrupo = buscaProximoIdPorFilial(grupoRepresentante.getFilial());
			grupoRepresentante.setIdGrupo(idGrupo);
		}
		this.grupoRepresentanteDAO.save(grupoRepresentante);		
	}

	public void atualizar(GrupoRepresentante grupoRepresentante){
		grupoRepresentante.convertGruposProdutos();
		this.grupoRepresentanteDAO.deleteGrupoRepresentanteParcelamento(grupoRepresentante);
		this.grupoRepresentanteDAO.deleteGrupoRepresentanteTabPreco(grupoRepresentante);
		this.grupoRepresentanteDAO.update(grupoRepresentante);
	}

	public GrupoRepresentante buscaPorID(Filial filial, Integer idGrupo){
		return this.grupoRepresentanteDAO.buscaPorID(filial, idGrupo);
	}

	public Integer buscaProximoIdPorFilial(Filial filial){
		return this.grupoRepresentanteDAO.buscaMaiorIdPorFilial(filial) +1 ;
	}
	
	public void excluir(GrupoRepresentante usuarioGrupo){
		this.grupoRepresentanteDAO.delete(usuarioGrupo);
	}
	
	public void carregaGruposProdutos(GrupoRepresentante grupoRepresentante){
		//Carrega os GruposProdutos
		GrupoProdutoControler grupoProdutoControler = new GrupoProdutoControler();
		List<GrupoProduto> lsGrupoProduto = 
				grupoProdutoControler.buscaPorIds(grupoRepresentante.getGruposProdutosStr(), grupoRepresentante.getFilial());
		grupoRepresentante.setGruposProdutos(lsGrupoProduto);
	}
}
