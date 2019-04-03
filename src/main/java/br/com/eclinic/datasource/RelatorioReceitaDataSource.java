/*package br.com.eclinic.datasource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.ui.Model;

import br.com.eclinic.entity.receita.Receita;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioReceitaDataSource extends
		JRAbstractBeanDataSourceProvider {

	private static final String BARRA = "/";
	public List<Receita> receitas;

	public RelatorioReceitaDataSource() {
		super(Receita.class);
	}

	public RelatorioReceitaDataSource(Receita receita, Model model) {
		this();
		this.receitas = new ArrayList<Receita>();
		this.receitas.add(receita);
		try {
			model.addAttribute("datasource", create(null));

			GregorianCalendar dataReceita = new GregorianCalendar();
			dataReceita.setTime(new Date());

			model.addAttribute("dataReceita",
					dataReceita.get(Calendar.DAY_OF_MONTH) + BARRA
							+ (dataReceita.get(Calendar.MONTH) + 1) + BARRA
							+ dataReceita.get(Calendar.YEAR));

		} catch (JRException e) {
			model.addAttribute("datasource", new JREmptyDataSource());
		}
		// csv, html, pdf and xls
		model.addAttribute("format", "pdf");
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		if (this.receitas == null || this.receitas.isEmpty()) {
			return new JREmptyDataSource();
		}
		return new JRBeanCollectionDataSource(this.receitas);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		this.receitas.clear();
		this.receitas = null;

	}

}
*/