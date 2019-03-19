package br.com.eclinic.entity.usuario;

public enum FuncionalidadesEnum {
	PRE_CADASTRO_COORDENADOR ("coordenadores",1),
	CADASTRO_CLIENTE ("clientes",2),
	CADASTRO_POSTE ("postes",3),
	CADASTRO_TECNICO ("tecnicos",4),
	CADASTRO_PRESTADORA ("prestadoras",5),
	CADASTRO_COMPONENTE ("componentes",6),
	ORDEM_DE_SERVICO ("ordensservico",7),
	BAIXA_ORDEM_DE_SERVICO ("baixaordensservico",8),
	VISUALIZAR_POSTE ("controladores",10),
	VISUALIZAR_VEICULO ("veiculos_rastreamento",11),
	CADASTRO_USUARIO ("usuarios",12),
	CADASTRO_FABRICANTE ("fabricantes",2),
	CADASTRO_VEICULO ("veiculos",2),
	AGENDAMENTO ("agendamentos",2),
	RELATORIOS ("Operacional",2);
	
	private int codigo;
	private String descricao;
	
	private FuncionalidadesEnum(String descricao,int codigo){
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
