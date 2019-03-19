package br.com.eclinic.util;

import br.com.eclinic.validator.Group.CnpjGroup;
import br.com.eclinic.validator.Group.CpfGroup;

public enum PessoaEnum {

	FISICA(1, "Pessoa Física", CpfGroup.class), JURIDICA(2, "Pessoa Jurídica", CnpjGroup.class);

	private int codigo;
	private String descricao;
	private Class<?> grupo;

	PessoaEnum(int codigo, String descricao, Class<?> grupo) {
		this.descricao = descricao;
		this.grupo = grupo;
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

	public Class<?> getGrupo() {
		return grupo;
	}

	@SuppressWarnings("static-access")
	public static PessoaEnum getEnumPorCodigo(Integer codigo) {

		PessoaEnum PessoaEnum = null;

		if (codigo == 1) {
			PessoaEnum = PessoaEnum.FISICA;
		}
		if (codigo == 2) {
			PessoaEnum = PessoaEnum.JURIDICA;
		}

		return PessoaEnum;
	}

}
