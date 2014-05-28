package br.com.vendaslim.web;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.hibernate.Hibernate;
import org.primefaces.context.RequestContext;

import br.com.vendaslim.controler.CidadeControler;
import br.com.vendaslim.controler.FilialControler;
import br.com.vendaslim.controler.GrupoProdutoControler;
import br.com.vendaslim.controler.ParcelamentoControler;
import br.com.vendaslim.controler.RepresentanteControler;
import br.com.vendaslim.controler.TabelaPrecoControler;
import br.com.vendaslim.controler.RepresentanteGrupoControler;
import br.com.vendaslim.domain.cadastro.Cidade;
import br.com.vendaslim.domain.cadastro.Empresa;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.pedido.Parcelamento;
import br.com.vendaslim.domain.pedido.TabelaPreco;
import br.com.vendaslim.domain.produto.GrupoProduto;
import br.com.vendaslim.domain.representante.GrupoRepresentante;
import br.com.vendaslim.domain.representante.GrupoRepresentanteParcelamento;
import br.com.vendaslim.domain.representante.GrupoRepresentanteTabPreco;
import br.com.vendaslim.domain.representante.Representante;
import br.com.vendaslim.domain.representante.RepresentanteFilial;
import br.com.vendaslim.domain.representante.RepresentanteParcelamento;
import br.com.vendaslim.domain.representante.RepresentanteTabPreco;
import br.com.vendaslim.util.MensagemUtil;
import br.com.vendaslim.web.util.ContextoUtil;
import br.com.vendaslim.web.util.Pages;

@ManagedBean(name="repreBean")
@ViewScoped
public class RepresentanteBean {

	private boolean edicao;
	private boolean edicaoFilial;
	private boolean objetoEdicaoCarregado;
	
	//Considerar dados do grupo como tabelas de preço, parcelamentos, etc;
	private boolean considerarGrupo;
	//Variavel utilizada para definir o status do campo Código
	private boolean informarCodigo;
	
	private Representante representante = new Representante();
	private RepresentanteFilial representanteFilial = new RepresentanteFilial();
	
	private List<Representante> representantes;
	//Todas as filiais
	private List<Filial> filiais = null;
	//Todas as tabelas de preço
	private List<TabelaPreco> tabPrecos = null;
	//Todos os parcelamentos
	private List<Parcelamento> parcelamentos = null;
	//Filial selecionado no combo
	private Filial filial = new Filial();

	//Tabelas de preço selecionadas para o grupo
	private List<TabelaPreco> tabelasSelecionadas = new ArrayList<TabelaPreco>();
	//Parcelamentos selecionados para o grupo
	private List<Parcelamento> parcelamentosSelecionados = new ArrayList<Parcelamento>();
	//Todos os grupos da empresa selecionada
	private List<GrupoRepresentante> gruposRepresentante = null;
	
	private GrupoRepresentante grupoRepresentanteSelecionado;
	
	//private List<RepresentanteFilial> representanteFiliais = new ArrayList<RepresentanteFilial>();
	
	private boolean abilitaConsiderarGrupo;
	
	private String filtroCidade;
		
	private Cidade cidadeSelecionada = null;	
	private Integer idCidade;
	private RepresentanteFilial representanteFilialSelecionado = new RepresentanteFilial();
	
	
	public List<Filial> listaFilial(){
		if(filiais == null){
			FilialControler controler = new FilialControler();
			Empresa empresa = ContextoUtil.getContextBean().getEmpresaLogado();
			this.filiais = controler.list(empresa);
			for(Filial filial_ : filiais){
				if(filial != null && filial_.getIdFilial() == filial.getIdFilial()){
					this.filial = filial_;
				}
			}
		}
		return this.filiais;
	}
	
	
	public List<TabelaPreco> listaTabelaPreco(){
		if(tabPrecos == null && filial != null && filial.getIdFilial() != null){
			TabelaPrecoControler controler = new TabelaPrecoControler();
			this.tabPrecos = controler.lista(filial);
		}
		return this.tabPrecos;
	}
	
