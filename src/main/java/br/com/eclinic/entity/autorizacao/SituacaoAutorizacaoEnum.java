package br.com.eclinic.entity.autorizacao;

public enum SituacaoAutorizacaoEnum {
	
	PENDENTE(1, "Pendente"), AUTORIZADO(2, "Autorizado"), CANCELADO(3, "Cancelado"), ORCAMENTO(4, "Orçamento");

	private int codigo;
	private String descricao;

	private SituacaoAutorizacaoEnum (int codigo, String descricao) {
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

	public static SituacaoAutorizacaoEnum getEnumPorCodigo(Integer codigo) {

		SituacaoAutorizacaoEnum situacaoAutorizacaoEnum = null;

		if (codigo == 1) {
			situacaoAutorizacaoEnum = SituacaoAutorizacaoEnum.PENDENTE;
		}
		if (codigo == 2) {
			situacaoAutorizacaoEnum = SituacaoAutorizacaoEnum.AUTORIZADO;
		}
		if (codigo == 3) {
			situacaoAutorizacaoEnum = SituacaoAutorizacaoEnum.CANCELADO;
		}
		if (codigo == 4) {
			situacaoAutorizacaoEnum = SituacaoAutorizacaoEnum.ORCAMENTO;
		}

		return situacaoAutorizacaoEnum;
	}

}
