package br.com.eclinic.entity.agendamento;

public enum TurnoAgendamentoEnum {
	
	MANHA (1, "Manhã"),
	TARDE (2,"Tarde"),
	NOITE (3,"Noite");
	
	private int codigo;
	private String descricao;
	
	private TurnoAgendamentoEnum(int codigo,String descricao){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

	public static TurnoAgendamentoEnum buscarPorCodigo(int codigo) {
		TurnoAgendamentoEnum retorno = TurnoAgendamentoEnum.MANHA;
		
		if(codigo == 1){
			retorno = TurnoAgendamentoEnum.MANHA;
		}
		if(codigo == 2){
			retorno = TurnoAgendamentoEnum.TARDE;
		}
		if(codigo == 3){
			retorno = TurnoAgendamentoEnum.NOITE;
		}
		return retorno;
	}

}
