package br.com.eclinic.entity.credenciado;

public enum SituacaoEnum {

	ATIVO(1, "Ativo"), SUSPENSO(2, "Suspenso"), CANCELADO(3, "Cancelado");

	private int codigo;
	private String descricao;

	private SituacaoEnum(int codigo, String descricao) {
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
	public static SituacaoEnum getEnumPorCodigo(Integer codigo) {

		SituacaoEnum situacaoEnum = null;

		if (codigo == 1) {
			situacaoEnum = situacaoEnum.ATIVO;
		}
		if (codigo == 2) {
			situacaoEnum = situacaoEnum.SUSPENSO;
		}
		if (codigo == 3) {
			situacaoEnum = situacaoEnum.CANCELADO;
		}

		return situacaoEnum;
	}

}
