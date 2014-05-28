package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.controler.GrupoProdutoControler;
import br.com.vendaslim.controler.ParcelamentoControler;
import br.com.vendaslim.controler.TabelaPrecoControler;
import br.com.vendaslim.controler.RepresentanteGrupoControler;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.cadastro.Usuario;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.representante.GrupoRepresentante;
import br.com.vendaslim.domain.representante.GrupoRepresentanteParcelamento;
import br.com.vendaslim.domain.representante.GrupoRepresentanteTabPreco;
import br.com.vendaslim.infra.representante.GrupoRepresentanteHibernate;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean(name="grupoRepBean")
@ViewScoped
public class GrupoRepresentanteBean {	
/*	public GrupoRepresentanteBean() {
		listaFilial();
	}*/	
	//Quando for edição, sera setado como true
	public boolean edicao;
	//Variavel criada para controlar para o initForm para que seja carregado somente uma vez.
	public boolean objetoEdicaoCarregado;
	//Variavel utilizada para definir o status do campo Código
	public boolean informarCodigo;
		
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
		//if(grupoRepresentante.getFilial() != null && grupoRepresentante.getFilial().getIdFilial() != null){
			RepresentanteGrupoControler controler = new RepresentanteGrupoControler();
			this.filial = grupoRepresentante.getFilial();
			if(this.filial.getIdFilial() != null){		
				this.grupoRepresentante = controler.buscaPorID(grupoRepresentante.getFilial(), grupoRepresentante.getIdGrupo());
				//Carrega os parcelamentos do Grupo e seta no combo para edição
				for(GrupoRepresentanteParcelamento grpRepParc : grupoRepresentante.getParcelamentos()){
					this.parcelamentosSelecionados.add(grpRepParc.getParcelamento());
				}
				//Carrega as tabelas do Grupo e seta no combo para edição 
				for(GrupoRepresentanteTabPreco grpRepTab : grupoRepresentante.getTabelasPreco()){
					this.tabelasSelecionadas.add(grpRepTab.getTabelaPreco());
				}
				controler.carregaGruposProdutos(grupoRepresentante);
			}
		}
		/*
		if(!edicao && filial != null && filial.getIdFilial() != null){
			UsuarioGrupoControler controler = new UsuarioGrupoControler();
			Integer idGrupo = controler.buscaProximoIdPorFilial(this.filial);
			this.grupoRepresentante.setIdGrupo(idGrupo);
		}*/
		
		objetoEdicaoCarregado = true;
	}	
	
	private GrupoRepresentante grupoRepresentante = new GrupoRepresentante();
	//Todos os grupos de representantes
	private List<GrupoRepresentante> lsGrupoRepresentantes = null;
	//Todas as filiais
	private List<Filial> lsFilial = null;
	//Todas as tabelas de preço
	private List<TabelaPreco> lsTabPreco = null;
	//Todos os parcelamentos
	private List<Parcelamento> lsParcelamento= null;
	//Filial selecionado no combo
	private Filial filial = null;
	
	//Tabelas de preço selecionadas para o grupo
	private List<TabelaPreco> tabelasSelecionadas = new ArrayList<TabelaPreco>();
	//Parcelamentos selecionados para o grupo
	private List<Parcelamento> parcelamentosSelecionados = new ArrayList<Parcelamento>();
	//Grupos de produtos selecionados
	//private List<GrupoProduto> gruposProdutosSelecionados;
	
	public List<GrupoRepresentante> lista(){
		if(lsGrupoRepresentantes == null){
			RepresentanteGrupoControler controler = new RepresentanteGrupoControler();
			Usuario usuarioLogado = ContextoUtil.getContextBean().getUsuarioLogado();
			this.lsGrupoRepresentantes = controler.list(usuarioLogado.getFilial().getEmpresa());
		}
		return this.lsGrupoRepresentantes;
	}
	
	public List<Filial> listaFilial(){
		if(lsFilial == null){
			FilialControler controler = new FilialControler();
			Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
			this.lsFilial = controler.list(empresa);
			for(Filial filial_ : lsFilial){
				if(filial != null &&  filial_.getIdFilial() == this.filial.getIdFilial()){
					this.filial = filial_;
				}
			}
		}
		return this.lsFilial;
	}
	
	
	public List<TabelaPreco> listaTabelaPreco(){
		if(lsTabPreco == null && filial != null && filial.getIdFilial() != null){
			TabelaPrecoControler controler = new TabelaPrecoControler();
			this.lsTabPreco = controler.lista(filial);
		}
		return this.lsTabPreco;
	}
	
	public List<Parcelamento> listaParcelamento(){
		if(lsParcelamento == null && filial != null && filial.getIdFilial() != null){
			ParcelamentoControler controler = new ParcelamentoControler();
			this.lsParcelamento = controler.lista(filial);
		}
		return this.lsParcelamento;
	}
	
	public String salvar(){
		grupoRepresentante.setFilial(filial);
		RepresentanteGrupoControler controler = new RepresentanteGrupoControler();
		
		if(!edicao && !informarCodigo){
			Integer idGrupo =controler.buscaProximoIdPorFilial(grupoRepresentante.getFilial());
			grupoRepresentante.setIdGrupo(idGrupo);
		}
		
		List<GrupoRepresentanteTabPreco> lsGrRepTab = new ArrayList<GrupoRepresentanteTabPreco>();
		for(TabelaPreco tabelaPreco : tabelasSelecionadas){
			GrupoRepresentanteTabPreco grRepTab = new GrupoRepresentanteTabPreco();
			grRepTab.setFilial(filial);
			grRepTab.setIdTabPreco(tabelaPreco.getIdTabelaPreco());
			grRepTab.setIdGrupo(grupoRepresentante.getIdGrupo());
			grRepTab.setDtHrAlteracao(new GregorianCalendar());
			lsGrRepTab.add(grRepTab);
		}
		grupoRepresentante.setTabelasPreco(lsGrRepTab);
	
		List<GrupoRepresentanteParcelamento> lsParcelamentos = new ArrayList<GrupoRepresentanteParcelamento>();
		for(Parcelamento parcelamento : parcelamentosSelecionados){
			GrupoRepresentanteParcelamento grpRepParcela = new GrupoRepresentanteParcelamento();
			grpRepParcela.setFilial(filial);
			grpRepParcela.setIdParcelamento(parcelamento.getIdParcelamento());
			grpRepParcela.setIdGrupo(grupoRepresentante.getIdGrupo());
			grpRepParcela.setDtHrAlteracao(new GregorianCalendar());
			lsParcelamentos.add(grpRepParcela);
		}
		grupoRepresentante.setParcelamentos(lsParcelamentos);
		grupoRepresentante.setDtHrAlteracao(new GregorianCalendar());
		
		
		if(edicao){
			controler.atualizar(grupoRepresentante);
		} else {
			controler.salvar(grupoRepresentante);
		}
		return Pages.LISTA_REPRESENTANTE_GRUPO+"?faces-redirect=true";
	}
	
	public void handleFilialChange(){			
		this.lsTabPreco = null;
		this.lsParcelamento = null;
		tabelasSelecionadas = new ArrayList<TabelaPreco>();
		parcelamentosSelecionados = new ArrayList<Parcelamento>();
	}
	
	public String novo(){
		return Pages.CADASTRO_REPRESENTANTE_GRUPO+"?faces-redirect=true";
	}
		
	public String editar(){
		return Pages.CADASTRO_REPRESENTANTE_GRUPO;
	}
	public void excluir(){
		if(grupoRepresentante != null && grupoRepresentante.getIdGrupo() != null){		
			RepresentanteGrupoControler controler  = new RepresentanteGrupoControler();
			controler.excluir(this.grupoRepresentante);
			this.lsGrupoRepresentantes = null;
		}		
	}
	
	public GrupoRepresentante getGrupoRepresentante() {
		return grupoRepresentante;
	}

	public void setGrupoRepresentante(GrupoRepresentante grupoRepresentante) {
		this.grupoRepresentante = grupoRepresentante;
	}

	public List<Filial> getLsFilial() {
		return lsFilial;
	}

	public void setLsFilial(List<Filial> lsFilial) {
		this.lsFilial = lsFilial;
	}

	public List<TabelaPreco> getLsTabPreco() {
		return lsTabPreco;
	}

	public void setLsTabPreco(List<TabelaPreco> lsTabPreco) {
		this.lsTabPreco = lsTabPreco;
	}

	public List<Parcelamento> getLsParcelamento() {
		return lsParcelamento;
	}

	public void setLsParcelamento(List<Parcelamento> lsParcelamento) {
		this.lsParcelamento = lsParcelamento;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public List<TabelaPreco> getTabelasSelecionadas() {
		return tabelasSelecionadas;
	}

	public void setTabelasSelecionadas(List<TabelaPreco> tabelasSelecionadas) {
		this.tabelasSelecionadas = tabelasSelecionadas;
	}

	public List<Parcelamento> getParcelamentosSelecionados() {
		return parcelamentosSelecionados;
	}

	public void setParcelamentosSelecionados(
			List<Parcelamento> parcelamentosSelecionados) {
		this.parcelamentosSelecionados = parcelamentosSelecionados;
	}

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public List<GrupoRepresentante> getLsGrupoRepresentantes() {
		return lsGrupoRepresentantes;
	}

	public void setLsGrupoRepresentantes(
			List<GrupoRepresentante> lsGrupoRepresentantes) {
		this.lsGrupoRepresentantes = lsGrupoRepresentantes;
	}

	public boolean isInformarCodigo() {
		return informarCodigo;
	}

	public void setInformarCodigo(boolean informarCodigo) {
		this.informarCodigo = informarCodigo;
	}	
}
