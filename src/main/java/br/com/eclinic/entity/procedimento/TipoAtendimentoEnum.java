package br.com.eclinic.entity.procedimento;

public enum TipoAtendimentoEnum {
	
	AMBULATORIAIS(1, "Ambulatoriais"), INTERNACOES(2, "Internações");
	
	private int codigo;
	private String descricao;

	private TipoAtendimentoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoAtendimentoEnum getEnumPorCodigo(Integer codigoTipoComponenteTransiente) {

		TipoAtendimentoEnum tipoAtendimentoEnum = null;

		if (codigoTipoComponenteTransiente == 1) {
			tipoAtendimentoEnum = TipoAtendimentoEnum.AMBULATORIAIS;
		}
		if (codigoTipoComponenteTransiente == 2) {
			tipoAtendimentoEnum = TipoAtendimentoEnum.INTERNACOES;
		}
		return tipoAtendimentoEnum;
	}

}