	public List<Parcelamento> listaParcelamento(){
		if(parcelamentos == null && filial != null && filial.getIdFilial() != null){
			ParcelamentoControler controler = new ParcelamentoControler();
			this.parcelamentos = controler.lista(filial);
		}
		return this.parcelamentos;
	}
	
	public List<GrupoRepresentante> listaGrupo(){
		if(this.filial != null && this.filial.getIdFilial() != null){
			RepresentanteGrupoControler controler = new RepresentanteGrupoControler();
			this.gruposRepresentante = controler.list(filial);
		}
		return this.gruposRepresentante;
	}
	
	public List<Representante> listaRepresentantes(){
		return listaRepresentantes(ContextoUtil.getContextBean().getEmpresaLogado());
	}
	
	public List<Representante> listaRepresentantes(Empresa empresa){
		if(this.representantes == null){
			RepresentanteControler controler = new RepresentanteControler();
			this.representantes = controler.lista(empresa);
		}
		return this.representantes;
	}
	
	//Atribui os atributos do grupo para os atributos do representante para ser exibido.
	//Se o representante pertencer a algum grupo, os dadaos do grupo são setados no seus atributos para carregar os campos
	private void atribuiDadosDoGrupo(RepresentanteFilial representanteFilial){
		if(representanteFilial.getGrupoRepresentante() != null 
						&& representanteFilial.getGrupoRepresentante().getIdGrupo() != null){
			
			GrupoRepresentante grupo = representanteFilial.getGrupoRepresentante();
			
			Set<RepresentanteTabPreco> lsRepTabPreco = new HashSet<RepresentanteTabPreco>();			
			for(GrupoRepresentanteTabPreco grupRepTabPreco : grupo.getTabelasPreco()){
				RepresentanteTabPreco repTabPreco = new RepresentanteTabPreco();
				repTabPreco.setTabelaPreco(grupRepTabPreco.getTabelaPreco());
				lsRepTabPreco.add(repTabPreco);
			}
			
			
			Set<RepresentanteParcelamento> lsRepParcela = new HashSet<RepresentanteParcelamento>();			
			for(GrupoRepresentanteParcelamento grupRepParcela : grupo.getParcelamentos()){
				RepresentanteParcelamento repParcela = new RepresentanteParcelamento();
				repParcela.setParcelamento(grupRepParcela.getParcelamento());
				lsRepParcela.add(repParcela);
			}
			
			
			representanteFilial.setComicaoVenda(grupo.getComicaoVenda());
			representanteFilial.setDescMax(grupo.getDescMax());
			representanteFilial.setMinVenda(grupo.getMinVenda());
			representanteFilial.setVisualizaTodosClientes(grupo.getVisualizaTodosClientes());
			representanteFilial.setParcelamentos(lsRepParcela);
			representanteFilial.setTabelasPreco(lsRepTabPreco);
			
			
		}
	}
		
	public void initForm(){
		if(edicao && !objetoEdicaoCarregado){
			representante.setEmpresa(ContextoUtil.getContextBean().getEmpresaLogado());
			RepresentanteControler controler = new RepresentanteControler();
			this.representante = controler.buscaPorId(representante);
			setCidadeSelecionada(representante.getCidade());
			
			for(RepresentanteFilial representanteFilial : representante.getRepresentanteFilial()){
				//Se o representante pertencer ao grupo, os dadaos do grupo são setados no seus atributos para carregar os campos
				//atribuiDadosDoGrupo(representanteFilial);
				System.out.println("");
			}
			
			//representanteFiliais = new ArrayList<RepresentanteFilial>(representante.getRepresentanteFilial());
			
						
			
			objetoEdicaoCarregado = true;
		}
	}
	
	public void buscaCidadePorCodigo(){
		if(idCidade != null && idCidade != 0){
			CidadeControler cidadeControler = new CidadeControler();
			Cidade cidade = cidadeControler.buscaPorCodigo(idCidade);
			if(cidade != null){
				this.representante.setCidade(cidade);
			} else {
				this.representante.setCidade(new Cidade());
			}			
		}
	}

