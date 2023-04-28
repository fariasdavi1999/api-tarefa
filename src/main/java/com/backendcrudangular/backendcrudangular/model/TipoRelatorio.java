package com.backendcrudangular.backendcrudangular.model;

public enum TipoRelatorio {

	PRIMEIRO("Primeiro"),
	
	SEGUNDO("Segundo");
	
	private final String descricaoTipo;
	
	TipoRelatorio(String descricaoTipo){
		this.descricaoTipo = descricaoTipo;
	}
	
	public String getDescricaoTipo() {
		return descricaoTipo;
	}
	
}
