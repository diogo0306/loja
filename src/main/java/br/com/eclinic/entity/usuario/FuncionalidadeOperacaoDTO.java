package br.com.eclinic.entity.usuario;

public class FuncionalidadeOperacaoDTO {
	
	private Long id;
	private String idExcluir;
	private Perfil perfil;
	private Funcionalidade funcionalidade;
	private boolean inserir;
	private boolean alterar;
	private boolean visualizar;
	private boolean excluir;
	public boolean isInserir() {
		return inserir;
	}
	public void setInserir(boolean inserir) {
		this.inserir = inserir;
	}
	public boolean isAlterar() {
		return alterar;
	}
	public void setAlterar(boolean alterar) {
		this.alterar = alterar;
	}
	public boolean isVisualizar() {
		return visualizar;
	}
	public void setVisualizar(boolean visualizar) {
		this.visualizar = visualizar;
	}
	public boolean isExcluir() {
		return excluir;
	}
	public void setExcluir(boolean excluir) {
		this.excluir = excluir;
	}
	public Funcionalidade getFuncionalidade() {
		return funcionalidade;
	}
	public void setFuncionalidade(Funcionalidade funcionalidade) {
		this.funcionalidade = funcionalidade;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIdExcluir() {
		return idExcluir;
	}
	public void setIdExcluir(String idExcluir) {
		this.idExcluir = idExcluir;
	}
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

}
