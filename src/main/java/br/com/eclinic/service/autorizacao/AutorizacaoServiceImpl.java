package br.com.eclinic.service.autorizacao;

import java.math.BigDecimal;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.entity.autorizacao.Autorizacao;
import br.com.eclinic.entity.autorizacao.AutorizacaoDTO;
import br.com.eclinic.entity.autorizacao.TipoAutorizacaoEnum;
import br.com.eclinic.entity.credenciado.Credenciado;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.fornecedor.Fornecedor;
import br.com.eclinic.entity.hospital.Hospital;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.autorizacao.AutorizacaoRepository;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.fornecedor.FornecedorService;
import br.com.eclinic.service.hospital.HospitalService;
import br.com.eclinic.service.paciente.PacienteService;

@Service(value = "autorizacaoService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AutorizacaoServiceImpl implements AutorizacaoService {

	@Autowired
	private AutorizacaoRepository autorizacaoRepository;
	@Autowired
	private ExameService exameService;
	@Autowired
	private CredenciadoService credenciadoService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private HospitalService hospitalService;
	@Autowired
	private FornecedorService fornecedorService;

	@Override
	public void salvar(Autorizacao autorizacao) {
		this.autorizacaoRepository.salvar(autorizacao);
	}

	@Override
	public void alterar(Autorizacao autorizacao) {
		this.autorizacaoRepository.alterar(autorizacao);
	}

	@Override
	public void excluir(Autorizacao autorizacao) {
		this.autorizacaoRepository.excluir(autorizacao);
	}

	@Override
	public Autorizacao buscar(Long pk) {
		return this.autorizacaoRepository.buscar(pk);
	}

	@Override
	public List<Autorizacao> listar() {
		return this.autorizacaoRepository.listar();
	}

	@Override
	public List<Autorizacao> consultar(Autorizacao autorizacao) {
		return this.autorizacaoRepository.consultar(autorizacao);
	}

	@Override
	public AutorizacaoDTO configurarForm(AutorizacaoDTO autorizacaoDTO, HttpServletRequest request) {

		Credenciado credenciado = new Credenciado();

		if (autorizacaoDTO.getAutorizacao().getCredenciado() != null
				&& autorizacaoDTO.getAutorizacao().getCredenciado().getId() != null) {
			credenciado = credenciadoService.buscar(autorizacaoDTO.getAutorizacao().getCredenciado().getId());
		}

		if (autorizacaoDTO.getAutorizacao().getCodicoTipoTransiente() != null
				&& autorizacaoDTO.getAutorizacao().getCodicoTipoTransiente() != "") {
			autorizacaoDTO.getAutorizacao().setTipo(TipoAutorizacaoEnum
					.getEnumPorCodigo(Integer.parseInt(autorizacaoDTO.getAutorizacao().getCodicoTipoTransiente())));
		}

		if (autorizacaoDTO.getAutorizacao().getTipo().equals(TipoAutorizacaoEnum.CONSULTA)) {
			autorizacaoDTO.getAutorizacao().setHospital(null);
			autorizacaoDTO.getAutorizacao().setFornecedor(null);
			if (credenciado != null && credenciado.getValorCobrado() != null) {
				autorizacaoDTO.getAutorizacao().setValor(credenciado.getValorCobrado());
			}
			if (autorizacaoDTO.getAutorizacao().getValorPagoFormatado() != null
					&& autorizacaoDTO.getAutorizacao().getValorPagoFormatado() != null) {
				autorizacaoDTO.getAutorizacao().setValorPago(new BigDecimal(
						autorizacaoDTO.getAutorizacao().getValorPagoFormatado().replace(".", "").replace(",", ".")));
			}
		}

		if (autorizacaoDTO.getAutorizacao().getTipo().equals(TipoAutorizacaoEnum.SP_SADP)) {
			if (autorizacaoDTO.getAutorizacao().getValorPagoSalaFormatado() != null
					&& autorizacaoDTO.getAutorizacao().getValorPagoSalaFormatado() != "") {
				autorizacaoDTO.getAutorizacao().setValorPagoSala(new BigDecimal(autorizacaoDTO.getAutorizacao()
						.getValorPagoSalaFormatado().replace(".", "").replace(",", ".")));
			}
			autorizacaoDTO.getAutorizacao().setFornecedor(null);
			autorizacaoDTO.getAutorizacao().setValor(autorizacaoDTO.getValorTotal());
			autorizacaoDTO.getAutorizacao().setValorPago(autorizacaoDTO.getValorTotal());
		}

		if (autorizacaoDTO.getAutorizacao().getTipo().equals(TipoAutorizacaoEnum.CIRURGIA)) {
			BigDecimal valorTotal = new BigDecimal(0);
			Exame exame = new Exame();
			if (autorizacaoDTO.getAutorizacao().getValorPagoSalaFormatado() != null
					&& autorizacaoDTO.getAutorizacao().getValorPagoSalaFormatado() != "") {
				autorizacaoDTO.getAutorizacao().setValorPagoSala(new BigDecimal(autorizacaoDTO.getAutorizacao()
						.getValorPagoSalaFormatado().replace(".", "").replace(",", ".")));
			}
			if (autorizacaoDTO.getAutorizacao().getValorPagoMaterialFormatado() != null
					&& autorizacaoDTO.getAutorizacao().getValorPagoMaterialFormatado() != "") {
				autorizacaoDTO.getAutorizacao().setValorPagoMaterial(new BigDecimal(autorizacaoDTO.getAutorizacao()
						.getValorPagoMaterialFormatado().replace(".", "").replace(",", ".")));
			}
			if (autorizacaoDTO.getExame() != null && autorizacaoDTO.getExame().getId() != null) {
				exame = exameService.buscar(autorizacaoDTO.getExame().getId());
				autorizacaoDTO.getAutorizacao().setExame(exame);
				autorizacaoDTO.getAutorizacao().setValor(exame.getValor());
			}

			valorTotal = autorizacaoDTO.getAutorizacao().getValor()
					.add(autorizacaoDTO.getAutorizacao().getValorPagoSala())
					.add(autorizacaoDTO.getAutorizacao().getValorPagoMaterial());
			autorizacaoDTO.getAutorizacao().setValorPago(valorTotal);
		}

		gerarNumeroAleatorio(autorizacaoDTO.getAutorizacao());

		return autorizacaoDTO;
	}

	public void gerarNumeroAleatorio(Autorizacao autorizacao) {
		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}
		Collections.shuffle(numeros);
		Random random = new Random();
		int x = random.nextInt(10001);
		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.CONSULTA)) {
			autorizacao.setNumeroAutorizacao("CON" + numeros.get(x).toString());
		}
		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.SP_SADP)) {
			autorizacao.setNumeroAutorizacao("PRO" + numeros.get(x).toString());
		}
		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.CIRURGIA)) {
			autorizacao.setNumeroAutorizacao("CIR" + numeros.get(x).toString());
		}
	}

	@Override
	public AutorizacaoDTO configurarExame(AutorizacaoDTO autorizacaoDTO, HttpServletRequest request) {

		List<Exame> lista = autorizacaoDTO.getExames();
		BigDecimal valorTotal = new BigDecimal(0);

		if (autorizacaoDTO.getValorTotal() != null) {
			valorTotal = autorizacaoDTO.getValorTotal();
		}

		if (lista == null) {
			lista = new ArrayList<Exame>();
		}

		if (autorizacaoDTO.getExame() != null && autorizacaoDTO.getExame().getId() != null) {
			Exame exame = exameService.buscar(autorizacaoDTO.getExame().getId());
			valorTotal = valorTotal.add(exame.getValor());
			autorizacaoDTO.setValorTotal(valorTotal);
			String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotal.doubleValue());
			autorizacaoDTO.setValorTotalFormatado(valor);
			lista.add(exame);
		}

		autorizacaoDTO.setExames(lista);
		autorizacaoDTO.setExame(null);

		return autorizacaoDTO;
	}

	@Override
	public List<Autorizacao> listarAutorizacaoPorPaciente(Long id) {
		return this.autorizacaoRepository.listarAutorizacaoPorPaciente(id);
	}

	@Override
	public Autorizacao configurarFormDetalhar(Autorizacao autorizacao) throws NegocioException {

		Paciente paciente = new Paciente();
		Credenciado credenciado = new Credenciado();
		Hospital hospital = new Hospital();
		Fornecedor fornecedor = new Fornecedor();
		Exame exame = new Exame();

		if (autorizacao.getPaciente() != null && autorizacao.getPaciente().getId() != null) {
			paciente = pacienteService.buscar(autorizacao.getPaciente().getId());
			paciente.setConsultas(null);
			paciente.setAgendamentos(null);
			paciente.setDependentes(null);
			paciente.setContratos(null);
			paciente.setCobrancas(null);
			paciente.setCobrancasAvulsas(null);
			paciente.setReceitas(null);
			autorizacao.setPaciente(paciente);
		}

		if (autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
			credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());
			credenciado.setConsultas(null);
			if (credenciado.getValorCobrado() != null) {
				String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(credenciado.getValorCobrado().doubleValue());
				credenciado.setValorCobradoTransiente(valor);
			}
			autorizacao.setCredenciado(credenciado);
		}

		if (autorizacao.getDataAutorizacao() != null) {
			Format format = new SimpleDateFormat("dd/MM/yyyy");
			String data = format.format(autorizacao.getDataAutorizacao());
			autorizacao.setDataFormatada(data);
		}

		if (autorizacao.getValorPago() != null) {
			String valorPago = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(autorizacao.getValorPago().doubleValue());
			autorizacao.setValorPagoFormatado(valorPago);
		}

		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.CONSULTA)) {
			autorizacao.setPaciente(paciente);
			autorizacao.setCredenciado(credenciado);
		}

		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.SP_SADP)) {
			
			BigDecimal valorTotal = new BigDecimal(0);
			
			if (autorizacao.getHospital() != null && autorizacao.getHospital().getId() != null) {
				hospital = hospitalService.buscar(autorizacao.getHospital().getId());
				if (hospital.getValorCobrado() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
							.format(hospital.getValorCobrado().doubleValue());
					hospital.setValorTransiente(valor);
				}
				autorizacao.setHospital(hospital);
			}
			if (autorizacao.getValorPagoSala() != null) {
				String valorSala = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(autorizacao.getValorPagoSala().doubleValue());
				autorizacao.setValorPagoSalaFormatado(valorSala);
			}
			
			List<Exame> exames = autorizacaoRepository.listarExamesPorAutorizacao(autorizacao.getId());
			if(exames != null) {
				for(Exame exa : exames) {
					if(exa.getValor() != null) {
						String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(exa.getValor().doubleValue());
						exa.setValorTransiente(valor);
					}
					valorTotal = valorTotal.add(exa.getValor());
				}
				
				String total = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(valorTotal.doubleValue());			
				autorizacao.setValorTotalFormatado(total);
				autorizacao.setExames(exames);
			}
			
		}

		if (autorizacao.getTipo().equals(TipoAutorizacaoEnum.CIRURGIA)) {
			if (autorizacao.getExame() != null && autorizacao.getExame().getId() != null) {
				exame = exameService.buscar(autorizacao.getExame().getId());
				autorizacao.setExame(exame);
			}
			if (autorizacao.getHospital() != null && autorizacao.getHospital().getId() != null) {
				hospital = hospitalService.buscar(autorizacao.getHospital().getId());
				if (hospital.getValorCobrado() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
							.format(hospital.getValorCobrado().doubleValue());
					hospital.setValorTransiente(valor);
				}
				autorizacao.setHospital(hospital);
			}
			if (autorizacao.getFornecedor() != null && autorizacao.getFornecedor().getId() != null) {
				fornecedor = fornecedorService.buscar(autorizacao.getFornecedor().getId());
				if (fornecedor.getValorCobrado() != null) {
					String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
							.format(fornecedor.getValorCobrado().doubleValue());
					fornecedor.setValorTransiente(valor);
				}
				autorizacao.setFornecedor(fornecedor);
			}
			if (autorizacao.getValorPagoSala() != null) {
				String valorSala = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(autorizacao.getValorPagoSala().doubleValue());
				autorizacao.setValorPagoSalaFormatado(valorSala);
			}
			if (autorizacao.getValorPagoMaterial() != null) {
				String valorMaterial = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
						.format(autorizacao.getValorPagoMaterial().doubleValue());
				autorizacao.setValorPagoMaterialFormatado(valorMaterial);
			}
		}

		return autorizacao;
	}

	@Override
	public List<Exame> listarExamesPorAutorizacao(Long id) {
		return this.autorizacaoRepository.listarExamesPorAutorizacao(id);
	}

	@Override
	public Autorizacao configurarGuiaConsulta(Autorizacao autorizacao) throws NegocioException {
		
		Paciente paciente = new Paciente();
		Credenciado credenciado = new Credenciado();
		
		if (autorizacao.getPaciente() != null && autorizacao.getPaciente().getId() != null) {
			paciente = pacienteService.buscar(autorizacao.getPaciente().getId());
			paciente.setNome(paciente.getNome().toUpperCase());
			paciente.setConsultas(null);
			paciente.setAgendamentos(null);
			paciente.setDependentes(null);
			paciente.setContratos(null);
			paciente.setCobrancas(null);
			paciente.setCobrancasAvulsas(null);
			paciente.setReceitas(null);
			autorizacao.setPaciente(paciente);
		}

		if (autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
			credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());
			credenciado.setNome(credenciado.getNome().toUpperCase());
			credenciado.setConsultas(null);
			autorizacao.setCredenciado(credenciado);
		}
		
		if(autorizacao.getDataAutorizacao() != null) {
			Format format = new SimpleDateFormat("dd/MM/yyyy");
			String data = format.format(autorizacao.getDataAutorizacao());
			autorizacao.setDataFormatada(data);
		}
		
		autorizacao.setNomeSolicitante(autorizacao.getNomeSolicitante().toUpperCase());
		
		return autorizacao;
	}

	@Override
	public Autorizacao configurarGuiaProc(Autorizacao autorizacao) throws NegocioException {
		
		Paciente paciente = new Paciente();
		Credenciado credenciado = new Credenciado();
		Hospital hospital = new Hospital();
		
		if (autorizacao.getPaciente() != null && autorizacao.getPaciente().getId() != null) {
			paciente = pacienteService.buscar(autorizacao.getPaciente().getId());
			paciente.setNome(paciente.getNome().toUpperCase());
			paciente.setConsultas(null);
			paciente.setAgendamentos(null);
			paciente.setDependentes(null);
			paciente.setContratos(null);
			paciente.setCobrancas(null);
			paciente.setCobrancasAvulsas(null);
			paciente.setReceitas(null);
			autorizacao.setPaciente(paciente);
		}

		if (autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
			credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());
			credenciado.setNome(credenciado.getNome().toUpperCase());
			credenciado.setConsultas(null);
			autorizacao.setCredenciado(credenciado);
		}
		
		if(autorizacao.getDataAutorizacao() != null) {
			Format format = new SimpleDateFormat("dd/MM/yyyy");
			String data = format.format(autorizacao.getDataAutorizacao());
			autorizacao.setDataFormatada(data);
		}
		
		if (autorizacao.getHospital() != null && autorizacao.getHospital().getId() != null) {
			hospital = hospitalService.buscar(autorizacao.getHospital().getId());
			hospital.setNome(hospital.getNome());
			autorizacao.setHospital(hospital);
		}
		
		if (autorizacao.getValorPagoSala() != null) {
			String valorSala = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(autorizacao.getValorPagoSala().doubleValue());
			autorizacao.setValorPagoSalaFormatado(valorSala);
		}
		
		List<Exame> exames = autorizacaoRepository.listarExamesPorAutorizacao(autorizacao.getId());
		if(exames != null) {
			for(Exame exa : exames) {
				exa.setNome(exa.getNome().toUpperCase());
			}
			autorizacao.setExames(exames);
		}
		
		autorizacao.setNomeSolicitante(autorizacao.getNomeSolicitante().toUpperCase());
		
		return autorizacao;
	}

	@Override
	public Autorizacao configurarGuiaCirurgia(Autorizacao autorizacao) throws NegocioException {
		
		Paciente paciente = new Paciente();
		Credenciado credenciado = new Credenciado();
		Hospital hospital = new Hospital();
		Fornecedor fornecedor = new Fornecedor();
		Exame exame = new Exame();
		
		if (autorizacao.getPaciente() != null && autorizacao.getPaciente().getId() != null) {
			paciente = pacienteService.buscar(autorizacao.getPaciente().getId());
			paciente.setNome(paciente.getNome().toUpperCase());
			paciente.setConsultas(null);
			paciente.setAgendamentos(null);
			paciente.setDependentes(null);
			paciente.setContratos(null);
			paciente.setCobrancas(null);
			paciente.setCobrancasAvulsas(null);
			paciente.setReceitas(null);
			autorizacao.setPaciente(paciente);
		}

		if (autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
			credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());
			credenciado.setNome(credenciado.getNome().toUpperCase());
			credenciado.setConsultas(null);
			autorizacao.setCredenciado(credenciado);
		}
		
		if(autorizacao.getDataAutorizacao() != null) {
			Format format = new SimpleDateFormat("dd/MM/yyyy");
			String data = format.format(autorizacao.getDataAutorizacao());
			autorizacao.setDataFormatada(data);
		}
		
		if (autorizacao.getExame() != null && autorizacao.getExame().getId() != null) {
			exame = exameService.buscar(autorizacao.getExame().getId());
			exame.setNome(exame.getNome().toUpperCase());
			String valor = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(exame.getValor().doubleValue());
			exame.setValorTransiente(valor);
			autorizacao.setExame(exame);
		}
		if (autorizacao.getHospital() != null && autorizacao.getHospital().getId() != null) {
			hospital = hospitalService.buscar(autorizacao.getHospital().getId());
			hospital.setNome(hospital.getNome());
			autorizacao.setHospital(hospital);
		}
		if (autorizacao.getFornecedor() != null && autorizacao.getFornecedor().getId() != null) {
			fornecedor = fornecedorService.buscar(autorizacao.getFornecedor().getId());
			fornecedor.setNome(fornecedor.getNome().toUpperCase());
			autorizacao.setFornecedor(fornecedor);
		}
		if (autorizacao.getValorPagoSala() != null) {
			String valorSala = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(autorizacao.getValorPagoSala().doubleValue());
			autorizacao.setValorPagoSalaFormatado(valorSala);
		}
		if (autorizacao.getValorPagoMaterial() != null) {
			String valorMaterial = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"))
					.format(autorizacao.getValorPagoMaterial().doubleValue());
			autorizacao.setValorPagoMaterialFormatado(valorMaterial);
		}
		
		autorizacao.setNomeSolicitante(autorizacao.getNomeSolicitante().toUpperCase());
		
		return autorizacao;
	}

	@Override
	public List<Autorizacao> consultarRelatorioAutorizacoes(Date dataInicial, Date dataFinal) {
		return this.autorizacaoRepository.consultarRelatorioAutorizacoes(dataInicial, dataFinal);
	}

	@Override
	public List<Autorizacao> configurarGuiaRelatorio(List<Autorizacao> lista) {
		
		for(Autorizacao autorizacao : lista) {
			if(autorizacao.getCredenciado() != null && autorizacao.getCredenciado().getId() != null) {
				Credenciado credenciado = credenciadoService.buscar(autorizacao.getCredenciado().getId());
				credenciado.setNome(credenciado.getNome().toUpperCase());
				credenciado.setConsultas(null);
				autorizacao.setCredenciado(credenciado);
			}
			if(autorizacao.getDataHoraExecucao() != null) {
				Format format = new SimpleDateFormat("dd/MM/yyyy");
				String data = format.format(autorizacao.getDataHoraExecucao());
				autorizacao.setDataExecucaoFormatada(data);
			}
			if(autorizacao.getValorPago() != null) {
				String valorPago = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(autorizacao.getValorPago().doubleValue());
				autorizacao.setValorPagoFormatado(valorPago);
			}
		}
		
		return lista;
	}

}
