package br.com.eclinic.entity.receita;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "medicamento")
@SequenceGenerator(name = "sequenceMedicamento", sequenceName = "medicamento_id_seq")
public class Medicamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String codigo;
	private String substancia1;
	private String substancia2;
	private String substancia3;
	private String substancia4;
	private String substancia5;
	private String substancia6;
	private String substancia7;
	private String substancia8;
	private String substancia9;
	private String substancia10;
	private String substancia11;
	private String dosagem1;
	private String dosagem2;
	private String dosagem3;
	private String dosagem4;
	private String dosagem5;
	private String dosagem6;
	private String dosagem7;
	private String dosagem8;
	private String dosagem9;
	private String dosagem10;
	private String dosagem11;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mdc_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "mdc_substancia_1", nullable = false)
	public String getSubstancia1() {
		return substancia1;
	}
	public void setSubstancia1(String substancia1) {
		this.substancia1 = substancia1;
	}
	
	@Column(name = "mdc_substancia_2", nullable = false)
	public String getSubstancia2() {
		return substancia2;
	}
	public void setSubstancia2(String substancia2) {
		this.substancia2 = substancia2;
	}
	@Column(name = "mdc_substancia_3", nullable = false)
	public String getSubstancia3() {
		return substancia3;
	}
	public void setSubstancia3(String substancia3) {
		this.substancia3 = substancia3;
	}
	@Column(name = "mdc_substancia_4", nullable = false)
	public String getSubstancia4() {
		return substancia4;
	}
	public void setSubstancia4(String substancia4) {
		this.substancia4 = substancia4;
	}
	@Column(name = "mdc_substancia_5", nullable = false)
	public String getSubstancia5() {
		return substancia5;
	}
	public void setSubstancia5(String substancia5) {
		this.substancia5 = substancia5;
	}
	@Column(name = "mdc_substancia_6", nullable = false)
	public String getSubstancia6() {
		return substancia6;
	}
	public void setSubstancia6(String substancia6) {
		this.substancia6 = substancia6;
	}
	@Column(name = "mdc_substancia_7", nullable = false)
	public String getSubstancia7() {
		return substancia7;
	}
	public void setSubstancia7(String substancia7) {
		this.substancia7 = substancia7;
	}
	@Column(name = "mdc_substancia_8", nullable = false)
	public String getSubstancia8() {
		return substancia8;
	}
	public void setSubstancia8(String substancia8) {
		this.substancia8 = substancia8;
	}
	@Column(name = "mdc_substancia_9", nullable = false)
	public String getSubstancia9() {
		return substancia9;
	}
	public void setSubstancia9(String substancia9) {
		this.substancia9 = substancia9;
	}
	@Column(name = "mdc_substancia_10", nullable = false)
	public String getSubstancia10() {
		return substancia10;
	}
	public void setSubstancia10(String substancia10) {
		this.substancia10 = substancia10;
	}
	@Column(name = "mdc_substancia_11", nullable = false)
	public String getSubstancia11() {
		return substancia11;
	}
	public void setSubstancia11(String substancia11) {
		this.substancia11 = substancia11;
	}
	
	@Column(name = "mdc_dosagem_1", nullable = false)
	public String getDosagem1() {
		return dosagem1;
	}
	public void setDosagem1(String dosagem1) {
		this.dosagem1 = dosagem1;
	}
	
	@Column(name = "mdc_dosagem_2", nullable = false)
	public String getDosagem2() {
		return dosagem2;
	}
	public void setDosagem2(String dosagem2) {
		this.dosagem2 = dosagem2;
	}
	@Column(name = "mdc_dosagem_3", nullable = false)
	public String getDosagem3() {
		return dosagem3;
	}
	public void setDosagem3(String dosagem3) {
		this.dosagem3 = dosagem3;
	}
	@Column(name = "mdc_dosagem_4", nullable = false)
	public String getDosagem4() {
		return dosagem4;
	}
	public void setDosagem4(String dosagem4) {
		this.dosagem4 = dosagem4;
	}
	@Column(name = "mdc_dosagem_5", nullable = false)
	public String getDosagem5() {
		return dosagem5;
	}
	public void setDosagem5(String dosagem5) {
		this.dosagem5 = dosagem5;
	}
	@Column(name = "mdc_dosagem_6", nullable = false)
	public String getDosagem6() {
		return dosagem6;
	}
	public void setDosagem6(String dosagem6) {
		this.dosagem6 = dosagem6;
	}
	@Column(name = "mdc_dosagem_7", nullable = false)
	public String getDosagem7() {
		return dosagem7;
	}
	public void setDosagem7(String dosagem7) {
		this.dosagem7 = dosagem7;
	}
	@Column(name = "mdc_dosagem_8", nullable = false)
	public String getDosagem8() {
		return dosagem8;
	}
	public void setDosagem8(String dosagem8) {
		this.dosagem8 = dosagem8;
	}
	@Column(name = "mdc_dosagem_9", nullable = false)
	public String getDosagem9() {
		return dosagem9;
	}
	public void setDosagem9(String dosagem9) {
		this.dosagem9 = dosagem9;
	}
	@Column(name = "mdc_dosagem_10", nullable = false)
	public String getDosagem10() {
		return dosagem10;
	}
	public void setDosagem10(String dosagem10) {
		this.dosagem10 = dosagem10;
	}
	@Column(name = "mdc_dosagem_11", nullable = false)
	public String getDosagem11() {
		return dosagem11;
	}
	public void setDosagem11(String dosagem11) {
		this.dosagem11 = dosagem11;
	}
	
	@Column(name = "mdc_codigo", nullable = false)
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
		

}
