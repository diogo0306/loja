package br.com.eclinic.entity.medico;


public enum SexoEnum {
	
	MASCULINO(1, "Masculino"), FEMININO(2, "Feminino");

	private int codigo;
	private String descricao;

	private SexoEnum(int codigo, String descricao) {
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
	
	public static SexoEnum getEnumPorCodigo(
			Integer codigoTipoComponenteTransiente) {

		SexoEnum sexoEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			sexoEnum = SexoEnum.MASCULINO;
		}
		if (codigoTipoComponenteTransiente == 2) {
			sexoEnum = SexoEnum.FEMININO;
		}
		return sexoEnum;
	}

}
