package br.com.eclinic.service.contas;

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

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.contas.ContasReceber;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.hibernate.contas.ContasReceberRepository;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.credenciado.CredenciadoService;

@Service(value = "contasReceberService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContasReceberServiceImpl implements ContasReceberService {

	@Autowired
	private ContasReceberRepository contasReceberRepository;
	@Autowired
	private AutorizacaoService autorizacaoService;
	@Autowired
	private CredenciadoService credenciadoService;

	@Override
	public void salvar(ContasReceber contasReceber) {
		this.contasReceberRepository.salvar(contasReceber);
	}

	@Override
	public void alterar(ContasReceber contasReceber) {
		this.contasReceberRepository.alterar(contasReceber);
	}

	@Override
	public void excluir(ContasReceber contasReceber) {
		this.contasReceberRepository.excluir(contasReceber);
	}

	@Override
	public ContasReceber buscar(Long pk) {
		return this.contasReceberRepository.buscar(pk);
	}

	@Override
	public List<ContasReceber> listar() {
		return this.contasReceberRepository.listar();
	}

	@Override
	public List<ContasReceber> consultar(ContasReceber contasReceber) {
		return this.contasReceberRepository.consultar(contasReceber);
	}

	@Override
	public List<ContasReceber> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		return this.contasReceberRepository.consultarRelatorioPorPeriodo(dataInicial, dataFinal);
	}

	@Override
	public List<ContasReceber> configurarGuiaRelatorio(List<ContasReceber> lista) {
		
		for(ContasReceber conta : lista) {
			if(conta.getAutorizacao() != null && conta.getAutorizacao().getId() != null) {
				Autorizacao autorizacao = autorizacaoService.buscar(conta.getAutorizacao().getId());
				conta.setAutorizacao(autorizacao);
			}
			if(conta.getCredenciado() != null && conta.getCredenciado().getId() != null) {
				Credenciado credenciado = credenciadoService.buscar(conta.getCredenciado().getId());
				credenciado.setNome(credenciado.getNome().toUpperCase());
				credenciado.setConsultas(null);
				conta.setCredenciado(credenciado);
			}
			if(conta.getDataPagamento() != null) {
				Format format = new SimpleDateFormat("dd/MM/yyyy");
				String data = format.format(conta.getDataPagamento());
				conta.setDataPagamentoFormatada(data);
			}
			if(conta.getValor() != null) {
				String valorPago = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(conta.getValor().doubleValue());
				conta.setValorPagoTransiente(valorPago);
			}
		}
		
		return lista;
	}

}
