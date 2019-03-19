package br.com.eclinic.entity.parametrizacao;

public enum ControleContratualEnum {
	
	PLANO(1, "001 - Plano"), OUTROS(2, "Outros");
	
	
	private int codigo;
	private String descricao;

	private ControleContratualEnum(int codigo, String descricao) {
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
	public static ControleContratualEnum getEnumPorCodigo(Integer codigo) {

		ControleContratualEnum controleContratualEnum = null;

		if (codigo == 1) {
			controleContratualEnum = controleContratualEnum.PLANO;
		}
		if (codigo == 2) {
			controleContratualEnum = controleContratualEnum.OUTROS;
		}
		
		return controleContratualEnum;
	}
}