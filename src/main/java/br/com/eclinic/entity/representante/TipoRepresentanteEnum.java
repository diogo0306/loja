package br.com.eclinic.entity.representante;

import br.com.eclinic.validator.Group.CnpjGroup;
import br.com.eclinic.validator.Group.CpfGroup;

public enum TipoRepresentanteEnum {
	
	PESSOA_FISICA(1, "Pessoa Física", CpfGroup.class), PESSOA_JURIDICA(2, "Pessoa Jurídica", CnpjGroup.class);
	
	private int codigo;
	private String descricao;
	private Class <?> grupo;

	private TipoRepresentanteEnum(int codigo, String descricao, Class <?> grupo) {
		this.codigo = codigo;
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

	public void setGrupo(Class<?> grupo) {
		this.grupo = grupo;
	}

	@SuppressWarnings("static-access")
	public static TipoRepresentanteEnum getEnumPorCodigo(Integer codigo) {

		TipoRepresentanteEnum tipoRepresentanteEnum = null;

		if (codigo == 1) {
			tipoRepresentanteEnum = tipoRepresentanteEnum.PESSOA_FISICA;
		}
		
		if (codigo == 2) {
			tipoRepresentanteEnum = tipoRepresentanteEnum.PESSOA_JURIDICA;
		}
		
		return tipoRepresentanteEnum;
		
		}

}
