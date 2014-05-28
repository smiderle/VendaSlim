package br.com.vendaslim.domain.produto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;

import br.com.vendaslim.domain.cadastro.Filial;

public class ProdutoPK implements Serializable{
	
	@ManyToOne(fetch=FetchType.LAZY)	
	@JoinColumns(
			{
				@JoinColumn(name="idempresa", referencedColumnName="idempresa"),
				@JoinColumn(name="idfilial", referencedColumnName="idfilial")
			}
			)
	private Filial filial;
	
	@Column
	private Integer idProduto;

}
