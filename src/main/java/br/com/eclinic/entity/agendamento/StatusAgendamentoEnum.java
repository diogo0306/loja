package br.com.eclinic.entity.agendamento;

public enum StatusAgendamentoEnum {
	AGUARDANDO (1, "Aguardando", "label-warning"),
	CONFIRMADO (2, "Cofirmado", "label-primary"),
	CONFIRMADO_URGENCIA (3, "Urgência", "label-danger"),
	EM_ATENDIMENTO (4, "Em antendimento", "label-info"),
	CANCELADO (5, "Cancelado", "label-default"),
	ATENDIDO (6, "Atendido", "label-success");
	
	private int codigo;
	private String descricao;
	private String estilo;
	
	private StatusAgendamentoEnum(int codigo,String descricao, String estilo){
		this.descricao = descricao;
		this.codigo = codigo;
		this.estilo = estilo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
