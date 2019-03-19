package br.com.eclinic.entity.fornecedor;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "fornecedor")
@SequenceGenerator(name = "sequenceFornecedor", sequenceName = "for_id_seq")
public class Fornecedor implements Serializable {

	private static final long serialVersionUID = -2504340702794977541L;
	private Long id;
	private String nome;
	private BigDecimal valorCobrado;
	private String valorTransiente;
	private String codigo;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "for_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@NotEmpty(message = "Insira o nome do fornecedor.")
	@NotBlank
	@Column(name = "for_nome")
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Column(name = "for_valor_cobrado")
	public BigDecimal getValorCobrado() {
		return valorCobrado;
	}
	public void setValorCobrado(BigDecimal valorCobrado) {
		this.valorCobrado = valorCobrado;
	}
	
	@Transient
	public String getValorTransiente() {
		return valorTransiente;
	}
	public void setValorTransiente(String valorTransiente) {
		this.valorTransiente = valorTransiente;
	}
	
	@Column(name = "for_codigo")
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
