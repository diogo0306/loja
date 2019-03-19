package br.com.eclinic.entity.autorizacao;

public enum TipoAutorizacaoEnum {
	
	CONSULTA(1, "Consulta"), SP_SADP(2, "SP/SADP"), CIRURGIA(3, "Cirurgia");

	private int codigo;
	private String descricao;

	private TipoAutorizacaoEnum (int codigo, String descricao) {
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

	public static TipoAutorizacaoEnum getEnumPorCodigo(Integer codigo) {

		TipoAutorizacaoEnum tipoAutorizacaoEnum = null;

		if (codigo == 1) {
			tipoAutorizacaoEnum = TipoAutorizacaoEnum.CONSULTA;
		}
		if (codigo == 2) {
			tipoAutorizacaoEnum = TipoAutorizacaoEnum.SP_SADP;
		}
		if (codigo == 3) {
			tipoAutorizacaoEnum = TipoAutorizacaoEnum.CIRURGIA;
		}

		return tipoAutorizacaoEnum;
	}

}
