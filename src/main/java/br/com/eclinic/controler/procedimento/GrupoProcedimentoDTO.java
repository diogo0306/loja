package br.com.eclinic.controler.procedimento;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.eclinic.entity.procedimento.GrupoProcedimento;
import br.com.eclinic.entity.procedimento.Procedimento;

@Component
public class GrupoProcedimentoDTO {

	private GrupoProcedimento grupoProcedimento;
	private Procedimento procedimento;
	private List<Procedimento> listaProcedimentos;

	public GrupoProcedimento getGrupoProcedimento() {
		return grupoProcedimento;
	}

	public void setGrupoProcedimento(GrupoProcedimento grupoProcedimento) {
		this.grupoProcedimento = grupoProcedimento;
	}

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public List<Procedimento> getListaProcedimentos() {
		return listaProcedimentos;
	}

	public void setListaProcedimentos(List<Procedimento> listaProcedimentos) {
		this.listaProcedimentos = listaProcedimentos;
	}
}
