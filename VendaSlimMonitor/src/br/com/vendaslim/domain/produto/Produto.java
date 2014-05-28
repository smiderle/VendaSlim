package br.com.vendaslim.domain.produto;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.vendaslim.domain.Domain;
import br.com.vendaslim.domain.cadastro.Filial;
@Entity
@Table(name="PRODUTO")
@IdClass(ProdutoPK.class)
public class Produto implements Domain{
	
	public Produto() {
		grupoProduto = new GrupoProduto();
	}
	
	@Id
	private Filial filial;
	@Id
	private Integer idProduto;	
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="idempresa", referencedColumnName="idempresa", insertable= false, updatable= false),
		@JoinColumn(name="idfilial", referencedColumnName="idfilial", insertable= false, updatable= false),
		@JoinColumn(name="idgrupo", referencedColumnName="idgrupo", insertable= false, updatable= false),
		
	})
	private GrupoProduto grupoProduto;
	
	@Column
	private Integer idGrupo;
	@Column
	private String descricao;	
	@Column
	private String referencia;
	@Column
	private String codbar;
	@Column
	private Double quantidade;
	@Column
	private Double descMax;
	@Column
	private Double precoVenda;
	@Column
	private Double precoPromocao;
	@Column
	private boolean promocao;
	@Column
	private boolean inativo;
	@Column
	private Calendar dtHrAlteracao;
	@Column
	private String unidade;
	
	public Filial getFilial() {
		return filial;
	}
	public void setFilial(Filial filial) {
		this.filial = filial;
	}
	public Integer getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(Integer idProduto) {
		this.idProduto = idProduto;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public GrupoProduto getGrupoProduto() {
		return grupoProduto;
	}
	public void setGrupoProduto(GrupoProduto grupoProduto) {
		if(grupoProduto != null)
			setIdGrupo(grupoProduto.getIdGrupo());
		else 
			setIdGrupo(null);
		this.grupoProduto = grupoProduto;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getCodbar() {
		return codbar;
	}
	public void setCodbar(String codbar) {
		this.codbar = codbar;
	}
	public Double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Double quantidade) {
		this.quantidade = quantidade;
	}
	public Double getDescMax() {
		return descMax;
	}
	public void setDescMax(Double descMax) {
		this.descMax = descMax;
	}
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	public Double getPrecoPromocao() {
		return precoPromocao;
	}
	public void setPrecoPromocao(Double precoPromocao) {
		this.precoPromocao = precoPromocao;
	}
	public Boolean getPromocao() {
		return promocao;
	}
	public void setPromocao(Boolean promocao) {
		this.promocao = promocao;
	}
	
	public Integer getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}
	public boolean isInativo() {
		return inativo;
	}
	public void setInativo(boolean inativo) {
		this.inativo = inativo;
	}
	public void setPromocao(boolean promocao) {
		this.promocao = promocao;
	}
	public Calendar getDtHrAlteracao() {
		return dtHrAlteracao;
	}
	public void setDtHrAlteracao(Calendar dtHrAlteracao) {
		this.dtHrAlteracao = dtHrAlteracao;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codbar == null) ? 0 : codbar.hashCode());
		result = prime * result + ((descMax == null) ? 0 : descMax.hashCode());
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result
				+ ((dtHrAlteracao == null) ? 0 : dtHrAlteracao.hashCode());
		result = prime * result + ((filial == null) ? 0 : filial.hashCode());
		result = prime * result
				+ ((grupoProduto == null) ? 0 : grupoProduto.hashCode());
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
		result = prime * result
				+ ((idProduto == null) ? 0 : idProduto.hashCode());
		result = prime * result + (inativo ? 1231 : 1237);
		result = prime * result
				+ ((precoPromocao == null) ? 0 : precoPromocao.hashCode());
		result = prime * result
				+ ((precoVenda == null) ? 0 : precoVenda.hashCode());
		result = prime * result + (promocao ? 1231 : 1237);
		result = prime * result
				+ ((quantidade == null) ? 0 : quantidade.hashCode());
		result = prime * result
				+ ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((unidade == null) ? 0 : unidade.hashCode());
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
		Produto other = (Produto) obj;
		if (codbar == null) {
			if (other.codbar != null)
				return false;
		} else if (!codbar.equals(other.codbar))
			return false;
		if (descMax == null) {
			if (other.descMax != null)
				return false;
		} else if (!descMax.equals(other.descMax))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (dtHrAlteracao == null) {
			if (other.dtHrAlteracao != null)
				return false;
		} else if (!dtHrAlteracao.equals(other.dtHrAlteracao))
			return false;
		if (filial == null) {
			if (other.filial != null)
				return false;
		} else if (!filial.equals(other.filial))
			return false;
		if (grupoProduto == null) {
			if (other.grupoProduto != null)
				return false;
		} else if (!grupoProduto.equals(other.grupoProduto))
			return false;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		if (idProduto == null) {
			if (other.idProduto != null)
				return false;
		} else if (!idProduto.equals(other.idProduto))
			return false;
		if (inativo != other.inativo)
			return false;
		if (precoPromocao == null) {
			if (other.precoPromocao != null)
				return false;
		} else if (!precoPromocao.equals(other.precoPromocao))
			return false;
		if (precoVenda == null) {
			if (other.precoVenda != null)
				return false;
		} else if (!precoVenda.equals(other.precoVenda))
			return false;
		if (promocao != other.promocao)
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (unidade == null) {
			if (other.unidade != null)
				return false;
		} else if (!unidade.equals(other.unidade))
			return false;
		return true;
	}
	
	
	
	

}
