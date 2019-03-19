	package br.com.eclinic.entity.medico;

public enum UfEnum {  
	
	

	AC(1, "Acre"), AL(2, "Alagoas"), AM(3, "Amazonas"), AP(4, "Amapá"), BA(5, "Bahia"), CE(6, "Ceará"), DF(7,
			"Distrito Federa"), ES(8, "Espírito Santo"), GO(9, "Goiás"), MA(10, "Maranhão"), MG(11,
					"Minas Gerais"), MS(12, "Mato Grosso do Sul"), MT(13, "Mato Grosso"), PA(14, "Pará"), PB(15,
							"Paraíba"), PE(16, "Pernambuco"), PI(17, "Piauí"), RJ(18, "Rio de Janeiro"), RN(19,
									"Rio Grande do Norte"), RS(20, "Rio Grande do Sul"), RO(21, "Rondônia"), RR(22,
											"Roraima"), SC(23, "Santa Catarina"), SP(24,
													"São Paulo"), SE(25, "Sergipe"), TO(26, "Tocantins"), NAO_INFORMADO(27, "Não informado");

	private int codigo;
	private String descricao;

	private UfEnum(int codigo, String descricao) {
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
	public static UfEnum getEnumPorCodigo(Integer codigo) {


		UfEnum UfEnum = null;

		if (codigo == 1) {
			UfEnum = UfEnum.AC;
		}
		if (codigo == 2) {
			UfEnum = UfEnum.AL;
		}
		if (codigo == 3) {
			UfEnum = UfEnum.AM;
		}
		if (codigo == 4) {
			UfEnum = UfEnum.AP;
		}
		if (codigo == 5) {
			UfEnum = UfEnum.BA;
		}
		if (codigo == 6) {
			UfEnum = UfEnum.CE;
		}
		if (codigo == 7) {
			UfEnum = UfEnum.DF;
		}
		if (codigo == 8) {
			UfEnum = UfEnum.ES;
		}
		if (codigo == 9) {
			UfEnum = UfEnum.GO;
		}
		if (codigo == 10) {
			UfEnum = UfEnum.MA;
		}
		if (codigo == 11) {
			UfEnum = UfEnum.MG;
		}
		if (codigo == 12) {
			UfEnum = UfEnum.MS;
		}
		if (codigo == 13) {
			UfEnum = UfEnum.MT;
		}
		if (codigo == 14) {
			UfEnum = UfEnum.PA;
		}
		if (codigo == 15) {
			UfEnum = UfEnum.PB;
		}
		if (codigo == 16) {
			UfEnum = UfEnum.PE;
		}
		if (codigo == 17) {
			UfEnum = UfEnum.PI;
		}
		if (codigo == 18) {
			UfEnum = UfEnum.RJ;
		}
		if (codigo == 19) {
			UfEnum = UfEnum.RN;
		}
		if (codigo == 20) {
			UfEnum = UfEnum.RS;
		}
		if (codigo == 21) {
			UfEnum = UfEnum.RO;
		}
		if (codigo == 22) {
			UfEnum = UfEnum.RR;
		}
		if (codigo == 23) {
			UfEnum = UfEnum.SC;
		}
		if (codigo == 24) {
			UfEnum = UfEnum.SP;
		}
		if (codigo == 25) {
			UfEnum = UfEnum.SE;
		}
		if (codigo == 26) {
			UfEnum = UfEnum.TO;
		}
		if (codigo == 27) {
			UfEnum = UfEnum.NAO_INFORMADO;
		}

		return UfEnum;
	}
	
	
	

}
