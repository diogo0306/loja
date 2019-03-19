package br.com.eclinic.entity.parametrizacao;

public enum TipoCobrancaEmpresaEnum {

	BOLETO_PROPRIO(1, "Boleto Próprio"), BOLETO_PRE_IMPRESSO(2, "Boleto Pré-Impresso"), CARNE(3, "Carnê"),
	EXPORTACAO(4, "Exportação"), CARNE_BOLETO(5, "Carnê Boleto");

	private int codigo;
	private String descricao;

	private TipoCobrancaEmpresaEnum(int codigo, String descricao) {
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
	public static TipoCobrancaEmpresaEnum getEnumPorCodigo(Integer codigo) {

		TipoCobrancaEmpresaEnum tipoCobrancaEmpresaEnum = null;

		if (codigo == 1) {
			tipoCobrancaEmpresaEnum = tipoCobrancaEmpresaEnum.BOLETO_PROPRIO;
		}
		if (codigo == 2) {
			tipoCobrancaEmpresaEnum = tipoCobrancaEmpresaEnum.BOLETO_PRE_IMPRESSO;
		}
		if (codigo == 3) {
			tipoCobrancaEmpresaEnum = tipoCobrancaEmpresaEnum.CARNE;
		}
		if (codigo == 4) {
			tipoCobrancaEmpresaEnum = tipoCobrancaEmpresaEnum.EXPORTACAO;
		}
		if (codigo == 5) {
			tipoCobrancaEmpresaEnum = tipoCobrancaEmpresaEnum.CARNE_BOLETO;
		}

		return tipoCobrancaEmpresaEnum;
	}

}
