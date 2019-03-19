package br.com.eclinic.entity.paciente;

public enum TipoControleEnum {

	PLANO("Plano", 1), CONTRATO("Contrato", 2);
	
	private String descricao;
	private int codigo;
	
	private TipoControleEnum(String descricao, int codigo) {
		this.descricao = descricao;
		this.codigo = codigo;
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
	
	public static TipoControleEnum getEnumPorCodigo (Integer codigo) {
		
		TipoControleEnum tipoControleEnum = null;
		
		if(codigo == 1) {
			tipoControleEnum = TipoControleEnum.PLANO;
		}
		
		if(codigo == 2) {
			tipoControleEnum = TipoControleEnum.CONTRATO;
		}
		
		return tipoControleEnum;
	}
}
