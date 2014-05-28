package br.com.vendaslim.web;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.CidadeDataModel;

import br.com.vendaslim.controler.CidadeControler;
import br.com.vendaslim.domain.cadastro.Cidade;

@ManagedBean
@ViewScoped
public class CidadeBean {
	private Cidade cidade = new Cidade();
	private List<Cidade> cidades;	
	private Cidade cidadeSelecionada; 
	private CidadeDataModel cidadeDataModel = new CidadeDataModel();
	
	private String filtroCidade;
	
	public void handleFiltroCidadeChange(){
		if(filtroCidade != null && filtroCidade.trim().length() >= 3){
			CidadeControler cidadeControler = new CidadeControler();
			this.cidades = cidadeControler.buscaPorFiltro(filtroCidade);
			cidadeDataModel = new CidadeDataModel(this.cidades);
		}
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public List<Cidade> getCidades() {
		return cidades;
	}

	public void setCidades(List<Cidade> cidades) {
		this.cidades = cidades;
	}


	public String getFiltroCidade() {
		return filtroCidade;
	}


	public void setFiltroCidade(String filtroCidade) {
		this.filtroCidade = filtroCidade;
	}


	public Cidade getCidadeSelecionada() {
		return cidadeSelecionada;
	}


	public void setCidadeSelecionada(Cidade cidadeSelecionada) {
		this.cidadeSelecionada = cidadeSelecionada;
	}


	public CidadeDataModel getCidadeDataModel() {
		return cidadeDataModel;
	}


	public void setCidadeDataModel(CidadeDataModel cidadeDataModel) {
		this.cidadeDataModel = cidadeDataModel;
	}
	
	
	
}
