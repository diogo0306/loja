package br.com.eclinic.entity.consulta;

public enum StatusConsultaEnum {
	
	PENDENTE(1, "Pendente"), PAGO(2, "Pago"), REALIZADO(3, "Realizado"), CANCELADO(4, "Cancelado");
	
	private int codigo;
	private String descricao;

	private StatusConsultaEnum(int codigo,String descricao){
		this.descricao = descricao;
		this.codigo = codigo;		
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
