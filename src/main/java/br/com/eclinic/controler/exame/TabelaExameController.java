package br.com.eclinic.controler.exame;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.eclinic.controler.EclinicController;
import br.com.eclinic.entity.exame.Exame;
import br.com.eclinic.entity.exame.Tabela;
import br.com.eclinic.entity.exame.TabelaDTO;
import br.com.eclinic.entity.exame.TabelaExame;
import br.com.eclinic.service.exame.ExameService;
import br.com.eclinic.service.exame.TabelaExameService;
import br.com.eclinic.service.exame.TabelaService;
import br.com.eclinic.validator.TabelaExameValidator;

@Controller
public class TabelaExameController extends EclinicController {

	@Autowired
	private TabelaExameService tabelaExameService;
	@Autowired
	private TabelaService tabelaService;
	@Autowired
	private ExameService exameService;

	@RequestMapping(value = "/tabela_exame/salvar", method = RequestMethod.GET)
	public String exibirSalvar(Model model, HttpServletRequest request) throws Exception {
		model.addAttribute("exameDTO", new ExameDTO());
		model.addAttribute("tabela", new Tabela());
		model.addAttribute("exame", new Exame());
		model.addAttribute("exames", exameService.listar());
		model.addAttribute("tabelas", tabelaService.listar());
		return "tabela_exame_incluir";
	}

	@RequestMapping(value = "/tabela_exame/salvar", method = RequestMethod.POST)
	public ModelAndView adicionar(Model model, @ModelAttribute ExameDTO exameDTO, BindingResult result,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws Exception {

		List<TabelaExame> lista = exameDTO.getListaTabelaExame();

		if (exameDTO.getTabelaExame().getValorTransiente().equalsIgnoreCase("0,00")) {
			exameDTO.getTabelaExame().setValorTransiente(null);
		}

		if (lista == null) {
			lista = new ArrayList<TabelaExame>();
		}

		TabelaExameValidator validator = new TabelaExameValidator();
		validator.validate(exameDTO.getTabelaExame(), result);

		ModelAndView mav = new ModelAndView("tabela_exame_incluir");
		mav.addObject("exameDTO", exameDTO);
		mav.addObject("tabela", new Tabela());
		mav.addObject("exame", new Exame());
		mav.addObject("exames", exameService.listar());
		mav.addObject("tabelas", tabelaService.listar());

		if (result.hasErrors()) {

			List<TabelaExame> listaCorrente = new ArrayList<TabelaExame>();

			for (TabelaExame tabela : lista) {
				if (tabela.getTabela().getId() != null) {
					listaCorrente.add(tabela);
				}
			}

			exameDTO.setListaTabelaExame(listaCorrente);
			mav.addObject("exameDTO", exameDTO);
			mav.addObject("tabela", new Tabela());
			mav.addObject("exame", new Exame());
			mav.addObject("exames", exameService.listar());
			mav.addObject("tabelas", tabelaService.listar());
			return mav;
		} else {

			Tabela tabelaBanco = tabelaService.buscar(exameDTO.getTabelaExame().getTabela().getId());
			Exame exame = exameService.buscar(exameDTO.getTabelaExame().getExame().getId());

			if (tabelaExameService.verificarPorExameTabela(tabelaBanco, exame) != null) {

				List<TabelaExame> listaCorrente = new ArrayList<TabelaExame>();

				for (TabelaExame tabela : lista) {
					if (tabela.getTabela().getId() != null) {
						listaCorrente.add(tabela);
					}
				}

				exameDTO.setListaTabelaExame(listaCorrente);
				mav.addObject(MESSAGE_ERROR, getMensagem("valor.inclusao.erro.existe"));
				mav.addObject("exameDTO", exameDTO);
				mav.addObject("tabela", new Tabela());
				mav.addObject("exame", new Exame());
				mav.addObject("exames", exameService.listar());
				mav.addObject("tabelas", tabelaService.listar());
				return mav;

			}

			TabelaExame tabelaExame = new TabelaExame();

			tabelaExame.setTabela(tabelaBanco);
			tabelaExame.setExame(exame);

			if (exameDTO.getTabelaExame().getValorTransiente() != null
					&& !exameDTO.getTabelaExame().getValorTransiente().isEmpty()) {
				tabelaExame.setValor(new BigDecimal(
						exameDTO.getTabelaExame().getValorTransiente().replace(".", "").replace(",", ".")));
			}

			lista.add(tabelaExame);

			List<TabelaExame> listaCorrente = new ArrayList<TabelaExame>();

			for (TabelaExame tabela : lista) {
				if (tabela.getTabela().getId() != null) {
					listaCorrente.add(tabela);
				}
			}

			exameDTO.setListaTabelaExame(listaCorrente);

			exameDTO.getTabelaExame().setExame(null);
			exameDTO.getTabelaExame().setValorTransiente(null);

			return mav;
		}
	}

	@RequestMapping(value = "/tabela_exame/salvar_valor", method = RequestMethod.POST)
	public ModelAndView salvarValor(@ModelAttribute ExameDTO exameDTO, BindingResult result, Model model,
			final RedirectAttributes redirectAttributes, HttpServletRequest request) throws ParseException {

		for (TabelaExame tabela : exameDTO.getListaTabelaExame()) {
			if (tabela.getTabela() != null) {
				tabelaExameService.salvar(tabela);
			}
		}

		ModelAndView modelAndView = new ModelAndView("tabela.pagina");
		modelAndView.addObject(MESSAGE, getMensagem("inclusao.sucesso"));
		modelAndView.addObject("tabelaExame", new Tabela());

		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value = "/tabela_exame/salvar-tabela", method = RequestMethod.POST)
	public TabelaDTO salvarTabela(@ModelAttribute Tabela tabela, BindingResult result,
			final RedirectAttributes redirectAttributes) throws Exception {

		TabelaDTO tabelaDTO = new TabelaDTO();
		tabela.setNome(tabela.getNome().toUpperCase());
		tabelaDTO.setTabela(tabela);

		if (tabela.getNome() == null || tabela.getNome().equalsIgnoreCase("")) {
			return null;
		}

		if (tabelaService.verificarNomeExistente(tabela) != null) {
			tabelaDTO.setStatus(false);
			tabelaDTO.setMensagem(getMensagem("tabela.inclusao.erro.existe"));
		} else {
			tabelaService.salvar(tabela);
			tabelaDTO.setStatus(true);
			tabelaDTO.setMensagem(getMensagem("inclusao.sucesso"));
		}

		return tabelaDTO;
	}

	@ResponseBody
	@RequestMapping(value = "/tabela_exame/salvar-exame", method = RequestMethod.POST)
	public TabelaDTO salvarExame(@ModelAttribute Exame exame, BindingResult result,
			final RedirectAttributes redirectAttributes) throws Exception {

		TabelaDTO tabelaDTO = new TabelaDTO();
		exame.setNome(exame.getNome().toUpperCase());
		tabelaDTO.setExame(exame);

		if (exame.getNome() == null || exame.getNome().equalsIgnoreCase("")) {
			return null;
		}

		if (exameService.verificarNomeExistente(exame) != null) {
			tabelaDTO.setStatus(false);
			tabelaDTO.setMensagem(getMensagem("tabela.inclusao.erro.existe"));
		} else {
			exameService.salvar(exame);
			tabelaDTO.setStatus(true);
			tabelaDTO.setMensagem(getMensagem("inclusao.sucesso"));
		}

		return tabelaDTO;
	}
}
