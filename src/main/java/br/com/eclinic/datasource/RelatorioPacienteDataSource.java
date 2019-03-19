package br.com.eclinic.datasource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.ui.Model;

import br.com.eclinic.entity.paciente.Paciente;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRAbstractBeanDataSourceProvider;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioPacienteDataSource extends
		JRAbstractBeanDataSourceProvider {

	private static final String BARRA = "/";
	public List<Paciente> pacientes;

	public RelatorioPacienteDataSource() {
		super(Paciente.class);
	}

	public RelatorioPacienteDataSource(Paciente paciente, Model model) {
		this();
		this.pacientes = new ArrayList<Paciente>();
		this.pacientes.add(paciente);
		try {
			model.addAttribute("datasource", create(null));
			Long idade = calcularIdadePaciente(paciente);
			model.addAttribute("idade", idade);
			model.addAttribute("sexo", paciente.getSexoEnum().getDescricao());

			GregorianCalendar dataCadastro = new GregorianCalendar();
			if(paciente.getDataInclusao() != null){
				dataCadastro.setTime(paciente.getDataInclusao());

				model.addAttribute("dataCadastro",
						dataCadastro.get(Calendar.DAY_OF_MONTH) + BARRA
								+ (dataCadastro.get(Calendar.MONTH) + 1) + BARRA
								+ dataCadastro.get(Calendar.YEAR));
			}
			
			
			GregorianCalendar dataNascimento = new GregorianCalendar();
			if(paciente.getDataNascimento() != null){
				dataNascimento.setTime(paciente.getDataNascimento());
				model.addAttribute("dataNascimento",
						dataNascimento.get(Calendar.DAY_OF_MONTH) + BARRA
								+ (dataNascimento.get(Calendar.MONTH) + 1) + BARRA
								+ dataNascimento.get(Calendar.YEAR));
			}
			
			
			
			
			if(paciente.getEstadoCivilEnum() != null){
				model.addAttribute("estadoCivil", paciente.getEstadoCivilEnum()
						.getDescricao());
			}else{
				model.addAttribute("estadoCivil", "");
			}
			
		} catch (JRException e) {
			model.addAttribute("datasource", new JREmptyDataSource());
		}
		// csv, html, pdf and xls
		model.addAttribute("format", "pdf");
	}

	private Long calcularIdadePaciente(Paciente paciente) {
		GregorianCalendar dataAtual = new GregorianCalendar();
		GregorianCalendar dataNascimento = new GregorianCalendar();

		if (paciente.getDataNascimento() != null) {
			dataNascimento.setTime(paciente.getDataNascimento());
		}

		int anoAtual = dataAtual.get(Calendar.YEAR);
		int anoNascimento = dataNascimento.get(Calendar.YEAR);

		return new Long(anoAtual - anoNascimento);
	}

	@Override
	public JRDataSource create(JasperReport arg0) throws JRException {
		if (this.pacientes == null || this.pacientes.isEmpty()) {
			return new JREmptyDataSource();
		}
		return new JRBeanCollectionDataSource(this.pacientes);
	}

	@Override
	public void dispose(JRDataSource arg0) throws JRException {
		this.pacientes.clear();
		this.pacientes = null;

	}

}
