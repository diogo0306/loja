package br.com.eclinic.entity.tabela;

public enum TipoBeneficiarioEnum {
	
	TITULAR(1, "Titular"), DEPENDENTE(2, "Dependente"), AGREGADO(3, "Agregado");
	
	private int codigo;
	private String descricao;
	
	private TipoBeneficiarioEnum (int codigo, String descricao) {
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
	
	public static TipoBeneficiarioEnum getEnumPorCodigo (Integer codigo) {
		
		TipoBeneficiarioEnum tipoBeneficiarioEnum = null;
		
		if(codigo == 1){
			tipoBeneficiarioEnum = TipoBeneficiarioEnum.TITULAR;
		}
		if(codigo == 2){
			tipoBeneficiarioEnum = TipoBeneficiarioEnum.DEPENDENTE;
		}
		if(codigo == 3){
			tipoBeneficiarioEnum = TipoBeneficiarioEnum.AGREGADO;
		}
		
		return tipoBeneficiarioEnum;
	}

}
