package br.com.eclinic.entity.produto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "produto")
@SequenceGenerator(name = "sequenceContrato", sequenceName = "produto_id_seq")
public class Produto implements Serializable {

	private static final long serialVersionUID = 4306308813837307806L;
	private Long id;
	private String codigo;
	private String nome;
	private TipoProdutoEnum tipoProdutoEnum;
	private String codigoTipoProdutoTransiente;
	private BigDecimal precoCusto;
	private String precoCustoTransiente;
	private BigDecimal precoVenda;
	private String precoVendaTransiente;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "prod_codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "prod_nome")
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "prod_tipo_enum")
	@Enumerated(EnumType.STRING)
	public TipoProdutoEnum getTipoProdutoEnum() {
		return tipoProdutoEnum;
	}

	public void setTipoProdutoEnum(TipoProdutoEnum tipoProdutoEnum) {
		this.tipoProdutoEnum = tipoProdutoEnum;
	}

	@Transient
	public String getCodigoTipoProdutoTransiente() {
		return codigoTipoProdutoTransiente;
	}

	public void setCodigoTipoProdutoTransiente(String codigoTipoProdutoTransiente) {
		this.codigoTipoProdutoTransiente = codigoTipoProdutoTransiente;
	}

	@Column(name = "prod_preco_custo")
	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	@Transient
	public String getPrecoCustoTransiente() {
		return precoCustoTransiente;
	}

	public void setPrecoCustoTransiente(String precoCustoTransiente) {
		this.precoCustoTransiente = precoCustoTransiente;
	}

	@Column(name = "prod_preco_venda")
	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	@Transient
	public String getPrecoVendaTransiente() {
		return precoVendaTransiente;
	}

	public void setPrecoVendaTransiente(String precoVendaTransiente) {
		this.precoVendaTransiente = precoVendaTransiente;
	}

}
