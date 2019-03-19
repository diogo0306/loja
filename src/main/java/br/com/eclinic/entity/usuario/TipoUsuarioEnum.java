package br.com.eclinic.entity.usuario;

public enum TipoUsuarioEnum {
	ADMIN ("ADMIN",1),
	OPERACIONAL ("OPERACIONAL",2),
	PACIENTE("PACIENTE", 3),
	MEDICO("MEDICO", 4),
	REPRESENTANTE("REPRESENTANTE", 5),
	CREDENCIADO("CREDENCIADO", 6);
	
	
	private int codigo;
	private String descricao;
	
	private TipoUsuarioEnum(String descricao,int codigo){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public int getCodigo(){
		return this.codigo;
	}
	
	public String getDescricao(){
		return this.descricao;
	}

}
