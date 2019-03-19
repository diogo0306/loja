package br.com.eclinic.entity.plano;

public enum TipoPlanoSaudeEnum {

	INDIVIDUAL_FAMILIAR(1, "Individual/Familiar"), COLETIVO_EMPRESARIAL(2, "Coletivo Empresarial"),
	COLETIVO_POR_ADESAO(3, "Coletivo por Adesão");

	private int codigo;
	private String descricao;

	private TipoPlanoSaudeEnum(int codigo, String descricao) {
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

	public static TipoPlanoSaudeEnum getEnumPorCodigo(Integer codigoTipoComponenteTransiente) {

		TipoPlanoSaudeEnum tipoPlanoSaudeEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			tipoPlanoSaudeEnum = TipoPlanoSaudeEnum.INDIVIDUAL_FAMILIAR;
		}
		if (codigoTipoComponenteTransiente == 2) {
			tipoPlanoSaudeEnum = TipoPlanoSaudeEnum.COLETIVO_EMPRESARIAL;
		}
		if (codigoTipoComponenteTransiente == 3) {
			tipoPlanoSaudeEnum = TipoPlanoSaudeEnum.COLETIVO_POR_ADESAO;
		}
		return tipoPlanoSaudeEnum;
	}

}
