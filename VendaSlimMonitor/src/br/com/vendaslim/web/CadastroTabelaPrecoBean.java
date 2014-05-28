package br.com.vendaslim.web;

import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.vendaslim.controler.TabelaPrecoControler;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean
@ViewScoped
public class CadastroTabelaPrecoBean {
	private List<TabelaPreco> tabelaPrecos;
	private TabelaPreco tabelaPreco = new TabelaPreco();
	private boolean informarCodigo;
	private boolean edicao;
	private boolean objetoEdicaoCarregado;
	private boolean atualizarLista;
	
	
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			TabelaPrecoControler controler = new TabelaPrecoControler();
			tabelaPreco.setFilial(ContextoUtil.getContextBean().getFilial());
			tabelaPreco = controler.buscaPorId(tabelaPreco);
		    objetoEdicaoCarregado = true;
		}
	}
	
	public List<TabelaPreco> listarPorFilial(){
		if(this.tabelaPrecos == null || atualizarLista){
			atualizarLista = false;
			Filial filial = ContextoUtil.getContextBean().getFilial();
			TabelaPrecoControler controler = new TabelaPrecoControler();
			this.tabelaPrecos = controler.lista(filial);
		}
		return this.tabelaPrecos;
	}
	
	public String salvar(){
		TabelaPrecoControler controler = new TabelaPrecoControler();		
		if(!edicao){			
			Filial filial  = ContextoUtil.getContextBean().getFilial();
			this.tabelaPreco.setFilial(filial);
			if(!informarCodigo){
				Integer idTabelaPreco = controler.buscaProximoIdPorFilial(filial);
				this.tabelaPreco.setIdTabelaPreco(idTabelaPreco);
			}
		}
		
		this.tabelaPreco.setDtHrAlteracao(new GregorianCalendar());
		
		if(edicao){
			controler.atualizar(tabelaPreco);
		} else {
			if(controler.buscaPorId(tabelaPreco) == null){
				if(!controler.existeDescricao(tabelaPreco)){
					controler.salvar(tabelaPreco);
				} else {
					String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
					String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_DESCRICAO_EXISTENTE,tabelaPreco.getDescricao());
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
					context.addMessage(null, message);
					return null;
				}
				
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_CODIGO_EXISTENTE,tabelaPreco.getIdTabelaPreco());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		return Pages.LISTA_TABELA_PRECO;
	}

	public String voltar(){
		return Pages.LISTA_TABELA_PRECO+"?faces-redirect=true";
	}
	
	public String novo(){
		return Pages.CADASTRO_TABELAPRECO+"?faces-redirect=true";
	}
	
	public String excluir(){
		TabelaPrecoControler controller = new TabelaPrecoControler();	
		
		if(controller.exclusaoPermitida(tabelaPreco, ContextoUtil.getContextBean().getFilial())){
			controller.remover(tabelaPreco);
			atualizarLista = true;
		} else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_TABELA_PRECO,tabelaPreco.getIdTabelaPreco());
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
			context.addMessage(null, message);	
			
		}
				return null;
	}
	
	public List<TabelaPreco> getTabelaPrecos() {
		return tabelaPrecos;
	}

	public void setTabelaPrecos(List<TabelaPreco> tabelaPrecos) {
		this.tabelaPrecos = tabelaPrecos;
	}

	public TabelaPreco getTabelaPreco() {
		return tabelaPreco;
	}

	public void setTabelaPreco(TabelaPreco tabelaPreco) {
		this.tabelaPreco = tabelaPreco;
	}

	public boolean isInformarCodigo() {
		return informarCodigo;
	}

	public void setInformarCodigo(boolean informarCodigo) {
		this.informarCodigo = informarCodigo;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public boolean isObjetoEdicaoCarregado() {
		return objetoEdicaoCarregado;
	}

	public void setObjetoEdicaoCarregado(boolean objetoEdicaoCarregado) {
		this.objetoEdicaoCarregado = objetoEdicaoCarregado;
	}
	
	
}
