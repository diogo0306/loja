package br.com.eclinic.entity.parametrizacao;


public enum MesEnum {

	JANEIRO(1, "Janeiro"), FEVEREIRO(2, "Fevereiro"),
	MARÇO(3, "Março"), ABRIL(4, "Abril"), MAIO(5, "Maio"),
	JUNHO(6, "Junho"), JULHO(7, "Julho"),
	AGOSTO(8, "Agosto"), SETEMBRO(9, "Setembro"), OUTUBRO(9, "Outubro"),
	NOVEMBRO(9, "Novembro"), DEZEMBRO(9, "Dezembro");

	private int codigo;
	private String descricao;

	private MesEnum(int codigo, String descricao) {
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
	public static MesEnum getEnumPorCodigo(Integer codigo) {

		MesEnum mesEnum = null;

		if (codigo == 1) {
			mesEnum = mesEnum.JANEIRO;
		}
		if (codigo == 2) {
			mesEnum = mesEnum.FEVEREIRO;
		}
		if (codigo == 3) {
			mesEnum = mesEnum.MARÇO;
		}
		if (codigo == 4) {
			mesEnum = mesEnum.ABRIL;
		}
		if (codigo == 5) {
			mesEnum = mesEnum.MAIO;
		}
		if (codigo == 6) {
			mesEnum = mesEnum.JUNHO;
		}
		if (codigo == 7) {
			mesEnum = mesEnum.JULHO;
		}
		if (codigo == 8) {
			mesEnum = mesEnum.AGOSTO;
		}
		if (codigo == 9) {
			mesEnum = mesEnum.SETEMBRO;
		}
		if (codigo == 10) {
			mesEnum = mesEnum.OUTUBRO;
		}
		if (codigo == 11) {
			mesEnum = mesEnum.NOVEMBRO;
		}
		if (codigo == 12) {
			mesEnum = mesEnum.DEZEMBRO;
		}

		return mesEnum;
	}
}
