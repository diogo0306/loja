package br.com.eclinic.entity.agendamento;

public enum TipoAgendamentoEnum {
	PRESENCIAL (1, "Presencial"),
	TELEFONE (2,"Telefone"),
	SISTEMA (3,"Sistema"),
	WEB (4, "Web");
	
	private int codigo;
	private String descricao;
	
	private TipoAgendamentoEnum(int codigo,String descricao){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

	public static TipoAgendamentoEnum buscarPorCodigo(int codigo) {
		TipoAgendamentoEnum retorno = TipoAgendamentoEnum.PRESENCIAL;
		
		if(codigo == 1){
			retorno = TipoAgendamentoEnum.PRESENCIAL;
		}
		if(codigo == 2){
			retorno = TipoAgendamentoEnum.TELEFONE;
		}	
		if(codigo == 3){
			retorno = TipoAgendamentoEnum.SISTEMA;
		}
		if(codigo == 4){
			retorno = TipoAgendamentoEnum.WEB;
		}
		
		return retorno;
	}

}
