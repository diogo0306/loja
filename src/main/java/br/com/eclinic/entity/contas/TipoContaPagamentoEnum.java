package br.com.eclinic.entity.contas;

public enum TipoContaPagamentoEnum {
	
	REPRESENTANTE(1, "Comissão/Representante"), CREDENCIADO(2, "Credenciado");

	private int codigo;
	private String descricao;

	private TipoContaPagamentoEnum (int codigo, String descricao) {
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
	
	public static TipoContaPagamentoEnum getEnumPorCodigo(Integer codigo) {

		TipoContaPagamentoEnum tipoContaPagamentoEnum = null;

		if (codigo == 1) {
			tipoContaPagamentoEnum = TipoContaPagamentoEnum.REPRESENTANTE;
		}
		if (codigo == 2) {
			tipoContaPagamentoEnum = TipoContaPagamentoEnum.CREDENCIADO;
		}

		return tipoContaPagamentoEnum;
	}

}
