package br.com.eclinic.entity.plano;

public enum AcomodacaoPlanoSaudeEnum {
	
	ENFERMARIA(1, "Enfermaria"), APARTAMENTO(2, "Apartamento"), ENFERMARIA_APARTAMENTO(3, "Enfermaria e Apartamento") ;

	private int codigo;
	private String descricao;

	private AcomodacaoPlanoSaudeEnum(int codigo, String descricao) {
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

	public static AcomodacaoPlanoSaudeEnum getEnumPorCodigo(
			Integer codigoTipoComponenteTransiente) {

		AcomodacaoPlanoSaudeEnum acomodacaoPlanoSaudeEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			acomodacaoPlanoSaudeEnum = AcomodacaoPlanoSaudeEnum.ENFERMARIA;
		}
		if (codigoTipoComponenteTransiente == 2) {
			acomodacaoPlanoSaudeEnum = AcomodacaoPlanoSaudeEnum.APARTAMENTO;
		}
		if (codigoTipoComponenteTransiente == 3) {
			acomodacaoPlanoSaudeEnum = AcomodacaoPlanoSaudeEnum.ENFERMARIA_APARTAMENTO;
		}
		return acomodacaoPlanoSaudeEnum;
	}

}
