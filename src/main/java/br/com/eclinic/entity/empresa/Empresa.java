
package br.com.eclinic.entity.empresa;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.enuns.SituacaoEnum;
import br.com.eclinic.entity.enuns.TipoPessoaEnum;
import br.com.eclinic.entity.medico.UfEnum;

@Entity
@Table(name = "empresa")
@SequenceGenerator(name = "sequenceEmpresa", sequenceName = "empresa_id_seq")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -4290901370105338789L;

	private Long id;
	private String nome;
	private String cnpj;
	private String inscriestadual;
	private String inscrimunicipal;
	private String contato;
	private String email;
	private String contratoanterior;
	private String nomefantasia;
	private String cidade;
	private String bairro;
	private String logradouro;
	private String cep;
	private String numero;
	private String complemento;
	private String telefone;
	private String celular;
	private String telefoneSecundario;
	private String ramal;
	private String ramalSecundario;
	private Date dataadesao;
	private String refreajuste;
	private String valorlimitebenificiario;
	private Boolean boletoindividual;
	private Boolean buscarvaloresdaultimavigencia;
	private Boolean gerarcobranca;
	private Boolean incluircoparticipacao;
	private Boolean incluirprorata;

	private Cliente cliente;
	private TipoPessoaEnum tipopessoa;
	private SituacaoEnum situacaoEnum;
	private UfEnum ufEnum;
	
	private String dataFormatada;
	
	

	private List<Contrato> contratos;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "emp_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "emp_nome", nullable = false)
	@NotEmpty
	@NotBlank(message = "Nome é obrigatório.")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@CNPJ(message = "CNPJ Inválido.")
	@Column(name = "emp_cnpj")
	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	@Column(name = "emp_insc_estadual")
	public String getInscriestadual() {
		return inscriestadual;
	}

	public void setInscriestadual(String inscriestadual) {
		this.inscriestadual = inscriestadual;
	}

	@Column(name = "emp_insc_municipal")
	public String getInscrimunicipal() {
		return inscrimunicipal;
	}

	public void setInscrimunicipal(String inscrimunicipal) {
		this.inscrimunicipal = inscrimunicipal;
	}

	@Column(name = "emp_contato")
	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	@Column(name = "emp_email")
	@Email(message = "Informe um e-mail valido.")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "emp_contrato_anterior")
	public String getContratoanterior() {
		return contratoanterior;
	}

	public void setContratoanterior(String contratoanterior) {
		this.contratoanterior = contratoanterior;
	}

	@Column(name = "emp_nome_fantasia")
	public String getNomefantasia() {
		return nomefantasia;
	}

	public void setNomefantasia(String nomefantasia) {
		this.nomefantasia = nomefantasia;
	}

	@Column(name = "emp_cidade")
	@NotBlank(message = "Cidade é obrigatório.")
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	@Column(name = "emp_bairro")
	@NotBlank(message = "CBairro é obrigatório.")
	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	@Column(name = "emp_logadouro")
	@NotBlank(message = "Endereço é obrigatório.")
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	@Column(name = "emp_cep")
	@NotBlank(message = "CEP é obrigatório.")
	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	@Column(name = "emp_numero")
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	@Column(name = "emp_complemento")
	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	@Column(name = "emp_telefone")
	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Column(name = "emp_celular")
	@NotBlank(message = "Celular é obrigatório.")
	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	@Column(name = "emp_telefone_secundario")
	public String getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(String telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}

	@Column(name = "emp_ramal")
	public String getRamal() {
		return ramal;
	}

	public void setRamal(String ramal) {
		this.ramal = ramal;
	}

	@Column(name = "emp_ramal_secundario")
	public String getRamalSecundario() {
		return ramalSecundario;
	}

	public void setRamalSecundario(String ramalSecundario) {
		this.ramalSecundario = ramalSecundario;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "emp_data_adesao")
	public Date getDataadesao() {
		return dataadesao;
	}

	public void setDataadesao(Date dataadesao) {
		this.dataadesao = dataadesao;
	} 
	
	@Column(name = "emp_referencia_reajuste")
	public String getRefreajuste() {
		return refreajuste;
	}

	public void setRefreajuste(String refreajuste) {
		this.refreajuste = refreajuste;
	}

	@Column(name = "emp_valor_limite_benificiario")
	public String getValorlimitebenificiario() {
		return valorlimitebenificiario;
	}

	public void setValorlimitebenificiario(String valorlimitebenificiario) {
		this.valorlimitebenificiario = valorlimitebenificiario;
	}

	@Column(name = "emp_boleto_individual")
	public Boolean getBoletoindividual() {
		return boletoindividual;
	}

	public void setBoletoindividual(Boolean boletoindividual) {
		this.boletoindividual = boletoindividual;
	}

	@Column(name = "emp_buscar_valores_ultimavigencia")
	public Boolean getBuscarvaloresdaultimavigencia() {
		return buscarvaloresdaultimavigencia;
	}

	public void setBuscarvaloresdaultimavigencia(Boolean buscarvaloresdaultimavigencia) {
		this.buscarvaloresdaultimavigencia = buscarvaloresdaultimavigencia;
	}

	@Column(name = "emp_gerar_cobranca")
	public Boolean getGerarcobranca() {
		return gerarcobranca;
	}

	public void setGerarcobranca(Boolean gerarcobranca) {
		this.gerarcobranca = gerarcobranca;
	}

	@Column(name = "emp_incluir_copartitipacao")
	public Boolean getIncluircoparticipacao() {
		return incluircoparticipacao;
	}

	public void setIncluircoparticipacao(Boolean incluircoparticipacao) {
		this.incluircoparticipacao = incluircoparticipacao;
	}

	@Column(name = "emp_incluir_pro_rata")
	public Boolean getIncluirprorata() {
		return incluirprorata;
	}

	public void setIncluirprorata(Boolean incluirprorata) {
		this.incluirprorata = incluirprorata;
	}

	@Column(name = "emp_situacao")
	@Enumerated(EnumType.STRING)
	public SituacaoEnum getSituacaoEnum() {
		return situacaoEnum;
	}
	
	public void setSituacaoEnum(SituacaoEnum situacaoEnum) {
		this.situacaoEnum = situacaoEnum;
	}

	@Column(name = "emp_tipo_pessoa")
	@Enumerated(EnumType.STRING)
	public TipoPessoaEnum getTipopessoa() {
		return tipopessoa;
	}

	public void setTipopessoa(TipoPessoaEnum tipopessoa) {
		this.tipopessoa = tipopessoa;
	}

	@Column(name = "emp_estado")
	@Enumerated(EnumType.STRING)
	public UfEnum getUfEnum() {
		return ufEnum;
	}

	public void setUfEnum(UfEnum ufEnum) {
		this.ufEnum = ufEnum;
	}


	@OneToMany(mappedBy = "empresa")
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Contrato> getContratos() {
		return contratos;
	}

	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cli_id", nullable = true)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Transient
	public String getDataFormatada() {
		SimpleDateFormat formatado = new SimpleDateFormat("dd/MM/yyyy");
        dataFormatada = formatado.format(dataadesao);
        return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

}
