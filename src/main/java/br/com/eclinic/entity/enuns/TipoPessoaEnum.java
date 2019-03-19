package br.com.eclinic.entity.enuns;



public enum TipoPessoaEnum {

	FISICA(1, "Pessoa Física"), JURIDICA(2, "Pessoa Jurídica");

	private int codigo;
	private String descricao;
	

	private TipoPessoaEnum (int codigo, String descricao ) {
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
	public static TipoPessoaEnum getEnumPorCodigo(Integer codigo) {

		TipoPessoaEnum tipoPessoaEnum = null;

		if (codigo == 1) {
			tipoPessoaEnum = tipoPessoaEnum.FISICA;
		}
		if (codigo == 2) {
			tipoPessoaEnum = tipoPessoaEnum.JURIDICA;
		}

		return tipoPessoaEnum;
	}

}
