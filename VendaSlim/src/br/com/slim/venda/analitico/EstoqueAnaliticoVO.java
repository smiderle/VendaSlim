package br.com.slim.venda.analitico;

import br.com.slim.venda.item.ItemVO;

public class EstoqueAnaliticoVO {
	public static final int ANALITICO_ENTRADA_MANUAL = 1;
	public static final int ANALITICO_SAIDA_MANUAL = 2;
	public static final int ANALITICO_VENDA_EFETIVADA = 3;
	public static final int ANALITICO_VENDA_CANCELADA = 4;

	private ItemVO itemVO;
	private long dateMov;
	private int tipoMov;
	private double quantidade;
	private int idEmpresa;
	
	public String getTipoMovDescricao(){
		if(getTipoMov() == ANALITICO_ENTRADA_MANUAL){
			return "Entrada Manual";
		} else if(getTipoMov() == ANALITICO_SAIDA_MANUAL){
			return "Saida Manual";
		} else  if(getTipoMov() == ANALITICO_VENDA_CANCELADA){
			return "Venda Cancelada";
		} else if(getTipoMov() == ANALITICO_VENDA_EFETIVADA){
			return "Venda Efetivada";
		}
		return "" ;
	}
	
	public boolean isEntry(){
		return getTipoMov() == ANALITICO_ENTRADA_MANUAL || 
				getTipoMov() ==  ANALITICO_VENDA_CANCELADA;
	}
	
	public ItemVO getItemVO() {
		return itemVO;
	}
	public void setItemVO(ItemVO itemVO) {
		this.itemVO = itemVO;
	}
	public long getDateMov() {
		return dateMov;
	}
	public void setDateMov(long dateMov) {
		this.dateMov = dateMov;
	}
	public int getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(int tipoMov) {
		this.tipoMov = tipoMov;
	}
	public double getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}
	
	
	
	public int getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(int idEmpresa) {
		this.idEmpresa = idEmpresa;
	}



	public static class EstoqueAnalitico {
		private EstoqueAnalitico(){}
		public static final String TABELA = "ESTOQUE_ANALITICO";
		public static final String IDITEM = "IDITEM";
		public static final String IDEMPRESA = "IDEMPRESA";
		public static final String DTMOVIMENTACAO = "DTMOVIMENTACAO";
		public static final String TIPOMOVIMENTACAO = "TIPOMOVIMENTACAO";
		public static final String QUANTIDADE = "QUANTIDADE";
		public static final String IDSEQUENCIA = "IDSEQUENCIA";
		public static final String[] COLUNAS = new String[]{IDITEM, DTMOVIMENTACAO, TIPOMOVIMENTACAO, QUANTIDADE, IDSEQUENCIA, IDEMPRESA};
	}
	

}
