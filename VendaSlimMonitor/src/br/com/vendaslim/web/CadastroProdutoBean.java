package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.GrupoProdutoDataModel;

import br.com.vendaslim.controler.ClienteControler;
import br.com.vendaslim.controler.GrupoProdutoControler;
import br.com.vendaslim.controler.ProdutoControler;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean
@ViewScoped
public class CadastroProdutoBean {
	private List<Produto> produtos;
	private Produto produto = new Produto();
	private boolean edicao;
	private List<Filial> filiais;
	private boolean informarCodigo;
	private boolean objetoEdicaoCarregado;
	private String filtroGrupoProduto;
	private GrupoProdutoDataModel grupoProdutoDatamodel = new GrupoProdutoDataModel();
	private String filtro = null;
	
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			ProdutoControler controler = new ProdutoControler();
		    produto.setFilial(ContextoUtil.getContextBean().getFilial());
		    produto = controler.buscaPorId(produto);
		    objetoEdicaoCarregado = true;
		} else if(!edicao && !objetoEdicaoCarregado){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			GrupoProdutoControler controler = new GrupoProdutoControler();
			List<GrupoProduto> gruposProdutos = controler.buscaTodosPorFilial(filial);
			grupoProdutoDatamodel = new GrupoProdutoDataModel(gruposProdutos);
			objetoEdicaoCarregado = true;
		}
	}
	
	public List<Produto> listaTodosPorFilial(){
		if(this.produtos == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			ProdutoControler controler = new ProdutoControler();
			this.produtos = controler.buscarTodosPorFilial(filial);
		}
		return this.produtos;
	}
	
	public void buscaGrupoProdutoPorId(){
		Filial filial = ContextoUtil.getContextBean().getFilial();
		GrupoProdutoControler controler = new GrupoProdutoControler();
		produto.getGrupoProduto().setFilial(filial);
		GrupoProduto grupoProduto = controler.buscaPorId(produto.getGrupoProduto());
		if(grupoProduto != null){
			this.produto.setGrupoProduto(grupoProduto);
		}
	}
	
	public String novo(){
		return Pages.CADASTRO_PRODUTO+"?faces-redirect=true";
	}
	
	public void excluir(){
		ProdutoControler controller = new ProdutoControler();
		Filial filial = ContextoUtil.getContextBean().getFilial();
		if(controller.exclusaoPermitida(filial, produto)){
			controller.delete(produto);
		} else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_PRODUTO, produto.getDescricao());
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, 
					summary));
		}
	}
	
	public String voltar(){
		return Pages.LISTA_PRODUTO+"?faces-redirect=true";
	}
	
	public String salvar(){
		ProdutoControler controler = new ProdutoControler();
		if(!edicao){
			Filial filial  = ContextoUtil.getContextBean().getFilial();
			this.produto.setFilial(filial);
			if(!informarCodigo){
				Integer idProduto = controler.buscaProximoIdPorFilial(filial);
				this.produto.setIdProduto(idProduto);
			}
		}
		
		this.produto.setDtHrAlteracao(new GregorianCalendar());
		
		
		if(produto.getGrupoProduto() == null || produto.getGrupoProduto().getIdGrupo() == null){
			//Produtos sem grupo
			this.produto.setIdGrupo(0);
		}
		
		if(edicao){
			controler.atualizar(produto);
		} else {
			if(controler.buscaPorId(produto) ==null){
				controler.salvar(produto);
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_CODIGO_EXISTENTE,produto.getIdProduto());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		return Pages.LISTA_PRODUTO;
	}
	
	public void handleFiltroGrupoChange(){
		GrupoProdutoControler controler = new GrupoProdutoControler();
		if(this.filtroGrupoProduto != null){			
			List<GrupoProduto> gruposProdutos = controler.buscaPorFiltro(filtroGrupoProduto);
			grupoProdutoDatamodel = new GrupoProdutoDataModel(gruposProdutos);
		}else {
			List<GrupoProduto> gruposProdutos = controler.buscaTodosPorFilial(ContextoUtil.getContextBean().getFilial());
			grupoProdutoDatamodel = new GrupoProdutoDataModel(gruposProdutos);
		}
	}
	public void pesquisarProduto(){
		if(this.filtro != null && !this.filtro.trim().equals("")){
			ProdutoControler controller =new ProdutoControler();
			produtos =  controller.buscaPorFiltro(ContextoUtil.getContextBean().getFilial(), this.filtro);
		}	
	}
	
	public void removerFiltro(){
		this.produtos = null;
		this.filtro = null;		
		listaTodosPorFilial();
	}
	
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	public boolean isEdicao() {
		return edicao;
	}
	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}
	public List<Filial> getFiliais() {
		return filiais;
	}
	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
	}
	public boolean isInformarCodigo() {
		return informarCodigo;
	}
	public void setInformarCodigo(boolean informarCodigo) {
		this.informarCodigo = informarCodigo;
	}
	public boolean isObjetoEdicaoCarregado() {
		return objetoEdicaoCarregado;
	}
	public void setObjetoEdicaoCarregado(boolean objetoEdicaoCarregado) {
		this.objetoEdicaoCarregado = objetoEdicaoCarregado;
	}

	public String getFiltroGrupoProduto() {
		return filtroGrupoProduto;
	}

	public void setFiltroGrupoProduto(String filtroGrupoProduto) {
		this.filtroGrupoProduto = filtroGrupoProduto;
	}

	public GrupoProdutoDataModel getGrupoProdutoDatamodel() {
		return grupoProdutoDatamodel;
	}

	public void setGrupoProdutoDatamodel(GrupoProdutoDataModel grupoProdutoDatamodel) {
		this.grupoProdutoDatamodel = grupoProdutoDatamodel;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
	
	
}
