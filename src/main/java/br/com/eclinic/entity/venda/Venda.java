package br.com.eclinic.entity.venda;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.eclinic.entity.clienteLoja.ClienteLoja;
import br.com.eclinic.entity.produto.Produto;

@Entity
@Table(name = "venda")
@SequenceGenerator(name = "sequenceVenda", sequenceName = "venda_id_seq")
public class Venda implements Serializable {

	private static final long serialVersionUID = 6286852298739405812L;
	private Long id;
	private String codigo;
	private Produto produto;
	/* private List<Produto> produtos; */
	private ClienteLoja clienteLoja;
	private BigDecimal valorVenda;
	private String valorVendaTransiente;
	private Date dataVenda;
	private String dataVendaFormatada;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ven_id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "ven_valor_venda")
	public BigDecimal getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(BigDecimal valorVenda) {
		this.valorVenda = valorVenda;
	}

	@Transient
	public String getValorVendaTransiente() {
		return valorVendaTransiente;
	}

	public void setValorVendaTransiente(String valorVendaTransiente) {
		this.valorVendaTransiente = valorVendaTransiente;
	}

	@Column(name = "ven_codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/*
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name = "prod_id", nullable = false)
	 * 
	 * @Cascade(value = { org.hibernate.annotations.CascadeType.ALL }) public
	 * List<Produto> getProdutos() { return produtos; }
	 * 
	 * public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
	 */

	@OneToOne
	@JoinColumn(name = "clienteLoja_id")
	public ClienteLoja getClienteLoja() {
		return clienteLoja;
	}

	public void setClienteLoja(ClienteLoja clienteLoja) {
		this.clienteLoja = clienteLoja;
	}

	@OneToOne
	@JoinColumn(name = "prod_id")
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "ven_data_venda")
	public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	@Transient
	public String getDataVendaFormatada() {

		SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy");
		dataVendaFormatada = formatada.format(dataVenda);
		return dataVendaFormatada;
	}

	public void setDataVendaFormatada(String dataVendaFormatada) {
		this.dataVendaFormatada = dataVendaFormatada;
	}

}
