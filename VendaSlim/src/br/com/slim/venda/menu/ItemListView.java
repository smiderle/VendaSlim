package br.com.slim.venda.menu;

public class ItemListView {
	private String opcao;
	private int iconeRid;
	
	public ItemListView(String opcao, int iconeRid) {
		super();
		this.opcao = opcao;
		this.iconeRid = iconeRid;
	}
	public ItemListView() {
		super();
	}
	
	
	public String getOpcao() {
		return opcao;
	}
	public void setOpcao(String opcao) {
		this.opcao = opcao;
	}
	public int getIconeRid() {
		return iconeRid;
	}
	public void setIconeRid(int iconeRid) {
		this.iconeRid = iconeRid;
	}
}