	public void handleFilialChange(){			
		this.tabPrecos = null;
		this.parcelamentos = null;
		tabelasSelecionadas = new ArrayList<TabelaPreco>();
		parcelamentosSelecionados = new ArrayList<Parcelamento>();
		gruposRepresentante = new ArrayList<GrupoRepresentante>();
		considerarGrupo = false;
		representanteFilial = new RepresentanteFilial();
		abilitaConsiderarGrupo = false;
		representanteFilialSelecionado.setFilial(this.filial);
	}
	
	public void handleConsiderarGrupoChange(){
		this.representanteFilial.setDescMax(grupoRepresentanteSelecionado.getDescMax());
		this.representanteFilial.setMinVenda(grupoRepresentanteSelecionado.getMinVenda());
		this.representanteFilial.setComicaoVenda(grupoRepresentanteSelecionado.getComicaoVenda());
		
		this.tabelasSelecionadas = new ArrayList<TabelaPreco>();
		this.parcelamentosSelecionados = new ArrayList<Parcelamento>();
		
		//Atribui as tabelas de preço e parcelamentos do grupo para o representante.		
		for(GrupoRepresentanteTabPreco grupoRepTabpreco : grupoRepresentanteSelecionado.getTabelasPreco()){
			this.tabelasSelecionadas.add(grupoRepTabpreco.getTabelaPreco());
		}	
		
		for(GrupoRepresentanteParcelamento grupoRepParcelamento : grupoRepresentanteSelecionado.getParcelamentos()){
			this.parcelamentosSelecionados.add(grupoRepParcelamento.getParcelamento());
		}
		representanteFilial.setFilial(this.filial);
		representanteFilial.setGruposProdutosStr(this.grupoRepresentanteSelecionado.getGruposProdutosStr());
		carregaGrupoProdutos(representanteFilial);
		
		
		
	}	


	public void handleGrupoChange(){
		considerarGrupo = false;
		representanteFilial = new RepresentanteFilial();
		tabelasSelecionadas = new ArrayList<TabelaPreco>();
		parcelamentosSelecionados = new ArrayList<Parcelamento>();
		//AO DESVINCULAR O GRUPO E SALVAR, ESTA CARREGANDO OS PRODUTOS DO GRUPO AINDA
		representanteFilialSelecionado.setGruposProdutos(new ArrayList<GrupoProduto>());
		representanteFilialSelecionado.setGruposProdutosStr("");
		if(grupoRepresentanteSelecionado == null){
			abilitaConsiderarGrupo = false;
		} else {
			abilitaConsiderarGrupo = true;
		}
	}	
	
	public void limpaOverlayPanelEmpresa(){
		representanteFilial = new RepresentanteFilial();
	}
	
	//Verifica se a empresa já foi adicionada para o representante
	public boolean filialAdicionada(){
		for(RepresentanteFilial representanteFilial : representante.getRepresentanteFilial()){
			if(representanteFilial.getFilial().getIdFilial().equals(this.filial.getIdFilial())){
				return true; 
			}
		}
		return false;
	}
	public void cancelaVinculaFilial(){
		this.edicaoFilial = false;
		
		
		this.representanteFilial = representanteFilialSelecionado = new RepresentanteFilial();
		this.filial = new Filial();
		this.grupoRepresentanteSelecionado = null;
		this.considerarGrupo = false;
		this.abilitaConsiderarGrupo = false;
		
	}
	
	/*
	 * Remove o representanteFilial da lista
	 */
	public void removeRepresentanteLista(RepresentanteFilial representanteFilial){
		int index = -1;
		boolean remover = false;
		for(RepresentanteFilial representanteFilialLista : representante.getRepresentanteFilial()){
			index++;
			if(representanteFilialLista.getFilial().equals(representanteFilial.getFilial())){
				remover = true;
				break;
			}
		}
		if(remover)
			representante.getRepresentanteFilial().remove(index);
	}
	
