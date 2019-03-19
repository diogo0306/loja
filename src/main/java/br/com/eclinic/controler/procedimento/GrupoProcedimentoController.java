package br.com.eclinic.controler.procedimento;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;
import br.com.eclinic.entity.procedimento.RelacaoGrupoProcedimento;
import br.com.eclinic.entity.procedimento.TipoAtendimentoEnum;
import br.com.eclinic.service.procedimento.GrupoProcedimentoService;
import br.com.eclinic.service.procedimento.ProcedimentoService;
import br.com.eclinic.service.procedimento.RelacaoGrupoProcedimentoService;

@Controller
public class GrupoProcedimentoController extends EclinicController {

	@Autowired
	private GrupoProcedimentoService grupoProcedimentoService;
	@Autowired
	private ProcedimentoService procedimentoService;
	@Autowired
	private RelacaoGrupoProcedimentoService relacaoGrupoProcedimentoService;

	@RequestMapping(value = "/grupos", method = RequestMethod.GET)
	private String grupos(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("grupoProcedimento", new GrupoProcedimento());
		model.addAttribute("grupos", grupoProcedimentoService.listar());
		return "grupos";
	}

	@RequestMapping(value = "/grupos", method = RequestMethod.POST)
	private String pesquisar(@ModelAttribute GrupoProcedimento grupoProcedimento, Model model,
			HttpServletRequest request) throws Exception {
		model.addAttribute("grupoProcedimento", grupoProcedimento);
		model.addAttribute("grupos", grupoProcedimentoService.consultar(grupoProcedimento));
		return "grupos";
	}

	@RequestMapping(value = "grupoProcedimento/salvar", method = RequestMethod.GET)
	private String salvarGrupo(Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("grupoProcedimento", new GrupoProcedimento());
		model.addAttribute("tipos", TipoAtendimentoEnum.values());

		return "grupo_procedimento_incluir";
	}

	@RequestMapping(value = "grupoProcedimento/salvar", method = RequestMethod.POST)
	private ModelAndView salvar(@ModelAttribute @Valid GrupoProcedimento grupoProcedimento, BindingResult result,
			Model model, final RedirectAttributes redirectAttributes) throws Exception {
		try {

			if (result.hasErrors()) {
				ModelAndView mav = new ModelAndView("grupo_procedimento_incluir");
				mav.addObject("grupoProcedimento", grupoProcedimento);
				mav.addObject("tipos", TipoAtendimentoEnum.values());
				return mav;
			}

			if (grupoProcedimento.getTipoTransiente() != null && grupoProcedimento.getTipoTransiente() != "") {
				grupoProcedimento.setTipoAtendimento(
						TipoAtendimentoEnum.getEnumPorCodigo(Integer.parseInt(grupoProcedimento.getTipoTransiente())));
			}

			grupoProcedimento.setNome(grupoProcedimento.getNome().toUpperCase());

			grupoProcedimentoService.salvar(grupoProcedimento);

			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/grupos");
			return retorno;

		} catch (Exception e) {
			throw new Exception(e);
		}
	}

	@RequestMapping(value = "/grupoProcedimento/procedimento/adicionar" + "/{pk}", method = RequestMethod.GET)
	private String adicionarProcedimento(@PathVariable Long pk, Model model) throws Exception {
		GrupoProcedimento grupoProcedimento = grupoProcedimentoService.buscar(pk);
		GrupoProcedimentoDTO grupoProcedimentoDTO = new GrupoProcedimentoDTO();
		grupoProcedimentoDTO.setGrupoProcedimento(grupoProcedimento);
		model.addAttribute("grupoProcedimentoDTO", grupoProcedimentoDTO);
		model.addAttribute("procedimentos", procedimentoService.listar());
		return "adicionar_procedimento";
	}

	@RequestMapping(value = "/grupoProcedimento/visualizar" + "/{pk}", method = RequestMethod.GET)
	private String visualizar(@PathVariable Long pk, Model model) throws Exception {
		GrupoProcedimento grupoProcedimento = grupoProcedimentoService.buscar(pk);
		List<Procedimento> procedimentos = grupoProcedimentoService.consultarProcedimentosPorGrupo(grupoProcedimento);
		grupoProcedimento.setProcedimentos(procedimentos);
		model.addAttribute("grupoProcedimento", grupoProcedimento);
		return "grupo_procedimento_visualizar";
	}

	@RequestMapping(value = "/grupoProcedimento/procedimento/adicionar", method = RequestMethod.POST)
	private ModelAndView addProcedimento(@ModelAttribute GrupoProcedimentoDTO grupoProcedimentoDTO, Model model,
			HttpServletRequest request) throws Exception {

		List<Procedimento> lista = grupoProcedimentoDTO.getListaProcedimentos();
		List<Procedimento> listaCorrente = new ArrayList<Procedimento>();

		if (lista == null) {
			lista = new ArrayList<Procedimento>();
		}

		if (grupoProcedimentoDTO.getProcedimento().getId() != null) {
			Procedimento procedimento = procedimentoService.buscar(grupoProcedimentoDTO.getProcedimento().getId());
			lista.add(procedimento);
		}

		for (Procedimento procedimento : lista) {
			if (procedimento.getId() != null && procedimento.getId() != 0) {
				listaCorrente.add(procedimento);
			}
		}

		grupoProcedimentoDTO.setListaProcedimentos(listaCorrente);
		grupoProcedimentoDTO.setProcedimento(null);

		ModelAndView mav = new ModelAndView("adicionar_procedimento");
		mav.addObject("grupoProcedimentoDTO", grupoProcedimentoDTO);
		mav.addObject("procedimentos", procedimentoService.listar());

		return mav;
	}

	@RequestMapping(value = "/grupoProcedimento/procedimento/salvar", method = RequestMethod.POST)
	public String salvarProcedimentoAoGrupo(@ModelAttribute GrupoProcedimentoDTO grupoProcedimentoDTO,
			BindingResult result, Model model, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws ParseException {

		try {
			for (Procedimento procedimento : grupoProcedimentoDTO.getListaProcedimentos()) {
				RelacaoGrupoProcedimento relacao = new RelacaoGrupoProcedimento();
				relacao.setGrupoProcedimento(grupoProcedimentoDTO.getGrupoProcedimento());
				relacao.setProcedimento(procedimento);
				relacaoGrupoProcedimentoService.salvar(relacao);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("inclusao.sucesso"));
		return "redirect:/grupos";
	}
}
