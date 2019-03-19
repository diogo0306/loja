package br.com.eclinic.entity.agendamento;


public enum StatusPagamentoEnum {

	PENDENTE(1, "Pendente"), PAGO(2, "Pago"), ESTORNADO(3, "Estornado"), CANCELADO(4, "Cancelado");

	private int codigo;
	private String descricao;

	private StatusPagamentoEnum(int codigo, String descricao) {
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

	@SuppressWarnings("static-access")
	public static StatusPagamentoEnum getEnumPorCodigo(Integer codigo) {

		StatusPagamentoEnum statusPagamentoEnum = null;

		if (codigo == 1) {
			statusPagamentoEnum = statusPagamentoEnum.PENDENTE;
		}
		if (codigo == 2) {
			statusPagamentoEnum = statusPagamentoEnum.PAGO;
		}
		if (codigo == 3) {
			statusPagamentoEnum = statusPagamentoEnum.ESTORNADO;
		}
		if (codigo == 4) {
			statusPagamentoEnum = statusPagamentoEnum.CANCELADO;
		}

		return statusPagamentoEnum;
	}

}
