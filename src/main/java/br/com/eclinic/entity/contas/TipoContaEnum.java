package br.com.eclinic.entity.contas;

public enum TipoContaEnum {
	
	CONTRATO(1, "Contrato"), AUTORIZACAO(2, "Autorização");

	private int codigo;
	private String descricao;

	private TipoContaEnum (int codigo, String descricao) {
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

	@SuppressWarnings("static-access")
	public static TipoContaEnum getEnumPorCodigo(Integer codigo) {

		TipoContaEnum tipoContaEnum = null;

		if (codigo == 1) {
			tipoContaEnum = tipoContaEnum.CONTRATO;
		}
		if (codigo == 2) {
			tipoContaEnum = tipoContaEnum.AUTORIZACAO;
		}

		return tipoContaEnum;
	}

}
