package br.com.eclinic.controler.exame;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.exception.NegocioException;
import br.com.eclinic.service.exame.TabelaExameService;
import br.com.eclinic.service.exame.TabelaService;
import br.com.eclinic.validator.TabelaValidator;

@Controller
public class TabelaController extends EclinicController {
	
	@Autowired
	private TabelaService tabelaService;
	@Autowired
	private TabelaExameService tabelaExameService;
	
	
	@RequestMapping(value = "/tabelas", method = RequestMethod.POST)
	public String tabelasPesquisar(@ModelAttribute Tabela tabela, Locale locale, Model model, HttpServletRequest request)
			throws Exception {

		return listar(tabela, locale, model, request);
	}

	@RequestMapping(value = "/tabelas", method = RequestMethod.GET)
	public String tabelas(Locale locale, Model model, HttpServletRequest request) throws Exception {

		model.addAttribute("tabelaExame", new Tabela());
		model.addAttribute("tabelaLista", tabelaService.listar());
		return "tabela.pagina";
	}
	
	private String listar(@ModelAttribute Tabela tabela, Locale locale, Model model, HttpServletRequest request) {
		
		List<Tabela> tabelaLista = new ArrayList<Tabela>();
		if(tabela.getNome() != null){
			Tabela tabelaConsulta = new Tabela();
			tabelaConsulta.setNome(tabela.getNome());
			tabelaLista = tabelaService.consultar(tabela);
		} else {
			tabelaLista = tabelaService.listar();
		}

		configurarPaginacao(tabelaLista, model);
		model.addAttribute("tabelaExame", tabela);
		model.addAttribute("tabelaLista", getPagedListHolder().getPageList());

		return "tabela.pagina";
	}
	
	@RequestMapping(value = "/tabela/visualizar/{tabelaId}", method = RequestMethod.GET)
	public ModelAndView tabelaVisualizar(@PathVariable String tabelaId, Locale locale, Model model,
			HttpServletRequest request) throws Exception {

		Tabela tabela = tabelaService.buscar(new Long(tabelaId));
		List<Exame> listaExames = tabelaExameService.consultarExamesPorTabela(tabela);
		tabela.setListaExame(listaExames);
	
		ModelAndView modelAndView = new ModelAndView("tabela_visualizar");

		modelAndView.addObject("tabela", tabela);

		return modelAndView;
	}
	
	@RequestMapping(value = "/tabela/salvar", method = RequestMethod.GET)
	public String exibirSalvar(@ModelAttribute Tabela tabela, Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("exameDTO", new ExameDTO());
		return "tabela_incluir";
	}
	
