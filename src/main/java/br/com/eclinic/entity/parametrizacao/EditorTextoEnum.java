package br.com.eclinic.entity.parametrizacao;

public enum EditorTextoEnum {
	
	NOTEPAD(1, "Notepad"), WORD(2, "Microsoft Word"), WRITER(2, "LibreOffice Writer");
	
	
	private int codigo;
	private String descricao;

	private EditorTextoEnum(int codigo, String descricao) {
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
	public static EditorTextoEnum getEnumPorCodigo(Integer codigo) {

		EditorTextoEnum editorTextoEnum = null;

		if (codigo == 1) {
			editorTextoEnum = editorTextoEnum.NOTEPAD;
		}
		if (codigo == 2) {
			editorTextoEnum = editorTextoEnum.WORD;
		}
		if (codigo == 3) {
			editorTextoEnum = editorTextoEnum.WRITER;
		}
		
		return editorTextoEnum;
	}

}

