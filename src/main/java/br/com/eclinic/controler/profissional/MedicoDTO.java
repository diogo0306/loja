package br.com.eclinic.controler.profissional;

import java.util.Date;
import java.util.List;

import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.medico.Medico;
import br.com.eclinic.entity.supervisor.Responsavel;

public class MedicoDTO {
	
	private Medico medico;
	private Responsavel responsavel;
	private List<Responsavel> listaResponsavel;
	private Date data;
	private Date dataNascimento;
	private Date dataContratacao;
	private Date inicioJornada;
	private Date fimJornada;
	private String valorFormatado;
	private Agenda agenda;
	private Jornada jornada;
	private Especialidade especialidade;
	private List<Jornada> listaJornada;
	private String dataContratacaoFormatada;
	private String dataNascimentoFormatada;
	
	
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
	public String getValorFormatado() {
		return valorFormatado;
	}
	public void setValorFormatado(String valorFormatado) {
		this.valorFormatado = valorFormatado;
	}
	public Especialidade getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	public Date getInicioJornada() {
		return inicioJornada;
	}
	public void setInicioJornada(Date inicioJornada) {
		this.inicioJornada = inicioJornada;
	}
	public Date getFimJornada() {
		return fimJornada;
	}
	public void setFimJornada(Date fimJornada) {
		this.fimJornada = fimJornada;
	}
	public Jornada getJornada() {
		return jornada;
	}
	public void setJornada(Jornada jornada) {
		this.jornada = jornada;
	}
	public List<Jornada> getListaJornada() {
		return listaJornada;
	}
	public void setListaJornada(List<Jornada> listaJornada) {
		this.listaJornada = listaJornada;
	}
	public Agenda getAgenda() {
		return agenda;
	}
	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public Responsavel getResponsavel() {
		return responsavel;
	}
	public void setResponsavel(Responsavel responsavel) {
		this.responsavel = responsavel;
	}

	public List<Responsavel> getListaResponsavel() {
		return listaResponsavel;
	}
	public void setListaResponsavel(List<Responsavel> listaResponsavel) {
		this.listaResponsavel = listaResponsavel;
	}

	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getDataContratacao() {
		return dataContratacao;
	}
	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	

}
