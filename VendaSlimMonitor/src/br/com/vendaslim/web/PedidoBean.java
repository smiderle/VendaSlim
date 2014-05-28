package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.Hibernate;
import org.primefaces.model.PedidoDataModel;

import br.com.vendaslim.controler.ClienteControler;
import br.com.vendaslim.controler.PedidoControler;
import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cliente.Cliente;
import br.com.vendaslim.domain.pedido.Pedido;
import br.com.vendaslim.domain.pedidoItem.PedidoItem;
import br.com.vendaslim.domain.produto.Produto;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.web.util.ContextoUtil;

@ManagedBean
@ViewScoped
public class PedidoBean {

	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private Pedido pedidoSelecionado = null;
	private PedidoDataModel pedidoDataModel = new PedidoDataModel();
	List<Representante> representantes ;
	private List<PedidoItem> lsPedidoItem = new ArrayList<PedidoItem>();
	//Variavale para controlar quando é trocado o pedido selecionado
	private boolean pedidoItemCarregado = false;
	
	public PedidoBean() {
		listaUltimosPedidos();
	}	
	

	public String getLabelDescontoAcrescimo(){
		if(pedidoSelecionado != null && pedidoSelecionado.getDescontoTotal() != null && pedidoSelecionado.getDescontoTotal() < 0)
			return "Acréscimo";
		else 
			return "Desconto";
	}
	
	public void listaUltimosPedidos(){
		PedidoControler controler = new PedidoControler();
		Filial filial = ContextoUtil.getContextBean().getFilial();		
		this.pedidos = controler.buscaUltimosPedidos(filial);
		this.pedidoDataModel = new PedidoDataModel(this.pedidos);
	}
	
	public List<PedidoItem> itensPedidos(){
		if(!pedidoItemCarregado){
			pedidoItemCarregado = true;
			if(pedidoSelecionado != null){
				PedidoControler controler = new PedidoControler();
				lsPedidoItem = controler.loadPedidoItem(pedidoSelecionado);
				if(lsPedidoItem != null){
					int size = lsPedidoItem.size();
					
					if(size < 11){
						for(int i = size+1; i < 11 ; i++){
							Produto produto = new Produto();					
							PedidoItem pedidoItem = new PedidoItem();
							pedidoItem.setProduto(produto);
							pedidoItem.setSequencia(i);
							lsPedidoItem.add(pedidoItem);
						}
					}				
				}
			}
		}
		return lsPedidoItem;
		
	}

	public List<Representante> listaRepresentantes(){
		if(this.representantes == null){
			Filial filial = ContextoUtil.getContextBean().getFilial();
			RepresentanteControler controler = new RepresentanteControler();
			this.representantes = controler.buscaPorFilial(filial);
		}
		return this.representantes;
	}
	

	
	public void buscaClientePorCodigo(){
		if(filtro.cliente != null ){
			filtro.cliente.setFilial(ContextoUtil.getContextBean().getFilial());
			ClienteControler controler = new ClienteControler();
			filtro.cliente = controler.buscaPorId(filtro.cliente);
			if(filtro.cliente == null)
				filtro.cliente = new Cliente();
		}
	}
	
	public void filtrarPedidos(){
		PedidoControler controler = new PedidoControler();
		this.pedidos = controler.buscaPorFiltro(filtro.getIdPedido(), filtro.getRepresentanteSelecionado(), filtro.getCliente(), filtro.getDtInicio(), 
				filtro.getDtFim(), null);
		
		this.pedidoDataModel = new PedidoDataModel(this.pedidos);
	}
	
	public void limparFiltro(){
		this.filtro = new Filtro();
		listaUltimosPedidos();
		
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Pedido getPedidoSelecionado() {
		return pedidoSelecionado;
	}

	public void setPedidoSelecionado(Pedido pedidoSelecionado) {
		pedidoItemCarregado = false;
		this.pedidoSelecionado = pedidoSelecionado;
	}

	public PedidoDataModel getPedidoDataModel() {
		return pedidoDataModel;
	}

	public void setPedidoDataModel(PedidoDataModel pedidoDataModel) {
		this.pedidoDataModel = pedidoDataModel;
	}

	public Filtro filtro = new Filtro();
	
	
	
	public Filtro getFiltro() {
		return filtro;
	}

	public void setFiltro(Filtro filtro) {
		this.filtro = filtro;
	}



	public class Filtro{
		private Integer idCliente;
		private Integer idRepresentante;
		private Integer idPedido;
		private Date dtInicio;
		private Date dtFim;
		private Representante representanteSelecionado;
		private Cliente cliente = new Cliente(); 
		
		
		public Integer getIdCliente() {
			return idCliente;
		}
		public void setIdCliente(Integer idCliente) {
			this.idCliente = idCliente;
		}
		public Integer getIdRepresentante() {
			return idRepresentante;
		}
		public void setIdRepresentante(Integer idRepresentante) {
			this.idRepresentante = idRepresentante;
		}
		public Integer getIdPedido() {
			return idPedido;
		}
		public void setIdPedido(Integer idPedido) {
			this.idPedido = idPedido;
		}
		public Date getDtInicio() {
			return dtInicio;
		}
		public void setDtInicio(Date dtInicio) {
			this.dtInicio = dtInicio;
		}
		public Date getDtFim() {
			return dtFim;
		}
		public void setDtFim(Date dtFim) {
			this.dtFim = dtFim;
		}
		
		
		public Representante getRepresentanteSelecionado() {
			return representanteSelecionado;
		}
		public void setRepresentanteSelecionado(Representante representanteSelecionado) {
			this.representanteSelecionado = representanteSelecionado;
		}
		public Cliente getCliente() {
			return cliente;
		}
		public void setCliente(Cliente cliente) {
			if(cliente != null)
				//cliente = new Cliente();
			this.cliente = cliente;
		}
		

		
	}
	
}
