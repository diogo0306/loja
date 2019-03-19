package br.com.eclinic.entity.autorizacao;

public enum CanalEnum {
	
	APP(1, "App/Aplicativo"), CLINICA(2, "Clínica");

	private int codigo;
	private String descricao;

	private CanalEnum (int codigo, String descricao) {
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

	public static CanalEnum getEnumPorCodigo(Integer codigo) {

		CanalEnum canalEnum = null;

		if (codigo == 1) {
			canalEnum = CanalEnum.APP;
		}
		if (codigo == 2) {
			canalEnum = CanalEnum.CLINICA;
		}
	
		return canalEnum;
	}

}
