package br.com.eclinic.entity.consulta;

public enum MotivoEnum {
	
	ADMISSIONAL(1, "Admissional"), DEMISSIONAL(2, "Demissional"), PERIODICO(3, "Periódico"),
	MUDANCA_DE_FUNCAO(4, "Mudança de Função"), RETORNO_AO_TRABALHO(5, "Retorno"), AVALIACAO_CLINICA(6, "Avaliação Clínica");
	
	private int codigo;
	private String descricao;
	
	private MotivoEnum(int codigo, String descricao){
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

	public static MotivoEnum getEnumPorCodigo (Integer codigo){
		
		MotivoEnum motivoEnum = null;
		
		if(codigo == 1){
			motivoEnum = MotivoEnum.ADMISSIONAL;
		}
		if(codigo == 2){
			motivoEnum = MotivoEnum.DEMISSIONAL;
		}
		if(codigo == 3){
			motivoEnum = MotivoEnum.PERIODICO;
		}
		if(codigo == 4){
			motivoEnum = MotivoEnum.MUDANCA_DE_FUNCAO;
		}
		if(codigo == 5){
			motivoEnum = MotivoEnum.RETORNO_AO_TRABALHO;
		}
		if(codigo == 6){
			motivoEnum = MotivoEnum.AVALIACAO_CLINICA;
		}
		
		return motivoEnum;
	}
	
}
