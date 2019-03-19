package br.com.eclinic.service.paciente;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.eclinic.controler.HomeController;
import br.com.eclinic.entity.cliente.Cliente;
import br.com.eclinic.entity.contrato.Contrato;
import br.com.eclinic.entity.contrato.DiaVencimentoEnum;
import br.com.eclinic.entity.contrato.TipoPessoaContratoEnum;
import br.com.eclinic.entity.dependente.Dependente;
import br.com.eclinic.entity.enuns.SituacaoContratoEnum;
import br.com.eclinic.entity.medico.OrgaoEmissorEnum;
import br.com.eclinic.entity.medico.SexoEnum;
import br.com.eclinic.entity.medico.UfEnum;
import br.com.eclinic.entity.paciente.CobrancaAvulsa;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.paciente.PacienteDTO;
import br.com.eclinic.entity.plano.Plano;
import br.com.eclinic.entity.receita.Medicamento;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.hibernate.medicamento.MedicamentoRepository;
import br.com.eclinic.hibernate.paciente.PacienteRepository;
import br.com.eclinic.hibernate.plano.PlanoRepository;

@Service(value = "pacienteService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class PacienteServiceImpl implements PacienteService {

	private static final String CODIGO = "codigo";
	private static final String MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS = "Paciente não existe mais na base de dados";

	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private MedicamentoRepository medicamentoRepository;
	@Autowired
	private PlanoRepository planoRepository;

	@Override
	public void salvar(Paciente paciente) {
		pacienteRepository.salvar(paciente);

	}

	@Override
	public void alterar(Paciente paciente) {
		pacienteRepository.alterar(paciente);

	}

	@Override
	public List<Paciente> listar() {
		return pacienteRepository.listar();
	}

	@Override
	public void excluir(Paciente paciente) throws NegocioException {
		Paciente pacienteBase = buscar(paciente.getId());
		if (pacienteBase == null) {
			throw new NegocioException(MEDICO_NAO_EXISTE_MAIS_NA_BASE_DE_DADOS);
		}
		pacienteRepository.excluir(pacienteBase);

	}

	@Override
	public Paciente buscar(Long pk) throws NegocioException {
		return pacienteRepository.buscar(pk);
	}

	@Override
	public List<Paciente> listarPorCliente(Cliente cliente) {
		return this.pacienteRepository.listarPorCliente(cliente);
	}

	@Override
	public List<PacienteDTO> listarPorClienteDTO(Cliente cliente) {
		return this.pacienteRepository.listarPorClienteDTO(cliente);
	}

	public PacienteRepository getPacienteRepository() {
		return pacienteRepository;
	}

	@Autowired
	public void setPacienteRepository(PacienteRepository pacienteRepository) {
		this.pacienteRepository = pacienteRepository;
	}

	@Override
	public List<Paciente> consultar(Paciente paciente) {
		return pacienteRepository.consultar(paciente);
	}

	@Override
	public List<PacienteDTO> consultarDTO(Paciente pacienteConsulta, Cliente cliente) {
		return pacienteRepository.consultarDTO(pacienteConsulta, cliente);
	}

	@Override
	public List<Medicamento> listarMedicamentos() {
		return medicamentoRepository.listarOrder(CODIGO);
	}

	@Autowired
	public void setMedicamentoRepository(MedicamentoRepository medicamentoRepository) {
		this.medicamentoRepository = medicamentoRepository;
	}

	@Override
	public Medicamento buscarMedicamento(Long id) {
		return medicamentoRepository.buscar(id);
	}

	@Override
	public String buscarMaiorCodigo() {
		return pacienteRepository.buscarMaiorCodigo();
	}

	@Override
	public Paciente consultarCpf(Paciente paciente) {
		return pacienteRepository.consultarCpf(paciente);
	}

	@Override
	public Paciente getPaciente(Long id) {
		return pacienteRepository.getPaciente(id);
	}

	@Override
	public PacienteDTO configurarFormBeneficiario(PacienteDTO pacienteDTO, HttpServletRequest request)
			throws ParseException {

		if (pacienteDTO.getPaciente().getCodigoSexoTransiente().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setCodigoSexoTransiente(null);
		}
		if (pacienteDTO.getPaciente().getDataNascimentoFormatada().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setDataNascimentoFormatada(null);
		}

		if (pacienteDTO.getPaciente().getDataNascimentoFormatada() != null) {
			configurarDataPaciente(pacienteDTO);
		}

		if (pacienteDTO.getPaciente().getCodigoSexoTransiente() != null) {
			pacienteDTO.getPaciente().setSexoEnum(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getCodigoSexoTransiente())));
		}

		if (pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != null
				&& pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != "") {
			pacienteDTO.getPaciente().getDocumentacao().setOrgaoEmissorEnum(OrgaoEmissorEnum.getEnumPorCodigo(
					Integer.parseInt(pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente())));
		}

		if (pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != null
				&& pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != "") {
			pacienteDTO.getPaciente().getEndereco().setEstado(UfEnum
					.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getEndereco().getEstadoTransiente())));
		}

		Paciente paciente = pacienteDTO.getPaciente();
		paciente.setDataNascimento(pacienteDTO.getDataPaciente());
		paciente.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		pacienteDTO.setPaciente(paciente);

		if (pacienteDTO.getContrato() != null) {

			Contrato contrato = pacienteDTO.getContrato();

			contrato.setSituacaoEnum(SituacaoContratoEnum.ATIVO);

			if (contrato.getCodigoTipoPessoaContratoTransiente() != null
					&& contrato.getCodigoTipoPessoaContratoTransiente() != "") {
				contrato.setTipoPessoaContratoEnum(TipoPessoaContratoEnum
						.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoTipoPessoaContratoTransiente())));
			}

			if (contrato.getCodigoDiaVencimentoTransiente() != null
					&& contrato.getCodigoDiaVencimentoTransiente() != "") {
				contrato.setDiaVencimentoEnum(DiaVencimentoEnum
						.getEnumPorCodigo(Integer.parseInt(contrato.getCodigoDiaVencimentoTransiente())));
			}

			if (contrato.getValorTaxaTransiente() != null && !contrato.getValorTaxaTransiente().isEmpty()) {
				contrato.setTaxa(new BigDecimal(contrato.getValorTaxaTransiente().replace(".", "").replace(",", ".")));
			}

			if (pacienteDTO.getPlano() != null && pacienteDTO.getPlano().getId() != null) {
				Plano plano = planoRepository.buscar(pacienteDTO.getPlano().getId());
				contrato.setValorContrato(plano.getValorPlano());
				contrato.setPlano(plano);
			}

			BigDecimal total = contrato.getValorContrato().add(contrato.getTaxa());
			contrato.setValorTotal(total);
			gerarNumeroAleatorio(contrato);
			contrato.setTipoPessoaContratoEnum(TipoPessoaContratoEnum.FISICA);
			contrato.setPaciente(paciente);

			pacienteDTO.setContrato(contrato);
		}

		return pacienteDTO;
	}

	private void configurarDataPaciente(PacienteDTO pacienteDTO) throws ParseException {
		Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy")
				.parse(pacienteDTO.getPaciente().getDataNascimentoFormatada());
		pacienteDTO.setDataPaciente(dataFormatada);
	}

	@Override
	public PacienteDTO configurarDependente(PacienteDTO pacienteDTO) throws ParseException {

		List<Dependente> listaDepentente = pacienteDTO.getListaDependente();

		if (listaDepentente == null) {
			listaDepentente = new ArrayList<Dependente>();
		}

		Dependente dependente = new Dependente();
		dependente.setNome(pacienteDTO.getDependente().getNome());
		dependente.setFiliacaoMae(pacienteDTO.getDependente().getFiliacaoMae());

		if (pacienteDTO.getDependente().getDataFormatada() != "") {
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(pacienteDTO.getDependente().getDataFormatada());
			dependente.setDataNascimento(data);
		}

		if (pacienteDTO.getDependente().getSexoTransiente() != null
				&& pacienteDTO.getDependente().getSexoTransiente() != "") {
			dependente.setSexo(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getDependente().getSexoTransiente())));

		}

		if(pacienteDTO.getDependente().getDataFormatada() != null && pacienteDTO.getDependente().getDataFormatada() != "") {
			Date data = new SimpleDateFormat("dd/MM/yyyy").parse(pacienteDTO.getDependente().getDataFormatada());
			dependente.setDataNascimento(data);
		}
		
		dependente.setCpf(pacienteDTO.getDependente().getCpf());
		dependente.setRg(pacienteDTO.getDependente().getRg());
		dependente.setOrgaoExpedidor(pacienteDTO.getDependente().getOrgaoExpedidor());

		listaDepentente.add(dependente);

		pacienteDTO.setListaDependente(listaDepentente);
		pacienteDTO.setDependente(null);

		return pacienteDTO;
	}
	
	@Override
	public PacienteDTO configurarCobrancaAvulsa(PacienteDTO pacienteDTO) throws ParseException {

		List<CobrancaAvulsa> listaCobrancaAvulsa = pacienteDTO.getListaCobrancaAvulsa();

		if (listaCobrancaAvulsa == null) {
			listaCobrancaAvulsa = new ArrayList<CobrancaAvulsa>();
		}

		CobrancaAvulsa cobrancaAvulsa = new CobrancaAvulsa();
		cobrancaAvulsa.setNome(pacienteDTO.getCobrancaAvulsa().getNome());
		cobrancaAvulsa.setDataFormatada(pacienteDTO.getCobrancaAvulsa().getDataFormatada());

		if (pacienteDTO.getCobrancaAvulsa().getValorTransiente() != ""
				&& !pacienteDTO.getCobrancaAvulsa().getValorTransiente().isEmpty()) {
			cobrancaAvulsa.setValor(new BigDecimal(
					pacienteDTO.getCobrancaAvulsa().getValorTransiente().replace(".", "").replace(",", ".")));
			cobrancaAvulsa.setValorTransiente(pacienteDTO.getCobrancaAvulsa().getValorTransiente());
		}

		if (pacienteDTO.getCobrancaAvulsa().getDataFormatada() != null
				&& pacienteDTO.getCobrancaAvulsa().getDataFormatada() != "") {
			Date dataFormatada = new SimpleDateFormat("yyyy-MM-dd")
					.parse(pacienteDTO.getCobrancaAvulsa().getDataFormatada());
			cobrancaAvulsa.setData(dataFormatada);
		}

		listaCobrancaAvulsa.add(cobrancaAvulsa);

		pacienteDTO.setListaCobrancaAvulsa(listaCobrancaAvulsa);
		pacienteDTO.setCobrancaAvulsa(null);

		return pacienteDTO;
	}

	public void gerarNumeroAleatorio(Contrato contrato) {

		List<Integer> numeros = new ArrayList<Integer>();
		for (int i = 1; i < 100001; i++) {
			numeros.add(i);
		}

		Collections.shuffle(numeros);

		Random random = new Random();
		int x = random.nextInt(10001);

		contrato.setNumero(numeros.get(x).toString());
	}

	@Override
	public PacienteDTO configurarFormAlterar(PacienteDTO pacienteDTO, HttpServletRequest request) throws ParseException {
		
		if (pacienteDTO.getPaciente().getCodigoSexoTransiente().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setCodigoSexoTransiente(null);
		}
		if (pacienteDTO.getPaciente().getDataNascimentoFormatada().equalsIgnoreCase("")) {
			pacienteDTO.getPaciente().setDataNascimentoFormatada(null);
		}

		if (pacienteDTO.getPaciente().getDataNascimentoFormatada() != null) {
			configurarDataPaciente(pacienteDTO);
		}

		if (pacienteDTO.getPaciente().getCodigoSexoTransiente() != null) {
			pacienteDTO.getPaciente().setSexoEnum(
					SexoEnum.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getCodigoSexoTransiente())));
		}

		if (pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != null
				&& pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente() != "") {
			pacienteDTO.getPaciente().getDocumentacao().setOrgaoEmissorEnum(OrgaoEmissorEnum.getEnumPorCodigo(
					Integer.parseInt(pacienteDTO.getPaciente().getDocumentacao().getOrgaoEmissorTransiente())));
		}

		if (pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != null
				&& pacienteDTO.getPaciente().getEndereco().getEstadoTransiente() != "") {
			pacienteDTO.getPaciente().getEndereco().setEstado(UfEnum
					.getEnumPorCodigo(Integer.parseInt(pacienteDTO.getPaciente().getEndereco().getEstadoTransiente())));
		}

		Paciente paciente = pacienteDTO.getPaciente();
		paciente.setDataNascimento(pacienteDTO.getDataPaciente());
		paciente.setCliente(HomeController.getUsuarioLogado(request).getCliente());
		pacienteDTO.setPaciente(paciente);
		
		return pacienteDTO;
	}

	
}