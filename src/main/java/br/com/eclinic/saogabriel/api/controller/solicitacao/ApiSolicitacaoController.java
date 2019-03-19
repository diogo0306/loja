package br.com.eclinic.saogabriel.api.controller.solicitacao;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.solicitacao.Solicitacao;
import br.com.eclinic.service.solicitacao.SolicitacaoService;

@Controller
public class ApiSolicitacaoController {

	@Autowired
	private SolicitacaoService solicitacaoService;

	@ExceptionHandler(Exception.class)
	@RequestMapping(value = "/api/solicitacao", produces = "application/json", method = RequestMethod.POST)
	public @ResponseBody Solicitacao salvar(@RequestBody Solicitacao solicitacao, Locale locale, Model model,
			HttpServletRequest request, HttpServletResponse response, Exception exception, Object handler)
			throws Exception {

		try {
			solicitacaoService.salvar(solicitacao);

		} catch (Exception e) {
			e.getMessage();
		}
		return solicitacao;
	}
}