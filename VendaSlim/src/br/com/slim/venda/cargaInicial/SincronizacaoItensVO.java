package br.com.slim.venda.cargaInicial;

public class SincronizacaoItensVO {
	
	
	public final static int PENDENTE = 0;
	public final static int SINCRONIZADO = 1;
	public final static int FALHA = 2;
	

	public SincronizacaoItensVO(String itemDescricao, double count) {
		super();
		this.itemDescricao = itemDescricao;
		this.count = count;
	}
	private String itemDescricao;	
	private double count;
	private int statusSincronizacao;
		
	public int getStatusSincronizacao() {
		return statusSincronizacao;
	}
	public void setStatusSincronizacao(int statusSincronizacao) {
		this.statusSincronizacao = statusSincronizacao;
	}
	public double getCount() {
		return count;
	}
	public void setCount(double count) {
		this.count = count;
	}
	public String getItemDescricao() {
		return itemDescricao;
	}
	public void setItemDescricao(String itemDescricao) {
		this.itemDescricao = itemDescricao;
	}
	
	
}
