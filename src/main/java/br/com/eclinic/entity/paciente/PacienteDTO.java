package br.com.eclinic.entity.paciente;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.contrato.CobrancaContrato;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.documentacao.Documentacao;
import br.com.eclinic.entity.endereco.Endereco;
import br.com.eclinic.entity.plano.Plano;

public class PacienteDTO {
	
	private Paciente paciente;
	private Dependente dependente;
	private CobrancaContrato cobrancaContrato;
	private CobrancaAvulsa cobrancaAvulsa;
	private Documentacao documentacao;
	private Endereco endereco;
	private Long id;
	private String nome;
	private String rg;
	private String cpf;
	private String sexo;
	private String telefone;
	private String codigoPacienteLegado;
	private Date dataNascimento;
	private String filiacaoMae;
	private String bairro;
	private String cidade;
	private String dataNascimentoFormatada;
	private Date dataCadastro;
	private String dataCadastroFormatada;
	private String planoDescricao;
	private List<Dependente> listaDependente;
	private List<CobrancaContrato> listaCobrancaContrato;
	private List<CobrancaAvulsa> listaCobrancaAvulsa;
	private Date data;
	private Date dataPaciente;
	private String sexoTransiente;
	private String sexoTransienteDependente;
	private Cliente cliente;
	private Contrato contrato;
	private Long idSolicitacao;
	private Plano plano;
	
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public Long getIdSolicitacao() {
		return idSolicitacao;
	}
	public void setIdSolicitacao(Long idSolicitacao) {
		this.idSolicitacao = idSolicitacao;
	}
	public Contrato getContrato() {
		return contrato;
	}
	public void setContrato(Contrato contrato) {
		this.contrato = contrato;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Documentacao getDocumentacao() {
		return documentacao;
	}
	public void setDocumentacao(Documentacao documentacao) {
		this.documentacao = documentacao;
	}
	public Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public List<Dependente> getListaDependente() {
		return listaDependente;
	}
	public void setListaDependente(List<Dependente> listaDependente) {
		this.listaDependente = listaDependente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getDataPaciente() {
		return dataPaciente;
	}
	public void setDataPaciente(Date dataPaciente) {
		this.dataPaciente = dataPaciente;
	}
	public String getSexoTransiente() {
		return sexoTransiente;
	}
	public void setSexoTransiente(String sexoTransiente) {
		this.sexoTransiente = sexoTransiente;
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
	public String getPlanoDescricao() {
		return planoDescricao;
	}
	public void setPlanoDescricao(String planoDescricao) {
		this.planoDescricao = planoDescricao;
	}
	
	public Date getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public String getDataCadastroFormatada() {
		if(this.dataCadastro != null){
			GregorianCalendar dataCadastro = new GregorianCalendar();
			dataCadastro.setTime(this.dataCadastro);
			dataCadastroFormatada = dataCadastro.get(Calendar.DAY_OF_MONTH) + "/"
			+ (dataCadastro.get(Calendar.MONTH) + 1) + "/"
			+ dataCadastro.get(Calendar.YEAR);
		}
		return dataCadastroFormatada;
	}
	public void setDataCadastroFormatada(String dataCadastroFormatada) {
		this.dataCadastroFormatada = dataCadastroFormatada;
	}
	public String getDataNascimentoFormatada() {
		if(this.dataNascimento != null){
			GregorianCalendar dataNascimento = new GregorianCalendar();
			dataNascimento.setTime(this.dataNascimento);
			dataNascimentoFormatada = dataNascimento.get(Calendar.DAY_OF_MONTH) + "/"
			+ (dataNascimento.get(Calendar.MONTH) + 1) + "/"
			+ dataNascimento.get(Calendar.YEAR);
		}
		return dataNascimentoFormatada;
	}
	public void setDataNascimentoFormatada(String dataNascimentoFormatada) {
		this.dataNascimentoFormatada = dataNascimentoFormatada;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getFiliacaoMae() {
		return filiacaoMae;
	}
	public void setFiliacaoMae(String filiacaoMae) {
		this.filiacaoMae = filiacaoMae;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public String getCodigoPacienteLegado() {
		return codigoPacienteLegado;
	}
	public void setCodigoPacienteLegado(String codigoPacienteLegado) {
		this.codigoPacienteLegado = codigoPacienteLegado;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public CobrancaContrato getCobrancaContrato() {
		return cobrancaContrato;
	}
	public void setCobrancaContrato(CobrancaContrato cobrancaContrato) {
		this.cobrancaContrato = cobrancaContrato;
	}
	public List<CobrancaContrato> getListaCobrancaContrato() {
		return listaCobrancaContrato;
	}
	public void setListaCobrancaContrato(List<CobrancaContrato> listaCobrancaContrato) {
		this.listaCobrancaContrato = listaCobrancaContrato;
	}
	public CobrancaAvulsa getCobrancaAvulsa() {
		return cobrancaAvulsa;
	}
	public void setCobrancaAvulsa(CobrancaAvulsa cobrancaAvulsa) {
		this.cobrancaAvulsa = cobrancaAvulsa;
	}
	public List<CobrancaAvulsa> getListaCobrancaAvulsa() {
		return listaCobrancaAvulsa;
	}
	public void setListaCobrancaAvulsa(List<CobrancaAvulsa> listaCobrancaAvulsa) {
		this.listaCobrancaAvulsa = listaCobrancaAvulsa;
	}
	
	

	
}
