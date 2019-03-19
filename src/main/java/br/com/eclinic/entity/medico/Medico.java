package br.com.eclinic.entity.medico;

import br.com.eclinic.entity.agenda.Agenda;
import br.com.eclinic.entity.agendamento.Agendamento;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.consulta.Consulta;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.especialidade.Especialidade;
import br.com.eclinic.entity.jornada.Jornada;
import br.com.eclinic.entity.supervisor.Responsavel;
import br.com.eclinic.saogabriel.api.entity.consultorio.Consultorio;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "medico")
@SequenceGenerator(name = "sequenceMedico", sequenceName = "medico_id_seq")
public class Medico implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String cpf;
    private String cnpj;
    private String rg;
    private String crm;
    private String telefone;
    private String celular;
    private String email;
    private String cidade;
    private String idade;
    private Date dataNascimento;
    private String dataNascimentoFormatada;
    private SexoEnum sexoEnum;
    private UfEnum ufEnum;
    private String bairro;
    private String logradouro;
    private String cep;
    private String numero;
    private String complemento;
    private String numeroContrato;
    private String situacaoContrato;
    private SituacaoEnum situacaoEnum;
    private TipoContratoEnum tipoContratoEnum;
    private String orgaoExpedidor;
    private Date dataContratacao;
    private String telefoneSecundario;
    private String ramal;
    private String ramalSecundario;
    private Cliente cliente;
    private List<Agendamento> agendamentos;
    private List<Consulta> consultas;

    private Responsavel responsavel;

    private List<Responsavel> responsaveis;
    private List<Jornada> jornadas;
    private BigDecimal duracao;
    private Agenda agenda;
    private Especialidade especialidade;
    private BigDecimal valorConsulta;
    private List<Consultorio> consultorio;

    private String dataContratacaoFormatada;
    private String codigoSexoTransiente;
    private String codigoOrgaoEmissorTransiente;
    private String codigoUfTransiente;
    private String codigoUfProfissionalTransiente;
    private String codigoRegiaoTransiente;
    private String tipoSituacaoTransiente;
    private String tipoContratoTransiente;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "age_id")
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Column(name = "med_duracao")
    public BigDecimal getDuracao() {
        return duracao;
    }

    public void setDuracao(BigDecimal duracao) {
        this.duracao = duracao;
    }

    @OneToMany(mappedBy = "medicoVinculo")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<Jornada> getJornadas() {
        return jornadas;
    }

    public void setJornadas(List<Jornada> jornadas) {
        this.jornadas = jornadas;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resp_id")
    public Responsavel getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Responsavel responsavel) {
        this.responsavel = responsavel;
    }

	@OneToMany(mappedBy = "medicoVinculo")
	@LazyCollection(LazyCollectionOption.TRUE)
	@Cascade(value = { org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
			org.hibernate.annotations.CascadeType.SAVE_UPDATE })
	public List<Responsavel> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Responsavel> responsaveis) {
		this.responsaveis = responsaveis;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "med_id", nullable = true)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank(message = "Nome é obrigatório.")
    @Column(name = "med_nome", nullable = true)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "med_cpf")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(name = "med_cnpj")
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Column(name = "med_rg")
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Column(name = "med_crm")
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    @Column(name = "med_telefone")
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Column(name = "med_celular")
    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    @Column(name = "med_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "med_numero_contrato")
    public String getNumeroContrato() {
        return numeroContrato;
    }

    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

    @Column(name = "med_situacao_contrato")
    public String getSituacaoContrato() {
        return situacaoContrato;
    }

    public void setSituacaoContrato(String situacaoContrato) {
        this.situacaoContrato = situacaoContrato;
    }

    @Column(name = "med_situacao")
    @Enumerated(EnumType.STRING)
    public SituacaoEnum getSituacaoEnum() {
        return situacaoEnum;
    }

    public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
        this.situacaoEnum = situacaoEnum;
    }

    @Column(name = "med_tipo")
    @Enumerated(EnumType.STRING)
    public TipoContratoEnum getTipoContratoEnum() {
        return tipoContratoEnum;
    }

    public void setTipoContratoEnum(TipoContratoEnum tipoContratoEnum) {
        this.tipoContratoEnum = tipoContratoEnum;
    }

    @Column(name = "med_cidade")
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(name = "med_sexo")
    @Enumerated(EnumType.STRING)
    public SexoEnum getSexoEnum() {
        return sexoEnum;
    }

    public void setSexoEnum(SexoEnum sexoEnum) {
        this.sexoEnum = sexoEnum;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "med_data_nascimento")
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cli_id")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @OneToMany(mappedBy = "medico")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<Agendamento> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
    }

    @OneToMany(mappedBy = "medico")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    @Transient
    public String getCodigoSexoTransiente() {
        return codigoSexoTransiente;
    }

    public void setCodigoSexoTransiente(String codigoSexoTransiente) {
        this.codigoSexoTransiente = codigoSexoTransiente;
    }

    @Transient
    public String getDataNascimentoFormatada() {
        SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        dataNascimentoFormatada = formatado.format(dataNascimento);
        return dataNascimentoFormatada;
    }

    public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
        this.dataNascimentoFormatada = dataNascimentoFormatada;
    }

    @Transient
    public String getCodigoOrgaoEmissorTransiente() {
        return codigoOrgaoEmissorTransiente;
    }

    public void setCodigoOrgaoEmissorTransiente(String codigoOrgaoEmissorTransiente) {
        this.codigoOrgaoEmissorTransiente = codigoOrgaoEmissorTransiente;
    }

    @Column(name = "med_uf")
    @Enumerated(EnumType.STRING)
    public UfEnum getUfEnum() {
        return ufEnum;
    }

    public void setUfEnum(UfEnum ufEnum) {
        this.ufEnum = ufEnum;
    }

    @Transient
    public String getCodigoUfTransiente() {
        return codigoUfTransiente;
    }

    public void setCodigoUfTransiente(String codigoUfTransiente) {
        this.codigoUfTransiente = codigoUfTransiente;
    }

    @Transient
    public String getCodigoRegiaoTransiente() {
        return codigoRegiaoTransiente;
    }

    public void setCodigoRegiaoTransiente(String codigoRegiaoTransiente) {
        this.codigoRegiaoTransiente = codigoRegiaoTransiente;
    }

    @Column(name = "med_idade")
    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    @Transient
    public String getCodigoUfProfissionalTransiente() {
        return codigoUfProfissionalTransiente;
    }

    public void setCodigoUfProfissionalTransiente(String codigoUfProfissionalTransiente) {
        this.codigoUfProfissionalTransiente = codigoUfProfissionalTransiente;
    }

    @Column(name = "med_bairro")
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(name = "med_logradouro")
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(name = "med_cep")
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @Column(name = "med_complemento")
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(name = "med_numero")
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Transient
    public String getTipoSituacaoTransiente() {
        return tipoSituacaoTransiente;
    }

    public void setTipoSituacaoTransiente(String tipoSituacaoTransiente) {
        this.tipoSituacaoTransiente = tipoSituacaoTransiente;
    }

    @Transient
    public String getTipoContratoTransiente() {
        return tipoContratoTransiente;
    }

    public void setTipoContratoTransiente(String tipoContratoTransiente) {
        this.tipoContratoTransiente = tipoContratoTransiente;
    }

    @Column(name = "med_orgao_expedidor")
    public String getOrgaoExpedidor() {
        return orgaoExpedidor;
    }

    public void setOrgaoExpedidor(String orgaoExpedidor) {
        this.orgaoExpedidor = orgaoExpedidor;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "med_data_contratacao")
    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    @Transient
    public String getDataContratacaoFormatada() {
        return dataContratacaoFormatada;
    }

    public void setDataContratacaoFormatada(String dataContratacaoFormatada) {
        this.dataContratacaoFormatada = dataContratacaoFormatada;
    }

    @Column(name = "med_telefone_secundario")
    public String getTelefoneSecundario() {
        return telefoneSecundario;
    }

    public void setTelefoneSecundario(String telefoneSecundario) {
        this.telefoneSecundario = telefoneSecundario;
    }

    @Column(name = "med_ramal")
    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    @Column(name = "med_ramal_secundario")
    public String getRamalSecundario() {
        return ramalSecundario;
    }

    public void setRamalSecundario(String ramalSecundario) {
        this.ramalSecundario = ramalSecundario;
    }

    @OneToMany(mappedBy = "medicos")
    @LazyCollection(LazyCollectionOption.TRUE)
    @Cascade(value = {org.hibernate.annotations.CascadeType.REMOVE, org.hibernate.annotations.CascadeType.ALL,
            org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    public List<Consultorio> getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(List<Consultorio> consultorio) {
        this.consultorio = consultorio;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "espec_id")
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Column(name = "med_valor_consulta")
    public BigDecimal getValorConsulta() {
        return valorConsulta;
    }

    public void setValorConsulta(BigDecimal valorConsulta) {
        this.valorConsulta = valorConsulta;
    }

}
