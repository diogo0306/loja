package br.com.eclinic.entity.credenciado;

public enum HonorarioContaInternaEnum {
	
	AMB92(1, "0005 - I - AMB 92"), TESTE(2, "TESTE");

	private int codigo;
	private String descricao;

	private HonorarioContaInternaEnum (int codigo, String descricao) {
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
	public static HonorarioContaInternaEnum getEnumPorCodigo(Integer codigo) {

		HonorarioContaInternaEnum honorarioContaInternaEnum = null;

		if (codigo == 1) {
			honorarioContaInternaEnum = honorarioContaInternaEnum.AMB92;
		}
		if (codigo == 2) {
			honorarioContaInternaEnum = honorarioContaInternaEnum.TESTE;
		}

		return honorarioContaInternaEnum;
	}

}
