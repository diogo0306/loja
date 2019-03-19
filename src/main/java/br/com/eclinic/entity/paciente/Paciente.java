package br.com.eclinic.entity.paciente;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.receita.Receita;

@Entity
@Table(name = "paciente")
@SequenceGenerator(name = "sequencePaciente", sequenceName = "paciente_id_seq")
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String matricula;
	private Date dataInclusao;
	private Date dataNascimento;
	private String dataNascimentoFormatada;
	private String idade;
	private String rg;
	private String cpf;
	private String filiacaoMae;
	private String filiacaoPai;
	private SexoEnum sexoEnum;
	private String codigoSexoTransiente;
	private Cliente cliente;
	private List<Consulta> consultas;
	private List<Agendamento> agendamentos;
	private EstadoCivilEnum estadoCivilEnum;
	private List<Receita> receitas;
	private Date dataUltimaConsulta;
	private String dataUltimaConsultaFormatada;
	private String codigoPacienteLegado;
	private String dataInclusaoFormatada;
	private String dataInclusaoFinalFormatada;
	private List<Dependente> dependentes;
	private Endereco endereco;
	private Documentacao documentacao;
	private Cobranca dadosCobranca;
	private List<Contrato> contratos;
	private List<CobrancaContrato> cobrancas;
	private List<CobrancaAvulsa> cobrancasAvulsas;
/*	private Contrato contrato;
*/
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "doc_id", nullable = false)
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	public Documentacao getDocumentacao() {
		return documentacao;
	}

	public void setDocumentacao(Documentacao documentacao) {
		this.documentacao = documentacao;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "end_id", nullable = false)
	@Cascade(value = { org.hibernate.annotations.CascadeType.ALL })
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@OneToMany(mappedBy = "pacienteVinculo")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Dependente> getDependentes() {
		return dependentes;
	}

	public void setDependentes(List<Dependente> dependentes) {
		this.dependentes = dependentes;
	}

	@OneToMany(mappedBy = "pacienteVinculo")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<CobrancaContrato> getCobrancas() {
		return cobrancas;
	}

	public void setCobrancas(List<CobrancaContrato> cobrancas) {
		this.cobrancas = cobrancas;
	}

	@OneToMany(mappedBy = "pacienteVinculo")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<CobrancaAvulsa> getCobrancasAvulsas() {
		return cobrancasAvulsas;
	}

	public void setCobrancasAvulsas(List<CobrancaAvulsa> cobrancasAvulsas) {
		this.cobrancasAvulsas = cobrancasAvulsas;
	}

	@Transient
	public String getDataUltimaConsultaFormatada() {
		if (this.dataUltimaConsulta != null) {
			GregorianCalendar dataUltimaConsulta = new GregorianCalendar();
			dataUltimaConsulta.setTime(this.dataUltimaConsulta);
			dataUltimaConsultaFormatada = dataUltimaConsulta.get(Calendar.DAY_OF_MONTH) + "/"
					+ (dataUltimaConsulta.get(Calendar.MONTH) + 1) + "/" + dataUltimaConsulta.get(Calendar.YEAR);
		}
		return dataUltimaConsultaFormatada;

	}

	public void setDataUltimaConsultaFormatada(String dataUltimaConsultaFormatada) {
		this.dataUltimaConsultaFormatada = dataUltimaConsultaFormatada;
	}

	@Transient
	public String getDataInclusaoFinalFormatada() {
		return dataInclusaoFinalFormatada;
	}

	public void setDataInclusaoFinalFormatada(String dataInclusaoFinalFormatada) {
		this.dataInclusaoFinalFormatada = dataInclusaoFinalFormatada;
	}

	@Transient
	public String getDataInclusaoFormatada() {
		return dataInclusaoFormatada;
	}

	public void setDataInclusaoFormatada(String dataInclusaoFormatada) {
		this.dataInclusaoFormatada = dataInclusaoFormatada;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pac_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "pac_nome", nullable = true)
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "pac_data_inclusao")
	public Date getDataInclusao() {
		return dataInclusao;
	}

	public void setDataInclusao(Date dataInclusao) {
		this.dataInclusao = dataInclusao;
	}

	@Column(name = "pac_data_nascimento", nullable = true)
	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Column(name = "pac_rg", nullable = true)
	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	@Column(name = "pac_cpf", nullable = true)
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToMany(mappedBy = "paciente", orphanRemoval = false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Consulta> getConsultas() {
		return consultas;
	}

	public void setConsultas(List<Consulta> consultas) {
		this.consultas = consultas;
	}

	@OneToMany(mappedBy = "paciente", orphanRemoval = false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}

	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}

	@Column(name = "pac_sexo", nullable = false)
	@Enumerated(EnumType.STRING)
	public SexoEnum getSexoEnum() {
		return sexoEnum;
	}

	public void setSexoEnum(SexoEnum sexoEnum) {
		this.sexoEnum = sexoEnum;
	}

	@Transient
	public String getCodigoSexoTransiente() {
		return codigoSexoTransiente;
	}

	public void setCodigoSexoTransiente(String codigoSexoEnum) {
		this.codigoSexoTransiente = codigoSexoEnum;
	}

	@Transient
	public String getDataNascimentoFormatada() {
		if (this.dataNascimento != null) {
			GregorianCalendar dataNascimento = new GregorianCalendar();
			dataNascimento.setTime(this.dataNascimento);
			dataNascimentoFormatada = dataNascimento.get(Calendar.DAY_OF_MONTH) + "/"
					+ (dataNascimento.get(Calendar.MONTH) + 1) + "/" + dataNascimento.get(Calendar.YEAR);
		}
		return dataNascimentoFormatada;
	}

	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}

	@Column(name = "pac_estado_civil")
	@Enumerated(EnumType.STRING)
	public EstadoCivilEnum getEstadoCivilEnum() {
		return estadoCivilEnum;
	}

	public void setEstadoCivilEnum(EstadoCivilEnum estadoCivilEnum) {
		this.estadoCivilEnum = estadoCivilEnum;
	}

	@OneToMany(mappedBy = "paciente", orphanRemoval = false)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Receita> getReceitas() {
		return receitas;
	}

	public void setReceitas(List<Receita> receitas) {
		this.receitas = receitas;
	}

	@Column(name = "pac_data_ultima_consulta")
	public Date getDataUltimaConsulta() {
		return dataUltimaConsulta;
	}

	public void setDataUltimaConsulta(Date dataUltimaConsulta) {
		this.dataUltimaConsulta = dataUltimaConsulta;
	}

	@Column(name = "pac_codigo_paciente_legado")
	public String getCodigoPacienteLegado() {
		return codigoPacienteLegado;
	}

	public void setCodigoPacienteLegado(String codigoPacienteLegado) {
		this.codigoPacienteLegado = codigoPacienteLegado;
	}


	@Column(name = "pac_idade")
	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	@Column(name = "pac_filiacao_mae")
	public String getFiliacaoMae() {
		return filiacaoMae;
	}

	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}

	@Column(name = "pac_filiacao_pai")
	public String getFiliacaoPai() {
		return filiacaoPai;
	}

	public void setFiliacaoPai(String filiacaoPai) {
		this.filiacaoPai = filiacaoPai;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cob_id")
	public Cobranca getDadosCobranca() {
		return dadosCobranca;
	}

	public void setDadosCobranca(Cobranca dadosCobranca) {
		this.dadosCobranca = dadosCobranca;
	}

	@OneToMany(mappedBy = "paciente")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}

	@Column(name = "pac_matricula")
	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/*@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "con_id")
	public Contrato getContrato() {
		return contrato;
	}

	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	
	*/

	
}
