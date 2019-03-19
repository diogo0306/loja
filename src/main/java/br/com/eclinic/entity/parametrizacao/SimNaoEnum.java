package br.com.eclinic.entity.parametrizacao;

public enum SimNaoEnum {
	
	SIM(1, "SIM"), NAO(2, "NÃO");
	
	
	private int codigo;
	private String descricao;

	private SimNaoEnum(int codigo, String descricao) {
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
	public static SimNaoEnum getEnumPorCodigo(Integer codigo) {

		SimNaoEnum simNaoEnum = null;

		if (codigo == 1) {
			simNaoEnum = simNaoEnum.SIM;
		}
		if (codigo == 2) {
			simNaoEnum = simNaoEnum.NAO;
		}
		
		return simNaoEnum;
	}

}
