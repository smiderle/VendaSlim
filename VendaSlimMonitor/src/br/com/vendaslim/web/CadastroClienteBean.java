package br.com.vendaslim.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.ClienteDataModel;

import br.com.vendaslim.controler.CidadeControler;
import br.com.vendaslim.controler.ClienteControler;
import br.com.vendaslim.controler.ParcelamentoControler;
import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.controler.TabelaPrecoControler;
import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean(name="cadastroClienteBean")
@ViewScoped
public class CadastroClienteBean {
	
	private boolean edicao;
	private boolean edicaoFilial;
	private boolean objetoEdicaoCarregado;
	private boolean informarCodigo;
	
	
	private Cliente cliente = new Cliente();
	private List<Cliente> clientes = null;
	
	private Cidade cidadeSelecionada = null;	
	private Integer idCidade;
	
	private List<TabelaPreco> tabPrecos;
	private List<Parcelamento> parcelamentos = null;
	private List<Representante> representantes = null;
	private String filtro = null;
	private ClienteDataModel clienteDataModel = new ClienteDataModel();
	//Variavel criada para controlar a atualização do DataModel. Pois é feita varias chamadas ao metodo em uma só requisição.
	private boolean atualizarClienteDataModel = true;
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			//cliente.setEmpresa(ContextoUtil.getContextBean().getEmpresaLogado());
			cliente.setFilial(ContextoUtil.getContextBean().getFilial());
			ClienteControler controler = new ClienteControler();
			this.cliente = controler.buscaPorId(cliente);
			setCidadeSelecionada(cliente.getCidade());
			objetoEdicaoCarregado = true;
			/*ClienteControler controler2 = new ClienteControler();
			controler2.geraClientes();*/
		}
	}
	
	
	public List<Representante> listaRepresentantes(){
		if(this.representantes == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			RepresentanteControler controler = new RepresentanteControler();
			this.representantes = controler.buscaPorFilial(filial);
		}
		return this.representantes;
	}
	
	public ClienteDataModel clientesDataModel(){
		if(atualizarClienteDataModel){
			this.clienteDataModel = new ClienteDataModel(listaClientes());
			atualizarClienteDataModel = false;
		}
		return clienteDataModel;
	}
	
	
	
	
	public List<Cliente> listaClientes(){
		if(this.clientes == null){
			ClienteControler controler= new ClienteControler();
			this.clientes = controler.listar();
		}
		return this.clientes;
	}
	
	public void removerFiltro(){
		this.clientes = null;
		this.filtro = null;
		atualizarClienteDataModel = true;
		listaClientes();
	}
	
	public String novo(){
		return Pages.CADASTRO_CLIENTE+"?faces-redirect=true";
	}
	
	public String voltar(){
		return Pages.LISTA_CLIENTE+"?faces-redirect=true";
	}
	
	public String salvar(){
		
		ClienteControler controler = new ClienteControler();
		Integer idCliente = null;
		
		if(cliente.getCpfCnpj() != null)
				cliente.setCpfCnpj(cliente.getCpfCnpj().replace(".","").replace("/", "").replace(".", "").replace("-", ""));
		if(cliente.getIncricao() != null)
			cliente.setIncricao(cliente.getIncricao().replace(".","").replace("/", "").replace(".", "").replace("-", ""));
		
		if(!informarCodigo && !edicao){
			idCliente = controler.buscaProximoIdPorFilial(ContextoUtil.getContextBean().getFilial());
			cliente.setIdCliente(idCliente);			
		} else {
			idCliente = cliente.getIdCliente();
		}		
		
		if(edicao){
			controler.atualizar(cliente);
		} else {
			cliente.setFilial(ContextoUtil.getContextBean().getFilial());
			if(controler.idDisponivel(cliente)){
				controler.salvar(cliente);
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_CLIENTE,cliente.getIdCliente());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		return Pages.LISTA_CLIENTE+"?faces-redirect=true";
	}
	
	public void excluir(){
		ClienteControler controller = new ClienteControler();
		if(controller.exclusaoPermitida(cliente)){
			controller.delete(cliente);
			this.clientes = null;
		} else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_CLIENTE, cliente.getNome());
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, 
					summary));
			
		}
	}
	
	public void buscaCidadePorCodigo(){
		if(idCidade != null && idCidade != 0){
			CidadeControler cidadeControler = new CidadeControler();
			Cidade cidade = cidadeControler.buscaPorCodigo(idCidade);
			if(cidade != null){
				this.cliente.setCidade(cidade);
			} else {
				this.cliente.setCidade(new Cidade());
			}			
		}
	}
	
	public List<TabelaPreco> listaTabelaPreco(){
		if(tabPrecos == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			TabelaPrecoControler controler = new TabelaPrecoControler();
			this.tabPrecos = controler.lista(filial);
		}
		return this.tabPrecos;
	}
	
	
	public List<Parcelamento> listaParcelamento(){
		if(parcelamentos == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			ParcelamentoControler controler = new ParcelamentoControler();
			this.parcelamentos = controler.lista(filial);
		}
		return this.parcelamentos;
	}
	
	public List<Cliente> pesquisarCliente(){
		if(this.filtro != null && !this.filtro.trim().equals("")){
			ClienteControler controler  = new ClienteControler();
			this.clientes = controler.buscaPorFiltro(this.filtro);
			atualizarClienteDataModel = true;
		}
		return this.clientes;
	}
	
	/*UI*/
	public String getLabelTipoPessoa(){
		if(cliente.getTipoPessoa() == null || cliente.getTipoPessoa() == 1){
			return "CPF";
		} else {
			return "CNPJ";
		}
	}
	
	public String getLabelInscricao(){
		if(cliente.getTipoPessoa() == null || cliente.getTipoPessoa() == 1){
			return "RG";
		} else {
			return "Insc. Est.";
		}
	}
	
	public String getMascaraTipoPessoa(){
		if(cliente.getTipoPessoa() == null || cliente.getTipoPessoa() == 1){
			return "999.999.999-99";
		} else {
			return "99.999.999/9999-99";
		}
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public boolean isEdicaoFilial() {
		return edicaoFilial;
	}

	public void setEdicaoFilial(boolean edicaoFilial) {
		this.edicaoFilial = edicaoFilial;
	}

	public boolean isObjetoEdicaoCarregado() {
		return objetoEdicaoCarregado;
	}

	public void setObjetoEdicaoCarregado(boolean objetoEdicaoCarregado) {
		this.objetoEdicaoCarregado = objetoEdicaoCarregado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}


	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}


	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		if(cidadeSelecionada != null){
			this.idCidade = cidadeSelecionada.getIdCidade();
			this.cidadeSelecionada = cidadeSelecionada;
			cliente.setCidade(getCidadeSelecionada());
		}		
	}


	public boolean isInformarCodigo() {
		return informarCodigo;
	}


	public void setInformarCodigo(boolean informarCodigo) {
		this.informarCodigo = informarCodigo;
	}


	public Integer getIdCidade() {
		return idCidade;
	}


	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}


	public List<TabelaPreco> getTabPrecos() {
		return tabPrecos;
	}


	public void setTabPrecos(List<TabelaPreco> tabPrecos) {
		this.tabPrecos = tabPrecos;
	}


	public List<Parcelamento> getParcelamentos() {
		return parcelamentos;
	}


	public void setParcelamentos(List<Parcelamento> parcelamentos) {
		this.parcelamentos = parcelamentos;
	}


	public String getFiltro() {
		return filtro;
	}


	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	
	
}