	public void vinculaFilial(){
		if(edicaoFilial){
			removeRepresentanteLista(representanteFilialSelecionado);
		}
		
		
		if(!filialAdicionada()){
			representanteFilial.setFilial(this.filial);
			representanteFilial.setGrupoRepresentante(grupoRepresentanteSelecionado);
			representanteFilial.setGruposProdutos(representanteFilialSelecionado.getGruposProdutos());
			representanteFilial.convertGruposProdutos();
			Set<RepresentanteParcelamento> lsRepreseParcelamento = new LinkedHashSet<RepresentanteParcelamento>();
			for (Parcelamento parcelamento : parcelamentosSelecionados) {
				RepresentanteParcelamento representanteParcelamento = new RepresentanteParcelamento();
				representanteParcelamento.setFilial(this.filial);
				representanteParcelamento.setParcelamento(parcelamento);
				representanteParcelamento.setRepresentanteFilial(representanteFilial);
				lsRepreseParcelamento.add(representanteParcelamento);
			}
			
			representanteFilial.setParcelamentos(lsRepreseParcelamento);
			
			Set<RepresentanteTabPreco> lsRepresentanteTabPreco = new LinkedHashSet<RepresentanteTabPreco>();
			for (TabelaPreco tabelaPreco : tabelasSelecionadas) {
				RepresentanteTabPreco representanteTabelaPreco = new RepresentanteTabPreco();
				representanteTabelaPreco.setFilial(filial);
				representanteTabelaPreco.setTabelaPreco(tabelaPreco);
				representanteTabelaPreco.setRepresentanteFilial(representanteFilial);
				lsRepresentanteTabPreco.add(representanteTabelaPreco);
			}
			representanteFilial.setTabelasPreco(lsRepresentanteTabPreco);		
			
			this.representante.getRepresentanteFilial().add(representanteFilial);
			//this.representanteFiliais.add(representanteFilial);
			
			representanteFilial = new RepresentanteFilial();
			parcelamentosSelecionados = new ArrayList<Parcelamento>();
			tabelasSelecionadas = new ArrayList<TabelaPreco>();
			representanteFilial.convertGruposProdutos();
		} else {
			FacesContext.getCurrentInstance().addMessage(null ,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Empresa já foi adicionada para este usuário.", "Não é permitido vincular duas vezes a mesma empresa para o mesmo usuário."));
			
		}
		this.edicaoFilial = false;
	}
	
	public void desvinculaFilial(){
		RepresentanteFilial representantenRemovido = null;
		for(RepresentanteFilial representanteFilial : representante.getRepresentanteFilial()){			
			if(representanteFilial.getFilial().equals(representanteFilialSelecionado.getFilial())){
				representantenRemovido = representanteFilial;
				break;
			}			
		}
		representante.getRepresentanteFilial().remove(representantenRemovido);
		//representanteFiliais.remove(representantenRemovido);
	}
	
	public void carregaGrupoProdutos(RepresentanteFilial representanteFilial){
		RepresentanteControler controler = new RepresentanteControler();
		controler.carregarGruposProdutos(representanteFilial);		
	}

	public String salvar(){
		//representante.setFilial(this.filial);
		RepresentanteControler controler = new RepresentanteControler();
		Integer idRepresentante = null;
		/*if(representante.getCidade() != null && representante.getCidade().getIdCidade() == null){
			representante.setCidade(null);
		}*/
				
		if(!informarCodigo && !edicao){
			idRepresentante = controler.buscaProximoIdPorFilial(ContextoUtil.getContextBean().getEmpresaLogado());
			representante.setIdRepresentante(idRepresentante);
		} else {
			idRepresentante = representante.getIdRepresentante();
		}
		
		for(RepresentanteFilial representanteFilial : representante.getRepresentanteFilial()){
			representanteFilial.setIdRepresentante(idRepresentante);
			representanteFilial.setDtHrAlteracao(new GregorianCalendar());
			//representanteFilial.setGrupoRepresentante(grupoRepresentanteSelecionado);
			
			//Só vincula as tabelas e parcelamentos ao represenante caso não possua um grupo. Caso contrario, ficarão vinculados ao grupo do representante.
			if(representanteFilial.getGrupoRepresentante() == null || representanteFilial.getGrupoRepresentante().getIdGrupo() == null){
				//Adiciona o idRepresentante
				for(RepresentanteParcelamento repParcela : representanteFilial.getParcelamentos()){
					repParcela.setIdRepresentante(idRepresentante);
					repParcela.setDtHrAlteracao(new GregorianCalendar());
				}
				//Adiciona o idRepresentante
				for(RepresentanteTabPreco repTabPreco: representanteFilial.getTabelasPreco()){
					repTabPreco.setIdRepresentante(idRepresentante);
					repTabPreco.setDtHrAlteracao(new GregorianCalendar());
				}
				
				
			//Se o representante tiver vinculado ao grupo, todos os atributos são stados como null, pois deve ser considerado os dados do grupo
			} else {
				representanteFilial.setParcelamentos(null);
				representanteFilial.setTabelasPreco(null);
				representanteFilial.setComicaoVenda(null);
				representanteFilial.setDescMax(null);
				representanteFilial.setMinVenda(null);
				representanteFilial.setGruposProdutosStr(null);
				representanteFilial.setVisualizaTodosClientes(false);
			}
			
		}
		
		
		//representante.setRepresentanteFilial(new HashSet<RepresentanteFilial>(representanteFiliais));
		
		if(edicao){
			controler.atualizar(representante);
		} else {
			representante.setEmpresa(ContextoUtil.getContextBean().getEmpresaLogado());
			if(controler.idDisponivel(representante)){				
				controler.salvar(representante);
			} else {
				String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO);
				String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_CADASTRO_NEGADO_REPRESENTANTE,representante.getIdRepresentante(), representante.getLogin());
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary,detail);
				context.addMessage(null, message);
				return null;
			}
		}
		
		return Pages.LISTA_REPERSENTANTE+"?faces-redirect=true";
	}
	
	public String voltar(){
		return Pages.LISTA_REPERSENTANTE+"?faces-redirect=true";
	}
	
	public String novo(){
		return Pages.CADASTRO_REPERSENTANTE+"?faces-redirect=true";
	}
	
	public String editar(){
		return Pages.CADASTRO_REPERSENTANTE+"?faces-redirect=true";
	}
	
	public void editarRepresentFilial(){
		this.edicaoFilial = true;
		carregaGrupoProdutos(representanteFilialSelecionado);
		this.representanteFilial = representanteFilialSelecionado;
		this.filial = representanteFilialSelecionado.getFilial();
		this.grupoRepresentanteSelecionado = representanteFilialSelecionado.getGrupoRepresentante();
		this.considerarGrupo = this.grupoRepresentanteSelecionado != null;
		this.abilitaConsiderarGrupo = this.grupoRepresentanteSelecionado != null;

		if(representanteFilialSelecionado.getGrupoRepresentante() != null){
			representanteFilialSelecionado.setDescMax(this.grupoRepresentanteSelecionado.getDescMax());
			representanteFilialSelecionado.setComicaoVenda(this.grupoRepresentanteSelecionado.getComicaoVenda());
			representanteFilialSelecionado.setMinVenda(this.grupoRepresentanteSelecionado.getMinVenda());
			representanteFilialSelecionado.setGruposProdutosStr(this.grupoRepresentanteSelecionado.getGruposProdutosStr());
			carregaGrupoProdutos(representanteFilialSelecionado);
			
		}		
	}
	
	public void excluir(){
		RepresentanteControler controler = new RepresentanteControler();
		if(controler.exclusaoPermitida()){
			
		} else {
			String summary = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA_REPRESENTANTE, representante.getNome());
			String detail = MensagemUtil.getMensagem(MensagemUtil.ERRO_EXCLUSAO_NEGADA);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, 
					summary));
			
		}
	}
	
	//Chamado quando uma linhja é selecionada no datatable
	/*public void representanteRowSelect(){
		System.out.println("");
	}*/

	public void setGrupoRepresentanteSelecionado(			
		GrupoRepresentante grupoRepresentanteSelecionado) {
		//TODO Ajuste Foi feito isso pois esses dois atributos, por serem LAZY, 
		//ocorre erro ao tentar recuperalos do objeto grupoRepresentanteSelecionado depois que fecha a session. 
		//Alterar caso encontre uma solução 
		if(grupoRepresentanteSelecionado != null && grupoRepresentanteSelecionado.getTabelasPreco() != null)
			grupoRepresentanteSelecionado.getTabelasPreco().size();
		if(grupoRepresentanteSelecionado != null && grupoRepresentanteSelecionado.getParcelamentos() != null)
			grupoRepresentanteSelecionado.getParcelamentos().size();
		this.grupoRepresentanteSelecionado = grupoRepresentanteSelecionado;
	}

	
	/*UI*/
	public String getLabelTipoPessoa(){
		if(representante.getTipoPessoa() == null || representante.getTipoPessoa() == 1){
			return "CPF";
		} else {
			return "CNPJ";
		}
	}
	
	public String getLabelInscricao(){
		if(representante.getTipoPessoa() == null || representante.getTipoPessoa() == 1){
			return "RG";
		} else {
			return "Insc. Est.";
		}
	}
	
	public String getMascaraTipoPessoa(){
		if(representante.getTipoPessoa() == null || representante.getTipoPessoa() == 1){
			return "999.999.999-99";
		} else {
			return "99.999.999/9999-99";
		}
	}
	
	
	//Getters and Setters

	public boolean isConsiderarGrupo() {
		return considerarGrupo;
	}


	public void setConsiderarGrupo(boolean considerarGrupo) {
		this.considerarGrupo = considerarGrupo;
	}


	public List<GrupoRepresentante> getGruposRepresentante() {
		return gruposRepresentante;
	}


	public void setGruposRepresentante(List<GrupoRepresentante> gruposRepresentante) {
		this.gruposRepresentante = gruposRepresentante;
	}


	public boolean isAbilitaConsiderarGrupo() {
		return abilitaConsiderarGrupo;
	}


	public void setAbilitaConsiderarGrupo(boolean abilitaConsiderarGrupo) {
		this.abilitaConsiderarGrupo = abilitaConsiderarGrupo;
	}
	
	

	public String getFiltroCidade() {
		return filtroCidade;
	}


	public void setFiltroCidade(String filtroCidade) {
		this.filtroCidade = filtroCidade;
	}	

