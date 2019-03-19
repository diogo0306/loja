package br.com.eclinic.validator.Representante;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import br.com.eclinic.entity.representante.Representante;

public class RepresentanteGroupSequenceProvider implements DefaultGroupSequenceProvider<Representante> {

	@Override
	public List<Class<?>> getValidationGroups(Representante representante) {
		List<Class<?>> grupos = new ArrayList<Class<?>>();
		grupos.add(Representante.class);

		if (isPessoaSelecionada(representante)) {
			grupos.add(representante.getTipoRepresentanteEnum().getGrupo());
		}
		return grupos;
	}

	private boolean isPessoaSelecionada(Representante representante) {
		return representante != null && representante.getTipoRepresentanteEnum() != null;
	}
}