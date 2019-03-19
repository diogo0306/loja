package br.com.eclinic.entity.procedimento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "relacao_grupo_procedimento")
@SequenceGenerator(name = "sequenceRelacaoGrupoProcedimento", sequenceName = "rel_grupo_procedimento_id_seq")
public class RelacaoGrupoProcedimento implements Serializable {

	private static final long serialVersionUID = -3468385028743768746L;
	private Long id;
	private GrupoProcedimento grupoProcedimento;
	private Procedimento procedimento;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rel_id", nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grup_proc_id", nullable = true)
	public GrupoProcedimento getGrupoProcedimento() {
		return grupoProcedimento;
	}
	public void setGrupoProcedimento(GrupoProcedimento grupoProcedimento) {
		this.grupoProcedimento = grupoProcedimento;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proc_id", nullable = true)
	public Procedimento getProcedimento() {
		return procedimento;
	}
	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

}
