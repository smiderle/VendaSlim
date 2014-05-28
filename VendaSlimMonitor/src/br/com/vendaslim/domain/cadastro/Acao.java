package br.com.vendaslim.domain.cadastro;

public enum Acao {
	
	
	Avisar(1, "Avisar"),
	Bloquear(2, "Bloquear"),
	Nada(3, "Nada");
	
	private int id;
	private String descricao;
	
	Acao(int id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}
	
	@Override
	public String toString() {	
		return this.descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
