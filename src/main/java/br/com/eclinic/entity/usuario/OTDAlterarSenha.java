package br.com.eclinic.entity.usuario;

public class OTDAlterarSenha {

	private Long idUsuario;
	private String senhaAtual;
	private String senhaNova;
	private String confirmacaoSenhaNova;
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getSenhaAtual() {
		return senhaAtual;
	}
	public void setSenhaAtual(String senhaAtual) {
		this.senhaAtual = senhaAtual;
	}
	public String getSenhaNova() {
		return senhaNova;
	}
	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}
	public String getConfirmacaoSenhaNova() {
		return confirmacaoSenhaNova;
	}
	public void setConfirmacaoSenhaNova(String confirmacaoSenhaNova) {
		this.confirmacaoSenhaNova = confirmacaoSenhaNova;
	}
	
	
}
