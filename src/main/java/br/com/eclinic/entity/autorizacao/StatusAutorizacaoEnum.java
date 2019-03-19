package br.com.eclinic.entity.autorizacao;

public enum StatusAutorizacaoEnum {

	PENDENTE(1, "Pendente"), PAGO(2, "Pago"), REALIZADO(3, "Realizado"), CANCELADO(4, "Cancelado");

	private int codigo;
	private String descricao;

	private StatusAutorizacaoEnum (int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
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

	public static StatusAutorizacaoEnum getEnumPorCodigo(Integer codigo) {

		StatusAutorizacaoEnum statusAutorizacaoEnum = null;

		if (codigo == 1) {
			statusAutorizacaoEnum = StatusAutorizacaoEnum.PENDENTE;
		}
		if (codigo == 2) {
			statusAutorizacaoEnum = StatusAutorizacaoEnum.PAGO;
		}
		if (codigo == 3) {
			statusAutorizacaoEnum = StatusAutorizacaoEnum.REALIZADO;
		}
		if (codigo == 4) {
			statusAutorizacaoEnum = StatusAutorizacaoEnum.CANCELADO;
		}

		return statusAutorizacaoEnum;
	}
	
}
