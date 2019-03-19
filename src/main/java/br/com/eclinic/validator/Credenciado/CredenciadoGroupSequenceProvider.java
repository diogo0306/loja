package br.com.eclinic.validator.Credenciado;

/*public class CredenciadoGroupSequenceProvider implements DefaultGroupSequenceProvider<Credenciado> {

	@Override
	public List<Class<?>> getValidationGroups(Credenciado credenciado) {
		List<Class<?>> grupos = new ArrayList<Class<?>>();
		grupos.add(Credenciado.class);

		if (isPessoaSelecionada(credenciado)) {
			grupos.add(credenciado.getTipoPessoa().getGrupo());
		}
		return grupos;
	}

	private boolean isPessoaSelecionada(Credenciado credenciado) {
		return credenciado != null && credenciado.getTipoPessoa() != null;
	}
}*/