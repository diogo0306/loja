package br.com.eclinic.entity.medico;

public enum EspecialidadeMedicaEnum {

	CLINICO_GERAL(1, "Clínico Geral"), CARDIOLOGISTA(2, "Cardiologista"), CIRURGIAO(
			3, "Cirurgião"), OBSTETRA(4, "Obstetra"), PEDIATRA(5, "Pediatra"), PSIQUIATRA(
			6, "Psiquiatra"), ORTOPEDISTA(7, "Ortopedista");

	private int codigo;
	private String descricao;

	private EspecialidadeMedicaEnum(int codigo, String descricao) {
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

	public static EspecialidadeMedicaEnum getEnumPorCodigo(Integer codigo) {

		EspecialidadeMedicaEnum especialidadeMedicaEnum = null;

		if (codigo == 1) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.CLINICO_GERAL;
		}
		if (codigo == 2) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.CARDIOLOGISTA;
		}
		if (codigo == 3) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.CIRURGIAO;
		}
		if (codigo == 4) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.OBSTETRA;
		}
		if (codigo == 5) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.PEDIATRA;
		}
		if (codigo == 6) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.PSIQUIATRA;
		}
		if (codigo == 7) {
			especialidadeMedicaEnum = EspecialidadeMedicaEnum.ORTOPEDISTA;
		}

		return especialidadeMedicaEnum;

	}

}
