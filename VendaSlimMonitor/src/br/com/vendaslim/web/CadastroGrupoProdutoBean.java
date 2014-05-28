package br.com.vendaslim.web;

import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.controler.GrupoProdutoControler;
import br.com.vendaslim.controler.ProdutoControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean(name="cadastroGrupoProdBean")
@ViewScoped
public class CadastroGrupoProdutoBean {
	private List<GrupoProduto> gruposProdutos;
	private GrupoProduto grupoProduto = new GrupoProduto();
	private boolean edicao;
	private List<Filial> filiais;
	private boolean informarCodigo;
	private boolean objetoEdicaoCarregado;
	private String filtro = null;
	
	
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			GrupoProdutoControler controler = new GrupoProdutoControler();
		    grupoProduto.setFilial(ContextoUtil.getContextBean().getFilial());
		    grupoProduto = controler.buscaPorId(grupoProduto);
		    objetoEdicaoCarregado = true;
		}
	}
	
	/*public List<Filial> listaFilial(){
		if(this.filiais == null){
			Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
			FilialControler controler = new FilialControler();
			this.filiais = controler.list(empresa);
		}
		return this.filiais;
	}*/
	
	
	public List<GrupoProduto> listaTodosPorFilial(){
		if(this.gruposProdutos == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			GrupoProdutoControler controler = new GrupoProdutoControler();
			this.gruposProdutos = controler.buscaTodosPorFilial(filial);
		}
		return this.gruposProdutos;
	}
	
	public String novo(){
		return Pages.CADASTRO_PRODUTO_GRUPO+"?faces-redirect=true";
	}
	
	public void excluir(){
		GrupoProdutoControler controller = new GrupoProdutoControler();
		if(controller.exclusaoPermitida(grupoProduto)){
			controller.delete(grupoProduto);
		}else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_GRUPO_PRODUTO,grupoProduto.getIdGrupo());
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
			context.addMessage(null, message);
		}
	}
	
	public String salvar(){
		GrupoProdutoControler controler = new GrupoProdutoControler();
		if(!edicao){			
			Filial filial  = ContextoUtil.getContextBean().getFilial();
			this.grupoProduto.setFilial(filial);
			if(!informarCodigo){
				Integer idGrupo = controler.buscaProximoIdPorFilial(filial);
				this.grupoProduto.setIdGrupo(idGrupo);
			}
		}
		
		
		this.grupoProduto.setDtHrAlteracao(new GregorianCalendar());
		
		if(edicao){
			controler.atualizar(grupoProduto);
		} else {
			if(controler.buscaPorId(grupoProduto) ==null){
				controler.salvar(grupoProduto);
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_CODIGO_EXISTENTE,grupoProduto.getIdGrupo());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		
		return Pages.LISTA_PRODUTO_GRUPO;
	}
	
	public void pesquisarGrupos(){
		if(this.filtro != null && !this.filtro.trim().equals("")){
			GrupoProdutoControler controller = new GrupoProdutoControler();			
			gruposProdutos =  controller.buscaPorFiltro(filtro);
		}	
	}
	
	public void removerFiltro(){
		this.gruposProdutos = null;
		this.filtro = null;		
		listaTodosPorFilial();
	}

	public String voltar(){
		return Pages.LISTA_PRODUTO_GRUPO+"?faces-redirect=true";
	}
	
	public List<GrupoProduto> getGruposProdutos() {
		return gruposProdutos;
	}

	public void setGruposProdutos(List<GrupoProduto> gruposProdutos) {
		this.gruposProdutos = gruposProdutos;
	}

	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}

	public void setGrupoProduto(GrupoProduto grupoProduto) {
		this.grupoProduto = grupoProduto;
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

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
	
}

