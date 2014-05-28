package br.com.vendaslim.web;

import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.vendaslim.controler.ParcelamentoControler;
import br.com.vendaslim.controler.TabelaPrecoControler;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean
@ViewScoped
public class CadastroParcelamentoBean {
	private List<Parcelamento> parcelamentos;
	private Parcelamento parcelamento = new Parcelamento();
	private boolean informarCodigo;
	private boolean edicao;
	private boolean objetoEdicaoCarregado;
	private boolean atualizarLista;
	
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			ParcelamentoControler controler = new ParcelamentoControler();
		    parcelamento.setFilial(ContextoUtil.getContextBean().getFilial());
		    parcelamento = controler.buscaPorId(parcelamento);
		    objetoEdicaoCarregado = true;
		}
	}
	
	public List<Parcelamento> listarPorFilial(){
		if(this.parcelamentos == null || atualizarLista){
			atualizarLista  = false;
			Filial filial = ContextoUtil.getContextBean().getFilial();
			ParcelamentoControler controler = new ParcelamentoControler();
			this.parcelamentos = controler.lista(filial);
		}
		return this.parcelamentos;
	}


	public List<Parcelamento> getParcelamentos() {
		return parcelamentos;
	}


	public void setParcelamentos(List<Parcelamento> parcelamentos) {
		this.parcelamentos = parcelamentos;
	}


	public Parcelamento getParcelamento() {
		return parcelamento;
	}


	public void setParcelamento(Parcelamento parcelamento) {
		this.parcelamento = parcelamento;
	}
	
	public String novo(){
		return Pages.CADASTRO_PARCELAMENTO+"?faces-redirect=true";
	}
	
	public void excluir(){		
		ParcelamentoControler controller = new ParcelamentoControler();
		
		if(controller.exclusaoPermitida(parcelamento, ContextoUtil.getContextBean().getFilial())){
			controller.remover(parcelamento);
			atualizarLista = true;
		} else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_PARCELAMENTO,parcelamento.getIdParcelamento());
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
			context.addMessage(null, message);	
			
		}
	}
	
	public String salvar(){
		ParcelamentoControler controler = new ParcelamentoControler();		
		if(!edicao){			
			Filial filial  = ContextoUtil.getContextBean().getFilial();
			this.parcelamento.setFilial(filial);
			if(!informarCodigo){
				Integer idParcelamento = controler.buscaProximoIdPorFilial(filial);
				this.parcelamento.setIdParcelamento(idParcelamento);
			}
		}
		
		this.parcelamento.setDtHrAlteracao(new GregorianCalendar());
		
		if(edicao){
			controler.atualizar(parcelamento);
		} else {
			if(controler.buscaPorId(parcelamento) == null){
				if(!controler.existeDescricaoParcelamento(parcelamento)){
					controler.salvar(parcelamento);
				} else {
					String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
					String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_DESCRICAO_EXISTENTE,parcelamento.getDescricao());
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
					context.addMessage(null, message);
					return null;
				}
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_CODIGO_EXISTENTE,parcelamento.getIdParcelamento());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		
		return Pages.LISTA_PARCELAMENTO;
	}

	
	public String voltar(){
		return Pages.LISTA_PARCELAMENTO+"?faces-redirect=true";
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
	
	
	
}