/*	public List<RepresentanteFilial> getRepresentanteFiliais() {
		return representanteFiliais;
	}


	public void setRepresentanteFiliais(
			List<RepresentanteFilial> representanteFiliais) {
		this.representanteFiliais = representanteFiliais;
	}*/


	public RepresentanteFilial getRepresentanteFilial() {
		return representanteFilial;
	}


	public void setRepresentanteFilial(RepresentanteFilial representanteFilial) {
		this.representanteFilial = representanteFilial;
	}


	/*public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}


	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}*/
	
	public boolean isEdicao() {
		return edicao;
	}


	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}


	public Representante getRepresentante() {
		return representante;
	}


	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}


	public List<Representante> getRepresentantes() {
		return representantes;
	}


	public void setRepresentantes(List<Representante> representantes) {
		this.representantes = representantes;
	}


	public List<Filial> getFiliais() {
		return filiais;
	}


	public void setFiliais(List<Filial> filiais) {
		this.filiais = filiais;
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
	
	public boolean isInformarCodigo() {
		return informarCodigo;
	}

	public void setInformarCodigo(boolean informarCodigo) {
		this.informarCodigo = informarCodigo;
	}
	
	public GrupoRepresentante getGrupoRepresentanteSelecionado() {
		return grupoRepresentanteSelecionado;
	}


	public RepresentanteFilial getRepresentanteFilialSelecionado() {
		return representanteFilialSelecionado;
	}


	public void setRepresentanteFilialSelecionado(
			RepresentanteFilial representanteFilialSelecionado) {
		this.representanteFilialSelecionado = representanteFilialSelecionado;
	}
	
	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}


	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		if(cidadeSelecionada != null){
			this.idCidade = cidadeSelecionada.getIdCidade();
			this.cidadeSelecionada = cidadeSelecionada;
			representante.setCidade(getCidadeSelecionada());
		}
	}


	public Integer getIdCidade() {
		return idCidade;
	}


	public void setIdCidade(Integer idCidade) {
		this.idCidade = idCidade;
	}


	public boolean isEdicaoFilial() {
		return edicaoFilial;
	}


	public void setEdicaoFilial(boolean edicaoFilial) {
		this.edicaoFilial = edicaoFilial;
	}
}
