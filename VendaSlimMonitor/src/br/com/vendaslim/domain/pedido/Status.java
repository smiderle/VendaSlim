package br.com.vendaslim.domain.pedido;

public enum Status {
	
	
	Pendente(1, "Pendente"),
	Aprovado(2, "Aprovado"),
	AguardandoAprovacao(3, "Aguardando Aprovação");
	
	private int id;
	private String descricao;
	
	Status(int id, String descricao) {
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
	
}
