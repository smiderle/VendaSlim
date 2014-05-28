package br.com.vendaslim.web;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import br.com.vendaslim.controler.GrupoProdutoControler;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean(name="gruproProdBean")
@RequestScoped
public class GrupoProdutoBean {
	private List<GrupoProduto> gruposProdutos;
	private List<GrupoProduto> gruposSelecionados;
	private Filial filialCarregado;
	
	/**
	 * Verifica se os ultimos grupos de produtos que foram carregados são da empresa passada por parametro.
	 * Isso para não ficar descendo toda vez no banco para buscar os grupos. 
	 */
	
	private boolean carregarGrupos(Filial filial){
		if((this.gruposProdutos == null && filial != null && filial.getIdFilial() != null)){
			return true;
		} else if(filial != null && filial.getIdFilial() != null && !filial.equals(filialCarregado)){
			return true;
		} else {
			return false;
		}
	}
	
	public List<GrupoProduto> listaTodos(Filial filial){
		GrupoProdutoControler controler = new GrupoProdutoControler();
		//if(this.gruposProdutos == null && filial != null && filial.getIdFilial() != null){
		if(carregarGrupos(filial)){
			this.filialCarregado = filial;
			this.gruposProdutos = controler.buscaTodosPorFilial(filial);
		}
		return this.gruposProdutos;
	}
	
	public List<GrupoProduto> getGruposProdutos() {
		return gruposProdutos;
	}

	public void setGruposProdutos(List<GrupoProduto> gruposProdutos) {
		this.gruposProdutos = gruposProdutos;
	}

	public List<GrupoProduto> getGruposSelecionados() {
		return gruposSelecionados;
	}

	public void setGruposSelecionados(List<GrupoProduto> gruposSelecionados) {
		this.gruposSelecionados = gruposSelecionados;
	}	
	
	
}
