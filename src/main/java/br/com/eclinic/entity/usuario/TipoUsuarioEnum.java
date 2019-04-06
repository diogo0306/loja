package br.com.eclinic.entity.usuario;

public enum TipoUsuarioEnum {
	ADMIN("ADMIN", 1), OPERACIONAL("OPERACIONAL", 2);

	private int codigo;
	private String descricao;

	private TipoUsuarioEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	public int getCodigo() {
		return this.codigo;
	}

	public String getDescricao() {
		return this.descricao;
	}

	@SuppressWarnings("static-access")
	public static TipoUsuarioEnum getEnumPorCodigo(Integer codigo) {

		TipoUsuarioEnum tipoUsuarioEnum = null;

		if (codigo == 1) {
			tipoUsuarioEnum = tipoUsuarioEnum.ADMIN;
		}
		if (codigo == 2) {
			tipoUsuarioEnum = tipoUsuarioEnum.OPERACIONAL;
		}

		return tipoUsuarioEnum;
	}

}
