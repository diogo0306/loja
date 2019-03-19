package br.com.eclinic.entity.solicitacao;

public enum StatusEnum {
	
	PENDENTE("Pendente", 1), APROVADO("Aprovado", 2), REPROVADO("Reprovado", 3), CONSULTA_AGENDADA("Consulta Agendada", 4);
	
	private String descricao;
	private int codigo;
	
	private StatusEnum (String descricao, Integer codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
