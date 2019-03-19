package br.com.eclinic.entity.paciente;

public enum EstadoCivilEnum {

	SOLTEIRO("Solteiro(a)", 1, 21, 31, 41), CASADO("Casado(a)", 2, 22, 32, 42), SEPARADO(
			"Separado(a)", 3, 23, 33, 43), DIVORCIADO("Divorciado(a)", 4, 24,
			34, 44), VIUVO("Viuvo(a)", 5, 25, 35, 45), OUTROS("Outros", 6, 26,
			36, 46);

	private String descricao;
	private int codigo;
	private int codigoTribualContas;
	private int codigoRaes;
	private int codigoOutrosTribunais;

	// FIXME rhas verificar a descrição do 3� c�digo.
	private EstadoCivilEnum(String descricao, int codigo,
			int codigoTribualContas, int codigoRaes, int codigoOutrosTribunais) {
		this.descricao = descricao;
		this.codigo = codigo;
	}

	/**
	 * Recupera o c�digo do enum.
	 */
	public int getCodigo() {
		return this.codigo;
	}

	/**
	 * Recupera a descrição do enum.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Configura a descrição do enum.
	 * 
	 * @param descricao
	 *            descrição.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Recupera o c�digo do estado civil no tribunal de contas.
	 */
	public int getCodigoTribualContas() {
		return codigoTribualContas;
	}

	/**
	 * Configura o c�digo do estado civil no tribunal de contas.
	 * 
	 * @param codigoTribualContas
	 *            c�digo do estado civil no tribunal de contas
	 */
	public void setCodigoTribualContas(int codigoTribualContas) {
		this.codigoTribualContas = codigoTribualContas;
	}

	/**
	 * Recupera o c�digo do estado civil na RAES.
	 */
	public int getCodigoRaes() {
		return codigoRaes;
	}

	/**
	 * Configura o c�digo do estado civil na RAES.
	 * 
	 * @param codigoRaes
	 *            c�digo do estado civil na RAES.
	 */
	public void setCodigoRaes(int codigoRaes) {
		this.codigoRaes = codigoRaes;
	}

	/**
	 * Recupera o c�digo do estado civil em outros tribunais..
	 */
	public int getCodigoOutrosTribunais() {
		return codigoOutrosTribunais;
	}

	/**
	 * Configura o c�digo do estado civil em outros tribunais.
	 * 
	 * @param codigoOutrosTribunais
	 *            c�digo do estado civil em outros tribunais.
	 */
	public void setCodigoOutrosTribunais(int codigoOutrosTribunais) {
		this.codigoOutrosTribunais = codigoOutrosTribunais;
	}
	
	public static EstadoCivilEnum getEnumPorCodigo (Integer codigo){
		
		EstadoCivilEnum estadoCivilEnum = null;
		
		if(codigo == 1){
			estadoCivilEnum = EstadoCivilEnum.SOLTEIRO;
		}
		if(codigo == 2){
			estadoCivilEnum = EstadoCivilEnum.CASADO;
		}
		if(codigo == 3){
			estadoCivilEnum = EstadoCivilEnum.SEPARADO;
		}
		if(codigo == 4){
			estadoCivilEnum = EstadoCivilEnum.DIVORCIADO;
		}
		if(codigo == 5){
			estadoCivilEnum = EstadoCivilEnum.VIUVO;
		}
		if(codigo == 6){
			estadoCivilEnum = EstadoCivilEnum.OUTROS;
		}
		
		return estadoCivilEnum;
	}

}