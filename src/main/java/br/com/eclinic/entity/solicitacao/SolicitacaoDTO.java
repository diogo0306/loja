package br.com.eclinic.entity.solicitacao;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.representante.Representante;
import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.entity.supervisor.Supervisor;

public class SolicitacaoDTO {

	private Solicitacao solicitacao;
	private SolicitacaoExame solicitacaoExame;
	private SolicitacaoConsulta solicitacaoConsulta;
	private Especialidade especialidade;
	private Agenda agenda;
	private Jornada jornada;
	private Paciente paciente;
	private Dependente dependente;
	private Medico medico;
	private Supervisor supervisor;
	private Responsavel responsavel;
	private Representante representante;
	private List<Dependente> listaDependente;
	private List<Responsavel> listaResponsavel;
	private Date data;
	private Date dataContratacao;
	private Date dataPaciente;
	private String sexoTransiente;
	private String sexoTransienteDependente;
	private Boolean aprovado;
	private Boolean reprovado;
	private Exame exame;
	private Credenciado credenciado;
	private String dataExameFormatada;
	private String dataContratacaoFormatada;
	private String dataNascimentoFormatada;
	private String valorFormatado;
	private String nomePaciente;
	private String solicitacaoEnum;
	private String cpf;
	private String observacoes;
	private String orientacoes;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSolicitacaoEnum() {
		return solicitacaoEnum;
	}

	public void setSolicitacaoEnum(String solicitacaoEnum) {
		this.solicitacaoEnum = solicitacaoEnum;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getValorFormatado() {
		return valorFormatado;
	}

	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}

	public String getDataContratacaoFormatada() {
		return dataContratacaoFormatada;
	}

	public void setDataContratacaoFormatada(String dataContratacaoFormatada) {
		this.dataContratacaoFormatada = dataContratacaoFormatada;
	}

	public String getDataNascimentoFormatada() {
		return dataNascimentoFormatada;
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public String getDataExameFormatada() {
		return dataExameFormatada;
	}

	public void setDataExameFormatada(String dataExameFormatada) {
		this.dataExameFormatada = dataExameFormatada;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Credenciado getCredenciado() {
		return credenciado;
	}

	public void setCredenciado(Credenciado credenciado) {
		this.credenciado = credenciado;
	}

	public SolicitacaoConsulta getSolicitacaoConsulta() {
		return solicitacaoConsulta;
	}

	public void setSolicitacaoConsulta(SolicitacaoConsulta solicitacaoConsulta) {
		this.solicitacaoConsulta = solicitacaoConsulta;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Jornada getJornada() {
		return jornada;
	}

	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}

	public SolicitacaoExame getSolicitacaoExame() {
		return solicitacaoExame;
	}

	public void setSolicitacaoExame(SolicitacaoExame solicitacaoExame) {
		this.solicitacaoExame = solicitacaoExame;
	}

	public Supervisor getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Supervisor supervisor) {
		this.supervisor = supervisor;
	}

	public Responsavel getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public Representante getRepresentante() {
		return representante;
	}

	public void setRepresentante(Representante representante) {
		this.representante = representante;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}

	public void setListaResponsavel(List<Responsavel> listaResponsavel) {
		this.listaResponsavel = listaResponsavel;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Date getDataPaciente() {
		return dataPaciente;
	}

	public void setDataPaciente(Date dataPaciente) {
		this.dataPaciente = dataPaciente;
	}

	public Boolean isReprovado() {
		return reprovado;
	}

	public Boolean isAprovado() {
		return aprovado;
	}

	public Boolean getAprovado() {
		return aprovado;
	}

	public void setAprovado(Boolean aprovado) {
		this.aprovado = aprovado;
	}

	public Boolean getReprovado() {
		return reprovado;
	}

	public void setReprovado(Boolean reprovado) {
		this.reprovado = reprovado;
	}

	public String getSexoTransienteDependente() {
		return sexoTransienteDependente;
	}

	public void setSexoTransienteDependente(String sexoTransienteDependente) {
		this.sexoTransienteDependente = sexoTransienteDependente;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Dependente getDependente() {
		return dependente;
	}

	public void setDependente(Dependente dependente) {
		this.dependente = dependente;
	}

	public List<Dependente> getListaDependente() {
		return listaDependente;
	}

	public void setListaDependente(List<Dependente> listaDependente) {
		this.listaDependente = listaDependente;
	}

	public String getSexoTransiente() {
		return sexoTransiente;
	}

	public void setSexoTransiente(String sexoTransiente) {
		this.sexoTransiente = sexoTransiente;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getOrientacoes() {
		return orientacoes;
	}

	public void setOrientacoes(String orientacoes) {
		this.orientacoes = orientacoes;
	}

}
