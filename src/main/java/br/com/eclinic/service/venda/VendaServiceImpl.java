package br.com.eclinic.service.venda;

import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.clienteLoja.ClienteLoja;

import br.com.eclinic.entity.produto.Produto;
import br.com.eclinic.entity.venda.Venda;
import br.com.eclinic.hibernate.clienteLoja.ClienteLojaRepository;
import br.com.eclinic.hibernate.produto.ProdutoRepository;
import br.com.eclinic.hibernate.venda.VendaRepository;
import br.com.eclinic.service.clienteLoja.ClienteLojaService;
import br.com.eclinic.service.produto.ProdutoService;

@Service(value = "vendaService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class VendaServiceImpl implements VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private ClienteLojaService clienteLojaService;

	@Autowired
	private ProdutoService produtoService;

	@Override
	public void salvar(Venda venda) {
		this.vendaRepository.salvar(venda);

	}

	@Override
	public void alterar(Venda venda) {
		this.vendaRepository.alterar(venda);

	}

	@Override
	public void excluir(Venda venda) {
		this.vendaRepository.excluir(venda);

	}

	@Override
	public Venda buscar(Long pk) {
		return this.vendaRepository.buscar(pk);
	}

	@Override
	public List<Venda> listar() {
		return this.vendaRepository.listar();
	}

	@Override
	public List<Venda> consultar(Venda venda) {
		return this.vendaRepository.consultar(venda);
	}

	@Override
	public List<Venda> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		return this.vendaRepository.consultarRelatorioPorPeriodo(dataInicial, dataFinal);
	}

	@Override
	public List<Venda> configurarGuiaRelatorio(List<Venda> lista) {
		for (Venda venda : lista) {

			if (venda.getClienteLoja() != null && venda.getClienteLoja().getId() != null) {
				ClienteLoja clienteLoja = clienteLojaService.buscar(venda.getClienteLoja().getId());
				venda.setClienteLoja(clienteLoja);
			}

			/*if (conta.getAutorizacao() != null && conta.getAutorizacao().getId() != null) {
				Autorizacao autorizacao = autorizacaoService.buscar(conta.getAutorizacao().getId());
				conta.setAutorizacao(autorizacao);
			}*/
			
			if (venda.getProduto() != null && venda.getProduto().getId() != null) {
				Produto produto = produtoService.buscar(venda.getProduto().getId());
				produto.setNome(produto.getNome().toUpperCase());
				venda.setProduto(produto);
			}
			/*if (conta.getDataPagamento() != null) {
				Format format = new SimpleDateFormat("dd/MM/yyyy");
				String data = format.format(conta.getDataPagamento());
				conta.setDataPagamentoFormatada(data);
			}
			if (conta.getValor() != null) {
				String valorPago = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(conta.getValor().doubleValue());
				conta.setValorPagoTransiente(valorPago);
			}*/
		}

	return lista;
}

}
