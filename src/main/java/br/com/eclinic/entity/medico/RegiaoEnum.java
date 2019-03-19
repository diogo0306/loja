package br.com.eclinic.entity.medico;

public enum RegiaoEnum {
	
	PRIMEIRA_REGIAO(1, "1ª Região"), SEGUNDA_REGIAO(2, "2ª Região"), TERCEIRA_REGIAO(3, "3ª Região"),
	QUARTA_REGIAO(4, "4ª Região"), QUINTA_REGIAO(5, "5ª Regiao"), SEXTA_REGIAO(6, "6ª Região"), SETIMA_REGIAO(7, "7ª Região"),
	OITAVA_REGIAO(8, "8ª Região"), NONA_REGIAO(9, "9ª Região"), DECIMA_REGIAO(10, "10ª Região"), DECIMA_PRIMEIRA(11, "11ª Região"),
	DECIMA_SEGUNDA(12, "12ª Região"), DECIMA_TERCEIRA(13, "13ª Região"), DECIMA_QUARTA(14, "14ª Região"), DECIMA_QUINTA(15, "15ª Região"),
	DECIMA_SEXTA(16, "16ª Região"), DECIMA_SETIMA(17, "17ª Região"), DECIMA_OITAVA(18, "18ª Região"), DECIMA_NONA(19, "19ª Região"),
	VIGESSIMA(20, "20ª Região");
	
	private int codigo;
	private String descricao;
	
	private RegiaoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@SuppressWarnings("static-access")
	public static RegiaoEnum getEnumPorCodigo (Integer codigo) {
		
		RegiaoEnum regiaoEnum = null;
		
		if (codigo == 1){
			regiaoEnum = regiaoEnum.PRIMEIRA_REGIAO;
		}	
		if (codigo == 2){
			regiaoEnum = regiaoEnum.SEGUNDA_REGIAO;
		}		
		if (codigo == 3){
			regiaoEnum = regiaoEnum.TERCEIRA_REGIAO;
		}	
		if (codigo == 4){
			regiaoEnum = regiaoEnum.QUARTA_REGIAO;
		}
		if (codigo == 5){
			regiaoEnum = regiaoEnum.QUINTA_REGIAO;
		}
		if (codigo == 6){
			regiaoEnum = regiaoEnum.SEXTA_REGIAO;
		}
		if (codigo == 7){
			regiaoEnum = regiaoEnum.SETIMA_REGIAO;
		}
		if (codigo == 8){
			regiaoEnum = regiaoEnum.OITAVA_REGIAO;
		}
		if (codigo == 9){
			regiaoEnum = regiaoEnum.NONA_REGIAO;
		}
		if (codigo == 10){
			regiaoEnum = regiaoEnum.DECIMA_REGIAO;
		}
		if (codigo == 11){
			regiaoEnum = regiaoEnum.DECIMA_PRIMEIRA;
		}
		if (codigo == 12){
			regiaoEnum = regiaoEnum.DECIMA_SEGUNDA;
		}
		if (codigo == 13){
			regiaoEnum = regiaoEnum.DECIMA_TERCEIRA;
		}
		if (codigo == 14){
			regiaoEnum = regiaoEnum.DECIMA_QUARTA;
		}
		if (codigo == 15){
			regiaoEnum = regiaoEnum.DECIMA_QUINTA;
		}
		if (codigo == 16){
			regiaoEnum = regiaoEnum.DECIMA_SEXTA;
		}
		if (codigo == 17){
			regiaoEnum = regiaoEnum.DECIMA_SETIMA;
		}
		if (codigo == 18){
			regiaoEnum = regiaoEnum.DECIMA_OITAVA;
		}
		if (codigo == 19){
			regiaoEnum = regiaoEnum.DECIMA_NONA;
		}
		if (codigo == 20){
			regiaoEnum = regiaoEnum.VIGESSIMA;
		}
		
		return regiaoEnum;
	}
}
