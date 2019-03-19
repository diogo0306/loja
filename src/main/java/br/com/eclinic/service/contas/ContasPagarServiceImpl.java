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
import br.com.eclinic.entity.contas.ContasPagar;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.hibernate.contas.ContasPagarRepository;
import br.com.eclinic.service.autorizacao.AutorizacaoService;
import br.com.eclinic.service.credenciado.CredenciadoService;

@Service(value = "contasPagarService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class ContasPagarServiceImpl implements ContasPagarService {
	
	@Autowired
	private ContasPagarRepository contasPagarRepository;
	@Autowired
	private AutorizacaoService autorizacaoService;
	@Autowired
	private CredenciadoService credenciadoService;

	@Override
	public void salvar(ContasPagar contasPagar) {
		this.contasPagarRepository.salvar(contasPagar);
	}

	@Override
	public void alterar(ContasPagar contasPagar) {
		this.contasPagarRepository.alterar(contasPagar);
	}

	@Override
	public void excluir(ContasPagar contasPagar) {
		this.contasPagarRepository.excluir(contasPagar);
	}

	@Override
	public ContasPagar buscar(Long pk) {
		return this.contasPagarRepository.buscar(pk);
	}

	@Override
	public List<ContasPagar> listar() {
		return this.contasPagarRepository.listar();
	}

	@Override
	public List<ContasPagar> consultar(ContasPagar contasPagar) {
		return this.contasPagarRepository.consultar(contasPagar);
	}

	@Override
	public List<ContasPagar> consultarRelatorioPorPeriodo(Date dataInicial, Date dataFinal) {
		return this.contasPagarRepository.consultarRelatorioPorPeriodo(dataInicial, dataFinal);
	}

	@Override
	public List<ContasPagar> configurarGuiaRelatorio(List<ContasPagar> lista) {
		
		for(ContasPagar conta : lista) {
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
			if(conta.getDataRealizado() != null) {
				Format format = new SimpleDateFormat("dd/MM/yyyy");
				String data = format.format(conta.getDataRealizado());
				conta.setDataFormatada(data);
			}
			if(conta.getValor() != null) {
				String valorPago = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(conta.getValor().doubleValue());
				conta.setValorFormatado(valorPago);
			}
		}
		
		return lista;
	}

}
