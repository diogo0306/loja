package br.com.eclinic.entity.exame;

public enum TipoStatusEnum {
	
	PENDENTE(1, "Pendente"), PAGO(2, "Pago"), REALIZADO(3, "Realizado"), CANCELADO(4, "Cancelado");
	
	private int codigo;
	private String descricao;

	private TipoStatusEnum (int codigo, String descricao) {
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
	
	public static TipoStatusEnum getEnumPorCodigo (Integer codigo) {
		
		TipoStatusEnum tipoStatusEnum = null;
		
		if(codigo == 1) {
			tipoStatusEnum = TipoStatusEnum.PENDENTE;
		}
		
		if(codigo == 2) {
			tipoStatusEnum = TipoStatusEnum.PAGO;
		}
		
		if(codigo == 3) {
			tipoStatusEnum = TipoStatusEnum.REALIZADO;
		}
		
		if(codigo == 4) {
			tipoStatusEnum = TipoStatusEnum.CANCELADO;
		}
		
		return tipoStatusEnum;
	}
}
