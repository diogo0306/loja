package br.com.eclinic.entity.medico;

public enum TipoContratoEnum {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoContratoEnum(int codigo, String descricao) {
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
	public static TipoContratoEnum getEnumPorCodigo(Integer codigo) {

		TipoContratoEnum tipoContratoEnum = null;

		if (codigo == 1) {
			tipoContratoEnum = tipoContratoEnum.PESSOA_FISICA;
		}

		if (codigo == 2) {
			tipoContratoEnum = tipoContratoEnum.PESSOA_JURIDICA;
		}

		return tipoContratoEnum;

	}

}
