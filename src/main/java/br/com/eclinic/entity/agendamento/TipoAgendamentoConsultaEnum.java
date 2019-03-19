package br.com.eclinic.entity.agendamento;

public enum TipoAgendamentoConsultaEnum {
	NOVA_CONSULTA (1, "Nova Consulta"),
	VOLTA (2,"Volta"),
	ENCAMINHAMENTO (3,"Encaminhamento"),
	PARECER (4,"Parecer");
	
	private int codigo;
	private String descricao;
	
	private TipoAgendamentoConsultaEnum(int codigo,String descricao){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

	public static TipoAgendamentoConsultaEnum buscarPorCodigo(int codigo) {
		TipoAgendamentoConsultaEnum retorno = TipoAgendamentoConsultaEnum.NOVA_CONSULTA;
		
		if(codigo == 1){
			retorno = TipoAgendamentoConsultaEnum.NOVA_CONSULTA;
		}
		if(codigo == 2){
			retorno = TipoAgendamentoConsultaEnum.VOLTA;
		}
		if(codigo == 3){
			retorno = TipoAgendamentoConsultaEnum.ENCAMINHAMENTO;
		}
		if(codigo == 4){
			retorno = TipoAgendamentoConsultaEnum.PARECER;
		}
		
		return retorno;
	}

}
