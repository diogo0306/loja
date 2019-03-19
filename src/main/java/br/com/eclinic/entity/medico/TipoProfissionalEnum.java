package br.com.eclinic.entity.medico;

public enum TipoProfissionalEnum {

	CLINICO_GERAL(1, "Clínico Geral"), FISIOTERAPEUTA(2, "Fisioterapeuta"), ODONTOLOGIA(3, "Odontólogo"), 
	FONOAUDIOLOGO(4, "Fonoaudiólogo"), ALERGOLOGISTA(5, "Alergologista"), CARDIOLOGISTA(6, "Cardiologista"),
	CIRURGIA_PLATICA(7, "Cirurgião Plástico"), COLOPROCTOLOGIA(8, "Coloproctologista"), DERMATOLOGIA(9, "Dermatologista"), 
	ENDOCRINOLOGIA(10, "Endocrinologista"), GASTROENTEROLOGIA(11, "Gastroenterologista"), GERIATRIA(12, "Geriatra"),
	OBSTETRICIA(13, "Obstetra"), HEMATOLOGIA(14, "Hematologista"), INFECTOLOGIA(15, "Infectologista"),
	MASTOLOGIA(16, "Mastologista"), MEDICINA_DO_TRABALHO(17, "Medicina do Trabalho"), NEUROLOGIA(18, "Neurologista"),
	OFTALMOLOGIA(19, "Oftalmologista"), ORTOPEDIA(20, "Ortopedista"), OTORRINOLARINGOLOGIA(21, "Otorrinolaringologista"),
	PATOLOGIA(22, "Patologista"), PEDIATRIA(23, "Pediatra"), PNEUMOLOGIA(24, "Pneumologista"), PSIQUIATRIA(25, "Psiquiatra"),
	RADIOLOGIA(26, "Radiologista"), RADIOTERAPIA(27, "Radioterapeuta"), REUMATOLOGIA(28, "Reumatologista"),
	UROLOGIA(29, "Urologista");

	private int codigo;
	private String descricao;

	private TipoProfissionalEnum(int codigo, String descricao) {
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

	public static TipoProfissionalEnum getEnumPorCodigo(Integer codigo) {

		TipoProfissionalEnum tipoProfissionalEnum = null;

		if (codigo == 1) {
			tipoProfissionalEnum = TipoProfissionalEnum.CLINICO_GERAL;
		}
		if (codigo == 2) {
			tipoProfissionalEnum = TipoProfissionalEnum.FISIOTERAPEUTA;
		}
		if (codigo == 3) {
			tipoProfissionalEnum = TipoProfissionalEnum.ODONTOLOGIA;
		}
		if (codigo == 4) {
			tipoProfissionalEnum = TipoProfissionalEnum.FONOAUDIOLOGO;
		}
		if (codigo == 5) {
			tipoProfissionalEnum = TipoProfissionalEnum.ALERGOLOGISTA;
		}
		if (codigo == 6) {
			tipoProfissionalEnum = TipoProfissionalEnum.CARDIOLOGISTA;
		}
		if (codigo == 7) {
			tipoProfissionalEnum = TipoProfissionalEnum.CIRURGIA_PLATICA;
		}
		if (codigo == 8) {
			tipoProfissionalEnum = TipoProfissionalEnum.COLOPROCTOLOGIA;
		}
		if (codigo == 9) {
			tipoProfissionalEnum = TipoProfissionalEnum.DERMATOLOGIA;
		}
		if (codigo == 10) {
			tipoProfissionalEnum = TipoProfissionalEnum.ENDOCRINOLOGIA;
		}
		if (codigo == 11) {
			tipoProfissionalEnum = TipoProfissionalEnum.GASTROENTEROLOGIA;
		}
		if (codigo == 12) {
			tipoProfissionalEnum = TipoProfissionalEnum.GERIATRIA;
		}
		if (codigo == 13) {
			tipoProfissionalEnum = TipoProfissionalEnum.OBSTETRICIA;
		}
		if (codigo == 14) {
			tipoProfissionalEnum = TipoProfissionalEnum.HEMATOLOGIA;
		}
		if (codigo == 15) {
			tipoProfissionalEnum = TipoProfissionalEnum.INFECTOLOGIA;
		}
		if (codigo == 16) {
			tipoProfissionalEnum = TipoProfissionalEnum.MASTOLOGIA;
		}
		if (codigo == 17) {
			tipoProfissionalEnum = TipoProfissionalEnum.MEDICINA_DO_TRABALHO;
		}
		if (codigo == 18) {
			tipoProfissionalEnum = TipoProfissionalEnum.NEUROLOGIA;
		}
		if (codigo == 19) {
			tipoProfissionalEnum = TipoProfissionalEnum.OFTALMOLOGIA;
		}
		if (codigo == 20) {
			tipoProfissionalEnum = TipoProfissionalEnum.ORTOPEDIA;
		}
		if (codigo == 21) {
			tipoProfissionalEnum = TipoProfissionalEnum.OTORRINOLARINGOLOGIA;
		}
		if (codigo == 22) {
			tipoProfissionalEnum = TipoProfissionalEnum.PATOLOGIA;
		}
		if (codigo == 23) {
			tipoProfissionalEnum = TipoProfissionalEnum.PEDIATRIA;
		}
		if (codigo == 24) {
			tipoProfissionalEnum = TipoProfissionalEnum.PNEUMOLOGIA;
		}
		if (codigo == 25) {
			tipoProfissionalEnum = TipoProfissionalEnum.PSIQUIATRIA;
		}
		if (codigo == 26) {
			tipoProfissionalEnum = TipoProfissionalEnum.RADIOLOGIA;
		}
		if (codigo == 27) {
			tipoProfissionalEnum = TipoProfissionalEnum.RADIOTERAPIA;
		}
		if (codigo == 28) {
			tipoProfissionalEnum = TipoProfissionalEnum.REUMATOLOGIA;
		}
		if (codigo == 29) {
			tipoProfissionalEnum = TipoProfissionalEnum.UROLOGIA;
		}

		return tipoProfissionalEnum;
	}

}
