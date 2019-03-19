package br.com.eclinic.entity.enuns;

public enum SituacaoContratoEnum {

	ATIVO(1, "Ativo"), SUSPENSO(2, "Suspenso"), INATIVO(3, "Inativo");
	
	private int codigo;
	private String descricao;

	private SituacaoContratoEnum(int codigo, String descricao) {
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
	
	public static SituacaoContratoEnum getEnumPorCodigo(
			Integer codigoTipoComponenteTransiente) {

		SituacaoContratoEnum situacaoEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			situacaoEnum = SituacaoContratoEnum.ATIVO;
		}
		if (codigoTipoComponenteTransiente == 2) {
			situacaoEnum = SituacaoContratoEnum.SUSPENSO;
		}
		if (codigoTipoComponenteTransiente == 3) {
			situacaoEnum = SituacaoContratoEnum.INATIVO;
		}
		return situacaoEnum;
	}

}