	@RequestMapping(value = "/tabela/salvar", method = RequestMethod.POST)
	public ModelAndView adicionar(Model model, @ModelAttribute ExameDTO exameDTO,
			BindingResult result, final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws Exception {
		
		List<Tabela> lista = exameDTO.getListaTabela();
		
		if(lista == null){
			lista = new ArrayList<Tabela>();
		}
		
		TabelaValidator tabelaValidator = new TabelaValidator();
		tabelaValidator.validate(exameDTO.getTabela(), result);
		
		ModelAndView mav = new ModelAndView("tabela_incluir");
		mav.addObject("exameDTO", exameDTO);
				
		if (result.hasErrors()) {
			
			List<Tabela> listaCorrente = new ArrayList<Tabela>();
			
			for(Tabela tabelaLista : lista){
				if(tabelaLista.getNome() == null){
					tabelaLista.setNome("");
				}
				if(tabelaLista.getNome().equalsIgnoreCase("")){
					tabelaLista.setNome(null);
				}
				if(tabelaLista.getNome() != null){
					listaCorrente.add(tabelaLista);
				}
			}
			
			exameDTO.setListaTabela(listaCorrente);
			mav.addObject("exameDTO", exameDTO);
			
			return mav;
		} else {
			
			if(tabelaService.verificarNomeExistente(exameDTO.getTabela()) != null){
				
				mav.addObject(MESSAGE_ERROR, getMensagem("tabela.inclusao.erro.existe"));
				
				List<Tabela> listaCorrente = new ArrayList<Tabela>();
				
				for(Tabela tabelaLista : lista){
					if(tabelaLista.getNome() == null){
						tabelaLista.setNome("");
					}
					if(tabelaLista.getNome().equalsIgnoreCase("")){
						tabelaLista.setNome(null);
					}
					if(tabelaLista.getNome() != null){
						listaCorrente.add(tabelaLista);
					}
				}
				
				exameDTO.setListaTabela(listaCorrente);
				mav.addObject("exameDTO", exameDTO);
				
				return mav;
			}
			
			Tabela tabela = new Tabela();
			
			tabela.setNome(exameDTO.getTabela().getNome().toUpperCase());
			tabela.setDescricao(exameDTO.getTabela().getDescricao());
			
			lista.add(tabela);
			
			List<Tabela> listaCorrente = new ArrayList<Tabela>();
			
			for(Tabela tabelaLista : lista){
				if(tabelaLista.getNome() == null){
					tabelaLista.setNome("");
				}
				if(tabelaLista.getNome().equalsIgnoreCase("")){
					tabelaLista.setNome(null);
				}
				if(tabelaLista.getNome() != null){
					listaCorrente.add(tabelaLista);
				}
			}
			
			exameDTO.setListaTabela(listaCorrente);
			
			exameDTO.getTabela().setNome(null);
			
			return mav;
		}		
	}
	
	@RequestMapping(value = "/tabela/salvar_tabela", method = RequestMethod.POST)
	public ModelAndView salvarTabela(@ModelAttribute ExameDTO exameDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		for(Tabela tabela : exameDTO.getListaTabela()){
			if(tabela.getNome() != null){
				tabelaService.salvar(tabela);
			}
		}
		
		ModelAndView modelAndView = new ModelAndView("tabela.pagina");
		modelAndView.addObject(MESSAGE, getMensagem("inclusao.sucesso"));
		modelAndView.addObject("tabelaExame", new Tabela());

		return modelAndView;
	}
	
	@ResponseBody
	@RequestMapping(value = "/tabela/paginacao/{acao}", method = RequestMethod.GET)
	public ModelAndView paginacao(@PathVariable String acao, Model model) throws Exception {		
		navegarPaginacao(acao, model);
		model.addAttribute("tabelaLista", getPagedListHolder().getPageList());
		model.addAttribute("tabelaExame", new Tabela());
		return new ModelAndView("tabela.lista");
	}
	
	@RequestMapping(value = "/tabela/alterar/{pk}", method = RequestMethod.GET)
	public String exibirAlterar(@PathVariable Long pk, Model model, HttpServletRequest request)
			throws Exception {
		Tabela tabela = tabelaService.buscar(pk);
		ExameDTO exameDTO = new ExameDTO();
		exameDTO.setTabela(tabela);
		
		if (tabela != null) {
			model.addAttribute("exameDTO", exameDTO);
			model.addAttribute("alterarTela", "");
			return "tabela_alterar";
		} else {
			return "redirect:../tabelas";
		}
	}
	
	@RequestMapping(value = "/tabela/alterar", method = RequestMethod.POST)
	public ModelAndView alterar(@ModelAttribute ExameDTO exameDTO, Model model, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request)
			throws NumberFormatException, NegocioException {

		TabelaValidator tabelaValidator = new TabelaValidator();
		tabelaValidator.validate(exameDTO.getTabela(), result);

		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("tabela_alterar");
			mav.addObject("exameDTO", exameDTO);
			return mav;
		} else {
			tabelaService.alterar(exameDTO.getTabela());
			redirectAttributes.addFlashAttribute(MESSAGE, getMensagem("alteracao.sucesso"));
			ModelAndView retorno = new ModelAndView("redirect:/tabelas");
			return retorno;
		}
	}

}
