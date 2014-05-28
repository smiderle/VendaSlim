package br.com.vendaslim.domain.representante;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
@Entity
@Table(name="REPFILIAL")
@IdClass(RepresentanteFilialPK.class)
public class RepresentanteFilial implements Domain{
	
	public RepresentanteFilial() {
		this.representante = new Representante();
	}
	
	@Id
	private Filial filial;
	
	@Id
	private Integer idRepresentante;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumns(
			{
				@JoinColumn(name="IDEMPRESA", insertable=false, updatable=false),
				@JoinColumn(name="IDREPRESENTANTE", insertable=false, updatable=false)
			}
			)
	private Representante representante;
		
	@OneToMany(mappedBy="representanteFilial", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<RepresentanteTabPreco> tabelasPreco;
	
	@OneToMany(mappedBy="representanteFilial", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private Set<RepresentanteParcelamento> parcelamentos;
	
	@ManyToOne
	@JoinColumns({		
		@JoinColumn(name="IDEMPRESA",referencedColumnName="idempresa", insertable=false, updatable=false),
		@JoinColumn(name="IDFILIAL",referencedColumnName="idfilial", insertable=false, updatable=false),
		@JoinColumn(name="IDGRUPO", referencedColumnName="idGrupo", insertable=false, updatable=false),
		
	})
	private GrupoRepresentante grupoRepresentante;
	
	@Column
	private Integer idGrupo;
	@Column
	private Double descMax;
	@Column
	private Double comicaoVenda;
	@Column(nullable=false)
	private boolean visualizaTodosClientes;
	@Column
	private Double minVenda;
	
	@Transient
	private List<GrupoProduto> gruposProdutos = new ArrayList<GrupoProduto>();
	
	@Column(name="GRUPOPRODUTO")
	private String gruposProdutosStr;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	/**
	 * Retorna os ids das tabelas de preco separados por virgula
	 * @return
	 */
	public String getTabelasPrecoString(){
		StringBuffer tabPrecoStr = new StringBuffer();
		if(tabelasPreco != null && tabelasPreco.size() > 0){		
			for (RepresentanteTabPreco repTabPreco : tabelasPreco) {
				tabPrecoStr.append(repTabPreco.getTabelaPreco().getIdTabelaPreco());
				tabPrecoStr.append(",");
			}
			//Remove a ultima virgula
			tabPrecoStr.deleteCharAt(tabPrecoStr.length()-1);
		}
		return tabPrecoStr.toString();
	}
	
	
	/**
	 * Retorna os ids das tabelas de preco separados por virgula
	 * @return
	 */
	public String getParcelamentosString(){
		StringBuffer parcelamentoStr = new StringBuffer();
		if(parcelamentos != null && parcelamentos.size() > 0){		
			for (RepresentanteParcelamento repParcelamento : parcelamentos) {
				parcelamentoStr.append(repParcelamento.getParcelamento().getIdParcelamento());
				parcelamentoStr.append(",");
			}
			//Remove a ultima virgula
			parcelamentoStr.deleteCharAt(parcelamentoStr.length()-1);
		}
		return parcelamentoStr.toString();
	}
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}	
	public Set<RepresentanteTabPreco> getTabelasPreco() {
		return tabelasPreco;
	}
	public void setTabelasPreco(Set<RepresentanteTabPreco> tabelasPreco) {
		this.tabelasPreco = tabelasPreco;
	}
	public Set<RepresentanteParcelamento> getParcelamentos() {
		return parcelamentos;
	}
	public void setParcelamentos(Set<RepresentanteParcelamento> parcelamentos) {
		this.parcelamentos = parcelamentos;
	}
	public GrupoRepresentante getGrupoRepresentante() {
		return grupoRepresentante;
	}
	public void setGrupoRepresentante(GrupoRepresentante grupoRepresentante) {
		if(grupoRepresentante != null)
			setIdGrupo(grupoRepresentante.getIdGrupo());
		this.grupoRepresentante = grupoRepresentante;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public Double getDescMax() {
		return descMax;
	}
	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}
	public Double getComicaoVenda() {
		return comicaoVenda;
	}
	public void setComicaoVenda(Double comicaoVenda) {
		this.comicaoVenda = comicaoVenda;
	}
	public boolean isVisualizaTodosClientes() {
		return visualizaTodosClientes;
	}
	public void setVisualizaTodosClientes(boolean visualizaTodosClientes) {
		this.visualizaTodosClientes = visualizaTodosClientes;
	}
	public Double getMinVenda() {
		return minVenda;
	}
	public void setMinVenda(Double minVenda) {
		this.minVenda = minVenda;
	}
	public Integer getIdRepresentante() {
		return idRepresentante;
	}
	public void setIdRepresentante(Integer idRepresentante) {
		this.idRepresentante = idRepresentante;
	}
	public Representante getRepresentante() {
		return representante;
	}
	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}
	
	public List<GrupoProduto> getGruposProdutos() {
		return gruposProdutos;
	}


	public void setGruposProdutos(List<GrupoProduto> gruposProdutos) {
		this.gruposProdutos = gruposProdutos;
	}


	public String getGruposProdutosStr() {
		return gruposProdutosStr;
	}


	public void setGruposProdutosStr(String gruposProdutosStr) {
		this.gruposProdutosStr = gruposProdutosStr;
	}

	

	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}


	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}


	/**
	 * Converte o GrupoProduto para uma string, separando por virgulas os IDS
	 */
	public void convertGruposProdutos(){
		if(getGruposProdutos() != null){
			StringBuilder sb = new StringBuilder();
			for (GrupoProduto grupoProduto  : getGruposProdutos()) {
				sb.append(grupoProduto.getIdGrupo());
				sb.append(",");
			}
			if(sb.length() > 0){
				sb.deleteCharAt(sb.length()-1);
				setGruposProdutosStr(sb.toString());
			} else {
				setGruposProdutosStr(null);
			}
		}
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime
				* result
				+ ((grupoRepresentante == null) ? 0 : grupoRepresentante
						.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepresentanteFilial other = (RepresentanteFilial) obj;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (grupoRepresentante == null) {
			if (other.grupoRepresentante != null)
				return false;
		} else if (!grupoRepresentante.equals(other.grupoRepresentante))
			return false;
		return true;
	}

	

}
