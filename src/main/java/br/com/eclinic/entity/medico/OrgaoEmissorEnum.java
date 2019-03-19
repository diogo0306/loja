package br.com.eclinic.entity.medico;

public enum OrgaoEmissorEnum {

	SDS(1, "SDS - Secretaria de Defesa Social"), SSP(2, "SSP - Secretaria de Segurança Pública"),PM(3,"PM - Polícia Militar"),
	PC(4,"PC - Policia Civil"), CNH(5,"CNH - Carteira Nacional de Habilitação"),
	CTPS(6,"CTPS - Carteira de Trabaho e Previdência Social"), DPF(7, "Departamento de Polícia Federal"),
	MTE(8, "Ministério do Trabalho e Emprego"), NAO_INFORMADO(9, "Não informado");
	
	private int codigo;
	private String descricao;

	private OrgaoEmissorEnum(int codigo, String descricao) {
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
	public static OrgaoEmissorEnum getEnumPorCodigo(Integer codigo) {

		OrgaoEmissorEnum OrgaoEmissorEnum = null;

		if (codigo == 1) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.SDS;
		}
		if (codigo == 2) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.SSP;		
		}
		if (codigo == 3) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.PM;		
		}
		if (codigo == 4) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.PC;		
		}
		if (codigo == 5) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.CNH;		
		}
		if (codigo == 6) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.CTPS;
		}
		if (codigo == 7) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.DPF;
		}
		if (codigo == 8) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.MTE;
		}
		if (codigo == 9) {
			OrgaoEmissorEnum = OrgaoEmissorEnum.NAO_INFORMADO;
		}
		
		return OrgaoEmissorEnum;
	}

}
