package br.com.eclinic.entity.parametrizacao;

public enum ControleBiometricoEnum {
	
	FINGKEY_HAMSTER(1, "Fingkey Hamster"), OUTROS(2, "Outros");
	
	
	private int codigo;
	private String descricao;

	private ControleBiometricoEnum(int codigo, String descricao) {
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
	public static ControleBiometricoEnum getEnumPorCodigo(Integer codigo) {

		ControleBiometricoEnum controleBiometricoEnum = null;

		if (codigo == 1) {
			controleBiometricoEnum = controleBiometricoEnum.FINGKEY_HAMSTER;
		}
		if (codigo == 2) {
			controleBiometricoEnum = controleBiometricoEnum.OUTROS;
		}
		
		return controleBiometricoEnum;
	}


}

