package br.com.vendaslim.domain.representante;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
import br.com.vendaslim.domain.produto.GrupoProduto;
@Entity
@Table(name="GRUPREP")
@IdClass(GrupoRepresentantePK.class)
public class GrupoRepresentante implements Domain{

	public GrupoRepresentante() {
		this.filial = new Filial();
	}
	
	@Id
	private Filial filial;
	@Id
	private Integer idGrupo;
	
	@Column(name="DESCRICAO", nullable=false)
	private String descricao;
	
	@Column(name="DESCMAX")
	private Double descMax;
	
	@Column(name="MINVENDA")	
	private Double minVenda;
	
	@Column(name="COMICAOVENDA")	
	private Double comicaoVenda;
		
	@Column(name="VISUALIZATODOSCLIENTES")
	private Boolean visualizaTodosClientes;
	
	@Column(name="DTHRCADASTRO", insertable=false, updatable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrCadastro;
	
	@Column(name="DTHRALTERACAO")	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar dtHrAlteracao;
	
	@Transient
	private List<GrupoProduto> gruposProdutos = new ArrayList<GrupoProduto>();
	
	@Column(name="GRUPOPRODUTO")
	private String gruposProdutosStr;
			
	@OneToMany(mappedBy="grupoRepresentante", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<GrupoRepresentanteTabPreco> tabelasPreco;
	
	@OneToMany(mappedBy="grupoRepresentante", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<GrupoRepresentanteParcelamento> parcelamentos;
	
	
	/**
	 * Retorna os ids das tabelas de preco separados por virgula
	 * @return
	 */
	public String getTabelasPrecoString(){
		StringBuffer tabPrecoStr = new StringBuffer();
		if(tabelasPreco != null && tabelasPreco.size() > 0){		
			for (GrupoRepresentanteTabPreco grupoRepTabPreco : tabelasPreco) {
				tabPrecoStr.append(grupoRepTabPreco.getTabelaPreco().getIdTabelaPreco());
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
			for (GrupoRepresentanteParcelamento grupoRepParcelamento : parcelamentos) {
				parcelamentoStr.append(grupoRepParcelamento.getParcelamento().getIdParcelamento());
				parcelamentoStr.append(",");
			}
			//Remove a ultima virgula
			parcelamentoStr.deleteCharAt(parcelamentoStr.length()-1);
		}
		return parcelamentoStr.toString();
	}
	
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
		
	public List<GrupoRepresentanteTabPreco> getTabelasPreco() {
		return tabelasPreco;
	}
	public void setTabelasPreco(List<GrupoRepresentanteTabPreco> tabelasPreco) {
		this.tabelasPreco = tabelasPreco;
	}
	public List<GrupoRepresentanteParcelamento> getParcelamentos() {
		return parcelamentos;
	}
	public void setParcelamentos(List<GrupoRepresentanteParcelamento> parcelamentos) {
		this.parcelamentos = parcelamentos;
	}
	public Double getDescMax() {
		return descMax;
	}
	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}
	public Double getMinVenda() {
		return minVenda;
	}
	public void setMinVenda(Double minVenda) {
		this.minVenda = minVenda;
	}
	public Double getComicaoVenda() {
		return comicaoVenda;
	}
	public void setComicaoVenda(Double comicaoVenda) {
		this.comicaoVenda = comicaoVenda;
	}
	public Boolean getVisualizaTodosClientes() {
		return visualizaTodosClientes;
	}
	public void setVisualizaTodosClientes(Boolean visualizaTodosClientes) {
		this.visualizaTodosClientes = visualizaTodosClientes;
	}
	public Calendar getDtHrCadastro() {
		return dtHrCadastro;
	}
	public void setDtHrCadastro(Calendar dtHrCadastro) {
		this.dtHrCadastro = dtHrCadastro;
	}
	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}
	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}

	public List<GrupoProduto> getGruposProdutos() {
		return gruposProdutos;
	}

	public void setGruposProdutos(List<GrupoProduto> gruposProdutos) {
		this.gruposProdutos = gruposProdutos;
	}

	public String getGruposProdutosStr() {
		return this.gruposProdutosStr;
	}

	public void setGruposProdutosStr(String gruposProdutosStr) {
		this.gruposProdutosStr = gruposProdutosStr;
	}	
	
	/**
	 * Converte o GrupoProduto para uma string, separando por virgulas os IDS
	 */
	public void convertGruposProdutos(){
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		return result;
	}

//FOI FEITO UMA ALTERAÇÃO NA FILIAL POIS ESTA VINDO LAZY E NÃO FAZ A COMPARAÇÃO CORRETA. Estava dando problema na edição de filial do representante, pois não carregava o grupo do representante para o representanteFilial
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoRepresentante other = (GrupoRepresentante) obj;
		if (filial == null) {
			if (other.filial != null)
				return false;
//FOI ALTERADO
		} else if (!filial.getIdFilial().equals(other.filial.getIdFilial()) || !filial.getEmpresa().getIdEmpresa().equals(other.filial.getEmpresa().getIdEmpresa()))
			return false;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		return true;
	}
	
	
	
	

	
	

	
	
}
