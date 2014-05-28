package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.MensagemDataModel;

import br.com.vendaslim.controler.MensagemControler;
import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.Mensagem;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean
@ViewScoped
public class MensagemBean {
	
	public MensagemBean() {
		listarMensagens();
	}
	
	private List<Mensagem> mensagens;
	private Mensagem mensagemSelecionada = new Mensagem();
	private boolean mensagensCarregadas = false;
	private Representante representanteSelecionado;
	private MensagemDataModel mensagemDataModel = null;
	private List<Representante> representantes = null;
	private List<Representante> representantesSelecionados = null;
	
	public List<Mensagem> listarMensagens(){
		if(!mensagensCarregadas){
			mensagensCarregadas = true;
			MensagemControler controler = new MensagemControler();
			this.mensagens = controler.listaTodos();
		}
		return this.mensagens;
	}
	
	public List<Representante> listaRepresentantes(){
		if(this.representantes == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			RepresentanteControler controler = new RepresentanteControler();
			this.representantes = controler.buscaPorFilial(filial);
		}
		return this.representantes;
	}
	
	public void salvar(){		
		if(representantesSelecionados!= null && representantesSelecionados.size() > 0){
			List<Mensagem> mensagens = new ArrayList<Mensagem>();
			MensagemControler controler = new MensagemControler();		
			Usuario usuario = ContextoUtil.getContextBean().getUsuarioLogado();
			Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
			
			Integer idMensagem = controler.buscaProximoId(empresa);
			for (Representante representante : representantesSelecionados) {
				Mensagem mensagem = new Mensagem();
				mensagem.setTitulo(mensagemSelecionada.getTitulo());
				mensagem.setMensagem(mensagemSelecionada.getMensagem());
				mensagem.setUsuarioOrigem(usuario);
				mensagem.setIdMensagem(idMensagem);
				mensagem.setEmpresa(empresa);
				mensagem.setRepresentantenDestino(representante);
				mensagem.setDtHrCadastro(new Date());
				mensagens.add(mensagem);
				idMensagem ++;
			}			
			
			controler.salvar(mensagens);
			listarMensagens();
			mensagemDataModel();
		}
	}
	
	public void novaMensagem(){
		this.mensagemSelecionada = new Mensagem();
	}
	
	public MensagemDataModel mensagemDataModel(){
		return mensagemDataModel = new MensagemDataModel(this.mensagens);
	}

	public List<Mensagem> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<Mensagem> mensagens) {
		this.mensagens = mensagens;
	}

	public Mensagem getMensagemSelecionada() {
		return mensagemSelecionada;
	}

	public void setMensagemSelecionada(Mensagem mensagemSelecionada) {
		if(mensagemSelecionada != null)
			this.mensagemSelecionada = mensagemSelecionada;
	}

	public boolean isMensagensCarregadas() {
		return mensagensCarregadas;
	}

	public void setMensagensCarregadas(boolean mensagensCarregadas) {
		this.mensagensCarregadas = mensagensCarregadas;
	}

	public Representante getRepresentanteSelecionado() {
		return representanteSelecionado;
	}

	public void setRepresentanteSelecionado(Representante representanteSelecionado) {
		mensagensCarregadas = false;
		this.representanteSelecionado = representanteSelecionado;
	}

	public MensagemDataModel getMensagemDataModel() {
		return mensagemDataModel;
	}

	public void setMensagemDataModel(MensagemDataModel mensagemDataModel) {
		this.mensagemDataModel = mensagemDataModel;
	}

	public List<Representante> getRepresentantes() {
		return representantes;
	}

	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}

	public List<Representante> getRepresentantesSelecionados() {
		return representantesSelecionados;
	}

	public void setRepresentantesSelecionados(
			List<Representante> representantesSelecionados) {
		this.representantesSelecionados = representantesSelecionados;
	}
	
	

}
