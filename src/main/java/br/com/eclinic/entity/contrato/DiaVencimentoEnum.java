package br.com.eclinic.entity.contrato;

public enum DiaVencimentoEnum {

	CINCO(1, "5"), DEZ(2, "10"), QUINZE(3, "15"), VINTE(4, "20"), VINTECINCO(5, "25"),
	TRINTA(6, "30");

	private int codigo;
	private String descricao;

	private DiaVencimentoEnum(int codigo, String descricao) {
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
	public static DiaVencimentoEnum getEnumPorCodigo(Integer codigo) {

		DiaVencimentoEnum diaVencimentoEnum = null;

		if (codigo == 1) {
			diaVencimentoEnum = diaVencimentoEnum.CINCO;
		}
		if (codigo == 2) {
			diaVencimentoEnum = diaVencimentoEnum.DEZ;
		}

		if (codigo == 3) {
			diaVencimentoEnum = diaVencimentoEnum.QUINZE;
		}

		if (codigo == 4) {
			diaVencimentoEnum = diaVencimentoEnum.VINTE;
		}

		if (codigo == 5) {
			diaVencimentoEnum = diaVencimentoEnum.VINTECINCO;
		}

		if (codigo == 6) {
			diaVencimentoEnum = diaVencimentoEnum.TRINTA;
		}

		return diaVencimentoEnum;

	}

}
