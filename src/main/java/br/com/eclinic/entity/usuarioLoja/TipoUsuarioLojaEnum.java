package br.com.eclinic.entity.usuarioLoja;

public enum TipoUsuarioLojaEnum {

	ADMINISTRADOR(1, "ADMINISTRADOR"), VENDEDOR(2, "VENDEDOR");

	private int codigo;
	private String descricao;

	private TipoUsuarioLojaEnum(int codigo, String descricao) {
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
	public static TipoUsuarioLojaEnum getEnumPorCodigo(Integer codigo) {

		TipoUsuarioLojaEnum tipoUsuarioLojaEnum = null;

		if (codigo == 1) {
			tipoUsuarioLojaEnum = tipoUsuarioLojaEnum.ADMINISTRADOR;
		}
		if (codigo == 2) {
			tipoUsuarioLojaEnum = tipoUsuarioLojaEnum.VENDEDOR;
		}

		return tipoUsuarioLojaEnum;
	}

}
