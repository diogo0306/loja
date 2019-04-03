package br.com.eclinic.entity.usuario;

import br.com.eclinic.entity.usuarioLoja.TipoUsuarioLojaEnum;

public enum TipoUsuarioEnum {
	ADMIN("ADMIN", 1), OPERACIONAL("OPERACIONAL", 2), PACIENTE("PACIENTE", 3), MEDICO("MEDICO", 4),
	REPRESENTANTE("REPRESENTANTE", 5), CREDENCIADO("CREDENCIADO", 6);

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
		if (codigo == 3) {
			tipoUsuarioEnum = tipoUsuarioEnum.PACIENTE;
		}
		if (codigo == 4) {
			tipoUsuarioEnum = tipoUsuarioEnum.MEDICO;
		}
		if (codigo == 5) {
			tipoUsuarioEnum = tipoUsuarioEnum.REPRESENTANTE;
		}
		if (codigo == 6) {
			tipoUsuarioEnum = tipoUsuarioEnum.CREDENCIADO;
		}

		return tipoUsuarioEnum;
	}

}
