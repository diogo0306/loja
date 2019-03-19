package br.com.eclinic.entity.contrato;

public enum TipoPessoaContratoEnum {
	
	FISICA(1, "Pessoa Física"), JURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;

	private TipoPessoaContratoEnum (int codigo, String descricao) {
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
	public static TipoPessoaContratoEnum getEnumPorCodigo(Integer codigo) {

		TipoPessoaContratoEnum tipoPessoaContratoEnum = null;

		if (codigo == 1) {
			tipoPessoaContratoEnum = tipoPessoaContratoEnum.FISICA;
		}
		if (codigo == 2) {
			tipoPessoaContratoEnum = tipoPessoaContratoEnum.JURIDICA;
		}

		return tipoPessoaContratoEnum;
	}


}
