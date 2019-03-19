package br.com.eclinic.controler.solicitacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.paciente.Paciente;
import br.com.eclinic.entity.solicitacao.SolicitacaoDTO;
import br.com.eclinic.entity.solicitacao.SolicitacaoExame;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.credenciado.CredenciadoService;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.paciente.PacienteService;
import br.com.eclinic.service.solicitacao.SolicitacaoExameService;

@Controller
public class SolicitacaoExameController extends EclinicController {
	
	private static final String VAZIO = "";
	
	@Autowired
	private SolicitacaoExameService solicitacaoExameService;
	@Autowired
	private PacienteService pacienteService;
	@Autowired
	private ExameService exameService;
	@Autowired
	private CredenciadoService credenciadoService;
	
	@RequestMapping(value = "/solicitacoes/exame", method = RequestMethod.GET)
	public String solicitacaoExame(Locale locale, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("solicitacaoExame", new SolicitacaoExame());
		model.addAttribute("solicitacoes", solicitacaoExameService.listar());
		return "solicitacao/exame";
	}
	
	@RequestMapping(value = "/solicitacoes/exame", method = RequestMethod.POST)
	public String solicitacoesPesquisar(@ModelAttribute SolicitacaoExame solicitacaoExame, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute("solicitacoes", solicitacaoExameService.consultar(solicitacaoExame));
		model.addAttribute("solicitacaoExame", solicitacaoExame);
		return "solicitacao/exame";
	}
	
	@RequestMapping(value = "/solicitacoes/exame/visualizar/{solicitacaoId}", method = RequestMethod.GET)
	public ModelAndView solicitacaoVisualizar(@PathVariable String solicitacaoId, Locale locale, Model model,
			HttpServletRequest request) throws Exception {
		
		ModelAndView modelAndView = new ModelAndView();
		
		SolicitacaoExame solicitacaoExame = solicitacaoExameService.buscar(new Long(solicitacaoId));
		Paciente paciente = pacienteService.buscar(solicitacaoExame.getIdPaciente());
		SolicitacaoDTO solicitacaoDTO = new SolicitacaoDTO();
		
		solicitacaoDTO.setPaciente(paciente);
		solicitacaoDTO.setSolicitacaoExame(solicitacaoExame);
		
		if(solicitacaoExame.getStatus().equalsIgnoreCase("APROVADO") || solicitacaoExame.getStatus().equalsIgnoreCase("REPROVADO")) {
			modelAndView = new ModelAndView("solicitacao.exame.detalhar");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
		} else {
			solicitacaoDTO.getSolicitacaoExame().setMotivo(null);			
			modelAndView = new ModelAndView("solicitacao.exame");
			modelAndView.addObject("solicitacaoDTO", solicitacaoDTO);
			modelAndView.addObject("exames", exameService.listar());
			modelAndView.addObject("credenciados", credenciadoService.listar());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/solicitacoes/exame/salvar", method = RequestMethod.POST)
	public ModelAndView salvarSolicitacaoExame(@ModelAttribute SolicitacaoDTO solicitacaoDTO,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException, NegocioException {
		
		ModelAndView retorno = new ModelAndView("redirect:/solicitacoes/exame");
		
		if (solicitacaoDTO.isReprovado() != null && solicitacaoDTO.isReprovado() == true) {
			if(solicitacaoDTO.getSolicitacaoExame().getId() != null && solicitacaoDTO.getSolicitacaoExame().getId() != 0) {
				SolicitacaoExame solicitacaoExame = solicitacaoExameService.buscar(solicitacaoDTO.getSolicitacaoExame().getId());
				solicitacaoExame.setStatus("REPROVADO");
				
				if (solicitacaoDTO.getSolicitacaoExame().getMotivo().equalsIgnoreCase("")
						|| solicitacaoDTO.getSolicitacaoExame().getMotivo() == null) {
					solicitacaoExame.setMotivo("NÃO INFORMADO");
				} else {
					solicitacaoExame.setMotivo(solicitacaoDTO.getSolicitacaoExame().getMotivo());
				}
				solicitacaoExameService.alterar(solicitacaoExame);
				retorno.addObject(MESSAGE, getMensagem("inclusao.sucesso"));
			}	
		}
		
		if (solicitacaoDTO.isAprovado() != null && solicitacaoDTO.isAprovado() == true) {
			if(solicitacaoDTO.getSolicitacaoExame().getId() != null && solicitacaoDTO.getSolicitacaoExame().getId() != 0) {
				SolicitacaoExame solicitacaoExame = solicitacaoExameService.buscar(solicitacaoDTO.getSolicitacaoExame().getId());
				if(solicitacaoDTO.getDataExameFormatada() != null && solicitacaoDTO.getDataExameFormatada() != VAZIO) {
					Date dataFormatada = new SimpleDateFormat("dd/MM/yyyy").parse(solicitacaoDTO.getDataExameFormatada());
					solicitacaoExame.setDataExame(dataFormatada);
				}
				solicitacaoExame.setIdExame(solicitacaoDTO.getExame().getId());
				solicitacaoExame.setIdCredenciado(solicitacaoDTO.getCredenciado().getId());
				solicitacaoExame.setStatus("APROVADO");
				solicitacaoExameService.alterar(solicitacaoExame);
				retorno.addObject(MESSAGE, getMensagem("inclusao.sucesso"));
			}
		}
		
		return retorno;
	}
}
