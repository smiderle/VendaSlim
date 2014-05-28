package br.com.vendaslim.controler;

import java.util.List;

import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.repository.IRepresentanteRepository;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.domain.representante.RepresentanteFilial;
import br.com.vendaslim.util.DAOFactory;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;

public class RepresentanteControler {

	private IRepresentanteRepository representDAO;
	
	public RepresentanteControler() {
		representDAO = DAOFactory.criaRepresentanteDAO();
	}
	
	public List<Representante> lista(Empresa empresa){
		return this.representDAO.lista(empresa);
	}
	
	public void salvar(Representante representante){
		this.representDAO.save(representante);
	}
	
	public void atualizar(Representante representante){
		this.representDAO.deleteRepresentanteParcelamento(representante);
		this.representDAO.deleteRepresentanteTabPreco(representante);
		this.representDAO.deleteRepresentanteFilial(representante);
		this.representDAO.update(representante);
	}
	
	public void excluir(Representante representante){
		this.representDAO.deleteRepresentanteParcelamento(representante);
		this.representDAO.deleteRepresentanteTabPreco(representante);
		this.representDAO.deleteRepresentanteFilial(representante);
		this.representDAO.deleteRepresentante(representante);
	}
	
	//Somente permite excluir o representante caso ainda não tenha sido gerado os dados para ele.
	public boolean exclusaoPermitida(){		
		return false;
	}
	
	//Valida se o Login ou id já existem para a empresa.
	public boolean idDisponivel(Representante representante){		
		return this.representDAO.buscaPorIdOuLogin(representante) == null;
	}
	
	
	public Representante buscaPorNome(String nome, Filial filial){
		if(filial == null)
			filial = ContextoUtil.getContextBean().getFilialLogado();
		return this.representDAO.buscaPorNome(nome, filial);
	}
	
	public Integer buscaProximoIdPorFilial(Empresa empresa){
		return this.representDAO.buscaMariorIdPorEmpresa(empresa) + 1;
	}
	
	public Representante buscaPorId(Representante representante){
		return this.representDAO.buscaPorId(representante);
	}
	
	public RepresentanteFilial carregarGruposProdutos(RepresentanteFilial representanteFilial){
		if(representanteFilial != null){
			GrupoProdutoControler grupoProdutoControler = new GrupoProdutoControler();
			representanteFilial.setGruposProdutos(
					grupoProdutoControler.buscaPorIds(representanteFilial.getGruposProdutosStr(), representanteFilial.getFilial()));
		}
		return representanteFilial;
	}
	
	public List<Representante> buscaPorFilial(Filial filial){
		return this.representDAO.buscaPorFilial(filial);
	}
}
