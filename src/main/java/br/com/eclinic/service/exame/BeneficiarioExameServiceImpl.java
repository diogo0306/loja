package br.com.eclinic.service.exame;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.controler.exame.BeneficiarioDTO;
import br.com.eclinic.entity.exame.BeneficiarioExame;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.hibernate.exame.BeneficiarioExameRepository;

@Service(value = "beneficiarioExameService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class BeneficiarioExameServiceImpl implements BeneficiarioExameService {

	@Autowired
	private BeneficiarioExameRepository beneficiarioExameRepository;
	@Autowired
	private TabelaExameService tabelaExameService;
	
	@Override
	public void salvar(BeneficiarioExame beneficiarioExame) {
		this.beneficiarioExameRepository.salvar(beneficiarioExame);
	}

	@Override
	public BeneficiarioExame buscar(Long pk) {
		return this.beneficiarioExameRepository.buscar(pk);
	}

	@Override
	public void alterar(BeneficiarioExame beneficiarioExame) {
		this.beneficiarioExameRepository.alterar(beneficiarioExame);
	}

	@Override
	public List<BeneficiarioExame> listar() {
		return this.beneficiarioExameRepository.listar();
	}

	@Override
	public void excluir(BeneficiarioExame beneficiarioExame) {
		this.beneficiarioExameRepository.excluir(beneficiarioExame);
	}

	@Override
	public void configurarForm(BeneficiarioDTO beneficiarioDTO) {
		
		TabelaExame tabelaExame = new TabelaExame();
		List<TabelaExame> listaTabelaExame = beneficiarioDTO.getListaTabelaExame();
		List<TabelaExame> listaCorrente = new ArrayList<TabelaExame>();
		
		if(listaTabelaExame == null) {
			listaTabelaExame = new ArrayList<TabelaExame>();
		}
		
		if(beneficiarioDTO.getTabela().getId() != null && beneficiarioDTO.getExame().getId() != null) {
			tabelaExame = tabelaExameService.verificarPorExameTabela(beneficiarioDTO.getTabela(), beneficiarioDTO.getExame());
			listaTabelaExame.add(tabelaExame);
		}
		
		for(TabelaExame tabela : listaTabelaExame) {
			if(tabela.getValor() != null) {
				listaCorrente.add(tabela);
			}
		}
		
		beneficiarioDTO.setListaTabelaExame(listaCorrente);
		
		configurarTotalizadorValores(beneficiarioDTO);
		
	}
	
	public BigDecimal configurarTotalizadorValores (BeneficiarioDTO beneficiarioDTO) {
		
		BigDecimal total = new BigDecimal(0);
		BigDecimal valor = new BigDecimal(0);
		
		for(TabelaExame tabelaExame : beneficiarioDTO.getListaTabelaExame()) {
			if(tabelaExame.getValor() != null) {
				valor = tabelaExame.getValor();
				total = total.add(valor);
			}
		}
		
		if(total.compareTo(new BigDecimal(0)) > 0) {
			String valorFormatado = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(total.doubleValue());
			beneficiarioDTO.setValorTotalFormatado(valorFormatado);
		}
		beneficiarioDTO.setValorTotal(total);
		
		return total;
	}
	
	public void ajustarLista (BeneficiarioDTO beneficiarioDTO) {
		
		List<TabelaExame> listaCorrente = new ArrayList<TabelaExame>();
		
		for(TabelaExame tabela : beneficiarioDTO.getListaTabelaExame()) {
			if(tabela.getValor() != null) {
				listaCorrente.add(tabela);
			}
		}
		
		beneficiarioDTO.setListaTabelaExame(listaCorrente);
		
	}

	@Override
	public List<BeneficiarioExame> consultar(BeneficiarioExame beneficiarioExame) throws ParseException {
		return this.beneficiarioExameRepository.consultar(beneficiarioExame);
	}

}
