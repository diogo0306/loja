package br.com.eclinic.entity.enuns;

public enum SituacaoEnum {

	ATIVO(1, "Ativo"), SUSPENSO(2, "Suspenso"), INATIVO(3, "Inativo");
	
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
	
	public static SituacaoEnum getEnumPorCodigo(
			Integer codigoTipoComponenteTransiente) {

		SituacaoEnum situacaoEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			situacaoEnum = SituacaoEnum.ATIVO;
		}
		if (codigoTipoComponenteTransiente == 2) {
			situacaoEnum = SituacaoEnum.SUSPENSO;
		}
		if (codigoTipoComponenteTransiente == 3) {
			situacaoEnum = SituacaoEnum.INATIVO;
		}
		return situacaoEnum;
	}

}
