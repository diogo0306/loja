package br.com.eclinic.entity.produto;

public enum TipoProdutoEnum {

	BRINCO(1, "Brinco"), COLAR(2, "Colar"), ANEL(3, "Anel"), PULSEIRA(4, "Pulseira");

	private int codigo;
	private String descricao;

	private TipoProdutoEnum(int codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	@SuppressWarnings("static-access")
	public static TipoProdutoEnum getEnumPorCodigo(Integer codigo) {

		TipoProdutoEnum tipoProdutoEnum = null;

		if (codigo == 1) {
			tipoProdutoEnum = tipoProdutoEnum.BRINCO;
		}
		if (codigo == 2) {
			tipoProdutoEnum = tipoProdutoEnum.COLAR;
		}
		if (codigo == 3) {
			tipoProdutoEnum = tipoProdutoEnum.ANEL;
		}
		if (codigo == 4) {
			tipoProdutoEnum = tipoProdutoEnum.PULSEIRA;
		}

		return tipoProdutoEnum;
	}

}
