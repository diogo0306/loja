package br.com.eclinic.controler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.base.Strings;

@SuppressWarnings({"rawtypes","unchecked"})
@Controller
public class EclinicController {

	protected static final String MESSAGE = "message";
	protected static final String MESSAGE_WARNING = "messageWarning";
	protected static final String MESSAGE_ERROR = "messageError";
	protected static final String PAGINACAO_AVANCAR = "avancar";
	protected static final int PAGINACAO_QTD_REGISTRO_POR_PAGINA = 10;

	private PagedListHolder pagedListHolder;

	@Autowired
	private ReloadableResourceBundleMessageSource message_resource;

	private static Locale LOCALE = LocaleContextHolder.getLocale();

	protected String getMensagem(String errorCode) {
		return message_resource.getMessage(errorCode, null, LOCALE);
	}

	protected void navegarPaginacao(String acao, Model model) {
		if (PAGINACAO_AVANCAR.equalsIgnoreCase(acao)) {
			pagedListHolder.nextPage();
		} else {
			pagedListHolder.previousPage();
		}
		model.addAttribute("isPrimeiraPagina", pagedListHolder.isFirstPage());
		model.addAttribute("isUltimaPagina", pagedListHolder.isLastPage());
		model.addAttribute("exibirPaginacao", true);

		model.addAttribute("quantidadeRegistros", pagedListHolder.getNrOfElements());
		model.addAttribute("paginacaoDe", pagedListHolder.getPage() + 1);
		model.addAttribute("paginacaoAte", pagedListHolder.getPageCount());
		model.addAttribute("quantidadeRegistrosPagina", pagedListHolder.getPageList().size());

	}

	protected void configurarPaginacao(List lista, Model model) {
		pagedListHolder = new PagedListHolder(lista);
		pagedListHolder.setPageSize(PAGINACAO_QTD_REGISTRO_POR_PAGINA);

		model.addAttribute("isPrimeiraPagina", true);
		model.addAttribute("exibirPaginacao", lista != null && lista.size() > PAGINACAO_QTD_REGISTRO_POR_PAGINA);

		model.addAttribute("quantidadeRegistros", pagedListHolder.getNrOfElements());
		model.addAttribute("paginacaoDe", pagedListHolder.getPage() + 1);
		model.addAttribute("paginacaoAte", pagedListHolder.getPageCount());
		model.addAttribute("quantidadeRegistrosPagina", pagedListHolder.getPageList().size());
	}

	protected void configurarPaginacao(List lista, Model model, int quantidadeRegistros) {
		pagedListHolder = new PagedListHolder(lista);
		pagedListHolder.setPageSize(quantidadeRegistros);

		model.addAttribute("isPrimeiraPagina", true);
		model.addAttribute("exibirPaginacao", lista != null && lista.size() > PAGINACAO_QTD_REGISTRO_POR_PAGINA);

		model.addAttribute("quantidadeRegistros", pagedListHolder.getNrOfElements());
		model.addAttribute("paginacaoDe", pagedListHolder.getPage() + 1);
		model.addAttribute("paginacaoAte", pagedListHolder.getPageCount());
		model.addAttribute("quantidadeRegistrosPagina", pagedListHolder.getPageList().size());
	}

	protected void configurarPaginacao(List lista, ModelAndView model) {
		pagedListHolder = new PagedListHolder(lista);
		pagedListHolder.setPageSize(PAGINACAO_QTD_REGISTRO_POR_PAGINA);

		model.addObject("isPrimeiraPagina", true);
		model.addObject("exibirPaginacao", lista != null && lista.size() > PAGINACAO_QTD_REGISTRO_POR_PAGINA);

		model.addObject("quantidadeRegistros", pagedListHolder.getNrOfElements());
		model.addObject("paginacaoDe", pagedListHolder.getPage() + 1);
		model.addObject("paginacaoAte", pagedListHolder.getPageCount());
		model.addObject("quantidadeRegistrosPagina", pagedListHolder.getPageList().size());

	}

	public PagedListHolder getPagedListHolder() {
		return pagedListHolder;
	}

	protected Double transformStringToDouble(String valor) {
		Double retorno = null;
		if (!Strings.isNullOrEmpty(valor)) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
			retorno = Double.parseDouble(valor);
		}
		return retorno;
	}

	protected BigDecimal transformStringToBigDecimal(String valor) {
		BigDecimal retorno = null;
		if (!Strings.isNullOrEmpty(valor)) {
			valor = valor.replace(".", "");
			valor = valor.replace(",", ".");
			retorno = new BigDecimal(valor);
		}
		return retorno;
	}

}
