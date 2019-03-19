package br.com.eclinic.entity.paciente;

public enum TipoTaxaEnum {
	
	VALOR("Valor", 1), PERCENTUAL("Percentual", 2);
	
	private String descricao;
	private int codigo;
	
	private TipoTaxaEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
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

	public static TipoTaxaEnum getEnumPorCodigo (Integer codigo) {
		
		TipoTaxaEnum tipoTaxaEnum = null;
		
		if(codigo == 1) {
			tipoTaxaEnum = TipoTaxaEnum.VALOR;
		}
		
		if(codigo == 2) {
			tipoTaxaEnum = TipoTaxaEnum.PERCENTUAL;
		}
		
		return tipoTaxaEnum;
	}
}
