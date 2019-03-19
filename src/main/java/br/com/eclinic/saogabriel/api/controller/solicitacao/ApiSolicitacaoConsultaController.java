package br.com.eclinic.saogabriel.api.controller.solicitacao;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.eclinic.entity.solicitacao.SolicitacaoConsulta;
import br.com.eclinic.entity.solicitacao.SolicitacaoExame;
import br.com.eclinic.service.solicitacao.SolicitacaoConsultaService;

import br.com.eclinic.service.solicitacao.SolicitacaoExameService;

@Controller
public class ApiSolicitacaoConsultaController {
	
	@Autowired
    private SolicitacaoConsultaService solicitacaoService;
	
	@Autowired
	private SolicitacaoExameService solicitacaoExameService;

	@RequestMapping(value = "/api/enviar/solicitacao/consulta", produces = "application/json")
    public @ResponseBody SolicitacaoConsulta salvar(@RequestBody SolicitacaoConsulta solicitacaoConsulta, Locale locale, Model model,
                              HttpServletRequest request) throws Exception {
		solicitacaoService.salvar(solicitacaoConsulta);
        return solicitacaoConsulta;
    }
	@RequestMapping(value = "/api/enviar/solicitacao/exame", produces = "application/json")
    public @ResponseBody SolicitacaoExame salvar(@RequestBody SolicitacaoExame solicitacaoExame, Locale locale, Model model,
                              HttpServletRequest request) throws Exception {
		solicitacaoExameService.salvar(solicitacaoExame);
        return solicitacaoExame;
    }
}